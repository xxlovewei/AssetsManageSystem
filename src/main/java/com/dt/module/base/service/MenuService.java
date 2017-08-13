package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
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
	public static String TYPE_BTN = "BTN";

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
		String sql = "select * from sys_menus_node where menu_id=? order by node_id   ";
		return ResData.SUCCESS(db.query(sql, menu_id).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:按照前端js要求直接生成树的json格式
	 */
	public ResData queryMenuNodesTree(String menu_id) {
		JSONArray r = new JSONArray();
		String basesql = "select * from sys_menus_node where menu_id=? and parent_id=? order by sort";
		RcdSet first_rs = db.query(basesql, menu_id, 0);
		for (int i = 0; i < first_rs.size(); i++) {
			JSONObject first_obj = JSONObject.parseObject(first_rs.getRcd(i).toJsonObject().toString());
			String first_key = first_rs.getRcd(i).getString("key");
			first_obj.put("STATE", first_key);
			RcdSet second_rs = db.query(basesql, menu_id, first_rs.getRcd(i).getString("node_id"));
			JSONArray second_arr = new JSONArray();
			for (int j = 0; j < second_rs.size(); j++) {
				JSONObject second_obj = JSONObject.parseObject(second_rs.getRcd(j).toJsonObject().toString());
				String second_key = second_rs.getRcd(j).getString("key");
				second_obj.put("STATE", first_key + "." + second_key);
				RcdSet third_rs = db.query(basesql, menu_id, second_rs.getRcd(j).getString("node_id"));
				second_obj.put("CHILDREN_CNT", third_rs.size());
				// 处理三层
				JSONArray third_arr = JSONArray.parseArray(third_rs.toJsonArrayWithJsonObject().toString());
				for (int f = 0; f < third_arr.size(); f++) {
					third_arr.getJSONObject(f).put("STATE",
							first_key + "." + second_key + "." + third_arr.getJSONObject(f).getString("KEY"));
				}
				second_obj.put("CHILDREN", third_arr);
				second_arr.add(second_obj);
			}
			first_obj.put("CHILDREN_CNT", second_rs.size());
			first_obj.put("CHILDREN", second_arr);
			r.add(first_obj);
		}
		return ResData.SUCCESS(r);
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
	public ResData addNode(TypedHashMap<String, Object> ps) {
		String menu_id = ps.getString("MENU_ID");
		String old_node_id = ps.getString("OLD_NODE_ID");
		String old_route = ps.getString("OLD_ROUTE");
		String node_name = ps.getString("NODE_NAME");
		String mark = ps.getString("MARK");
		String logo = ps.getString("LOGO");
		String key = ps.getString("KEY");
		String node_id = getNextNodeId();
		Insert ins = new Insert("sys_menus_node");
		String type = ps.getString("ACTIONTYPE", "ADD");
		if (type.equals("ADDMASTER")) {
			// 增加第一个节点
			if (ToolUtil.isEmpty(menu_id)) {
				return ResData.FAILURE_ERRREQ_PARAMS();
			}
			String nodeid = getNextNodeId();
			ins.set("NODE_ID", nodeid);
			ins.set("PARENT_ID", "0");
			ins.set("ROUTE", nodeid);
		} else {
			ins.set("NODE_ID", node_id);
			ins.set("PARENT_ID", old_node_id);
			ins.set("ROUTE", old_route + "-" + node_id);
		}
		ins.set("MENU_ID", menu_id);
		// ins.set("NODE_ID", node_id);
		ins.set("NODE_NAME", node_name);
		// ins.set("PARENT_ID", old_node_id);
		// ins.set("ROUTE", old_route + "-" + node_id);
		ins.setIf("KEY", key);
		ins.set("IS_ACTION", ps.getString("IS_ACTION"));
		ins.set("DELETED", "N");
		ins.set("IS_G_SHOW", ps.getString("IS_G_SHOW"));
		ins.setIf("LOGO", logo);
		ins.setIf("MARK", mark);
		ins.setIf("TYPE", validType(ps.getString("TYPE")));
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:删除一个节点
	 */
	public ResData deleteNode(String node_id) {
		int v = db.uniqueRecord("select count(1) value from sys_menus_node where   parent_id=?", node_id)
				.getInteger("value");
		if (v > 0) {
			return ResData.FAILURE("请先删除子节点");
		} else {
			db.execute("delete from sys_menus_node where  node_id=?", node_id);
			return ResData.SUCCESS_OPER();
		}
	}
	/**
	 * @Description:更新节点数据
	 */
	public ResData updateNode(TypedHashMap<String, Object> ps) {
		String menu_id = ps.getString("MENU_ID");
		String node_id = ps.getString("NODE_ID");
		String node_name = ps.getString("NODE_NAME");
		String mark = ps.getString("MARK");
		String key = ps.getString("KEY");
		String module_id = ps.getString("MODULE_ID");
		String sort = ps.getString("SORT");
		String logo = ps.getString("LOGO");
		Update ups = new Update("sys_menus_node");
		ups.set("NODE_NAME", node_name);
		ups.setIf("KEY", key);
		ups.setIf("SORT", sort);
		ups.setIf("LOGO", logo);
		ups.setIf("MARK", mark);
		ups.setIf("MODULE_ID", module_id);
		ups.setIf("IS_ACTION", ps.getString("IS_ACTION"));
		ups.setIf("IS_G_SHOW", ps.getString("IS_G_SHOW"));
		ups.setIf("TYPE", validType(ps.getString("TYPE")));
		ups.where().and("MENU_ID=?", menu_id).and("NODE_ID=?", node_id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:获取节点下一个序列号
	 */
	public String getNextNodeId() {
		return db.uniqueRecord("select decode(max(node_id),null,1,max(node_id)+1) value from sys_menus_node ")
				.getString("value");
	}
	/**
	 * @Description:获取树的第一个节点的父节点
	 */
	public String getRootParentId(String id) {
		String sql = "select min(parent_id) parent_id from SYS_MENUS_NODE where menu_id=?";
		Rcd rs = db.uniqueRecord(sql, id);
		String parent_id = rs.getString("parent_id");
		if (ToolUtil.isEmpty(parent_id)) {
			return getNextNodeId();
		} else {
			return parent_id;
		}
	}
}
