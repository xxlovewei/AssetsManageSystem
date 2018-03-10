package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.RegionService;

@Controller
@RequestMapping(value = "/api")
public class RegionController extends BaseController {
	@Autowired
	private RegionService regionService = null;

	@RequestMapping(value = "/region/queryTree.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R regionqueryTree() {
		return regionService.queryRegion();
	}
	@RequestMapping(value = "/region/getChildren.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getChildrens(String id) {
		return regionService.queryRegionById(id);
	}
	/**
	 * @Description:获取省份数据
	 */
	@RequestMapping(value = "/qud/queryShengF.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryShengF(String ex) {
		return regionService.queryShengF(null);
	}
	/**
	 * @Description:获取城市数据
	 */
	@RequestMapping(value = "/qud/queryChengS.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryChengS(String exclude, String sfid) {
		return regionService.queryChengS(sfid, null);
	}
	/**
	 * @Description:获取区县数据
	 */
	@RequestMapping(value = "/qud/queryQuX.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryQuX(String exclude, String csid) {
		return regionService.queryQuX(csid, null);
	}
}
