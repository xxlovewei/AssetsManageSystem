package com.dt.module.base.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.module.base.service.ApiService;

/**
 * @author 作者 Lank
 * @version 创建时间：2017年8月1日 下午7:45:13 类说明
 */
public class CollectApiUrlJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(CollectApiUrlJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		_log.info("collect urls start.");
		ApiService.me().updateApi();
		_log.info("collect urls end.");
	}
}
