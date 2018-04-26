package com.dt.module.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.om.service.UrlMetricService;
import com.dt.module.om.service.UrlTouchService;

/**
 * @author: jinjie
 * @date: 2018年4月26日 上午10:03:01
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class UrlMetricController extends BaseController {
	@Autowired
	UrlTouchService urlTouchService;
	@Autowired
	UrlMetricService urlMetricService;

	@RequestMapping("/mn/urlTouchExample.do")
	@ResponseBody
	@Acl(info = "测试例子", value = Acl.ACL_ALLOW)
	public R urlTouchExample() {
		return urlTouchService.touchUrlExample();
	}

	@RequestMapping("/mn/queryUrlMetricData.do")
	@ResponseBody
	@Acl(info = "查询UrlMetric数据", value = Acl.ACL_ALLOW)
	public R queryUrlMetricData() {
		return urlMetricService.queryUrlMetricsWithData();
	}
}
