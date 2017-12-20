package com.dt.module.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
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
import com.dt.core.common.shiro.ShiroKit;
import com.dt.core.common.shiro.ShiroUser;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.LoginService;
import com.dt.module.base.service.UserService;
import com.dt.module.base.service.WxUserService;

/**
 * @author: algernonking
 * @date: 2017年11月23日 下午9:45:39
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class LoginSmallProgramController extends BaseController {
	@Autowired
	WxUserService wxUserService;
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginService;

	private ResData getOpenIdStr(String code) {
		ResData res = new ResData();
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", "wx5a945d59434c7f0d");
		map.put("js_code", code);
		map.put("grant_type", "authorization_code");
		map.put("secret", "3f7660b289e8aa7ca1dce78cc19cc288");
		String str = HttpKit.sendGet(url, map);
		JSONObject strobj = JSONObject.parseObject(str);
		System.out.println("strobj" + strobj.toJSONString());
		// 判断是否获取open_id
		String openId = strobj.getString("openid");
		if (ToolUtil.isEmpty(openId)) {
			res.setCode(10000);
			res.setSuccess(false);
			res.setMessage("未获取Openid");
			return res;
		}
		res.setData(strobj);
		res.setSuccess(true);
		return res;
	}

	@RequestMapping(value = "/smallprogram/login.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "小程序用户登录")
	public ResData login(String code, HttpServletRequest request) {
		ResData res = new ResData();
		if (ToolUtil.isEmpty(code)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		ResData strres = getOpenIdStr(code);
		if (strres.isFailed()) {
			return strres;
		}
		String openId = strres.getDataToJSONObject().getString("openid");
		// 判断用户是否存在
		ResData userrs = wxUserService.existUserByOpenId(openId);
		if (userrs.isFailed()) {
			res.setCode(10001);
			res.setSuccess(false);
			res.setMessage("用户不存在");
			return res;
		}
		Subject currentUser = ShiroKit.getSubject();
		String user_id = userrs.getDataToJSONObject().getString("user_id");
		String pwd = userrs.getDataToJSONObject().getString("pwd");
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
			res.setCode(10002);
			res.setSuccess(false);
			res.setMessage("登录失败");
			return res;
		}

		// 添加更新sys_session表
		String tid = super.getSession().getId();
		ShiroUser shiroUser = ShiroKit.getUser();
		JSONObject ret = new JSONObject();
		ret.put("cookie", tid);
		ret.put("token", tid);

		super.getSession().setAttribute("shiroUser", shiroUser);
		super.getSession().setAttribute("user_id", shiroUser.id);
		loginService.recLogin(shiroUser.id, tid, request);
		res.setCode(10003);
		res.setSuccess(true);
		res.setData(ret);
		return res;

	}

	@RequestMapping(value = "/smallprogram/register.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "小程序用户注册")
	public ResData register(String code, String avatarUrl, String city, String country, String nickName,
			String province) {
		ResData strres = getOpenIdStr(code);
		if (strres.isFailed()) {
			return strres;
		}
		String openId = strres.getDataToJSONObject().getString("openid");
		if (ToolUtil.isOneEmpty(avatarUrl, nickName, openId)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		TypedHashMap<String, Object> ps = new TypedHashMap<String, Object>();
		ps.put("open_id", openId);
		ps.put("locked", "N");
		ps.put("nickname", nickName);
		ps.put("avatarurl", avatarUrl);
		ps.put("score", "0");
		ResData rs = userService.addUser(ps, UserService.USER_TYPE_CRM);
		return rs;
	}

	@RequestMapping("/smallprogram/userQueryById.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "小程序用户信息")
	public ResData userQueryById() {
		return ResData.SUCCESS_OPER(userService.queryUserById(getUserId()));
	}

	@RequestMapping("/smallprogram/checkLogin.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "检测登录")
	public ResData checkLogin() {
		Subject currentUser = ShiroKit.getSubject();
		String userId = this.getUserId();
		System.out.println(userId + "," + currentUser.isAuthenticated());

		if (ToolUtil.isNotEmpty(userId) && userId.length() > 2) {
			return ResData.SUCCESS_OPER();
		} else {
			return ResData.FAILURE();
		}
	}

}