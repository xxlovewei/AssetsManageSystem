package com.dt.module.mall.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.SQL;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.MD5Util;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:32:54
 * @Description: TODO
 */
@Service
public class OrderService extends BaseService {

	public static String ORDER_STATUS_CANCEL = "1";// 已取消
	public static String ORDER_STATUS_WAITTING_PAY = "2";// 待付款
	public static String ORDER_STATUS_WAITTING_DELIVERY = "4";// 待收款
	public static String ORDER_STATUS_WAITTING_TAKE = "6";// 待收货
	public static String ORDER_STATUS_WAITTING_EVALUATE = "8";// 待评价
	public static String ORDER_STATUS_FINISH = "10";// 已完成
	public static String ORDER_STATUS_RETURNING = "12";// 退货中
	public static String ORDER_STATUS_RETURN_FINISH = "14";// 退货成功
	
	public static String ORDER_ACTION_CREATE="create";
	public static String ORDER_ACTION_CANCEL="cancel";
	public static String ORDER_ACTION_CHANGE_MONEY="change_money";
	public static String ORDER_ACTION_CHANGE_STATUS="change_status";
	public static String ORDER_ACTION_UNKNOW="unknow";
	public Boolean recordOrderLog(String action,String order_id,String ct,String user_id) {
		Insert me=new Insert("mall_order_log");
		me.set("id", db.getUUID());
		me.set("order_id", order_id);
		me.set("user_id", user_id);
		me.setIf("action", action);
		me.setIf("ct", ct);
		me.setSE("cdate", DBUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return true;
	}
	
	private String createOrderId() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地
		String str = dateFormat.format(now) +MD5Util.encrypt(db.getUUID());
		return str;
	}

	public ResData createOrder(TypedHashMap<String, Object> ps,String user_id,String status) {
		//创建订单
		if(ToolUtil.isOneEmpty(status,user_id,ps.getString("TOTALAMOUNT"))){
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		
		List<SQL> sqls=new ArrayList<SQL>();
		String orderId=createOrderId();
		
		//订单详细
		JSONArray goodsarr=JSONArray.parseArray(ps.getString("GOODJSONSTR","[]"));
		if(ToolUtil.isEmpty(goodsarr)) {
			return ResData.FAILURE("创建订单失败,解析失败"); 
		}
		if(goodsarr.size()==0) {
			return ResData.FAILURE("创建订单失败,解析产品失败"); 
		}
		for(int i=0;i<goodsarr.size();i++) {
			Insert ins=new Insert("mall_order_detail");
			JSONObject e=goodsarr.getJSONObject(i);
			ins.set("id",db.getUUID() );
			ins.set("order_id", orderId);
			ins.set("is_delete", "N");
			ins.set("spu", e.getString("SPU"));
			ins.setIf("prod_name", e.getString("PROD_NAME"));
			ins.set("price", e.getString("PRICE"));
			ins.set("buy_number", e.getString("BUY_NUMBER"));
			ins.setIf("sku", e.getString("SKU"));
			ins.setIf("label", e.getString("LABEL"));
			ins.setIf("master_pic", e.getString("MASTER_PIC"));
			ins.setIf("propertyChildIds", e.getString("PROPERTYCHILDIDS"));
			ins.setSE("cdate", DBUtil.getDBDateString(db.getDBType()));
			sqls.add(ins);	
		}
		
		
		Insert order = new Insert("mall_order");
		order.set("order_id",orderId );
		order.set("is_delete", "N");
		order.set("user_id", user_id);
		order.set("id", MD5Util.encrypt(db.getUUID().toUpperCase()));
		order.setIf("order_type",  ps.getString("ORDER_TYPE"));
		order.setIf("isNeedLogistics",  ps.getString("ISNEEDLOGISTICS","0"));
		order.setIf("totalamount",  ps.getString("TOTALAMOUNT","0"));
		order.setIf("provinceId",  ps.getString("PROVINCEID"));
		order.setIf("cityId",  ps.getString("CITYID"));
		order.setIf("areaId",  ps.getString("AREAID"));
		order.setIf("address",  ps.getString("ADDRESS"));
		order.setIf("linkman",  ps.getString("LINKMAN"));
		order.setIf("mobile",  ps.getString("MOBILE"));
		order.setIf("calcute",  ps.getString("CALCUTE"));
		order.setIf("code",  ps.getString("CODE"));
		order.setIf("status",  status);
		order.setIf("dtl_number",  goodsarr.size());
		order.setIf("remark",  ps.getString("REMARK",""));
		order.setSE("cdate", DBUtil.getDBDateString(db.getDBType()));
		order.setSE("mdate", DBUtil.getDBDateString(db.getDBType()));
		sqls.add(order);
		
		//订单日志
		if(recordOrderLog(ORDER_ACTION_CREATE,orderId,"create order.",user_id)) {
			db.executeSQLList(sqls);
		}else {
			return ResData.FAILURE("创建订单失败,未写入日志");
		}
		
		return ResData.SUCCESS_OPER();
	}

	public ResData cancelOrder(String user_id,String order_id) {
		Update me=new Update("mall_order");
		me.set("status", ORDER_STATUS_CANCEL);
		me.where().and("user_id=?", user_id).and("order_id=?",order_id);
		System.out.println(me.getSQL());
		db.execute(me);
		recordOrderLog(ORDER_ACTION_CREATE,order_id,"create order.",user_id);
		return ResData.SUCCESS_OPER();
	}

	public ResData changeOrderMoney() {
		return null;
	}

	public ResData changeOrderStatus() {
		return null;
	}
	
	public ResData queryOrder(String status,int pageSize, int pageIndex) {
		JSONObject res=new JSONObject();
		
		String sql="select * from mall_order where is_delete='N' ";
		RcdSet orderrs=db.query(sql);
		JSONArray orderarr=ConvertUtil.OtherJSONObjectToFastJSONArray(orderrs.toJsonArrayWithJsonObject());
		res.put("orderList", orderarr);
		
		res.put("logisticsMap", orderarr);
		res.put("goodsMap", orderarr);
		
		
		return ResData.SUCCESS_OPER(res);
	}
	
	

}
