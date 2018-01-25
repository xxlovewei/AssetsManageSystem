package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.mall.service.BannerAndItemService;
import com.dt.tool.lang.TypedHashMap;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:29:24
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class BannerAndItemController extends BaseController {

	@Autowired
	BannerAndItemService bannerAndItemService = null;

	
	@RequestMapping("/banner/queryBanner.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询横幅")
	public ResData queryBanner(String type) {
		return bannerAndItemService.queryBanner(type);

	}
	
	@RequestMapping("/banner/queryBannerItems.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询横幅")
	public ResData queryBannerItems(String banner_id,String is_used) {
		return bannerAndItemService.queryBannerItems(banner_id,is_used);

	}

	@RequestMapping("/banner/delBannerItem.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "删除横幅条目")
	public ResData delBannerItem(String id) {
		return bannerAndItemService.delBannerItem(id);

	}
	
	@RequestMapping("/banner/queryBannerItemById.do")
	@Res
	@Acl(info = "查询横幅条目")
	public ResData queryBannerItemById(String id) {
		return bannerAndItemService.queryBannerItemById(id);
	}

	@RequestMapping("/banner/saveBannerItem.do")
	@Res
	@Acl(info = "更新横幅条目")
	public ResData saveBannerItem() {

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return bannerAndItemService.addBannerItem(ps);
		} else {
			return bannerAndItemService.updateBannerItem(ps);
		}
	}
	

}
