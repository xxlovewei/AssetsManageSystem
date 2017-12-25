package com.dt.module.om.service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年12月21日 下午3:15:41
 * @Description: TODO
 */
public class NodeService extends BaseService {

	public static String LOGIN_TYPE_SSH = "ssh";
	public static String NODE_TYPE_HOST = "host";
	public static String NODE_SMALL_TYPE_HOST_LINUX = "linux";
	public static String NODE_SMALL_TYPE_HOST_WINDOW = "window";
	public static String NODE_SMALL_TYPE_HOST_AIX = "aix";

	public static String NODE_TYPE_DB = "db";
	public static String NODE_SMALL_TYPE_DB_ORACLE = "oracle";
	public static String NODE_SMALL_TYPE_DB_MYSQL = "mysql";
	public static String NODE_SMALL_TYPE_DB_MSSQL = "mssql";
	
	public static String NODE_TYPE_APP = "app";
	public static String NODE_SMALL_TYPE_APP_TOMCAT = "tomcat";

	public ResData addNode(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("om_node");
		me.set("id", db.getUUID());
		if (ToolUtil.isEmpty(ps.getString("BELONGTOID"))) {
			me.setIf("belongtoId", "0");
		} else {
			me.setIf("belongtoId", ps.getString("BELONGTOID"));
		}
		me.setIf("type", ps.getString("TYPE", ""));
		me.setIf("smalltype", ps.getString("SMALLTYPE", ""));
		me.setIf("name", ps.getString("NAME", ""));
		me.setIf("operator", ps.getString("OPERATOR", ""));
		me.setIf("ip", ps.getString("IP", ""));
		me.setIf("username", ps.getString("USERNAME", ""));
		me.setIf("pwd", ps.getString("PWD", ""));
		me.setIf("logintype", ps.getString("LOGINTYPE", ""));
		me.setIf("port", ps.getString("PORT", ""));
		me.setIf("isvalid", ps.getString("ISVALID", "N"));
		me.setIf("isrunning", ps.getString("ISRUNNING", "N"));
		me.setIf("deleted", "N");
		me.setIf("od", ps.getString("OD", "1"));
		me.setIf("mark", ps.getString("MARK", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData updateNode(TypedHashMap<String, Object> ps) {
		Update me = new Update("om_node");
		me.setIf("name", ps.getString("NAME", ""));
		me.setIf("operator", ps.getString("OPERATOR", ""));
		me.setIf("ip", ps.getString("IP", ""));
		me.setIf("username", ps.getString("USERNAME", ""));
		me.setIf("pwd", ps.getString("PWD", ""));
		me.setIf("logintype", ps.getString("LOGINTYPE", ""));
		me.setIf("port", ps.getString("PORT", ""));
		me.setIf("isvalid", ps.getString("ISVALID", "N"));
		me.setIf("isrunning", ps.getString("ISRUNNING", "N"));
		me.setIf("od", ps.getString("OD", "1"));
		me.setIf("mark", ps.getString("MARK", ""));
		me.where().and("id=?", ps.getString("ID", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData queryNodeById(String id) {
		return ResData.SUCCESS_OPER(db.uniqueRecord("select * from om_node where deleted='N' and id=?").toJsonObject());
	}

	public ResData queryNode(TypedHashMap<String, Object> ps) {
		String sql = "select * from om_node where deleted='N' ";
		if (ToolUtil.isNotEmpty(ps.getString("TYPE"))) {
			sql += "and type='" + ps.getString("TYPE") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("SMALLTYPE"))) {
			sql += "and smalltype='" + ps.getString("SMALLTYPE") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("LOGINTYPE"))) {
			sql += "and logintype='" + ps.getString("LOGINTYPE") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("ISRUNNING"))) {
			sql += "and isrunning='" + ps.getString("ISRUNNING") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("ISVALID"))) {
			sql += "and isvalid='" + ps.getString("ISVALID") + "'";
		}
		if (ToolUtil.isNotEmpty(ps.getString("BELONGTOID"))) {
			sql += "and belongtoid='" + ps.getString("BELONGTOID") + "'";
		}

		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public ResData deleteNode(String id) {
		Update me = new Update("om_node");
		me.setIf("deleted", "N");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

}
