package com.dt.module.wx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.shiro.ShiroKit;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.wx.pojo.WeixinOauth2Token;
import com.dt.module.wx.service.WebOAuthService;
import com.dt.module.wx.service.WxService;
import com.dt.module.wx.util.AdvancedUtil;

/**
 * @author: jinjie
 * @date: 2018年5月18日 上午11:54:42
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class WebOAuthController extends BaseController {

	@Value("${wx.appId}")
	public String appIdconf;

	@Value("${wx.secret}")
	public String secretconf;

	@Autowired
	WxService wxService;

	@Autowired
	WebOAuthService webOAuthService;

	private static Logger _log = LoggerFactory.getLogger(WebOAuthController.class);

	public WeixinOauth2Token getOauth2AccessToken(String code) {
		if (ToolUtil.isOneEmpty(appIdconf, secretconf)) {
			return null;
		}
		return AdvancedUtil.getOauth2AccessToken(appIdconf, secretconf, code);

	}

	@ResponseBody
	@Acl(info = "网页授权跳转", value = Acl.ACL_USER)
	@RequestMapping(value = "/wx/test222.do")
	public String test(HttpServletResponse response) {
		return "ok";
	}

	@Acl(info = "网页授权跳转", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/webOauth2.do")
	public void webOauth2(String code, String state, HttpServletResponse response) {

		// 获取open_id
		String open_id = "";
		try {
			open_id = (String) HttpKit.getRequest().getSession().getAttribute("open_id");
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ToolUtil.isEmpty(open_id)) {
			WeixinOauth2Token r = getOauth2AccessToken(code);
			if (ToolUtil.isNotEmpty(r)) {
				open_id = r.getOpenId();
				HttpKit.getRequest().getSession().setAttribute("open_id", open_id);
			}
		}

		// 获取跳转等基本信息
		JSONObject br = wxService.baseWebOauth2Process(state, open_id);
		String url = br.getString("url");
		// 处理登录
		R r = wxService.baseToLogin(open_id, br.getString("login"));
		if (r.isFailed()) {
			_log.info("Login failed,msg:" + r.getMessage());
			url = "blank";
		}
		_log.info("put user_id into session,user_id:" + r.queryDataToJSONObject().getString("user_id"));
		super.getSession().setAttribute("user_id", r.queryDataToJSONObject().getString("user_id"));
		ShiroKit.getSession().setAttribute("sessionFlag", true);
		try {
			_log.info("open_id:" + open_id + ",sendRedirect:" + url);
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ResponseBody
	@Acl(info = "查询WebOAuth", value = Acl.ACL_DENY)
	@RequestMapping(value = "/wx/queryWebOAuth.do")
	public R queryWebOAuth() {
		return webOAuthService.queryWebOAuth();
	}

	@ResponseBody
	@Acl(info = "删除WebOAuth", value = Acl.ACL_DENY)
	@RequestMapping(value = "/wx/delWebOAuth.do")
	public R delWebOAuth(String id) {
		return webOAuthService.delWebOAuth(id);
	}

	@ResponseBody
	@Acl(info = "根据Id查询WebOAuth", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/queryWebOAuthById.do")
	public R queryWebOAuthById(String id) {
		return webOAuthService.queryWebOAuthById(id);
	}

	@ResponseBody
	@Acl(info = "保存WebOAuth", value = Acl.ACL_DENY)
	@RequestMapping(value = "/wx/saveWebOAuth.do")
	public R saveWebOAuth() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return webOAuthService.addWebOAuth(ps);
		} else {
			return webOAuthService.updateWebOAuth(ps);
		}
	}

}
