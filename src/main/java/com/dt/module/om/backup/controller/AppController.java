package com.dt.module.om.backup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;

/**
 * @author: algernonking
 * @date: 2018年1月24日 下午5:25:46
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class AppController extends BaseController {

	@RequestMapping("/test/test.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "启动应用节点")
	public String startNodeApp(Model model, String id) {

		System.out.println("id:" + id.length());
		model.addAttribute("name", "Dear");

		return "zoolist";
	}

}
