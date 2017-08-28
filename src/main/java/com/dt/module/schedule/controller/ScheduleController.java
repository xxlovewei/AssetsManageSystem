package com.dt.module.schedule.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.module.schedule.service.JobService;

@Controller
public class ScheduleController extends BaseController {
	@Autowired
	private JobService jobService = null;

	@RequestMapping("/api/schedule/queryJobs.do")
	@Res
	@Acl
	public Object queryJobs() {
		return ResData.SUCCESS_OPER(jobService.queryJob(""));
	}
	@RequestMapping("/api/schedule/removejob.do")
	@Res
	@Acl
	public ResData removejob() throws IOException {
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/pausejob.do")
	@Res
	@Acl
	public ResData pausejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.pausejob(seq);
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/resumejob.do")
	@Res
	@Acl
	public ResData resumejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.resumejob(seq);
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/enablejob.do")
	@Res
	@Acl
	public ResData enablejob(String seq) {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.enableJob(seq);
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/disablejob.do")
	@Res
	@Acl
	public ResData disablejob(String seq) throws IOException {
		if (ToolUtil.isEmpty(seq)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		jobService.disabledJob(seq);
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/runonce.do")
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
