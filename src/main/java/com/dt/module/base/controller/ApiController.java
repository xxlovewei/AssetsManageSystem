package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
 
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.ApiService;
import com.dt.module.base.service.PositionService;
import com.dt.module.base.service.RoleService;
 

/**
 * @author: algernonking
 * @date: Nov 4, 2017 7:15:22 PM
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController extends BaseController {
	@Autowired
	ApiService apiService;
	@Autowired
	RoleService roleService;

	@Autowired
	PositionService positionService;

	@RequestMapping(value = "/position/queryAddress.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "通过经纬度查询行政地址")
	public R queryAddress(String lat, String lng) {
		return positionService.queryZone(lat, lng);
	}
	 
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
 

	@RequestMapping(value = "/api/queryApi.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "查询api")
	public R queryApi() {
		return apiService.queryApi();
	}

	@RequestMapping(value = "/api/updateApi.do", method = RequestMethod.POST)
	@ResponseBody
	public R updateApi() {
		return apiService.updateApi();
	}
}
