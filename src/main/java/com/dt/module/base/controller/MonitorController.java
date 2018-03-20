package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.MonitorService;

/**
 * @author: jinjie
 * @date: 2018年3月20日 下午2:09:43
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class MonitorController extends BaseController {
	@Autowired
	MonitorService monitorService;

	@ResponseBody
	@Acl(info = "检测是否存活", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/monitor/sayHello.do")
	public R sayHello(String module_id) {
		return monitorService.sayHello();
	}
}
