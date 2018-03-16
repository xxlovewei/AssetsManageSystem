package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.UserReceivingAddrService;

/**
 * @author: algernonking
 * @date: Oct 25, 2017 5:04:11 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class UserReceivingAddrController extends BaseController {
	@Autowired
	private UserReceivingAddrService userReceivingAddrService;

	@RequestMapping("/user/queryReceivingAddrById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "获取收货详情")
	public R queryReceivingAddrById() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		} else {
			return R.SUCCESS_OPER(userReceivingAddrService.queryReceivingAddrById(id));
		}
	}

	@RequestMapping("/user/queryReceivingAddr.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "获取所有收货地址")
	public R queryReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return R.FAILURE_USER_QUERY();
		} else {
			return R.SUCCESS_OPER(userReceivingAddrService.queryReceivingAddr(getUserId()));
		}
	}

	@RequestMapping("/user/delReceivingAddr.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "删除个人收货地址")
	public R delReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return R.FAILURE_USER_QUERY();
		}
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		} else {
			return userReceivingAddrService.delReceivingAddr(getUserId(), id, false);
		}
	}

	@RequestMapping("/user/saveReceivingAddr.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "修改收货地址")
	public R saveReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return R.FAILURE_USER_QUERY();
		}
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return userReceivingAddrService.addReceivingAddr(getUserId(), ps);
		} else {
			return userReceivingAddrService.updateReceivingAddr(ps);
		}
	}

	@RequestMapping("/user/setDefReceivingAddr.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "设置默认地址")
	public R setDefReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return R.FAILURE_USER_QUERY();
		}
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		} else {
			return userReceivingAddrService.updateDefReceivingAddr(getUserId(), id);
		}
	}

	@RequestMapping("/user/queryDefReceivingAddr.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "查询地址")
	public R queryDefReceivingAddr() {
		return userReceivingAddrService.queryDefReceivingAddr(getUserId());
	}

}
