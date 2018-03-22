package com.dt.module.product.service;

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
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:15:30
 * @Description: TODO
 */
@Service
public class CategoryBService extends BaseService {
	// 只支持4级商品类目
	// 后台的子节点为品类,挂载属性模版。
	// 属性:基本属性,销售属性,描述属性。
	// 发布产品的时候必须选择一条销售属性,否则无法发布产品
	// 属性:
	// 公共属性放到产品表中去，
	// 基本属性，销售属性在品类模版中
	// 只能选取销售属性中的部分组成sku组合,且必须有一条sku，否则无法发布产品
	/**
	 * @Description:新增加节点,类目下已经有品类后,则不能在添加层级，原则是:品类是类目的最后一级
	 */
	public static String TYPE_ROOT = "root";
	public static String TYPE_NODE = "node";
	public static String TYPE_CATEGORY = "category";
	public static String ACTION_CAT = "cat";
	public static String ACTION_NODE = "node";

	/**
	 * @Description: 添加节点
	 */
	public R addCategoryB(TypedHashMap<String, Object> ps) {
		String nodetype = TYPE_NODE;
		String id = ps.getString("id");
		String action = ps.getString("action");
		String text = ps.getString("text", "新节点");
		if (ToolUtil.isOneEmpty(id, action)) {
			return R.FAILURE_REQ_PARAM_ERROR();
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
			return R.FAILURE("无法判定当前动作");
		}
		// 获取序列号
		String next_id = getNextId();
		if (ToolUtil.isEmpty(next_id)) {
			return R.FAILURE("获取序列号失败");
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
				return R.FAILURE("根节点不允许插入品类");
			}
			ins.set("route", next_id);
		} else {
			// 获取父节点信息
			Rcd brs = db.uniqueRecord("select * from product_category where is_deleted='N' and id=?", id);
			if (ToolUtil.isEmpty(brs)) {
				return R.FAILURE("节点不存在");
			}
			// 如果父节点是品类,则不允许派生
			if (brs.getString("is_cat").equals("Y")) {
				return R.FAILURE("本节点为品类,不允许创建子节点");
			}
			ins.set("route", brs.getString("route") + "-" + next_id);
		}
		db.execute(ins);
		JSONObject e = new JSONObject();
		e.put("id", next_id);
		e.put("type", nodetype);
		return R.SUCCESS_OPER(e);
	}

	/**
	 * @Description: 删除节点
	 */
	public R deleteCategoryB(String id) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Rcd rs = db.uniqueRecord("select count(1) value from product_category where is_deleted='N' and parent_id=?",
				id);
		if (rs.getInteger("value") > 0) {
			return R.FAILURE("先删除子节点");
		} else {
			Update ups = new Update("product_category");
			ups.set("is_deleted", "Y");
			ups.where().and("id=?", id);
			db.execute(ups);
		}
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description:更新节点
	 */
	public void updateCategoryB() {
	}

	/**
	 * @Description:重命名节点
	 */
	public R renameCategoryB(String id, String text) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		if (id.equals("0")) {
			return R.FAILURE("根节点不允许修改");
		}
		Update ups = new Update("product_category");
		ups.setIf("text", text);
		ups.where().and("id=?", id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description:按照前端树查询节点单个数据
	 */
	public void queryCategoryBById() {
	}

	/**
	 * @Description:按照前端树查询节点数据
	 */
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

	/**
	 * @Description:发布产品时,获取所有可用的品类
	 */
	public R queryAllProdCatList() {
		String sql = "";
		if (db.getDBType().equals(DbUtil.TYPE_MYSQL)) {
			sql = "select " + "levels, " + "level1_name, " + "case levels " + "when 0 " + "then level1_name "
					+ "when 1 " + "then concat(level1_name, '->', level2_name) " + "when 2 "
					+ "then concat(level1_name, '->', level2_name, '->', level3_name) " + "when 3 "
					+ "then concat(level1_name, '->', level2_name, '->', level3_name, '->', level4_name) " + "when 4 "
					+ "then concat(level1_name, '->', level2_name, '->', level3_name, '->', level4_name, '->', level5_name) "
					+ "when 5 " + "then "
					+ "concat(level1_name, '->', level2_name, '->', level3_name, '->', level4_name, '->', level5_name, '->', level6_name) "
					+ "when 6 " + "then "
					+ "concat(level1_name, '->', level2_name, '->', level3_name, '->', level4_name, '->', level5_name, '->', level6_name, "
					+ "'->', level7_name) " + "when 7 " + "then "
					+ "concat(level1_name, '->', level2_name, '->', level3_name, '->', level4_name, '->', level5_name, '->', level6_name, "
					+ "'->', level7_name, '->', level8_name) " + "else " + "'层级太多' end routename, " + "outtable.route, "
					+ "outtable.id " + "from (select " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level1) level1_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level2) level2_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level3) level3_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level4) level4_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level5) level5_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level6) level6_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level7) level7_name, " + "(select text " + "from product_category i "
					+ "where i.id = outer2.level8) level8_name, " + "outer2.* " + "from (select "
					+ "length(route) - length(replace(route, '-', ''))                          levels, "
					+ "case instr(route, '-') " + "when 0 " + "then route "
					+ "else substr(route, 1, instr(route, '-') - 1) end                         level1, "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then substr(route, case when substring_index(route, '-', 2) = substring_index(route, '-', 1) "
					+ "then 0 " + "else length(substring_index(route, '-', 1)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 2) = substring_index(route, '-', 1) "
					+ "then 0 " + "else length(substring_index(route, '-', 1)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 2) = substring_index(route, '-', 1) "
					+ "then 0 " + "else length(substring_index(route, '-', 1)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 3) = substring_index(route, '-', 2) " + "then 0 "
					+ "else length(substring_index(route, '-', 2)) + 1 end - "
					+ "case when substring_index(route, '-', 2) = substring_index(route, '-', 1) " + "then 0 "
					+ "else length(substring_index(route, '-', 1)) + 1 end - 1) end level2, "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then '-1' " + "when 2 "
					+ "then substr(route, case when substring_index(route, '-', 3) = substring_index(route, '-', 2) "
					+ "then 0 " + "else length(substring_index(route, '-', 2)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 3) = substring_index(route, '-', 2) "
					+ "then 0 " + "else length(substring_index(route, '-', 2)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 3) = substring_index(route, '-', 2) "
					+ "then 0 " + "else length(substring_index(route, '-', 2)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 4) = substring_index(route, '-', 3) " + "then 0 "
					+ "else length(substring_index(route, '-', 3)) + 1 end - "
					+ "case when substring_index(route, '-', 3) = substring_index(route, '-', 2) " + "then 0 "
					+ "else length(substring_index(route, '-', 2)) + 1 end - 1) end level3, "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then '-1' " + "when 2 " + "then '-1' " + "when 3 " + "then "
					+ "substr(route, case when substring_index(route, '-', 4) = substring_index(route, '-', 3) "
					+ "then 0 " + "else length(substring_index(route, '-', 3)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 4) = substring_index(route, '-', 3) "
					+ "then 0 " + "else length(substring_index(route, '-', 3)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 4) = substring_index(route, '-', 3) "
					+ "then 0 " + "else length(substring_index(route, '-', 3)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 5) = substring_index(route, '-', 4) " + "then 0 "
					+ "else length(substring_index(route, '-', 4)) + 1 end - "
					+ "case when substring_index(route, '-', 4) = substring_index(route, '-', 3) " + "then 0 "
					+ "else length(substring_index(route, '-', 3)) + 1 end - 1) end level4, "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then '-1' " + "when 2 " + "then '-1' " + "when 3 " + "then '-1' " + "when 4 " + "then "
					+ "substr(route, case when substring_index(route, '-', 5) = substring_index(route, '-', 4) "
					+ "then 0 " + "else length(substring_index(route, '-', 4)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 5) = substring_index(route, '-', 4) "
					+ "then 0 " + "else length(substring_index(route, '-', 4)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 5) = substring_index(route, '-', 4) "
					+ "then 0 " + "else length(substring_index(route, '-', 4)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 6) = substring_index(route, '-', 5) " + "then 0 "
					+ "else length(substring_index(route, '-', 5)) + 1 end - "
					+ "case when substring_index(route, '-', 5) = substring_index(route, '-', 4) " + "then 0 "
					+ "else length(substring_index(route, '-', 4)) + 1 end - 1) end level5, "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then '-1' " + "when 2 " + "then '-1' " + "when 3 " + "then '-1' " + "when 4 " + "then '-1' "
					+ "when 5 " + "then "
					+ "substr(route, case when substring_index(route, '-', 6) = substring_index(route, '-', 5) "
					+ "then 0 " + "else length(substring_index(route, '-', 5)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 6) = substring_index(route, '-', 5) "
					+ "then 0 " + "else length(substring_index(route, '-', 5)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 6) = substring_index(route, '-', 5) "
					+ "then 0 " + "else length(substring_index(route, '-', 5)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 7) = substring_index(route, '-', 6) " + "then 0 "
					+ "else length(substring_index(route, '-', 6)) + 1 end - "
					+ "case when substring_index(route, '-', 6) = substring_index(route, '-', 5) " + "then 0 "
					+ "else length(substring_index(route, '-', 5)) + 1 end - 1) end level6, " + " "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then '-1' " + "when 2 " + "then '-1' " + "when 3 " + "then '-1' " + "when 4 " + "then '-1' "
					+ "when 5 " + "then '-1' " + "when 6 "
					+ "then substr(route, case when substring_index(route, '-', 7) = substring_index(route, '-', 6) "
					+ "then 0 " + "else length(substring_index(route, '-', 6)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 7) = substring_index(route, '-', 6) "
					+ "then 0 " + "else length(substring_index(route, '-', 6)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 7) = substring_index(route, '-', 6) "
					+ "then 0 " + "else length(substring_index(route, '-', 6)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 8) = substring_index(route, '-', 7) " + "then 0 "
					+ "else length(substring_index(route, '-', 7)) + 1 end - "
					+ "case when substring_index(route, '-', 7) = substring_index(route, '-', 6) " + "then 0 "
					+ "else length(substring_index(route, '-', 6)) + 1 end - 1) end level7, "
					+ "case length(route) - length(replace(route, '-', '')) " + "when 0 " + "then '-1' " + "when 1 "
					+ "then '-1' " + "when 2 " + "then '-1' " + "when 3 " + "then '-1' " + "when 4 " + "then '-1' "
					+ "when 5 " + "then '-1' " + "when 6 " + "then '-1' " + "when 7 " + "then "
					+ "substr(route, case when substring_index(route, '-', 8) = substring_index(route, '-', 7) "
					+ "then 0 " + "else length(substring_index(route, '-', 7)) + 1 end + 1, "
					+ "length(route) - case when substring_index(route, '-', 8) = substring_index(route, '-', 7) "
					+ "then 0 " + "else length(substring_index(route, '-', 7)) + 1 end) "
					+ "else substr(route, case when substring_index(route, '-', 8) = substring_index(route, '-', 7) "
					+ "then 0 " + "else length(substring_index(route, '-', 7)) + 1 end + 1, "
					+ "case when substring_index(route, '-', 9) = substring_index(route, '-', 8) " + "then 0 "
					+ "else length(substring_index(route, '-', 8)) + 1 end - "
					+ "case when substring_index(route, '-', 8) = substring_index(route, '-', 7) " + "then 0 "
					+ "else length(substring_index(route, '-', 7)) + 1 end - 1) end level8, " + "route, " + "id "
					+ "from product_category t " + "where is_deleted = 'N' and is_cat = 'Y') outer2) outtable "
					+ "order by route ";
		} else if (db.getDBType().equals(DbUtil.TYPE_ORACLE)) {
			sql = "select " + "levels, " + "level1_name, " + "decode(levels, " + "0,level1_name, "
					+ "1,level1_name||'->'||level2_name, " + "2,level1_name||'->'||level2_name||'->'||level3_name, "
					+ "3,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name, "
					+ "4,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name, "
					+ "5,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name, "
					+ "6,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name, "
					+ "7,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name||'->'||level8_name, "
					+ "'层级太多' " + ") routename, " + "outtable.route,outtable.id from ( " + "select "
					+ "(select text  from product_category i where i.ID=outer.level1) level1_name, "
					+ "(select text  from product_category i where i.ID=outer.level2) level2_name, "
					+ "(select text  from product_category i where i.ID=outer.level3) level3_name, "
					+ "(select text  from product_category i where i.ID=outer.level4) level4_name, "
					+ "(select text  from product_category i where i.ID=outer.level5) level5_name, "
					+ "(select text  from product_category i where i.ID=outer.level6) level6_name, "
					+ "(select text  from product_category i where i.ID=outer.level7) level7_name, "
					+ "(select text  from product_category i where i.ID=outer.level8) level8_name, " + "outer.* "
					+ "from " + "( " + "select length(route) - length(replace(route,'-','')) levels , "
					+ "decode(instr(route,'-'), 0,route,substr(route,1,instr(route,'-') -1)) level1 , "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', "
					+ "1 , substr(route,instr(route,'-',1,1)+1, length(route)-instr(route,'-',1,1)), "
					+ "substr(route,instr(route,'-',1,1)+1 ,instr(route,'-',1,2) - instr(route,'-',1,1) -1)) level2, "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
					+ "2 , substr(route,instr(route,'-',1,2)+1, length(route)-instr(route,'-',1,2)), "
					+ "substr(route,instr(route,'-',1,2)+1 ,instr(route,'-',1,3) - instr(route,'-',1,2) -1)) level3, "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
					+ "2 , '-1', " + "3 , substr(route,instr(route,'-',1,3)+1, length(route)-instr(route,'-',1,3)), "
					+ "substr(route,instr(route,'-',1,3)+1 ,instr(route,'-',1,4) - instr(route,'-',1,3) -1)) level4, "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
					+ "2 , '-1', " + "3 , '-1', "
					+ "4 , substr(route,instr(route,'-',1,4)+1, length(route)-instr(route,'-',1,4)), "
					+ "substr(route,instr(route,'-',1,4)+1 ,instr(route,'-',1,5) - instr(route,'-',1,4) -1)) level5, "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
					+ "2 , '-1', " + "3 , '-1', " + "4 , '-1', "
					+ "5 , substr(route,instr(route,'-',1,5)+1, length(route)-instr(route,'-',1,5)), "
					+ "substr(route,instr(route,'-',1,5)+1 ,instr(route,'-',1,6) - instr(route,'-',1,5) -1)) level6, "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
					+ "2 , '-1', " + "3 , '-1', " + "4 , '-1', " + "5 , '-1', "
					+ "6 , substr(route,instr(route,'-',1,6)+1, length(route)-instr(route,'-',1,6)), "
					+ "substr(route,instr(route,'-',1,6)+1 ,instr(route,'-',1,7) - instr(route,'-',1,6) -1)) level7, "
					+ "decode(length(route) - length(replace(route,'-','')), " + "0 , '-1', " + "1 , '-1', "
					+ "2 , '-1', " + "3 , '-1', " + "4 , '-1', " + "5 , '-1', " + "6 , '-1', "
					+ "7 , substr(route,instr(route,'-',1,7)+1, length(route)-instr(route,'-',1,7)), "
					+ "substr(route,instr(route,'-',1,7)+1 ,instr(route,'-',1,8) - instr(route,'-',1,7) -1)) level8, "
					+ "route,id "
					+ "from product_category t where is_deleted='N' and is_cat='Y') outer) outtable order by route ";
		}
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public R publishCatAttrList(TypedHashMap<String, Object> ps) {
		JSONObject rs = new JSONObject();
		String cat_id = ps.getString("cat_id"); // 必须存在
		String base_attr = ToolUtil.parseYNValueDefY(ps.getString("base_attr", "Y"));
		String sale_attr = ToolUtil.parseYNValueDefY(ps.getString("sale_attr", "Y"));
		String is_used = ToolUtil.parseYNValueDefY(ps.getString("is_used", ""));
		if (ToolUtil.isEmpty(cat_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		// 获得基本属性
		if (base_attr.equals("Y")) {
			rs.put("base_attr", getBaseAttr(cat_id, is_used));
		}
		// 获得销售属性
		if (sale_attr.equals("Y")) {
			rs.put("sale_attr", getSaleAttr(cat_id, is_used));
			// 获取说有该品牌下的销售属性选项值
			rs.put("sale_attr_set_map", getBaseAttrValueMap(cat_id));
		}
		return R.SUCCESS_OPER(rs);
	}

	private JSONArray getSaleAttr(String cat_id, String is_used) {
		JSONArray rs = new JSONArray();
		String attrsql = "select * from product_category_attr where is_deleted='N' and cat_id=? and attr_type='sale' order by od";
		if (ToolUtil.isEmpty(is_used)) {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " ");
		} else {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " and is_used='" + is_used + "' ");
		}
		RcdSet attr_rs = db.query(attrsql, cat_id);
		for (int i = 0; i < attr_rs.size(); i++) {
			JSONObject e = new JSONObject();
			e = ConvertUtil.OtherJSONObjectToFastJSONObject(attr_rs.getRcd(i).toJsonObject());
			// 销售属性必须可枚举,否则不显示,不多做判断,当前只支持枚举多选
			if ("Y".equals(attr_rs.getRcd(i).getString("is_enum"))) {
				String isql = "select * from product_category_attr_set where is_deleted='N' and attr_id=? and cat_id=? order by od";
				e.put("list", ConvertUtil.OtherJSONObjectToFastJSONArray(
						db.query(isql, attr_rs.getRcd(i).getString("attr_id"), cat_id).toJsonArrayWithJsonObject()));
			}
			rs.add(e);
		}
		return rs;
	}

	private JSONArray getBaseAttr(String cat_id, String is_used) {
		JSONArray rs = new JSONArray();
		String attrsql = "select * from product_category_attr where cat_id=? and is_deleted='N' and attr_type='base' <#IS_USED#> order by od";
		if (ToolUtil.isEmpty(is_used)) {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " ");
		} else {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " and is_used='" + is_used + "' ");
		}
		RcdSet attr_rs = db.query(attrsql, cat_id);
		for (int i = 0; i < attr_rs.size(); i++) {
			JSONObject e = new JSONObject();
			e = ConvertUtil.OtherJSONObjectToFastJSONObject(attr_rs.getRcd(i).toJsonObject());
			// 不做判断,全部将属性值数据填充
			String isql = "select * from product_category_attr_set where attr_id=? and is_deleted='N' and cat_id=? order by od";
			e.put("list", ConvertUtil.OtherJSONObjectToFastJSONArray(
					db.query(isql, attr_rs.getRcd(i).getString("attr_id"), cat_id).toJsonArrayWithJsonObject()));
			rs.add(e);
		}
		return rs;
	}

	// 获取品类下面的所有的值,匹配数据用
	private JSONArray getBaseAttrValueMap(String cat_id) {
		String sql = "select a.attr_set_id, value from product_category_attr_set a,product_category_attr b where a.attr_id=b.attr_id and b.is_deleted='N' and a.is_deleted='N' and b.cat_id=? and b.attr_type='sale'";
		return ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, cat_id).toJsonArrayWithJsonObject());
	}
}
