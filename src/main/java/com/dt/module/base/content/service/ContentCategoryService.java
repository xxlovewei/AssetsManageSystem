package com.dt.module.base.content.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.Rcd;
import com.dt.dao.RcdSet;
import com.dt.dao.sql.Insert;
import com.dt.dao.sql.Update;
import com.dt.dao.util.TypedHashMap;
import com.dt.tool.util.ConvertUtil;
import com.dt.tool.util.ToolUtil;

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
		if (db.uniqueRecord("select count(1) value from ct_category where deleted='N' and parent_id=?", id)
				.getInteger("value") > 0) {
			return ResData.FAILURE("请先删除子节点");
		}
		Update me = new Update("ct_category");
		me.set("deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 根据ID显示第一层的数据
	 */
	public ResData queryCategoryFirstFloor(String rootId, String isAction) {
		String sql = "select * from ct_category where root=? and deleted='N' and node_level=1";
		if (ToolUtil.isNotEmpty(isAction)) {
			sql = sql + " and isaction='" + ToolUtil.parseYNValueDefY(isAction) + "'";
		}
		sql = sql + " order by od";
		return ResData.SUCCESS_OPER(db.query(sql, rootId).toJsonArrayWithJsonObject());
	}
	
	/**
	 * @Description: 显示子节点数据
	 */
	public ResData queryCategoryChildren(String parentId, String isAction) {
		String sql = "select * from ct_category where parent_id=? and deleted='N' ";
		if (ToolUtil.isNotEmpty(isAction)) {
			sql = sql + " and isaction='" + ToolUtil.parseYNValueDefY(isAction) + "'";
		}
		sql = sql + " order by od";
		return ResData.SUCCESS_OPER(db.query(sql, parentId).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 后端angular显示内容
	 */
	public ResData queryCategoryTreeList(String root_id) {
		if (ToolUtil.isEmpty(root_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray res = new JSONArray();
		String rootsql = "select * from ct_category_root where id=? and deleted='N'";
		Rcd root_rs = db.uniqueRecord(rootsql, root_id);
		JSONObject root = new JSONObject();
		root.put("id", root_id);
		root.put("parent", "#");
		root.put("text", root_rs.getString("name"));
		root.put("type", "root");
		res.add(root);
		RcdSet rs = db.query("select * from ct_category where root=? and deleted='N'", root_id);
		JSONObject e = new JSONObject();
		for (int i = 0; i < rs.size(); i++) {
			e = new JSONObject();
			e.put("id", rs.getRcd(i).getString("id"));
			e.put("text", rs.getRcd(i).getString("name"));
			e.put("type", "node");
			e.put("parent", rs.getRcd(i).getString("parent_id"));
			res.add(e);
		}
		return ResData.SUCCESS_OPER(res);
	}
	/**
	 * @Description:查询某个节点
	 */
	public ResData queryCategoryById(String id) {
		String sql = "select a.*,b.name rootname from ct_category a,ct_category_root b where a.root=b.id and a.id=?";
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
		return ResData.SUCCESS_OPER(
				db.query("select * from ct_category where deleted='N' and root=?", root).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:获取节点下一个序列号
	 */
	public String getNextNodeId() {
		return db.uniqueRecord("select case when max(id) is null then 50 else max(id)+1 end value from ct_category").getString("value");
	}
	/**
	 * @Description:更新节点数据
	 */
	public ResData updateCategory(TypedHashMap<String, Object> ps) {
		String id = ps.getString("id");
		String name = ps.getString("name", "idle");
		Update ups = new Update("ct_category");
		ups.setIf("name", name);
		ups.setIf("mpic", ps.getString("mpic"));
		ups.setIf("mark", ps.getString("mark"));
		ups.setIf("od", ps.getString("od"));
		ups.setIf("isaction", ps.getString("isaction"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:插入节点
	 */
	public ResData addCategory(TypedHashMap<String, Object> ps) {
		String old_id = ps.getString("old_id");
		String old_node_type = ps.getString("old_node_type");
		String name = ps.getString("name");
		if (ToolUtil.isOneEmpty(old_id, old_node_type, name)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		String id = this.getNextNodeId();
		Insert me = new Insert("ct_category");
		if (old_node_type.equals("root")) {
			// 树的根节点添加第一个节点
			me.set("root", old_id);
			me.set("route", id);
			me.set("parent_id", old_id);
			me.set("node_level", "1");
		} else {
			// 树的添加节点
			ResData oldNode = queryCategoryById(old_id);
			if (!oldNode.isSuccess()) {
				return oldNode;
			}
			JSONObject oldData = JSONObject.parseObject(oldNode.getData().toString());
			me.set("root", oldData.getString("root"));
			me.set("route", oldData.getString("route") + "-" + id);
			me.set("parent_id", old_id);
			me.set("node_level", oldData.getIntValue("node_level") + 1);
		}
		me.set("id", id);
		me.set("deleted", "N");
		me.setIf("mark", ps.getString("mark"));
		me.setIf("mpic", ps.getString("mpic"));
		me.setIf("name", ps.getString("name", "idle"));
		me.setIf("isaction", ps.getString("isaction"));
		me.setIf("od", ConvertUtil.toInt(ps.getString("od"), 99));
		db.execute(me);
		return queryCategoryById(id);
	}
}
