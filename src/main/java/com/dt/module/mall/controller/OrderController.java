package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.mall.service.OrderService;
import com.dt.module.mall.service.ShopService;

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
		return orderService.createOrder(ps,getUserId(),OrderService.ORDER_STATUS_WAITTING_PAY);
	}
	

	@RequestMapping(value = "/order/cancelOrder.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "取消订单")
	public ResData cancelOrder(String order_id) {
		return null;
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


	 
	
}

