package com.dt.module.base.schedule.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
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
		return ResData.SUCCESS_OPER(jobService.queryJob(type, getUserId()));
	}

	@RequestMapping("/schedule/removejob.do")
	@Res
	@Acl
	public ResData removejob() throws IOException {
		return ResData.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/pausejob.do")
	@Res
	@Acl
	public ResData pausejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.pausejob(seq);
		return ResData.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/resumejob.do")
	@Res
	@Acl
	public ResData resumejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.resumejob(seq);
		return ResData.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/enablejob.do")
	@Res
	@Acl
	public ResData enablejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.enableJob(seq);
		return ResData.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/disablejob.do")
	@Res
	@Acl
	public ResData disablejob(String seq) throws IOException {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.disabledJob(seq);
		return ResData.SUCCESS_OPER();
	}

	@RequestMapping("/schedule/runonce.do")
	@Res
	@Acl
	public ResData runonce(String seq) throws IOException {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.runoncejob(seq);
		return ResData.SUCCESS_OPER();
	}
}
