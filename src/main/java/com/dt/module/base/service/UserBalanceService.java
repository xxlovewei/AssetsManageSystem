package com.dt.module.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.ResData;
import com.dt.core.dao.sql.Insert;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年2月25日 下午12:40:45
 * @Description: 用户余额统一通过该服务来调用
 */
@Service
public class UserBalanceService extends BaseService {
	public static String ACTION_ADD = "add";
	public static String ACTION_REDUCE = "reduce";

	/**
	 * @Description: 增加用户余额
	 */
	@Transactional
	public ResData addBalance(String user_id, Double value, String mark, String flag) {
		if (ToolUtil.isOneEmpty(user_id, value)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Double v = db.uniqueRecord("select balance from sys_user_info where user_id=?", user_id).getDouble("balance");
		if(ToolUtil.isEmpty(v)) {
			return ResData.FAILURE_NODATA();
		}
		db.execute("update sys_user_info set balance=balance+" + value + " where user_id=?", user_id);
		Insert me = new Insert("sys_user_bal_dtl");
		me.set("id", db.getUUID());
		me.set("user_id", user_id);
		me.set("action", ACTION_ADD);
		me.set("value", value);
		me.setIf("mark", mark);
		me.setIf("flag", flag);
		me.set("is_delete", "N");
		me.setSE("rtime", DbUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 减少用户余额
	 */
	@Transactional
	public ResData reduceBalance(String user_id, Double value, String mark, String flag) {
		if (ToolUtil.isOneEmpty(user_id, value)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Double v = db.uniqueRecord("select balance from sys_user_info where user_id=?", user_id).getDouble("balance");
		if(ToolUtil.isEmpty(v)) {
			return ResData.FAILURE_NODATA();
		}
		if (v>=value) {
			db.execute("update sys_user_info set balance=balance-" + value + " where user_id=?", user_id);
			Insert me = new Insert("sys_user_bal_dtl");
			me.set("id", db.getUUID());
			me.set("user_id", user_id);
			me.set("action", ACTION_REDUCE);
			me.set("value", value);
			me.setIf("mark", mark);
			me.setIf("flag", flag);
			me.set("is_delete", "N");
			me.setSE("rtime", DbUtil.getDBDateString(db.getDBType()));
			db.execute(me);
		} else {
			return ResData.FAILURE("用户余额不够");
		}
		return ResData.SUCCESS_OPER();
	}
}
