package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
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

	@ResponseBody
	@Acl(info = "查询获取所有可用的品类")
	@RequestMapping("/categoryB/prodPublishCatList.do")
	public R prodPublishCatList() {
		return categoryBService.queryAllProdCatList();
	}

	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "按照前端树查询节点数据")
	@RequestMapping("/categoryB/queryTreeList.do")
	public R categoryBqueryTreeList() {
		return R.SUCCESS_OPER(categoryBService.queryCategoryBTreeList());
	}

	@ResponseBody
	@Acl(info = "删除节点")
	@RequestMapping("/categoryB/delete.do")
	public R categoryBdelete(String id) {
		return categoryBService.deleteCategoryB(id);
	}

	@ResponseBody
	@Acl(info = "查询属性的所有属性值")
	@RequestMapping("/categoryB/catAttrValueQuery.do")
	public R catAttrValueQuery(String attr_id) {
		return categoryAttrValueService.queryAttrValue(attr_id);
	}

	@ResponseBody
	@Acl(info = "根据Id查询单个属性")
	@RequestMapping("/categoryB/catAttrQueryById.do")
	public R catAttrQueryById(String id) {
		return categoryAttrService.queryAttrById(id);
	}

	@ResponseBody
	@Acl(info = "新增属性值")
	@RequestMapping("/categoryB/catAttrValueAdd.do")
	public R catAttrValueAdd() throws IOException {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrValueService.addAttrValue(ps);
	}

	@ResponseBody
	@Acl(info = "根据属性值修改")
	@RequestMapping("/categoryB/catAttrValueUpdate.do")
	public R catAttrValueUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrValueService.updateAttrValue(ps);
	}

	@ResponseBody
	@Acl(info = "删除属性值,直接删除")
	@RequestMapping("/categoryB/catAttrValueDel.do")
	public R catAttrValueDel(String ID) {
		return categoryAttrValueService.deleteAttrValue(ID);
	}

	@ResponseBody
	@Acl(info = "添加属性")
	@RequestMapping("/categoryB/catAttrAdd.do")
	public R catAttrAdd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrService.addAttr(ps);
	}

	@ResponseBody
	@Acl(info = "如果该属性没有使用,直接删除")
	@Transactional
	@RequestMapping("/categoryB/catAttrDel.do")
	public R catAttrDel(String id) {
		return categoryAttrService.deleteAttr(id);
	}

	@ResponseBody
	@Acl(info = "更新属性")
	@RequestMapping("/categoryB/catAttrUpdate.do")
	public R catAttrUpdate() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryAttrService.updateAttr(ps);
	}

	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询品类的根据属性定")
	@RequestMapping("/categoryB/catAttrQuery.do")
	public R catAttrQuery(String cat_id) throws IOException {
		return categoryAttrService.queryAttr(cat_id);
	}

	@ResponseBody
	@Acl(info = "无")
	@RequestMapping("/categoryB/update.do")
	public R categoryBupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return R.SUCCESS();
	}

	@ResponseBody
	@Acl(info = "重命名节点")
	@RequestMapping("/categoryB/rename.do")
	public R categoryBrename(String id, String text) throws IOException {
		if (ToolUtil.isOneEmpty(id, text)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		if ("0".equals(id)) {
			return R.FAILURE("根节点不允许修改");
		}
		return categoryBService.renameCategoryB(id, text);
	}

	@ResponseBody
	@Acl(info = "添加节点")
	@RequestMapping("/categoryB/add.do")
	public R categoryBadd() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.addCategoryB(ps);
	}

	@ResponseBody
	@Acl(info = "获取产品属性")
	@RequestMapping("/categoryB/prodPublishCatAttrList.do")
	public R prodPublishCatAttrList() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return categoryBService.publishCatAttrList(ps);
	}
}
