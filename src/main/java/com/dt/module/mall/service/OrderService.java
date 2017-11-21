package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:32:54
 * @Description: TODO
 */
@Service
public class OrderService extends BaseService {

	public static String ORDER_STATUS_WAITTING_CANCEL = "1";// 取消
	public static String ORDER_STATUS_WAITTING_PAY = "2";// 待付款
	public static String ORDER_STATUS_WAITTING_DELIVERY = "4";// 待收款
	public static String ORDER_STATUS_WAITTING_TAKE = "6";// 待收货
	public static String ORDER_STATUS_WAITTING_EVALUATE = "8";// 待评价
	public static String ORDER_STATUS_WAITTING_FINISH = "10";// 已完成

	
	
	public ResData createOrder() {
		return null;
	}

	public ResData cancelOrder() {
		return null;
	}

	public ResData changeOrderMoney() {
		return null;
	}

	public ResData changeOrderStatus() {
		return null;
	}

}
