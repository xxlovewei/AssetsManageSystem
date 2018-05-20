package com.dt.module.shop.controller;

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
import com.dt.module.shop.service.DxlService;

/**
 * @author: algernonking
 * @date: 2018年5月18日 下午7:12:35
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class DxlController extends BaseController {
	@Autowired
	ClassService classService;

	// 大类
	@RequestMapping("/prod/queryDl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryDl(String is_used) {
		return classService.queryClass(DxlService.TYPE_DL, is_used);
	}

	@RequestMapping("/prod/saveDl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY)
	public R saveDl() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("class_id");
		if (ToolUtil.isEmpty(id)) {
			return classService.addClass(ps, DxlService.TYPE_DL);
		} else {
			return classService.updateClass(ps, DxlService.TYPE_DL);
		}
	}

	@RequestMapping("/prod/queryDlById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryDlById(String id) {
		return classService.queryClassById(id);
	}

	@RequestMapping("/prod/delDl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY)
	public R delDl(String id) {
		return classService.deleteClass(id);
	}

	// 小类
	@RequestMapping("/prod/queryXl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryXl(String is_used) {
		return classService.queryClass(DxlService.TYPE_XL, is_used);
	}

	@RequestMapping("/prod/saveXl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY)
	public R saveXl() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("class_id");
		if (ToolUtil.isEmpty(id)) {
			return classService.addClass(ps, DxlService.TYPE_XL);
		} else {
			return classService.updateClass(ps, DxlService.TYPE_XL);
		}
	}

	@RequestMapping("/prod/queryXlById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryXlById(String id) {
		return classService.queryClassById(id);
	}

	@RequestMapping("/prod/delXl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY)
	public R delXl(String id) {
		return classService.deleteClass(id);
	}

}
