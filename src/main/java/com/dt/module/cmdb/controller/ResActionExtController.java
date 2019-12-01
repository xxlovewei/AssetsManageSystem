package com.dt.module.cmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.cmdb.entity.ResAction;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResActionService;
import com.dt.module.cmdb.service.impl.ResActionService;
import com.dt.module.cmdb.service.impl.ResExtService;

import java.util.ArrayList;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2019-12-01
 */
@Controller
@RequestMapping("/api/cmdb/resActionExt")
public class ResActionExtController extends BaseController {

	@Autowired
	IResActionService ResActionServiceImpl;

	@Autowired
	IResActionItemService ResActionItemServiceImpl;

	@Autowired
	ResExtService resExtService;

	@Autowired
	ISysUserInfoService SysUserInfoServiceImpl;

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insert.do")
	public R insert(ResAction entity, String items) {
		System.out.println(entity.toString());
		String uuid = resExtService.createUuid(entity.getType());
		entity.setUuid(uuid);
		entity.setSpstatus(ResActionService.ACT_STATUS_SFA);
		entity.setOperuserid(this.getUserId());
		entity.setOperusername(SysUserInfoServiceImpl.getById(this.getUserId()).getName());
		JSONArray items_arr = JSONArray.parseArray(items);
		List<ResActionItem> entityList = new ArrayList<ResActionItem>();
		for (int i = 0; i < items_arr.size(); i++) {
			ResActionItem e = new ResActionItem();
			e.setActuuid(uuid);
			e.setResid(items_arr.getJSONObject(i).getString("id"));
			e.setStatus("out");
			entityList.add(e);
		}
		entity.setTotal(ConvertUtil.toBigDecimal(entityList.size()));
		ResActionItemServiceImpl.saveBatch(entityList);
		ResActionServiceImpl.save(entity);
		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList(String type) {

		QueryWrapper<ResAction> ew = new QueryWrapper<ResAction>();
		if (ToolUtil.isNotEmpty(type)) {
			ew.and(i -> i.eq("type", type));
		}
		return R.SUCCESS_OPER(ResActionServiceImpl.list(ew));
	}

	// 不用许编辑资产，只能编辑备注，原因
	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/save.do")
	public R save(ResAction entity) {
		String status = entity.getSpstatus();
		if (status.equals(ResActionService.ACT_STATUS_SFA)) {
			ResActionServiceImpl.updateById(entity);
		} else {
			return R.FAILURE("当前状态不允许修改");
		}
		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/removeById.do")
	public R removeById(String id) {
		ResAction r = ResActionServiceImpl.getById(id);

		if (r.getSpstatus() == null) {
			ResActionServiceImpl.removeById(id);
		} else {
			if (ResActionService.ACT_STATUS_SFA.equals(r.getSpstatus())) {
				ResActionServiceImpl.removeById(id);
			} else {
				return R.FAILURE("当前状态不允许删除");
			}
		}

		return R.SUCCESS_OPER();
	}

}
