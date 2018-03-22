package com.dt.module.wx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.wx.service.WxConfigService;

/**
 * @author: jinjie
 * @date: 2018年3月22日 上午8:42:21
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class WxConfigController extends BaseController {

	@Autowired
	private WxConfigService wxConfigService;

	@ResponseBody
	@Acl(info = "从本地配置中获取app配置信息", value = Acl.ACL_ALLOW)
	@RequestMapping("/wx/getAccessToken.do")
	public R getconfig() {
		return wxConfigService.queryAccessToken();
	}
	
	@ResponseBody
	@Acl(info = "从本地配置中获取app配置信息", value = Acl.ACL_ALLOW)
	@RequestMapping("/wx/getConfig.do")
	public R getConfig(String url) {
		return wxConfigService.queryWxConfig(url);
	}

	@ResponseBody
	@Acl(info = "从数据库获取app配置信息", value = Acl.ACL_ALLOW)
	@RequestMapping("/wx/getConfigByid.do")
	public R getConfigByid(String id, String url) {
		return wxConfigService.queryWxConfigByIdFromDb(id, url);

	}
}
