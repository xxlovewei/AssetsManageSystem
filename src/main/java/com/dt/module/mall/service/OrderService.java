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
		if(ToolUtil.isOneEmpty(status,user_id,ps.getString("totalamount"))){
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		
		List<SQL> sqls=new ArrayList<SQL>();
		String orderId=createOrderId();
		
		//订单详细
		JSONArray goodsarr=JSONArray.parseArray(ps.getString("goodjsonstr","[]"));
		if(ToolUtil.isEmpty(goodsarr)) {
			return ResData.FAILURE("创建订单失败,解析失败"); 
		}
		if(goodsarr.size()==0) {
			return ResData.FAILURE("创建订单失败,解析产品失败"); 
		}
		for(int i=0;i<goodsarr.size();i++) {
			Insert ins=new Insert("mall_order_detail");
			JSONObject e=goodsarr.getJSONObject(i);
			System.out.println(e.toJSONString());
			ins.set("id",db.getUUID() );
			ins.set("order_id", orderId);
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
		order.set("order_id",orderId );
		order.set("is_delete", "N");
		order.set("user_id", user_id);
		order.set("id", MD5Util.encrypt(db.getUUID().toUpperCase()));
		order.setIf("order_type",  ps.getString("order_type"));
		order.setIf("isNeedLogistics",  ps.getString("isneedlogistics","0"));
		order.setIf("totalamount",  ps.getString("totalamount","0"));
		order.setIf("provinceId",  ps.getString("provinceid"));
		order.setIf("cityId",  ps.getString("cityid"));
		order.setIf("areaId",  ps.getString("areaid"));
		order.setIf("address",  ps.getString("address"));
		order.setIf("linkman",  ps.getString("linkman"));
		order.setIf("mobile",  ps.getString("mobile"));
		order.setIf("calcute",  ps.getString("calcute"));
		order.setIf("code",  ps.getString("code"));
		order.setIf("status",  status);
		order.setIf("dtl_number",  goodsarr.size());
		order.setIf("remark",  ps.getString("remark",""));
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
		res.put("orderlist", orderarr);
		
		res.put("logisticsmap", orderarr);
		res.put("goodsmap", orderarr);
		
		
		return ResData.SUCCESS_OPER(res);
	}
	
	

}
