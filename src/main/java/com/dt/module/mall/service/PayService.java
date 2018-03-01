package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.ResData;
import com.dt.core.dao.sql.Update;

/**
 * @author: algernonking
 * @date: 2017年11月23日 上午9:29:46
 * @Description: TODO
 */
@Service
public class PayService extends BaseService {

	public ResData toPay(String order_id) {
		Update me = new Update("mall_order");
		me.set("status", OrderService.ORDER_STATUS_WAITTING_DELIVERY);
		me.set("is_pay", "Y");
		me.where().and("order_id=?", order_id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

}
