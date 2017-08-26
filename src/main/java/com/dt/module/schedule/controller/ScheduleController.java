package com.dt.module.schedule.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
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
	public ResData removejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/pausejob.do")
	@Res
	@Acl
	public ResData pausejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		jobService.pausejob(request.getParameter("seq"));
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/resumejob.do")
	@Res
	@Acl
	public ResData resumejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		jobService.resumejob(request.getParameter("seq"));
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/enablejob.do")
	@Res
	@Acl
	public ResData enablejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		jobService.enableJob(request.getParameter("seq"));
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/disablejob.do")
	@Res
	@Acl
	public ResData disablejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		jobService.disabledJob(request.getParameter("seq"));
		return ResData.SUCCESS_OPER();
	}
	@RequestMapping("/api/schedule/runonce.do")
	@Res
	@Acl
	public ResData runonce(HttpServletRequest request, HttpServletResponse response) throws IOException {
		jobService.runoncejob(request.getParameter("seq"));
		return ResData.SUCCESS_OPER();
	}
}
