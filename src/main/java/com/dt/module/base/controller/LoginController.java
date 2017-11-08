package com.dt.module.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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
import com.dt.core.common.shiro.ShiroKit;
import com.dt.core.common.shiro.ShiroUser;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.module.base.service.LoginService;
import com.dt.module.base.service.MenuRootService;
import com.dt.module.base.service.UserService;

@Controller
@RequestMapping("/api")
public class LoginController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	LoginService loginService = null;
	@Autowired
	MenuRootService menuRootService;

	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping(value = "/user/login.do")
	@Res
	public ResData logindo(String user, String pwd, String type, String client, HttpServletRequest request) {

		// 判断client是否存在
		if (ToolUtil.isEmpty(client)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}

		JSONObject r = new JSONObject();
		_log.info("user:" + user + ",pwd:" + pwd + ",type:" + type);
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
		_log.info("session timeout:" + ShiroKit.getSession().getTimeout());
		JSONObject u = userObj;
		// 覆盖重要信息
		u.put("PWD", "********");
		r.put("user_info", u);
		// 系统信息
		r.put("systems", menuRootService.queryMenuRoot());
		// r.put

		r.put("token", TokenUtil.generateValue());
		_log.info("login:" + r.toJSONString());
		loginService.recLogin(shiroUser.id, super.getSession().getId(), request);
		return ResData.SUCCESS("登录成功", r);
	}

	@RequestMapping(value = "/user/checkLogin.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData checkLogin() throws IOException {
		if (ShiroKit.isAuthenticated()) {
			return ResData.SUCCESS("已登录");
		} else {
			return ResData.FAILURE("未登录");
		}
	}

	@RequestMapping(value = "/user/logout.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData loginout() throws IOException {
		return ResData.SUCCESS("成功退出");
	}
}
