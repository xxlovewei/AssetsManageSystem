package com.dt.module.om.controller;

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

	@RequestMapping("/mn/om/urlTouchExample.do")
	@ResponseBody
	@Acl(info = "测试例子", value = Acl.ACL_ALLOW)
	public R urlTouchExample() {
		return urlTouchService.touchUrlExample();
	}

	@RequestMapping("/mn/om/queryUrlMetricById.do")
	@ResponseBody
	@Acl(info = "查询UrlMetric数据", value = Acl.ACL_ALLOW)
	public R queryUrlMetricById(String node) {
		return urlMetricService.queryUrlMetricById(node);
	}

	@RequestMapping("/mn/om/queryUrlMetricData.do")
	@ResponseBody
	@Acl(info = "查询UrlMetric数据", value = Acl.ACL_ALLOW)
	public R queryUrlMetricData() {
		return urlMetricService.queryUrlMetricsWithData();
	}

	@RequestMapping("/mn/om/deleteUrlMetric.do")
	@ResponseBody
	@Acl(info = "删除UrlMetric数据", value = Acl.ACL_DENY)
	public R deleteUrlMetric(String node) {
		return urlMetricService.deleteUrlMetric(node);
	}

	@RequestMapping("/mn/om/saveUrlMetric.do")
	@ResponseBody
	@Acl(info = "保存UrlMetric数据", value = Acl.ACL_DENY)
	public R saveUrlMetric(String node) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("node"))) {
			return urlMetricService.addUrlMetric(ps);
		} else {
			return urlMetricService.updateUrlMetric(ps);
		}
	}

	@RequestMapping("/mn/om/queryTouchMetricData.do")
	@ResponseBody
	@Acl(info = "查询UrlMetric数据", value = Acl.ACL_ALLOW)
	public R queryTouchMetricData(String node, String type, String time) {
		return urlTouchService.queryTouchMetricData(node, type, time);
	}

}
