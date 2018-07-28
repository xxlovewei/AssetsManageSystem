package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.module.base.service.impl.JobService;

/** 
 * @author: algernonking
 * @date: 2017年11月9日 下午2:34:37 
 * @Description: TODO 
 */
public class CollectApiJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(CollectApiJob.class);

	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		_log.info("collect urls start.");
		//ApiService.me().updateApi();
		JobService.me().finishedJobUpdate(jc);
	}
}


