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
	public static String USER_TYPE_SYS = "sys";
	public static String USER_TYPE_EMPL = "empl";

	public static UserService me() {
		return SpringContextUtil.getBean(UserService.class);
	}
	/**
	 * @Description: 获得用户信息
	 */
	public User getUser(String id) {
		User user = new User();
		String sql = "select * from SYS_USER_info a LEFT JOIN  HRM_EMPLOYEE b on a.USER_ID=b.USER_ID where a.user_id=?";
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
			JSONObject first_obj = JSONObject.parseObject(first_rs.getRcd(i).toJsonObject().toString());
			String first_key = first_rs.getRcd(i).getString("key");
			first_obj.put("STATE", first_key);
			RcdSet second_rs = db.query(basesql, meu_id, first_rs.getRcd(i).getString("node_id"));
			JSONArray second_arr = new JSONArray();
			for (int j = 0; j < second_rs.size(); j++) {
				JSONObject second_obj = JSONObject.parseObject(second_rs.getRcd(j).toJsonObject().toString());
				String second_key = second_rs.getRcd(j).getString("key");
				second_obj.put("STATE", first_key + "." + second_key);
				RcdSet third_rs = db.query(basesql, meu_id, second_rs.getRcd(j).getString("node_id"));
				second_obj.put("CHILDREN_CNT", third_rs.size());
				// 处理三层
				JSONArray third_arr = JSONArray.parseArray(third_rs.toJsonArrayWithJsonObject().toString());
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
	public Boolean isExistUser(String user_id) {
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
		if (ToolUtil.isEmpty(empl_id)) {
			return false;
		}
		Rcd rs = db.uniqueRecord("select * from SYS_USER_INFO where deleted='N' and empl_id=?", empl_id);
		if (rs == null) {
			return false;
		}
		return true;
	}
	/**
	 * @Description: 判断用户是否锁定
	 */
	public Boolean isLocked(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return false;
		}
		Rcd rs = db.uniqueRecord("select locked from SYS_USER_INFO where deleted='N' and user_id=?", user_id);
		if (rs == null) {
			return false;
		}
		if (rs.getString("locked").equals("Y")) {
			return true;
		}
		return false;
	}
	/**
	 * @Description: 判断用户的组织ID
	 */
	public String getEmplId(String user_id) {
		Rcd rs = db.uniqueRecord("select empl_id from SYS_USER_INFO where deleted='N'  and user_id=?", user_id);
		if (rs == null) {
			return null;
		}
		return rs.getString("empl_id");
	}
	public String getUserId(String empl_id) {
		Rcd rs = db.uniqueRecord("select user_id from SYS_USER_INFO where deleted='N'  and empl_id=?", empl_id);
		if (rs == null) {
			return null;
		}
		return rs.getString("user_id");
	}
	/**
	 * @Description: 根据用户ID查找
	 */
	public ResData queryUserById(String user_id) {
		String sql = "select  a.user_id user_no ,a.user_name,a.locked ,b.* from  (select * from  sys_user_info where user_id=?) a  left join hrm_employee b on a.user_id=b.user_id ";
		Rcd rs = db.uniqueRecord(sql, user_id);
		if (ToolUtil.isEmpty(rs)) {
			ResData.FAILURE_NODATA();
		}
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}
	/**
	 * @Description: 根据用户组查询
	 */
	public ResData queryUserByGroup(String group_id) {
		String basesql = " select a.type ,a.user_id user_no ,a.user_name,a.locked ,b.* from (select * from sys_user_info where deleted='N') a left join hrm_employee b on a.user_id=b.user_id    ";
		String sql = "";
		if (ToolUtil.isEmpty(group_id)) {
			sql = basesql + " order by a.empl_id";
		} else {
			// 选择组
			sql = " select t1.* from ( " + basesql
					+ " ) t1 ,sys_user_group_item t2 where t1.user_no=t2.user_id  and group_id='" + group_id
					+ "' order by t1.user_no  ";
		}
		return ResData.SUCCESS("操作成功", db.query(sql).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 按照系统用户ID删除用户,同时删除组织用户及其相关
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
		// 处理组织人员表
		Update ups1 = new Update("hrm_employee");
		ups1.set("deleted", "Y");
		ups1.where().and("user_id=?", user_id);
		db.execute(ups1);
		// 删除用户组中的数据
		db.execute("delete from SYS_USER_GROUP_ITEM where user_id=?", user_id);
		String type = validUserType(getUserType(user_id));
		if (type.equals(USER_TYPE_EMPL)) {
			// 员工表
			db.execute(
					"update HRM_ORG_EMPLOYEE set deleted='N' where EMPL_ID in (select EMPL_ID from HRM_EMPLOYEE  where user_id=?)",
					user_id);
		} else if (type.equals(USER_TYPE_SYS)) {
			//
		}
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 判断插入用户的类型
	 */
	private String validUserType(String type) {
		if (ToolUtil.isEmpty(type)) {
			return UserService.USER_TYPE_SYS;
		}
		if (type.equals(UserService.USER_TYPE_SYS) || type.equals(UserService.USER_TYPE_EMPL)) {
			return type;
		}
		return UserService.USER_TYPE_SYS;
	}
	/**
	 * @Description: 增加用户
	 * @Description:user:必须(USER_NAME)
	 * @Description:empl:必须(组织信息NODES(node_id)) type:sys|empl
	 */
	@Transactional
	public ResData addUser(TypedHashMap<String, Object> ps, String type) {
		ArrayList<String> exeSqls = new ArrayList<String>();
		type = validUserType(type);
		// 获取user_id和empl_id
		String user_id = UuidUtil.getUUID();
		ResData emplRes = getEmplNextId();
		if (!emplRes.isSuccess()) {
			return ResData.FAILURE("生成序列号失败");
		}
		String empl_id = (String) emplRes.getData();
		/*********************************** 插入sys_user_info表 **************************************/
		Insert ins = new Insert("sys_user_info");
		String username = ps.getString("USER_NAME");
		ins.set("empl_id", empl_id);
		if (type.equals(UserService.USER_TYPE_EMPL)) {
			username = "empl" + empl_id;
		}
		if (!ifUserNameValid(username)) {
			return ResData.FAILURE("登录名不可用");
		}
		ins.set("USER_ID", user_id);
		ins.set("USER_NAME", username);
		ins.set("PWD", ps.getString("PWD", "0"));
		ins.set("DELETED", "N");
		ins.set("TYPE", type);
		ins.set("LOCKED", ps.getString("LOCKED", "Y"));
		ins.setIf("ORG_ID", ps.getString("ORG_ID"));
		ins.setIf("STATUS", ps.getString("STATUS"));
		exeSqls.add(ins.getSQL());
		/*********************************** 插入empl表 **************************************/
		Insert empl = new Insert("hrm_employee");
		empl.set("user_id", user_id);
		empl.set("deleted", "N");
		empl.set("empl_id", empl_id);
		empl.set("nick", ps.getString("NICK", "toy"));
		empl.set("sex", ps.getString("SEX", "1"));
		empl.setIf("name", ps.getString("NAME"));
		empl.setIf("tel", ps.getString("TEL"));
		empl.setIf("qq", ps.getString("QQ"));
		empl.setIf("mail", ps.getString("MAIL"));
		empl.setIf("profile", ps.getString("PROFILE"));
		empl.setIf("mark", ps.getString("MARK"));
		empl.setIf("homeadd_def", ps.getString("HOMEADD_DEF"));
		empl.setIf("receadd_def", ps.getString("RECEADD_DEF"));
		empl.setIf("weixin", ps.getString("WEIXIN"));
		exeSqls.add(empl.getSQL());
		/*********************************** 组织内用户插入的判断 **************************************/
		if (type.equals(UserService.USER_TYPE_EMPL)) {
			// 先判断组织
			ResData emplpartRes = ifEmplCanMultiPart();
			if (!emplpartRes.isSuccess()) {
				return ResData.FAILURE("组织判断失败,请检查系统参数配置");
			}
			String nodes = ps.getString("NODES");
			if (ToolUtil.isEmpty(nodes)) {
				return ResData.FAILURE_ERRREQ_PARAMS();
			}
			// 用户组织数据
			JSONArray nodes_arr = (JSONArray) JSONArray.parse(nodes);
			for (int i = 0; i < nodes_arr.size(); i++) {
				String node_id = nodes_arr.getJSONObject(i).getString("NODE_ID");
				Insert ins3 = new Insert("hrm_org_employee");
				ins3.set("id", UuidUtil.getUUID());
				ins3.set("node_id", node_id);
				ins3.set("deleted", "N");
				ins3.set("empl_id", empl_id);
				exeSqls.add(ins3.getSQL());
			}
		}
		/*********************************** 插入empl表 **************************************/
		for (int i = 0; i < exeSqls.size(); i++) {
			System.out.println(exeSqls.get(i).toString());
			db.execute(exeSqls.get(i).toString());
		}
		return ResData.SUCCESS_OPER();
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
	 * @Description: 修改用户
	 * @Description:user:必须要有user_id
	 * @Description:empl:必须要有empl_id
	 */
	@Transactional
	public ResData updateUser(TypedHashMap<String, Object> ps, String type) {
		ArrayList<String> exeSqls = new ArrayList<String>();
		// 最终根据user_id去更新用户数据
		// 获取用户的user_id,empl_id
		type = validUserType(type);
		String user_id = "";
		String empl_id = "";
		if (type.equals(UserService.USER_TYPE_EMPL)) {
			empl_id = ps.getString("EMPL_ID", "");
			user_id = this.getUserId(empl_id);
			if (ToolUtil.isOneEmpty(empl_id, user_id)) {
				return ResData.FAILURE_ERRREQ_PARAMS();
			}
		} else if (type.equals(UserService.USER_TYPE_SYS)) {
			user_id = ps.getString("USER_ID");
		}
		/*********************************** 更新sys_user_info表 **************************************/
		Update ups = new Update("sys_user_info");
		ups.set("LOCKED", ps.getString("LOCKED", "Y"));
		ups.setIf("STATUS", ps.getString("STATUS"));
		ups.where().and("user_id=?", user_id);
		exeSqls.add(ups.getSQL());
		/*********************************** 插入empl表 **************************************/
		Update empl = new Update("hrm_employee");
		empl.set("nick", ps.getString("NICK", "toy"));
		empl.set("sex", ps.getString("SEX", "1"));
		empl.setIf("name", ps.getString("NAME"));
		empl.setIf("tel", ps.getString("TEL"));
		empl.setIf("qq", ps.getString("QQ"));
		empl.setIf("mail", ps.getString("MAIL"));
		empl.setIf("profile", ps.getString("PROFILE"));
		empl.setIf("mark", ps.getString("MARK"));
		empl.setIf("homeadd_def", ps.getString("HOMEADD_DEF"));
		empl.setIf("receadd_def", ps.getString("RECEADD_DEF"));
		empl.setIf("weixin", ps.getString("WEIXIN"));
		empl.where().and("empl_id=?", empl_id);
		exeSqls.add(empl.getSQL());
		/*********************************** 组织内用户插入的判断 **************************************/
		if (type.equals(UserService.USER_TYPE_EMPL)) {
			// 先判断组织
			exeSqls.add("delete from hrm_org_employee where empl_id='" + empl_id + "'");
			ResData emplpartRes = ifEmplCanMultiPart();
			if (!emplpartRes.isSuccess()) {
				return ResData.FAILURE("组织判断失败,请检查系统参数配置");
			}
			String nodes = ps.getString("NODES");
			if (ToolUtil.isEmpty(nodes)) {
				return ResData.FAILURE_ERRREQ_PARAMS();
			}
			// 用户组织数据
			JSONArray nodes_arr = (JSONArray) JSONArray.parse(nodes);
			for (int i = 0; i < nodes_arr.size(); i++) {
				String node_id = nodes_arr.getJSONObject(i).getString("NODE_ID");
				Insert ins3 = new Insert("hrm_org_employee");
				ins3.set("id", UuidUtil.getUUID());
				ins3.set("node_id", node_id);
				ins3.set("deleted", "N");
				ins3.set("empl_id", empl_id);
				exeSqls.add(ins3.getSQL());
			}
		}
		/*********************************** 插入empl表 **************************************/
		for (int i = 0; i < exeSqls.size(); i++) {
			System.out.println(exeSqls.get(i).toString());
			db.execute(exeSqls.get(i).toString());
		}
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: sys_user_info的user_name是唯一的,判断是否唯一
	 */
	public Boolean ifUserNameValid(String user) {
		if (user != null && db.uniqueRecord("select * from sys_user_info where user_name=?", user) == null) {
			return true;
		}
		return false;
	}
	/**
	 * @Description: 修改用户角色
	 */
	public ResData changeUserRole(TypedHashMap<String, Object> ps) {
		String userids = ps.getString("USER_IDS");
		String roles = ps.getString("ROLES");
		if (userids == null || roles == null) {
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
						roles_arr.getJSONObject(j).getString("ROLE_ID"));
			}
		}
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 判断用户是否可以存在多个组织中
	 */
	public ResData ifEmplCanMultiPart() {
		// 组织个数控制判断
		Rcd ctl = db.uniqueRecord("select * from SYS_PARAMS where id='sys_empl_org_num_ctl'");
		if (ToolUtil.isEmpty(ctl)) {
			return ResData.FAILURE_SYS_PARAMS();
		}
		String ctlstr = ctl.getString("value");
		if (ToolUtil.isEmpty(ctlstr)) {
			return ResData.FAILURE_SYS_PARAMS();
		}
		String res = "";
		if (ctlstr.equals("N")) {
			res = "N";
		} else if (ctlstr.equals("Y")) {
			res = "Y";
		} else {
			res = "N";
		}
		return ResData.SUCCESS_OPER(res);
	}
	/**
	 * @Description: 获取用户类型
	 */
	public String getUserType(String user_id) {
		String sql = "select type from sys_user_info where user_id=?";
		Rcd rs = db.uniqueRecord(sql, user_id);
		if (rs == null) {
			return null;
		}
		return validUserType(rs.getString("type"));
	}
}
