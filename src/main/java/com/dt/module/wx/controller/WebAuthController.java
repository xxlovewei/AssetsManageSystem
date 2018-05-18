package com.dt.module.wx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.dao.Rcd;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.wx.pojo.WeixinOauth2Token;
import com.dt.module.wx.util.AdvancedUtil;

/**
 * @author: jinjie
 * @date: 2018年5月18日 上午11:54:42
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class WebAuthController extends BaseController {

	@Value("${wx.appId}")
	public String appIdconf;

	@Value("${wx.secret}")
	public String secretconf;

	private static Logger _log = LoggerFactory.getLogger(WebAuthController.class);

	public WeixinOauth2Token getOauth2AccessToken(String code) {
		if (ToolUtil.isOneEmpty(appIdconf, secretconf)) {
			return null;
		}
		return AdvancedUtil.getOauth2AccessToken(appIdconf, secretconf, code);

	}

	@Acl(info = "网页授权跳转", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/webOauth2.do")
	public void webOauth2(String code, String state, HttpServletResponse response) {
		_log.info("code:" + code + ",state:" + state);
		Rcd rs = db.uniqueRecord("select * from wx_web_auth where id=?", state);
		String url = "blank";
		if (ToolUtil.isNotEmpty(rs)) {
			url = rs.getString("value");
			if (ToolUtil.isEmpty(url)) {
				url = "blank";
			}
		}
		_log.info("url:" + url);
		String open_id = (String) HttpKit.getRequest().getAttribute("open_id");
		if (ToolUtil.isEmpty(open_id)) {
			WeixinOauth2Token r = getOauth2AccessToken(code);
			if (ToolUtil.isNotEmpty(r)) {
				open_id = r.getOpenId();
				HttpKit.getRequest().setAttribute("open_id", r);
			}
		}
		try {
			_log.info("open_id:" + open_id + ",sendRedirect:" + url);
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
