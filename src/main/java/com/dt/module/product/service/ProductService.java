package com.dt.module.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.Rcd;
import com.dt.dao.RcdSet;
import com.dt.dao.sql.Delete;
import com.dt.dao.sql.Insert;
import com.dt.dao.sql.SQL;
import com.dt.dao.sql.Update;
import com.dt.dao.util.TypedHashMap;
import com.dt.tool.util.ConvertUtil;
import com.dt.tool.util.DbUtil;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:16:07
 * @Description: TODO
 */
// 销售属性必须选择,否则无法下单,如果为选全销售属性,则显示默认价格,默认库存
// 产品发布，SKU生成规则是从product_PRODUCT_CATEGORY_ATTR_SET中的ATTR_SET_ID中匹配,
// 注意一个品牌中的ATTR_SET_ID必须唯一。ATTR_SET_ID后期可以不唯一,否则生成的SKU发生错误
@Service
public class ProductService extends BaseService {
	public static String IAMGE_TYPE_PROD = "prod";
	public static String IAMGE_TYPE_PROD_MOBILE = "mobile";

	/**
	 * @Description:获取产品销售属性
	 */
	public JSONArray getProdSaleList(String spu) {
		JSONArray rs = new JSONArray();
		String sql = "select * from product_sku where spu=? ";
		RcdSet r = db.query(sql, spu);
		for (int i = 0; i < r.size(); i++) {
			JSONObject obj = new JSONObject();
			obj = ConvertUtil.OtherJSONObjectToFastJSONObject(r.getRcd(i).toJsonObject());
			// 获取属性
			obj.put("attr_set_ids",
					ConvertUtil.OtherJSONObjectToFastJSONArray(
							db.query("select * from product_sku_map where sku=?", r.getRcd(i).getString("sku"))
									.toJsonArrayWithJsonObject()));
			rs.add(obj);
		}
		return rs;
	}

	/**
	 * @Description:获取产品sku
	 */
	public JSONArray getProdSkuCombination(String spu) {
		String sql = "select * from product_sku where spu=?";
		RcdSet r = db.query(sql, spu);
		JSONArray rs = new JSONArray();
		for (int i = 0; i < r.size(); i++) {
			JSONObject e = new JSONObject();
			e.put(r.getRcd(i).getString("sku_uuid"),
					ConvertUtil.OtherJSONObjectToFastJSONObject(r.getRcd(i).toJsonObject()));
			rs.add(e);
		}
		return rs;
	}

	/**
	 * @Description:获取商品的基本属性
	 */
	public JSONArray getProdSaleDirect(String spu) {
		JSONArray r = new JSONArray();
		// 跟随模版排序
		String sql = "select " + "distinct a.attr_id,a.name,od " + "from "
				+ "product_attr_set b,product_category_attr a " + " where " + "a.attr_id=b.attr_id " + "and b.spu=? "
				+ "and b.is_sku='Y' " + "and attr_type='sale' "
				+ "and cat_id in (select cat_id from product where spu=? ) " + "order by od ";
		RcdSet rs = db.query(sql, spu, spu);
		for (int i = 0; i < rs.size(); i++) {
			JSONObject e = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.getRcd(i).toJsonObject());
			// 跟随模版排序
			String isql = "select " + "distinct a.attr_set_id,a.value,od " + "from "
					+ "product_attr_set b,product_category_attr_set a " + "where " + "a.attr_id=b.attr_id "
					+ "and b.spu=? " + "and b.is_sku='Y' " + "and a.attr_id=? "
					+ "and cat_id in (select cat_id from product where spu=?) " + "order by od ";
			e.put("list", ConvertUtil.OtherJSONObjectToFastJSONArray(
					db.query(isql, spu, rs.getRcd(i).getString("attr_id"), spu).toJsonArrayWithJsonObject()));
			r.add(e);
		}
		return r;
	}

	/**
	 * @Description:获取商品的图片列表
	 */
	public JSONArray getProdPics(String spu) {
		return ConvertUtil.OtherJSONObjectToFastJSONArray(
				db.query("select * from product_pic where spu=? and type=? order by od", spu, IAMGE_TYPE_PROD)
						.toJsonArrayWithJsonObject());
	}

	/**
	 * @Description:更新商品的图片列表
	 */
	public ResData updateProdPics(String spu, String pics) {
		JSONArray pics_arr = JSONArray.parseArray(pics);
		db.executeSQLList(updateProdPics(spu, pics_arr));
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:获取商品的基本属性
	 */
	private JSONArray getProdBaseList(String spu, String cat_id) {
		JSONArray rs = new JSONArray();
		String basesql = "select a.*, b.value,b.attr_set_id from ( "
				+ "select * from product_category_attr where cat_id=? "
				+ "and is_used='Y' and is_deleted='N' and attr_type='base')a "
				+ "left join (select * from product_attr_set where spu=? and is_sku='N') b "
				+ "on a.attr_id=b.attr_id ";

		RcdSet attr_rs = db.query(basesql, cat_id, spu);
		for (int i = 0; i < attr_rs.size(); i++) {
			JSONObject obj = ConvertUtil.OtherJSONObjectToFastJSONObject(attr_rs.getRcd(i).toJsonObject());
			if (attr_rs.getRcd(i).getString("input_type").equals(CategoryAttrService.INPUTTYPE_INPUT)) {
				obj.put("attr_set_value", attr_rs.getRcd(i).getString("value"));
			} else if (attr_rs.getRcd(i).getString("input_type").equals(CategoryAttrService.INPUTTYPE_SEL_SINGLE)) {
				obj.put("attr_set_value", attr_rs.getRcd(i).getString("attr_set_id"));
			}
			if ("Y".equals(attr_rs.getRcd(i).getString("is_enum"))) {
				String isql = "select * from product_category_attr_set where is_deleted='N' and attr_id=? and cat_id=? order by od";
				obj.put("list", ConvertUtil.OtherJSONObjectToFastJSONArray(
						db.query(isql, attr_rs.getRcd(i).getString("attr_id"), cat_id).toJsonArrayWithJsonObject()));
			}
			rs.add(obj);
		}
		return rs;
	}

	/**
	 * @Description:批量删除商品
	 */
	public ResData deleteProds(String prods) {
		if (ToolUtil.isEmpty(prods)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray prod_arr = JSONArray.parseArray(prods);
		if (prod_arr.size() == 0) {
			return ResData.FAILURE("请选择至少一个商品");
		}
		List<SQL> sqls = new ArrayList<SQL>();
		for (int i = 0; i < prod_arr.size(); i++) {
			Update ups = new Update("product");
			ups.set("is_deleted", "Y");
			ups.where().and("spu=?", prod_arr.getJSONObject(i).getString("spu"));
			sqls.add(ups);
		}
		db.executeSQLList(sqls);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:按照后台类目查询产品
	 */
	public ResData queryProdByCat(String cat_id) {
		if (ToolUtil.isEmpty(cat_id)) {
			return ResData.FAILURE("请输入品类ID");
		}
		String sql = "select * from product where cat_id=? and is_deleted='N' ";
		RcdSet rs = db.query(sql, cat_id);
		return ResData.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	/**
	 * @Description:按照SPU查询产品信息
	 */
	public ResData queryProdBySpu(String spu) {
		JSONObject res = new JSONObject();
		if (ToolUtil.isEmpty(spu)) {
			return ResData.FAILURE("请选择商品");
		}
		/************** 获得商品公共属性 **************/
		String sql = "select * from product where is_deleted='N' and spu=?";
		Rcd rs = db.uniqueRecord(sql, spu);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE("商品不存在");
		}
		res = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
		/************** 获得商品基本属性 **************/
		// 用于修改基本属性
		res.put("base_attr", getProdBaseList(spu, rs.getString("cat_id")));
		// 用于修改商品销售属性
		res.put("sale_data_list", getProdSaleList(spu));
		return ResData.SUCCESS_OPER(res);
	}

	/**
	 * @Description:批量上架或下架产品
	 */
	public ResData prodOffOn(String prods, String is_off) {
		if (ToolUtil.isOneEmpty(prods, is_off)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (!(is_off.equals("Y") || is_off.equals("N"))) {
			return ResData.FAILURE("参数有误");
		}
		JSONArray prod_arr = JSONArray.parseArray(prods);
		if (prod_arr.size() == 0) {
			return ResData.FAILURE("请选择至少一个商品");
		}
		List<SQL> sqls = new ArrayList<SQL>();
		for (int i = 0; i < prod_arr.size(); i++) {
			Update ups = new Update("product");
			ups.set("is_off", is_off);
			ups.where().and("spu=?", prod_arr.getJSONObject(i).getString("spu"));
			sqls.add(ups);
		}
		db.executeSQLList(sqls);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:更新产品基本属性
	 */
	@Transactional
	public ResData updateProdBaseAttr(TypedHashMap<String, Object> ps) {
		/************************************ 处理主表 ************************/
		ArrayList<SQL> basesql = new ArrayList<SQL>();
		String spu = ps.getString("spu");
		String base_data = ps.getString("base_res");
		Update ups = new Update("product");
		ups.set("prod_name", ps.getString("prod_name"));
		ups.set("list_price", ps.getString("list_price"));
		ups.set("list_ori_price", ps.getString("list_ori_price"));
		ups.setIf("code", ps.getString("code"));
		ups.setIf("sales", ps.getString("sales"));
		ups.setIf("prod_desc", ps.getString("prod_desc"));
		ups.setIf("brand_id", ps.getString("brand_id"));
		ups.set("unit", ps.getString("unit"));
		ups.set("title", ps.getString("title"));
		ups.setIf("place", ps.getString("place"));
		ups.where().and("spu=?", spu);
		basesql.add(ups);
		// 处理基本属性
		JSONArray base_arr = JSONArray.parseArray(base_data);
		for (int i = 0; i < base_arr.size(); i++) {
			JSONObject obj = base_arr.getJSONObject(i);
			// 基本属性，不包括多选处理
			if (obj.getString("attr_type").equals(CategoryAttrService.ATTR_TYPE_BASE)
					&& !obj.getString("input_type").equals(CategoryAttrService.INPUTTYPE_SEL_MULTI)) {
				// 校验
				if (obj.getString("is_need").equals("Y")) {
					if (!obj.containsKey("attr_set_value")) {
						return ResData.FAILURE("请输入属性:" + obj.getString("NAME"));
					}
					if (obj.getString("attr_set_value").length() == 0) {
						return ResData.FAILURE("请输入属性:" + obj.getString("NAME"));
					}
				}
				Insert basins = new Insert("product_attr_set");
				basins.set("id", db.getUUID());
				basins.set("spu", spu);
				basins.set("is_sku", "N");
				basins.set("attr_id", obj.getString("attr_id"));
				if (obj.getString("input_type").equals(CategoryAttrService.INPUTTYPE_INPUT)) {
					basins.set("value", obj.getString("attr_set_value"));
				} else if (obj.getString("input_type").equals(CategoryAttrService.INPUTTYPE_SEL_SINGLE)) {
					basins.set("attr_set_id", obj.getString("attr_set_value"));
				}
				basesql.add(basins);
			}
		}
		db.execute("delete from product_attr_set where is_sku='N' and spu=?", spu);
		db.executeSQLList(basesql);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:获取商品的销售属性
	 */
	@Transactional
	public ResData updateProdSaleAttr(TypedHashMap<String, Object> ps) {
		int totalStock = 0;
		String spu = ps.getString("spu");
		String rebuild = ps.getString("rebuild");
		String sale_data = ps.getString("sale_res");
		String sale_data_kv = ps.getString("sale_kv");
		JSONArray sale_arr = JSONArray.parseArray(sale_data);
		JSONArray salekv_arr = JSONArray.parseArray(sale_data_kv);
		// 检查rebuild参数
		if (ToolUtil.isEmpty(rebuild)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (!(rebuild.endsWith("Y") || rebuild.endsWith("N"))) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		ArrayList<String> sqls = new ArrayList<String>();
		if (rebuild.equals("N")) {
			// 没有重新生成规格
			for (int i = 0; i < sale_arr.size(); i++) {
				JSONObject sale_obj = sale_arr.getJSONObject(i);
				Update ups = new Update("product_sku");
				ups.setIf("code", sale_obj.getString("code"));
				if (ToolUtil.isOneEmpty(sale_obj.getString("price"), sale_obj.getString("stock"))) {
					return ResData.FAILURE("请输入正确的价格或库存数");
				}
				int stock = sale_obj.getIntValue("stock");
				int price = sale_obj.getIntValue("price");
				ups.set("stock", stock);
				ups.set("price", price);
				totalStock = totalStock + stock;
				ups.where().and("sku=?", sale_obj.getString("sku"));
				sqls.add(ups.getSQL());
			}
		} else if (rebuild.equals("Y")) {
			// 删除sku数据,重新插入数据
			ResData res = getAddProdSkuSqls(spu, sale_arr, salekv_arr);
			if (res.isSuccess()) {
				sqls.add("delete from product_sku where spu='" + spu + "'");
				sqls.add("delete from product_sku_map where spu='" + spu + "'");
				sqls.add("delete from product_attr_set where spu='" + spu + "' and is_sku='Y'");
				JSONArray rs_sqls = ((JSONObject) res.getData()).getJSONArray("sqls");
				for (int i = 0; i < rs_sqls.size(); i++) {
					sqls.add(rs_sqls.get(i).toString());
				}
				totalStock = ((JSONObject) res.getData()).getIntValue("stock");
			} else {
				return res;
			}
		}
		// 更新主表库存
		sqls.add("update product set stock=" + totalStock + " where spu='" + spu + "'");
		// 执行语句
		db.executeStringList(sqls);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:获取更新商品规格的语句
	 */
	private ResData getAddProdSkuSqls(String spu, JSONArray sale_arr, JSONArray salekv_arr) {
		if (ToolUtil.isOneEmpty(spu, sale_arr, salekv_arr)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONObject res = new JSONObject();
		JSONArray sqls = new JSONArray();
		int totalStock = 0;
		for (int i = 0; i < sale_arr.size(); i++) {
			JSONObject sale_obj = sale_arr.getJSONObject(i);
			String skuuuid = "";
			String sku = ToolUtil.getUUID();
			Insert sale_ins = new Insert("product_sku");
			sale_ins.set("spu", spu);
			sale_ins.set("sku", sku);
			sale_ins.setIf("is_off", "N");
			sale_ins.setIf("code", sale_obj.getString("code"));
			if (ToolUtil.isOneEmpty(sale_obj.getString("price"), sale_obj.getString("stock"))) {
				return ResData.FAILURE("请输入正确的价格或库存数");
			}
			int stock = sale_obj.getIntValue("stock");
			sale_ins.set("stock", stock);
			sale_ins.set("price", sale_obj.getIntValue("price"));
			totalStock = totalStock + stock;
			// 获得SKU生成sku详细信息
			JSONArray arrmap = sale_obj.getJSONArray("attr_set_ids");
			for (int j = 0; j < arrmap.size(); j++) {
				String attr_set_id = arrmap.getJSONObject(j).getString("attr_set_id");
				skuuuid = skuuuid + "," + attr_set_id;
				Insert saledelins = new Insert("product_sku_map");
				saledelins.set("id", ToolUtil.getUUID());
				saledelins.set("spu", spu);
				saledelins.set("sku", sku);
				saledelins.set("attr_set_id", attr_set_id);
				sqls.add(saledelins.getSQL());
			}
			// 去掉第一个
			skuuuid = skuuuid.replaceFirst(",", "");
			sale_ins.set("sku_uuid", skuuuid);
			sqls.add(sale_ins.getSQL());
		}
		// 写入product_attr_set
		for (int i = 0; i < salekv_arr.size(); i++) {
			JSONObject salekv_obj = salekv_arr.getJSONObject(i);
			JSONArray salekv_obj_arr = salekv_obj.getJSONArray("data");
			for (int j = 0; j < salekv_obj_arr.size(); j++) {
				Insert ins2 = new Insert("product_attr_set");
				ins2.set("id", ToolUtil.getUUID());
				ins2.set("spu", spu);
				ins2.set("attr_set_id", salekv_obj_arr.getString(j));
				ins2.set("attr_id", salekv_obj.getString("attr_id"));
				ins2.set("is_sku", "Y");
				sqls.add(ins2.getSQL());
			}
		}
		res.put("stock", totalStock);
		res.put("sqls", sqls);
		return ResData.SUCCESS_OPER(res);
	}

	/**
	 * @Description:发布商品
	 */
	public ResData publishProduct(TypedHashMap<String, Object> ps) {
		ArrayList<String> basesql = new ArrayList<String>();
		String spu = db.getUUID();
		String cat_id = ps.getString("cat_id");
		if (ToolUtil.isEmpty("cat_id")) {
			return ResData.FAILURE("请选择品类");
		}
		if (ToolUtil.isEmpty(ps.getString("shop_id"))) {
			return ResData.FAILURE("必须选择一个店铺");
		}
		/************************************ 处理主表 ************************/
		Insert ins = new Insert("product");
		int totalStock = 0;
		ins.set("spu", spu);
		ins.setIf("shop_id", ps.getString("shop_id"));
		ins.setIf("prod_name", ps.getString("prod_name"));
		ins.set("cat_id", cat_id);
		ins.setIf("list_price", ps.getString("list_price"));
		ins.setIf("list_ori_price", ps.getString("list_ori_price"));
		ins.setIf("code", ps.getString("code"));
		ins.setIf("sales", ps.getString("sales", "0"));
		ins.set("is_off", "N");
		ins.setIf("title", ps.getString("title"));
		ins.setIf("prod_desc", ps.getString("prod_desc"));
		ins.setIf("brand_id", ps.getString("brand_id"));
		ins.setIf("pic_id", ps.getString("pic_id"));
		ins.setIf("unit", ps.getString("unit"));
		ins.setIf("place", ps.getString("place"));
		ins.setIf("goodreputation", ps.getString("goodreputation", "0"));
		ins.setIf("bonuspoints", ps.getString("bonuspoints", "0"));
		ins.setIf("mobile_profile_html", ps.getString("mobile_profile_html", ""));
		ins.setIf("weight", ps.getString("weight", "0"));
		ins.setIf("isneedlogistics", ps.getString("isneedlogistics", "1"));
		ins.setSE("cdate", DbUtil.getDBDateString(db.getDBType()));
		/************************************ 处理基本属性 ************************/
		String base_data = ps.getString("base_res");
		JSONObject base_obj = JSONObject.parseObject(base_data);
		if (base_obj.containsKey("multiattrdata")) {
			// 基本属性,多选暂时不支持,处理逻辑复杂
		}
		if (base_obj.containsKey("attrdata")) {
			// 存在单选和输入
			JSONArray base_arr = base_obj.getJSONArray("attrdata");
			for (int i = 0; i < base_arr.size(); i++) {
				JSONObject obj = base_arr.getJSONObject(i);
				// 基本属性，不包括多选处理
				if (obj.getString("attr_type").equals(CategoryAttrService.ATTR_TYPE_BASE)
						&& !obj.getString("input_type").equals(CategoryAttrService.INPUTTYPE_SEL_MULTI)) {
					// 校验是否必须
					if (obj.getString("is_need").equals("Y")) {
						if (!obj.containsKey("attr_set_value")) {
							return ResData.FAILURE("请输入属性:" + obj.getString("name"));
						}
						if (obj.getString("attr_set_value").trim().equals("")) {
							return ResData.FAILURE("请输入属性:" + obj.getString("name"));
						}
					}
					Insert basins = new Insert("product_attr_set");
					basins.set("id", db.getUUID());
					basins.set("spu", spu);
					basins.set("is_sku", "N");
					basins.set("attr_id", obj.getString("attr_id"));
					if (obj.getString("input_type").equals(CategoryAttrService.INPUTTYPE_INPUT)) {
						// 处理输入
						basins.set("value", obj.getString("attr_set_value"));
					} else if (obj.getString("input_type").equals(CategoryAttrService.INPUTTYPE_SEL_SINGLE)) {
						// 处理单选
						basins.set("attr_set_id", obj.getString("attr_set_value"));
					}
					basesql.add(basins.getSQL());
				}
			}
		}
		/********** 处理销售属性(必须存在) *****/
		String sale_data = ps.getString("sale_res");// 每组SKU数据
		String sale_data_kv = ps.getString("sale_kv");
		JSONArray sale_arr = JSONArray.parseArray(sale_data);
		JSONArray salekv_arr = JSONArray.parseArray(sale_data_kv);
		ResData res = getAddProdSkuSqls(spu, sale_arr, salekv_arr);
		if (res.isSuccess()) {
			JSONArray rs_sqls = ((JSONObject) res.getData()).getJSONArray("sqls");
			for (int i = 0; i < rs_sqls.size(); i++) {
				basesql.add(rs_sqls.get(i).toString());
			}
			totalStock = ((JSONObject) res.getData()).getIntValue("stock");
		} else {
			return res;
		}
		ins.set("stock", totalStock);
		/************************************ 处理图片 ************************/
		String prod_sales = ps.getString("pics");// 每组SKU数据
		JSONArray prod_sales_arr = JSONArray.parseArray(prod_sales);
		if (ToolUtil.isEmpty(prod_sales_arr) || prod_sales_arr.size() == 0) {
			return ResData.FAILURE("请选择产品图片");
		}
		ArrayList<SQL> picssqls = updateProdPics(spu, prod_sales_arr);
		/************************************ 更新数据 ************************/
		basesql.add(ins.getSQL());
		db.executeStringList(basesql);
		db.executeSQLList(picssqls);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:更新产品图片
	 */
	public ArrayList<SQL> updateProdPics(String spu, JSONArray pics) {
		ArrayList<SQL> res = new ArrayList<SQL>();
		Delete dls=new Delete();
		dls.from("product_pic");
		dls.where().and("type=?",IAMGE_TYPE_PROD).and("spu=?",spu);
		res.add(dls);
		for (int i = 0; i < pics.size(); i++) {
			Insert ins = new Insert("product_pic");
			ins.set("id", ToolUtil.getUUID());
			ins.set("spu", spu);
			ins.setIf("pic_id", pics.getJSONObject(i).getString("pic_id"));
			ins.set("type", IAMGE_TYPE_PROD);
			ins.setIf("od", ConvertUtil.toInt(pics.getJSONObject(i).getString("od"), 1));
			res.add(ins);
		}
		return res;
	}

	/**
	 * @Description:根据产品Id获取销售属性(商城)
	 */
	public JSONArray queryProdSaleBySpuForMall(String spu) {
		JSONArray res = new JSONArray();
		String sql = "select distinct c.attr_id ,a.name ,a.od,b.cat_id from product_category_attr a,product b,product_attr_set c where a.cat_id=b.cat_id and  b.spu=? and c.spu=b.spu and c.is_sku='Y' and c.attr_id=a.attr_id order by a.od";
		RcdSet rs = db.query(sql, spu);
		for (int i = 0; i < rs.size(); i++) {
			JSONObject e = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.getRcd(i).toJsonObject());
			String esql = "select a.value,a.attr_set_id,a.od from product_category_attr_set a,product_attr_set b where cat_id=? and a.attr_set_id=b.attr_set_id and b.is_sku='Y' and b.attr_id=?  and b.spu=? order by a.od";
			e.put("childscurgoods",
					ConvertUtil.OtherJSONObjectToFastJSONArray(
							db.query(esql, rs.getRcd(i).getString("cat_id"), rs.getRcd(i).getString("attr_id"), spu)
									.toJsonArrayWithJsonObject()));
			res.add(e);
		}
		return res;
	}

	/**
	 * @Description:产品销售属性选择后获取sku详细数据,sku数据如果不存在则请重新下单或加入购物车
	 */
	public ResData queryProdSkuDetail(String spu, String propertyChildIds) {
		String ids = "";
		String[] items = propertyChildIds.split(",");
		int cnt = items.length;
		for (int i = 0; i < items.length; i++) {
			String[] tmpstr = items[i].split(":");
			if (tmpstr.length == 2) {
				ids = ids + tmpstr[1] + ",";
			}
		}
		ids = ids.substring(0, ids.length() - 1);
		String sql = "select * from product_sku a,(select sku from (select sku,count(1) cnt from product_sku_map where spu=? and attr_set_id in ("
				+ ids + ") group by sku ) where cnt=?) b where a.sku=b.sku and a.spu=?";
		return ResData.SUCCESS_OPER(db.uniqueRecord(sql, spu, cnt, spu).toJsonObject());
	}

	/**
	 * @Description:根据产品Id查询产品信息[微商城]
	 */
	public ResData queryProdBySpuForMall(String spu) {
		JSONObject res = new JSONObject();
		res.put("basicinfo",
				ConvertUtil.OtherJSONObjectToFastJSONObject(db.uniqueRecord(
						"select a.*,(select name from product_brand where brand_id=a.brand_id) brand_name from product a where spu=?",
						spu).toJsonObject()));
		res.put("pics", getProdPics(spu));
		res.put("properties", queryProdSaleBySpuForMall(spu));
		return ResData.SUCCESS_OPER(res);

	}

	/**
	 * @Description:根据产品SPU和SKU获取数据[微商城]
	 */
	public ResData queryProdBySpuSkuForMall(String spu, String sku) {
		String sql = "select * from product_sku where spu=? and sku=?";
		Rcd rs = db.uniqueRecord(sql, spu, sku);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		}
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}

	/**
	 * @Description:根据产品SPU获取数据[微商城]
	 */
	public ResData queryProdBySpuNotSkuForMall(String spu) {
		String sql = "select * from product_sku where spu=?";
		return ResData.SUCCESS_OPER(db.uniqueRecord(sql).toJsonObject());
	}

}
