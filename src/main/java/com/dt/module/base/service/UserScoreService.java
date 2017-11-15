package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:41:45
 * @Description: 用户积分统一通过该服务来调用
 */
@Service
public class UserScoreService extends BaseService {

	public static String ACTION_ADD = "add";
	public static String ACTION_REDUCE = "reduce";

	/**
	 * @Description: 增加用户积分
	 */
	public ResData addScore(String user_id, int value, String mark, String flag) {
		if (ToolUtil.isOneEmpty(user_id, value)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		db.execute("update sys_user_info set score=score+" + value + " where user_id=?", user_id);
		Insert me = new Insert("sys_user_score_dtl");
		me.set("id", db.getUUID());
		me.set("user_id", user_id);
		me.set("action", ACTION_ADD);
		me.set("value", value);
		me.setIf("mark", mark);
		me.setIf("flag", flag);
		me.set("is_delete", "N");
		me.setSE("rtime", DBUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 减少用户积分
	 */
	public ResData reduceScore(String user_id, int value, String mark, String flag) {
		if (ToolUtil.isOneEmpty(user_id, value)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		int v = db.uniqueRecord("select score from sys_user_info where user_id=?", user_id).getInteger("score");
		if (value >= v) {
			db.execute("update sys_user_info set score=score-" + value + " where user_id=?", user_id);
			Insert me = new Insert("sys_user_score_dtl");
			me.set("id", db.getUUID());
			me.set("user_id", user_id);
			me.set("action", ACTION_REDUCE);
			me.set("value", value);
			me.setIf("mark", mark);
			me.setIf("flag", flag);
			me.set("is_delete", "N");
			me.setSE("rtime", DBUtil.getDBDateString(db.getDBType()));
			db.execute(me);
		} else {
			return ResData.FAILURE("用户积分不够");
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询用户积分记录
	 */
	public ResData queryScore(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		return ResData.SUCCESS_OPER(
				db.query("select * from sys_user_score_dtl where user_id=?", user_id).toJsonArrayWithJsonObject());

	}

	/**
	 * @Description: 查询用户积分记录
	 */
	public int queryTodayFlagCount(String user_id, String flag) {
		if (ToolUtil.isEmpty(user_id)) {
			user_id = "";
		}
		if (ToolUtil.isEmpty(flag)) {
			flag = "";
		}
		String sql="select * from sys_user_score_dtl where rtime>sysdate-1 and user_id=? and flag=?";
		return db.query(sql,user_id,flag).size();
		
	}

	/**
	 * @Description: 初始化用户积分
	 */
	public ResData initScore(String user_id, int value) {
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update up1 = new Update("sys_user_info");
		up1.set("score", value);
		up1.where().and("user_id=?", user_id);
		Update up2 = new Update("sys_user_score_dtl");
		up2.set("is_delete", "Y");
		up2.where().and("user_id=?", user_id);
		db.executes(up1, up2);
		return ResData.SUCCESS_OPER();
	}

}
