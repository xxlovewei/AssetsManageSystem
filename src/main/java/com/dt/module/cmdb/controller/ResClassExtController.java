package com.dt.module.cmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.ct.service.ContentCategoryService;

/**
 * @author: algernonking
 * @date: Dec 31, 2018 2:14:26 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/cmdb/resClass")
public class ResClassExtController extends BaseController {

	@Autowired
	ContentCategoryService contentCategoryService;

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryCategoryChildren.do")
	public R queryCategoryChildren() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String parentId = ps.getString("parent_id");
		String isAction = ps.getString("is_action");
		return contentCategoryService.queryCategoryChildren(parentId, isAction);

	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryConfItemByCategory.do")
	public R queryConfItemByCategory(String class_id) {

		if (ToolUtil.isNotEmpty(class_id)) {
			String sql = "select b.* from ct_class_item a,res_class b  where a.class_id='" + class_id
					+ "' and  a.value=b.class_id and a.dr='0' and b.dr='0' ";
			return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
		} else {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResByConfItem.do")
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
