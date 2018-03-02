package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.product.service.CategoryAttrService;
import com.dt.module.product.service.CategoryAttrValueService;
import com.dt.module.product.service.CategoryBService;

@Controller
@RequestMapping("/api")
public class CategoryBackgroundController extends BaseController {
	@Autowired
	CategoryBService categoryBService;
	@Autowired
	CategoryAttrService categoryAttrService;
	@Autowired
	CategoryAttrValueService categoryAttrValueService;

	@Res
	@Acl
	@RequestMapping("/categoryB/prodPublishCatList.do")
	public R prodPublishCatList() {
		return categoryBService.queryAllProdCatList();
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/queryTreeList.do")
	public R categoryBqueryTreeList() {
		return R.SUCCESS_OPER(categoryBService.queryCategoryBTreeList());
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/delete.do")
	public R categoryBdelete(String id) {
		return categoryBService.deleteCategoryB(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueQuery.do")
	public R catAttrValueQuery(String attr_id) {
		return categoryAttrValueService.queryAttrValue(attr_id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrQueryById.do")
	public R catAttrQueryById(String id) {
		return categoryAttrService.queryAttrById(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueAdd.do")
	public R catAttrValueAdd() throws IOException {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrValueService.addAttrValue(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueUpdate.do")
	public R catAttrValueUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrValueService.updateAttrValue(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueDel.do")
	public R catAttrValueDel(String ID) {
		return categoryAttrValueService.deleteAttrValue(ID);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrAdd.do")
	public R catAttrAdd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrService.addAttr(ps);
	}
	@Res
	@Acl
	@Transactional
	@RequestMapping("/categoryB/catAttrDel.do")
	public R catAttrDel(String id) {
		return categoryAttrService.deleteAttr(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrUpdate.do")
	public R catAttrUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrService.updateAttr(ps);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/catAttrQuery.do")
	public R catAttrQuery(String cat_id) throws IOException {
		return categoryAttrService.queryAttr(cat_id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/update.do")
	public R categoryBupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return R.SUCCESS();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/rename.do")
	public R categoryBrename(String id, String text) throws IOException {
		if (ToolUtil.isOneEmpty(id, text)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		if (id.equals("0")) {
			return R.FAILURE("根节点不允许修改");
		}
		return categoryBService.renameCategoryB(id, text);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/add.do")
	public R categoryBadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.addCategoryB(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/prodPublishCatAttrList.do")
	// 获取产品属性
	public R prodPublishCatAttrList() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.publishCatAttrList(ps);
	}
}
