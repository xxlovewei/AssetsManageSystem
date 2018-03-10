package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.mall.service.OrderService;

/**
 * @author: algernonking
 * @date: 2017年11月23日 上午9:30:03
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class PayController extends BaseController {
//	@Autowired
//	private PayService payService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/pay/toPay.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "支付")
	public R toPay(String order_id) {
		// 仅仅测试
		return orderService.payOrderFinishAuto(order_id, getUserId());
	}
}
