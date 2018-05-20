package com.dt.module.shop.controller;

import com.dt.core.common.base.BaseController;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年5月20日 下午3:21:17
 * @Description: TODO
 */
public class BaseShopController extends BaseController {

	public String getShopUserId() {
		// 按理说肯定能拿到user_id;
		String user_id = getUserId();
		if (ToolUtil.isNotEmpty(user_id)) {
			return getUserId();
		} else {
			// 获取open_id无用户则注册用户
			return "";
		}

	}
}
