package com.dt.module.hrm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.UuidUtil;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.ParamsService;
import com.dt.module.base.service.UserService;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:11:19
 * @Description: TODO
 */
@Service
public class EmplService extends BaseService {
	@Autowired
	private UserService userService;
	@Autowired
	private ParamsService paramsService;

	/**
	 * @Description: 添加员工
	 */
	@Transactional
	public ResData addEmployee(TypedHashMap<String, Object> ps) {
		ArrayList<String> exeSqls = new ArrayList<String>();
		// 先判断组织
		String nodes = ps.getString("NODES");
		if (ToolUtil.isEmpty(nodes)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray nodes_arr = (JSONArray) JSONArray.parse(nodes);
		String emplpartCtl = ifEmplCanMultiPart();
		if (emplpartCtl.equals("Y")) {
			if (nodes_arr.size() > 1) {
				return ResData.FAILURE("必须属于一个组织,不可多选");
			}
		}
		ResData user_rs = userService.addUser(ps, UserService.USER_TYPE_EMPL);
		String empl_id = userService.getEmplIdFromUserId((String) user_rs.getData());
		for (int i = 0; i < nodes_arr.size(); i++) {
			String node_id = nodes_arr.getJSONObject(i).getString("NODE_ID");
			Insert ins3 = new Insert("hrm_org_employee");
			ins3.set("id", UuidUtil.getUUID());
			ins3.set("node_id", node_id);
			ins3.set("deleted", "N");
			ins3.set("empl_id", empl_id);
			exeSqls.add(ins3.getSQL());
		}
		if (user_rs.isSuccess()) {
			for (int i = 0; i < exeSqls.size(); i++) {
				db.execute(exeSqls.get(i).toString());
			}
		} else {
			return user_rs;
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 根据empl_id删除员工
	 */
	public ResData delEmployee(String empl_id) {
		String user_id = userService.getUserIdFromEmpl(empl_id);
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE("用户ID不存在");
		}
		return userService.deleteUser(user_id);
	}

	/**
	 * @Description: 根据empl_id更新员工
	 */
	public ResData updateEmployee(TypedHashMap<String, Object> ps) {
		ArrayList<String> exeSqls = new ArrayList<String>();
		String user_id = ps.getString("USER_ID");
		String empl_id = ps.getString("EMPL_ID");
		if (ToolUtil.isEmpty(empl_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		// 判断是否需要重置user_id,因为updateUser是根据user_id修改数据
		if (ToolUtil.isEmpty(user_id)) {
			user_id = userService.getUserIdFromEmpl(empl_id);
		}
		ps.put("USER_ID", user_id);
		/***********************************
		 * 组织内用户插入的判断
		 **************************************/
		String nodes = ps.getString("NODES");
		if (ToolUtil.isEmpty(nodes)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray nodes_arr = (JSONArray) JSONArray.parse(nodes);
		String emplpartCtl = ifEmplCanMultiPart();
		if (emplpartCtl.equals("Y")) {
			if (nodes_arr.size() > 1) {
				return ResData.FAILURE("必须属于一个组织,不可多选");
			}
		}
		exeSqls.add("delete from hrm_org_employee where empl_id='" + empl_id + "'");
		for (int i = 0; i < nodes_arr.size(); i++) {
			String node_id = nodes_arr.getJSONObject(i).getString("NODE_ID");
			Insert ins3 = new Insert("hrm_org_employee");
			ins3.set("id", UuidUtil.getUUID());
			ins3.set("node_id", node_id);
			ins3.set("deleted", "N");
			ins3.set("empl_id", empl_id);
			exeSqls.add(ins3.getSQL());
		}
		/*********************************** 执行 **************************************/
		ResData user_rs = userService.updateUser(ps, UserService.USER_TYPE_EMPL);
		if (user_rs.isSuccess()) {
			for (int i = 0; i < exeSqls.size(); i++) {
				db.execute(exeSqls.get(i).toString());
			}
		} else {
			return user_rs;
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 根据组织ID查找员工
	 */
	public ResData queryEmplByOrg(String node_id) {
		if (ToolUtil.isEmpty(node_id)) {
			return ResData.FAILURE("无节点");
		}
		String sql = "select c.* from hrm_org_employee a,sys_user_info c where a.empl_id=c.empl_id and c.user_type= ? and a.node_id=?";
		RcdSet rs = db.query(sql, UserService.USER_TYPE_EMPL, node_id);
		return ResData.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 查询员工
	 */
	public ResData queryEmplList(TypedHashMap<String, Object> ps) {
		String node_id = ps.getString("NODE_ID");
		String name = ps.getString("NAME");
		String bsql = "";
		if (node_id != null && (!node_id.equals("-1"))) {
			// 选择需要的节点
			Rcd routev = db.uniqueRecord("select route from hrm_org_part where node_id=?", node_id);
			if (routev == null) {
				ResData.FAILURE("该节点不存在");
			}
			String route = routev.getString("route").replaceAll("-", ",");
			bsql = "select b.*,c.node_name from hrm_org_employee a,sys_user_info b,hrm_org_part c where b.deleted='N' and a.empl_id = b.empl_id and c.node_id=a.node_id ";
			bsql = bsql + " and a.node_id in(" + route + ") ";
		} else {
			bsql = "select b.*,c.node_name from hrm_org_employee a,sys_user_info b,hrm_org_part c where b.deleted='N' and a.empl_id = b.empl_id and c.node_id=a.node_id ";
		}
		if (name != null && (!name.trim().equals(""))) {
			bsql = bsql + " and b.name like '%" + name + "%'";
		}
		bsql = bsql + " order by name";
		System.out.println(bsql);
		return ResData.SUCCESS_OPER(db.query(bsql).toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 根据empl_id查找员工
	 */
	public ResData queryEmplById(String empl_id) {
		JSONObject res = new JSONObject();
		Rcd info = db.uniqueRecord("select * from sys_user_info where deleted='N' and empl_id=?", empl_id);
		if (info == null) {
			return ResData.FAILURE_NODATA();
		}
		// 获取组织信息

		res = ConvertUtil.OtherJSONObjectToFastJSONObject(info.toJsonObject());
		res.put("PARTS", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(
				"select a.*,b.node_name from hrm_org_employee a,hrm_org_part b where a.node_id=b.node_id and empl_id=?",
				empl_id).toJsonArrayWithJsonObject()));
		return ResData.SUCCESS("获取成功", res);
	}

	/**
	 * @Description: 判断用户是否可以存在多个组织中,默认返回N
	 *               sys_empl_org_num_ctl:N(可以多个组织),Y(只能属于一个组织)
	 */
	public String ifEmplCanMultiPart() {
		ResData emplpartRes = paramsService.queryParamsByIdWithExist("sys_empl_org_num_ctl",
				ParamsService.TYPE_SYSINTER, "N");
		return ToolUtil.parseYNValueDefN(emplpartRes.getDataToJSONObject().getString("VALUE"));
	}
}
