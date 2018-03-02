package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
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
	public R addMenuRoot() {
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 更新菜单
	 */
	public R updateMenuRoot() {
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 删除菜单
	 */
	public R deleteMenuRoot() {
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询菜单
	 */
	public JSONArray queryMenuRoot() {
		return ConvertUtil.OtherJSONObjectToFastJSONArray(
				db.query("select * from sys_menus order by sort").toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 查询我的菜单
	 */
	public JSONArray queryMyMenuRoot(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return new JSONArray();
		}

		if (BaseCommon.isSuperAdmin(user_id)) {
			return queryMenuRoot();
		}

		String sql = "select * from sys_menus where menu_id in ( select distinct menu_id from sys_user_role a,sys_role_module b,sys_menus_node c where a.role_id=b.role_id and c.node_id=b.module_id and user_id=?) order by sort";
		return ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql,user_id).toJsonArrayWithJsonObject());

	}

}
