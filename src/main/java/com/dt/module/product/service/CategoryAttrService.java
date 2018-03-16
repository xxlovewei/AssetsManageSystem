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
 * @date: 2017年9月9日 下午9:28:14
 * @Description: TODO
 */
@Service
public class CategoryAttrService extends BaseService {
	public static String INPUTTYPE_INPUT = "input";
	public static String INPUTTYPE_SEL_MULTI = "select-multi";
	public static String INPUTTYPE_SEL_SINGLE = "select-single";
	public static String ATTR_TYPE_SALE = "sale";
	public static String ATTR_TYPE_BASE = "base";
//注意：规格模版属性不能直接删除
	/**
	 * @Description: 添加属性
	 */
	public R addAttr(TypedHashMap<String, Object> ps) {
		String cat_id = ps.getString("cat_id");
		if (ToolUtil.isEmpty(cat_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Rcd cat_rs = db.uniqueRecord("select * from product_category where is_deleted='N' and id=? and is_cat='Y'",
				cat_id);
		if (ToolUtil.isEmpty(cat_rs)) {
			return R.FAILURE("不存在该品类");
		}
		// 添加一条销售属性，每个品类必须要有一个销售属性
		String next_attr_id = getNextAttrId();
		String attr_type = ps.getString("attr_type");
		String input_type = ps.getString("input_type");
		Insert ins = new Insert("product_category_attr");
		ins.set("id", db.getUUID());
		ins.set("attr_id", next_attr_id);
		ins.set("is_deleted", "N");
		ins.set("is_key", "N");
		ins.set("is_need", ps.getString("is_need"));
		ins.set("can_alias", ps.getString("can_alias"));
		ins.set("name", ps.getString("name"));
		ins.set("cat_id", cat_id);
		ins.set("od", ps.getString("od"));
		ins.set("attr_type", attr_type);
		ins.set("is_used", ps.getString("is_used"));
		ins.set("is_search", ps.getString("is_search"));
		if (attr_type.equals(ATTR_TYPE_SALE)) {
			// 如果是销售属性只支持多选枚举
			ins.set("is_input", "N");
			ins.set("is_enum", "Y");
			if (!input_type.equals(INPUTTYPE_SEL_MULTI)) {
				return R.FAILURE("销售属性只支持多选框");
			} else {
				ins.set("input_type", INPUTTYPE_SEL_MULTI);
			}
		} else if (attr_type.equals(ATTR_TYPE_BASE)) {
			// 如果是基本属性,支持输入(键盘输入),单选,不支持多选
			ins.set("input_type", input_type);
			if (input_type.equals(INPUTTYPE_INPUT)) {
				ins.set("is_input", "Y");
				ins.set("is_enum", "N");
			} else if (input_type.equals(INPUTTYPE_SEL_SINGLE)) {
				ins.set("is_input", "N");
				ins.set("is_enum", "Y");
			} else if (input_type.equals(INPUTTYPE_SEL_MULTI)) {
				return R.FAILURE("基本属性暂不支持多选组件");
			} else {
				return R.FAILURE_REQ_PARAM_ERROR();
			}
		} else {
			return R.FAILURE("请选择正确的属性");
		}
		db.execute(ins);
		return R.SUCCESS_OPER();
	}
	/**
	 * @Description: 获取下一个序列号
	 */
	public String getNextAttrId() {
		return db
				.uniqueRecord(
						"select case when max(attr_id) is null then 10 else max(attr_id)+1 end value from product_category_attr")
				.getString("value");
	}
	/**
	 * @Description: 如果该属性没有使用,直接删除
	 */
	public R deleteAttr(String id) {
		int uscnt = db.uniqueRecord(
				"select count(1) value from product_attr_set a,product_category_attr b where a.attr_id=b.attr_id and b.id=? and b.is_deleted='N' ",
				id).getInteger("value");
		if (uscnt > 0) {
			return R.FAILURE("已有产品在使用中,暂不可删除");
		}
		// 如果确实没有使用
		Update ups = new Update("product_category_attr");
		ups.set("is_deleted", "Y");
		ups.where().and("id=?", id);
		// 删除对应的属性值
		Update ups2 = new Update("product_category_attr_set");
		ups2.set("is_deleted", "Y");
		ups2.where().and("attr_id in (select attr_id from product_category_attr where id=?)", id);
		db.executes(ups,ups2);
		return R.SUCCESS_OPER();
	}
	/**
	 * @Description: 更新属性
	 */
	public R updateAttr(TypedHashMap<String, Object> ps) {
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Update ups = new Update("product_category_attr");
		ups.setIf("is_need", ps.getString("is_need"));
		ups.setIf("can_alias", ps.getString("can_alias"));
		ups.setIf("name", ps.getString("name"));
		ups.setIf("od", ps.getString("od"));
		ups.setIf("is_used", ps.getString("is_used"));
		ups.setIf("is_search", ps.getString("is_search"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return R.SUCCESS_OPER();
	}
	/**
	 * @Description: 查询品类的根据属性定义
	 */
	public R queryAttr(String cat_id) {
		if (ToolUtil.isEmpty(cat_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		String sql = "select a.*,case a.attr_type when 'sale' then '销售属性' when 'base' then '基本属性' else '未知' end attr_type_name from product_category_attr a where is_deleted='N' and cat_id=? order by attr_type,od";
		return R.SUCCESS_OPER(db.query(sql, cat_id).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 根据Id查询单个属性
	 */
	public R queryAttrById(String id) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Rcd r = db.uniqueRecord("select * from product_category_attr where id=?", id);
		return R.SUCCESS_OPER(r.toJsonObject());
	}
}
