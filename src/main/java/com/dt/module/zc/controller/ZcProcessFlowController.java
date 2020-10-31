package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bstek.uflo.command.impl.jump.JumpNode;
import com.bstek.uflo.model.HistoryTask;
import com.bstek.uflo.model.ProcessDefinition;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.model.task.TaskState;
import com.bstek.uflo.process.flow.SequenceFlowImpl;
import com.bstek.uflo.process.node.Node;
import com.bstek.uflo.query.HistoryTaskQuery;
import com.bstek.uflo.query.TaskQuery;
import com.bstek.uflo.service.*;
import com.bstek.uflo.utils.EnvironmentUtils;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.entity.SysProcessDef;
import com.dt.module.flow.entity.SysProcessForm;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.ISysProcessDefService;
import com.dt.module.flow.service.ISysProcessFormService;
import com.dt.module.flow.service.ISysProcessSettingService;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import com.dt.module.form.service.ISysFormService;
import com.dt.module.form.service.impl.FormServiceImpl;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.service.IResCollectionreturnItemService;
import com.dt.module.zc.service.IResCollectionreturnService;
import com.dt.module.zc.service.impl.FlowDataService;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: algernonking
 * @date: Dec 2, 2019 2:31:20 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/zc/flow")
public class ZcProcessFlowController extends BaseController {

    @Autowired
    ZcChangeService zcChangeService;

    @Autowired
    FlowDataService flowDataService;

    @Autowired
    IResCollectionreturnService ResCollectionreturnServiceImpl;


    @Autowired
    SysUfloProcessService sysUfloProcessService;

    @Autowired
    FormServiceImpl formServiceImpl;

    @Autowired
    ZcCommonService resExtService;

    @Autowired
    ISysUserInfoService SysUserInfoServiceImpl;
    @Autowired
    ISysProcessDefService SysProcessDefServiceImpl;
    @Autowired
    IResActionItemService ResActionItemServiceImpl;
    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;
    @Autowired
    ISysProcessSettingService SysProcessSettingServiceImpl;
    @Autowired
    ISysFormService SysFormServiceImpl;
    @Autowired
    ISysProcessFormService SysProcessFormServiceImpl;
    @Autowired
    private ProcessService processService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryFlowTaskInfoByBusid.do")
    public R queryFlowTaskInfo(String busid) {
        return flowDataService.queryFlowTaskInfoByBusid(busid);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryFlowDataByBusid.do")
    public R queryFlowDataByBusid(String busid) {
        return flowDataService.queryFlowDataByBusid(busid);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/myProcessTodo.do")
    public R loadTodo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginUsername = EnvironmentUtils.getEnvironment().getLoginUser();
        String taskName = req.getParameter("taskName");
        int pageSize = Integer.valueOf(req.getParameter("pageSize"));
        int pageIndex = Integer.valueOf(req.getParameter("pageIndex"));
        int firstResult = (pageIndex - 1) * pageSize;
        TaskQuery query = taskService.createTaskQuery();
        query.addTaskState(TaskState.Created);
        query.addTaskState(TaskState.InProgress);
        query.addTaskState(TaskState.Ready);
        query.addTaskState(TaskState.Suspended);
        query.addTaskState(TaskState.Reserved);

        query.addAssignee(loginUsername).addOrderDesc("createDate").page(firstResult, pageSize);
        if (StringUtils.isNotBlank(taskName)) {
            query.nameLike("%" + taskName + "%");
        }

        List<Task> tasks = query.list();
        return R.SUCCESS_OPER(JSONArray.parseArray(JSON.toJSONString(tasks, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect)));
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/myProcessloadHistory.do")
    public R loadHistory(HttpServletRequest req, HttpServletResponse resp, String sdate, String edate)
            throws ServletException, IOException, ParseException {
        String loginUsername = EnvironmentUtils.getEnvironment().getLoginUser();
        int pageSize = Integer.valueOf(req.getParameter("pageSize"));
        int pageIndex = Integer.valueOf(req.getParameter("pageIndex"));
        String taskName = req.getParameter("taskName");
        int firstResult = (pageIndex - 1) * pageSize;
        HistoryTaskQuery query = historyService.createHistoryTaskQuery();
        if (StringUtils.isNotBlank(taskName)) {
            query.nameLike("%" + taskName + "%");
        }
        query.assignee(loginUsername).addOrderDesc("endDate").page(firstResult, pageSize);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (ToolUtil.isNotEmpty(sdate)) {
            Date ssdate = format.parse(sdate);
            query.createDateGreaterThenOrEquals(ssdate);
        }

        if (ToolUtil.isNotEmpty(edate)) {
            Date eedate = format.parse(edate);
            query.createDateLessThenOrEquals(eedate);
        }

        int total = query.count();
        List<HistoryTask> tasks = query.list();
        JSONObject retrunObject = new JSONObject();
        retrunObject.put("iTotalRecords", total);
        retrunObject.put("iTotalDisplayRecords", total);
        retrunObject.put("data", JSONArray.parseArray(JSON.toJSONString(tasks, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect)));
        return R.clearAttachDirect(retrunObject);


    }


    @ResponseBody
    @Acl(info = "发起流程", value = Acl.ACL_USER)
    @RequestMapping(value = "/startAssetFlow.do")
    public R startProcess(String formtype, String ifsp, String title, String busid, String ptype, String psubtype, String processdefid) {

        SysProcessDef pdef = SysProcessDefServiceImpl.getById(processdefid);
        String pinst = "";
        if ("1".equals(ifsp)) {
            // 需要审批,发起流程
            StartProcessInfo startProcessInfo = new StartProcessInfo(EnvironmentUtils.getEnvironment().getLoginUser());
            startProcessInfo.setCompleteStartTask(true);
            startProcessInfo.setBusinessId(busid);
            startProcessInfo.setPromoter(this.getUserId());
            //startProcessInfo.setTag(SysProcessDataService.PTYPE_ASSET);
            startProcessInfo.setSubject(title == null ? "" : title);
            startProcessInfo.setCompleteStartTaskOpinion(getName() + "开始发起流程");
            Map<String, Object> variables = new HashMap<>();
            variables.put("flowstartusername", this.getName());
            startProcessInfo.setVariables(variables);
            ProcessInstance inst = processService.startProcessByKey(pdef.getPtplkey(), startProcessInfo);
            pinst = Long.toString(inst.getId());
            // 插入流程数据
            SysProcessData pd = new SysProcessData();
            pd.setBusid(busid);
            pd.setBustype(SysProcessDataService.BUSTYPE_ASSET);
            pd.setPtitle(title);
            pd.setPtype(ptype);
            pd.setPsubtype(psubtype);
            pd.setPstatus(SysProcessDataService.PSTATUS_INAPPROVAL);
            pd.setPstatusdtl(SysProcessDataService.PSTATUS_DTL_INAPPROVAL);
            pd.setIfsp(ifsp);
            pd.setPstartuserid(getUserId());
            pd.setPstartusername(getName());
            pd.setProcesskey(pdef.getPtplkey());
            pd.setProcessinstanceid(Long.toString(inst.getId()));
            pd.setFormtype(formtype);
            SysProcessDataServiceImpl.saveOrUpdate(pd);
        }
        //修改原状态
        zcChangeService.zcStartFlow(pinst, busid, ptype, ifsp, new JSONObject());
        return R.SUCCESS_OPER();
    }


    @RequestMapping("/queryTask.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R queryTask(String taskId) {
        sysUfloProcessService.queryTask(taskId);
        return R.SUCCESS_OPER();
    }

    @RequestMapping("/completeTask.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R completeTask(String variables, String taskId, String opinion) {
        long taskId_l = ConvertUtil.toLong(taskId);
        Task tsk = taskService.getTask(taskId_l);
        sysUfloProcessService.addVariablesInProcessInstance(tsk.getProcessInstanceId(), "pstatusdtl", SysProcessDataService.PSTATUS_DTL_SUCCESS);
        R r = sysUfloProcessService.completeTask(taskId, opinion);
        return r;
//        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
//        ProcessDefinition process = processService.getProcessById(tsk.getProcessId());
        //       Node node = process.getNode(tsk.getNodeName());
//        List<SequenceFlowImpl> flows = node.getSequenceFlows();
//        if (flows.size() > 0) {
//            SequenceFlowImpl flowimpl = flows.get(0);
//            String toNode = flowimpl.getToNode();
//            if (toNode != null) {
//                if (toNode.startsWith("结束") || toNode.startsWith("流程结束") || toNode.toLowerCase().startsWith("end")) {
//                    //盘点为最后一个节点
//                    QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
//                    qw.eq("busid", tsk.getBusinessId());
//                    SysProcessData sd = SysProcessDataServiceImpl.getOne(qw);
//                    Date date = new Date(); // 获取一个Date对象
//                    DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
//                    String nowtime = simpleDateFormat.format(date);
//                    uw.set("pstatus", SysProcessDataService.PSTATUS_FINISH);
//                    uw.set("pstatusdtl", SysProcessDataService.PSTATUS_DTL_SUCCESS);
//                    uw.set("pendtime", nowtime);
        // 流程类型处理
//                    if (busType != null) {
//                        if (busType.equals("LY") || busType.equals("JY") || busType.equals("DB") || busType.equals("ZY")) {
//                            uw.set("busstatus", "out");
//                        }
//                    }
//                    SysProcessDataServiceImpl.update(uw);
//                    zcChangeService.zcfinishFlow(sd.getProcessinstanceid());
//                }
//            }
//        }

    }

//    @RequestMapping("/refuseTask.do")
//    @ResponseBody
//    @Acl(info = "", value = Acl.ACL_USER)
//    public R refuseTask(String taskId, String opinion) {
//        R r = sysUfloProcessService.refuseTask(taskId, opinion);
//        return r;
//    }

    @RequestMapping("/refuseTaskForwardEnd.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R refuseTaskForwardEnd(String taskId, String opinion) {
        //流程跳转到最后节点
        TaskOpinion op = new TaskOpinion(opinion);
        long taskId_l = ConvertUtil.toLong(taskId);
        Task tsk = taskService.getTask(taskId_l);
        String instid = Long.toString(tsk.getProcessInstanceId());
        List<JumpNode> nodes = taskService.getAvaliableForwardTaskNodes(taskId_l);
        if (nodes.size() == 0) {
            return R.FAILURE("无法跳转至结束流程");
        }
        JumpNode jn = nodes.get(nodes.size() - 1);
        if (jn.isTask()) {
            return R.FAILURE("获取的最后一个节点不是结束流程");
        }
        sysUfloProcessService.addVariablesInProcessInstance(tsk.getProcessInstanceId(), "pstatusdtl", SysProcessDataService.PSTATUS_DTL_FAILED);
        taskService.forward(taskId_l, jn.getName(), op);
        return R.SUCCESS_OPER();
    }

    @RequestMapping("/getAvaliableForwardTaskNodes.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R getAvaliableForwardTaskNodes(String taskId) {
        R r = sysUfloProcessService.getAvaliableForwardTaskNodes(taskId);
        return r;
    }

    @RequestMapping("/getAvaliableRollbackTaskNodes.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R getAvaliableRollbackTaskNodes(String taskId) {
        R r = sysUfloProcessService.getAvaliableRollbackTaskNodes(taskId);
        return r;
    }

    @RequestMapping("/rollback.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R rollback(String taskId, String opinion) {
        R r = sysUfloProcessService.forwardStart(taskId, opinion);
        return r;
    }

    @RequestMapping("/withdraw.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R withdraw(String taskId, String opinion) {
        R r = sysUfloProcessService.withdraw(taskId, opinion);
        return r;
    }

    @RequestMapping("/forward.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R forward(String taskId, String target, String opinion) {
        R r = sysUfloProcessService.forward(taskId, target, opinion);
        return r;
    }

    @RequestMapping("/queryTaskNodeDtl.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R queryTaskNodeDtl(String taskId) {
        R r = sysUfloProcessService.queryTaskNodeDtl(taskId);
        return r;
    }

    @RequestMapping("/completeStartTask.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R completeStartTask(String taskId) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        long taskId_l = ConvertUtil.toLong(taskId);
        // 修改流程标记
        Task tsk = taskService.getTask(taskId_l);
        String instid = Long.toString(tsk.getProcessInstanceId());
        // 更新状态
        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.eq("processInstanceId", instid);
        uw.set("pstatus", SysUfloProcessService.P_STATUS_RUNNING);
        uw.set("pstatusdtl", SysUfloProcessService.P_DTL_STATUS_INREVIEW);
        uw.set("ptitle", ps.getString("dtitle", " "));
        uw.set("dtitle", ps.getString("dtitle", " "));
        uw.set("df1", ps.getString("df1", " "));
        uw.set("df2", ps.getString("df2", " "));
        uw.set("dct", ps.getString("dct", " "));
        SysProcessDataServiceImpl.update(uw);
        R r = sysUfloProcessService.completeTask(taskId, ps.getString("opinion"));
        return r;
    }

}
