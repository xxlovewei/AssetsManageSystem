package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.entity.SysDict;
import com.dt.module.base.service.ISysDictService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/api/sysDict")
public class SysDictController extends BaseController {

	@Autowired
	ISysDictService SysDictServiceImpl;

	@ResponseBody
	@Acl(info = "根据Id删除", value = Acl.ACL_DENY)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		return R.SUCCESS_OPER(SysDictServiceImpl.removeById(id));
	}

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectById.do")
	public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		return R.SUCCESS_OPER(SysDictServiceImpl.getById(id));
	}

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insert.do")
	public R insert(SysDict entity) {
		return R.SUCCESS_OPER(SysDictServiceImpl.save(entity));
	}

	@ResponseBody
	@Acl(info = "根据Id更新", value = Acl.ACL_DENY)
	@RequestMapping(value = "/updateById.do")
	public R updateById(SysDict entity) {
		return R.SUCCESS_OPER(SysDictServiceImpl.updateById(entity));
	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(SysDict entity) {
		return R.SUCCESS_OPER(SysDictServiceImpl.saveOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		return R.SUCCESS_OPER(SysDictServiceImpl.list(null));
	}

	@ResponseBody
	@Acl(info = "查询所有,有分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectPage.do")
	public R selectPage(String start, String length,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String pageSize,
			@RequestParam(value = "pageIndex", required = true, defaultValue = "1") String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		QueryWrapper<SysDict> ew = new QueryWrapper<SysDict>();
		// ew.and(i -> i.eq("user_id", getUserId()).apply(pagesize>10,
		// "rtime>sysdate-1","23"));
		IPage<SysDict> pdata = SysDictServiceImpl.page(new Page<SysDict>(pageindex, pagesize), ew);
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", pdata.getTotal());
		retrunObject.put("iTotalDisplayRecords", pdata.getTotal());
		retrunObject.put("data", JSONArray.parseArray(JSON.toJSONString(pdata.getRecords(),
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect)));
		return R.clearAttachDirect(retrunObject);
	}

}
