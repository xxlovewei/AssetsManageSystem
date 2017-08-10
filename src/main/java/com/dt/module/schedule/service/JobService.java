package com.dt.module.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.dao.Rcd;
import com.dt.core.db.DB;
import com.dt.module.schedule.entity.ScheduleJob;

/**
 * @author 作者 Lank
 * @version 创建时间：2017年8月1日 下午5:50:44 类说明
 */

@Service
public class JobService {

	@Autowired
	ScheduleMangerService scheduleMangerService = null;

	@Autowired
	DB db;

	public JSONArray queryJob(String type) {
		JSONArray data = null;

		
		data = scheduleMangerService.getJobAll();

		return data;
	}

	private ScheduleJob getScheduleJob(String seq) {
		String sql = "select * from sys_job where seq=?";

		ScheduleJob job = new ScheduleJob();
		job.setJobInstanceValid(false);
		Rcd res = db.uniqueRecord(sql,seq);
		if (res != null) {

			job.setJobSeq(res.getString("seq").toString());
			job.setCronExpression(res.getString("jobcron").toString());
			job.setJobGroup(res.getString("jobgroup").toString());
			job.setJobName(res.getString("jobname").toString());
			job.setJobClassName(res.getString("jobclassname").toString());
			job.setJobInstanceValid(true);
		}

		return job;
	}

	public JSONObject enableJob(String seq) {
		ScheduleJob job = getScheduleJob(seq);
		if (job.isJobInstanceValid()) {
			db.execute("update sys_job set jobenable='true' where seq=? ",seq);
			scheduleMangerService.jobAdd(job);
		}
		return scheduleMangerService.jobStatus(job);
	}

	public JSONObject disabledJob(String seq) {
		ScheduleJob job = getScheduleJob(seq);
		if (job.isJobInstanceValid()) {
			db.execute("update sys_job set jobenable='false' where seq= ?", seq);
			scheduleMangerService.jobDel(job);
		}
		return scheduleMangerService.jobStatus(job);
	}

	public void removejob(String seq, String jobname, String jobgroupname) {
		ScheduleJob job = getScheduleJob(seq);
		/* 数据库中删除 */
		db.execute("delete from sys_job where jobtype<>'sys' and seq=?", seq);

		job.setJobSeq(seq);
		job.setJobGroup(jobgroupname);
		job.setJobName(jobname);
		scheduleMangerService.jobDel(job);
	}

	public JSONObject pausejob(String seq) {
		ScheduleJob job = getScheduleJob(seq);
		if (job.isJobInstanceValid()) {
			scheduleMangerService.jobPause(job);
		}
		return scheduleMangerService.jobStatus(job);
	}

	public JSONObject resumejob(String seq) {
		ScheduleJob job = getScheduleJob(seq);
		if (job.isJobInstanceValid()) {
			scheduleMangerService.jobResume(job);
		}
		return scheduleMangerService.jobStatus(job);

	}

	public void runoncejob(String seq) {

		ScheduleJob job = getScheduleJob(seq);
		if (job.isJobInstanceValid()) {
			if (!scheduleMangerService.jobExist(job)) {
				scheduleMangerService.jobAdd(job);
			}
			scheduleMangerService.jobTriggerRun(job);
		}
	}

}
