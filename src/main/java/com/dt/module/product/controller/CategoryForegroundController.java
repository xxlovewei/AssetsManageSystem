package com.dt.module.product.controller;

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
import com.dt.module.product.service.CategoryFRootService;
import com.dt.module.product.service.CategoryFService;

@Controller
@RequestMapping("/api")
public class CategoryForegroundController extends BaseController {

	@Autowired
	private CategoryFRootService categoryFRootService;
	@Autowired
	private CategoryFService categoryFService;

	@ResponseBody
	@Acl(info = "添加根节点")
	@RequestMapping("/categoryF/rootCatAdd.do")
	public R rootCatAdd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFRootService.addCategoryFRoot(ps);
	}

	@ResponseBody
	@Acl(info = "删除根节点")
	@RequestMapping("/categoryF/rootCatDel.do")
	public R rootCatDel(String id) {
		return categoryFRootService.deleteCategoryFRoot(id);
	}

	@ResponseBody
	@Acl(info = "更新根节点")
	@RequestMapping("/categoryF/rootCatUpdate.do")
	public R rootCatUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFRootService.updateCategoryFRoot(ps);
	}

	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询根节点")
	@RequestMapping("/categoryF/rootCatQuery.do")
	public R rootCatQuery() {
		return categoryFRootService.queryCategoryFRoot();
	}

	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "根据Id查询根节点")
	@RequestMapping("/categoryF/rootCatQueryById.do")
	public R rootCatQueryById(String id) {
		return categoryFRootService.queryCategoryFRootByid(id);
	}

	@ResponseBody
	@Acl(info = "查询节点")
	@RequestMapping("/categoryF/queryTreeList.do")
	public R categoryFqueryTreeList(String root_id) {
		if (ToolUtil.isEmpty(root_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		} else {
			return R.SUCCESS_OPER(categoryFService.queryCategoryFTreeList(root_id));
		}
	}

	@ResponseBody
	@Acl(info = "添加节点")
	@RequestMapping("/categoryF/add.do")
	public R categoryFadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryFService.addCategoryF(ps);
	}

	@ResponseBody
	@Acl(info = "重命名节点")
	@RequestMapping("/categoryF/rename.do")
	public R categoryBrename(String id, String text) {
		return categoryFService.renameCategoryF(id, text);
	}
}
