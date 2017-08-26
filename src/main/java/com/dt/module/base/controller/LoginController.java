package com.dt.module.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.shiro.ShiroKit;
import com.dt.core.shiro.ShiroUser;
import com.dt.module.base.service.LoginService;
import com.dt.module.base.service.UserService;

@Controller
public class LoginController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	LoginService loginService = null;

	@Acl(value = "allow")
	@RequestMapping(value = "/user/login.do")
	@Res
	public ResData logindo(String user, String pwd, String type) {
		JSONObject r = new JSONObject();
		System.out.println("user:" + user + ",pwd:" + pwd + ",type:" + type);
		if (ToolUtil.isOneEmpty(user, pwd)) {
			return ResData.FAILURE("请输入账号或密码");
		}
		// 验证登录方式及用户类型
		ResData vlrs = loginService.validLogin(user, type, UserService.USER_TYPE_EMPL);
		if (vlrs.isFailed()) {
			return vlrs;
		}
		JSONObject userObj = vlrs.getDataToJSONObject();
		// 是否被锁
		if ("Y".equals(ToolUtil.parseYNValueDefY(userObj.getString("LOCKED")))) {
			return ResData.FAILURE("用户被锁定,请联系管理人员");
		}
		// 登录操作
		Subject currentUser = ShiroKit.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userObj.getString("USER_ID"), pwd.toCharArray());
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException ex) {
			return ResData.FAILURE("登录失败" + ex.getMessage());
		}
		ShiroUser shiroUser = ShiroKit.getUser();
		super.getSession().setAttribute("shiroUser", shiroUser);
		super.getSession().setAttribute("user_id", shiroUser.id);
		ShiroKit.getSession().setAttribute("sessionFlag", true);
		JSONObject u = userObj;
		// 覆盖重要信息
		u.put("PWD", "********");
		r.put("user_info", u);
		r.put("token", TokenUtil.generateValue());
		_log.info("login:" + r.toJSONString());
		return ResData.SUCCESS("登录成功", r);
	}
	@RequestMapping(value = "/user/checkLogin.do")
	@Res
	@Acl
	public ResData checkLogin(HttpServletRequest request, HttpServletResponse response, String user, String pwd)
			throws IOException {
		_log.info("Check Request,Login User_id:" + ShiroKit.getUser().id + "|"
				+ super.getSession().getAttribute("user_id"));
		if (ShiroKit.isAuthenticated()) {
			return ResData.SUCCESS("Ok");
		} else {
			return ResData.SUCCESS("Failed");
		}
	}
	@RequestMapping(value = "/user/logout.do")
	@Res
	@Acl
	public ResData loginout(HttpServletRequest request, HttpServletResponse response, String user, String pwd)
			throws IOException {
		ShiroKit.getSubject().logout();
		return ResData.SUCCESS("成功退出");
	}
}
