package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.lang.NumberUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年5月24日 下午6:15:52
 * @Description: TODO
 */
@Service
public class FundService extends BaseService {


	
	
 
	public static String TYPE_TX = "tx";// 提现
	public static String TYPE_CZ = "cz";// 充值
	public static String TYPE_GW = "gw";// 购物
	public static String TYPE_JF = "jf";// 纠纷回扣
	public static String TYPE_SELL = "sell";// 卖掉
	public static String TYPE_REFUND = "refund";// 退款

	public static String ORDER_TYPE_DEFAULT = "default";// 购物
  
	public static String FUND_STATUS_FINISH = "finish";
	public static String FUND_STATUS_TXING = "tixing";
	public static int FUND_AMOUNT_PLUS = 1;
	public static int FUND_AMOUNT_NOT_PLUS = 0;
	public String recAmountFundChange(String user_id, String type, String order_type, int is_plus, String status,
			JSONObject rec) {
		Insert me = new Insert("sys_user_fund_rec");
		me.set("id", db.getUUID());
		me.set("user_id", user_id);
		me.setIf("order_id", rec.getString("order_id"));
		me.setIf("is_plus", is_plus);
		me.setIf("status", status);
		me.set("dr", "0");
		me.setIf("amount", rec.getString("amount"));
		me.setIf("oper_id", getUserId());
		me.setIf("mark", rec.getString("mark"));
		me.setIf("item_id", rec.getString("item_id"));
		me.setIf("order_type", order_type);
		me.setIf("type", type);
		me.setSE("create_time", "sysdate");
		me.setSE("process_time", "sysdate");
		return me.getSQL();
	}
	
	
	/* 查询我到提现记录 */

	public R queryMyFundTix(String status, String numstr) {
		return queryFundTix(this.getUserId(), status, numstr);
	}

	/* 查询提现记录 */
	public R queryFundTix(String user_id, String status, String numstr) {
		int num = ToolUtil.toInt(numstr, -1);
		String sql = "select t.*, decode(status,'finish','提现成功','failed','提现失败','tixing','提现中','未知') statusstr from sys_user_fund_rec t where dr=0 and user_id=? and type='" + TYPE_TX + "'";
		if (ToolUtil.isNotEmpty(status)) {
			sql = sql + " and status='" + status + "'";
		}
		String esql = sql + " order by create_time desc";
		if (num != -1) {
			esql = "select * from (" + sql + ") where rownum<" + num;
		}
		return R.SUCCESS_OPER(db.query(esql, user_id).toJsonArrayWithJsonObject());
	}

	/* 查询我的资金变动 */
	public R queryMyFundChange() {
		String sql = "select * from sys_user_fund_rec where dr=0 and user_id=?  order by create_time desc";
		return R.SUCCESS_OPER(db.query(sql, getUserId()).toJsonArrayWithJsonObject());
	}

	/*********************************
	 * 提现类
	 *******************************************************/
	// 我的提现操作，指定金额
	public R actionMyTix(String jestr) {
		return actionTix(jestr, getUserId());
	}

	// 我的提现操作，全部金额
	public R actionMyAllTix() {
		Rcd rs = db.uniqueRecord("select amount from sys_user_info where user_id=?", getUserId());
		if (rs == null) {
			return R.FAILURE("用户不存在");
		}

		return actionTix(rs.getString("amount"), getUserId());
	}

	/* 我触发提现操作 */
	public R actionTix(String jestr, String user_id) {
		R ck = buyToReduceFundCheck(jestr, user_id);
		if (ck.isFailed()) {
			return ck;
		}
		// 金额保留两位小数
		Double r_je = NumberUtil.formatStrTwo(jestr, 0.00);
		if (r_je <= 0) {
			return R.FAILURE("金额错误");
		}
		/******************************************* 检查 *******************************/
		// 扣除总金额
		String sql1 = "update sys_user_info set amount=amount-" + r_je + " ,tixamount=tixamount+" + r_je
				+ " where user_id='" + user_id + "'";
		List<String> sqls = new ArrayList<String>();
		sqls.add(sql1);
		// 插入资金流水表
		Insert me = new Insert("sys_user_fund_rec");
		me.set("id", db.getUUID());
		me.set("is_plus", 0);
		me.set("dr", 0);
		me.set("user_id", user_id);
		me.set("title", "提现操作");
		me.set("mark", "提现操作");
		me.set("amount", r_je);
		me.set("type", TYPE_TX);
		me.setIf("oper_id", getUserId());
		me.set("status", FUND_STATUS_TXING);
		me.setSE("create_time", DbUtil.getDbDateString(db.getDBType()));
		sqls.add(me.getSQL());

		db.executeStringList(sqls);
		return R.SUCCESS("提现成功");
	}

	/*********************************
	 * 平台批量结算提现
	 *******************************************************/
	public R finishBatchFundTix(String ids) {

		List<String> sqls = new ArrayList<String>();

		if (ToolUtil.isEmpty(ids)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		JSONArray ids_arr = JSONArray.parseArray(ids);
		if (ids_arr == null || ids_arr.size() == 0) {
			return R.FAILURE_OPER();
		}
		for (int i = 0; i < ids_arr.size(); i++) {
			String id = ids_arr.getString(i);
			Rcd rs = db.uniqueRecord("select * from sys_user_fund_rec where dr=0 and id=?", id);
			if (rs == null) {
				return R.FAILURE_NO_DATA();
			}
			String je = rs.getString("amount");
			String amount_owner = rs.getString("user_id");
			// 减少提现冻结金额额
			String sql1 = "update sys_user_info set tixamount=tixamount-" + je + " where user_id='" + amount_owner
					+ "'";
			sqls.add(sql1);
			// 更新资金流水
			Update me = new Update("sys_user_fund_rec");
			me.set("status", FUND_STATUS_FINISH);
			me.set("oper_id", getUserId());
			me.setSE("process_time", DbUtil.getDbDateString(db.getDBType()));
			me.where().and("id=?", id);
			sqls.add(me.getSQL());
		}
		db.executeStringList(sqls);
		return R.SUCCESS_OPER();
	}

	/*********************************
	 * 平台结算提现
	 *******************************************************/
	public R finishFundTix(String id) {
		List<String> sqls = new ArrayList<String>();
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		Rcd rs = db.uniqueRecord("select * from sys_user_fund_rec where dr=0 and id=?", id);
		if (rs == null) {
			return R.FAILURE_NO_DATA();
		}
		String je = rs.getString("amount");
		String amount_owner = rs.getString("user_id");
		// 减少提现冻结金额额
		String sql1 = "update sys_user_info set tixamount=tixamount-" + je + " where user_id='" + amount_owner + "'";
		sqls.add(sql1);
		// 更新资金流水
		Update me = new Update("sys_user_fund_rec");
		me.set("status", FUND_STATUS_FINISH);
		me.set("oper_id", getUserId());
		me.setSE("process_time", DbUtil.getDbDateString(db.getDBType()));
		me.where().and("id=?", id);
		sqls.add(me.getSQL());
		db.executeStringList(sqls);
		return R.SUCCESS_OPER();
	}

	/*********************************
	 * 购物类
	 *******************************************************/
	// 检测金额是否够用，jestr截取保留两位小数
	public R buyToReduceFundCheck(String jestr, String user_id) {

		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE("用户信息有误");
		}

		// 金额保留两位小数
		Double r_je = NumberUtil.formatStrTwo(jestr, 0.00);
		if (r_je <= 0) {
			return R.FAILURE("金额有误");
		}

		String sql = "select * from sys_user_info where user_id='" + user_id + "' and amount-" + r_je + " >=0";
		if (ToolUtil.isEmpty(db.uniqueRecord(sql))) {
			return R.FAILURE("资金不足,无法操作");
		}

		return R.SUCCESS_OPER();

	}

	// 减少金额用于支付购物等
	public R buyBusiFund(String user_id, String jestr, String order_id, TypedHashMap<String, Object> ps) {
		R rs = buyBusiFundSqls(user_id, jestr, order_id, ps);
		if (rs.isFailed()) {
			return rs;
		}

		ArrayList<String> sqls = new ArrayList<String>();
		JSONArray r = rs.queryDataToJSONArray();
		for (int i = 0; i < r.size(); i++) {
			sqls.add(r.getString(i));
		}
		db.executeStringList(sqls);
		return R.SUCCESS_OPER();
	}

	public ArrayList<String> buyBusiFundSqlsDirect(String user_id, String jestr, String type, String order_id,
			TypedHashMap<String, Object> ps) {
		ArrayList<String> sqls = new ArrayList<String>();
		R rs = buyBusiFundSqls(user_id, jestr, order_id, ps);
		if (rs.isSuccess()) {
			JSONArray r = rs.queryDataToJSONArray();
			for (int i = 0; i < r.size(); i++) {
				sqls.add(r.getString(i));
			}
		}
		return sqls;
	}

	// 减少金额用于支付购物等的sql语句
	public R buyBusiFundSqls(String user_id, String jestr, String order_id, TypedHashMap<String, Object> ps) {
		// 检测用户是否有效
		if (ToolUtil.isOneEmpty(jestr, order_id, user_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		R cr = buyToReduceFundCheck(jestr, user_id);
		if (cr.isFailed()) {
			return cr;
		}
		/******************************************* 检查 *******************************/
		String sql1 = "update sys_user_info set amount=amount-" + jestr + " where user_id='" + user_id + "'";
		JSONArray sqls = new JSONArray();
		sqls.add(sql1);

		// 插入资金流水表
		Insert me = new Insert("sys_user_fund_rec");
		me.set("id", db.getUUID());
		me.set("is_plus", 0);
		me.set("dr", 0);
		me.set("user_id", user_id);
		me.setIf("title", ps.getString("title", ""));
		me.setIf("order_type", ps.getString("order_type", ORDER_TYPE_DEFAULT));
		me.set("amount", jestr);
		me.set("type", TYPE_GW);
		me.set("status", FUND_STATUS_FINISH);
		me.set("order_id", order_id);
		me.setIf("oper_id", getUserId());
		me.setSE("create_time", DbUtil.getDbDateString(db.getDBType()));
		me.setSE("process_time", DbUtil.getDbDateString(db.getDBType()));
		sqls.add(me.getSQL());
		return R.SUCCESS_OPER(sqls);
	}

	public static void main(String[] args) {

	}



}
