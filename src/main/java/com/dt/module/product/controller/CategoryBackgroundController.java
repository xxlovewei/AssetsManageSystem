package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
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
	public ResData prodPublishCatList() {
		return categoryBService.queryAllProdCatList();
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/queryTreeList.do")
	public ResData categoryBqueryTreeList() {
		return ResData.SUCCESS_OPER(categoryBService.queryCategoryBTreeList());
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/delete.do")
	public ResData categoryBdelete(String id) {
		return categoryBService.deleteCategoryB(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueQuery.do")
	public ResData catAttrValueQuery(String attr_id) {
		return categoryAttrValueService.queryAttrValue(attr_id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrQueryById.do")
	public ResData catAttrQueryById(String id) {
		return categoryAttrService.queryAttrById(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueAdd.do")
	public ResData catAttrValueAdd() throws IOException {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrValueService.addAttrValue(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueUpdate.do")
	public ResData catAttrValueUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrValueService.updateAttrValue(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrValueDel.do")
	public ResData catAttrValueDel(String ID) {
		return categoryAttrValueService.deleteAttrValue(ID);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrAdd.do")
	public ResData catAttrAdd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrService.addAttr(ps);
	}
	@Res
	@Acl
	@Transactional
	@RequestMapping("/categoryB/catAttrDel.do")
	public ResData catAttrDel(String id) {
		return categoryAttrService.deleteAttr(id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/catAttrUpdate.do")
	public ResData catAttrUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrService.updateAttr(ps);
	}
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	@RequestMapping("/categoryB/catAttrQuery.do")
	public ResData catAttrQuery(String cat_id) throws IOException {
		return categoryAttrService.queryAttr(cat_id);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/update.do")
	public ResData categoryBupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResData.SUCCESS();
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/rename.do")
	public ResData categoryBrename(String id, String text) throws IOException {
		if (ToolUtil.isOneEmpty(id, text)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		if (id.equals("0")) {
			return ResData.FAILURE("根节点不允许修改");
		}
		return categoryBService.renameCategoryB(id, text);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/add.do")
	public ResData categoryBadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.addCategoryB(ps);
	}
	@Res
	@Acl
	@RequestMapping("/categoryB/prodPublishCatAttrList.do")
	// 获取产品属性
	public ResData prodPublishCatAttrList() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.publishCatAttrList(ps);
	}
}
