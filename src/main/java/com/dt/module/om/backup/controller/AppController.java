package com.dt.module.om.backup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dt.core.annotion.Acl;
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
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "启动应用节点")
	public ModelAndView startNodeApp(Model model, String id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("users", "test");
		return mv;

	}

}
