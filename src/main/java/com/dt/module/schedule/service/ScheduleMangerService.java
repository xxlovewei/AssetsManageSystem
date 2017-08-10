package com.dt.module.schedule.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.util.SpringContextUtil;
import com.dt.core.common.util.support.ReflectKit;
import com.dt.core.db.DB;
import com.dt.module.schedule.entity.ScheduleJob;
import com.google.common.collect.Maps;



@Service
public class ScheduleMangerService {

	
	@Autowired
	private DB db;
	private   SchedulerFactory sf = new StdSchedulerFactory();
	
	
	public static ScheduleMangerService me() {
		return SpringContextUtil.getBean(ScheduleMangerService.class);
	}
	

	public  void jobInitLoadFromDb() {
		String sql = "select * from sys_job where jobenable='true'";
		RcdSet res=db.query(sql);
		for(int i=0;i<res.size();i++){
			ScheduleJob job = new ScheduleJob();
			job.setJobSeq(res.getRcd(i).getString("seq"));
			job.setCronExpression(res.getRcd(i).getString("jobcron"));
			job.setJobGroup(res.getRcd(i).getString("jobgroup"));
			job.setJobName(res.getRcd(i).getString("jobname"));
			job.setJobClassName(res.getRcd(i).getString("jobclassname"));
			
			jobAdd(job);

		}

	}

	public void scheduleStop() {
		try {
			sf.getScheduler().shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void schedulegGetStatus() {
		try {
			Boolean  isShutdown  = sf.getScheduler().isShutdown();
			if (isShutdown) {
				System.out.println("this is stop");
			} else {
				System.out.println("this is start");
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void scheduleStart() {
		try {
			sf.getScheduler().start();;
	 
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public  JSONArray getJobAll() {
		/* 初始化后运行 */
		String sql = " select t.*,'本地执行' nodename from sys_job t where   jobtype <>'node'  ";                        
		 
		Map<String, ScheduleJob> jobs = Maps.newLinkedHashMap();
	 
		RcdSet res=db.query(sql);
		for (int i = 0; i < res.size(); i++) {
			ScheduleJob job = new ScheduleJob();
			 
			job.setJobSeq(res.getRcd(i).getString("seq").toString());
			job.setNode(res.getRcd(i).getString("node").toString());
			job.setNodeName(res.getRcd(i).getString("nodename").toString());
			job.setJobName(res.getRcd(i).getString("jobname").toString());
			job.setJobGroup(res.getRcd(i).getString("jobgroup").toString());
			job.setJobClassName(res.getRcd(i).getString("jobclassname").toString());
			job.setCronExpression(res.getRcd(i).getString("jobcron").toString());
			job.setJobType(res.getRcd(i).getString("jobtype").toString());
			job.setJobEnable(res.getRcd(i).getString("jobenable").toString());
			job.setJobDesc(res.getRcd(i).getString("mark").toString());
			job.setJobSource("tab");
			jobs.put(res.getRcd(i).getString("seq").toString(), job);
		}
 

		Scheduler scheduler;
		try {
			scheduler = sf.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					jobKey.getName().substring(jobKey.getName().indexOf("#idle#"));
					String seqtmp = jobKey.getName().substring(jobKey.getName().indexOf("#idle#") + 6);
					if (jobs.containsKey(seqtmp)) {
						jobs.get(seqtmp).setJobPlanStatus(triggerState.name());
						jobs.get(seqtmp).setJobTrigger(trigger.getKey() + "");
					} else {
						ScheduleJob job = new ScheduleJob();
						//jobnametest#idle#16051e6d2fdbf49cc3b7b58b57a6acaf
						job.setJobPlanStatus(triggerState.name());
						job.setJobTrigger(trigger.getKey() + "");
						job.setJobGroup(jobKey.getGroup());
						job.setJobName(jobKey.getName().replaceFirst(seqtmp, "").replaceFirst("#idle#", ""));
						job.setJobSeq(seqtmp);
						job.setJobSource("scheplan");
						job.setJobEnable("true");
						if (trigger instanceof CronTrigger) {
							CronTrigger cronTrigger = (CronTrigger) trigger;
							String cronExpression = cronTrigger.getCronExpression();
							job.setCronExpression(cronExpression);
						}
						jobs.put(seqtmp, job);
					}
				}
			}// end JobKey

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray data = new JSONArray();
		JSONObject object = null;
		Iterator iterator2 = jobs.keySet().iterator();
		while (iterator2.hasNext()) {
			object = new JSONObject();
			Object o = iterator2.next();
			String key = (String) o;
			ScheduleJob value = (ScheduleJob) jobs.get(key);
			// System.out.println(key + ":" + "=" + value.getJobPlanStatus() +
			// value.getJobtrigger());
			object.put("seq", value.getJobSeq());
			object.put("node", value.getNode());
			object.put("nodename", value.getNodeName());
			object.put("jobname", value.getJobTrueName());
			object.put("jobgroup", value.getJobGroup());
			object.put("jobcron", value.getCronExpression());
			object.put("jobcontent", value.getCronExpression() + "  " + value.getJobClassName());
			object.put("jobclassname", value.getJobClassName());
			object.put("jobenable", value.getJobEnable());
			object.put("jobrunstatus", value.getJobPlanStatus());
			object.put("jobtrigger", value.getJobTrigger());
			object.put("jobtype", value.getJobType());
			object.put("mark", value.getJobDesc());
			data.add(object);
		}
		return data;
	}

	public void jobInfo(ScheduleJob job) {
		Scheduler scheduler;
		try {
			scheduler = sf.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
			System.out.println("this job " + scheduler.checkExists(jobKey));
			System.out.println("this groupnames " + scheduler.getJobGroupNames());

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 

	public JSONObject jobStatus(ScheduleJob job) {
		JSONObject object = new JSONObject();
		object.put("jobPlanStatus", "");
		Scheduler scheduler;
		try {
			scheduler = sf.getScheduler();

			JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

			if (triggers.size() == 1) {
				Trigger.TriggerState triggerState = scheduler.getTriggerState(triggers.get(0).getKey());
				String status = triggerState.name();
				object.put("jobPlanStatus", status);
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	
	public boolean jobAdd(ScheduleJob job) {
		
		if(jobExist(job)){
			return true;
		}
		
		Scheduler scheduler;
		try {
			scheduler = sf.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobRunName(), job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				/* 创建JobDetail */
				Class<Job> jobjf = ReflectKit.on(job.getJobClassName()).get();
				JobDetail jobDetail = ReflectKit.on("org.quartz.JobBuilder").call("newJob", jobjf.newInstance().getClass()).call("withIdentity", job.getJobRunName(), job.getJobGroup()).call("build")
						.get();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				/* 表达式调度构建 */
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				/* 按新的cronExpression表达式构建一个新的trigger */
				trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobRunName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
				return true;
			} else {
				System.out.println("exits");
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void jobDel(ScheduleJob job) {
		JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
		try {
			Scheduler scheduler = sf.getScheduler();
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
	public void jobTriggerRun(ScheduleJob job) {

		JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
		try {
			Scheduler scheduler = sf.getScheduler();
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
//
	public void jobPause(ScheduleJob job) {
		JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
		try {
			Scheduler scheduler = sf.getScheduler();
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean jobExist(ScheduleJob job) {
		boolean returnvalue = false;
		JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
		try {
			Scheduler scheduler = sf.getScheduler();
			returnvalue = scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnvalue;
	}

	public void jobResume(ScheduleJob job) {
		JobKey jobKey = JobKey.jobKey(job.getJobRunName(), job.getJobGroup());
		try {
			Scheduler scheduler = sf.getScheduler();
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}