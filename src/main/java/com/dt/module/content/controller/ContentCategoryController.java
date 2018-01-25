package com.dt.module.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.content.service.ContentCategoryService;
import com.dt.module.content.service.ContentRootCategoryService;
import com.dt.util.support.HttpKit;
import com.dt.util.support.TypedHashMap;

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

	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/queryRootCategory.do")
	public ResData queryRootCategory() {
		return contentRootCategoryService.queryRootCategory();
	}
	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/queryCategoryById.do")
	public ResData queryCategoryById(String id) {
		return contentCategoryService.queryCategoryById(id);
	}
	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/deleteCategory.do")
	public ResData deleteCategory(String id) {
		return contentCategoryService.deleteCategory(id);
	}
	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/addCategory.do")
	public ResData addCategory() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return contentCategoryService.addCategory(ps);
	}
	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/updateCategory.do")
	public ResData updateCategory() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return contentCategoryService.updateCategory(ps);
	}
	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/queryCategory.do")
	public ResData queryCategory(String root) {
		return contentCategoryService.queryCategory(root);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping(value = "/ctCategroy/queryCategoryChildren.do")
	public ResData queryCategoryChildren() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String parentId = ps.getString("parent_id");
		String isAction = ps.getString("is_action");
		return contentCategoryService.queryCategoryChildren(parentId, isAction);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping(value = "/ctCategroy/queryCategoryFirstFloor.do")
	public ResData queryCategoryFirstFloor() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String rootId = ps.getString("root");
		String isAction = ps.getString("is_action");
		return contentCategoryService.queryCategoryFirstFloor(rootId, isAction);
	}
	@Res
	@Acl
	@RequestMapping(value = "/ctCategroy/queryCategoryTreeList.do")
	public ResData queryCategoryTreeList(String root) {
		return contentCategoryService.queryCategoryTreeList(root);
	}
}
