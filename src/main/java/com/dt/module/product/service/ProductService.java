package com.dt.module.product.service;

import java.util.ArrayList;

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
import com.dt.core.common.util.UuidUtil;
import com.dt.core.common.util.support.TypedHashMap;

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
	/**
	 * @Description:获取产品销售属性
	 */
	public JSONArray getProdSaleList(String spu) {
		JSONArray rs = new JSONArray();
		String sql = "select * from PRODUCT_SKU where spu=? ";
		RcdSet r = db.query(sql, spu);
		for (int i = 0; i < r.size(); i++) {
			JSONObject obj = new JSONObject();
			
			obj = ConvertUtil.OtherJSONObjectToFastJSONObject(r.getRcd(i).toJsonObject());
			// 获取属性
			obj.put("ATTR_SET_IDS", db.query("select * from PRODUCT_SKU_MAP where sku=?", r.getRcd(i).getString("sku"))
					.toJsonArrayWithJsonObject());
			rs.add(obj);
		}
		return rs;
	}
	/**
	 * @Description:获取产品sku
	 */
	public JSONArray getProdSkuCombination(String spu) {
		String sql = "select * from PRODUCT_SKU where spu=?";
		RcdSet r = db.query(sql, spu);
		JSONArray rs = new JSONArray();
		for (int i = 0; i < r.size(); i++) {
			JSONObject e = new JSONObject();
			
			e.put(r.getRcd(i).getString("SKU_UUID"), ConvertUtil.OtherJSONObjectToFastJSONObject(r.getRcd(i).toJsonObject()));
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
				+ "PRODUCT_ATTR_SET b,PRODUCT_CATEGORY_ATTR a " + "where " + "a.attr_id=b.attr_id " + "and b.spu=? "
				+ "and b.is_sku='Y' " + "and ATTR_TYPE='sale' "
				+ "and cat_id in (select cat_id from PRODUCT where spu=? ) " + "order by od ";
		RcdSet rs = db.query(sql, spu, spu);
		for (int i = 0; i < rs.size(); i++) {
	
			JSONObject e = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.getRcd(i).toJsonObject());
			// 跟随模版排序
			String isql = "select " + "distinct a.attr_set_id,a.value,od " + "from "
					+ "PRODUCT_ATTR_SET b,PRODUCT_CATEGORY_ATTR_SET a " + "where " + "a.attr_id=b.attr_id "
					+ "and b.spu=? " + "and b.is_sku='Y' " + "and a.ATTR_ID=? "
					+ "and cat_id in (select cat_id from PRODUCT where spu=?) " + "order by od ";
			e.put("LIST", db.query(isql, spu, rs.getRcd(i).getString("attr_id"), spu).toJsonArrayWithJsonObject());
			r.add(e);
		}
		return r;
	}
	/**
	 * @Description:获取商品的基本属性
	 */
	private JSONArray getProdBaseList(String spu, String cat_id) {
		JSONArray rs = new JSONArray();
		String basesql = " " + "select a.*, b.value ,b.attr_set_id from ( "
				+ "select * from PRODUCT_CATEGORY_ATTR where cat_id=? "
				+ "and is_used='Y' and is_deleted='N' and attr_type='base')a "
				+ "left join (  select * from PRODUCT_ATTR_SET  where spu=? and is_sku='N') b "
				+ "on a.ATTR_ID=b.ATTR_ID ";
		RcdSet attr_rs = db.query(basesql, cat_id, spu);
		for (int i = 0; i < attr_rs.size(); i++) {
			
			JSONObject obj = ConvertUtil.OtherJSONObjectToFastJSONObject(attr_rs.getRcd(i).toJsonObject());
			if (attr_rs.getRcd(i).getString("INPUT_TYPE").equals("input")) {
				obj.put("ATTR_SET_VALUE", attr_rs.getRcd(i).getString("value"));
			} else if (attr_rs.getRcd(i).getString("INPUT_TYPE").equals("select-single")) {
				obj.put("ATTR_SET_VALUE", attr_rs.getRcd(i).getString("attr_set_id"));
			}
			if ("Y".equals(attr_rs.getRcd(i).getString("is_enum"))) {
				String isql = "select * from PRODUCT_CATEGORY_ATTR_SET where  is_deleted='N' and attr_id=? and cat_id=? order by od";
				obj.put("LIST",
						db.query(isql, attr_rs.getRcd(i).getString("attr_id"), cat_id).toJsonArrayWithJsonObject());
			} else {
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
		for (int i = 0; i < prod_arr.size(); i++) {
			Update ups = new Update("PRODUCT");
			ups.set("is_deleted", "Y");
			ups.where().and("spu=?", prod_arr.getJSONObject(i).getString("spu"));
			db.execute(ups);
		}
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:按照类目查询产品
	 */
	public ResData queryProdByCat(String cat_id) {
		if (ToolUtil.isEmpty(cat_id)) {
			return ResData.FAILURE("请输入品类ID");
		}
		String sql = "select * from PRODUCT where cat_id=? and is_deleted='N' ";
		RcdSet rs = db.query(sql, cat_id);
		return ResData.SUCCESS(rs.toJsonArrayWithJsonObject());
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
		String sql = "select * from PRODUCT where is_deleted='N' and spu=?";
		Rcd rs = db.uniqueRecord(sql, spu);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE("商品不存在");
		}
		 
		res =ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
		/************** 获得商品基本属性 **************/
		// 用于修改基本属性
		res.put("BASE_ATTR", getProdBaseList(spu, rs.getString("cat_id")));
		// 用于修改商品销售属性
		res.put("SALE_DATA_LIST", getProdSaleList(spu));
		return ResData.SUCCESS(res);
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
		for (int i = 0; i < prod_arr.size(); i++) {
			Update ups = new Update("PRODUCT");
			ups.set("is_off", is_off);
			ups.where().and("spu=?", prod_arr.getJSONObject(i).getString("spu"));
			db.execute(ups);
		}
		return ResData.SUCCESS_OPER();
	}
	
	/**
	 * @Description:更新产品基本属性
	 */
	@Transactional
	public ResData updateProdBaseAttr(TypedHashMap<String, Object> ps) {
		/************************************ 处理主表 ************************/
		ArrayList<String> basesql = new ArrayList<String>();
		String spu = ps.getString("SPU");
		String base_data = ps.getString("BASE_RES");
		Update ups = new Update("product");
		ups.set("prod_name", ps.getString("PROD_NAME"));
		ups.set("list_price", ps.getString("LIST_PRICE"));
		ups.set("list_ori_price", ps.getString("LIST_ORI_PRICE"));
		ups.setIf("code", ps.getString("CODE"));
		ups.setIf("sales", ps.getString("SALES"));
		ups.setIf("prod_desc", ps.getString("PROD_DESC"));
		ups.setIf("brand_id", ps.getString("BRAND_ID"));
		ups.set("unit", ps.getString("UNIT"));
		ups.set("title", ps.getString("TITLE"));
		ups.setIf("place", ps.getString("PLACE"));
		ups.where().and("spu=?", spu);
		basesql.add(ups.getSQL());
		// 处理基本属性
		JSONArray base_arr = JSONArray.parseArray(base_data);
		for (int i = 0; i < base_arr.size(); i++) {
			JSONObject obj = base_arr.getJSONObject(i);
			// 基本属性，不包括多选处理
			if (obj.getString("ATTR_TYPE").equals("base") && !obj.getString("INPUT_TYPE").equals("select-multi")) {
				// 校验
				if (obj.getString("IS_NEED").equals("Y")) {
					if (!obj.containsKey("ATTR_SET_VALUE")) {
						return ResData.FAILURE("请输入属性:" + obj.getString("NAME"));
					}
					if (obj.getString("ATTR_SET_VALUE").length() == 0) {
						return ResData.FAILURE("请输入属性:" + obj.getString("NAME"));
					}
				}
				Insert basins = new Insert("product_attr_set");
				basins.set("id", db.getUUID());
				basins.set("spu", spu);
				basins.set("is_sku", "N");
				basins.set("attr_id", obj.getString("ATTR_ID"));
				if (obj.getString("INPUT_TYPE").equals("input")) {
					basins.set("value", obj.getString("ATTR_SET_VALUE"));
				} else if (obj.getString("INPUT_TYPE").equals("select-single")) {
					basins.set("attr_set_id", obj.getString("ATTR_SET_VALUE"));
				}
				basesql.add(basins.getSQL());
			}
		}
		//
		db.execute("delete from PRODUCT_ATTR_set  where is_sku='N' and spu=?", spu);
		for (int i = 0; i < basesql.size(); i++) {
			db.execute(basesql.get(i));
		}
		return ResData.SUCCESS_OPER();
	}
	
	/**
	 * @Description:获取商品的销售属性
	 */
	@Transactional
	public ResData updateProdSaleAttr(TypedHashMap<String, Object> ps) {
		int totalStock = 0;
		String spu = ps.getString("SPU");
		String rebuild = ps.getString("REBUILD");
		String sale_data = ps.getString("SALE_RES");
		String sale_data_kv = ps.getString("SALE_KV");
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
				ups.setIf("code", sale_obj.getString("CODE"));
				if (ToolUtil.isOneEmpty(sale_obj.getString("PRICE"), sale_obj.getString("PRICE"))) {
					return ResData.FAILURE("请输入正确的价格或库存数");
				}
				int stock = sale_obj.getIntValue("STOCK");
				int price = sale_obj.getIntValue("PRICE");
				ups.set("stock", stock);
				ups.set("price", price);
				totalStock = totalStock + stock;
				ups.where().and("sku=?", sale_obj.getString("SKU"));
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
		for (int j = 0; j < sqls.size(); j++) {
			db.execute(sqls.get(j));
		}
		return ResData.SUCCESS_OPER();
	}
	
	/**
	 * @Description:获取更新商品规格的语句
	 */
	private ResData getAddProdSkuSqls(String spu, JSONArray sale_arr, JSONArray salekv_arr) {
		// product_sku sku记录表
		// product_sku_map sku的attr_id列表
		// product_attr_set
		JSONObject res = new JSONObject();
		JSONArray sqls = new JSONArray();
		int totalStock = 0;
		for (int i = 0; i < sale_arr.size(); i++) {
			JSONObject sale_obj = sale_arr.getJSONObject(i);
			String skuuuid = "";
			String sku = UuidUtil.getUUID();
			Insert sale_ins = new Insert("product_sku");
			sale_ins.set("spu", spu);
			sale_ins.set("sku", sku);
			sale_ins.setIf("is_off", "N");
			sale_ins.setIf("code", sale_obj.getString("CODE"));
			if (ToolUtil.isOneEmpty(sale_obj.getString("PRICE"), sale_obj.getString("PRICE"))) {
				return ResData.FAILURE("请输入正确的价格或库存数");
			}
			int stock = sale_obj.getIntValue("STOCK");
			sale_ins.set("stock", stock);
			sale_ins.set("price", sale_obj.getIntValue("PRICE"));
			totalStock = totalStock + stock;
			// 获得SKU生成sku详细信息
			JSONArray arrmap = sale_obj.getJSONArray("ATTR_SET_IDS");
			for (int j = 0; j < arrmap.size(); j++) {
				String attr_set_id = arrmap.getJSONObject(j).getString("ATTR_SET_ID");
				skuuuid = skuuuid + "," + attr_set_id;
				Insert saledelins = new Insert("product_sku_map");
				saledelins.set("id", UuidUtil.getUUID());
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
			JSONArray salekv_obj_arr = salekv_obj.getJSONArray("DATA");
			for (int j = 0; j < salekv_obj_arr.size(); j++) {
				Insert ins2 = new Insert("product_attr_set");
				ins2.set("id", UuidUtil.getUUID());
				ins2.set("spu", spu);
				ins2.set("attr_set_id", salekv_obj_arr.getString(j));
				ins2.set("attr_id", salekv_obj.getString("ATTR_ID"));
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
		String spu = db.getUUID();
		String sale_data = ps.getString("SALE_RES");
		String sale_data_kv = ps.getString("SALE_KV");
		String base_data = ps.getString("BASE_RES");
		String cat_id = ps.getString("CAT_ID");
		if (ToolUtil.isEmpty("cat_id")) {
			return ResData.FAILURE("请选择品类");
		}
		/************************************ 处理主表 ************************/
		Insert ins = new Insert("product");
		int totalStock = 0;
		ins.set("SPU", spu);
		ins.set("prod_name", ps.getString("PROD_NAME"));
		ins.set("cat_id", cat_id);
		ins.set("list_price", ps.getString("LIST_PRICE"));
		ins.set("list_ori_price", ps.getString("LIST_ORI_PRICE"));
		ins.setIf("code", ps.getString("CODE"));
		ins.setIf("sales", ps.getString("SALES"));
		ins.set("is_off", "N");
		ins.set("is_deleted", "N");
		ins.setIf("prod_desc", ps.getString("PROD_DESC"));
		ins.setIf("brand_id", ps.getString("BRAND_ID"));
		ins.setIf("master_pic", ps.getString("MASTER_PIC"));
		ins.set("unit", ps.getString("UNIT"));
		ins.set("title", ps.getString("TITLE"));
		ins.setIf("place", ps.getString("PLACE"));
		/************************************ 处理基本属性 ************************/
		ArrayList<String> basesql = new ArrayList<String>();
		JSONObject base_obj = JSONObject.parseObject(base_data);
		if (base_obj.containsKey("multiattrdata")) {
			// 基本属性,多选暂时不支持,处理逻辑太复杂
		}
		if (base_obj.containsKey("attrdata")) {
			// 存在单项和输入
			JSONArray base_arr = base_obj.getJSONArray("attrdata");
			for (int i = 0; i < base_arr.size(); i++) {
				JSONObject obj = base_arr.getJSONObject(i);
				// 基本属性，不包括多选处理
				if (obj.getString("ATTR_TYPE").equals("base") && !obj.getString("INPUT_TYPE").equals("select-multi")) {
					// 校验
					if (obj.getString("IS_NEED").equals("Y")) {
						if (!obj.containsKey("ATTR_SET_VALUE")) {
							return ResData.FAILURE("请输入属性:" + obj.getString("NAME"));
						}
						if (obj.getString("ATTR_SET_VALUE").length() == 0) {
							return ResData.FAILURE("请输入属性:" + obj.getString("NAME"));
						}
					}
					Insert basins = new Insert("product_attr_set");
					basins.set("id", db.getUUID());
					basins.set("spu", spu);
					basins.set("is_sku", "N");
					basins.set("attr_id", obj.getString("ATTR_ID"));
					if (obj.getString("INPUT_TYPE").equals("input")) {
						basins.set("value", obj.getString("ATTR_SET_VALUE"));
					} else if (obj.getString("INPUT_TYPE").equals("select-single")) {
						basins.set("attr_set_id", obj.getString("ATTR_SET_VALUE"));
					}
					basesql.add(basins.getSQL());
				}
			}
		}
		/********** 处理销售属性(必须存在) *****/
		JSONArray sale_arr = JSONArray.parseArray(sale_data);
		JSONArray salekv_arr = JSONArray.parseArray(sale_data_kv);
		ResData res = getAddProdSkuSqls(spu, sale_arr, salekv_arr);
		if (res.isSuccess()){
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
		/************************************ 更新数据 ************************/
		basesql.add(ins.getSQL());
		for (int i = 0; i < basesql.size(); i++) {
			db.execute(basesql.get(i));
		}
		return ResData.SUCCESS_OPER();
	}
}
