
package com.dt.module.flow.controller;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: algernonking
 * @date: Nov 30, 2019 8:24:46 AM
 * @Description: TODO
 */

@Controller
@RequestMapping("/api")
public class SysUfloProcessExtController extends BaseController {


    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Autowired
    SysUfloProcessService sysUfloProcessService;

    public static void main(String[] args) {

    }

    @RequestMapping("/flow/startProcess.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R startProcess(String key, String type) {
        return sysUfloProcessService.startProcess(key, type);
    }

    @RequestMapping("/flow/completeTask.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R computeTask(String variables, String taskId, String opinion) {
        return sysUfloProcessService.completeTask(taskId, opinion);
    }

    @RequestMapping("/flow/cancelTask.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R computeTask(String taskId, String opinion) {
        return sysUfloProcessService.cancelTask(taskId, opinion);
    }

    @RequestMapping("/flow/loadProcessInstanceData.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R loadProcessInstanceData(String processInstanceId) {
        return sysUfloProcessService.loadProcessTaskinfo(processInstanceId);
    }

    @RequestMapping("/flow/loadProcessTaskinfo.do")
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    public R loadProcessTaskinfo(String processInstanceId) {
        return sysUfloProcessService.loadProcessTaskinfo(processInstanceId);
    }

    @RequestMapping("/flow/query.do")
    @ResponseBody
    @Acl(info = "添加人员")
    public R flowquery(String id) {

        return R.SUCCESS_OPER();
    }

}
