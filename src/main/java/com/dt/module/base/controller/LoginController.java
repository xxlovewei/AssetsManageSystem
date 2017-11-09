package com.dt.module.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.authc.LockedAccountException;
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

	/**
	 * @Description: user,pwd,type,client必须部不为空
	 */
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping(value = "/user/login.do")
	@Res
	public ResData logindo(String user, String pwd, String type, String client, HttpServletRequest request) {

		// 判断client是否存在
		if (ToolUtil.isEmpty(client)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}

		// 验证账户是否有效
		ResData vlrs = loginService.validLogin(user, type, UserService.USER_TYPE_EMPL);
		if (vlrs.isFailed()) {
			return vlrs;
		}
		JSONObject userObj = vlrs.getDataToJSONObject();

		// 登录操作
		Subject currentUser = ShiroKit.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userObj.getString("USER_ID"),
				pwd == null ? null : pwd.toCharArray());
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
			// 其他错误，比如锁定，如果想单独处理请单独catch处理
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
		JSONObject u = userObj;
		// 覆盖重要信息
		u.put("PWD", "********");
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
			return ResData.SUCCESS("已登录");
		} else {
			return ResData.FAILURE("未登录");
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
