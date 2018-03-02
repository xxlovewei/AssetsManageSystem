package com.dt.module.product.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

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
	public R addCategoryFRoot(TypedHashMap<String, Object> ps) {
		Insert ins = new Insert("product_cat_user_root");
		String code = ps.getString("code");
		if (ToolUtil.isEmpty(code)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		if (db.uniqueRecord(" select count(1) value from product_cat_user_root where is_deleted='N' and code=? ", code)
				.getInteger("value") > 0) {
			return R.FAILURE("该编码已使用");
		}
		ins.set("id", getNextUserCatId());
		ins.set("is_deleted", "N");
		ins.set("is_used", ps.getString("is_used"));
		ins.setIf("text", ps.getString("text"));
		ins.setIf("code", ps.getString("code"));
		ins.setIf("mark", ps.getString("mark"));
		db.execute(ins);
		return R.SUCCESS_OPER();
	}
	public R deleteCategoryFRoot(String id) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Update me = new Update("product_cat_user_root");
		me.set("is_deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}
	public R queryCategoryFRoot() {
		String sql = "select * from product_cat_user_root where is_deleted='N' order by od";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	public R updateCategoryFRoot(TypedHashMap<String, Object> ps) {
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		// 更新的不允许更新code
		Update ups = new Update("product_cat_user_root");
		ups.set("is_used", ps.getString("is_used"));
		ups.setIf("text", ps.getString("text"));
		ups.setIf("mark", ps.getString("mark"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}
	public R queryCategoryFRootByid(String id) {
		String sql = "select * from product_cat_user_root where is_deleted='N' and id=? ";
		return R.SUCCESS_OPER(db.uniqueRecord(sql, id).toJsonObject());
	}
}
