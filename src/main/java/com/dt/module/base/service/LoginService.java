package com.dt.module.base.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.db.DB;

/**
 * @author: algernonking
 * @date: 2017年8月7日 上午7:58:00
 * @Description:
 */
@Service
public class LoginService extends BaseService {
	@Autowired
	UserService userService;
	/**
	 * @Description: 所有都登录最终统一转成user_id去判断
	 */
	public static String LOGIN_TYPE_QQ = "qq";
	public static String LOGIN_TYPE_EMPL = "empl";
	public static String LOGIN_TYPE_USERNAME = "username";
	public static String LOGIN_TYPE_MAIL = "mail";
	public static String LOGIN_TYPE_MOBILE = "mobile";
	public static String LOGIN_TYPE_MOBILE_CODE = "mobile_code";// 手机验证码登录
	public static String LOGIN_TYPE_WEIXIN = "weixin";
	public static String LOGIN_TYPE_ZFB = "zfb";

	public JSONArray getLoginType() {
		JSONArray res = new JSONArray();
		return res;
	}

	public void login() {
	}

	/**
	 * @Description:判断登录方式是否有效,user_type中如果存在两条以上数据,则不允许登录
	 */
	public ResData validLoginType(String value, String login_type, String user_type) {
		ResData res = null;
		if (ToolUtil.isOneEmpty(value, login_type, user_type)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (login_type.equals(LOGIN_TYPE_EMPL) || login_type.equals(LOGIN_TYPE_USERNAME)
				|| login_type.equals(LOGIN_TYPE_MOBILE_CODE)) {
			res = ResData.SUCCESS_OPER();
		} else if (login_type.equals(LOGIN_TYPE_MAIL)) {
			if (db.uniqueRecord("select count(1) cnt from sys_user_info where user_type=? and mail=?", user_type, value)
					.getInteger("cnt") >= 2) {
				res = ResData.FAILURE("存在两条记录,无法匹配记录不允许登录");
			} else {
				res = ResData.SUCCESS_OPER();
			}
		} else if (login_type.equals(LOGIN_TYPE_MOBILE)) {
			if (db.uniqueRecord("select count(1) cnt from sys_user_info where user_type=? and tel=?", user_type, value)
					.getInteger("cnt") >= 2) {
				res = ResData.FAILURE("存在两条记录,无法匹配记录不允许登录");
			} else {
				res = ResData.SUCCESS_OPER();
			}
		} else {
			res = ResData.FAILURE("没有匹配登录方式");
		}
		if (ToolUtil.isEmpty(res)) {
			res = ResData.FAILURE_NODATA();
		}
		return res;
	}

	/**
	 * @Description:将所有登录方式转换成系统user_id的登录形式,如果可以登录,则返回一组用户数据 login_type
	 *                                                        如果是empl或username忽略user_type类型，
	 *                                                        因为empl和username全局唯一
	 */
	public ResData validLogin(String value, String login_type, String user_type) {
		ResData validLogin = validLoginType(value, login_type, user_type);
		if (validLogin.isFailed()) {
			return validLogin;
		}
		String user_id = "";
		if (login_type.equals(LOGIN_TYPE_EMPL)) {
			user_id = userService.getUserIdFromEmpl(value);
			if (ToolUtil.isEmpty(user_id)) {
				return ResData.FAILURE("用户名不存在");
			}
		} else if (login_type.equals(LOGIN_TYPE_USERNAME)) {
			user_id = userService.getUserIdFromUserName(value);
			if (ToolUtil.isEmpty(user_id)) {
				return ResData.FAILURE("用户名不存在");
			}
		} else if (login_type.equals(LOGIN_TYPE_MAIL)) {
			ArrayList<String> ulist = userService.getUserIdFromMail(value, user_type);
			if (ulist.size() >= 2) {
				return ResData.FAILURE("存在两条记录,无法匹配记录不允许登录");
			} else if (ulist.size() == 0) {
				return ResData.FAILURE("用户不存在");
			} else {
				user_id = ulist.get(0);
			}
		} else if (login_type.equals(LOGIN_TYPE_MOBILE)) {
			ArrayList<String> ulist = userService.getUserIdFromMobile(value, user_type);
			if (ulist.size() >= 2) {
				return ResData.FAILURE("存在两条记录,无法匹配记录不允许登录");
			} else if (ulist.size() == 0) {
				return ResData.FAILURE("用户不存在");
			} else {
				user_id = ulist.get(0);
			}
		} else {
			return ResData.FAILURE("没有匹配登录方式");
		}
		JSONObject resObj = userService.queryUserById(user_id);
		if (ToolUtil.isEmpty(resObj)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(resObj);
		}
	}

	/**
	 * @Description: 退出登录
	 */
	public void logout() {
	}

	public void recLogin(String user_id, String cookie, HttpServletRequest request) {
		Insert me = new Insert("sys_log_login");
		me.set("id", db.getUUID());
		me.setIf("ip", HttpKit.getIpAddr(request));
		me.setIf("user_id", user_id);
		me.setSE("rdate", DBUtil.getDBDateString(db.getDBType()));
		db.execute(me);

		if (ToolUtil.isNotEmpty(cookie)) {
			Update ups = new Update("sys_session");
			ups.set("user_id", user_id);
			ups.setIf("ip", HttpKit.getIpAddr(request));
			ups.setSE("login_time", DBUtil.getDBDateString(DB.instance().getDBType()));
			ups.where().and("cookie=?", cookie);
			db.execute(ups);
		}
	}

	/**
	 * @Description: 检查登录状态
	 */
	public void loginCheck() {
	}
	
	
	
	
}
