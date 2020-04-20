package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZcChangeService extends BaseService {

    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Autowired
    IResService ResServiceImpl;

    public R ZcChange(String uuid,String type){
        if(type.equals(ZcCommonService.ZC_BUS_TYPE_LY)){
            return ZcLyChange(uuid);
        } else if(type.equals(ZcCommonService.ZC_BUS_TYPE_JY)){
            return ZcJyChange(uuid);
        }else if(type.equals(ZcCommonService.DATARANGE_DB)){
            return ZcLyChange(uuid);
        }

        return R.SUCCESS();
    }
    //领用
    public R ZcLyChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("prerecycle=recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_INUSE);
        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //借用
    public R ZcJyChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("prerecycle=recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_BORROW);
        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }
    //归还
    public R ZcGhChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("recycle=prerecycle");
        ups.set("prerecycle","");
        ResServiceImpl.update(ups);

        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.eq("busid", uuid);
        uw.set("busstatus", "in");
        SysProcessDataServiceImpl.update(uw);
        return R.SUCCESS_OPER();
    }


    //退库
    public R ZcTkChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("recycle=prerecycle");
        ups.set("prerecycle","");
        ResServiceImpl.update(ups);

        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.eq("busid", uuid);
        uw.set("busstatus", "in");
        SysProcessDataServiceImpl.update(uw);

        return R.SUCCESS_OPER();
    }




}
