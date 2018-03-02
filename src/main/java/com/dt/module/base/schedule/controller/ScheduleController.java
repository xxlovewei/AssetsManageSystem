package com.dt.module.base.schedule.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.schedule.service.JobService;

@Controller
@RequestMapping("/api")
public class ScheduleController extends BaseController {
	@Autowired
	private JobService jobService = null;

	@RequestMapping("/schedule/queryJobs.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "查询任务")
	public Object queryJobs(String type) {
		return R.SUCCESS_OPER(jobService.queryJob(type, getUserId()));
	}

	@RequestMapping("/schedule/removejob.do")
	@Res
	@Acl
	public R removejob() throws IOException {
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/pausejob.do")
	@Res
	@Acl
	public R pausejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		jobService.pausejob(seq);
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/resumejob.do")
	@Res
	@Acl
	public R resumejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		jobService.resumejob(seq);
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/enablejob.do")
	@Res
	@Acl
	public R enablejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		jobService.enableJob(seq);
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/disablejob.do")
	@Res
	@Acl
	public R disablejob(String seq) throws IOException {
		if (ToolUtil.isEmpty(seq)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		jobService.disabledJob(seq);
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/runonce.do")
	@Res
	@Acl
	public R runonce(String seq) throws IOException {
		if (ToolUtil.isEmpty(seq)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		jobService.runoncejob(seq);
		return R.SUCCESS_OPER();
	}
}
