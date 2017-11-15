package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
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
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON,info="获取收货详情")
	public ResData queryReceivingAddrById() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		} else {
			return ResData.SUCCESS_OPER(userReceivingAddrService.queryReceivingAddrById(id));
		}
	}

	@RequestMapping("/user/queryReceivingAddr.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON,info="获取所有收货地址")
	public ResData queryReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return ResData.FAILURE_GETUSER();
		} else {
			return ResData.SUCCESS_OPER(userReceivingAddrService.queryReceivingAddr(getUserId()));
		}
	}

	@RequestMapping("/user/delReceivingAddr.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON,info="删除个人收货地址")
	public ResData delReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return ResData.FAILURE_GETUSER();
		}
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		} else {
			return userReceivingAddrService.delReceivingAddr(getUserId(), id, false);
		}
	}

	@RequestMapping("/user/saveReceivingAddr.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "修改收货地址")
	public ResData saveReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return ResData.FAILURE_GETUSER();
		}
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return userReceivingAddrService.addReceivingAddr(getUserId(), ps);
		} else {
			return userReceivingAddrService.updateReceivingAddr(ps);
		}
	}

	@RequestMapping("/user/setDefReceivingAddr.do")
	@Res
	@Acl
	public ResData setDefReceivingAddr() {
		if (ToolUtil.isEmpty(getUserId())) {
			return ResData.FAILURE_GETUSER();
		}
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		} else {
			return userReceivingAddrService.updateDefReceivingAddr(getUserId(), id);
		}
	}
}
