package com.dt.module.base.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.tool.util.ConvertUtil;
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
	public static String TIX_STATUS_ING = "tixing";
	public static String TIX_STATUS_OVER = "tixover";

	public static String TYPE_CZ = "cz";// 充值
	public static String TYPE_GW = "gw";// 购物

	/* 查询我到提现记录 */
	public R queryMyFundTix(String status) {
		String sql = "select * from sys_user_fund_rec where dr=0 and user_id=? and type='" + TYPE_TX + "'";
		if (ToolUtil.isNotEmpty(status)) {
			sql = sql + " and status='" + status + "'";
		}
		sql = sql + " order by create_time desc";
		return R.SUCCESS_OPER(db.query(sql, getUserId()).toJsonArrayWithJsonObject());
	}

	/* 我触发现录操作 */
	public R actionMyTix(String jestr) {
		// 检测用户是否有效
		String user_id = this.getUserId();
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE("用户信息有误");
		}

		// 金额保留两位小数
		Double double_je = ConvertUtil.toDouble(jestr, 0.00);
		if (double_je <= 0) {
			return R.FAILURE("金额有误,提现失败");
		}
		BigDecimal b = new BigDecimal(double_je);
		double r_je = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

		String sql = "select * from sys_user_info where user_id='" + user_id + "' and amount-" + r_je + " >0";
		if (ToolUtil.isEmpty(db.uniqueRecord(sql))) {
			return R.FAILURE("资金不足,无法提现");
		}
		// 扣除总金额
		String sql1 = "update sys_user_info set amount=amount-" + r_je + " ,tixamount=tixamount+" + r_je
				+ " where user_id='" + user_id + "'";
		List<String> sqls = new ArrayList<String>();
		sqls.add(sql1);
		// 插入资金流水表
		Insert me = new Insert("sys_user_fund_rec");
		me.set("id", db.getUUID());
		me.set("is_plus", 0);
		me.set("amount", db.getUUID());
		me.set("type", TYPE_TX);
		me.set("status", TIX_STATUS_ING);
		me.setSE("create_time", DbUtil.getDbDateString(db.getDBType()));
		sqls.add(me.getSQL());

		db.executeStringList(sqls);
		return R.SUCCESS_OPER("提现成功");
	}

	/* 平台结算提现 */
	public R finishFundTix(String id) {
		List<String> sqls = new ArrayList<String>();
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		String user_id = getUserId();
		Rcd rs = db.uniqueRecord("select * from sys_user_fund_rec where dr=0 and id=?", id);
		if (rs == null) {
			return R.FAILURE_NO_DATA();
		}
		String je = rs.getString("amount");
		// 减少提现冻结金额额
		String sql1 = "update sys_user_info set amount=amount-" + je + " ,tixamount=tixamount+" + je
				+ " where user_id='" + user_id + "'";
		sqls.add(sql1);
		// 更新资金流水
		Update me = new Update("sys_user_fund_rec");
		me.set("status", TIX_STATUS_OVER);
		me.setSE("process_time", DbUtil.getDbDateString(db.getDBType()));
		me.where().and("id=?", id);

		sqls.add(me.getSQL());
		db.executeStringList(sqls);
		return R.SUCCESS_OPER();
	}

	public static void main(String[] args) {
		Double double_je = ConvertUtil.toDouble("122.120122", 0.00);
		BigDecimal b = new BigDecimal(double_je);
		double df = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		System.out.println(df);
		Double v = ConvertUtil.toDouble("12.122", 0.0);
		System.out.println(v);
		if (v <= 0) {
			System.out.println(111);
		} else {
			System.out.println(222);
		}

	}

}
