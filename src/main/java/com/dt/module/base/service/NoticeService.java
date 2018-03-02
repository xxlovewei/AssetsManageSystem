package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年11月16日 上午9:40:42
 * @Description: TODO
 */
@Service
public class NoticeService extends BaseService {
	public static String TYPE_MALL = "mall";
	public static String TYPE_SYSTEM = "system";
	public static String TYPE_USER = "user";

	public R addNotice(TypedHashMap<String, Object> ps, String user_id) {
		Insert me = new Insert("sys_notice");
		me.set("id", db.getUUID());
		me.setIf("type", ps.getString("type"));
		me.setIf("title", ps.getString("title"));
		me.setIf("ct", ps.getString("ct"));
		me.setIf("is_show", ps.getString("is_show", "N"));
		me.set("is_delete", "N");
		me.setIf("user_id", user_id);
		me.setSE("rdate", DbUtil.getDBDateString(db.getDBType()));
		me.setSE("cdate", DbUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R deleteNotice(String id) {
		Update me = new Update("sys_notice");
		me.set("is_delete", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R changeShowNotice(String id, String is_show) {
		Update me = new Update("sys_notice");
		me.set("is_show", is_show);
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R updateNotice(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_notice");
		me.set("id", db.getUUID());
		me.setIf("title", ps.getString("title"));
		me.setIf("ct", ps.getString("ct"));
		me.setIf("is_show", ps.getString("is_show", "N"));
		me.setSE("rdate", DbUtil.getDBDateString(db.getDBType()));
		me.where().and("id=?", ps.getString("id", ""));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public int queryNoticeCount(TypedHashMap<String, Object> ps, String type, String is_show, String user_id) {
		String sql = processQuerySql(ps, type, is_show, user_id);
		sql = "select count(1) cnt from (" + sql + ")";
		return db.uniqueRecord(sql).getInteger("cnt");
	}

	private String processQuerySql(TypedHashMap<String, Object> ps, String type, String is_show, String user_id) {

		String sql = "select * from sys_notice where is_delete='N' ";

		String bdate = ps.getString("bdate");// 2012-01-01
		String edate = ps.getString("edate");// 2012-01-01
		if (ToolUtil.isNotEmpty(type)) {
			sql = sql + " and type='" + type + "'";
		}
		if (ToolUtil.isNotEmpty(is_show)) {
			sql = sql + " and is_show='" + is_show + "'";
		}

		if (ToolUtil.isNotEmpty(user_id)) {
			sql = sql + " and user_id='" + user_id + "'";
		}

		if (ToolUtil.isNotEmpty(bdate)) {
			sql = sql + " rdate>to_date('" + bdate + "','yyyy-mm-dd')";
		}

		if (ToolUtil.isNotEmpty(bdate)) {
			sql = sql + " rdate<to_date('" + edate + "','yyyy-mm-dd')";
		}

		return sql;

	}

	public R queryNotice(TypedHashMap<String, Object> ps, String type, String is_show, String user_id,
			int pageSize, int pageIndex) {
		String sql = processQuerySql(ps, type, is_show, user_id);
		return R.SUCCESS_OPER(
				db.query(DbUtil.getDBPageSql(db.getDBType(), sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}

	public R queryNoticeById(String id) {
		Rcd rs = db.uniqueRecord("select * from sys_notice where is_delete='N' and id=?", id);
		if (ToolUtil.isEmpty(rs)) {
			return R.FAILURE_NO_DATA();
		} else {
			return R.SUCCESS_OPER(rs.toJsonObject());
		}
	}
}
