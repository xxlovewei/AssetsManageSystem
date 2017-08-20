package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
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
			obj.put(kv.getJSONObject(i).getString("ROLE_ID"), kv.getJSONObject(i).getString("ROLE_NAME"));
		}
		return ResData.SUCCESS_OPER(obj);
	}
	@RequestMapping(value = "/role/roleQueryById.do")
	@Res
	@Acl
	public ResData roleQueryById(String ROLE_ID) {
		return roleService.queryRoleById(ROLE_ID);
	}
	@RequestMapping(value = "/role/roleSave.do")
	@Res
	@Acl
	public ResData roleSave() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("ROLE_ID");
		if (ToolUtil.isEmpty(id)) {
			return roleService.addRole(ps);
		} else {
			return roleService.updateRole(ps);
		}
	}
	@RequestMapping(value = "/role/roleDelete.do")
	@Res
	@Acl
	public ResData roleDelete(String ID) {
		if (ToolUtil.isEmpty(ID)) {
			return ResData.FAILURE_OPER();
		}
		return roleService.deleteRole(ID);
	}
}
