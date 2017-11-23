package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.mall.service.OrderService;
import com.dt.module.mall.service.PayService;

/**
 * @author: algernonking
 * @date: 2017年11月23日 上午9:30:03
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class PayController extends BaseController {
	@Autowired
	private PayService payService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/pay/toPay.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "支付")
	public ResData toPay(String order_id) {
		// 仅仅测试
		return orderService.payOrderFinishAuto(order_id, getUserId());
	}
}
