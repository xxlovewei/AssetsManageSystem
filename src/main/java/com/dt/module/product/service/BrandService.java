package com.dt.module.product.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月9日 上午11:12:59
 * @Description: TODO
 */
@Service
public class BrandService extends BaseService {
	
	/**
	 * @Description:查询所有品牌
	 */
	public ResData queryBrand() {
		return ResData.SUCCESS(
				db.query("select * from PRODUCT_BRAND where IS_DELETED='N' order by od ").toJsonArrayWithJsonObject());
	}
	
	/**
	 * @Description:删除品牌
	 */
	public ResData deleteBrand(String brand_id) {
		Update ups = new Update("PRODUCT_BRAND");
		ups.set("is_deleted", "Y");
		ups.where().and("brand_id=?", brand_id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	
	/**
	 * @Description:添加品牌
	 */
	public ResData addBrand(TypedHashMap<String, Object> ps) {
		Insert ins = new Insert("PRODUCT_BRAND");
		ins.set("BRAND_ID", db.getUUID());
		ins.setIf("BRAND_CODE", ps.getString("BRAND_CODE"));
		ins.setIf("NAME", ps.getString("NAME"));
		ins.setIf("IS_DELETED", "N");
		ins.setIf("MARK", ps.getString("MARK"));
		ins.setIf("OD", ps.getString("OD"));
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:修改品牌
	 */
	public ResData updateBrand(TypedHashMap<String, Object> ps) {
		String id = ps.getString("BRAND_ID");
		Update ups = new Update("PRODUCT_BRAND");
		ups.setIf("BRAND_CODE", ps.getString("BRAND_CODE"));
		ups.setIf("NAME", ps.getString("NAME"));
		ups.setIf("MARK", ps.getString("MARK"));
		ups.setIf("OD", ps.getString("OD"));
		ups.where().and("brand_id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:获取某个品牌详情
	 */
	public ResData queryBrandById(String brand_id) {
		return ResData
				.SUCCESS(db.uniqueRecord("select * from PRODUCT_BRAND where IS_DELETED='N' and brand_id=? ", brand_id)
						.toJsonObject());
	}
	
	
}
