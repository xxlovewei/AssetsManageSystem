package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年11月17日 下午1:57:54
 * @Description: TODO
 */
@Service
public class MallProdService extends BaseService {

	public int queryClassProdNotSelCount(String cat_id, String class_id) {

		if (ToolUtil.isOneEmpty(cat_id, class_id)) {
			return 0;
		}
		String sql = "select * from product a where cat_id='" + cat_id
				+ "' and not exists (select * from sys_ct_class_item b where b.value=a.spu and class_id='" + class_id
				+ "')";

		sql = "select count(1) cnt from (" + sql + ")";
		return db.uniqueRecord(sql).getInteger("cnt");
	}

	public ResData queryClassProdNotSel(String cat_id, String class_id, int pageSize, int pageIndex) {
		if (ToolUtil.isOneEmpty(cat_id, class_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		String sql = "select * from product a where cat_id='" + cat_id
				+ "' and is_deleted='N' and not exists (select * from sys_ct_class_item b where b.value=a.spu and class_id='"
				+ class_id + "')";
		return ResData.SUCCESS_OPER(
				db.query(DBUtil.getDBPageSql(db.getDBType(), sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}

	private String queryClassProdSql(TypedHashMap<String, Object> ps, String class_id) {

		String sql = "select b.id item_id, a.* from product a,sys_ct_class_item b where a.spu=b.value and a.is_deleted='N' ";

		if (ToolUtil.isNotEmpty(class_id)) {
			sql = sql + " and class_id='" + class_id + "'";
		}
		if (ToolUtil.isNotEmpty(ps.getString("is_used"))) {
			sql = sql + " and is_used='" + ps.getString("is_used") + "'";
		}
		if (ToolUtil.isNotEmpty(ps.getString("is_off"))) {
			sql = sql + " and is_off='" + ps.getString("is_off") + "'";
		}
		return sql;
	}

	public int queryClassProdCount(TypedHashMap<String, Object> ps, String class_id) {
		String sql = "select count(1) cnt from (" + queryClassProdSql(ps, class_id) + ")";
		return db.uniqueRecord(sql).getInteger("cnt");
	}

	public ResData queryClassProd(TypedHashMap<String, Object> ps, String class_id, int pageSize, int pageIndex) {
		String sql = queryClassProdSql(ps, class_id);
		return ResData.SUCCESS_OPER(
				db.query(DBUtil.getDBPageSql(db.getDBType(), sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}

}
