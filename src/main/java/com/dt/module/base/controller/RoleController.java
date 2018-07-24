package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.RoleService;

@Controller
@RequestMapping(value = "/api")
public class RoleController extends BaseController {
	@Autowired
	RoleService roleService;

	 
	@RequestMapping(value = "/role/roleQueryFormatKV.do")
	@ResponseBody
	@Acl(info = "查询权限")
	public R roleQueryFormatKV() {
		R res = roleService.queryRole();
		if (res.isFailed()) {
			return res;
		}
		JSONArray kv = res.queryDataToJSONArray();
		JSONObject obj = new JSONObject();
		for (int i = 0; i < kv.size(); i++) {
			obj.put(kv.getJSONObject(i).getString("role_id"), kv.getJSONObject(i).getString("role_name"));
		}
		return R.SUCCESS_OPER(obj);
	}
 
}
