package com.dt.module.schedule.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author 作者 Lank 
* @version 创建时间：2017年8月1日 下午7:45:13 
* 类说明 
*/

public class JobExample implements Job {
 
	private static Logger _log = LoggerFactory.getLogger(JobExample.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		System.out.println("testA"+df.format(new Date()));
		_log.info("hello");
		try {
	        Thread.sleep(30000);//括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
	} catch (InterruptedException e) {
	        e.printStackTrace();
	}
	}
}
