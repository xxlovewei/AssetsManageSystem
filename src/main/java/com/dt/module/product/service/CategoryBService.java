package com.dt.module.product.service;

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
 * @date: 2017年8月9日 上午11:15:30
 * @Description: TODO
 */
@Service
public class CategoryBService extends BaseService {
	/**
	 * @Description:新增加节点,类目下已经有品类后,则不能在添加层级，原则是:品类是类目的最后一级
	 */
	public static String TYPE_ROOT = "root";
	public static String TYPE_NODE = "node";
	public static String TYPE_CATEGORY = "category";
	public static String ACTION_CAT = "cat";
	public static String ACTION_NODE = "node";

	public ResData addCategoryB(TypedHashMap<String, Object> ps) {
		String nodetype = TYPE_NODE;
		String id = ps.getString("ID");
		String action = ps.getString("ACTION");
		String text = ps.getString("TEXT", "新节点");
		if (ToolUtil.isOneEmpty(id, action)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		// 判断操作类型
		String is_cat = "";
		if (action.equals(ACTION_CAT)) {
			is_cat = "Y";
			nodetype = TYPE_CATEGORY;
		} else if (action.equals(ACTION_NODE)) {
			is_cat = "N";
			nodetype = TYPE_NODE;
		} else {
			return ResData.FAILURE("无法判定当前动作");
		}
		// 获取序列号
		String next_id = getNextId();
		if (ToolUtil.isEmpty(next_id)) {
			return ResData.FAILURE("获取序列号失败");
		}
		/***************************** 前期检查结束 ***********************************/
		Insert ins = new Insert("product_category");
		ins.set("id", next_id);
		ins.set("parent_id", id);
		ins.set("is_deleted", "N");
		ins.set("is_cat", is_cat);
		ins.set("text", text);
		// 设置route
		if (id.equals("0")) {
			if (is_cat.equals("Y")) {
				return ResData.FAILURE("根节点不允许插入品类");
			}
			ins.set("route", next_id);
		} else {
			// 获取父节点信息
			Rcd brs = db.uniqueRecord("select * from product_category where is_deleted='N' and id=?", id);
			if (ToolUtil.isEmpty(brs)) {
				return ResData.FAILURE("节点不存在");
			}
			//如果父节点是品类,则不允许派生
			if(brs.getString("is_cat").equals("Y")){
				return ResData.FAILURE("本节点为品类,不允许创建子节点");
			}
			ins.set("route",  brs.getString("route") + "-" + next_id);
		}
		db.execute(ins);
		JSONObject e = new JSONObject();
		e.put("id", next_id);
		e.put("type", nodetype);
		return ResData.SUCCESS_OPER(e);
	}
	/**
	 * @Description: 删除节点
	 */
	public ResData deleteCategoryB(String id) {
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Rcd rs = db.uniqueRecord("select count(1) value from product_category where is_deleted='N' and parent_id=?",
				id);
		if (rs.getInteger("value") > 0) {
			return ResData.FAILURE("先删除子节点");
		} else {
			Update ups = new Update("PRODUCT_category");
			ups.set("is_deleted", "Y");
			ups.where().and("id=?", id);
			db.execute(ups);
		}
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:更新节点
	 */
	public void updateCategoryB() {
	}
	/**
	 * @Description:重命名节点
	 */
	public ResData renameCategoryB(String id, String text) {
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (id.equals("0")) {
			return ResData.FAILURE("根节点不允许修改");
		}
		Update ups = new Update("product_category");
		ups.setIf("text", text);
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:按照前端树查询节点数据
	 */
	public void queryCategoryBById() {
	}
	// 只支持4级商品类目
	// 后台的子节点为品类,挂载属性模版。
	// 属性:基本属性,销售属性,描述属性。
	// 发布产品的时候必须选择一条销售属性,否则无法发布产品
	// 属性:
	// 公共属性放到产品表中去，
	// 基本属性，销售属性在品类模版中
	// 只能选取销售属性中的部分组成sku组合,且必须有一条sku，否则无法发布产品
	public JSONArray queryCategoryBTreeList() {
		JSONArray res = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", 0);
		root.put("parent", "#");
		root.put("text", "商品类目");
		root.put("is_cat", "N");
		root.put("type", "root");
		res.add(root);
		RcdSet rs = db.query("select * from product_category where is_deleted='N'");
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
	/**
	 * @Description:获取下一个节点Id
	 */
	public String getNextId() {
		return db
				.uniqueRecord("select case when max(id) is null then 10 else max(id)+1 end value from product_category")
				.getString("value");
	}
}
