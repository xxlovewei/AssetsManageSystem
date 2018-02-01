package com.dt.module.base.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.sql.Delete;
import com.dt.dao.sql.Insert;
import com.dt.dao.sql.Update;
import com.dt.module.db.DB;
import com.dt.tool.lang.SpringContextUtil;
import com.dt.tool.util.DbUtil;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2017年8月7日 上午7:58:00
 * @Description:
 */
@Service
public class LoginService extends BaseService {

	public static LoginService me() {
		return SpringContextUtil.getBean(LoginService.class);
	}

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
	public static String LOGIN_TYPE_VALID_MESSAGE = "不支持的登录类型";

	public static String CLIENT_TYPE_WEB = "web";
	public static String CLIENT_TYPE_WEIXIN = "weixin";
	public static String CLIENT_TYPE_SMALLPROGRAM = "smallprogram";// 小程序
	public static String CLIENT_TYPE_APP = "app";
	public static String CLIENT_TYPE_VALID_MESSAGE = "不支持的客户端类型";

	public ResData validClientType(String value) {
		if (ToolUtil.isEmpty(value)) {
			return ResData.FAILURE(CLIENT_TYPE_VALID_MESSAGE);
		}
		if (value.equals(CLIENT_TYPE_WEB) || value.equals(CLIENT_TYPE_WEB) || value.equals(CLIENT_TYPE_SMALLPROGRAM)
				|| value.equals(CLIENT_TYPE_APP)) {
			return ResData.SUCCESS_OPER();
		} else {
			return ResData.FAILURE(CLIENT_TYPE_VALID_MESSAGE);
		}
	}

	public ResData querySupportClientType() {
		return ResData.SUCCESS_OPER();
	}

	public ResData querySupportLoginType() {
		return ResData.SUCCESS_OPER();
	}

	public void login() {
	}

	public JSONObject queryLoginUserInfo(String user_id) {
		return UserService.me().queryUserById(user_id);
	}

	/**
	 * @Description:判断登录方式是否有效,user_type中如果存在两条以上数据,则不允许登录,正确则返回唯一的user_id
	 */
	public ResData validLoginType(String value, String login_type) {

		// 匹配login_type字段
		if (ToolUtil.isEmpty(login_type)) {
			return ResData.FAILURE(LOGIN_TYPE_VALID_MESSAGE);
		}

		if (login_type.equals(LOGIN_TYPE_QQ) || login_type.equals(LOGIN_TYPE_EMPL) || login_type.equals(LOGIN_TYPE_USERNAME)
				|| login_type.equals(LOGIN_TYPE_MAIL) || login_type.equals(LOGIN_TYPE_MOBILE)
				|| login_type.equals(LOGIN_TYPE_MOBILE_CODE) || login_type.equals(LOGIN_TYPE_WEIXIN)
				|| login_type.equals(LOGIN_TYPE_ZFB)) {
		} else {
			return ResData.FAILURE(LOGIN_TYPE_VALID_MESSAGE);
		}

		// 校验login_type
		// 只校验了LOGIN_TYPE_EMPL,LOGIN_TYPE_USERNAME,LOGIN_TYPE_MOBILE,LOGIN_TYPE_MAIL
		ResData res = null;
		String user_id = "";
		if (ToolUtil.isOneEmpty(value, login_type)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}

		if (login_type.equals(LOGIN_TYPE_EMPL)) {
			// 系统本身唯一
			user_id = UserService.me().getUserIdFromEmpl(value);
			if (ToolUtil.isNotEmpty(user_id)) {
				res = ResData.SUCCESS_OPER(user_id);
			} else {
				res = ResData.FAILURE("用户不存在");
			}
		} else if (login_type.equals(LOGIN_TYPE_USERNAME)) {
			// 系统本身唯一
			user_id = UserService.me().getUserIdFromUserName(value);
			if (ToolUtil.isNotEmpty(user_id)) {
				res = ResData.SUCCESS_OPER(user_id);
			} else {
				res = ResData.FAILURE("用户不存在");
			}
		} else if (login_type.equals(LOGIN_TYPE_MOBILE)) {
			// 系统本身可能不唯一
			String[] userids = UserService.me().getUserIdFromMobile(value, login_type);
			if (ToolUtil.isNotEmpty(userids)) {
				if (userids.length == 1) {
					res = ResData.SUCCESS_OPER(userids[0]);
				} else if (userids.length == 0) {
					res = ResData.FAILURE("用户不存在");
				} else {
					res = ResData.FAILURE("存在两条记录,无法匹配记录不允许登录");
				}
			} else {
				res = ResData.FAILURE("用户不存在");
			}
		} else if (login_type.equals(LOGIN_TYPE_MAIL)) {
			String[] userids = UserService.me().getUserIdFromMail(value, login_type);
			if (ToolUtil.isNotEmpty(userids)) {
				if (userids.length == 1) {
					res = ResData.SUCCESS_OPER(userids[0]);
				} else if (userids.length == 0) {
					res = ResData.FAILURE("用户不存在");
				} else {
					res = ResData.FAILURE("存在两条记录,无法匹配记录不允许登录");
				}
			} else {
				res = ResData.FAILURE("用户不存在");
			}
		} else {
			res = ResData.FAILURE("未实现校验,暂不支持");
		}

		// 返回数据
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
	public ResData validLogin(String value, String login_type, String client) {
		if(ToolUtil.isOneEmpty(value,login_type,client)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		// 判断loginclient
		ResData validLoginClient = validClientType(client);
		if (validLoginClient.isFailed()) {
			return validLoginClient;
		}

		// 判断及验证logintype
		ResData validLoginType = validLoginType(value, login_type);
		if (validLoginType.isFailed()) {
			return validLoginType;
		}
		return validLoginType;
	}

	/**
	 * @Description: 退出登录
	 */
	public void logout(String cookie) {
		if (ToolUtil.isNotEmpty(cookie)) {
			Delete del = new Delete();
			del.from(" sys_session ");
			del.where().and("cookie=?", cookie);
			db.execute(del);
		}
	}

	public void recLogin(String user_id, String cookie, HttpServletRequest request) {
		Insert me = new Insert("sys_log_login");
		me.set("id", db.getUUID());
		me.setIf("ip", HttpKit.getIpAddr(request));
		me.setIf("user_id", user_id);
		me.setSE("rdate", DbUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		if (ToolUtil.isNotEmpty(cookie)) {
			String agent = request.getHeader("User-Agent");
			String client = (String) request.getParameter("client");
			Update ups = new Update("sys_session");
			ups.set("user_id", user_id);
			ups.setIf("ip", HttpKit.getIpAddr(request));
			ups.setIf("agent", agent);
			ups.setIf("client", client);
			ups.setSE("login_time", DbUtil.getDBDateString(DB.instance().getDBType()));
			ups.where().and("cookie=?", cookie);
			System.out.println(ups.getSQL());
			db.execute(ups);
		}
	}

	/**
	 * @Description: 检查登录状态
	 */
	public void loginCheck() {
	}

}
