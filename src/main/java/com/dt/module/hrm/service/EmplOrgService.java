package com.dt.module.hrm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:11:32
 * @Description: TODO
 */
@Service
public class EmplOrgService extends BaseService {
	private String LEVEL_SPLIT = "/";
	private static Logger _log = LoggerFactory.getLogger(EmplOrgService.class);
//	// 排除根节点，支持到7层节点树层级显示
//	String orgsql = "select " + "decode(levels, " + "1,level2_name, " + "2,level2_name||'->'||level3_name, "
//			+ "3,level2_name||'->'||level3_name||'->'||level4_name, "
//			+ "4,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name, "
//			+ "5,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name, "
//			+ "6,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name, "
//			+ "7,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name||'->'||level8_name, "
//			+ "'层级太多' " + ") routename, " + "outtable.node_id ,outtable.levels from ( " + "select "
//			+ "(select org_name  from hrm_org_info i where i.org_id=outer.level1) level1_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level2) level2_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level3) level3_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level4) level4_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level5) level5_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level6) level6_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level7) level7_name, "
//			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level8) level8_name, " + "outer.* "
//			+ "from " + "( " + "select LENGTH(route) - LENGTH(REPLACE(route,'-','')) levels , "
//			+ "decode(instr(route,'-'), 0,route,substr(route,1,instr(route,'-') -1)) level1 , "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', "
//			+ "1 , substr(route,instr(route,'-',1,1)+1, LENGTH(route)-instr(route,'-',1,1)), "
//			+ "substr(route,instr(route,'-',1,1)+1 ,instr(route,'-',1,2) - instr(route,'-',1,1) -1)) level2, "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
//			+ "2 , substr(route,instr(route,'-',1,2)+1, LENGTH(route)-instr(route,'-',1,2)), "
//			+ "substr(route,instr(route,'-',1,2)+1 ,instr(route,'-',1,3) - instr(route,'-',1,2) -1)) level3, "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
//			+ "3 , substr(route,instr(route,'-',1,3)+1, LENGTH(route)-instr(route,'-',1,3)), "
//			+ "substr(route,instr(route,'-',1,3)+1 ,instr(route,'-',1,4) - instr(route,'-',1,3) -1)) level4, "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
//			+ "3 , '-1', " + "4 , substr(route,instr(route,'-',1,4)+1, LENGTH(route)-instr(route,'-',1,4)), "
//			+ "substr(route,instr(route,'-',1,4)+1 ,instr(route,'-',1,5) - instr(route,'-',1,4) -1)) level5, "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
//			+ "3 , '-1', " + "4 , '-1', "
//			+ "5 , substr(route,instr(route,'-',1,5)+1, LENGTH(route)-instr(route,'-',1,5)), "
//			+ "substr(route,instr(route,'-',1,5)+1 ,instr(route,'-',1,6) - instr(route,'-',1,5) -1)) level6, "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
//			+ "3 , '-1', " + "4 , '-1', " + "5 , '-1', "
//			+ "6 , substr(route,instr(route,'-',1,6)+1, LENGTH(route)-instr(route,'-',1,6)), "
//			+ "substr(route,instr(route,'-',1,6)+1 ,instr(route,'-',1,7) - instr(route,'-',1,6) -1)) level7, "
//			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
//			+ "3 , '-1', " + "4 , '-1', " + "5 , '-1', " + "6 , '-1', "
//			+ "7 , substr(route,instr(route,'-',1,7)+1, LENGTH(route)-instr(route,'-',1,7)), "
//			+ "substr(route,instr(route,'-',1,7)+1 ,instr(route,'-',1,8) - instr(route,'-',1,7) -1)) level8, "
//			+ "route,node_id " + "from hrm_org_part t where 1=1 <#IF#>) outer) outtable order by route ";

	/**
	 * @Description:查询所有组织信息
	 */
	public ResData queryEmplOrg() {
		return ResData.SUCCESS_OPER(db.query("select * from hrm_org_info").toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:添加组织
	 */
	@Transactional
	public ResData addEmplOrg(TypedHashMap<String, Object> ps) {
		String cur_node_id = "";
		String org_id = ps.getString("ORG_ID");
		String node_name = ps.getString("NODE_NAME");
		String parent_id = ps.getString("PARENT_ID");
		_log.info("node_id:" + cur_node_id);
		_log.info("org_id:" + org_id);
		_log.info("parent_id:" + parent_id);
		if (ToolUtil.isOneEmpty(parent_id, org_id, node_name)) {
			return ResData.FAILURE("无父节点或无组织节点");
		}
		// 从hrm_org_part,和hrm_org_info找到最大node_id,全局唯一
		Rcd idrs = db.uniqueRecord(
				"select case when max(node_id) is null then 50 else max(node_id)+1 end value from (select node_id node_id from hrm_org_part union all select org_id node_id from hrm_org_info)");
		if (ToolUtil.isEmpty(idrs)) {
			return ResData.FAILURE("发生系统错误,请开发人员协助");
		} else {
			cur_node_id = idrs.getString("value");
		}
		Rcd verifyrs = db.uniqueRecord("select * from hrm_org_part where node_id=?", parent_id);
		Insert ins = new Insert("hrm_org_part");
		ins.set("org_id", org_id);
		// 判断是否是第一个节点
		if (ToolUtil.isEmpty(verifyrs) || org_id.equals(parent_id)) {
			ins.set("route", cur_node_id);
		} else {
			ins.set("route", verifyrs.getString("route") + "-" + cur_node_id);
		}
		ins.set("node_id", cur_node_id);
		ins.set("parent_id", parent_id);
		ins.setIf("node_name", node_name);
		db.execute(ins);
		updateRouteName(cur_node_id, node_name);
		JSONObject ro = new JSONObject();
		ro.put("ID", cur_node_id);
		return ResData.SUCCESS_OPER(ro);
	}
	/**
	 * @Description:修改组织
	 */
	@Transactional
	public ResData updateEmplOrg(TypedHashMap<String, Object> ps) {
		String node_id = ps.getString("NODE_ID");
		String node_name = ps.getString("NODE_NAME");
		if (ToolUtil.isEmpty(node_name)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("hrm_org_part");
		ups.setIf("node_name", node_name);
		ups.where().and("node_id=?", node_id);
		db.execute(ups);
		updateRouteName(node_id, node_name);
		JSONObject ro = new JSONObject();
		ro.put("ID", node_id);
		return ResData.SUCCESS_OPER(ro);
	}
	/**
	 * @Description:更新节点路径名称
	 */
	private void updateRouteName(String node_id, String node_name) {
		Rcd rs = db.uniqueRecord("select * from hrm_org_part where node_id=?", node_id);
		// 判断如果一致则不需要更新routename
		if (ToolUtil.isEmpty(rs)) {
			return;
		}
		if (rs.getString("node_name").equals("node_name")) {
			return;
		}
		String ids = rs.getString("route");
		JSONArray arr = ConvertUtil.toJSONArrayFromString(ids, "id", "-");
		String route_name = "";
		for (int i = 0; i < arr.size(); i++) {
			route_name = route_name + LEVEL_SPLIT
					+ db.uniqueRecord("select node_name from hrm_org_part where node_id=?",
							arr.getJSONObject(i).getString("id")).getString("node_name");
		}
		route_name = route_name.replaceFirst(LEVEL_SPLIT, "");
		Update me = new Update("hrm_org_part");
		me.set("route_name", route_name);
		me.where().and("node_id=?", node_id);
		db.execute(me);
		RcdSet rds = db.query("select node_id,node_name from hrm_org_part where parent_id=?", node_id);
		for (int j = 0; j < rds.size(); j++) {
			// 递归调用
			updateRouteName(rds.getRcd(j).getString("node_id"), rds.getRcd(j).getString("node_name"));
		}
	}
	/**
	 * @Description:删除组织
	 */
	@Transactional
	public ResData deleteEmplOrg(String node_id) {
		if (ToolUtil.isEmpty(node_id)) {
			return ResData.FAILURE("无节点,请选择节点");
		}
		// 检查是否有下一级节点
		if (db.uniqueRecord("select count(1) v from hrm_org_part where parent_id=? ", node_id).getInteger("v") > 0) {
			return ResData.FAILURE("请先删除子节点");
		}
		// 检查节点是否有人员信息,如果有人,在判断是否需要删除
		if (db.uniqueRecord("select count(1) v from hrm_org_employee where node_id=? ", node_id).getInteger("v") > 0) {
			if (db.uniqueRecord(
					"select count(1) v from sys_user_info a,hrm_org_employee b where a.empl_id=b.empl_id and a.deleted='N' and b.node_id=?",
					node_id).getInteger("v") > 0) {
				return ResData.FAILURE("请先删除人员信息");
			}
		}
		// 删除节点
		db.execute("delete from hrm_org_part where node_id=?", node_id);
		db.execute("delete from hrm_org_employee where node_id=?", node_id);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:查询某个组织信息
	 */
	public ResData queryEmplOrgById(String node_id) {
		if (ToolUtil.isEmpty(node_id)) {
			return ResData.FAILURE("无父节点");
		}
		String sql = "select * from hrm_org_part where node_id=?";
		Rcd rs = db.uniqueRecord(sql, node_id);
		if (rs == null) {
			return ResData.FAILURE("该节点不存在");
		} else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}
	/**
	 * @Description:横行显示组织信息,类似A->B->C-D
	 */
	public ResData queryEmplOrgLevelList() {
		return ResData
				.SUCCESS_OPER(db.query("select node_id,route_name routename ,route from hrm_org_part order by route")
						.toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:树行显示组织信息
	 */
	public ResData queryEmplOrgNodeTree(String org_id) {
		// 公司,部门,组
		if (ToolUtil.isEmpty(org_id)) {
			return ResData.FAILURE("根节点不存在");
		}
		Rcd rootrs = db.uniqueRecord("select * from hrm_org_info where org_id=?", org_id);
		String rootname = ToolUtil.isEmpty(rootrs.getString("org_name")) ? "组织树" : rootrs.getString("org_name");
		JSONArray res = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", org_id);
		root.put("parent", "#");
		root.put("text", rootname);
		root.put("type", "root");
		res.add(root);
		String sql = "select * from hrm_org_part where org_id='" + org_id + "' ";
		RcdSet rs = db.query(sql);
		JSONObject e = new JSONObject();
		for (int i = 0; i < rs.size(); i++) {
			e = new JSONObject();
			e.put("id", rs.getRcd(i).getString("node_id"));
			e.put("text", rs.getRcd(i).getString("node_name"));
			e.put("parent", rs.getRcd(i).getString("parent_id"));
			res.add(e);
		}
		return ResData.SUCCESS_OPER(res);
	}
}
