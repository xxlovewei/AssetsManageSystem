package com.dt.module.base.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.content.service.CompanyService;

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
	@ResponseBody
	@Acl(value = Acl.TYPE_ALLOW)
	public R queryCompany() {
		return companyService.queryCompany();
	}
	/**
	 * @Description: 更新公司内容
	 */
	@RequestMapping(value = "/company/updateCompany.do")
	@ResponseBody
	@Acl
	public R updateCompany() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return companyService.updateCompany(ps);
	}
}
