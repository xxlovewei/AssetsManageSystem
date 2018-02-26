package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;

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
		return ResData.SUCCESS_OPER(
				db.query("select * from product_brand where is_deleted='N' order by od").toJsonArrayWithJsonObject());
	}
	
	/**
	 * @Description:删除品牌
	 */
	public ResData deleteBrand(String brand_id) {
		Update ups = new Update("product_brand");
		ups.set("is_deleted", "Y");
		ups.where().and("brand_id=?", brand_id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	
	/**
	 * @Description:添加品牌
	 */
	public ResData addBrand(TypedHashMap<String, Object> ps) {
		Insert ins = new Insert("product_brand");
		ins.set("brand_id", db.getUUID());
		ins.setIf("brand_code", ps.getString("brand_code"));
		ins.setIf("name", ps.getString("name"));
		ins.setIf("is_deleted", "N");
		ins.setIf("mark", ps.getString("mark"));
		ins.setIf("od", ps.getString("od"));
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:修改品牌
	 */
	public ResData updateBrand(TypedHashMap<String, Object> ps) {
		String id = ps.getString("brand_id");
		Update ups = new Update("product_brand");
		ups.setIf("brand_code", ps.getString("brand_code"));
		ups.setIf("name", ps.getString("name"));
		ups.setIf("mark", ps.getString("mark"));
		ups.setIf("od", ps.getString("od"));
		ups.where().and("brand_id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description:获取某个品牌详情
	 */
	public ResData queryBrandById(String brand_id) {
		return ResData
				.SUCCESS_OPER(db.uniqueRecord("select * from product_brand where is_deleted='N' and brand_id=? ", brand_id)
						.toJsonObject());
	}
	
	
}
