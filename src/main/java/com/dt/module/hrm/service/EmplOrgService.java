package com.dt.module.hrm.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:11:32
 * @Description: TODO
 */
@Service
public class EmplOrgService extends BaseService {
	// 排除根节点，支持到7层节点树层级显示
	String orgsql = "select " + "decode(levels, " + "1,level2_name, " + "2,level2_name||'->'||level3_name, "
			+ "3,level2_name||'->'||level3_name||'->'||level4_name, "
			+ "4,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name, "
			+ "5,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name, "
			+ "6,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name, "
			+ "7,level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name||'->'||level8_name, "
			+ "'层级太多' " + ") routename, " + "outtable.node_id ,outtable.levels from ( " + "select "
			+ "(select org_name  from hrm_org_info i where i.org_id=outer.level1) level1_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level2) level2_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level3) level3_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level4) level4_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level5) level5_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level6) level6_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level7) level7_name, "
			+ "(select node_name  from hrm_org_part i where i.node_id=outer.level8) level8_name, " + "outer.* "
			+ "from " + "( " + "select LENGTH(route) - LENGTH(REPLACE(route,'-','')) levels , "
			+ "decode(instr(route,'-'), 0,route,substr(route,1,instr(route,'-') -1)) level1 , "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', "
			+ "1 , substr(route,instr(route,'-',1,1)+1, LENGTH(route)-instr(route,'-',1,1)), "
			+ "substr(route,instr(route,'-',1,1)+1 ,instr(route,'-',1,2) - instr(route,'-',1,1) -1)) level2, "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
			+ "2 , substr(route,instr(route,'-',1,2)+1, LENGTH(route)-instr(route,'-',1,2)), "
			+ "substr(route,instr(route,'-',1,2)+1 ,instr(route,'-',1,3) - instr(route,'-',1,2) -1)) level3, "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
			+ "3 , substr(route,instr(route,'-',1,3)+1, LENGTH(route)-instr(route,'-',1,3)), "
			+ "substr(route,instr(route,'-',1,3)+1 ,instr(route,'-',1,4) - instr(route,'-',1,3) -1)) level4, "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
			+ "3 , '-1', " + "4 , substr(route,instr(route,'-',1,4)+1, LENGTH(route)-instr(route,'-',1,4)), "
			+ "substr(route,instr(route,'-',1,4)+1 ,instr(route,'-',1,5) - instr(route,'-',1,4) -1)) level5, "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
			+ "3 , '-1', " + "4 , '-1', "
			+ "5 , substr(route,instr(route,'-',1,5)+1, LENGTH(route)-instr(route,'-',1,5)), "
			+ "substr(route,instr(route,'-',1,5)+1 ,instr(route,'-',1,6) - instr(route,'-',1,5) -1)) level6, "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
			+ "3 , '-1', " + "4 , '-1', " + "5 , '-1', "
			+ "6 , substr(route,instr(route,'-',1,6)+1, LENGTH(route)-instr(route,'-',1,6)), "
			+ "substr(route,instr(route,'-',1,6)+1 ,instr(route,'-',1,7) - instr(route,'-',1,6) -1)) level7, "
			+ "decode(LENGTH(route) - LENGTH(REPLACE(route,'-','')), " + "0 , '-1', " + "1 , '-1', " + "2 , '-1', "
			+ "3 , '-1', " + "4 , '-1', " + "5 , '-1', " + "6 , '-1', "
			+ "7 , substr(route,instr(route,'-',1,7)+1, LENGTH(route)-instr(route,'-',1,7)), "
			+ "substr(route,instr(route,'-',1,7)+1 ,instr(route,'-',1,8) - instr(route,'-',1,7) -1)) level8, "
			+ "route,node_id " + "from hrm_org_part t where 1=1 <#IF#>) outer) outtable order by route ";
 

	public void Test(){
		
	}
	/**
	 * @Description:查询所有组织信息
	 */
	public ResData queryEmplOrg() {
		return ResData.SUCCESS_OPER(db.query("select * from hrm_org_info ").toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:添加组织
	 */
	public ResData addEmplOrg(TypedHashMap<String, Object> ps) {
		 
		String id = ps.getString("NODE_ID");
		String org_id = ps.getString("ORG_ID");
		String parent_id = ps.getString("PARENT_ID");
		
		if (ToolUtil.isOneEmpty(parent_id,org_id)) {
			return ResData.FAILURE("无父节点或无组织节点");
		}
		
		// 从hrm_org_part,和hrm_org_info表中找到当前route,route的第一个节点为根节点,id 是hrm_org_part最大
		String tsql = " select t.* ,(select case when max(node_id) is null then 50 else max(node_id)+1 end  value from hrm_org_part) seq  ";
		tsql = tsql + " from (select org_id node_id,org_id||'' route from hrm_org_info ";
		tsql = tsql + " union all ";
		tsql = tsql + " select node_id node_id,route route from hrm_org_part)t  ";
		tsql = tsql + " where node_id=? ";
		Rcd rcd = db.uniqueRecord(tsql, parent_id);
		if (rcd == null) {
			return ResData.FAILURE("发生系统错误,请开发人员协助");
		}
		id = rcd.getString("seq");
		String route = rcd.getString("route");
		if (ToolUtil.isEmpty(route)) {
			return ResData.FAILURE("发生系统错误,上级路径不存在,请开发人员协助");
		}
		Insert ins = new Insert("hrm_org_part");
		ins.set("org_id", org_id);
		ins.set("route", route + "-" + id);
		ins.set("node_id", id);
		ins.set("parent_id", parent_id);
		ins.setIf("node_name", ps.getString("NODE_NAME",""));
		db.execute(ins);
		JSONObject ro = new JSONObject();
		ro.put("ID", id);
		return ResData.SUCCESS_OPER(ro);
	}
	/**
	 * @Description:修改组织
	 */
	public ResData updateEmplOrg(TypedHashMap<String, Object> ps) {
		String id = ps.getString("NODE_ID");
		Update ups = new Update("hrm_org_part");
		ups.setIf("node_name", ps.getString("NODE_NAME"));
		ups.where().and("node_id=?", id);
		db.execute(ups);
		JSONObject ro = new JSONObject();
		ro.put("ID", id);
		return ResData.SUCCESS_OPER(ro);
		 
	}
	/**
	 * @Description:删除组织
	 */
	public ResData deleteEmplOrg(String node_id) {
		if (ToolUtil.isEmpty(node_id)) {
			return ResData.FAILURE("无节点,请选择节点");
		}
		// 检查是否有下一级节点
		if (db.uniqueRecord("select count(1) v from hrm_org_part where parent_id=? ", node_id).getInteger("v") > 0) {
			return ResData.FAILURE("请先删除子节点");
		}
		// 检查节点是否有人员信息
		if (db.uniqueRecord("select count(1) v from hrm_org_employee where node_id=? ", node_id).getInteger("v") > 0) {
			return ResData.FAILURE("请先删除人员信息");
		}
		// 删除节点
		db.execute("delete from hrm_org_part where node_id=?", node_id);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:查询某个组织信息
	 */
	public ResData queryEmplOrgById(String node_id) {
		if (ToolUtil.isEmpty(node_id)) {
			return ResData.FAILURE("无父节点");
		}
		String sql = " select * from hrm_org_part where node_id=?";
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
		String orgsql2 = orgsql.replaceAll("<#IF#>", "");
		return ResData.SUCCESS_OPER(db.query(orgsql2).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:树行显示组织信息
	 */
	public ResData queryEmplOrgNodeTree(String org_id) {
		// 公司,部门,组
		if (ToolUtil.isEmpty(org_id)) {
			return ResData.FAILURE("根节点不存在");
		}
		String sql = " select * from hrm_org_part where org_id='" + org_id + "' ";
		RcdSet rs = db.query(sql);
		JSONArray res = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", org_id);
		root.put("parent", "#");
		root.put("text", "组织树");
		root.put("type", "root");
		res.add(root);
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
