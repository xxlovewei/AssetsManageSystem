package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ClassService;

/**
 * @author: algernonking
 * @date: 2017年11月17日 上午9:37:26
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ClassController extends BaseController {

	@Autowired
	ClassService classService;

	@RequestMapping(value = "/class/saveClass.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "编辑分类")
	public ResData saveClass() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("class_id"))) {
			return classService.addClass(ps);
		}
		return classService.updateClass(ps);
	}

	@RequestMapping(value = "/class/deleteClass.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "删除分类")
	public ResData deleteClass(String class_id) {
		return classService.deleteClass(class_id);
	}

	@RequestMapping(value = "/class/queryClass.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询分类")
	public ResData queryClass(String class_id, String type, String is_used) {
		return classService.queryClass(class_id, type, is_used);
	}

	@RequestMapping(value = "/class/queryClassById.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询单条分类")
	public ResData queryClassById(String class_id) {
		return classService.queryClassById(class_id);
	}

	@RequestMapping(value = "/class/saveClassItem.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "编辑分类条目")
	public ResData saveClassItem() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("id"))) {
			return classService.addClassItem(ps);
		}
		return classService.updateClassItem(ps);

	}

	@RequestMapping(value = "/class/addClassItems.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "批量增加分类条目")
	public ResData addClassItems() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return classService.addClassItems(ps);

	}

	@RequestMapping(value = "/class/deleteClassItem.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "删除分类条目")
	public ResData deleteClassItem(String id) {
		return classService.deleteClassItem(id);
	}

	@RequestMapping(value = "/class/queryClassItem.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询分类条目")
	public ResData queryClassItem(String class_id, String is_used) {
		return classService.queryClassItem(class_id, is_used);
	}

	@RequestMapping(value = "/class/queryClassItemById.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询单条分类条目")
	public ResData queryClassItemById(String id) {
		return classService.queryClassItemById(id);
	}

}
