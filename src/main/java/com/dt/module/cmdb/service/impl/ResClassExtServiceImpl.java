package com.dt.module.cmdb.service.impl;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Jan 1, 2019 9:01:39 PM
 * @Description: TODO
 */
@Service
public class ResClassExtServiceImpl extends BaseService {

	
	
	public R queryResByConfItem(String id) {

		if (ToolUtil.isNotEmpty(id)) {
			// 获取属性
			String attrsql = "select * from res_class_attrs where class_id='" + id + "' and dr='0'";
			String sql = "select";
			RcdSet attrs_rs = db.query(attrsql);
			for (int i = 0; i < attrs_rs.size(); i++) {
				String valsql = "";
				if (attrs_rs.getRcd(i).getString("attr_type").equals("number")) {
					valsql = "to_number(attr_value)";
				} else {
					valsql = "attr_value";
				}
				sql = sql + " (select " + valsql
						+ " from res_attr_value i where i.dr=0 and i.res_id=t.id and i.attr_id='"
						+ attrs_rs.getRcd(i).getString("attr_id") + "') \"" + attrs_rs.getRcd(i).getString("attr_code")
						+ "\",  ";
			}
			sql = sql + " (select name from sys_dict_item where dict_item_id=t.pinp and rownum<2) pinpstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.loc and rownum<2) locstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.status and rownum<2) statusstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.env and rownum<2) envstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.mainlevel and rownum<2) mainlevelstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.company and rownum<2) companystr,"
					+ " (select name from sys_dict_item where dict_item_id=t.pinp and rownum<2) pinpstr2,";
			sql = sql + " t.* from res t where dr=0 and class_id='" + id + "' ";
			return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
		} else {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
	}

}
