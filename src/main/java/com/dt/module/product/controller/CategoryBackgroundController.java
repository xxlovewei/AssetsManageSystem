package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.core.db.DB;
import com.dt.module.product.service.CategoryBService;

/*后台商品类目管理*/
@Controller
@RequestMapping("/api")
public class CategoryBackgroundController extends BaseController {
	@Autowired
	private DB db = null;
	@Autowired
	CategoryBService categoryBService;

	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/prodPublishCatList")
	// 产品发布时选择产品类目
	public ResData prodPublishCatList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// resource type url
		String sql = "select " + "levels, " + "level1_name, " + "decode(levels, " + "0,level1_name, "
				+ "1,level1_name||'->'||level2_name, " + "2,level1_name||'->'||level2_name||'->'||level3_name, "
				+ "3,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name, "
				+ "4,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name, "
				+ "5,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name, "
				+ "6,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name, "
				+ "7,level1_name||'->'||level2_name||'->'||level3_name||'->'||level4_name||'->'||level5_name||'->'||level6_name||'->'||level7_name||'->'||level8_name, "
				+ "'层级太多' " + ") routename, " + "outtable.route,outtable.id from ( " + "select "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level1) level1_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level2) level2_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level3) level3_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level4) level4_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level5) level5_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level6) level6_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level7) level7_name, "
				+ "(select text  from PRODUCT_CATEGORY i where i.ID=outer.level8) level8_name, " + "outer.* " + "from "
				+ "( " + "select LENGTH(route) - LENGTH(REPLACE(route,'-','')) levels , "
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
				+ "route,id "
				+ "from PRODUCT_CATEGORY t where is_deleted='N' and is_cat='Y') outer) outtable order by route ";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/queryTreeList.do")
	public ResData categoryBqueryTreeList() {
		return ResData.SUCCESS_OPER(categoryBService.queryCategoryBTreeList());
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/delete.do")
	public ResData categoryBdelete(String id) {
		return categoryBService.deleteCategoryB(id);
	}
	// 获取一个品类下的单个属性的属性项数据
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/catAttrValueQuery.do")
	public ResData catAttrValueQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String attr_id = request.getParameter("attr_id");
		if (attr_id == null) {
			return ResData.FAILURE_OPER();
		}
		String sql = "select * from product_category_attr_set where is_deleted='N' and attr_id=? order by od";
		return ResData.SUCCESS_OPER(db.query(sql, attr_id).toJsonArrayWithJsonObject());
	}
	// 获取一个品类下的所有属性数据及属性项目的数据,可能用户加产品的时候需要
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/catAttrQueryById.do")
	public ResData catAttrQueryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("ID");
		Rcd r = db.uniqueRecord("select * from product_category_attr where ID=?", id);
		return ResData.SUCCESS("操作成功", r.toJsonObject());
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueAdd.do")
	public ResData catAttrValueAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String attr_id = request.getParameter("attr_id");
		String sql = "select ( select decode(max(attr_set_id),null,1,max(attr_set_id)+1)  value from product_category_attr_set  ) next_attr_set_id, a.* from PRODUCT_CATEGORY_ATTR a where IS_DELETED='N' and attr_id=?";
		Rcd rs = db.uniqueRecord(sql, attr_id);
		if (rs == null) {
			return ResData.FAILURE_OPER();
		}
		String next_attr_set_id = rs.getString("next_attr_set_id");
		String cat_id = rs.getString("cat_id");
		Insert ins = new Insert("product_category_attr_set");
		ins.set("id", db.getUUID());
		ins.set("attr_set_id", next_attr_set_id);
		ins.set("is_deleted", "N");
		ins.set("attr_id", attr_id);
		ins.set("cat_id", cat_id);
		ins.set("od", 0);
		ins.set("value", "新选项值");
		db.execute(ins);
		// 插入一条空行
		return ResData.SUCCESS_OPER();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueUpdate.do")
	public ResData catAttrValueUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String attr_set_id = request.getParameter("ID");
		Update ups = new Update("product_category_attr_set");
		ups.setIf("value", request.getParameter("VALUE"));
		ups.setIf("od", request.getParameter("OD"));
		ups.setIf("SN", request.getParameter("SN"));
		ups.setIf("is_used", request.getParameter("IS_USED"));
		ups.where().and("id=?", attr_set_id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueDel.do")
	public ResData catAttrValueDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("ID");
		Update ups = new Update("product_category_attr_set");
		ups.set("is_deleted", "Y");
		ups.where().and("id=?", id);
		// 是否直接删除?
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrAdd.do")
	public ResData catAttrAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 新增不影响
		// 删除,策略如果已经被使用就不允许删除
		// cat_id 是否存在
		String cat_id = request.getParameter("CAT_ID");
		if (cat_id == null) {
			return ResData.FAILURE_OPER();
		}
		Rcd cat_rs = db.uniqueRecord("select *  from product_category where is_deleted='N'  and id=? and is_cat='Y'",
				cat_id);
		if (cat_rs == null) {
			return ResData.FAILURE("不存在该品类");
		}
		String tsql = "select ( select decode(max(attr_id),null,1,max(attr_id)+1) value from product_category_attr) attr_id, ";
		tsql = tsql
				+ " ( select decode(max(attr_set_id),null,1,max(attr_set_id)+1) value from product_category_attr_set) attr_set_id ";
		tsql = tsql + " from dual ";
		Rcd attr_rs = db.uniqueRecord(tsql);
		if (attr_rs == null) {
			return ResData.FAILURE("生成失败");
		}
		// 添加一条销售属性，每个品类必须要有一个销售属性
		String next_attr_id = attr_rs.getString("attr_id");
		String attr_type = request.getParameter("ATTR_TYPE");
		String input_type = request.getParameter("INPUT_TYPE");
		Insert ins = new Insert("product_category_attr");
		ins.set("id", db.getUUID());
		ins.set("attr_id", next_attr_id);
		ins.set("is_deleted", "N");
		ins.set("is_key", "N");
		ins.set("is_need", request.getParameter("IS_NEED"));
		ins.set("can_alias", request.getParameter("CAN_ALIAS"));
		ins.set("name", request.getParameter("NAME"));
		ins.set("cat_id", cat_id);
		ins.set("od", request.getParameter("OD"));
		ins.set("attr_type", attr_type);
		ins.set("is_used", request.getParameter("IS_USED"));
		ins.set("is_search", request.getParameter("IS_SEARCH"));
		// input,select-multi,select-single
		if (attr_type.equals("sale")) {
			// 如果是销售属性只支持多选枚举
			ins.set("is_input", "N");
			ins.set("is_enum", "Y");
			ins.set("input_type", "select-multi");
			if (!input_type.equals("select-multi")) {
				return ResData.FAILURE("销售元素只能选择多选框");
			}
		} else if (attr_type.equals("base")) {
			ins.set("input_type", input_type);
			if (input_type.equals("input")) {
				ins.set("is_input", "Y");
				ins.set("is_enum", "N");
			} else if (input_type.equals("select-single") || input_type.equals("select-multi")) {
				ins.set("is_input", "N");
				ins.set("is_enum", "Y");
			} else {
				return ResData.FAILURE("请选择正确的属性");
			}
		} else {
			return ResData.FAILURE("请选择正确的属性");
		}
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	@Res
	@Acl
	@Transactional
	@RequestMapping("/categoryB/catAttrDel.do")
	public ResData catAttrDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("ID");
		int uscnt = db.uniqueRecord(
				"select count(1) value from product_attr_set a,product_category_attr b where a.attr_id=b.attr_id and b.id=? and b.is_deleted='N' ",
				id).getInteger("value");
		if (uscnt > 0) {
			return ResData.FAILURE("已有产品在使用中,暂不可删除");
		}
		// 如果确实没有使用
		Update ups = new Update("product_category_attr");
		ups.set("is_deleted", "Y");
		ups.where().and("id=?", id);
		// 删除对应的属性值
		Update ups2 = new Update("product_category_attr_set");
		ups2.set("is_deleted", "Y");
		ups2.where().and("attr_id in (select attr_id from product_category_attr where id=?)", id);
		db.execute(ups2);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrUpdate.do")
	public ResData catAttrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("ID");
		if (id == null) {
			return ResData.FAILURE("请输入ID");
		}
		Update ups = new Update("product_category_attr");
		ups.setIf("is_need", request.getParameter("IS_NEED"));
		ups.setIf("can_alias", request.getParameter("CAN_ALIAS"));
		ups.setIf("name", request.getParameter("NAME"));
		ups.set("od", request.getParameter("OD"));
		ups.set("is_used", request.getParameter("IS_USED"));
		ups.set("is_search", request.getParameter("IS_SEARCH"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	// 获取一个品类下的所有属性数据
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/catAttrQuery.do")
	public ResData catAttrQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cat_id = request.getParameter("cat_id");
		if (cat_id == null) {
			return ResData.FAILURE_OPER();
		}
		String sql = "select a.* ,decode(a.ATTR_TYPE,'sale','销售属性','base','基本属性','desc','描述属性','未知') attr_type_name from product_category_attr a where  is_deleted='N'  and cat_id=? order by attr_type,od";
		return ResData.SUCCESS_OPER(db.query(sql, cat_id).toJsonArrayWithJsonObject());
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/update.do")
	public ResData categoryBupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResData.SUCCESS();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/rename.do")
	public ResData categoryBrename(String id, String text) throws IOException {
		if (ToolUtil.isOneEmpty(id, text)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (id.equals("0")) {
			return ResData.FAILURE("根节点不允许修改");
		}
		return categoryBService.renameCategoryB(id, text);
	}
	@Res
	@Acl
	@Transactional
	@RequestMapping("/categoryB/add.do")
	public ResData categoryBadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.addCategoryB(ps);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/prodPublishCatAttrList.do")
	// 获取产品属性
	public ResData prodPublishCatAttrList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject rs = new JSONObject();
		String cat_id = request.getParameter("CAT_ID"); // 必须存在
		String base_attr = request.getParameter("BASE_ATTR"); // BASE_ATTR不存在则显示
		if (base_attr == null || base_attr.equals("Y")) {
			base_attr = "Y";
		} else {
			base_attr = "N";
		}
		String sale_attr = request.getParameter("SALE_ATTR");// SALE_ATTR不存在则显示
		if (sale_attr == null || sale_attr.equals("Y")) {
			sale_attr = "Y";
		} else {
			sale_attr = "N";
		}
		// 获得基本属性
		if (base_attr.equals("Y")) {
			rs.put("BASE_ATTR", getProdBaseAttr(cat_id, request.getParameter("IS_USED")));
		}
		// 获得销售属性
		if (sale_attr.equals("Y")) {
			rs.put("SALE_ATTR", getProdSaleAttr(cat_id, request.getParameter("IS_USED")));
			// 获取说有该品牌下的销售属性选项值
			rs.put("SALE_ATTR_SET_MAP", getProdBaseAttrValue(cat_id));
		}
		return ResData.SUCCESS_OPER(rs);
	}
	private JSONArray getProdSaleAttr(String cat_id, String is_used) {
		JSONArray rs = new JSONArray();
		String attrsql = "select * from product_category_attr where  is_deleted='N' and cat_id=? and attr_type='sale' order by od";
		if (is_used != null) {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " and is_used='" + is_used + "' ");
		} else {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " ");
		}
		RcdSet attr_rs = db.query(attrsql, cat_id);
		for (int i = 0; i < attr_rs.size(); i++) {
			JSONObject e = new JSONObject();
			e = ConvertUtil.OtherJSONObjectToFastJSONObject(attr_rs.getRcd(i).toJsonObject());
			// 销售属性必须可枚举,否则不显示,强制不做判断，只支持枚举多选
			if ("Y".equals(attr_rs.getRcd(i).getString("is_enum"))) {
				String isql = "select * from product_category_attr_set where  is_deleted='N' and attr_id=? and cat_id=? order by od";
				e.put("LIST", ConvertUtil.OtherJSONObjectToFastJSONArray(
						db.query(isql, attr_rs.getRcd(i).getString("attr_id"), cat_id).toJsonArrayWithJsonObject()));
			} else {
			}
			rs.add(e);
		}
		return rs;
	}
	private JSONArray getProdBaseAttr(String cat_id, String is_used) {
		JSONArray rs = new JSONArray();
		String attrsql = "select * from product_category_attr where cat_id=? and is_deleted='N' and attr_type='base' <#IS_USED#> order by od";
		if (is_used != null) {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " and is_used='" + is_used + "' ");
		} else {
			attrsql = attrsql.replaceAll("<#IS_USED#>", " ");
		}
		RcdSet attr_rs = db.query(attrsql, cat_id);
		for (int i = 0; i < attr_rs.size(); i++) {
			JSONObject e = new JSONObject();
			e = ConvertUtil.OtherJSONObjectToFastJSONObject(attr_rs.getRcd(i).toJsonObject());
			String isql = "select * from product_category_attr_set where attr_id=? and is_deleted='N' and cat_id=? order by od";
			e.put("LIST", db.query(isql, attr_rs.getRcd(i).getString("attr_id"), cat_id).toJsonArrayWithJsonObject());
			rs.add(e);
		}
		return rs;
	}
	// 获取品牌下面的所有的值,匹配数据用
	private JSONArray getProdBaseAttrValue(String cat_id) {
		String sql = "select a.attr_set_id, value from product_category_attr_set a,product_category_attr b where a.attr_id=b.attr_id and b.is_deleted='N' and a.is_deleted='N' and b.cat_id=?  and b.attr_type='sale'";
		return ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, cat_id).toJsonArrayWithJsonObject());
	}
}
