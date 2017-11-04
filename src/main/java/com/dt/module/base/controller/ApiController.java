package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
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
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON)
	public ResData queryApi() {
		return apiService.queryApi();
	}
	@RequestMapping(value = "/api/updateApi.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON)
	public ResData updateApi() {
		return apiService.updateApi();
	}
}
