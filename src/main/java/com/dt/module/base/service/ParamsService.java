package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月6日 下午3:22:34
 * @Description: TODO
 */
@Service
public class ParamsService extends BaseService {
	public static String TYPE_USER = "user";
	public static String TYPE_SYSINTER = "sysinter";
	public static String TYPE_SYSTEM = "system";

	/**
	 * @Description: 添加参数
	 */
	// 类型system|user|sysinter(系统内置，不可改动)
	public R addParams(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_params");
		me.set("id", ToolUtil.getUUID());
		me.set("deleted", "N");
		me.setIf("name", ps.getString("name", ""));
		me.setIf("value", ps.getString("value", ""));
		me.set("type", ps.getString("type"));
		me.setIf("mark", ps.getString("mark", ""));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询所有参数
	 */
	public R queryParams() {
		// 排除内置
		String sql = "select * from sys_params where deleted='N' and type<>'sysinter' ";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 修改参数
	 */
	public R updateParams(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_params");
		me.setIf("name", ps.getString("name", ""));
		me.setIf("value", ps.getString("value", ""));
		me.set("type", ps.getString("type"));
		me.setIf("mark", ps.getString("mark", ""));
		me.where().and("id=?", ps.getString("id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 删除参数
	 */
	public R deleteParams(String id) {
		Update me = new Update("sys_params");
		me.set("deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 按照Id查询参数
	 */
	public R queryParamsById(String id) {
		String sql = "select * from sys_params where deleted='N' and id=?";
		Rcd rs = db.uniqueRecord(sql, id);
		if (ToolUtil.isEmpty(rs)) {
			return R.FAILURE_NODATA();
		}
		return R.SUCCESS_OPER(rs.toJsonObject());
	}

	/**
	 * @Description: 按照Id查询参数,如果不存在则填充数据
	 */
	public R queryParamsByIdWithExist(String id, String def_value, String type) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}

		String sql = "select * from sys_params where deleted='N' and id=?";
		Rcd rs = db.uniqueRecord(sql, id);
		if (ToolUtil.isEmpty(rs)) {
			// 数据不存在
			Update ups = new Update("sys_params");
			ups.set("deleted", "Y");
			ups.where().and("id=?", id);
			Insert me = new Insert("sys_params");
			
			me.set("id", id);
			me.set("deleted", "N");
			me.set("type", type);
			me.setIf("value", def_value);
			db.executes(ups, me);
		}

		// 重新查询
		return queryParamsById(id);
	}
}
