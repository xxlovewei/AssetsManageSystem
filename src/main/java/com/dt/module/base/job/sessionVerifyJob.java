package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.module.base.service.ApiService;

/**
 * @author: algernonking
 * @date: Nov 7, 2017 10:27:05 AM
 * @Description: TODO
 */
public class sessionVerifyJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(sessionVerifyJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		_log.info("collect urls start.");
		ApiService.me().updateApi();
		_log.info("collect urls end.");
	}
}
