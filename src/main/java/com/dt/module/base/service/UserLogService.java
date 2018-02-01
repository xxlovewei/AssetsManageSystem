package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.util.TypedHashMap;
import com.dt.tool.util.DbUtil;

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
	public ResData queryAccessLog(String user_id, TypedHashMap<String, Object> ps, int pageSize, int pageIndex) {
		String sql = "select * from sys_log_access where rtime>sysdate-365 and user_id='" + user_id
				+ "' order by rtime desc";
		return ResData.SUCCESS_OPER(
				db.query(DbUtil.getDBPageSql(db.getDBType(), sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 获取访问日志页数
	 */
	public int queryAccessLogPageCount(String user_id, TypedHashMap<String, Object> ps, int pageSize) {
		String basesql = "select * from sys_log_access where rtime>sysdate-365 and user_id='" + user_id + "'";
		String sql = "select count(1) value from (" + basesql + ") ";
		int total = db.uniqueRecord(sql).getInteger("value");
		return total;
	}
	/**
	 * @Description: 获取登录日志
	 */
	public ResData queryLoginLog(String user_id) {
		String sql = "select a.*,b.user_name,b.nickname from sys_log_login a,sys_user_info b where a.user_id=b.user_id and a.user_id=? and rdate>sysdate-90 order by a.rdate desc";
		return ResData.SUCCESS_OPER(db.query(sql, user_id).toJsonArrayWithJsonObject());
	}
}
