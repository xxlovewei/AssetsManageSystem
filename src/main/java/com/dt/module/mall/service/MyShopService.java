package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年9月8日 下午5:24:24
 * @Description: TODO
 */
@Service
public class MyShopService extends BaseService {
	public R queryMyShopByUserId(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE_ERRREQ_PARAMS();
		}
		return R.SUCCESS_OPER(
				db.query("select * from mall_shop a,mall_myshop b where a.shop_id=b.shop_id and b.user_id=?",user_id)
						.toJsonArrayWithJsonObject());
	}
}
