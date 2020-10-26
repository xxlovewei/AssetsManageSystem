package com.dt.module.flow.base;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.node.Node;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.service.impl.ZcChangeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    ZcChangeService zcChangeService;


    @Override
    public void leave(Node node, ProcessInstance processInstance, Context context) {
        System.out.println("###AssetsEndEventHandler Leave Node###");
        QueryWrapper<SysProcessData> qw = new QueryWrapper<SysProcessData>();
        qw.eq("busid", processInstance.getBusinessId());
        SysProcessData r = SysProcessDataServiceImpl.getOne(qw);
        if (SysProcessDataService.PSTATUS_FINISH.equals(r.getPstatus())) {
            //流程已经结束
        } else {
            //流程正常结束
            Date date = new Date(); // 获取一个Date对象
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
            String nowtime = simpleDateFormat.format(date);
            UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
            uw.set("pstatus", SysProcessDataService.PSTATUS_FINISH);
            uw.set("pstatusdtl", SysProcessDataService.PSTATUS_DTL_SUCCESS);
            uw.set("pendtime", nowtime);
            SysProcessDataServiceImpl.update(uw);
        }
        zcChangeService.zcfinishFlow(r.getProcessinstanceid());
        System.out.println("流程结束\n\n");
    }


}
