package com.dt.module.base.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.entity.User;

/**
 * @author: algernonking
 * @date: 2017年8月6日 上午7:31:25
 * @Description: TODO 1>所有的正常用户必须要在sys_user_info登记
 */
@Service
public class UserService extends BaseService {
	// 系统人员
	public static String USER_TYPE_SYS = "sys";
	// 组织内人员
	public static String USER_TYPE_EMPL = "empl";

	// 会员粉丝人员
	public static String USER_TYPE_CRM = "crm";

	public static String USER_TYPE_XCX = "xcx";

	// APP
	public static String USER_TYPE_APP = "app";

	public static String USER_TYPE_WX = "wx";

	 
	private static Logger _log = LoggerFactory.getLogger(UserService.class);

	public static UserService me() {
		return SpringContextUtil.getBean(UserService.class);
	}
//
//	public static Boolean clearUserMenus() {
//		userMenus.clear();
//		return true;
//	}
//
//	public static JSONArray getUserMenu(String key) {
//		if (userMenus.containsKey(key)) {
//			return userMenus.get(key);
//		} else {
//			return null;
//		}
//	}
//
//	public static void addUserMenu(String key, JSONArray value) {
//		userMenus.put(key, value);
//	}

//	/**
//	 * @Description: 获得用户信息
//	 */
//	public User getUser(String id) {
//		User user = new User();
//		String sql = "select * from sys_user_info a where a.user_id=?";
//		// 账号状态信息
//		Rcd u_rs = db.uniqueRecord(sql, id);
//		user.setUserId(u_rs.getString("user_id"));
//		user.setPassword(u_rs.getString("pwd"));
//		user.setAccount(u_rs.getString("user_name"));
//		user.setName(u_rs.getString("user_name"));
//		user.setSalt(MD5Util.encrypt(u_rs.getString("user_id")));
//		if (ToolUtil.isNotEmpty(u_rs.getString("locked")) && u_rs.getString("locked").equals("N")) {
//			user.setIsLocked(false);
//		}
//
//		// 获取角色信息
//		String sql2 = "select a.role_id,b.role_name from sys_user_role a,sys_role_info b where a.role_id=b.role_id and user_id=?";
//		RcdSet r_rs = db.query(sql2, id);
//		HashMap<String, String> rmap = new HashMap<String, String>();
//		for (int i = 0; i < r_rs.size(); i++) {
//			rmap.put(r_rs.getRcd(i).getString("role_id"), r_rs.getRcd(i).getString("role_name"));
//		}
//		user.setRolsSet(rmap);
//		return user;
//	}

	/**
	 * @Description: 获得用户菜单,限制3层
	 */
	
	/**
	 * @Description: 根据角色查处用户的权限
	 */
//	@SuppressWarnings("unchecked")
//	public List<String> findPermissionsByRoleId(String roleId) {
//		_log.info("获取角色权限:" + roleId);
//		return db.query(
//				"select ct from sys_role_module a,sys_modules_item b where a.module_id=b.module_id and role_id=?",
//				roleId).toList("ct");
//	}

//	/**
//	 * @Description: 根据角色id查找角色名称
//	 */
//	public String findRoleNameByRoleId(String roleId) {
//		return db.uniqueRecord("select role_name from sys_role_info where role_id=?", roleId).getString("role_name");
//	}

//	/**
//	 * @Description: 判断用户是否存在
//	 */
//	public Boolean isExistUserId(String user_id) {
//		if (ToolUtil.isEmpty(user_id)
//				|| db.uniqueRecord("select * from sys_user_info where dr='0' and user_id=?", user_id) == null) {
//			return false;
//		}
//		return true;
//	}

//	/**
//	 * @Description: 判断组织内用户是否存在
//	 */
//	public Boolean isExistEmpl(String empl_id) {
//		String user_id = getUserIdFromEmpl(empl_id);
//		return isExistUserId(user_id);
//	}

	 

//	/**
//	 * @Description: 根据user_id获取empl_id
//	 */
//	public String getEmplIdFromUserId(String user_id) {
//		if (ToolUtil.isEmpty(user_id)) {
//			return null;
//		}
//		Rcd rs = db.uniqueRecord("select empl_id from sys_user_info where dr='0' and user_id=?", user_id);
//		if (rs == null) {
//			return null;
//		}
//		return rs.getString("empl_id");
//	}
//
//	/**
//	 * @Description: 根据empl_id获取user_id
//	 */
//	public String getUserIdFromEmpl(String empl_id) {
//		if (ToolUtil.isEmpty(empl_id)) {
//			return null;
//		}
//		Rcd rs = db.uniqueRecord("select user_id from sys_user_info where dr='0' and empl_id=?", empl_id);
//		if (rs == null) {
//			return null;
//		}
//		return rs.getString("user_id");
//	}

	/**
	 * @Description: 根据mobile_id获取user_id
	 */
//	public String[] getUserIdFromMobile(String mobile, String user_type) {
//		if (ToolUtil.isOneEmpty(mobile, user_type)) {
//			return null;
//		}
//		RcdSet rs = db.query("select user_id from sys_user_info where dr='0' and tel=? and user_type=?", mobile,
//				user_type);
//		if (rs.size() > 0) {
//			return rs.toStringArray("user_id");
//		}
//		return null;
//	}

	/**
	 * @Description: 根据用户名获取用户ID
	 */
//	public String getUserIdFromUserName(String username) {
//		if (ToolUtil.isEmpty(username)) {
//			return null;
//		}
//		Rcd rs = db.uniqueRecord("select user_id from sys_user_info where dr='0' and user_name=?", username);
//		if (rs == null) {
//			return null;
//		}
//		return rs.getString("user_id");
//	}
 
	/**
	 * @Description: 判断插入用户的类型,默认返回系统用户类型
//	 */
//	private String validUserType(String type, String def) {
//		if (ToolUtil.isEmpty(type)) {
//			return def;
//		}
//		if (type.equals(UserService.USER_TYPE_SYS) || type.equals(UserService.USER_TYPE_EMPL)
//				|| type.equals(UserService.USER_TYPE_CRM) || type.equals(UserService.USER_TYPE_WX)) {
//			return type;
//		}
//		return def;
//	}

	/**
	 * @Description: 增加用户
	 */
	@Transactional
	public R addUser(TypedHashMap<String, Object> ps, String type) {

		String user_id = ToolUtil.getUUID();
		R emplRes = getEmplNextId();
		if (emplRes.isFailed()) {
			return R.FAILURE("生成序列号失败");
		}
		// 校验用户类型
		//type = validUserType(type, USER_TYPE_SYS);
		String username = "";
		String empl_id = (String) emplRes.getData();
		// 处理唯一登录名
		if (type.equals(UserService.USER_TYPE_EMPL)) {
			username = "empl" + empl_id;
		} else if (type.equals(UserService.USER_TYPE_SYS)) {
			username = ps.getString("user_name", MD5Util.encrypt(user_id));
		} else if (type.equals(UserService.USER_TYPE_CRM)) {
			// 粉丝，微信注册
			username = MD5Util.encrypt(db.getUUID());
			empl_id = username;
		} else if (type.equals(UserService.USER_TYPE_WX)) {
			// 粉丝，微信注册
			username = MD5Util.encrypt(db.getUUID());
			empl_id = username;
		}
//		if (!ifUserNameValid(username)) {
//			return R.FAILURE("登录名不可用");
//		}

		Insert ins = new Insert("sys_user_info");
		ins.setSE("create_time", DbUtil.getDbDateString(db.getDBType()));
		ins.setSE("update_time", DbUtil.getDbDateString(db.getDBType()));
		ins.set("user_id", user_id); // 账户系统唯一ID(唯一)
		ins.set("empl_id", empl_id); // empl_id
		ins.set("user_name", username); // 账户名称(唯一)
		ins.set("user_type", type); // 账户类型
		ins.setIf("nickname", ps.getString("nickname", "toy")); // 昵称
		ins.setIf("name", ps.getString("name", "toy")); // 姓名
		ins.setIf("pwd", ps.getString("pwd", "0")); // 密码
		ins.setIf("status", ps.getString("status")); // 状态
		ins.setIf("org_id", ps.getString("org_id")); // 组织Id
		ins.setIf("locked", ps.getString("locked", "Y")); // 是否锁定
		ins.setIf("tel", ps.getString("tel")); // 手机号
		ins.setIf("qq", ps.getString("qq")); // qq
		ins.setIf("mail", ps.getString("mail")); // 邮箱
		ins.setIf("profile", ps.getString("profile")); // 介绍
		ins.setIf("mark", ps.getString("mark")); // 注释
		ins.setIf("photo", ps.getString("photo")); // 头像
		ins.setIf("homeaddr_def", ps.getString("homeaddr_def")); // 家庭地址
		ins.setIf("receaddr_def", ps.getString("receaddr_def")); // 默认收货地址
		ins.setIf("weixin", ps.getString("weixin")); // 微信号
		ins.setIf("sex", ps.getString("sex", "1")); // 性别 1男，2女
		ins.setIf("system", ps.getString("system", "1")); // 系统
		ins.setIf("shop_id", ps.getString("shop_id")); // 默认所属店铺
		ins.setIf("score", ps.getString("score", "0")); // 积分
		ins.setIf("open_id", ps.getString("open_id")); // 微信open_id
		ins.setIf("balance", ps.getString("balance", "0"));// 余额
		ins.setIf("avatarurl", ps.getString("avatarurl"));// 微信logo
		ins.setIf("card", ps.getString("card"));// 银行卡
		ins.setIf("amount", ToolUtil.toInt(ps.getString("amount"), 0));// 金额
		ins.setIf("famount", ToolUtil.toInt(ps.getString("famount"), 0));// 冻结金额
		ins.setIf("tixamount", ToolUtil.toInt(ps.getString("tixamount"), 0));// 提现中金额
		ins.setIf("credit_score", ToolUtil.toInt(ps.getString("credit_score"), 0));// 信用分
		ins.setIf("identity_card", ps.getString("identity_card"));// 身份证
		ins.setIf("driver_card", ps.getString("driver_card"));// 驾照
		ins.setIf("nation", ps.getString("nation"));// 民族
		ins.setIf("native_place", ps.getString("native_place"));// 籍贯
		ins.setIf("self_evaluate", ps.getString("self_evaluate"));//自我评价
		ins.setIf("short_mobile", ps.getString("short_mobile"));//手机短号
 
		ins.set("dr", "0");
		db.execute(ins);
		return R.SUCCESS_OPER(user_id);
	}

	/**
	 * @Description: 获取Empl的下一个ID
	 */
	public R getEmplNextId() {
		Rcd seqrs = db.uniqueRecord(
				"select case when value is null then '50' else value end seq from sys_params where id='sys_empl_no'");
		if (ToolUtil.isEmpty(seqrs)) {
			return R.FAILURE("获取员工编号错误,无法生成员工.");
		}
		String empl_id = (ConvertUtil.toInt(seqrs.getString("seq")) + 1) + "";
		Update me = new Update("sys_params");
		me.set("value", empl_id);
		me.where().and("id=?", "sys_empl_no");
		db.execute(me);
		return R.SUCCESS_OPER(ConvertUtil.formatIntToString(empl_id, 6, 100));
	}

	/**
	 * @Description: 根据user_id修改人员表
	 */
	@Transactional
	public R updateUser(TypedHashMap<String, Object> ps, String type) {
		// 最终根据user_id去更新用户数据
		// 获取用户的user_id,empl_id
		//type = validUserType(type, USER_TYPE_SYS);
		String user_id = ps.getString("user_id");
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Update ups = new Update("sys_user_info");
		ups.setSE("update_time", DbUtil.getDbDateString(db.getDBType()));
		ups.setIf("nickname", ps.getString("nickname", "toy"));
		ups.setIf("name", ps.getString("name", "toy"));
		ups.setIf("pwd", ps.getString("pwd", "0"));
		ups.setIf("status", ps.getString("status"));
		ups.setIf("org_id", ps.getString("org_id"));
		ups.setIf("locked", ps.getString("locked", "Y"));
		ups.setIf("tel", ps.getString("tel"));
		ups.setIf("qq", ps.getString("qq"));
		ups.setIf("mail", ps.getString("mail"));
		ups.setIf("profile", ps.getString("profile"));
		ups.setIf("mark", ps.getString("mark"));
		ups.setIf("homeadd_def", ps.getString("homeadd_def"));
		ups.setIf("receadd_def", ps.getString("receadd_def"));
		ups.setIf("weixin", ps.getString("weixin"));
		ups.setIf("sex", ps.getString("sex", "1"));
		ups.setIf("avatarurl", ps.getString("avatarurl"));// 微信logo
		ups.setIf("card", ps.getString("card"));// 银行卡

		ups.setIf("identity_card", ps.getString("identity_card"));// 身份证
		ups.setIf("driver_card", ps.getString("driver_card"));// 驾照
		ups.setIf("nation", ps.getString("nation"));// 民族
		ups.setIf("native_place", ps.getString("native_place"));// 籍贯
		ups.setIf("self_evaluate", ps.getString("self_evaluate"));
		ups.setIf("short_mobile", ps.getString("short_mobile"));//手机短号
		ups.where().and("user_id=?", user_id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: sys_user_info的user_name是唯一的,判断是否唯一
	 */
//	public Boolean ifUserNameValid(String user) {
//		if (ToolUtil.isEmpty(user)) {
//			return false;
//		}
//		Rcd rs = db.uniqueRecord("select * from sys_user_info where user_name=?", user);
//		if (rs == null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
 
}
