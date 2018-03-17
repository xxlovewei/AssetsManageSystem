package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.DbUtil;
import com.dt.module.db.DB;

/**
 * @author: algernonking
 * @date: Oct 26, 2017 9:56:06 AM
 * @Description: TODO
 */
@Service
public class UserLogService extends BaseService {
	/**
	 * @Description: 获取访问日志
	 */
	public R queryAccessLog(String user_id, TypedHashMap<String, Object> ps, int pageSize, int pageIndex) {
		String sql = "select * from sys_log_access where rtime>"
				+ DbUtil.getDbDayBeforeString(DB.instance().getDBType(), "300") + " and user_id='" + user_id
				+ "' order by rtime desc";
		return R.SUCCESS_OPER(
				db.query(DbUtil.getDBPageSql(db.getDBType(), sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 获取访问日志页数
	 */
	public int queryAccessLogPageCount(String user_id, TypedHashMap<String, Object> ps, int pageSize) {
		String basesql = "select * from sys_log_access where rtime>"
				+ DbUtil.getDbDayBeforeString(DB.instance().getDBType(), "300") + " and user_id='" + user_id + "'";
		String sql = "select count(1) value from (" + basesql + ") ";
		int total = db.uniqueRecord(sql).getInteger("value");
		return total;
	}

	/**
	 * @Description: 获取登录日志
	 */
	public R queryLoginLog(String user_id) {
		String sql = "select a.*,b.user_name,b.nickname from sys_log_login a,sys_user_info b where a.user_id=b.user_id and a.user_id=? and rdate>"
				+ DbUtil.getDbDayBeforeString(DB.instance().getDBType(), "90") + " order by a.rdate desc";
		return R.SUCCESS_OPER(db.query(sql, user_id).toJsonArrayWithJsonObject());
	}
}
