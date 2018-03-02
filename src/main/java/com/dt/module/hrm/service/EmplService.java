package com.dt.module.hrm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
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
	public R addEmployee(TypedHashMap<String, Object> ps) {
		ArrayList<SQL> exeSqls = new ArrayList<SQL>();
		// 先判断组织
		String nodes = ps.getString("nodes");
		if (ToolUtil.isEmpty(nodes)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray nodes_arr = (JSONArray) JSONArray.parse(nodes);
		String emplpartCtl = ifEmplCanMultiPart();
		if (emplpartCtl.equals("Y")) {
			if (nodes_arr.size() > 1) {
				return R.FAILURE("必须属于一个组织,不可多选");
			}
		}
		R user_rs = userService.addUser(ps, UserService.USER_TYPE_EMPL);
		String empl_id = userService.getEmplIdFromUserId((String) user_rs.getData());
		for (int i = 0; i < nodes_arr.size(); i++) {
			String node_id = nodes_arr.getJSONObject(i).getString("node_id");
			Insert ins3 = new Insert("hrm_org_employee");
			ins3.set("id", ToolUtil.getUUID());
			ins3.set("node_id", node_id);
			ins3.set("deleted", "N");
			ins3.set("empl_id", empl_id);
			exeSqls.add(ins3);
		}
		if (user_rs.isSuccess()) {
			db.executeSQLList(exeSqls);
		} else {
			return user_rs;
		}
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 根据empl_id删除员工
	 */
	public R delEmployee(String empl_id) {
		String user_id = userService.getUserIdFromEmpl(empl_id);
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE("用户ID不存在");
		}
		return userService.deleteUser(user_id);
	}

	/**
	 * @Description: 根据empl_id更新员工
	 */
	public R updateEmployee(TypedHashMap<String, Object> ps) {
		ArrayList<SQL> exeSqls = new ArrayList<SQL>();
		String user_id = ps.getString("user_id");
		String empl_id = ps.getString("empl_id");
		if (ToolUtil.isEmpty(empl_id)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		// 判断是否需要重置user_id,因为updateUser是根据user_id修改数据
		if (ToolUtil.isEmpty(user_id)) {
			user_id = userService.getUserIdFromEmpl(empl_id);
		}
		ps.put("user_id", user_id);
		/***********************************
		 * 组织内用户插入的判断
		 **************************************/
		String nodes = ps.getString("nodes");
		if (ToolUtil.isEmpty(nodes)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray nodes_arr = (JSONArray) JSONArray.parse(nodes);
		String emplpartCtl = ifEmplCanMultiPart();
		if (emplpartCtl.equals("Y")) {
			if (nodes_arr.size() > 1) {
				return R.FAILURE("必须属于一个组织,不可多选");
			}
		}

		Delete dls=new Delete();
		dls.from("hrm_org_employee");
		dls.where().and("empl_id=?", empl_id);
		exeSqls.add(dls);
		for (int i = 0; i < nodes_arr.size(); i++) {
			String node_id = nodes_arr.getJSONObject(i).getString("node_id");
			Insert ins3 = new Insert("hrm_org_employee");
			ins3.set("id", ToolUtil.getUUID());
			ins3.set("node_id", node_id);
			ins3.set("deleted", "N");
			ins3.set("empl_id", empl_id);
			exeSqls.add(ins3);
		}
		/*********************************** 执行 **************************************/
		R user_rs = userService.updateUser(ps, UserService.USER_TYPE_EMPL);
		if (user_rs.isSuccess()) {
			db.executeSQLList(exeSqls);
		} else {
			return user_rs;
		}
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 根据组织ID查找员工
	 */
	public R queryEmplByOrg(String node_id) {
		if (ToolUtil.isEmpty(node_id)) {
			return R.FAILURE("无节点");
		}
		String sql = "select c.* from hrm_org_employee a,sys_user_info c where a.empl_id=c.empl_id and c.user_type= ? and a.node_id=?";
		RcdSet rs = db.query(sql, UserService.USER_TYPE_EMPL, node_id);
		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 查询员工
	 */
	public R queryEmplList(TypedHashMap<String, Object> ps) {
		String node_id = ps.getString("node_id");
		String name = ps.getString("name");
		String bsql = "";
		if (node_id != null && (!node_id.equals("-1"))) {
			// 选择需要的节点
			Rcd routev = db.uniqueRecord("select route from hrm_org_part where node_id=?", node_id);
			if (routev == null) {
				R.FAILURE("该节点不存在");
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
		return R.SUCCESS_OPER(db.query(bsql).toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 根据empl_id查找员工
	 */
	public R queryEmplById(String empl_id) {
		JSONObject res = new JSONObject();
		Rcd info = db.uniqueRecord("select * from sys_user_info where deleted='N' and empl_id=?", empl_id);
		if (ToolUtil.isEmpty(info)) {
			return R.FAILURE_NODATA();
		}
		// 获取组织信息
		res = ConvertUtil.OtherJSONObjectToFastJSONObject(info.toJsonObject());
		res.put("PARTS", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(
				"select a.*,b.node_name from hrm_org_employee a,hrm_org_part b where a.node_id=b.node_id and empl_id=?",
				empl_id).toJsonArrayWithJsonObject()));
		return R.SUCCESS("获取成功", res);
	}

	/**
	 * @Description: 判断用户是否可以存在多个组织中,默认返回N
	 *               sys_empl_org_num_ctl:N(可以多个组织),Y(只能属于一个组织)
	 */
	public String ifEmplCanMultiPart() {
		R emplpartRes = paramsService.queryParamsByIdWithExist("sys_empl_org_num_ctl",
				ParamsService.TYPE_SYSINTER, "N");
		return ToolUtil.parseYNValueDefN(emplpartRes.getDataToJSONObject().getString("value"));
	}
}
