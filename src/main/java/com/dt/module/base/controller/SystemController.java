package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
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
	@ResponseBody
	@Acl(info = "查询在线session", value = Acl.ACL_DENY)
	public R getOnlineSession() {
		return systemService.queryOnLineSession();
	}

	@RequestMapping(value = "/system/queryMsg.do")
	@ResponseBody
	@Acl(info = "查询消息", value = Acl.ACL_ALLOW)
	public R queryMsg() {
		return systemService.queryMsg();
	}

}
