package com.dt.module.base.service;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.tool.lang.TypedHashMap;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Nov 1, 2017 8:49:51 AM
 * @Description: TODO
 */
@Service
public class StoreSqlService extends BaseService {
	private static Logger _log = LoggerFactory.getLogger(StoreSqlService.class);
	public static String ACL_PUBLIC = "public";
	public static String ACL_USER = "user";
	public static String ACL_SYSTEM = "system";
	public static String RETURN_ACTION = "action";
	public static String RETURN_OBJECT = "object";
	public static String RETURN_ARRARY = "array";
	public static String VAR_SPLIT = "@";

	/**
	 * @Description: 根据条件返回数据，无分页功能
	 */
	@SuppressWarnings("rawtypes")
	public ResData commandAction(TypedHashMap<String, Object> ps, String user_id, String acl) {
		String sql = "";
		String store_id = ps.getString("store_id");
		String return_type = "";
		String is_used = "N";
		if (ToolUtil.isNotEmpty(store_id)) {
			String storesql = "select * from ct_uri where store_id=?";
			if (ToolUtil.isNotEmpty(acl) && acl.equals(ACL_PUBLIC)) {
				storesql = storesql + " and acl='" + ACL_PUBLIC + "'";
			}
			Rcd brs = db.uniqueRecord(storesql, store_id);
			if (ToolUtil.isNotEmpty(brs)) {
				sql = brs.getString("sql");
				return_type = brs.getString("return_type");
				is_used = brs.getString("is_used");
			}
		}
		// 判断是否可以使用
		if (!is_used.equals("Y")) {
			return ResData.FAILURE("功能未激活");
		}
		// 处理自定义变量,格式:@var@
		Iterator<Entry<String, Object>> i = ps.entrySet().iterator();
		while (i.hasNext()) {
			Entry entry = (java.util.Map.Entry) i.next();
			String key = entry.getKey().toString();
			String value = (String) entry.getValue();
			_log.info("key:" + key + ",value:" + value);
			if (key.startsWith(VAR_SPLIT)) {
				_log.info("key to replace:" + key + VAR_SPLIT + ",value:" + value);
				sql = sql.replaceAll(key + VAR_SPLIT, value);
			}
		}
		// 处理系统定义的变量,格式:@<var>@
		_log.info("execute sql:" + sql);
		if (ToolUtil.isNotEmpty(sql)) {
			if (return_type.equals(RETURN_ARRARY)) {
				RcdSet rs = db.query(sql);
				return ResData.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
			} else if (return_type.equals(RETURN_OBJECT)) {
				Rcd rs = db.uniqueRecord(sql);
				return ResData.SUCCESS_OPER(rs.toJsonObject());
			} else {
				db.execute(sql);
				return ResData.SUCCESS_OPER();
			}
		} else {
			return ResData.FAILURE("Sql语句有误");
		}
	}
	public ResData queryStoreSql(String cat_id) {
		String sql = "select * from ct_uri where is_deleted='N' ";
		if (ToolUtil.isNotEmpty(cat_id)) {
			sql = sql + " and cat_id='" + cat_id + "'";
		}
		RcdSet rs = db.query(sql);
		return ResData.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}
	public ResData queryStoreSqlById(String store_id) {
		Rcd rs = db.uniqueRecord("select * from ct_uri where store_id=?", store_id);
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}
	private ResData checkStoreSqlFormat(TypedHashMap<String, Object> ps) {
		
		//检查alias_id;
		

		// 弱弱的检查下
		String msg = "Sql文本于返回类型不匹配";
		String sql = ps.getString("sql", "").trim();
		String return_type = ps.getString("return_type", RETURN_ACTION);
		if (sql.toLowerCase().startsWith("select")) {
			if (return_type.equals(RETURN_ARRARY) || return_type.equals(RETURN_OBJECT)) {
				return ResData.SUCCESS_OPER();
			} else {
				return ResData.FAILURE(msg);
			}
		} else {
			if (return_type.equals(RETURN_ACTION)) {
				return ResData.SUCCESS_OPER();
			} else {
				return ResData.FAILURE(msg);
			}
		}
		
		
		
	}
	public ResData addStoreSql(TypedHashMap<String, Object> ps, String user_id) {
		ResData rs = checkStoreSqlFormat(ps);
		if (rs.isFailed()) {
			return rs;
		}
		Insert me = new Insert("ct_uri");
		me.set("store_id", db.getUUID());
		me.setIf("name", ps.getString("name"));
		me.setIf("cat_id", ps.getString("cat_id"));
		me.setIf("uri", ps.getString("uri"));
		me.setIf("uri_parameter", ps.getString("uri_parameter"));
		me.setIf("user_id", user_id);
		me.setIf("sql", ps.getString("sql"));
		me.setIf("db_id", ps.getString("db_id"));
		// me.setIf("ctime", ps.getString("ctime"));
		me.set("is_deleted", "N");
		me.set("acl", ps.getString("acl", ACL_USER));
		me.setIf("mark", ps.getString("mark"));
		me.set("return_type", ps.getString("return_type", RETURN_ACTION));
		me.setIf("is_used", ps.getString("is_used"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	public ResData updateStoreSql(TypedHashMap<String, Object> ps, String user_id) {
		ResData rs = checkStoreSqlFormat(ps);
		if (rs.isFailed()) {
			return rs;
		}
		Update me = new Update("ct_uri");
		me.setIf("name", ps.getString("name"));
		me.setIf("uri", ps.getString("uri"));
		me.setIf("uri_parameter", ps.getString("uri_parameter"));
		me.setIf("user_id", user_id);
		me.setIf("sql", ps.getString("sql"));
		me.setIf("db_id", ps.getString("db_id"));
		me.set("acl", ps.getString("acl", ACL_USER));
		me.setIf("mark", ps.getString("mark"));
		me.set("return_type", ps.getString("return_type", RETURN_ACTION));
		me.setIf("is_used", ps.getString("is_used"));
		me.where().and("store_id=?", ps.getString("store_id"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	public ResData deleteStoreSql(String store_id) {
		Update me = new Update("ct_uri");
		me.set("is_deleted", "Y");
		me.where().and("store_id=?", store_id);
		System.out.println("sql" + me.getSQL());
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
}
