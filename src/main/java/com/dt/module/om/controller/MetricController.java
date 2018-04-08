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
import com.dt.module.om.service.MetricGroupService;
import com.dt.module.om.service.MetricService;

/**
 * @author: algernonking
 * @date: 2018年4月6日 下午8:39:20
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class MetricController extends BaseController {
	@Autowired
	MetricService metricService;

	@Autowired
	MetricGroupService metricGroupService;

	@RequestMapping("/mn/saveMetricGroup.do")
	@ResponseBody
	@Acl(info = "添加更新Metric组", value = Acl.ACL_DENY)
	public R saveMetricGroup() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("id"))) {
			return metricGroupService.addMetricGroup(ps);
		} else {
			return metricGroupService.updateMetricGroup(ps);
		}

	}

	@RequestMapping("/mn/delMetricGroup.do")
	@ResponseBody
	@Acl(info = "删除Metric组", value = Acl.ACL_DENY)
	public R delMetricGroup(String id) {
		return metricGroupService.delMetricGroup(id);
	}

	@RequestMapping("/mn/queryMetricGroup.do")
	@ResponseBody
	@Acl(info = "查询Metric组", value = Acl.ACL_DENY)
	public R queryMetricGroup() {
		return metricGroupService.queryMetricGroup();
	}

	@RequestMapping("/mn/queryMetricGroupById.do")
	@ResponseBody
	@Acl(info = "根据Id查询Metric组", value = Acl.ACL_DENY)
	public R queryMetricGroupById(String id) {
		return metricGroupService.queryMetricGroupById(id);
	}

	@RequestMapping("/mn/delMetric.do")
	@ResponseBody
	@Acl(info = "删除Metric", value = Acl.ACL_DENY)
	public R delMetric(String id) {
		return metricService.delMetric(id);
	}

	@RequestMapping("/mn/saveMetric.do")
	@ResponseBody
	@Acl(info = "添加更新Metric", value = Acl.ACL_DENY)
	public R saveMetric() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("id"))) {
			return metricService.addMetric(ps);
		} else {
			return metricService.updateMetric(ps);
		}

	}

	@RequestMapping("/mn/queryMetricById.do")
	@ResponseBody
	@Acl(info = "根据Id查询Metric", value = Acl.ACL_DENY)
	public R queryMetricById(String id) {
		return metricService.queryMetricById(id);
	}

	@RequestMapping("/mn/queryMetric.do")
	@ResponseBody
	@Acl(info = "查询所有Metric", value = Acl.ACL_DENY)
	public R queryMetric() {
		return metricService.queryMetric();
	}

	@RequestMapping("/mn/queryMetricGroupMetrics.do")
	@ResponseBody
	@Acl(info = "查询所有MetricGroup中的metric", value = Acl.ACL_DENY)
	public R queryMetricGroupMetrics(String id) {
		return metricGroupService.queryMetricGroupMetrics(id);
	}

	@RequestMapping("/mn/metricGroupAddMetrics.do")
	@ResponseBody
	@Acl(info = "添加Metric到MetricGroup中", value = Acl.ACL_DENY)
	public R metricGroupAddMetrics(String id, String ids) {
		return metricGroupService.metricGroupAddMetrics(id, ids);
	}

	@RequestMapping("/mn/metricGroupNeedMetrics.do")
	@ResponseBody
	@Acl(info = "查询所有MetricGroup中的未加入到metric", value = Acl.ACL_DENY)
	public R metricGroupNeedMetrics(String id) {
		return metricGroupService.metricGroupNeedMetrics(id);
	}

	@RequestMapping("/mn/delMetricGroupMetric.do")
	@ResponseBody
	@Acl(info = "删除MetricGroup中的metric", value = Acl.ACL_DENY)
	public R delMetricGroupMetric(String id, String mid) {
		return metricGroupService.delMetricGroupMetric(id, mid);
	}

}
