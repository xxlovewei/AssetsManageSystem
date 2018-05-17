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
import com.dt.module.base.service.MenuRootService;

@Controller
@RequestMapping(value = "/api")
public class MenuRootController extends BaseController {

	@Autowired
	MenuRootService menuRootService;

	@ResponseBody
	@Acl(info = "查询Root菜单", value = Acl.ACL_DENY)
	@RequestMapping(value = "/menu/queryMenuRoot.do")
	public R queryMenuRoot() {
		return R.SUCCESS_OPER(menuRootService.queryMenuRoot(null));
	}

	@ResponseBody
	@Acl(info = "根据Id查询Root菜单", value = Acl.ACL_DENY)
	@RequestMapping(value = "/menu/queryMenuRootById.do")
	public R queryMenuRootById(String id) {
		return menuRootService.queryMenuRootById(id);
	}

	@ResponseBody
	@Acl(info = "删除Root菜单", value = Acl.ACL_DENY)
	@RequestMapping(value = "/menu/deleteMenuRoot.do")
	public R deleteMenuRoot(String id) {
		return menuRootService.deleteMenuRoot(id);
	}

	@ResponseBody
	@Acl(info = "保存Root菜单", value = Acl.ACL_DENY)
	@RequestMapping(value = "/menu/saveMenuRoot.do")
	public R saveMenuRoot() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("menu_id"))) {
			return menuRootService.addMenuRoot(ps);
		} else {
			return menuRootService.updateMenuRoot(ps);
		}
	}

}
