package com.dt.module.base.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.core.tool.util.ConvertUtil;
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

	private static HashMap<String, JSONArray> userMenus = new HashMap<String, JSONArray>();
	private static Logger _log = LoggerFactory.getLogger(UserService.class);

	public static UserService me() {
		return SpringContextUtil.getBean(UserService.class);
	}

	public static Boolean clearUserMenus() {
		userMenus.clear();
		return true;
	}

	public static JSONArray getUserMenu(String key) {
		if (userMenus.containsKey(key)) {
			return userMenus.get(key);
		} else {
			return null;
		}
	}

	public static void addUserMenu(String key, JSONArray value) {
		userMenus.put(key, value);
	}

	/**
	 * @Description: 获得用户信息
	 */
	public User getUser(String id) {
		User user = new User();
		String sql = "select * from sys_user_info a where a.user_id=?";
		// 账号状态信息
		Rcd u_rs = db.uniqueRecord(sql, id);
		user.setUserId(u_rs.getString("user_id"));
		user.setPassword(u_rs.getString("pwd"));
		user.setAccount(u_rs.getString("user_name"));
		user.setName(u_rs.getString("user_name"));
		user.setSalt(MD5Util.encrypt(u_rs.getString("user_id")));
		if (ToolUtil.isNotEmpty(u_rs.getString("locked")) && u_rs.getString("locked").equals("N")) {
			user.setIsLocked(false);
		}

		// 获取角色信息
		String sql2 = "select a.role_id,b.role_name from sys_user_role a,sys_role b where a.role_id=b.role_id and user_id=?";
		RcdSet r_rs = db.query(sql2, id);
		_log.info("已经获取角色数:" + r_rs.size());
		HashMap<String, String> rmap = new HashMap<String, String>();
		for (int i = 0; i < r_rs.size(); i++) {
			rmap.put(r_rs.getRcd(i).getString("role_id"), r_rs.getRcd(i).getString("role_name"));
		}
		user.setRolsSet(rmap);
		return user;
	}

	/**
	 * @Description: 获得用户菜单,限制3层
	 */
	public JSONArray getMenuTree(String user_id, String menu_id) {
		// 获得所有tree的node,限制3层
		String mflag = MD5Util.encrypt(user_id + menu_id);
		if (userMenus.containsKey(mflag)) {
			_log.info("get menus from map");
		}
		String basesql = "";
		if (BaseCommon.isSuperAdmin(user_id)) {
			basesql = "select * from sys_menus_node where deleted='N' and menu_id='" + menu_id
					+ "' and parent_id = ? order by sort";
		} else {

			basesql = " select distinct level1 node_id                                                 "
					+ "   from (select *                                                               "
					+ "           from (select b.module_id,                                            "
					+ "                        c.route,                                                "
					+ "                        c.node_name,                                            "
					+ "                        decode(instr(route, '-'),                               "
					+ "                               0,                                               "
					+ "                               route,                                           "
					+ "                               substr(route, 1, instr(route, '-') - 1)) level1  "
					+ "                   from sys_user_role a, sys_role_module b, sys_menus_node c    "
					+ "                  where c.node_id = b.module_id                                 "
					+ "                    and a.role_id = b.role_id                                   "
					+ "                    and user_id = '<#USER_ID#>')                                "
					+ "         union all                                                              "
					+ "         select *                                                               "
					+ "           from (select b.module_id,                                            "
					+ "                        c.route,                                                "
					+ "                        c.node_name,                                            "
					+ "                        decode(length(route) - length(replace(route, '-', '')), "
					+ "                               0,                                               "
					+ "                               '-1',                                            "
					+ "                               1,                                               "
					+ "                               substr(route,                                    "
					+ "                                      instr(route, '-', 1, 1) + 1,              "
					+ "                                      length(route) - instr(route, '-', 1, 1)), "
					+ "                               substr(route,                                    "
					+ "                                      instr(route, '-', 1, 1) + 1,              "
					+ "                                      instr(route, '-', 1, 2) -                 "
					+ "                                      instr(route, '-', 1, 1) - 1)) level2      "
					+ "                   from sys_user_role a, sys_role_module b, sys_menus_node c    "
					+ "                  where c.node_id = b.module_id                                 "
					+ "                    and a.role_id = b.role_id                                   "
					+ "                    and user_id = '<#USER_ID#>')                                "
					+ "         union all                                                              "
					+ "         select *                                                               "
					+ "           from (select b.module_id,                                            "
					+ "                        c.route,                                                "
					+ "                        c.node_name,                                            "
					+ "                        decode(length(route) - length(replace(route, '-', '')), "
					+ "                               0,                                               "
					+ "                               '-1',                                            "
					+ "                               1,                                               "
					+ "                               '-1',                                            "
					+ "                               2,                                               "
					+ "                               substr(route,                                    "
					+ "                                      instr(route, '-', 1, 2) + 1,              "
					+ "                                      length(route) - instr(route, '-', 1, 2)), "
					+ "                               substr(route,                                    "
					+ "                                      instr(route, '-', 1, 2) + 1,              "
					+ "                                      instr(route, '-', 1, 3) -                 "
					+ "                                      instr(route, '-', 1, 2) - 1)) level3      "
					+ "                   from sys_user_role a, sys_role_module b, sys_menus_node c    "
					+ "                  where c.node_id = b.module_id                                 "
					+ "                    and a.role_id = b.role_id                                   "
					+ "                    and user_id = '<#USER_ID#>'))                               "
					+ "  where level1 <> '-1'";
			basesql = "select a.* from sys_menus_node a, (" + basesql + ") b "
					+ "where a.deleted='N' and a.node_id = b.node_id and menu_id = '" + menu_id + "' and parent_id = ? "
					+ "order by sort ";
			basesql = basesql.replaceAll("<#USER_ID#>", user_id);

		}
		_log.info("getMenu sql:" + basesql + ",menu_id:" + menu_id);
		JSONArray r = new JSONArray();
		RcdSet first_rs = db.query(basesql, 0);
		for (int i = 0; i < first_rs.size(); i++) {
			JSONObject first_obj = ConvertUtil.OtherJSONObjectToFastJSONObject(first_rs.getRcd(i).toJsonObject());
			String first_key = first_rs.getRcd(i).getString("key");
			// 菜单显示控制
			if (!BaseCommon.isSuperAdmin(user_id)) {
				String first_is_show = first_rs.getRcd(i).getString("is_g_show");
				if (ToolUtil.isNotEmpty(first_is_show) && first_is_show.equals("N")) {
					continue;
				}
			}
			first_obj.put("state", first_key);
			int second_pid = first_rs.getRcd(i).getInteger("node_id");
			RcdSet second_rs = db.query(basesql, second_pid);
			JSONArray second_arr = new JSONArray();
			for (int j = 0; j < second_rs.size(); j++) {
				JSONObject second_obj = ConvertUtil.OtherJSONObjectToFastJSONObject(second_rs.getRcd(j).toJsonObject());
				String second_key = second_rs.getRcd(j).getString("key");
				// 菜单显示控制
				if (!BaseCommon.isSuperAdmin(user_id)) {
					String second_is_show = second_rs.getRcd(j).getString("is_g_show");
					if (ToolUtil.isNotEmpty(second_is_show) && second_is_show.equals("N")) {
						continue;
					}
				}
				second_obj.put("state", first_key + "." + second_key);
				int third_pid = second_rs.getRcd(j).getInteger("node_id");
				RcdSet third_rs = db.query(basesql, third_pid);
				second_obj.put("children_cnt", third_rs.size());
				// 处理三层
				JSONArray third_arr = ConvertUtil.OtherJSONObjectToFastJSONArray(third_rs.toJsonArrayWithJsonObject());
				for (int f = 0; f < third_arr.size(); f++) {
					// 菜单显示控制
					if (!BaseCommon.isSuperAdmin(user_id)) {
						String third_is_show = third_arr.getJSONObject(f).getString("is_g_show");
						if (ToolUtil.isNotEmpty(third_is_show) && third_is_show.equals("N")) {
							third_arr.remove(f);
						}
					}
					third_arr.getJSONObject(f).put("state",
							first_key + "." + second_key + "." + third_arr.getJSONObject(f).getString("key"));
				}
				second_obj.put("children", third_arr);
				second_arr.add(second_obj);
			}
			first_obj.put("children_cnt", second_rs.size());
			first_obj.put("children", second_arr);
			r.add(first_obj);
		}
		userMenus.put(mflag, r);
		return r;
	}

	/**
	 * @Description: 根据角色查处用户的权限
	 */
	@SuppressWarnings("unchecked")
	public List<String> findPermissionsByRoleId(String roleId) {
		_log.info("获取角色权限:" + roleId);
		return db.query(
				"select ct from sys_role_module a,sys_modules_item b where a.module_id=b.module_id and role_id=?",
				roleId).toList("ct");
	}

	/**
	 * @Description: 根据角色id查找角色名称
	 */
	public String findRoleNameByRoleId(String roleId) {
		return db.uniqueRecord("select role_name from sys_role where role_id=?", roleId).getString("role_name");
	}

	/**
	 * @Description: 判断用户是否存在
	 */
	public Boolean isExistUserId(String user_id) {
		if (ToolUtil.isEmpty(user_id)
				|| db.uniqueRecord("select * from sys_user_info where deleted='N' and user_id=?", user_id) == null) {
			return false;
		}
		return true;
	}

	/**
	 * @Description: 判断组织内用户是否存在
	 */
	public Boolean isExistEmpl(String empl_id) {
		String user_id = getUserIdFromEmpl(empl_id);
		return isExistUserId(user_id);
	}

	/**
	 * @Description: 判断用户是否锁定
	 */
	public Boolean isLocked(String user_id) {
		JSONObject res = queryUserById(user_id);
		if (ToolUtil.isEmpty(res)) {
			return true;
		}
		String locked = res.getString("locked");
		if (ToolUtil.isEmpty(locked)) {
			return true;
		}
		if (locked.equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @Description: 根据user_id获取empl_id
	 */
	public String getEmplIdFromUserId(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return null;
		}
		Rcd rs = db.uniqueRecord("select empl_id from sys_user_info where deleted='N' and user_id=?", user_id);
		if (rs == null) {
			return null;
		}
		return rs.getString("empl_id");
	}

	/**
	 * @Description: 根据empl_id获取user_id
	 */
	public String getUserIdFromEmpl(String empl_id) {
		if (ToolUtil.isEmpty(empl_id)) {
			return null;
		}
		Rcd rs = db.uniqueRecord("select user_id from sys_user_info where deleted='N' and empl_id=?", empl_id);
		if (rs == null) {
			return null;
		}
		return rs.getString("user_id");
	}

	/**
	 * @Description: 根据mobile_id获取user_id
	 */
	public String[] getUserIdFromMobile(String mobile, String user_type) {
		if (ToolUtil.isOneEmpty(mobile, user_type)) {
			return null;
		}
		RcdSet rs = db.query("select user_id from sys_user_info where deleted='N' and tel=? and user_type=?", mobile,
				user_type);
		if (rs.size() > 0) {
			return rs.toStringArray("user_id");
		}
		return null;
	}

	/**
	 * @Description: 根据用户名获取用户ID
	 */
	public String getUserIdFromUserName(String username) {
		if (ToolUtil.isEmpty(username)) {
			return null;
		}
		Rcd rs = db.uniqueRecord("select user_id from sys_user_info where deleted='N' and user_name=?", username);
		if (rs == null) {
			return null;
		}
		return rs.getString("user_id");
	}

	/**
	 * @Description: 根据邮箱获取用户ID
	 */
	public String[] getUserIdFromMail(String value, String user_type) {

		if (ToolUtil.isOneEmpty(value, user_type)) {
			return null;
		}
		RcdSet rs = db.query("select user_id from sys_user_info where deleted='N' and user_type=? and mail=?", value,
				user_type);
		return rs.toStringArray("user_id");

	}

	/**
	 * @Description: 获取所有用户类型
	 */
	public JSONArray getAllUserTypes() {
		JSONArray res = new JSONArray();
		return res;
	}

	/**
	 * @Description: 获取当前用户类型
	 */
	public String getUserType(String user_id) {
		JSONObject res = queryUserById(user_id);
		if (ToolUtil.isEmpty(res)) {
			return null;
		}
		String type = res.getString("type");
		if (ToolUtil.isEmpty(type)) {
			return null;
		}
		return type;
	}

	/**
	 * @Description: 根据用户ID查找
	 */
	public JSONObject queryUserById(String user_id) {
		String sql = "select * from sys_user_info where deleted='N' and user_id=?";
		Rcd rs = db.uniqueRecord(sql, user_id);
		if (ToolUtil.isEmpty(rs)) {
			return null;
		}
		return ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
	}

	/**
	 * @Description: 查询用户拥有的权限信息
	 */
	public HashMap<String, String> queryUserRole(String user_id) {
		HashMap<String, String> res = new HashMap<String, String>();
		String sql = "select a.*,b.role_name from sys_user_role a,sys_role b where a.role_id=b.role_id and user_id=?";
		RcdSet rs = db.query(sql, user_id);
		for (int i = 0; i < rs.size(); i++) {
			res.put(rs.getRcd(i).getString("role_id"), rs.getRcd(i).getString("role_name"));
		}
		return res;
	}

	/**
	 * @Description: 根据用户组查询
	 */
	public R queryUserByGroup(String group_id) {
		String basesql = "select * from sys_user_info a where deleted='N' and user_id not in ('"
				+ BaseCommon.getSuperAdmin() + "') ";
		String sql = "";
		if (ToolUtil.isEmpty(group_id)) {
			sql = basesql + " order by a.empl_id";
		} else {
			// 选择组
			sql = " select t1.* from ( " + basesql
					+ " ) t1 ,sys_user_group_item t2 where t1.user_id=t2.user_id and group_id='" + group_id
					+ "' order by t1.empl_id  ";
		}
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());

	}

	/**
	 * @Description: 分页查询用户
	 */
	public R queryUserPage(TypedHashMap<String, Object> ps, String type, int pageSize, int pageIndex) {
		return R.SUCCESS();
	}

	/**
	 * @Description: 按照系统用户ID删除用户
	 */
	@Transactional
	public R deleteUser(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("sys_user_info");
		ups.set("deleted", "Y");
		ups.where().and("user_id=?", user_id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 判断插入用户的类型,默认返回系统用户类型
	 */
	private String validUserType(String type, String def) {
		if (ToolUtil.isEmpty(type)) {
			return def;
		}
		if (type.equals(UserService.USER_TYPE_SYS) || type.equals(UserService.USER_TYPE_EMPL)
				|| type.equals(UserService.USER_TYPE_CRM)) {
			return type;
		}
		return def;
	}

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
		type = validUserType(type, USER_TYPE_SYS);
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
		}
		if (!ifUserNameValid(username)) {
			return R.FAILURE("登录名不可用");
		}

		Insert ins = new Insert("sys_user_info");
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
		ins.setIf("sex", ps.getString("sex", "1")); // 性别
		ins.setIf("system", ps.getString("system", "1")); // 系统
		ins.setIf("shop_id", ps.getString("shop_id")); // 默认所属店铺
		ins.set("score", ps.getString("score","0")); // 积分
		ins.setIf("open_id", ps.getString("open_id")); // 微信open_id
		ins.set("balance", ps.getString("balance", "0"));//余额
		ins.setIf("avatarurl", ps.getString("avatarurl"));// 微信logo
		ins.set("deleted", "N");
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
		type = validUserType(type, USER_TYPE_SYS);
		String user_id = ps.getString("user_id");
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("sys_user_info");
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
		ups.where().and("user_id=?", user_id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: sys_user_info的user_name是唯一的,判断是否唯一
	 */
	public Boolean ifUserNameValid(String user) {
		if (ToolUtil.isEmpty(user)) {
			return false;
		}
		Rcd rs = db.uniqueRecord("select * from sys_user_info where user_name=?", user);
		if (rs == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description: 修改用户角色
	 */
	public R changeUserRole(TypedHashMap<String, Object> ps) {
		String userids = ps.getString("user_ids");
		String roles = ps.getString("roles");
		if (ToolUtil.isOneEmpty(userids, roles)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray userids_arr = JSONArray.parseArray(userids);
		JSONArray roles_arr = JSONArray.parseArray(roles);
		if (ToolUtil.isEmpty(roles_arr)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		if (userids_arr.isEmpty()) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		for (int i = 0; i < userids_arr.size(); i++) {
			// 处理用户
			db.execute("delete from sys_user_role where user_id=?", userids_arr.getString(i));
			for (int j = 0; j < roles_arr.size(); j++) {
				db.execute("insert into sys_user_role(user_id,role_id) values(?,?)", userids_arr.getString(i),
						roles_arr.getString(j));
			}
		}
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 修改用户密码
	 */
	public R changeUserPwd(String opwd, String npwd, String user_id) {
		String csql = "select count(1) value from sys_user_info where pwd='" + opwd + "' and user_id=?";
		if (db.uniqueRecord(csql, user_id).getString("value").equals("1")) {
			Update me = new Update("sys_user_info");
			me.set("pwd", npwd);
			me.where().and("user_id=?", user_id);
			db.execute(me);
		} else {
			return R.FAILURE("旧密码不正确,请重新输入.");
		}
		return R.SUCCESS_OPER();
	}

}
