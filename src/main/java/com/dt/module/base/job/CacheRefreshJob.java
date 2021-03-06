package com.dt.module.base.job;

import com.dt.module.base.service.impl.CacheService;
import com.dt.module.base.service.impl.JobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: algernonking
 * @date: 2018年4月4日 下午3:15:52
 * @Description: TODO
 */
public class CacheRefreshJob implements Job {
    private static Logger _log = LoggerFactory.getLogger(CacheRefreshJob.class);

    @Override
    public void execute(JobExecutionContext jc) throws JobExecutionException {
        _log.info("CacheRefreshJob start.");
        CacheService.me().refreshCaches();
        _log.info("CacheRefreshJob end.");
        JobService.me().finishedJobUpdate(jc);
    }


}
