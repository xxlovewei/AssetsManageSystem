package com.dt.module.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.om.service.ResourceService;
import com.dt.module.om.service.WarnService;

/**
 * @author: algernonking
 * @date: 2018年4月8日 上午8:58:28
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ResourceController extends BaseController {
	@Autowired
	WarnService warnService;

	@Autowired
	ResourceService resourceService;

	@RequestMapping("/mn/queryMenus.do")
	@ResponseBody
	@Acl(info = "查询资源左边的清单", value = Acl.ACL_ALLOW)
	public R queryMenus() {
		return resourceService.queryMenus();
	}

	@RequestMapping("/mn/queryResourceByMetric.do")
	@ResponseBody
	@Acl(info = "查询资源Metric数据", value = Acl.ACL_ALLOW)
	public R queryResourceByMetric(String node_id, String metric_id, String data_interval) {
		return resourceService.queryResourceByMetric(node_id, metric_id, data_interval);
	}

	@RequestMapping("/mn/queryWarnDataForDashboard.do")
	@ResponseBody
	@Acl(info = "查询告警处理的数据", value = Acl.ACL_ALLOW)
	public R queryWarnDataForDashboard(String day) {
		return warnService.queryWarnDataForDashboard(day);
	}

	@RequestMapping("/mn/deleteWarnData.do")
	@ResponseBody
	@Acl(info = "删除告警处理的数据", value = Acl.ACL_ALLOW)
	public R deleteWarnData(String id) {
		return warnService.deleteWarnData(id);
	}

}
