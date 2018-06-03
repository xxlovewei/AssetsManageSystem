package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dt.module.base.service.JobService;
import com.dt.module.om.service.WarnService;

/**
 * @author: algernonking
 * @date: 2017年11月9日 下午2:30:20
 * @Description: TODO
 */
public class MnMetricWarnCheckJob implements Job {
	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {

		WarnService.me().queryWarnMetric();
		JobService.me().finishedJobUpdate(jc);
	}
}
