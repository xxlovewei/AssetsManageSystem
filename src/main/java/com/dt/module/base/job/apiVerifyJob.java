package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.module.base.service.ApiService;

/**
 * @author: algernonking
 * @date: Nov 5, 2017 8:56:06 AM
 * @Description: TODO
 */
public class apiVerifyJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(apiVerifyJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	}
}
