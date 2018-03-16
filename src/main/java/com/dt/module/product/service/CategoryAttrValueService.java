package com.dt.module.product.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年9月9日 下午9:28:37
 * @Description: TODO
 */
@Service
public class CategoryAttrValueService extends BaseService {
	/**
	 * @Description: 新增属性值
	 */
	public R addAttrValue(TypedHashMap<String, Object> ps) {
		String attr_id = ps.getString("attr_id");
		// 在product_category_attr_set表中保持在一个品类中唯一,为了后期如果做所有产品的同一个属性的值的ID一致预留,预留ID必须在10000以下
		String sql = "select ( select case when max(attr_set_id) is null then 10000 else max(attr_set_id)+1 end value from product_category_attr_set) next_attr_set_id, a.* from product_category_attr a where is_deleted='N' and attr_id=?";
		Rcd rs = db.uniqueRecord(sql, attr_id);
		if (ToolUtil.isEmpty(rs)) {
			return R.FAILURE_NO_DATA();
		}
		String next_attr_set_id = rs.getString("next_attr_set_id");
		String cat_id = rs.getString("cat_id");
		Insert ins = new Insert("product_category_attr_set");
		ins.set("id", db.getUUID());
		ins.set("attr_set_id", next_attr_set_id);
		ins.set("is_deleted", "N");
		ins.set("attr_id", attr_id);
		ins.setIf("cat_id", cat_id);
		ins.setIf("od", ps.getString("od", "0"));
		ins.setIf("value", "新选项值");
		db.execute(ins);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 删除属性值,直接删除
	 */
	public R deleteAttrValue(String id) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		// 直接删除,如果后期更新产品,则根据新的属性值来
		Update ups = new Update("product_category_attr_set");
		ups.set("is_deleted", "Y");
		ups.where().and("id=?", id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询属性的所有属性值
	 */
	public R queryAttrValue(String attr_id) {
		if (ToolUtil.isEmpty(attr_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		String sql = "select * from product_category_attr_set where is_deleted='N' and attr_id=? order by od";
		return R.SUCCESS_OPER(db.query(sql, attr_id).toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 根据Id查询属性值
	 */
	public R queryAttrValueById() {
		return null;
	}

	/**
	 * @Description: 根据属性值修改
	 */
	public R updateAttrValue(TypedHashMap<String, Object> ps) {
		String attr_set_id = ps.getString("id");
		if (ToolUtil.isEmpty(attr_set_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Update me = new Update("product_category_attr_set");
		me.setIf("value", ps.getString("value"));
		me.setIf("od", ps.getString("od"));
		me.where().and("id=?", attr_set_id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}
}
