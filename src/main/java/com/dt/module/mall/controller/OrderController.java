package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.mall.service.OrderService;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:32:29
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order/createOrder.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "创建订单")
	public ResData createOrder() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return orderService.createOrder(ps, getUserId(), OrderService.ORDER_STATUS_WAITTING_PAY);
	}

	@RequestMapping(value = "/order/cancelOrder.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "取消订单")
	public ResData cancelOrder(String order_id) {
		return orderService.cancelOrder(getUserId(), order_id);

	}

	@RequestMapping(value = "/order/changeOrderMoney.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "修改订单金额")
	public ResData changeOrderMoney() {
		return null;
	}

	@RequestMapping(value = "/order/changeOrderStatus.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "修改订单状态")
	public ResData changeOrderStatus() {
		return null;
	}

	@RequestMapping(value = "/order/queryMyOrder.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "查询个人订单")
	public ResData queryMyOrder(String status, String pageSize, String pageIndex) {
		return orderService.queryMyOrder(status, getUserId(), ConvertUtil.toInt(pageSize, 100),
				ConvertUtil.toInt(pageIndex, 1));
	}

	@RequestMapping(value = "/order/queryMyOrderDetail.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "查询个人某个订单")
	public ResData queryMyOrderDetail(String order_id) {
		return orderService.queryMyOrderDetail(order_id);
	}

	@RequestMapping(value = "/order/deliveryOrder.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "确认发货")
	public ResData deliveryOrder(String order_id) {
		return orderService.deliveryOrder(order_id, getUserId());
	}

	@RequestMapping(value = "/order/receiptOrder.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "确认收货")
	public ResData receiptOrder(String order_id) {
		return orderService.receiptOrder(order_id, getUserId(),true);
	}

	@RequestMapping(value = "/order/reputationGoods.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "商品评价")
	public ResData reputationGood(String order_id, String reputations) {
		return orderService.reputationGood(order_id, reputations, this.getUserId());
	}

	@RequestMapping(value = "/order/queryOrderStatistics.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "获取订单状态统计")
	public ResData queryOrderStatistics() {
		return orderService.queryOrderStatistics(getUserId());
	}

}
