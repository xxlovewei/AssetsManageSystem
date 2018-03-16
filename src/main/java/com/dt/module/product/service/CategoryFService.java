package com.dt.module.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:15:53
 * @Description: TODO
 */
@Service
public class CategoryFService extends BaseService {
	// 出发点:聚合后台类目数据，精准化营销,导购,导航等
	// 后台类目的子节点为虚拟品类，不挂载具体属性模版，从后台属性模版中继承，
	// 前台类目由后台叶子类目+属性组成(并且只能添加是枚举类型的属性)。
	@Autowired
	private CategoryFRootService categoryFRootService;

	public R addCategoryF(TypedHashMap<String, Object> ps) {
		// 类目下已经有品类后,则不能在添加层级，原则是:品类是类目的最后一级
		// 节点类型,root,node,category
		String node_type = "node";
		String id = ps.getString("id");
		String action = ps.getString("action");// cate,node
		String text = ps.getString("text");
		String is_cat = "N";
		if (action.equals("cat")) {
			is_cat = "Y";
			node_type = "category";
		} else if (action.equals("node")) {
			is_cat = "N";
			node_type = "node";
		} else {
			return R.FAILURE("选择正确的操作");
		}
		Insert ins = new Insert("product_cat_user");
		int next_id = categoryFRootService.getNextUserCatId();
		ins.set("id", next_id);
		ins.set("is_deleted", "N");
		ins.setIf("text", text);
		ins.set("parent_id", id);
		ins.set("is_cat", is_cat);
		String curInfosql = "";
		curInfosql = curInfosql
				+ " select 'node' type,route,is_cat,root_id from product_cat_user where id=? union all ";
		curInfosql = curInfosql
				+ " select 'root' type ,'' route,'' is_cat,0 root_id from product_cat_user_root where id=? ";
		Rcd cur_rs = db.uniqueRecord(curInfosql, id, id);
		if (ToolUtil.isEmpty(cur_rs)) {
			return R.FAILURE_OPER();
		}
		if (cur_rs.getString("type").equals("root")) {
			// 本节点为根节点
			ins.set("root_id", id);
			ins.set("route", next_id);
		} else {
			// 本节点不是根节点
			ins.set("root_id", cur_rs.getString("root_id"));
			ins.set("route", cur_rs.getString("route") + "-" + next_id);
			// 如果当前是层级没有现在，如果是当前是品类，则无法在创建
			String cur_is_cat = cur_rs.getString("is_cat");
			if (cur_is_cat.equals("Y")) {
				return R.FAILURE("当前层级下不允许在创建子节点");
			}
		}
		db.execute(ins);
		JSONObject e = new JSONObject();
		e.put("id", next_id);
		e.put("type", node_type);
		return R.SUCCESS("操作成功", e);
	}

	public R updateCategoryF() {
		return null;
	}

	public R renameCategoryF(String id, String text) {
		if (ToolUtil.isOneEmpty(id, text)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		String curInfosql = "";
		curInfosql = curInfosql
				+ " select 'node' type,route,is_cat,root_id from product_cat_user where id=? union all ";
		curInfosql = curInfosql
				+ " select 'root' type ,'' route,'' is_cat,0 root_id from product_cat_user_root where id=? ";
		Rcd cur_rs = db.uniqueRecord(curInfosql, id, id);
		if (ToolUtil.isEmpty(cur_rs)) {
			return R.FAILURE_OPER();
		}
		if (cur_rs.getString("type").equals("root")) {
			// 本节点为根节点
			return R.FAILURE("根节点不允许修改");
		}
		Update ups = new Update("product_cat_user");
		ups.setIf("text", text);
		ups.where().and("id=?", id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}

	public R delCategoryF() {
		return null;
	}

	public JSONArray queryCategoryFTreeList(String root_id) {

		JSONArray res = new JSONArray();
		String rootsql = "select * from product_cat_user_root where id=?  and is_deleted='N'";
		Rcd root_rs = db.uniqueRecord(rootsql, root_id);
		JSONObject root = new JSONObject();
		root.put("id", root_id);
		root.put("parent", "#");
		root.put("text", root_rs.getString("text"));
		root.put("is_cat", "N");
		root.put("type", "root");
		res.add(root);
		RcdSet rs = db.query("select * from product_cat_user where root_id=? and is_deleted='N'", root_id);
		JSONObject e = new JSONObject();
		for (int i = 0; i < rs.size(); i++) {
			e = new JSONObject();
			e.put("id", rs.getRcd(i).getString("id"));
			e.put("text", rs.getRcd(i).getString("text"));
			e.put("is_cat", rs.getRcd(i).getString("is_cat"));
			if (rs.getRcd(i).getString("is_cat").equals("Y")) {
				e.put("type", "category");
			} else {
				e.put("type", "node");
			}
			e.put("parent", rs.getRcd(i).getString("parent_id"));
			res.add(e);
		}
		return res;
	}

	public R queryCategoryFById() {
		return null;
	}
}
