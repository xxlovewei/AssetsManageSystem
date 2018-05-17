package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月10日 下午7:42:50
 * @Description: TODO
 */
@Service
public class MenuRootService extends BaseService {
	/**
	 * @Description: 添加菜单
	 */
	public R addMenuRoot(TypedHashMap<String, Object> ps) {

		Insert me = new Insert("sys_menus");
		me.set("dr", 0);
		me.setIf("name", ps.getString("name"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("type", ps.getString("type"));
		me.setIf("sort", ps.getString("sort"));
		me.setIf("used", ps.getString("used"));
		me.setIf("org_id", ps.getString("org_id"));
		me.setIf("menu_id", db.getUUID());
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 更新菜单
	 */
	public R updateMenuRoot(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_menus");
		me.set("dr", 0);
		me.setIf("name", ps.getString("name"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("type", ps.getString("type"));
		me.setIf("sort", ps.getString("sort"));
		me.setIf("used", ps.getString("used"));
		me.setIf("org_id", ps.getString("org_id"));
		me.where().and("menu_id=?", ps.getString("menu_id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 删除菜单
	 */
	public R deleteMenuRoot(String id) {
		Update me = new Update("sys_menus");
		me.set("dr", 1);
		me.where().and("menu_id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询菜单
	 */
	public JSONArray queryMenuRoot(String used) {
		String sql = "select * from sys_menus where dr=0 ";
		if (ToolUtil.isNotEmpty(used)) {
			sql = sql + " and used='" + used + "'";
		}
		sql = sql + " order by sort";
		return ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql).toJsonArrayWithJsonObject());
	}

	public R queryMenuRootById(String id) {

		Rcd rs = db.uniqueRecord("select * from sys_menus where dr=0 and menu_id=?", id);
		if (rs == null) {
			return R.FAILURE_NO_DATA();
		} else {
			return R.SUCCESS_OPER(rs.toJsonObject());
		}

	}

	/**
	 * @Description: 查询我的菜单
	 */
	public JSONArray queryMyMenuRoot(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return new JSONArray();
		}

		if (BaseCommon.isSuperAdmin(user_id)) {
			return queryMenuRoot(null);
		}

		String sql = "select * from sys_menus where dr=0 and menu_id in ( select distinct menu_id from sys_user_role a,sys_role_module b,sys_menus_node c where a.role_id=b.role_id and c.node_id=b.module_id and user_id=?) order by sort";
		return ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, user_id).toJsonArrayWithJsonObject());

	}

}
