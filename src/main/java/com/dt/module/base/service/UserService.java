package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.SpringContextUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.UuidUtil;
import com.dt.core.common.util.support.TypedHashMap;
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

	public static UserService me() {
		return SpringContextUtil.getBean(UserService.class);
	}
	/**
	 * @Description: 获得用户信息
	 */
	public User getUser(String id) {
		User user = new User();
		String sql = "select * from SYS_USER_info a where a.user_id=?";
		// 账号状态信息
		Rcd u_rs = db.uniqueRecord(sql, id);
		user.setUserId(u_rs.getString("user_id"));
		user.setPassword(u_rs.getString("pwd"));
		user.setAccount(u_rs.getString("user_name"));
		user.setName(u_rs.getString("user_name"));
		user.setSalt("salt");
		if (u_rs.getString("locked").equals("Y")) {
			user.setIsLocked(true);
		}
		// 获取角色信息
		String sql2 = "select a.role_id,b.ROLE_NAME from SYS_USER_ROLE a, sys_role b where a.ROLE_ID=b.ROLE_ID and user_id=?";
		RcdSet r_rs = db.query(sql2, id);
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
	public JSONArray getMenuTree(String user_id, String meu_id) {
		// 获得所有tree的node,限制3层
		String sql = "with idtab as ( " + "select distinct  level1 node_id from ( "
				+ "select * from (select b.module_id,c.route,c.node_name , "
				+ "decode(instr(route,'-'), 0,route,substr(route,1,instr(route,'-') -1)) level1 "
				+ "from  sys_user_role a, sys_role_module b ,sys_menus_node c " + "where c.node_id=b.module_id "
				+ "and a.role_id=b.role_id " + "and user_id='<#USER_ID#>' ) " + "union all select * from ( "
				+ "select b.module_id,c.route,c.node_name , " + "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), "
				+ "0 , '-1', " + "1 , substr(route,instr(route,'-',1,1)+1, LENGTH(route)-instr(route,'-',1,1)), "
				+ "substr(route,instr(route,'-',1,1)+1 ,instr(route,'-',1,2) - instr(route,'-',1,1) -1)) level2 "
				+ "from  sys_user_role a, sys_role_module b ,sys_menus_node c " + "where c.node_id=b.module_id "
				+ "and a.role_id=b.role_id " + "and user_id='<#USER_ID#>' ) " + "union all " + "select * from ( "
				+ "select b.module_id,c.route,c.node_name , " + "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), "
				+ "0 , '-1', " + "1 , '-1', "
				+ "2 , substr(route,instr(route,'-',1,2)+1, LENGTH(route)-instr(route,'-',1,2)), "
				+ "substr(route,instr(route,'-',1,2)+1 ,instr(route,'-',1,3) - instr(route,'-',1,2) -1)) level3 "
				+ "from  sys_user_role a, sys_role_module b ,sys_menus_node c " + "where c.node_id=b.module_id "
				+ "and a.role_id=b.role_id " + "and user_id='<#USER_ID#>' )) where level1<>'-1' " + ") "
				+ "select * from sys_menus_node a,  idtab b "
				+ "where a.node_id=b.node_id and menu_id=? and parent_id=? order by sort ";
		JSONArray r = new JSONArray();
		String basesql = sql.replaceAll("<#USER_ID#>", user_id);
		RcdSet first_rs = db.query(basesql, meu_id, 0);
		for (int i = 0; i < first_rs.size(); i++) {
			JSONObject first_obj = ConvertUtil.OtherJSONObjectToFastJSONObject(first_rs.getRcd(i).toJsonObject());
			String first_key = first_rs.getRcd(i).getString("key");
			first_obj.put("STATE", first_key);
			RcdSet second_rs = db.query(basesql, meu_id, first_rs.getRcd(i).getString("node_id"));
			JSONArray second_arr = new JSONArray();
			for (int j = 0; j < second_rs.size(); j++) {
				JSONObject second_obj = ConvertUtil.OtherJSONObjectToFastJSONObject(second_rs.getRcd(j).toJsonObject());
				String second_key = second_rs.getRcd(j).getString("key");
				second_obj.put("STATE", first_key + "." + second_key);
				RcdSet third_rs = db.query(basesql, meu_id, second_rs.getRcd(j).getString("node_id"));
				second_obj.put("CHILDREN_CNT", third_rs.size());
				// 处理三层
				JSONArray third_arr = ConvertUtil.OtherJSONObjectToFastJSONArray(third_rs.toJsonArrayWithJsonObject());
				for (int f = 0; f < third_arr.size(); f++) {
					third_arr.getJSONObject(f).put("STATE",
							first_key + "." + second_key + "." + third_arr.getJSONObject(f).getString("KEY"));
				}
				second_obj.put("CHILDREN", third_arr);
				second_arr.add(second_obj);
			}
			first_obj.put("CHILDREN_CNT", second_rs.size());
			first_obj.put("CHILDREN", second_arr);
			r.add(first_obj);
		}
		return r;
	}
	/**
	 * @Description: 根据角色查处用户的权限
	 */
	@SuppressWarnings("unchecked")
	public List<String> findPermissionsByRoleId(String roleId) {
		return db.query(" select * from SYS_ROLE_MODULE where role_id=?", roleId).toList("module_id");
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
				|| db.uniqueRecord("select * from SYS_USER_INFO where deleted='N' and user_id=?", user_id) == null) {
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
		String locked = res.getString("LOCKED");
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
		Rcd rs = db.uniqueRecord("select empl_id from SYS_USER_INFO where deleted='N'  and user_id=?", user_id);
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
		Rcd rs = db.uniqueRecord("select user_id from SYS_USER_INFO where deleted='N'  and empl_id=?", empl_id);
		if (rs == null) {
			return null;
		}
		return rs.getString("user_id");
	}
	/**
	 * @Description: 根据用户名获取用户ID
	 */
	public String getUserIdFromUserName(String username) {
		if (ToolUtil.isEmpty(username)) {
			return null;
		}
		Rcd rs = db.uniqueRecord("select user_id from SYS_USER_INFO where deleted='N'  and user_name=?", username);
		if (rs == null) {
			return null;
		}
		return rs.getString("user_id");
	}
	/**
	 * @Description: 根据邮箱获取用户ID
	 */
	public ArrayList<String> getUserIdFromMail(String value, String user_type) {
		ArrayList<String> res = new ArrayList<String>();
		if (ToolUtil.isOneEmpty(value, user_type)) {
			return res;
		}
		RcdSet rs = db.query("select user_id from SYS_USER_INFO where deleted='N' and user_type=? and mail=?", value,
				user_type);
		for (int i = 0; i < rs.size(); i++) {
			res.add(rs.getRcd(i).getString("user_id"));
		}
		return res;
	}
	/**
	 * @Description: 根据手机号获取用户ID
	 */
	public ArrayList<String> getUserIdFromMobile(String value, String user_type) {
		ArrayList<String> res = new ArrayList<String>();
		if (ToolUtil.isOneEmpty(value, user_type)) {
			return res;
		}
		RcdSet rs = db.query("select user_id from SYS_USER_INFO where deleted='N' and user_type=? and tel=?", value,
				user_type);
		for (int i = 0; i < rs.size(); i++) {
			res.add(rs.getRcd(i).getString("user_id"));
		}
		return res;
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
		String type = res.getString("TYPE");
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
		String sql = "select a.*,b.role_name from SYS_USER_ROLE a,SYS_ROLE b where a.ROLE_ID=b.ROLE_ID and user_id=?";
		RcdSet rs = db.query(sql, user_id);
		for (int i = 0; i < rs.size(); i++) {
			res.put(rs.getRcd(i).getString("role_id"), rs.getRcd(i).getString("role_name"));
		}
		return res;
	}
	/**
	 * @Description: 根据用户组查询
	 */
	public ResData queryUserByGroup(String group_id) {
		String basesql = " select * from sys_user_info a where deleted='N' ";
		String sql = "";
		if (ToolUtil.isEmpty(group_id)) {
			sql = basesql + " order by a.empl_id";
		} else {
			// 选择组
			sql = " select t1.* from ( " + basesql
					+ " ) t1 ,sys_user_group_item t2 where t1.user_id=t2.user_id  and group_id='" + group_id
					+ "' order by t1.empl_id  ";
		}
		return ResData.SUCCESS("操作成功", db.query(sql).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 分页查询用户
	 */
	public ResData queryUserPage(TypedHashMap<String, Object> ps, String type, int pageSize, int pageIndex) {
		return ResData.SUCCESS();
	}
	/**
	 * @Description: 按照系统用户ID删除用户
	 */
	@Transactional
	public ResData deleteUser(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		// 处理系统人员表
		Update ups = new Update("sys_user_info");
		ups.set("deleted", "Y");
		ups.where().and("user_id=?", user_id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 判断插入用户的类型,默认返回系统用户类型
	 */
	private String validUserType(String type, String def) {
		if (ToolUtil.isEmpty(type)) {
			return def;
		}
		if (type.equals(UserService.USER_TYPE_SYS) || type.equals(UserService.USER_TYPE_EMPL)) {
			return type;
		}
		return def;
	}
	/**
	 * @Description: 增加用户
	 */
	@Transactional
	public ResData addUser(TypedHashMap<String, Object> ps, String type) {
		type = validUserType(type, USER_TYPE_SYS);
		String username = "";
		String user_id = UuidUtil.getUUID();
		ResData emplRes = getEmplNextId();
		if (emplRes.isFailed()) {
			return ResData.FAILURE("生成序列号失败");
		}
		String empl_id = (String) emplRes.getData();
		// 处理唯一登录名
		if (type.equals(UserService.USER_TYPE_EMPL)) {
			username = "Empl_" + empl_id;
		} else if (type.equals(UserService.USER_TYPE_SYS)) {
			username = ps.getString("USER_NAME");
		}
		if (!ifUserNameValid(username)) {
			return ResData.FAILURE("登录名不可用");
		}
		Insert ins = new Insert("sys_user_info");
		ins.set("USER_ID", user_id);
		ins.set("EMPL_ID", empl_id);
		ins.set("USER_NAME", username);
		ins.set("USER_TYPE", type);
		ins.setIf("NICKNAME", ps.getString("NICKNAME", "toy"));
		ins.setIf("NAME", ps.getString("NAME", "toy"));
		ins.setIf("PWD", ps.getString("PWD", "0"));
		ins.setIf("STATUS", ps.getString("STATUS"));
		ins.setIf("ORG_ID", ps.getString("ORG_ID"));
		ins.setIf("LOCKED", ps.getString("LOCKED", "Y"));
		ins.setIf("TEL", ps.getString("TEL"));
		ins.setIf("QQ", ps.getString("QQ"));
		ins.setIf("MAIL", ps.getString("MAIL"));
		ins.setIf("PROFILE", ps.getString("PROFILE"));
		ins.setIf("MARK", ps.getString("MARK"));
		ins.setIf("HOMEADD_DEF", ps.getString("HOMEADD_DEF"));
		ins.setIf("RECEADD_DEF", ps.getString("RECEADD_DEF"));
		ins.setIf("WEIXIN", ps.getString("WEIXIN"));
		ins.setIf("SEX", ps.getString("SEX", "1"));
		ins.set("DELETED", "N");
		db.execute(ins);
		return ResData.SUCCESS_OPER(user_id);
	}
	/**
	 * @Description: 获取Empl的下一个ID
	 */
	public ResData getEmplNextId() {
		Rcd seqrs = db
				.uniqueRecord("select LPAD(cast(value as int)+1,6,'0') seq from sys_params where id='sys_empl_no'");
		if (seqrs == null) {
			return ResData.FAILURE("获取员工编号错误,无法生成员工.");
		}
		String empl_id = seqrs.getString("seq");
		Update me = new Update("sys_params");
		me.set("value", empl_id);
		me.where().and("id=?", "sys_empl_no");
		db.execute(me);
		return ResData.SUCCESS_OPER(empl_id);
	}
	/**
	 * @Description: 根据user_id修改人员表
	 */
	@Transactional
	public ResData updateUser(TypedHashMap<String, Object> ps, String type) {
		// 最终根据user_id去更新用户数据
		// 获取用户的user_id,empl_id
		type = validUserType(type, USER_TYPE_SYS);
		String user_id = ps.getString("USER_ID");
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("sys_user_info");
		ups.setIf("NICKNAME", ps.getString("NICKNAME", "toy"));
		ups.setIf("NAME", ps.getString("NAME", "toy"));
		ups.setIf("PWD", ps.getString("PWD", "0"));
		ups.setIf("STATUS", ps.getString("STATUS"));
		ups.setIf("ORG_ID", ps.getString("ORG_ID"));
		ups.setIf("LOCKED", ps.getString("LOCKED", "Y"));
		ups.setIf("TEL", ps.getString("TEL"));
		ups.setIf("QQ", ps.getString("QQ"));
		ups.setIf("MAIL", ps.getString("MAIL"));
		ups.setIf("PROFILE", ps.getString("PROFILE"));
		ups.setIf("MARK", ps.getString("MARK"));
		ups.setIf("HOMEADD_DEF", ps.getString("HOMEADD_DEF"));
		ups.setIf("RECEADD_DEF", ps.getString("RECEADD_DEF"));
		ups.setIf("WEIXIN", ps.getString("WEIXIN"));
		ups.setIf("SEX", ps.getString("SEX", "1"));
		ups.where().and("user_id=?", user_id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
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
	public ResData changeUserRole(TypedHashMap<String, Object> ps) {
		String userids = ps.getString("USER_IDS");
		String roles = ps.getString("ROLES");
		if (ToolUtil.isOneEmpty(userids, roles)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray userids_arr = JSONArray.parseArray(userids);
		JSONArray roles_arr = JSONArray.parseArray(roles);
		if (ToolUtil.isEmpty(roles_arr)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (userids_arr.size() == 0) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		for (int i = 0; i < userids_arr.size(); i++) {
			// 处理用户
			db.execute("delete from SYS_USER_ROLE where user_id=?", userids_arr.getString(i));
			for (int j = 0; j < roles_arr.size(); j++) {
				db.execute("insert into SYS_USER_ROLE(user_id,role_id) values(?,?)", userids_arr.getString(i),
						roles_arr.getString(j));
			}
		}
		return ResData.SUCCESS_OPER();
	}
}
