package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dt.module.base.schedule.service.JobService;
import com.dt.module.om.service.UrlTouchService;

/**
 * @author: jinjie
 * @date: 2018年4月26日 上午8:47:42
 * @Description: TODO
 */

public class UrlTouchJob implements Job {
	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {

		UrlTouchService.me().executeAllUrls();
		UrlTouchService.me().checkUrlMetricDataForwarnning();
		JobService.me().finishedJobUpdate(jc);
	}
}
