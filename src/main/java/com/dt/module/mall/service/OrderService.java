package com.dt.module.mall.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.SQL;
import com.dt.core.common.dao.sql.Update;
import com.dt.tool.lang.TypedHashMap;
import com.dt.tool.util.ConvertUtil;
import com.dt.tool.util.DBUtil;
import com.dt.tool.util.MD5Util;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:32:54
 * @Description: TODO
 */
@Service
public class OrderService extends BaseService {

	public static String ORDER_STATUS_CANCEL = "1";// 已取消
	public static String ORDER_STATUS_WAITTING_PAY = "2";// 待付款 dir
	public static String ORDER_STATUS_WAITTING_DELIVERY = "4";// 待发货 dir
	public static String ORDER_STATUS_WAITTING_TAKE = "6";// 待收货 dir
	public static String ORDER_STATUS_WAITTING_EVALUATE = "8";// 待评价dir
	public static String ORDER_STATUS_FINISH = "10";// 已完成 dir
	public static String ORDER_STATUS_RETURNING = "12";// 退货中 dir
	public static String ORDER_STATUS_RETURN_FINISH = "14";// 退货成功 dir

	public static String ORDER_ACTION_CREATE = "create";
	public static String ORDER_ACTION_PAY = "pay";
	public static String ORDER_ACTION_CANCEL = "cancel";
	public static String ORDER_ACTION_DELIVERY = "delivery";
	public static String ORDER_ACTION_RECEIPT = "receipt";
	public static String ORDER_ACTION_REPUTATION = "reputation";
	public static String ORDER_ACTION_CHANGE_MONEY = "change_money";
	public static String ORDER_ACTION_CHANGE_STATUS = "change_status";
	public static String ORDER_ACTION_UNKNOW = "unknow";

	/**
	 * @Description: 记录修改
	 */
	public Boolean recordOrderLog(String action, String order_id, String ct, String user_id) {
		Insert me = new Insert("mall_order_log");
		me.set("id", db.getUUID());
		me.set("order_id", order_id);
		me.set("user_id", user_id);
		me.setIf("action", action);
		me.setIf("ct", ct);
		me.setSE("cdate", DBUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return true;
	}

	/**
	 * @Description: 产生订单号
	 */
	private String createOrderId() {
		Date now = new Date();
		int max = 999;
		int min = 100;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");// 可以方便地
		String str = "OD" + dateFormat.format(now) + MD5Util.encrypt(db.getUUID()).substring(0, 5).toUpperCase() + s;
		return str;
	}

	/**
	 * @Description: 创建商城产品订单
	 */
	public ResData createOrder(TypedHashMap<String, Object> ps, String user_id, String status) {
		// 创建订单
		if (ToolUtil.isOneEmpty(status, user_id, ps.getString("amountreal"), ps.getString("amount"),
				ps.getString("yunprice"))) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}

		
		List<SQL> sqls = new ArrayList<SQL>();
		String orderId = createOrderId();

		// 订单详细
		JSONArray goodsarr = JSONArray.parseArray(ps.getString("goodjsonstr", "[]"));
		if (ToolUtil.isEmpty(goodsarr)) {
			return ResData.FAILURE("创建订单失败,解析失败");
		}
		if (goodsarr.size() == 0) {
			return ResData.FAILURE("创建订单失败,解析产品失败");
		}
		for (int i = 0; i < goodsarr.size(); i++) {
			Insert ins = new Insert("mall_order_detail");
			JSONObject e = goodsarr.getJSONObject(i);
			System.out.println(e.toJSONString());
			ins.set("id", MD5Util.encrypt(db.getUUID()));
			ins.set("order_id", orderId);
			ins.setIf("shop_id", e.getString("shop_id"));
			ins.set("is_delete", "N");
			ins.set("spu", e.getString("spu"));
			ins.setIf("prod_name", e.getString("prod_name"));
			ins.set("price", e.getString("price"));
			ins.set("buy_number", e.getString("buy_number"));
			ins.setIf("sku", e.getString("sku"));
			ins.setIf("label", e.getString("label"));
			ins.setIf("pic_id", e.getString("pic_id"));
			ins.setIf("propertychildids", e.getString("propertychildids"));
			ins.setSE("cdate", DBUtil.getDBDateString(db.getDBType()));
			sqls.add(ins);
		}

		Insert order = new Insert("mall_order");
		order.set("order_id", orderId);
		order.set("is_delete", "N");
		order.set("user_id", user_id);
		order.set("id", MD5Util.encrypt(db.getUUID().toUpperCase()));
		order.setIf("order_type", ps.getString("order_type"));
		order.setIf("isNeedLogistics", ps.getString("isneedlogistics", "0"));
		order.setIf("amountreal", ps.getString("amountreal", "0"));
		order.setIf("amount", ps.getString("amount", "0"));
		order.setIf("yunprice", ps.getString("yunprice", "0"));
		order.setIf("provinceId", ps.getString("provinceid"));
		order.setIf("cityId", ps.getString("cityid"));
		order.setIf("areaId", ps.getString("areaid"));
		order.setIf("address", ps.getString("address"));
		order.setIf("linkman", ps.getString("linkman"));
		order.setIf("mobile", ps.getString("mobile"));
		order.setIf("calcute", ps.getString("calcute"));
		order.setIf("code", ps.getString("code"));
		order.setIf("status", status);
		order.setIf("is_pay", "N");
		order.setIf("dtl_number", goodsarr.size());
		order.setIf("remark", ps.getString("remark", ""));
		order.setSE("cdate", DBUtil.getDBDateString(db.getDBType()));
		order.setSE("mdate", DBUtil.getDBDateString(db.getDBType()));
		sqls.add(order);

		// 订单日志
		if (recordOrderLog(ORDER_ACTION_CREATE, orderId, "创建订单", user_id)) {
			db.executeSQLList(sqls);
		} else {
			return ResData.FAILURE("创建订单失败,未写入日志");
		}

		return ResData.SUCCESS_OPER();
	}

	public ResData cancelOrder(String user_id, String order_id) {
		Update me = new Update("mall_order");
		me.set("status", ORDER_STATUS_CANCEL);
		me.where().and("order_id=?", order_id);
		System.out.println(me.getSQL());
		// 订单日志
		if (recordOrderLog(ORDER_ACTION_CANCEL, order_id, "取消订单", user_id)) {
			db.execute(me);
		} else {
			return ResData.FAILURE("操作失败,未写入日志");
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 修改订单价格
	 */
	public ResData changeOrderMoney(String user_id, String order_id, String newmoney) {
		if (ToolUtil.isOneEmpty(user_id, order_id, newmoney)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		// 获取原先金额
		String oldmoney = db.uniqueRecord("select * from mall_order where order_id=?", order_id)
				.getString("amountreal");
		if (ToolUtil.isEmpty(oldmoney)) {
			return ResData.FAILURE("原金额不正确");
		}
		Update ups = new Update("mall_order");
		ups.set("amountreal", newmoney);
		ups.setSE("mdate", DBUtil.getDBDateString(db.getDBType()));
		ups.where().and("order_id=?", order_id);

		// 订单日志
		if (recordOrderLog(ORDER_ACTION_CHANGE_MONEY, order_id, "修改价格:" + oldmoney + "-->" + newmoney, user_id)) {
			db.execute(ups);
		} else {
			return ResData.FAILURE("操作失败,未写入日志");
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询我的订单列表
	 */
	public ResData queryMyOrder(String status, String user_id, int pageSize, int pageIndex) {
		JSONObject res = new JSONObject();

		String sql = "select a.*,case status when 1 then '已取消' when 2 then '待付款' when 4 then '待发货'  when 6 then '待收货' when 8 then '待评价' when 10 then '已完成' when 12 then '退货中' when 14 then '退货成功' else '未知' end statusstr from mall_order a where is_delete='N' and user_id =?";
		// status 1,2,3,
		System.out.println(status);
		if (ToolUtil.isNotEmpty(status)) {
			sql += " and status in (" + status + ") ";
		}
		sql += " order by cdate desc";
		RcdSet orderrs = db.query(sql, user_id);
		JSONArray orderarr = ConvertUtil.OtherJSONObjectToFastJSONArray(orderrs.toJsonArrayWithJsonObject());
		res.put("orderlist", orderarr);
		// res.put("logisticsmap", orderarr);
		JSONObject goodsmapobj = new JSONObject();
		for (int i = 0; i < orderrs.size(); i++) {
			String goodsql = "select * from mall_order_detail where order_id=?";
			goodsmapobj.put(orderrs.getRcd(i).getString("id"), ConvertUtil.OtherJSONObjectToFastJSONArray(
					db.query(goodsql, orderrs.getRcd(i).getString("order_id")).toJsonArrayWithJsonObject()));
		}
		res.put("goodsmap", goodsmapobj);
		return ResData.SUCCESS_OPER(res);
	}

	/**
	 * @Description: 查询我的订单详情
	 */
	public ResData queryMyOrderDetail(String orderId) {

		JSONObject res = new JSONObject();
		Rcd rs = db.uniqueRecord(
				"select a.*, case status when 1 then '已取消' when 2 then '待付款' when 4 then '待发货'  when 6 then '待收货' when 8 then '待评价' when 10 then '已完成' when 12 then '退货中' when 14 then '退货成功' else '未知' end statusstr  from mall_order a where order_id=?",
				orderId);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		} else {
			res.put("orderinfo", ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject()));
			String goodsql = "select * from mall_order_detail where order_id=?";
			res.put("goods",
					ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(goodsql, orderId).toJsonArrayWithJsonObject()));
		}
		return ResData.SUCCESS_OPER(res);
	}

	/**
	 * @Description: 订单支付成功后改写订单状态
	 */
	public ResData payOrderFinish(String order_id, String user_id, String next_status) {
		List<String> sqls=new ArrayList<String>();
		
		//更新订单表
		Update ups = new Update("mall_order");
		ups.set("is_pay", "Y");
		ups.setSE("pdate", DBUtil.getDBDateString(db.getDBType()));
		ups.setIf("status", next_status);
		ups.where().and("order_id=?", order_id);
		sqls.add(ups.getSQL());
		
		//更新产品表及减少库存数
		String sql="select * from mall_order_detail where order_id=?";
		RcdSet rs=db.query(sql,order_id);
		for(int i=0;i<rs.size();i++) {
			String spu=rs.getRcd(i).getString("spu");
			String sku=rs.getRcd(i).getString("sku");
			int buy_number=rs.getRcd(i).getInteger("buy_number");
			//如果sku为空则只减小产品的库存
			String prodsql="update product set sales=sales+1,stock=stock-"+buy_number+" where spu='"+spu+"'";
			sqls.add(prodsql);
			if(ToolUtil.isNotEmpty(sku)) {
				String prodskusql="update product_sku set stock=stock-"+buy_number+" where sku='"+sku+"'";
				sqls.add(prodskusql);
			}
		}
		
		// 订单日志
		if (recordOrderLog(ORDER_ACTION_PAY, order_id, "订单支付", user_id)) {
			db.executeStringList(sqls);
		} else {
			return ResData.FAILURE("操作失败,未写入日志");
		}
		return ResData.SUCCESS_OPER();

	}

	/**
	 * @Description: 订单支付成功后结束
	 */
	public ResData payOrderFinishToFinish(String order_id, String user_id, String next_status) {
		return payOrderFinish(order_id, user_id, ORDER_STATUS_FINISH);
	}

	/**
	 * @Description: 订单支付成功后等待发货
	 */
	public ResData payOrderFinishToDelivery(String order_id, String user_id, String next_status) {
		return payOrderFinish(order_id, user_id, ORDER_STATUS_WAITTING_DELIVERY);
	}

	/**
	 * @Description: 订单支付成功后等待评价
	 */
	public ResData payOrderFinishToEvluate(String order_id, String user_id, String next_status) {
		return payOrderFinish(order_id, user_id, ORDER_STATUS_WAITTING_EVALUATE);
	}

	/**
	 * @Description: 订单支付成功
	 */
	public ResData payOrderFinishAuto(String order_id, String user_id) {
		if (queryOrderHasLogistics(order_id)) {
			return payOrderFinish(order_id, user_id, ORDER_STATUS_WAITTING_DELIVERY);
		} else {
			return payOrderFinish(order_id, user_id, ORDER_STATUS_WAITTING_EVALUATE);
		}
	}

	/**
	 * @Description: 订单是否需要物流
	 */
	public boolean queryOrderHasLogistics(String order_id) {
		Rcd rs = db.uniqueRecord("select * from mall_order where order_id=?", order_id);
		if (ToolUtil.isEmpty(rs)) {
			return false;
		} else {
			if (rs.getInteger("isneedlogistics") > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @Description: 订单确认发货
	 */
	public ResData deliveryOrder(String order_id, String user_id) {
		Update me = new Update("mall_order");
		me.set("status", ORDER_STATUS_WAITTING_TAKE);
		me.where().and("order_id=?", order_id);
		// 订单日志
		if (recordOrderLog(ORDER_ACTION_DELIVERY, order_id, "确认发货", user_id)) {
			db.execute(me);
		} else {
			return ResData.FAILURE("操作失败,未写入日志");
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 订单确认收货,if_evaluate是否进入评价阶段
	 */
	public ResData receiptOrder(String order_id, String user_id, Boolean if_evaluate) {
		Update me = new Update("mall_order");
		if (if_evaluate) {
			me.set("status", ORDER_STATUS_WAITTING_EVALUATE);
		} else {
			me.set("status", ORDER_STATUS_FINISH);
		}
		me.where().and("order_id=?", order_id);
		// 订单日志
		if (recordOrderLog(ORDER_ACTION_RECEIPT, order_id, "确认收货", user_id)) {
			db.execute(me);
		} else {
			return ResData.FAILURE("操作失败,未写入日志");
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 对商品评价
	 */
	public ResData reputationGood(String order_id, String reputations, String user_id) {

		if (ToolUtil.isOneEmpty(order_id, user_id, reputations)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		List<SQL> sqls = new ArrayList<SQL>();
		JSONArray pjarr = JSONArray.parseArray(reputations);
		if (ToolUtil.isEmpty(pjarr) || pjarr.size() == 0) {
			return ResData.FAILURE("获取评价失败");
		}
		for (int i = 0; i < pjarr.size(); i++) {
			if (ToolUtil.isEmpty(pjarr.getJSONObject(i).getString("reputation"))) {
				return ResData.FAILURE("未获取评价数据");
			}
			Update me = new Update("mall_order_detail");
			me.set("reputation", pjarr.getJSONObject(i).getString("reputation"));
			me.setIf("ct_evaluate", pjarr.getJSONObject(i).getString("remark"));
			me.where().and("id=?", pjarr.getJSONObject(i).getString("id")).and("order_id=?", order_id);
			sqls.add(me);
		}
		Update ups = new Update("mall_order");
		ups.set("status", ORDER_STATUS_FINISH);
		ups.where().and("order_id=?", order_id);
		sqls.add(ups);
		// 订单日志
		if (recordOrderLog(ORDER_ACTION_REPUTATION, order_id, "评价订单", user_id)) {
			db.executeSQLList(sqls);
		} else {
			return ResData.FAILURE("操作失败,未写入日志");
		}
		return ResData.SUCCESS_OPER();
	}

	/**
	 * @Description: 获取订单统计
	 */
	public ResData queryOrderStatistics(String user_id) {
		JSONObject res = new JSONObject();
		res.put("no_pay", 0);// 未付款 2
		res.put("no_deliver", 0);// 未发货 4
		res.put("no_take", 0);// 未收货 6
		res.put("no_evaluate", 0);// 未评价 8
		res.put("no_returning", 0);// 退货中 12
		String sql = "select status,count(1) cnt from mall_order where is_delete='N' and user_id=? group by status";
		RcdSet rs = db.query(sql, user_id);
		for (int i = 0; i < rs.size(); i++) {
			String status = rs.getRcd(i).getString("status");
			if (status.equals(ORDER_STATUS_WAITTING_PAY)) {
				res.put("no_pay", rs.getRcd(i).getInteger("cnt"));
			} else if (status.equals(ORDER_STATUS_WAITTING_DELIVERY)) {
				res.put("no_deliver", rs.getRcd(i).getInteger("cnt"));
			} else if (status.equals(ORDER_STATUS_WAITTING_TAKE)) {
				res.put("no_take", rs.getRcd(i).getInteger("cnt"));
			} else if (status.equals(ORDER_STATUS_WAITTING_EVALUATE)) {
				res.put("no_evaluate", rs.getRcd(i).getInteger("cnt"));
			} else if (status.equals(ORDER_STATUS_RETURNING)) {
				res.put("no_returning", rs.getRcd(i).getInteger("cnt"));
			}
		}
		return ResData.SUCCESS_OPER(res);
	}

}
