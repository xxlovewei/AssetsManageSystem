package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.entity.ResPurchase;
import com.dt.module.zc.service.IResPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dt.core.common.base.R;
@Service
public class ResPurchaseService extends BaseService {


    @Autowired
    IResPurchaseService ResPurchaseServiceImpl;

    //开始流程
    public R startFlow(String pinst, String uuid, String ifsp) {
        if ("1".equals(ifsp)) {
            UpdateWrapper<ResPurchase> ups = new UpdateWrapper<ResPurchase>();
            ups.set("pinst", pinst);
            ups.set("status", SysProcessDataService.PSTATUS_DTL_INAPPROVAL);
            ups.eq("busid", uuid);
            ResPurchaseServiceImpl.update(ups);
        } else if ("0".equals(ifsp)) {
            UpdateWrapper<ResPurchase> ups = new UpdateWrapper<ResPurchase>();
            ups.set("status", SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
            ups.eq("busid", uuid);
            ResPurchaseServiceImpl.update(ups);
            sure(uuid, SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
        }
        return R.SUCCESS_OPER();
    }

    //取消
    public R cancel(String busid, String status) {
        UpdateWrapper<ResPurchase> ups = new UpdateWrapper<ResPurchase>();
        ups.set("status", status);
        ups.eq("busid", busid);
        ResPurchaseServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //确认
    public R sure(String busid, String status) {
        UpdateWrapper<ResPurchase> ups = new UpdateWrapper<ResPurchase>();
        ups.set("status", status);
        ups.eq("busid", busid);
        ResPurchaseServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }


    //结束流程
    public R finishFlow(String busid, String status) {
        if (SysProcessDataService.PSTATUS_DTL_FAILED.equals(status)) {
            return cancel(busid, SysProcessDataService.PSTATUS_DTL_FAILED);
        } else if (SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(status)) {
            return sure(busid, SysProcessDataService.PSTATUS_DTL_SUCCESS);
        } else {
            return R.FAILURE_NO_DATA();
        }
    }


}
