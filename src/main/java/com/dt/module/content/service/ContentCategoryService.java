package com.dt.module.content.service;

import org.springframework.stereotype.Service;

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
 * @date: 2017年8月11日 上午11:30:01
 * @Description: TODO
 */
@Service
public class ContentCategoryService extends BaseService {
	/**
	 * @Description: 删除节点
	 */
	public ResData deleteCategory(String id) {
		if (db.uniqueRecord("select count(1) value from CT_CATEGORY where deleted='N' and parent_id=?", id)
				.getInteger("value") > 0) {
			return ResData.FAILURE("请先删除子节点");
		}
		Update me = new Update("CT_CATEGORY");
		me.set("deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 根据ID显示第一层的数据
	 */
	public ResData queryCategoryFirstFloor(String rootId, String isAction) {
		String sql = "select * from CT_CATEGORY where root=? and deleted='N' and NODE_LEVEL=1";
		if (ToolUtil.isNotEmpty(isAction)) {
			sql = sql + " and ISACTION='" + ToolUtil.parseYNValueDefY(isAction) + "'";
		}
		sql = sql + " order by od";
		return ResData.SUCCESS(db.query(sql, rootId).toJsonArrayWithJsonObject());
	}
	
	/**
	 * @Description: 显示子节点数据
	 */
	public ResData queryCategoryChildren(String parentId, String isAction) {
		String sql = "select * from CT_CATEGORY where parent_id=? and deleted='N' ";
		if (ToolUtil.isNotEmpty(isAction)) {
			sql = sql + " and ISACTION='" + ToolUtil.parseYNValueDefY(isAction) + "'";
		}
		sql = sql + " order by od";
		System.out.println(sql);
		return ResData.SUCCESS(db.query(sql, parentId).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 后端angular显示内容
	 */
	public ResData queryCategoryTreeList(String root_id) {
		if (ToolUtil.isEmpty(root_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray res = new JSONArray();
		String rootsql = "select * from CT_CATEGORY_ROOT where ID=?  and deleted='N'";
		Rcd root_rs = db.uniqueRecord(rootsql, root_id);
		JSONObject root = new JSONObject();
		root.put("id", root_id);
		root.put("parent", "#");
		root.put("text", root_rs.getString("name"));
		root.put("type", "root");
		res.add(root);
		RcdSet rs = db.query("select * from CT_CATEGORY where root=? and deleted='N'", root_id);
		JSONObject e = new JSONObject();
		for (int i = 0; i < rs.size(); i++) {
			e = new JSONObject();
			e.put("id", rs.getRcd(i).getString("id"));
			e.put("text", rs.getRcd(i).getString("name"));
			e.put("type", "node");
			e.put("parent", rs.getRcd(i).getString("parent_id"));
			res.add(e);
		}
		return ResData.SUCCESS(res);
	}
	/**
	 * @Description:查询某个节点
	 */
	public ResData queryCategoryById(String id) {
		String sql = "select a.*,b.NAME ROOTNAME from  CT_CATEGORY a, CT_CATEGORY_ROOT b where a.ROOT=b.ID and a.id=?";
		Rcd rs = db.uniqueRecord(sql, id);
		if (rs == null) {
			return ResData.FAILURE_NODATA();
		}
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}
	/**
	 * @Description:查询所有数据
	 */
	public ResData queryCategory(String root) {
		return ResData.SUCCESS(
				db.query("select * from CT_CATEGORY where deleted='N' and root=?", root).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:获取节点下一个序列号
	 */
	public String getNextNodeId() {
		return db.uniqueRecord("select case when max(id) is null then 50 else max(id)+1 end  value from CT_CATEGORY").getString("value");
	}
	/**
	 * @Description:更新节点数据
	 */
	public ResData updateCategory(TypedHashMap<String, Object> ps) {
		String id = ps.getString("ID");
		String name = ps.getString("NAME", "idle");
		Update ups = new Update("CT_CATEGORY");
		ups.setIf("NAME", name);
		ups.setIf("MPIC", ps.getString("MPIC"));
		ups.setIf("MARK", ps.getString("MARK"));
		ups.setIf("OD", ps.getString("OD"));
		ups.setIf("ISACTION", ps.getString("ISACTION"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:插入节点
	 */
	// ID NUMBER(10) not null
	// ROOT VARCHAR2(50) not null,
	// NAME VARCHAR2(200),
	// MPIC VARCHAR2(50),
	// PARENT_ID NUMBER(10),
	// ROUTE VARCHAR2(500),
	// MARK VARCHAR2(500),
	// NODE_LEVEL NUMBER(2),
	// OD NUMBER(2),
	// ISACTION CHAR,
	// DELETED CHAR
	public ResData addCategory(TypedHashMap<String, Object> ps) {
		String old_id = ps.getString("OLD_ID");
		String old_node_type = ps.getString("OLD_NODE_TYPE");
		String name = ps.getString("NAME");
		if (ToolUtil.isOneEmpty(old_id, old_node_type, name)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		String id = this.getNextNodeId();
		Insert me = new Insert("CT_CATEGORY");
		if (old_node_type.equals("root")) {
			// 树的根节点添加第一个节点
			me.set("ROOT", old_id);
			me.set("ROUTE", id);
			me.set("PARENT_ID", old_id);
			me.set("NODE_LEVEL", "1");
		} else {
			// 树的添加节点
			ResData oldNode = queryCategoryById(old_id);
			if (!oldNode.isSuccess()) {
				return oldNode;
			}
			JSONObject oldData = JSONObject.parseObject(oldNode.getData().toString());
			me.set("ROOT", oldData.getString("ROOT"));
			me.set("ROUTE", oldData.getString("ROUTE") + "-" + id);
			me.set("PARENT_ID", old_id);
			me.set("NODE_LEVEL", oldData.getIntValue("NODE_LEVEL") + 1);
		}
		me.set("ID", id);
		me.set("DELETED", "N");
		me.setIf("MARK", ps.getString("MARK"));
		me.setIf("MPIC", ps.getString("MPIC"));
		me.setIf("NAME", ps.getString("NAME", "idle"));
		me.setIf("ISACTION", ps.getString("ISACTION"));
		me.setIf("OD", ConvertUtil.toInt(ps.getString("OD"), 99));
		db.execute(me);
		return queryCategoryById(id);
	}
}
