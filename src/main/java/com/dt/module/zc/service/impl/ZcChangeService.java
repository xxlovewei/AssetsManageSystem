package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.service.IResAllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZcChangeService extends BaseService {

    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    public R ZcChange(String uuid,String type){
        if(type.equals(ZcCommonService.ZC_BUS_TYPE_LY)){
            return ZcLyChange(uuid);
        } else if(type.equals(ZcCommonService.ZC_BUS_TYPE_JY)){
            return ZcJyChange(uuid);
        }else if(type.equals(ZcCommonService.ZC_BUS_TYPE_DB)){
            return ZcDBChange(uuid);
        }else {
        }
        return R.SUCCESS();
    }

    //**************************领用/退库************************//
    //领用
    public R ZcLyChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("prerecycle=recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_INUSE);
        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //领用取消
    public R ZcLyCancelChange(String uuid){

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

    //**************************借用/归还************************//
    //借用
    public R ZcJyChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("prerecycle=recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_BORROW);
        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }
    //借用取消
    public R ZcJyCancel(String uuid){
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


    //**************************调拨************************//
    public R ZcDBChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_allocate_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("prerecycle =recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_ALLOCATION);
        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //资产调拨确认
    public R ZcDBSureChange(String uuid){

        QueryWrapper<ResAllocate> ew = new QueryWrapper<ResAllocate>();
        ew.and(i -> i.eq("uuid",uuid));
        ResAllocate obj=ResAllocateServiceImpl.getOne(ew);

        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_allocate_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("recycle=prerecycle");
        ups.set("prerecycle","");
        ups.set("used_userid","");
        if(ToolUtil.isNotEmpty(obj.getToloc())){
            ups.set("loc",obj.getToloc());
        }else{
            ups.set("loc","");
        }

        if(ToolUtil.isNotEmpty(obj.getTolocdtl())){
            ups.set("locdtl",obj.getTolocdtl());
        }else{
            ups.set("locdtl","");
        }

        if(ToolUtil.isNotEmpty(obj.getTobelongcompid())){
            ups.set("belong_company_id",obj.getTobelongcompid());
            ups.set("used_company_id",obj.getTobelongcompid());
        }else{
            ups.set("belong_company_id","");
            ups.set("used_company_id","");
        }

        if(ToolUtil.isNotEmpty(obj.getTousedpartid())){
            ups.set("part_id",obj.getTousedpartid());
        }else{
            ups.set("part_id","");
        }

        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //资产调拨取消
    public R ZcDBCancelChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_allocate_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("recycle=prerecycle");
        ups.set("prerecycle","");
        ResServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }





}
