package com.dt.module.flow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bstek.uflo.diagram.TaskInfo;
import com.bstek.uflo.model.HistoryTask;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.model.task.TaskState;
import com.bstek.uflo.query.HistoryTaskQuery;
import com.bstek.uflo.query.TaskQuery;
import com.bstek.uflo.service.HistoryService;
import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskOpinion;
import com.bstek.uflo.service.TaskService;
import com.bstek.uflo.utils.EnvironmentUtils;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: Nov 30, 2019 8:24:46 AM
 * @Description: TODO
 */

@Controller
@RequestMapping("/api")
public class SysUfloExtController extends BaseController {

	@Autowired
	private ProcessService processService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	public static void main(String[] args) {

	}

	private Map<String, Object> buildVariables(String variables) {
		if (StringUtils.isBlank(variables)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = mapper.readValue(variables, ArrayList.class);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Map<String, Object> m : list) {
				String key = m.get("key").toString();
				Object value = m.get("value");
				map.put(key, value);
			}
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/flow/computeTask.do")
	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	public R computeTask(String variables, String taskId, String opinion) {
		TaskOpinion op = new TaskOpinion(opinion);
		long taskId_l = ConvertUtil.toLong(taskId);
		Map<String, Object> variableMaps = buildVariables(variables);
		taskService.start(taskId_l);
		if (variableMaps != null) {
			taskService.complete(taskId_l, variableMaps, op);
		} else {
			taskService.complete(taskId_l, op);
		}
		return R.SUCCESS_OPER();
	}

	private List<TaskInfo> buildTaskInfos(List<Task> tasks) {
		List<TaskInfo> infos = new ArrayList<TaskInfo>();
		for (Task task : tasks) {
			TaskInfo info = new TaskInfo();
			info.setAssignee(task.getAssignee());
			info.setBusinessId(task.getBusinessId());
			info.setCreateDate(task.getCreateDate());
			info.setDescription(task.getDescription());
			info.setDuedate(task.getDuedate());
			info.setOpinion(task.getOpinion());
			info.setOwner(task.getOwner());
			info.setProcessId(task.getProcessId());
			info.setProcessInstanceId(task.getProcessInstanceId());
			info.setState(task.getState());
			info.setTaskId(task.getId());
			info.setTaskName(task.getTaskName());
			info.setTaskId(task.getId());
			info.setType(task.getType());
			info.setUrl(task.getUrl());
			infos.add(info);
		}
		return infos;
	}

	@RequestMapping("/flow/loadProcessInstanceData.do")
	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	public R loadProcessInstanceData(String processInstanceId) {
		TaskQuery query = taskService.createTaskQuery();
		long processInstanceIdl = ConvertUtil.toLong(processInstanceId);
		query.rootProcessInstanceId(processInstanceIdl);
		// query.nodeName(node.getName());
		query.addTaskState(TaskState.Created);
		query.addTaskState(TaskState.InProgress);
		query.addTaskState(TaskState.Ready);
		query.addTaskState(TaskState.Suspended);
		query.addTaskState(TaskState.Reserved);
		List<Task> tasks = query.list();
		List<TaskInfo> taskinfo = buildTaskInfos(tasks);

		HistoryTaskQuery historyTaskQuery = historyService.createHistoryTaskQuery();
		historyTaskQuery.rootProcessInstanceId(processInstanceIdl);
		List<HistoryTask> historyTasks = historyTaskQuery.list();
		List<TaskInfo> histaskinfo = buildHistoryTaskInfos(historyTasks);
		JSONArray data = new JSONArray();
		if (taskinfo.size() > 0) {
			data = JSONArray.parseArray(JSON.toJSONString(taskinfo, SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect));
		}
		if (histaskinfo.size() > 0) {
			data = JSONArray.parseArray(JSON.toJSONString(histaskinfo, SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect));
		}

		return R.SUCCESS_OPER(data);
	}

	private List<TaskInfo> buildHistoryTaskInfos(List<HistoryTask> tasks) {
		List<TaskInfo> infos = new ArrayList<TaskInfo>();
		for (HistoryTask task : tasks) {
			TaskInfo info = new TaskInfo();
			info.setAssignee(task.getAssignee());
			info.setBusinessId(task.getBusinessId());
			info.setCreateDate(task.getCreateDate());
			info.setDescription(task.getDescription());
			info.setDuedate(task.getDuedate());
			info.setEndDate(task.getEndDate());
			info.setOpinion(task.getOpinion());
			info.setOwner(task.getOwner());
			info.setProcessId(task.getProcessId());
			info.setProcessInstanceId(task.getProcessInstanceId());
			info.setState(task.getState());
			info.setTaskId(task.getId());
			info.setTaskName(task.getTaskName());
			info.setTaskId(task.getId());
			info.setType(task.getType());
			info.setUrl(task.getUrl());
			infos.add(info);
		}
		return infos;
	}

	@RequestMapping("/flow/query.do")
	@ResponseBody
	@Acl(info = "添加人员")
	public R flowquery(String id) {

		return R.SUCCESS_OPER();
	}

}
