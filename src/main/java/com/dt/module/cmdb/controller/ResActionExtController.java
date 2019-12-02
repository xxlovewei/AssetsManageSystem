package com.dt.module.cmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.service.HistoryService;
import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskService;
import com.bstek.uflo.utils.EnvironmentUtils;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.cmdb.entity.ResAction;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResActionService;
import com.dt.module.cmdb.service.impl.ResActionService;
import com.dt.module.cmdb.service.impl.ResExtService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessClassItemService;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;

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

	@Autowired
	private ProcessService processService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	ISysProcessClassItemService SysProcessClassItemServiceImpl;

	@Autowired
	ISysProcessDataService SysProcessDataServiceImpl;

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insert.do")
	public R insert(ResAction entity, String items) {

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

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(String id) {

		ResAction r = ResActionServiceImpl.getById(id);
		String uuid = r.getUuid();
		JSONObject res = JSONObject.parseObject(JSON.toJSONString(r, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect));
		String sql = "select " + ResExtService.resSqlbody
				+ " t.*,a.backtime,a.status actitemstatus from res_action_item a,res t where a.resid=t.id and a.dr='0' and actuuid=?";
		RcdSet rs = db.query(sql, uuid);
		res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));
		return R.SUCCESS_OPER(res);
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
