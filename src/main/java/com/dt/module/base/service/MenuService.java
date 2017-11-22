package com.dt.module.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月6日 下午8:49:32
 * @Description: Menu树根据ID可有多棵,node_id整个表唯一递增
 */
@Service
public class MenuService extends BaseService {
	public static String TYPE_DIR = "dir";
	public static String TYPE_MENU = "menu";
	public static String TYPE_BTN = "btn";
	private String LEVEL_SPLIT = "/";

	public static String validType(String value) {
		if (ToolUtil.isEmpty(value)) {
			return TYPE_DIR;
		}
		if (value.equals(TYPE_DIR) || value.equals(TYPE_MENU) || value.equals(TYPE_BTN)) {
			return value;
		}
		return TYPE_DIR;
	}
	/**
	 * @Description: 直接查询所有节点
	 */
	public ResData queryMenuNodes(String menu_id) {
		String sql = "select (select count(1) from sys_modules_item where module_id=node_id) acl_cnt,a.*,case type when 'dir' then '目录' when 'menu' then '菜单' else '未知' end typetext from sys_menus_node a where menu_id=? and deleted='N' order by node_id";
		return ResData.SUCCESS_OPER(db.query(sql, menu_id).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:按照前端js要求直接生成树的json格式
	 */
	public ResData queryMenuNodesTree(String menu_id) {
		JSONArray r = new JSONArray();
		String basesql = "select * from sys_menus_node where menu_id=? and parent_id=? and deleted='N' order by sort";
		RcdSet first_rs = db.query(basesql, menu_id, 0);
		for (int i = 0; i < first_rs.size(); i++) {
			JSONObject first_obj = ConvertUtil.OtherJSONObjectToFastJSONObject(first_rs.getRcd(i).toJsonObject());
			String first_key = first_rs.getRcd(i).getString("key");
			first_obj.put("state", first_key);
			RcdSet second_rs = db.query(basesql, menu_id, first_rs.getRcd(i).getString("node_id"));
			JSONArray second_arr = new JSONArray();
			for (int j = 0; j < second_rs.size(); j++) {
				JSONObject second_obj = ConvertUtil.OtherJSONObjectToFastJSONObject(second_rs.getRcd(i).toJsonObject());
				String second_key = second_rs.getRcd(j).getString("key");
				second_obj.put("state", first_key + "." + second_key);
				RcdSet third_rs = db.query(basesql, menu_id, second_rs.getRcd(j).getString("node_id"));
				second_obj.put("children_cnt", third_rs.size());
				// 处理三层
				JSONArray third_arr = ConvertUtil.OtherJSONObjectToFastJSONArray(third_rs.toJsonArrayWithJsonObject());
				for (int f = 0; f < third_arr.size(); f++) {
					third_arr.getJSONObject(f).put("state",
							first_key + "." + second_key + "." + third_arr.getJSONObject(f).getString("key"));
				}
				second_obj.put("children", third_arr);
				second_arr.add(second_obj);
			}
			first_obj.put("children_cnt", second_rs.size());
			first_obj.put("children", second_arr);
			r.add(first_obj);
		}
		return ResData.SUCCESS_OPER(r);
	}
	/**
	 * @Description:查询菜单一个节点的数据
	 */
	public ResData queryNodeById() {
		return ResData.SUCCESS();
	}
	/**
	 * @Description:添加一个节点
	 */
	@Transactional
	public ResData addNode(TypedHashMap<String, Object> ps) {
		String menu_id = ps.getString("menu_id");
		String old_node_id = ps.getString("old_node_id");
		String old_route = ps.getString("old_route");
		String node_name = ps.getString("node_name");
		String mark = ps.getString("mark");
		String logo = ps.getString("logo");
		String key = ps.getString("key");
		String node_id = getNextNodeId();
		Insert ins = new Insert("sys_menus_node");
		String type = ps.getString("actiontype", "add");
		String nodeid = getNextNodeId();
		if (type.equals("addmaster")) {
			// 增加第一个节点
			if (ToolUtil.isEmpty(menu_id)) {
				return ResData.FAILURE_ERRREQ_PARAMS();
			}
			ins.set("node_id", nodeid);
			ins.set("parent_id", "0");
			ins.set("route", nodeid);
		} else {
			nodeid = node_id;
			ins.set("node_id", node_id);
			ins.set("parent_id", old_node_id);
			ins.set("route", old_route + "-" + node_id);
		}
		ins.set("menu_id", menu_id);
		// ins.set("NODE_ID", node_id);
		ins.set("node_name", node_name);
		// ins.set("PARENT_ID", old_node_id);
		// ins.set("ROUTE", old_route + "-" + node_id);
		ins.setIf("key", key);
		ins.set("is_action", ps.getString("is_action"));
		ins.set("deleted", "N");
		ins.set("is_g_show", ps.getString("is_g_show"));
		ins.setIf("logo", logo);
		ins.setIf("mark", mark);
		ins.setIf("type", validType(ps.getString("type")));
		db.execute(ins);
		updateRouteName(nodeid, node_name);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:删除一个节点
	 */
	public ResData deleteNode(String node_id) {
		int v = db.uniqueRecord("select count(1) value from sys_menus_node where deleted='N' and parent_id=? ", node_id)
				.getInteger("value");
		if (v > 0) {
			return ResData.FAILURE("请先删除子节点");
		} else {
			Update ups=new Update("sys_menus_node");
			ups.set("deleted", "Y");
			ups.where().and("node_id=?",node_id);
			db.execute(ups);
			return ResData.SUCCESS_OPER();
		}
	}
	/**
	 * @Description:更新节点数据
	 */
	@Transactional
	public ResData updateNode(TypedHashMap<String, Object> ps) {
		String menu_id = ps.getString("menu_id");
		String node_id = ps.getString("node_id");
		String node_name = ps.getString("node_name");
		String mark = ps.getString("mark");
		String key = ps.getString("key");
		String module_id = ps.getString("module_id");
		String sort = ps.getString("sort");
		String logo = ps.getString("logo");
		if (ToolUtil.isEmpty(node_name)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("sys_menus_node");
		ups.set("node_name", node_name);
		ups.setIf("key", key);
		ups.setIf("sort", sort);
		ups.setIf("logo", logo);
		ups.setIf("mark", mark);
		ups.setIf("module_id", module_id);
		ups.setIf("is_action", ps.getString("is_action"));
		ups.setIf("is_g_show", ps.getString("is_g_show"));
		ups.setIf("type", validType(ps.getString("type")));
		ups.where().and("menu_id=?", menu_id).and("node_id=?", node_id);
		db.execute(ups);
		updateRouteName(node_id, node_name);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:更新节点路径名称
	 */
	private void updateRouteName(String node_id, String node_name) {
		Rcd rs = db.uniqueRecord("select * from sys_menus_node where deleted='N' and node_id=?", node_id);
		// 判断如果一致则不需要更新routename
		if (ToolUtil.isEmpty(rs)) {
			return;
		}
		if (rs.getString("node_name").equals("node_name")) {
			return;
		}
		String ids = rs.getString("route");
		JSONArray arr = ConvertUtil.toJSONArrayFromString(ids, "id", "-");
		String route_name = "";
		for (int i = 0; i < arr.size(); i++) {
			route_name = route_name + LEVEL_SPLIT
					+ db.uniqueRecord("select node_name from sys_menus_node where deleted='N' and node_id=?",
							arr.getJSONObject(i).getString("id")).getString("node_name");
		}
		route_name = route_name.replaceFirst(LEVEL_SPLIT, "");
		Update me = new Update("sys_menus_node");
		me.set("route_name", route_name);
		me.where().and("node_id=?", node_id);
		db.execute(me);
		RcdSet rds = db.query("select node_id,node_name from sys_menus_node where deleted='N' and parent_id=?", node_id);
		for (int j = 0; j < rds.size(); j++) {
			// 递归调用
			updateRouteName(rds.getRcd(j).getString("node_id"), rds.getRcd(j).getString("node_name"));
		}
	}
	/**
	 * @Description:获取节点下一个序列号，sys_menus_node表全局唯一
	 */
	public String getNextNodeId() {
		return db
				.uniqueRecord(
						"select case when max(node_id) is null then 50 else max(node_id)+1 end value from sys_menus_node ")
				.getString("value");
	}
	/**
	 * @Description:获取树的第一个节点的父节点
	 */
	public String getRootParentId(String id) {
		String sql = "select min(parent_id) parent_id from sys_menus_node where deleted='N' and menu_id=?";
		Rcd rs = db.uniqueRecord(sql, id);
		String parent_id = rs.getString("parent_id");
		if (ToolUtil.isEmpty(parent_id)) {
			return getNextNodeId();
		} else {
			return parent_id;
		}
	}
}
