package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.support.TypedHashMap;

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
				db.query(DBUtil.getDBPageSql(db.getDBType(), sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}
	public int queryAccessLogPageCount(String user_id, TypedHashMap<String, Object> ps, int pageSize) {
		String basesql = "select * from sys_log_access where rtime>sysdate-365 and user_id='" + user_id + "'";
		String sql = "select count(1) value from (" + basesql + ") ";
		int total = db.uniqueRecord(sql).getInteger("value");
		return total;
	}
}
