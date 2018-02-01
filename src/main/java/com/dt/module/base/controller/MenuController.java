package com.dt.module.base.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.dao.util.TypedHashMap;
import com.dt.module.base.service.MenuRoleMapService;
import com.dt.module.base.service.MenuRootService;
import com.dt.module.base.service.MenuService;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

@Controller
@RequestMapping(value = "/api")
public class MenuController extends BaseController {
	@Autowired
	MenuService menuService;
	@Autowired
	MenuRootService menuRootService;
	@Autowired
	MenuRoleMapService menuRoleMapService;

	@Res
	@Acl
	@RequestMapping(value = "/menu/treeTop.do")
	public ResData treeTop() {
		return ResData.SUCCESS_OPER(menuRootService.queryMenuRoot());
	}
	@RequestMapping(value = "/menu/deleteNode.do")
	@Res
	@Acl
	public ResData deleteNode(String node_id) {
		return menuService.deleteNode(node_id);
	}
	@RequestMapping(value = "/menu/addNode.do")
	@Res
	@Acl
	public ResData addNode() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return menuService.addNode(ps);
	}
	@RequestMapping(value = "/menu/updateNode.do")
	@Res
	@Acl
	public ResData updateNode() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return menuService.updateNode(ps);
	}
	@RequestMapping(value = "/menu/treeDataDirect.do")
	@Res
	@Acl(value=Acl.TYPE_USER_COMMON)
	public ResData treeDataDirect(String id) {
		return menuService.queryMenuNodes(id);
	}
	@Acl(value = Acl.TYPE_USER_COMMON)
	@RequestMapping(value = "/menu/treeMenus.do")
	@Res
	public ResData treeMenus() {
		return menuService.queryMenuNodesTree("1");
	}
	@RequestMapping(value = "/menu/treeNodeRoleMap.do")
	@Res
	@Acl
	public ResData treeNodeRoleMap(String role_id, String modules_arr, String menu_id) {
		if (ToolUtil.isOneEmpty(role_id, modules_arr, menu_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		return menuRoleMapService.treeNodeRoleMap(role_id, modules_arr, menu_id);
	}
	@RequestMapping(value = "/menu/treeRoleChecked.do")
	@Res
	@Acl
	public ResData treeRoleChecked(String menu_id, String role_id) throws IOException {
		return menuRoleMapService.treeRoleChecked(menu_id, role_id);
	}
}
