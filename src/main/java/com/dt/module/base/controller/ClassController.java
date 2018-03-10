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
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "编辑分类")
	public R saveClass() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("class_id"))) {
			return classService.addClass(ps);
		}
		return classService.updateClass(ps);
	}

	@RequestMapping(value = "/class/deleteClass.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "删除分类")
	public R deleteClass(String class_id) {
		return classService.deleteClass(class_id);
	}

	@RequestMapping(value = "/class/queryClass.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询分类")
	public R queryClass(String class_id, String type, String is_used) {
		return classService.queryClass(class_id, type, is_used);
	}

	@RequestMapping(value = "/class/queryClassById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询单条分类")
	public R queryClassById(String class_id) {
		return classService.queryClassById(class_id);
	}

	@RequestMapping(value = "/class/saveClassItem.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "编辑分类条目")
	public R saveClassItem() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("id"))) {
			return classService.addClassItem(ps);
		}
		return classService.updateClassItem(ps);

	}

	@RequestMapping(value = "/class/addClassItem.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "增加条目")
	public R addClassItem() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return classService.addClassItem(ps);

	}

	@RequestMapping(value = "/class/addClassItems.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "批量增加分类条目")
	public R addClassItems() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return classService.addClassItems(ps);

	}

	@RequestMapping(value = "/class/deleteClassItem.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "删除分类条目")
	public R deleteClassItem(String id) {
		return classService.deleteClassItem(id);
	}

	@RequestMapping(value = "/class/queryClassItem.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询分类条目")
	public R queryClassItem(String class_id, String is_used) {
		return classService.queryClassItem(class_id, is_used);
	}

	@RequestMapping(value = "/class/queryClassItemById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询单条分类条目")
	public R queryClassItemById(String id) {
		return classService.queryClassItemById(id);
	}

}
