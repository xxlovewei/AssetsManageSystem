package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
 
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.ApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author: algernonking
 * @date: Nov 4, 2017 7:15:22 PM
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
@Api(value = "ApiController|Api相关的Controller")
public class ApiController extends BaseController {
	@Autowired
	ApiService apiService;

	@RequestMapping(value = "/api/queryApi.do")
	@ResponseBody
	@ApiOperation(value = "查询当前所有Apis", notes = "返回R")
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
