package com.dt.module.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
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
import com.dt.core.common.base.BaseCodeMsgEnum;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.shiro.ShiroKit;
import com.dt.core.common.shiro.ShiroUser;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.module.base.service.LoginService;
import com.dt.module.base.service.MenuRootService;

@Controller
@RequestMapping("/api")
public class LoginController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	LoginService loginService = null;
	@Autowired
	MenuRootService menuRootService;

	/**
	 * @Description: user,pwd,type,client必须部不为空
	 */
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping(value = "/user/login.do")
	@Res
	public ResData logindo(String user, String pwd, String type, String client, HttpServletRequest request) {

		// 验证账户是否有效
		ResData vlrs = loginService.validLogin(user, type, client);
		if (vlrs.isFailed()) {
			return vlrs;
		}
		String user_id = vlrs.getData().toString();
		// 登录操作
		Subject currentUser = ShiroKit.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user_id, pwd == null ? null : pwd.toCharArray());
		token.setRememberMe(true);
		String error = "";
		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) {
			error = "账户不存在";
		} catch (IncorrectCredentialsException e) {
			error = "账号密码错误";
		} catch (ExcessiveAttemptsException e) {
			// TODO: handle exception
			error = "登录失败多次，账户锁定10分钟";
		} catch (LockedAccountException e) {
			error = "账户已被锁定";
		} catch (AuthenticationException e) {
			error = "其他错误：" + e.getMessage();
		}
		if (ToolUtil.isNotEmpty(error)) {
			return ResData.FAILURE(error);
		}

		ShiroUser shiroUser = ShiroKit.getUser();
		super.getSession().setAttribute("shiroUser", shiroUser);
		super.getSession().setAttribute("user_id", shiroUser.id);
		ShiroKit.getSession().setAttribute("sessionFlag", true);

		JSONObject r = new JSONObject();
		JSONObject u = loginService.queryLoginUserInfo(user_id);
		// 覆盖重要信息
		u.put("pwd", "********");
		r.put("user_info", u);
		// 系统信息
		r.put("systems", menuRootService.queryMenuRoot());
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
			return ResData.SUCCESS(BaseCodeMsgEnum.USER_ALREADY_LOGIN.getMessage());
		} else {
			return ResData.FAILURE_NOT_LOGIN();
		}
	}

	/**
	 * @Description: 实体已在shiro中实现
	 */
	@RequestMapping(value = "/user/logout.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData loginout() throws IOException {
		return ResData.SUCCESS("成功退出");
	}
}
