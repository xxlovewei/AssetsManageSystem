package com.dt.module.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;

/**
 * @author: jinjie
 * @date: 2018年3月9日 上午8:24:34
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class Acontroller extends BaseController {

	@RequestMapping("/demo/thy.html")
	@Acl(value = Acl.TYPE_ALLOW)
	public String thy() {
		BaseCommon.print("thy.html");
		return "thy.html";

	}

	@RequestMapping("/demo/jsp.jsp")
	@Acl(value = Acl.TYPE_ALLOW)
	public String jsp() {
		BaseCommon.print("jsp.jsp");
		return jsp("jsp");
	}

	@RequestMapping("/demo/api.do")
	@Acl(value=Acl.TYPE_ALLOW)
	@ResponseBody
	public R api() {
		BaseCommon.print("api");
		return R.SUCCESS();
	 
	}
	
	@RequestMapping("/demo/api2.do")
	@Acl(value=Acl.TYPE_ALLOW)
	@ResponseBody
	public R api2() {
		BaseCommon.print("api");
		return R.SUCCESS_OPER();
	}
	
	@RequestMapping("/demo/api3.do")
	@Acl(value=Acl.TYPE_ALLOW)
	@ResponseBody
	public String api23() {
		BaseCommon.print("api3");
		return R.SUCCESS_OPER().asJsonStr();
	}
}
