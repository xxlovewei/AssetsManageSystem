package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.mall.service.BannerAndItemService;

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
	@ResponseBody
	@Acl(value = Acl.TYPE_ALLOW, info = "查询横幅")
	public R queryBanner(String type) {
		return bannerAndItemService.queryBanner(type);

	}
	
	@RequestMapping("/banner/queryBannerItems.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_ALLOW, info = "查询横幅")
	public R queryBannerItems(String banner_id,String is_used) {
		return bannerAndItemService.queryBannerItems(banner_id,is_used);

	}

	@RequestMapping("/banner/delBannerItem.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_ALLOW, info = "删除横幅条目")
	public R delBannerItem(String id) {
		return bannerAndItemService.delBannerItem(id);

	}
	
	@RequestMapping("/banner/queryBannerItemById.do")
	@ResponseBody
	@Acl(info = "查询横幅条目")
	public R queryBannerItemById(String id) {
		return bannerAndItemService.queryBannerItemById(id);
	}

	@RequestMapping("/banner/saveBannerItem.do")
	@ResponseBody
	@Acl(info = "更新横幅条目")
	public R saveBannerItem() {

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return bannerAndItemService.addBannerItem(ps);
		} else {
			return bannerAndItemService.updateBannerItem(ps);
		}
	}
	

}
