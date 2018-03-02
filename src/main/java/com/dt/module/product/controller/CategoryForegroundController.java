package com.dt.module.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.product.service.CategoryFRootService;
import com.dt.module.product.service.CategoryFService;

 
@Controller
@RequestMapping("/api")
public class CategoryForegroundController extends BaseController {
	 
	@Autowired
	private CategoryFRootService categoryFRootService;
	@Autowired
	private CategoryFService categoryFService;

	@Res
	@Acl
	@RequestMapping("/categoryF/rootCatAdd.do")
	public R rootCatAdd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFRootService.addCategoryFRoot(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/rootCatDel.do")
	public R rootCatDel(String id) {
		return categoryFRootService.deleteCategoryFRoot(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/rootCatUpdate.do")
	public R rootCatUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFRootService.updateCategoryFRoot(ps);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryF/rootCatQuery.do")
	public R rootCatQuery() {
		return categoryFRootService.queryCategoryFRoot();
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryF/rootCatQueryById.do")
	public R rootCatQueryById(String id) {
		return categoryFRootService.queryCategoryFRootByid(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/queryTreeList.do")
	public R categoryFqueryTreeList(String root_id)  {
		if (ToolUtil.isEmpty(root_id)) {
			return R.FAILURE_ERRREQ_PARAMS();
		} else {
			return R.SUCCESS_OPER(categoryFService.queryCategoryFTreeList(root_id));
		}
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/add.do")
	public R categoryFadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFService.addCategoryF(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/rename.do")
	public R categoryBrename(String id, String text)  {
		return categoryFService.renameCategoryF(id, text);
	}
}
