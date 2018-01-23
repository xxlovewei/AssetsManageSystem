package com.dt.module.base.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dt.core.common.util.MD5Util;
import com.dt.module.db.DB;
import com.dt.module.schedule.service.JobService;

/**
 * @author: algernonking
 * @date: 2017年11月9日 下午2:30:20
 * @Description: TODO
 */
public class BackupModuleJob implements Job {
	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {

		String sql = "insert into sys_modules_item_history select t.*,sysdate,'"
				+ MD5Util.encrypt(new Date().getTime() + "") + "' from sys_modules_item t";
		DB.instance().execute(sql);
		JobService.me().finishedJobUpdate(jc);
	}
}
