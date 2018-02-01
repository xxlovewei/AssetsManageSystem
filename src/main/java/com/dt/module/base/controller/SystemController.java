package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.module.base.service.SystemService;

/**
 * @author: algernonking
 * @date: 2017年11月7日 下午8:19:58
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class SystemController {
	@Autowired
	SystemService systemService;

	@RequestMapping(value = "/system/getOnlineSession.do")
	@Res
	@Acl
	public ResData getOnlineSession() {
		return systemService.queryOnLineSession();
	}

}
