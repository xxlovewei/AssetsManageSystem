package com.dt.module.base.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.MenuRoleMapService;
import com.dt.module.base.service.MenuRootService;
import com.dt.module.base.service.MenuService;

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
	@RequestMapping(value = "/menu/treeTop.do")
	public ResData treeTop() {
		return menuRootService.queryMenuRoot();
	}
	@RequestMapping(value = "/menu/deleteNode.do")
	@Res
	@Acl
	public ResData deleteNode(String NODE_ID) {
		return menuService.deleteNode(NODE_ID);
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
	@Acl
	public ResData treeDataDirect(String ID) {
		return menuService.queryMenuNodes(ID);
	}
	@Acl
	@RequestMapping(value = "/menu/treeMenus.do")
	@Res
	public ResData treeMenus() {
		return menuService.queryMenuNodesTree("1");
	}
	@RequestMapping(value = "/menu/treeNodeRoleMap.do")
	@Res
	@Acl
	public ResData treeNodeRoleMap(String ROLE_ID, String MODULES_ARR) {
		return menuRoleMapService.treeNodeRoleMap(ROLE_ID, MODULES_ARR);
	}
	@RequestMapping(value = "/menu/treeRoleChecked.do")
	@Res
	@Acl
	public ResData treeRoleChecked(String MENU_ID, String ROLE_ID) throws IOException {
		return menuRoleMapService.treeRoleChecked(MENU_ID, ROLE_ID);
	}
}
