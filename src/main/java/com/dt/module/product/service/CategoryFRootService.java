package com.dt.module.product.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年9月9日 上午11:33:23
 * @Description: TODO
 */
@Service
public class CategoryFRootService extends BaseService {
	public int getNextUserCatId() {
		String sql = "select case when max(id) is null then 10 else max(id)+1 end value from (select id from product_cat_user_root union all select id from product_cat_user)";
		return db.uniqueRecord(sql).getInteger("value");
	}
	public ResData addCategoryFRoot(TypedHashMap<String, Object> ps) {
		Insert ins = new Insert("product_cat_user_root");
		String code = ps.getString("code");
		if (ToolUtil.isEmpty(code)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (db.uniqueRecord(" select count(1) value from product_cat_user_root where is_deleted='N' and code=? ", code)
				.getInteger("value") > 0) {
			return ResData.FAILURE("该编码已使用");
		}
		ins.set("id", getNextUserCatId());
		ins.set("is_deleted", "N");
		ins.set("is_used", ps.getString("is_used"));
		ins.setIf("text", ps.getString("text"));
		ins.setIf("code", ps.getString("code"));
		ins.setIf("mark", ps.getString("mark"));
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	public ResData deleteCategoryFRoot(String id) {
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update me = new Update("product_cat_user_root");
		me.set("is_deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	public ResData queryCategoryFRoot() {
		String sql = "select * from product_cat_user_root where is_deleted='N' order by od";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	public ResData updateCategoryFRoot(TypedHashMap<String, Object> ps) {
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		// 更新的不允许更新code
		Update ups = new Update("product_cat_user_root");
		ups.set("is_used", ps.getString("is_used"));
		ups.setIf("text", ps.getString("text"));
		ups.setIf("mark", ps.getString("mark"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	public ResData queryCategoryFRootByid(String id) {
		String sql = "select * from product_cat_user_root where is_deleted='N' and id=? ";
		return ResData.SUCCESS_OPER(db.uniqueRecord(sql, id).toJsonObject());
	}
}
