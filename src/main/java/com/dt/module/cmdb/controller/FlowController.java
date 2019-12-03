package com.dt.module.cmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.service.HistoryService;
import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskService;
import com.bstek.uflo.utils.EnvironmentUtils;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.impl.ResActionService;
import com.dt.module.cmdb.service.impl.ResExtService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessClassItemService;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;

/**
 * @author: algernonking
 * @date: Dec 2, 2019 2:31:20 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/cmdb/flow")
public class FlowController extends BaseController {

	@Autowired
	SysUfloProcessService sysUfloProcessService;

	@Autowired
	IResActionItemService ResActionItemServiceImpl;

	@Autowired
	ResExtService resExtService;

	@Autowired
	ISysUserInfoService SysUserInfoServiceImpl;

	@Autowired
	private ProcessService processService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	ISysProcessClassItemService SysProcessClassItemServiceImpl;

	@Autowired
	ISysProcessDataService SysProcessDataServiceImpl;

	@ResponseBody
	@Acl(info = "发起流程", value = Acl.ACL_USER)
	@RequestMapping(value = "/zc/startProcess.do")
	public R startProcess(String spmethod, String processkey) {
		String busid = ToolUtil.getUUID();
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		UpdateWrapper<SysProcessData> up = new UpdateWrapper<SysProcessData>();
		SysProcessData sd = SysProcessDataServiceImpl.getById(ps.getString("id"));
		if (sd == null) {
			return R.FAILURE("不存在流程数据");
		}
		if ("1".equals(spmethod)) {
			// 需要审批
			if (sd.getProcessInstanceId() != null) {
				return R.FAILURE("已有流程,无法重复提交");
			}

			StartProcessInfo startProcessInfo = new StartProcessInfo(EnvironmentUtils.getEnvironment().getLoginUser());
			startProcessInfo.setCompleteStartTask(true);
			startProcessInfo.setBusinessId(busid);
			startProcessInfo.setTag(sd.getPtype());
			startProcessInfo.setSubject(sd.getDtitle());
			startProcessInfo.setCompleteStartTaskOpinion("发起流程");
			ProcessInstance inst = processService.startProcessByKey(processkey, startProcessInfo);
			// 插入流程数据
			up.set("busid", busid);
			up.set("processkey", processkey);
			up.set("ptitle", sd.getDtitle());
			up.set("pstatus", SysUfloProcessService.P_TYPE_RUNNING);
			up.set("pstatusdtl", ResActionService.ACT_STATUS_INREVIEW);
			up.set("processInstanceId", inst.getId() + "");
			up.set("pstartuserid", getUserId());
			up.set("pstartusername", SysUserInfoServiceImpl.getById(this.getUserId()).getName());

		} else {
			up.set("pstatusdtl", ResActionService.ACT_STATUS_APPROVALSUCCESS);
		}
		up.set(spmethod != null, "dmethod", spmethod);
		up.eq("id", ps.getString("id"));
		SysProcessDataServiceImpl.update(up);
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/zc/computeTask.do")
	@ResponseBody
	@Acl(info = "", value = Acl.ACL_USER)
	public R computeTask(String variables, String taskId, String opinion) {
		R r = sysUfloProcessService.computeTask(variables, taskId, opinion);

		return r;
	}
}
