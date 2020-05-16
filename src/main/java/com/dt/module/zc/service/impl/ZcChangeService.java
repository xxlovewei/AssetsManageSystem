package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.entity.ResAllocateItem;
import com.dt.module.zc.entity.ResChangeItem;
import com.dt.module.zc.service.IResAllocateItemService;
import com.dt.module.zc.service.IResAllocateService;
import com.dt.module.zc.service.IResChangeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZcChangeService extends BaseService {

    @Autowired
    IResAllocateItemService ResAllocateItemServiceImpl;

    @Autowired
    IResActionItemService ResActionItemServiceImpl;

    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResChangeItemService ResChangeItemServiceImpl;


    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    public R zcSureChange(String uuid, String type){
        if(type.equals(ZcCommonService.ZC_BUS_TYPE_LY)){
            return zcLySureChange(uuid);
        } else if(type.equals(ZcCommonService.ZC_BUS_TYPE_JY)){
            return zcJySureChange(uuid);
        }else if(type.equals(ZcCommonService.ZC_BUS_TYPE_DB)){
        }else {
        }
        return R.SUCCESS();
    }


    public R ZcStartChange(String uuid, String type){
        if(type.equals(ZcCommonService.ZC_BUS_TYPE_LY)){
            return zcLyStartChange(uuid);
        } else if(type.equals(ZcCommonService.ZC_BUS_TYPE_JY)){
            return zcJyStartChange(uuid);
        }else{

        }
        return R.SUCCESS();
    }


    public R zcCancelChange(String uuid, String type){
        if(type.equals(ZcCommonService.ZC_BUS_TYPE_LY)){
            return zcLyCancelChange(uuid);
        } else if(type.equals(ZcCommonService.ZC_BUS_TYPE_JY)){
            return zcJyCancelChange(uuid);
        }
        return R.SUCCESS();
    }

    //**************************领用/退库************************//
    //领用
    public R zcLySureChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.set("prerecycle","");
        ups.set("recycle",ZcCommonService.RECYCLE_INUSE);
        ResServiceImpl.update(ups);


        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setMark("资产领用审批通过");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();


    }

    //领用取消
    public R zcLyStartChange(String uuid){
        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setMark("发起领用申请,等待审批");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //领用取消
    public R zcLyCancelChange(String uuid){
        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setMark("领用申请取消");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //退库
    public R zcTkSureChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
        ups.set("prerecycle","");
        ups.set("recycle",ZcCommonService.RECYCLE_IDLE);
        ResServiceImpl.update(ups);

        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.set("busstatus", "in");
        uw.eq("busid", uuid);
        SysProcessDataServiceImpl.update(uw);


        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setMark("资产已退库");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);


        return R.SUCCESS_OPER();
    }

    //**************************借用/归还************************//
    //借用
    public R zcJySureChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
    //    ups.set("prerecycle","");
        ups.setSql("prerecycle=recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_BORROW);
        ResServiceImpl.update(ups);


        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("资产借用审批通过");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    public R zcJyStartChange(String uuid){
        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("发起借用申请,等待审批");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //借用取消
    public R zcJyCancelChange(String uuid){
        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("取消借用审批");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }


    //归还
    public R zcGhChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
      //  ups.setSql("recycle=prerecycle");
        ups.set("prerecycle","");
        ups.setSql("recycle=prerecycle");
      //  ups.set("recycle",ZcCommonService.RECYCLE_IDLE);
        ResServiceImpl.update(ups);
        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.eq("busid", uuid);
        uw.set("busstatus", "in");
        SysProcessDataServiceImpl.update(uw);


        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("资产归还");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }



    //**************************调拨************************//
    public R zcDBChange(String uuid){

        //修改资产
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_allocate_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("prerecycle =recycle");
        ups.set("recycle",ZcCommonService.RECYCLE_ALLOCATION);
        ResServiceImpl.update(ups);

        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> ew = new QueryWrapper<ResAllocateItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResAllocateItem> items=ResAllocateItemServiceImpl.list(ew);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setMark("发起调拨");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //资产调拨确认
    public R zcDBSureChange(String uuid){

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


        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> qw = new QueryWrapper<ResAllocateItem>();
        qw.and(i -> i.eq("busuuid", uuid));
        List<ResAllocateItem> items=ResAllocateItemServiceImpl.list(qw);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setMark("确认调拨");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //资产调拨取消
    public R zcDBCancelChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_allocate_item where dr='0' and busuuid='"+uuid+"'");
        ups.setSql("recycle=prerecycle");
        ups.set("prerecycle","");
        ResServiceImpl.update(ups);

        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> qw = new QueryWrapper<ResAllocateItem>();
        qw.and(i -> i.eq("busuuid", uuid));
        List<ResAllocateItem> items=ResAllocateItemServiceImpl.list(qw);
        for(int i=0;i<items.size();i++){
            ResChangeItem e=new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setMark("取消调拨");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }





}
