package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.db.DB;
import com.dt.module.schedule.service.JobService;

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
		DB.instance().execute("delete from sys_session where user_id is null and sysdate-lastaccess>3");
		JobService.me().finishedJobUpdate(jc);
		_log.info("session clear end.");
	}
}
