package com.dt.module.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.content.service.CompanyService;
import com.dt.module.content.service.NewsService;

/** 
 * @author: algernonking
 * @date: 2017年8月12日 下午9:39:49 
 * @Description: TODO 
 */
@Controller
@RequestMapping(value = "/api")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	/**
	 * @Description: 查找公司内容
	 */
	@RequestMapping(value = "/company/queryCompany.do")
	@Res
	@Acl(value="allow")
	public ResData queryCompany() {
		return companyService.queryCompany();
	}
	/**
	 * @Description: 更新公司内容
	 */
	@RequestMapping(value = "/company/updateCompany.do")
	@Res
	@Acl(value="allow")
	public ResData updateCompany() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return companyService.updateCompany(ps);
		 
	}
	 
}

