package com.dt.module.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
/*
	@Autowired
	public SCM scm = null;

	@RequestMapping("/demo/scmdb.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_API)
	@ResponseBody
	public R scm() {
		return R.SUCCESS_OPER(scm.query("select * from buh_puhckd").toJsonArrayWithJsonObject());

	}*/

	@RequestMapping("/demo/thy.html")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW)
	public String thy() {
		BaseCommon.print("thy.html");
		return "thy.html";

	}

	@RequestMapping("/demo/jsp.jsp")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW)
	public String jsp() {
		BaseCommon.print("jsp.jsp");
		return jsp("jsp");
	}

	@RequestMapping("/demo/api4.do")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW)
	@ResponseBody
	public R api4() {
		BaseCommon.print("api");
		return R.SUCCESS();

	}

	@RequestMapping("/demo/api.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_VIEW)
	@ResponseBody
	public R api() {
		BaseCommon.print("api");
		return R.SUCCESS();

	}

	@RequestMapping("/demo/api2.do")
	@Acl(value = Acl.ACL_ALLOW)
	@ResponseBody
	public R api2() {
		BaseCommon.print("api");
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/demo/api3.do")
	@Acl(value = Acl.ACL_ALLOW)
	@ResponseBody
	public String api23() {
		BaseCommon.print("api3");
		return R.SUCCESS_OPER().asJsonStr();
	}
}
