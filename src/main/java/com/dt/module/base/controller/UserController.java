package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.UserService;

@Controller
public class UserController extends BaseController {
 
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/userSave.do")
	@Res
	@Acl
	@Transactional
	public ResData userSave() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String user_id = ps.getString("USER_ID");
		if (ToolUtil.isEmpty(user_id)) {
			return userService.addUser(ps, UserService.USER_TYPE_SYS);
		} else {
			return userService.updateUser(ps, UserService.USER_TYPE_SYS);
		}
	}
	@RequestMapping(value = "/user/userDelete.do")
	@Res
	@Acl
	@Transactional
	public ResData userDelete(String user_ids) {
		if (ToolUtil.isEmpty(user_ids)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray ids_arr = JSONArray.parseArray(user_ids);
		if (ids_arr == null || ids_arr.size() == 0) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		boolean flag = true;
		for (int i = 0; i < ids_arr.size(); i++) {
			if (!userService.deleteUser(ids_arr.getString(i)).isSuccess()) {
				flag = false;
			}
		}
		if (flag) {
			return ResData.SUCCESS_OPER();
		} else {
			return ResData.FAILURE("部分用户删除失败,请留意");
		}
	}
	@RequestMapping("/user/userQueryById.do")
	@Res
	@Acl
	public ResData userQueryById(String user_id) {
		return userService.queryUserById(user_id);
	}
	@RequestMapping("/user/userRoleChange.do")
	@Res
	@Acl
	@Transactional
	public ResData userRoleChange() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return userService.changeUserRole(ps);
	}
 
	@RequestMapping("/user/userQueryByGroup.do")
	@Res
	@Acl
	public ResData userQueryByGroup(String group_id)  {
		return userService.queryUserByGroup(group_id);
	}
	@RequestMapping("/user/getUserMenus.do")
	@Res
	@Acl
	public ResData treeDataDirect(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE("获取用户ID失败");
		}
		return ResData.SUCCESS(userService.getMenuTree(user_id, "1"));
	}
}
