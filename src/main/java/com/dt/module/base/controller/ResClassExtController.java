package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.IResClassService;
import com.dt.module.ct.service.ContentCategoryService;

/**
 * @author: algernonking
 * @date: Dec 31, 2018 2:14:26 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/base/resClass")
public class ResClassExtController extends BaseController {

	@Autowired
	IResClassService ResClassServiceImpl;

	@Autowired
	ContentCategoryService contentCategoryService;
//	@Autowired
//	ResClassExtServiceImpl resClassExtServiceImpl;

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

		if (ToolUtil.isEmpty(class_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();

		}
		String sql = "select b.* from ct_class_item a,res_class b  where a.class_id=? and  a.value=b.class_id and a.dr='0' and b.dr='0' ";
		return R.SUCCESS_OPER(db.query(sql, class_id).toJsonArrayWithJsonObject());

	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/delResClass.do")
	public R delResClass(String id) {

		if (ToolUtil.isNotEmpty(id)) {
			String sql = "select count(1) cnt from res_class_attrs where class_id=? and dr='0' ";
			if (db.uniqueRecord(sql, id).getInteger("cnt") > 0) {
				return R.FAILURE("请先删除属性项目 ");
			} else {
				ResClassServiceImpl.removeById(id);
				return R.SUCCESS_OPER();
			}
		} else {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

	}

}
