package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.PositionService;

/**
 * @author: algernonking
 * @date: 2018年5月16日 下午7:32:38
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class PositionController extends BaseController {
	@Autowired
	PositionService positionService;

	@RequestMapping(value = "/position/queryAddress.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "通过经纬度查询行政地址")
	public R queryAddress(String lat, String lng) {
		return positionService.queryZone(lat, lng);
	}
}
