package com.dt.module.flow.base;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.node.Node;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import com.dt.module.zc.service.impl.ZcChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AssetsEndEventHandler extends BaseNodeEventHandler {


    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Override
    public void enter(Node node, ProcessInstance processInstance, Context context) {
        System.out.println("###AssetsEndEventHandler Enter Node###");
        QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
        qw.eq("busid", processInstance.getBusinessId());
        SysProcessData r = SysProcessDataServiceImpl.getOne(qw);
        System.out.println("sp" + r.toString());
    }


    @Autowired
    @Lazy
    ZcChangeService zcChangeService;

    @Autowired
    SysUfloProcessService sysUfloProcessService;

    @Override
    public void leave(Node node, ProcessInstance processInstance, Context context) {
        System.out.println("###AssetsEndEventHandler Leave Node###" + processInstance.getProcessId());
        String dtlstatus = "";
        Long id=processInstance.getId();
        Long pid=processInstance.getParentId();
        dtlstatus = context.getProcessService().getProcessVariable("pstatusdtl", processInstance.getId()).toString();
        Date date = new Date(); // 获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
        String nowtime = simpleDateFormat.format(date);
        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.set("pstatus", SysProcessDataService.PSTATUS_FINISH);
        uw.set("pendtime", nowtime);

        if (SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(dtlstatus)) {
            System.out.println("调用流程正常结束");
            uw.set("pstatusdtl", SysProcessDataService.PSTATUS_DTL_SUCCESS);
        } else if (SysProcessDataService.PSTATUS_DTL_FAILED.equals(dtlstatus)) {
            System.out.println("调用流程-拒绝");
            uw.set("pstatusdtl", SysProcessDataService.PSTATUS_DTL_FAILED);
        } else {
            dtlstatus = "";
            System.out.println("调用流程-结束状态未知。" + processInstance.getId());
        }
        if (!"".equals(dtlstatus)) {
            SysProcessDataServiceImpl.update(uw);
            if("0".equals(Long.toString(pid))){
                zcChangeService.zcfinishFlow(Long.toString(id));
            }else{
                zcChangeService.zcfinishFlow(Long.toString(pid));
            }

        }
        System.out.println("流程结束\n\n");
    }


}
