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
import com.dt.module.zc.entity.*;
import com.dt.module.zc.service.*;
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
    IResScrapeItemService ResScrapeItemServiceImpl;

    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    public R zcSureChange(String uuid, String type) {
        if (type.equals(ZcCommonService.ZC_BUS_TYPE_LY)) {
            return zcLySureChange(uuid);
        } else if (type.equals(ZcCommonService.ZC_BUS_TYPE_JY)) {
            return zcJySureChange(uuid);
        } else if (type.equals(ZcCommonService.ZC_BUS_TYPE_DB)) {
        } else if (type.equals(ZcCommonService.ZC_BUS_TYPE_BF)) {
            return zcBFSureChange(uuid);
        } else {
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
    //领用确认
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
            e.setMark("资产领用");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();


    }

    //领用申请
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
            e.setMark("资产退还");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);


        return R.SUCCESS_OPER();
    }

    //**************************借用/归还************************//
    //借用确认
    public R zcJySureChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id","select resid from res_action_item where dr='0' and busuuid='"+uuid+"'");
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
            e.setMark("资产借用");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //借用申请
    public R zcJyStartChange(String uuid){
        //记录资产变更
        ArrayList<ResChangeItem> cols=new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items= ResActionItemServiceImpl.list(ew);
        for(int i=0; i<items.size(); i++){
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


    //资产借用归还
    public R zcGhChange(String uuid){
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id", "select resid from res_action_item where dr='0' and busuuid='" + uuid + "'");
        ups.setSql("recycle=prerecycle");
        ups.set("prerecycle", "");
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


    //资产报废确认
    public R zcBFSureChange(String uuid) {
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResScrapeItem> ew = new QueryWrapper<ResScrapeItem>();
        ew.and(i -> i.eq("uuid", uuid));
        List<ResScrapeItem> items = ResScrapeItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_BF);
            e.setMark("资产报废");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.FAILURE_OPER();
    }


    @Autowired
    IResCMaintenanceService ResCMaintenanceServiceImpl;

    @Autowired
    IResCMaintenanceItemService ResCMaintenanceItemServiceImpl;

    public R zcCGWBSureChange(String uuid) {
        QueryWrapper<ResCMaintenanceItem> qw = new QueryWrapper<ResCMaintenanceItem>();
        qw.and(i -> i.eq("busuuid", uuid));
        ResCMaintenanceItem entity = ResCMaintenanceItemServiceImpl.getOne(qw);

        String sql = "update res_c_maintenance_item a,res b set a.fwb=b.wb,\n" +
                "  a.fwbsupplier=b.wbsupplier,\n" +
                "  a.fwbauto=b.wb_auto,\n" +
                "  a.fwbct=b.wbct,\n" +
                "  a.fwboutdate=b.wbout_date\n" +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);
        String sql2 = "update res_c_maintenance_item b,res a set a.wb=b.twb,\n" +
                "  a.wbsupplier=b.twbsupplier,\n" +
                "  a.wb_auto=b.twbauto,\n" +
                "  a.wbct=b.twbct,\n" +
                "  a.wbout_date=b.twboutdate\n" +
                "where a.id=b.resid and b.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResCMaintenanceItem> qw2 = new QueryWrapper<ResCMaintenanceItem>();
        qw2.and(i -> i.eq("busuuid", uuid));
        List<ResCMaintenanceItem> items = ResCMaintenanceItemServiceImpl.list(qw2);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_CGWB);
            e.setMark("维保信息变更");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.FAILURE_OPER();
    }

    @Autowired
    IResCFinanceService ResCFinanceServiceImpl;

    @Autowired
    IResCFinanceItemService ResCFinanceItemServiceImpl;

    //资产变更
    public R zcCGCWSureChange(String uuid) {
        QueryWrapper<ResCFinance> qw = new QueryWrapper<ResCFinance>();
        qw.and(i -> i.eq("busuuid", uuid));
        ResCFinance entity = ResCFinanceServiceImpl.getOne(qw);

        String sql = "update res_c_finance_item a,res b set a.fbuyprice=b.buy_price,\n" +
                "  a.fbelongcomp=b.belong_company_id,\n" +
                "  a.fbelongpart=b.belong_part_id,\n" +
                "  a.faccumulateddepreciation=b.accumulateddepreciation,\n" +
                "  a.fnetworth=b.net_worth,\n" +
                "  a.fresidualvalue=b.residualvalue\n" +
                "where a.resid=b.id and a.busuuid=? and a.dr='0'";
        db.execute(sql, uuid);
        String sql2 = "update res_c_finance_item a,res b set a.fbuyprice=b.buy_price,\n" +
                "b.belong_company_id=a.tbelongcomp,\n" +
                "b.belong_part_id=a.tbelongpart,\n" +
                "b.accumulateddepreciation=a.taccumulateddepreciation,\n" +
                "b.net_worth=a.tnetworth,\n" +
                "b.residualvalue=a.tresidualvalue\n" +
                "where a.resid=b.id and a.busuuid=? and a.dr='0'";
        db.execute(sql2, uuid);
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResCFinanceItem> qw2 = new QueryWrapper<ResCFinanceItem>();
        qw2.and(i -> i.eq("busuuid", uuid));
        List<ResCFinanceItem> items = ResCFinanceItemServiceImpl.list(qw2);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_CGCW);
            e.setMark("财务信息变更");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.FAILURE_OPER();
    }

}
