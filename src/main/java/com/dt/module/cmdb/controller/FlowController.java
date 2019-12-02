package com.dt.module.cmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.service.HistoryService;
import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskService;
import com.bstek.uflo.utils.EnvironmentUtils;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResActionService;
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
	IResActionService ResActionServiceImpl;

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

		if ("1".equals(spmethod)) {
			// 需要审批
			StartProcessInfo startProcessInfo = new StartProcessInfo(EnvironmentUtils.getEnvironment().getLoginUser());
			startProcessInfo.setCompleteStartTask(true);
			startProcessInfo.setBusinessId(busid);
			startProcessInfo.setSubject("资产流程");
			startProcessInfo.setCompleteStartTaskOpinion("发起流程");
			ProcessInstance inst = processService.startProcessByKey(processkey, startProcessInfo);
			// 插入流程数据
			SysProcessData pd = new SysProcessData();
			pd.setBusid(busid);
			pd.setProcesskey(processkey);
			pd.setPtype(ps.getString("type"));
			pd.setPstatus(SysUfloProcessService.P_TYPE_RUNNING);
			pd.setProcessInstanceId(inst.getId() + "");
			pd.setPstartuserid(this.getUserId());
			SysProcessDataServiceImpl.save(pd);

			Update me = new Update("res_action");
			me.set("spmethod", spmethod);
			me.set("tplinstid", inst.getId() + "");
			me.set("spstatus", ResActionService.ACT_STATUS_INREVIEW);
			me.where().and("id=?", ps.getString("id"));
			db.execute(me);

			// 更新资产单据表
		} else {
			// 不需要审批
			Update me = new Update("res_action");
			me.set("spmethod", spmethod);
			me.set("spstatus", ResActionService.ACT_STATUS_APPROVALSUCCESS);
			me.where().and("id=?", ps.getString("id"));
			db.execute(me);
		}

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
