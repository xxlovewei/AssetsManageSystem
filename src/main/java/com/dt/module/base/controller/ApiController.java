package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.ApiService;

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

	@RequestMapping(value = "/api/queryApi.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R queryApi() {
		return apiService.queryApi();
	}
	@RequestMapping(value = "/api/updateApi.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R updateApi() {
		return apiService.updateApi();
	}
}
