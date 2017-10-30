package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.base.service.RegionService;

@Controller
@RequestMapping(value = "/api")
public class RegionController extends BaseController {
	@Autowired
	private RegionService regionService = null;

	@RequestMapping(value = "/region/queryTree.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData regionqueryTree() {
		return regionService.queryRegion();
	}
	@RequestMapping(value = "/region/getChildren.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData getChildrens(String id) {
		return regionService.queryRegionById(id);
	}
	/**
	 * @Description:获取省份数据
	 */
	@RequestMapping(value = "/qud/queryShengF.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryShengF(String ex) {
		return regionService.queryShengF(null);
	}
	/**
	 * @Description:获取城市数据
	 */
	@RequestMapping(value = "/qud/queryChengS.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryChengS(String exclude, String sfid) {
		return regionService.queryChengS(sfid, null);
	}
	/**
	 * @Description:获取区县数据
	 */
	@RequestMapping(value = "/qud/queryQuX.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryQuX(String exclude, String csid) {
		return regionService.queryQuX(csid, null);
	}
}
