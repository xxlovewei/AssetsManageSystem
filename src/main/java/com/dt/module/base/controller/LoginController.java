package com.dt.module.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.db.DB;
import com.dt.core.shiro.ShiroKit;
import com.dt.core.shiro.ShiroUser;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private DB db = null;

	@Acl(value = "allow")
	@RequestMapping(value="/user/login.do")
	@Res
	public ResData logindo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		JSONObject r = new JSONObject();
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("type"); // username

		if (user == null || pwd == null) {
			return ResData.FAILURE("请输入账号或密码");
		}

		String sql = "select * from sys_user_info a where a.deleted='N' ";

		if (type == null || type.equals("username")) {
			sql = sql + " and user_name='" + user + "' ";
		} else {
			// 其他登录类型
		}

		// 判断是否存在
		Rcd userrs = db.uniqueRecord(sql);
		if (userrs == null) {

			return ResData.FAILURE("此账户不存在");
		}

		// 判断是否锁定
		String accountlk = userrs.getString("locked");
		if (accountlk == null || !accountlk.equals("N")) {
			return ResData.FAILURE("账户被锁定,请联系管理人员");
		}

		// 登录操作
		Subject currentUser = ShiroKit.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userrs.getString("user_id"), pwd.toCharArray());
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
		
		JSONObject u = ConvertUtil.OtherJSONObjectToFastJSONObject(userrs.toJsonObject());
		// 覆盖重要信息
		u.put("PWD", "********");
		r.put("user_info", u);
		r.put("token", TokenUtil.generateValue());
		System.out.println(r.toJSONString());
		return ResData.SUCCESS("登录.", r);

	}

	@RequestMapping(value="/user/checkLogin.do")
	@Res
	@Acl
	public ResData checkLogin(HttpServletRequest request, HttpServletResponse response, String user, String pwd)
			throws IOException {

		System.out.println("Check Request,Login User_id:" + ShiroKit.getUser().id + "|" + super.getSession().getAttribute("user_id"));
		if (ShiroKit.isAuthenticated()) {
			return ResData.SUCCESS("Ok");
		} else {
			return ResData.SUCCESS("Failed");
		}
	 
	}

	@RequestMapping(value="/user/logout.do")
	@Res
	@Acl
	public ResData loginout(HttpServletRequest request, HttpServletResponse response, String user, String pwd)
			throws IOException {

		ShiroKit.getSubject().logout();
		return ResData.SUCCESS("成功退出");

	}

}
