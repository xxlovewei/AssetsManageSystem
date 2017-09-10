package com.dt.module.product.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

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

	public ResData addAttr(TypedHashMap<String, Object> ps) {
		String cat_id = ps.getString("CAT_ID");
		if (ToolUtil.isEmpty(cat_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Rcd cat_rs = db.uniqueRecord("select * from product_category where is_deleted='N' and id=? and is_cat='Y'",
				cat_id);
		if (ToolUtil.isEmpty(cat_rs)) {
			return ResData.FAILURE("不存在该品类");
		}
		// 添加一条销售属性，每个品类必须要有一个销售属性
		String next_attr_id = getNextAttrId();
		String attr_type = ps.getString("ATTR_TYPE");
		String input_type = ps.getString("INPUT_TYPE");
		Insert ins = new Insert("product_category_attr");
		ins.set("id", db.getUUID());
		ins.set("attr_id", next_attr_id);
		ins.set("is_deleted", "N");
		ins.set("is_key", "N");
		ins.set("is_need", ps.getString("IS_NEED"));
		ins.set("can_alias", ps.getString("CAN_ALIAS"));
		ins.set("name", ps.getString("NAME"));
		ins.set("cat_id", cat_id);
		ins.set("od", ps.getString("OD"));
		ins.set("attr_type", attr_type);
		ins.set("is_used", ps.getString("IS_USED"));
		ins.set("is_search", ps.getString("IS_SEARCH"));
		if (attr_type.equals(ATTR_TYPE_SALE)) {
			// 如果是销售属性只支持多选枚举
			ins.set("is_input", "N");
			ins.set("is_enum", "Y");
			if (!input_type.equals(INPUTTYPE_SEL_MULTI)) {
				return ResData.FAILURE("销售属性只支持多选框");
			} else {
				ins.set("input_type", INPUTTYPE_SEL_MULTI);
			}
		} else if (attr_type.equals(ATTR_TYPE_BASE)) {
			// 如果是基本属性,支持输入(键盘输入),单选,多选
			ins.set("input_type", input_type);
			if (input_type.equals(INPUTTYPE_INPUT)) {
				ins.set("is_input", "Y");
				ins.set("is_enum", "N");
			} else if (input_type.equals(INPUTTYPE_SEL_MULTI) || input_type.equals(INPUTTYPE_SEL_SINGLE)) {
				ins.set("is_input", "N");
				ins.set("is_enum", "Y");
			} else {
				return ResData.FAILURE_ERRREQ_PARAMS();
			}
		} else {
			return ResData.FAILURE("请选择正确的属性");
		}
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	public String getNextAttrId() {
		return db
				.uniqueRecord(
						"select case when max(attr_id) is null then 10 else max(attr_id)+1 end value from product_category_attr")
				.getString("value");
	}
	public ResData deleteAttr(String id) {
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
	public ResData updateAttr(TypedHashMap<String, Object> ps) {
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("product_category_attr");
		ups.setIf("is_need", ps.getString("IS_NEED"));
		ups.setIf("can_alias", ps.getString("CAN_ALIAS"));
		ups.setIf("name", ps.getString("NAME"));
		ups.setIf("od", ps.getString("OD"));
		ups.setIf("is_used", ps.getString("IS_USED"));
		ups.setIf("is_search", ps.getString("IS_SEARCH"));
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	public ResData queryAttr(String cat_id) {
		if (ToolUtil.isEmpty(cat_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		String sql = "select a.*,case a.attr_type when 'sale' then '销售属性' when 'base' then '基本属性' else '未知' end attr_type_name from product_category_attr a where is_deleted='N' and cat_id=60 order by attr_type,od";
		return ResData.SUCCESS_OPER(db.query(sql, cat_id).toJsonArrayWithJsonObject());
	}
	public ResData queryAttrById(String id) {
		if (ToolUtil.isEmpty(id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Rcd r = db.uniqueRecord("select * from product_category_attr where id=?", id);
		return ResData.SUCCESS("操作成功", r.toJsonObject());
	}
}
