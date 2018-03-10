package com.dt.module.base.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.content.service.ContentCategoryService;
import com.dt.module.base.content.service.ContentRootCategoryService;

/**
 * @author: algernonking
 * @date: 2017年8月11日 上午11:29:31
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class ContentCategoryController extends BaseController {
	@Autowired
	ContentRootCategoryService contentRootCategoryService;
	@Autowired
	ContentCategoryService contentCategoryService;

	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/queryRootCategory.do")
	public R queryRootCategory() {
		return contentRootCategoryService.queryRootCategory();
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/queryCategoryById.do")
	public R queryCategoryById(String id) {
		return contentCategoryService.queryCategoryById(id);
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/deleteCategory.do")
	public R deleteCategory(String id) {
		return contentCategoryService.deleteCategory(id);
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/addCategory.do")
	public R addCategory() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return contentCategoryService.addCategory(ps);
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/updateCategory.do")
	public R updateCategory() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return contentCategoryService.updateCategory(ps);
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/queryCategory.do")
	public R queryCategory(String root) {
		return contentCategoryService.queryCategory(root);
	}
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/ctCategroy/queryCategoryChildren.do")
	public R queryCategoryChildren() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String parentId = ps.getString("parent_id");
		String isAction = ps.getString("is_action");
		return contentCategoryService.queryCategoryChildren(parentId, isAction);
	}
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/ctCategroy/queryCategoryFirstFloor.do")
	public R queryCategoryFirstFloor() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String rootId = ps.getString("root");
		String isAction = ps.getString("is_action");
		return contentCategoryService.queryCategoryFirstFloor(rootId, isAction);
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/ctCategroy/queryCategoryTreeList.do")
	public R queryCategoryTreeList(String root) {
		return contentCategoryService.queryCategoryTreeList(root);
	}
}
