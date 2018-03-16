package com.dt.module.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.module.db.SCM;
import com.dt.module.demo.service.AService;

/**
 * @author: jinjie
 * @date: 2018年3月9日 上午8:24:34
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class Acontroller extends BaseController {

	@Autowired
	private AService aService;
	
	@Autowired
	public SCM scm = null;

	@RequestMapping("/demo/db.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_API,info="demo")
	@ResponseBody
	@Transactional
	public R scm() {
		return aService.test();

	}

	@RequestMapping("/demo/scmdb2.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_API,info="demo")
	@ResponseBody
	@Transactional("transactionManagerScm")
	public R scm2() {
		Insert ins1=new Insert("dt_a");
		ins1.set("id", "a");
		scm.execute(ins1);
		
		Insert ins2=new Insert("dt_a");
		ins2.set("id2", "a");
		scm.execute(ins2);
		
		
		return R.SUCCESS_OPER(scm.query("select * from T where rownum<10").toJsonArrayWithJsonObject());
	}

	@RequestMapping("/demo/thy.html")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW,info="demo")
	public String thy() {
		BaseCommon.print("thy.html");
		return "thy.html";

	}

	@RequestMapping("/demo/jsp.jsp")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW,info="demo")
	public String jsp() {
		BaseCommon.print("jsp.jsp");
		return jsp("jsp");
	}

	@RequestMapping("/demo/api4.do")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW,info="demo")
	@ResponseBody
	public R api4() {
		BaseCommon.print("api");
		return R.SUCCESS();

	}

	@RequestMapping("/demo/api.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_VIEW,info="demo")
	@ResponseBody
	public R api() {
		BaseCommon.print("api");
		return R.SUCCESS();

	}

	@RequestMapping("/demo/api2.do")
	@Acl(value = Acl.ACL_ALLOW,info="demo")
	@ResponseBody
	public R api2() {
		BaseCommon.print("api");
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/demo/api3.do")
	@Acl(value = Acl.ACL_ALLOW,info="demo")
	@ResponseBody
	public String api23() {
		BaseCommon.print("api3");
		return R.SUCCESS_OPER().asJsonStr();
	}
}
