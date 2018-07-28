package com.dt.module.base.service.impl;

import java.io.IOException;

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

@Controller
@RequestMapping(value = "/api")
public class MenuController extends BaseController {
	@Autowired
	MenuService menuService;
 
	@Autowired
	MenuRoleMapService menuRoleMapService;

	@Autowired
	ModuleItemMapService moduleItemMapService;

	@ResponseBody
	@Acl(info="查询模块")
	@RequestMapping(value = "/module/queryModuleItemMap.do")
	public R queryModuleItem(String module_id) {
		return moduleItemMapService.queryModuleItem(module_id);
	}
	@ResponseBody
	@Acl(info="更新模块")
	@RequestMapping(value = "/module/updateModuleItemMap.do")
	public R updateModuleItemMap(String module_id, String items) {
		return moduleItemMapService.updateModuleItem(module_id, items);
	}
 
	@RequestMapping(value = "/menu/deleteNode.do")
	@ResponseBody
	@Acl(info = "删除菜单", value = Acl.ACL_DENY)
	public R deleteNode(String node_id) {
		return menuService.deleteNode(node_id);
	}

	@RequestMapping(value = "/menu/addNode.do")
	@ResponseBody
	@Acl(info = "增加节点", value = Acl.ACL_DENY)
	public R addNode() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return menuService.addNode(ps);
	}

	@RequestMapping(value = "/menu/updateNode.do")
	@ResponseBody
	@Acl(info = "更新节点", value = Acl.ACL_DENY)
	public R updateNode() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return menuService.updateNode(ps);
	}

	@RequestMapping(value = "/menu/treeDataDirect.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "查询菜单")
	public R treeDataDirect(String id) {
		return menuService.queryMenuNodes(id);
	}

	@Acl(value = Acl.ACL_USER, info = "查询菜单")
	@RequestMapping(value = "/menu/treeMenus.do")
	@ResponseBody
	public R treeMenus() {
		return menuService.queryMenuNodesTree("1");
	}

	@RequestMapping(value = "/menu/treeNodeRoleMap.do")
	@ResponseBody
	@Acl(info = "查询菜单权限")
	public R treeNodeRoleMap(String role_id, String modules_arr, String menu_id) {
		if (ToolUtil.isOneEmpty(role_id, modules_arr, menu_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		return menuRoleMapService.treeNodeRoleMap(role_id, modules_arr, menu_id);
	}

	@RequestMapping(value = "/menu/treeRoleChecked.do")
	@ResponseBody
	@Acl(info = "查询菜单权限检测")
	public R treeRoleChecked(String menu_id, String role_id) throws IOException {
		return menuRoleMapService.treeRoleChecked(menu_id, role_id);
	}
}
