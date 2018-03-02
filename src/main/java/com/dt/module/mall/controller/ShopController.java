package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.mall.service.MyShopService;
import com.dt.module.mall.service.ShopService;

/**
 * @author: algernonking
 * @date: 2017年9月8日 下午4:03:57
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ShopController extends BaseController {
	@Autowired
	private ShopService shopService;

	@Autowired
	private MyShopService myShopService;

	@RequestMapping("/shop/deleteShop.do")
	@Res
	@Acl
	public R deleteShop(String shop_id) {
		return shopService.deleteShop(shop_id);
	}

	@RequestMapping("/shop/queryShop.do")
	@Res
	@Acl
	public R queryShop() {
		return shopService.queryShop();
	}

	@RequestMapping("/shop/queryShopById.do")
	@Res
	@Acl
	public R queryShopById(String shop_id) {
		return shopService.queryShopById(shop_id);
	}

	@RequestMapping("/shop/saveShop.do")
	@Res
	@Acl
	public R saveShop(String shop_id) {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("shop_id");
		if (ToolUtil.isEmpty(id)) {
			return shopService.addShop(ps);
		} else {
			return shopService.updateShop(ps);
		}
	}

	@RequestMapping("/shop/queryMyShop.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R queryMyShop() {
		return myShopService.queryMyShopByUserId(super.getUserId());
	}
}
