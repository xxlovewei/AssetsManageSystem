package com.dt.module.base.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
import com.dt.module.base.service.SysUserService;
import com.dt.module.base.service.UserService;

@Controller
@RequestMapping("/api")
public class SysUserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/user/userSave.do")
	@Res
	@Acl
	@Transactional
	public ResData userSave() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String user_id = ps.getString("USER_ID");
		if (ToolUtil.isEmpty(user_id)) {
			return sysUserService.addSysUser(ps);
		} else {
			return sysUserService.updateSysUser(ps);
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
			if (!sysUserService.deleteSysUser(ids_arr.getString(i)).isSuccess()) {
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
		return sysUserService.queryUserById(user_id);
	}
	@RequestMapping("/user/queryRole.do")
	@Res
	@Acl
	public ResData queryRole(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray res = new JSONArray();
		HashMap<String, String> map = userService.queryUserRole(user_id);
		Iterator<Entry<String, String>> i = map.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) i.next();
			res.add(entry.getKey().toString());
		}
		return ResData.SUCCESS_OPER(res);
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
	public ResData userQueryByGroup(String group_id) {
		return userService.queryUserByGroup(group_id);
	}
	@RequestMapping("/user/getUserMenus.do")
	@Res
	@Acl
	public ResData getUserMenus(String menu_id) {
		if (ToolUtil.isEmpty(menu_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		return ResData.SUCCESS_OPER(userService.getMenuTree(getUserId(), menu_id));
	}
}
