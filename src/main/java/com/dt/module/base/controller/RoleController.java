package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.RoleService;

@Controller
@RequestMapping(value = "/api")
public class RoleController extends BaseController {
	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/role/roleQuery.do")
	@Res
	@Acl
	public ResData roleQuery() {
		return roleService.queryRole();
	}
	@RequestMapping(value = "/role/roleQueryFormatKV.do")
	@Res
	@Acl
	public ResData roleQueryFormatKV() {
		ResData res = roleService.queryRole();
		if (res.isFailed()) {
			return res;
		}
		JSONArray kv = res.getDataToJSONArray();
		JSONObject obj = new JSONObject();
		for (int i = 0; i < kv.size(); i++) {
			obj.put(kv.getJSONObject(i).getString("role_id"), kv.getJSONObject(i).getString("role_name"));
		}
		return ResData.SUCCESS_OPER(obj);
	}
	@RequestMapping(value = "/role/roleQueryById.do")
	@Res
	@Acl
	public ResData roleQueryById(String role_id) {
		return roleService.queryRoleById(role_id);
	}
	@RequestMapping(value = "/role/roleSave.do")
	@Res
	@Acl
	public ResData roleSave() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("role_id");
		if (ToolUtil.isEmpty(id)) {
			return roleService.addRole(ps);
		} else {
			return roleService.updateRole(ps);
		}
	}
	@RequestMapping(value = "/role/roleDelete.do")
	@Res
	@Acl
	public ResData roleDelete(String role_id) {
		if (ToolUtil.isEmpty(role_id)) {
			return ResData.FAILURE_OPER();
		}
		return roleService.deleteRole(role_id, false);
	}
}
