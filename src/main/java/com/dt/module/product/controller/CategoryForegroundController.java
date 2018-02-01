package com.dt.module.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.dao.util.TypedHashMap;
import com.dt.module.product.service.CategoryFRootService;
import com.dt.module.product.service.CategoryFService;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

 
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
	public ResData rootCatAdd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFRootService.addCategoryFRoot(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/rootCatDel.do")
	public ResData rootCatDel(String id) {
		return categoryFRootService.deleteCategoryFRoot(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/rootCatUpdate.do")
	public ResData rootCatUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFRootService.updateCategoryFRoot(ps);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryF/rootCatQuery.do")
	public ResData rootCatQuery() {
		return categoryFRootService.queryCategoryFRoot();
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryF/rootCatQueryById.do")
	public ResData rootCatQueryById(String id) {
		return categoryFRootService.queryCategoryFRootByid(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/queryTreeList.do")
	public ResData categoryFqueryTreeList(String root_id)  {
		if (ToolUtil.isEmpty(root_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		} else {
			return ResData.SUCCESS_OPER(categoryFService.queryCategoryFTreeList(root_id));
		}
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/add.do")
	public ResData categoryFadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFService.addCategoryF(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryF/rename.do")
	public ResData categoryBrename(String id, String text)  {
		return categoryFService.renameCategoryF(id, text);
	}
}
