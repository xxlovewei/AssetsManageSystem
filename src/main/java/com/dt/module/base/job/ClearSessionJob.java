package com.dt.module.base.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.module.base.service.impl.JobService;
import com.dt.module.db.DB;

/**
 * @author: algernonking
 * @date: Nov 7, 2017 10:27:05 AM
 * @Description: TODO
 */
public class ClearSessionJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(ClearSessionJob.class);

	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		_log.info("session clear start.");

		JobService.me().finishedJobUpdate(jc);
		_log.info("session clear end.");
	}
}
