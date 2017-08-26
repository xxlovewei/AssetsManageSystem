package com.dt.module.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月6日 下午10:13:08
 * @Description: 树节点模块,角色映射
 */
@Service
public class MenuRoleMapService extends BaseService {
	/**
	 * @Description: 一个角色拥有的节点
	 */
	@Transactional
	public ResData treeNodeRoleMap(String role_id, String modulesarr, String menu_id) {
		if (ToolUtil.isOneEmpty(role_id, modulesarr)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		db.execute(
				"delete from sys_role_module where role_id=? and module_id in (select node_id from SYS_MENUS_NODE where MENU_ID=?)",
				role_id, menu_id);
		JSONArray ms = JSONArray.parseArray(modulesarr);
		for (int i = 0; i < ms.size(); i++) {
			db.execute("insert into sys_role_module values(?,?)", role_id, ms.getString(i));
		}
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 一个角色拥有的节点对比树
	 */
	public ResData treeRoleChecked(String menu_id, String role_id) {
		if (ToolUtil.isOneEmpty(menu_id, role_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray resarr = new JSONArray();
		String sql = "select t.* , ( select count(1) from  sys_menus_node where parent_id=t.node_id) children_cnt, (select count(1) from sys_role_module where role_id='"
				+ role_id + "' and module_id=t.node_id) checked from sys_menus_node t where menu_id='" + menu_id
				+ "'    and deleted='N' order by type ";
		System.out.println(sql);
		RcdSet res = db.query(sql);
		JSONObject e;
		JSONObject stat;
		for (int i = 0; i < res.size(); i++) {
			e = new JSONObject();
			int children_cnt = res.getRcd(i).getInteger("children_cnt");
			String type = res.getRcd(i).getString("type");
			e.put("id", res.getRcd(i).getString("node_id"));
			e.put("type", res.getRcd(i).getString("type"));
			e.put("parent",
					res.getRcd(i).getString("parent_id").equals("0") ? "#" : res.getRcd(i).getString("parent_id"));
			e.put("text", res.getRcd(i).getString("node_name") == null ? "" : res.getRcd(i).getString("node_name"));
			e.put("children_cnt", children_cnt);
			stat = new JSONObject();
			int checked = res.getRcd(i).getInteger("checked");
			if (type.equals(MenuService.TYPE_MENU) || type.equals(MenuService.TYPE_BTN)) {
				if (checked > 0) {
					stat.put("selected", true);
				} else {
					stat.put("selected", false);
				}
			}
			e.put("state", stat);
			resarr.add(e);
		}
		return ResData.SUCCESS(resarr);
	}
}
