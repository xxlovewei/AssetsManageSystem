package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.busenum.ZcRecycleEnum;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.zc.entity.*;
import com.dt.module.zc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ZcChangeService extends BaseService {


    @Autowired
    @Lazy
    ResLoanreturnService resLoanreturnService;

    @Autowired
    @Lazy
    ResCollectionreturnService resCollectionreturnService;

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
    @Autowired
    IResCBasicinformationService ResCBasicinformationServiceImpl;
    @Autowired
    IResCBasicinformationItemService ResCBasicinformationItemServiceImpl;
    @Autowired
    IResCMaintenanceService ResCMaintenanceServiceImpl;
    @Autowired
    IResCMaintenanceItemService ResCMaintenanceItemServiceImpl;
    @Autowired
    IResCFinanceService ResCFinanceServiceImpl;
    @Autowired
    IResCFinanceItemService ResCFinanceItemServiceImpl;
    @Autowired
    IResCollectionreturnItemService ResCollectionreturnItemServiceImpl;
    @Autowired
    IResLoanreturnItemService ResLoanreturnItemServiceImpl;
    @Autowired
    IResResidualItemService ResResidualItemServiceImpl;
    @Autowired
    IResResidualService ResResidualServiceImpl;
    @Autowired
    ResCMaintenanceService resCMaintenanceService;
    @Autowired
    ResCFinanceService resCFinanceService;
    @Autowired
    ResCBasicinformationService resCBasicinformationService;


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

    public R ZcStartChange(String uuid, String type) {
        if (type.equals(ZcCommonService.ZC_BUS_TYPE_LY)) {
            return zcLyStartChange(uuid);
        } else if (type.equals(ZcCommonService.ZC_BUS_TYPE_JY)) {
            return zcJyStartChange(uuid);
        }
        return R.SUCCESS();
    }

    public R zcCancelChange(String uuid, String type) {
        if (type.equals(ZcCommonService.ZC_BUS_TYPE_LY)) {
            return zcLyCancelChange(uuid);
        } else if (type.equals(ZcCommonService.ZC_BUS_TYPE_JY)) {
            return zcJyCancelChange(uuid);
        }
        return R.SUCCESS();
    }


    public R zcRkConfirm(String uuid) {
        QueryWrapper<Res> ew = new QueryWrapper<Res>();
        ew.eq("uuid", uuid);
        Res entity = ResServiceImpl.getOne(ew);
        ResChangeItem e = new ResChangeItem();
        e.setBusuuid(uuid);
        e.setResid(entity.getId());
        e.setFillct("1");
        e.setCt("资产入库");
        e.setMark("资产入库");
        e.setType(ZcCommonService.ZC_BUS_TYPE_RK);
        ResChangeItemServiceImpl.saveOrUpdate(e);
        return R.SUCCESS_OPER();
    }

    //折旧
    public R zcZjConfirm(String uuid) {
        //保存更新前的数据
        String sql = " update res_residual_item a,res b set \n" +
                "   a.buyprice=b.buy_price\n" +
                " , a.bnetworth=b.net_worth\n" +
                " , a.baccumulateddepreciation=b.accumulateddepreciation\n" +
                "   where a.resid=b.id and a.uuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);

        QueryWrapper<ResResidualItem> ew = new QueryWrapper<ResResidualItem>();
        ew.eq("uuid", uuid);
        List<ResResidualItem> list = ResResidualItemServiceImpl.list(ew);

        List<Res> list2 = new ArrayList<Res>();
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        for (int i = 0; i < list.size(); i++) {
            ResResidualItem item = list.get(i);
            UpdateWrapper<Res> resups = new UpdateWrapper<Res>();
            resups.set("net_worth", item.getAnetworth());
            resups.setSql("accumulateddepreciation=accumulateddepreciation+" + item.getLossprice());
            resups.setSql("lastdepreciationdate=now()");
            resups.eq("id", item.getResid());
            ResServiceImpl.update(resups);

            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(item.getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_ZJ);
            e.setMark("资产折旧");
            cols.add(e);
        }

        UpdateWrapper<ResResidual> ups = new UpdateWrapper<ResResidual>();
        ups.set("status", ResResidualExtService.STATUS_SUCCESS);
        ups.eq("uuid", uuid);
        ResResidualServiceImpl.update(ups);
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //**************************领用/退库************************//
    //领用确认
    public R zcLyConfirm(String uuid) {
        //保存变更前数据
        String sql = " update res_collectionreturn_item a,res b set \n" +
                "   a.fusedcompanyid=b.used_company_id\n" +
                " , a.fpartid=b.part_id\n" +
                " , a.fuseduserid=b.used_userid\n" +
                " , a.floc=b.loc\n" +
                " , a.flocdtl=b.locdtl\n" +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);

        //更新数据
        String sql2 = " update res_collectionreturn_item a,res b set \n" +
                "b.loc=a.tloc," +
                "b.used_company_id=a.tusedcompanyid," +
                "b.part_id=a.tpartid," +
                "b.used_userid=a.tuseduserid," +
                "b.locdtl=a.tlocdtl," +
                "b.recycle='" + ZcRecycleEnum.RECYCLE_INUSE.getValue() + "'," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='', " +
                "b.uuidly=a.busuuid " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResCollectionreturnItem> ew = new QueryWrapper<ResCollectionreturnItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResCollectionreturnItem> items = ResCollectionreturnItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setMark("资产领用");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }


    //领用退库确认
    public R zcTkConfirm(String uuid) {
        //保存变更前数据
        String sql = " update res_collectionreturn_item a,res b set \n" +
                "   a.fusedcompanyid=b.used_company_id\n" +
                " , a.fpartid=b.part_id\n" +
                " , a.fuseduserid=b.used_userid\n" +
                " , a.floc=b.loc\n" +
                " , a.flocdtl=b.locdtl\n" +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);

        //更新数据
        String sql2 = "update res_collectionreturn_item a,res b set \n" +
                "b.loc=a.tloc," +
                "b.used_company_id=a.tusedcompanyid," +
                "b.part_id=a.tpartid," +
                "b.used_userid=a.tuseduserid," +
                "b.locdtl=a.tlocdtl," +
                "b.recycle='" + ZcRecycleEnum.RECYCLE_IDLE.getValue() + "'," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='', " +
                "b.uuidly='' " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        String sql3 = " update res_collectionreturn_item a,res_collectionreturn_item b set " +
                " a.returnuuid=b.busuuid," +
                " a.rreturndate=b.rreturndate," +
                " a.isreturn='1'" +
                " where a.resid=b.resid and b.busuuid=? and b.dr='0'";
        db.execute(sql3, uuid);
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResCollectionreturnItem> ew = new QueryWrapper<ResCollectionreturnItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResCollectionreturnItem> items = ResCollectionreturnItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_TK);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产退库");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    public R zcJyConfirm(String uuid) {
        //保存变更前数据
        String sql = " update res_loanreturn_item a,res b set \n" +
                "   a.frecycle=b.recycle\n" +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);
        //更新数据
        String sql2 = "update res_loanreturn_item a,res b set \n" +
                "b.recycle='" + ZcRecycleEnum.RECYCLE_BORROW.getValue() + "'," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='', " +
                "b.uuidjy=a.busuuid " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResLoanreturnItem> ew = new QueryWrapper<ResLoanreturnItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResLoanreturnItem> items = ResLoanreturnItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产借用");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    public R zcGhConfirm(String uuid) {
        //更新数据
        String sql2 = " update res_loanreturn_item a,res b set \n" +
                "b.recycle=a.frecycle," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='', " +
                "b.uuidjy='' " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        String sql3 = " update res_loanreturn_item a,res_loanreturn_item b set " +
                " a.returnuuid=b.busuuid," +
                " a.rreturndate=b.rreturndate," +
                " a.isreturn='1'" +
                " where a.resid=b.resid and b.busuuid=? and b.dr='0'";
        db.execute(sql3, uuid);
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResLoanreturnItem> ew = new QueryWrapper<ResLoanreturnItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResLoanreturnItem> items = ResLoanreturnItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_GH);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产归还");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }


    //领用确认
    public R zcLySureChange(String uuid) {
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id", "select resid from res_action_item where dr='0' and busuuid='" + uuid + "'");
        ups.set("prerecycle", "");
        ups.set("recycle", ZcRecycleEnum.RECYCLE_INUSE.getValue());
        ResServiceImpl.update(ups);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产领用");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //领用申请
    public R zcLyStartChange(String uuid) {
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("发起领用申请,等待审批");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //领用取消
    public R zcLyCancelChange(String uuid) {
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("领用申请取消");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //退库
    public R zcTkSureChange(String uuid) {
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id", "select resid from res_action_item where dr='0' and busuuid='" + uuid + "'");
        ups.set("prerecycle", "");
        ups.set("recycle", ZcRecycleEnum.RECYCLE_IDLE.getValue());
        ResServiceImpl.update(ups);

        UpdateWrapper<SysProcessData> uw = new UpdateWrapper<SysProcessData>();
        uw.set("busstatus", "in");
        uw.eq("busid", uuid);
        SysProcessDataServiceImpl.update(uw);


        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产退还");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //**************************借用/归还************************//
    //借用确认
    public R zcJySureChange(String uuid) {
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id", "select resid from res_action_item where dr='0' and busuuid='" + uuid + "'");
        ups.setSql("prerecycle=recycle");
        ups.set("recycle", ZcRecycleEnum.RECYCLE_BORROW.getValue());
        ResServiceImpl.update(ups);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("资产借用");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //借用申请
    public R zcJyStartChange(String uuid) {
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("发起借用申请,等待审批");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //借用取消
    public R zcJyCancelChange(String uuid) {
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("取消借用审批");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //资产借用归还
    public R zcGhChange(String uuid) {
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
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResActionItem> items = ResActionItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setMark("资产归还");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //**************************调拨************************//
    public R zcDBChange(String uuid) {
        //保存之前数据
        String sql = " update res_allocate_item a,res b set \n" +
                "   a.frecycle=b.recycle\n" +
                " , a.floc=b.loc\n" +
                " , a.flocdtl=b.locdtl\n" +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);

        //修改资产
        UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
        ups.inSql("id", "select resid from res_allocate_item where dr='0' and busuuid='" + uuid + "'");
        ups.set("recycle", ZcRecycleEnum.RECYCLE_ALLOCATION.getValue());
        ResServiceImpl.update(ups);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> ew = new QueryWrapper<ResAllocateItem>();
        ew.and(i -> i.eq("busuuid", uuid));
        List<ResAllocateItem> items = ResAllocateItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setMark("开始调拨");
            e.setFillct("1");
            e.setCt("开始调拨!【状态】由\"闲置\"变更为\"调拨中\"");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //资产调拨确认
    public R zcDBSureChange(String uuid) {

        String sql2 = "update res_allocate_item a,res b set a.update_by='" + this.getUserId() + "'";
        sql2 = sql2 + ",b.recycle=a.frecycle";
        sql2 = sql2 + ",b.locdtl=a.tolocdtl";
        sql2 = sql2 + ",b.loc=a.toloc";
        sql2 = sql2 + ",b.used_company_id=a.tousedcompid";
        sql2 = sql2 + " where a.resid=b.id and a.busuuid=? and a.dr='0'";
        db.execute(sql2, uuid);

        String sql3 = "update res_allocate_item a set acttime=now() where busuuid=?";
        String sql4 = "update res_allocate a set acttime=now() where uuid=?";
        db.execute(sql3, uuid);
        db.execute(sql4, uuid);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> qw = new QueryWrapper<ResAllocateItem>();
        qw.and(i -> i.eq("busuuid", uuid));
        List<ResAllocateItem> items = ResAllocateItemServiceImpl.list(qw);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setMark("确认调拨");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //资产调拨取消
    public R zcDBCancelChange(String uuid) {

        //更新之前数据
        String sql = " update res_allocate_item a,res b set \n" +
                "   b.recycle =a.frecycle\n" +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> qw = new QueryWrapper<ResAllocateItem>();
        qw.and(i -> i.eq("busuuid", uuid));
        List<ResAllocateItem> items = ResAllocateItemServiceImpl.list(qw);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setMark("取消调拨");
            e.setFillct("1");
            e.setCt("取消调拨!【状态】由\"调拨中\"变更为\"闲置\"");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
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
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //资产变更
    public R zcCGJBSureChange(String uuid) {
        QueryWrapper<ResCBasicinformation> qw = new QueryWrapper<ResCBasicinformation>();
        qw.and(i -> i.eq("busuuid", uuid));
        ResCBasicinformation entity = ResCBasicinformationServiceImpl.getOne(qw);
        String sql = " update res_c_basicinformation_item a,res b set \n" +
                "   a.fclassid=b.class_id\n" +
                " , a.fmodel=b.model\n" +
                " , a.fsn=b.sn\n" +
                " , a.fzcsource=b.zcsource\n" +
                " , a.fzccnt=b.zc_cnt\n" +
                " , a.fsupplier=b.supplier\n" +
                " , a.fbrand=b.brand\n" +
                " , a.fbuytime=b.buy_time\n" +
                " , a.floc=b.loc\n" +
                " , a.fconfdesc=b.confdesc\n" +
                " , a.fusefullife=b.usefullife\n" +
                " , a.fusedcompanyid=b.used_company_id\n" +
                " , a.fpartid=b.part_id\n" +
                " , a.fuseduserid=b.used_userid\n" +
                " where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);
        String sql2 = "update res_c_basicinformation_item b,res a set a.update_by='" + this.getUserId() + "'";
        if ("true".equals(entity.getTclassidstatus())) {
            sql2 = sql2 + ",a.class_id=b.tclassid";
        }
        if ("true".equals(entity.getTmodelstatus())) {
            sql2 = sql2 + ",a.model=b.tmodel";
        }
        if ("true".equals(entity.getTclassidstatus())) {
            sql2 = sql2 + ",a.class_id=b.tclassid";
        }
        if ("true".equals(entity.getTsnstatus())) {
            sql2 = sql2 + ",a.sn=b.tsn";
        }
        if ("true".equals(entity.getTzcsourcestatus())) {
            sql2 = sql2 + ",a.zcsource=b.tzcsource";
        }
        if ("true".equals(entity.getTzccntstatus())) {
            sql2 = sql2 + ",a.zc_cnt=b.tzccnt";
        }
        if ("true".equals(entity.getTsupplierstatus())) {
            sql2 = sql2 + ",a.supplier=b.tsupplier";
        }
        if ("true".equals(entity.getTbrandstatus())) {
            sql2 = sql2 + ",a.brand=b.tbrand";
        }
        if ("true".equals(entity.getTbuytimestatus())) {
            sql2 = sql2 + ",a.buy_time=b.tbuytime";
        }
        if ("true".equals(entity.getTlocstatus())) {
            sql2 = sql2 + ",a.loc=b.tloc";
        }
        if ("true".equals(entity.getTusefullifestatus())) {
            sql2 = sql2 + ",a.usefullife=b.tusefullife";
        }
        if ("true".equals(entity.getTusedcompanyidstatus())) {
            sql2 = sql2 + ",a.used_company_id=b.tusedcompanyid";
        }
        if ("true".equals(entity.getTpartidstatus())) {
            sql2 = sql2 + ",a.part_id=b.tpartid";
        }
        if ("true".equals(entity.getTuseduseridstatus())) {
            sql2 = sql2 + ",a.used_userid=b.tuseduserid";
        }
        if ("true".equals(entity.getTlabel1status())) {
            sql2 = sql2 + ",a.fs1=b.tlabel1";
        }
        if ("true".equals(entity.getTlocdtlstatus())) {
            sql2 = sql2 + ",a.locdtl=b.tlocdtl";
        }
        if ("true".equals(entity.getTunitstatus())) {
            sql2 = sql2 + ",a.unit=b.tunit";
        }
        if ("true".equals(entity.getTconfdescstatus())) {
            sql2 = sql2 + ",a.confdesc=b.tconfdesc";
        }
        sql2 = sql2 + " where a.id=b.resid and b.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();

        QueryWrapper<ResCBasicinformationItem> qw2 = new QueryWrapper<ResCBasicinformationItem>();
        qw2.and(i -> i.eq("busuuid", uuid));
        List<ResCBasicinformationItem> items = ResCBasicinformationItemServiceImpl.list(qw2);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(uuid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_CGJB);
            e.setMark("实体信息变更");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

    public R zcCGWBSureChange(String uuid) {
        QueryWrapper<ResCMaintenance> qw = new QueryWrapper<ResCMaintenance>();
        qw.and(i -> i.eq("busuuid", uuid));
        ResCMaintenance entity = ResCMaintenanceServiceImpl.getOne(qw);

        String sql = "update res_c_maintenance_item a,res b set a.fwb=b.wb,\n" +
                "  a.fwbsupplier=b.wbsupplier,\n" +
                "  a.fwbauto=b.wb_auto,\n" +
                "  a.fwbct=b.wbct,\n" +
                "  a.fwboutdate=b.wbout_date\n" +
                " where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, uuid);
        System.out.println(entity);
        String sql2 = "update res_c_maintenance_item b,res a set a.update_by='" + this.getUserId() + "'";
        if ("true".equals(entity.getTwbstatus())) {
            sql2 = sql2 + ",a.wb=b.twb";
        }
        if ("true".equals(entity.getTwbsupplierstatus())) {
            sql2 = sql2 + ",a.wbsupplier=b.twbsupplier";
        }
        if ("true".equals(entity.getTwbautostatus())) {
            sql2 = sql2 + ",a.wb_auto=b.twbauto";
        }
        if ("true".equals(entity.getTwbctstatus())) {
            sql2 = sql2 + ",a.wbct=b.twbct";
        }
        if ("true".equals(entity.getTwboutdatestatus())) {
            sql2 = sql2 + ",a.wbout_date=b.twboutdate";
        }
        sql2 = sql2 + " where a.id=b.resid and b.busuuid=? and b.dr='0' and a.dr='0'";
        System.out.println(sql2);
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
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }

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
                " where a.resid=b.id and a.busuuid=? and a.dr='0'";
        db.execute(sql, uuid);

        String sql2 = "update res_c_finance_item a,res b set  a.update_by='" + this.getUserId() + "'";
        if ("true".equals(entity.getTbuypricestatus())) {
            sql2 = sql2 + ",b.buy_price=a.tbuyprice";
        }
        if ("true".equals(entity.getTbelongcompstatus())) {
            sql2 = sql2 + ",b.belong_company_id=a.tbelongcomp";
        }
        if ("true".equals(entity.getTbelongpartstatus())) {
            sql2 = sql2 + ",b.belong_part_id=a.tbelongpart";
        }
        if ("true".equals(entity.getTaccumulatedstatus())) {
            sql2 = sql2 + ",b.accumulateddepreciation=a.taccumulateddepreciation";
        }
        if ("true".equals(entity.getTnetworthstatus())) {
            sql2 = sql2 + ",b.net_worth=a.tnetworth";
        }
        if ("true".equals(entity.getTresidualvaluestatus())) {
            sql2 = sql2 + ",b.residualvalue=a.tresidualvalue";
        }
        sql2 = sql2 + " where a.resid=b.id and a.busuuid=? and a.dr='0'";
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
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        fillChangeCt();
        return R.SUCCESS_OPER();
    }


    public R fillChangeCt() {
        QueryWrapper<ResChangeItem> ew = new QueryWrapper<ResChangeItem>();
        ew.and(i -> i.eq("fillct", "0"));
        List<ResChangeItem> list = ResChangeItemServiceImpl.list(ew);

        for (int i = 0; i < list.size(); i++) {
            ResChangeItem entity = list.get(i);
            if (ToolUtil.isNotEmpty(entity.getType())) {
                if (ZcCommonService.ZC_BUS_TYPE_TK.equals(entity.getType())) {
                    fillChangeTypeTK(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_LY.equals(entity.getType())) {
                    fillChangeTypeLY(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_JY.equals(entity.getType())) {
                    fillChangeTypeJY(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_GH.equals(entity.getType())) {
                    fillChangeTypeGH(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_CGJB.equals(entity.getType())) {
                    fillChangeTypeCGJB(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_CGWB.equals(entity.getType())) {
                    fillChangeTypeCGWB(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_CGCW.equals(entity.getType())) {
                    fillChangeTypeCGCW(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_ZJ.equals(entity.getType())) {
                    fillChangeTypeZJ(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_BF.equals(entity.getType())) {
                    fillChangeTypeBF(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_DB.equals(entity.getType())) {
                    fillChangeTypeDB(entity);
                } else if (ZcCommonService.ZC_BUS_TYPE_ZY.equals(entity.getType())) {
                    fillChangeTypeZY(entity);
                }

            }
        }

        return R.SUCCESS_OPER();
    }


    public R fillChangeTypeCGCW(ResChangeItem entity) {
        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();
        R res = resCFinanceService.selectData(busuuid, resid);
        JSONArray res_arr = res.queryDataToJSONArray();
        if (res_arr.size() == 1) {
            JSONObject item = res_arr.getJSONObject(0);
            ct = "";
            String tbelongcompstatus = item.getString("tbelongcompstatus");
            String tbelongpartstatus = item.getString("tbelongpartstatus");
            String tbuypricestatus = item.getString("tbuypricestatus");
            String tnetworthstatus = item.getString("tnetworthstatus");
            String tresidualvaluestatus = item.getString("tresidualvaluestatus");
            String taccumulatedstatus = item.getString("taccumulatedstatus");

            String fbelongcompfullname = item.getString("fbelongcompfullname");
            String tbelongcompfullname = item.getString("tbelongcompfullname");
            String fbuyprice = item.getString("fbuyprice");
            String tbuyprice = item.getString("tbuyprice");
            String fnetworth = item.getString("fnetworth");
            String tnetworth = item.getString("tnetworth");
            String faccumulateddepreciation = item.getString("faccumulateddepreciation");
            String taccumulateddepreciation = item.getString("taccumulateddepreciation");
            String fresidualvalue = item.getString("fresidualvalue");
            String tresidualvalue = item.getString("tresidualvalue");

            if (ToolUtil.isNotEmpty(tbelongcompstatus) && "true".equals(tbelongcompstatus)) {
                ct = ct + "【所属公司】字段由 \"" + fbelongcompfullname + "\" 变更为 \"" + tbelongcompfullname + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tbuypricestatus) && "true".equals(tbuypricestatus)) {
                ct = ct + "【采购单价】字段由 \"" + fbuyprice + "\" 变更为 \"" + tbuyprice + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tnetworthstatus) && "true".equals(tnetworthstatus)) {
                ct = ct + "【资产净值】字段由 \"" + fnetworth + "\" 变更为 \"" + tnetworth + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tresidualvaluestatus) && "true".equals(tresidualvaluestatus)) {
                ct = ct + "【设置残值】字段由 \"" + fresidualvalue + "\" 变更为 \"" + tresidualvalue + "\" ;";
            }

            if (ToolUtil.isNotEmpty(taccumulatedstatus) && "true".equals(taccumulatedstatus)) {
                ct = ct + "【累计折旧】字段由 \"" + faccumulateddepreciation + "\" 变更为 \"" + taccumulateddepreciation + "\" ;";
            }
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }

    public R fillChangeTypeCGWB(ResChangeItem entity) {
        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();

        R res = resCMaintenanceService.selectData(busuuid, resid);

        JSONArray res_arr = res.queryDataToJSONArray();
        if (res_arr.size() == 1) {
            ct = "";
            JSONObject item = res_arr.getJSONObject(0);
            String twbstatus = item.getString("twbstatus");
            String twbsupplierstatus = item.getString("twbsupplierstatus");
            String twbautostatus = item.getString("twbautostatus");
            String twbctstatus = item.getString("twbctstatus");
            String twboutdatestatus = item.getString("twboutdatestatus");

            String fwbstr = item.getString("fwbstr");
            String twbstr = item.getString("twbstr");
            String fwbsupplierstr = item.getString("fwbsupplierstr");
            String twbsupplierstr = item.getString("twbsupplierstr");
            String fwboutdatestr = item.getString("fwboutdatestr");
            String twboutdatestr = item.getString("twboutdatestr");
            String fwbct = item.getString("fwbct");
            String twbct = item.getString("twbct");
            if (ToolUtil.isNotEmpty(twbstatus) && "true".equals(twbstatus)) {
                ct = ct + "【维保状态】字段由 \"" + fwbstr + "\" 变更为 \"" + twbstr + "\" ;";
            }
            if (ToolUtil.isNotEmpty(twbsupplierstatus) && "true".equals(twbsupplierstatus)) {
                ct = ct + "【维保商】字段由 \"" + fwbsupplierstr + "\" 变更为 \"" + twbsupplierstr + "\" ;";
            }
            if (ToolUtil.isNotEmpty(twboutdatestatus) && "true".equals(twboutdatestatus)) {
                ct = ct + "【脱保日期】字段由 \"" + fwboutdatestr + "\" 变更为 \"" + twboutdatestr + "\" ;";
            }
            if (ToolUtil.isNotEmpty(twbctstatus) && "true".equals(twbctstatus)) {
                ct = ct + "【维保说明】字段由 \"" + fwbct + "\" 变更为 \"" + twbct + "\" ;";
            }
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }

    public R fillChangeTypeCGJB(ResChangeItem entity) {
        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();
        R res = resCBasicinformationService.selectData(busuuid, resid);
        JSONArray res_arr = res.queryDataToJSONArray();
        if (res_arr.size() == 1) {
            JSONObject item = res_arr.getJSONObject(0);
            ct = "";
            String tclassidstatus = item.getString("tclassidstatus");
            String tmodelstatus = item.getString("tmodelstatus");
            String tsnstatus = item.getString("tsnstatus");
            String tzcsourcestatus = item.getString("tzcsourcestatus");
            String tzccntstatus = item.getString("tzccntstatus");
            String tsupplierstatus = item.getString("tsupplierstatus");
            String tbrandstatus = item.getString("tbrandstatus");
            String tbuytimestatus = item.getString("tbuytimestatus");
            String tlocstatus = item.getString("tlocstatus");
            String tusefullifestatus = item.getString("tusefullifestatus");
            String tusedcompanyidstatus = item.getString("tusedcompanyidstatus");
            String tpartidstatus = item.getString("tpartidstatus");
            String tuseduseridstatus = item.getString("tuseduseridstatus");
            String tlabel1status = item.getString("tlabel1status");
            String tconfdescstatus = item.getString("tconfdescstatus");
            String tlocdtlstatus = item.getString("tlocdtlstatus");
            String tunitstatus = item.getString("tunitstatus");

            String fclassfullname = item.getString("fclassfullname");
            String tclassfullname = item.getString("tclassfullname");
            String fmodel = item.getString("fmodel");
            String tmodel = item.getString("tmodel");
            String fsn = item.getString("fsn");
            String tsn = item.getString("tsn");
            String funit = item.getString("funit");
            String tunit = item.getString("tunit");
            String fzccnt = item.getString("fzccnt");
            String tzccnt = item.getString("tzccnt");
            String fsupplierstr = item.getString("fsupplierstr");
            String tsupplierstr = item.getString("tsupplierstr");
            String fbrandstr = item.getString("fbrandstr");
            String tbrandstr = item.getString("tbrandstr");
            String fzcsourcestr = item.getString("fzcsourcestr");
            String tzcsourcestr = item.getString("tzcsourcestr");
            String flocstr = item.getString("flocstr");
            String tlocstr = item.getString("tlocstr");
            String flocdtl = item.getString("flocdtl");
            String tlocdtl = item.getString("tlocdtl");
            String fusefullifestr = item.getString("fusefullifestr");
            String tusefullifestr = item.getString("tusefullifestr");
            String fbuytimestr = item.getString("fbuytimestr");
            String tbuytimestr = item.getString("tbuytimestr");
            String fconfdesc = item.getString("fconfdesc");
            String tconfdesc = item.getString("tconfdesc");
            String fusedcompanyname = item.getString("fusedcompanyname");
            String tusedcompanyname = item.getString("tusedcompanyname");
            String fpartname = item.getString("fpartname");
            String tpartname = item.getString("tpartname");
            String fusedusername = item.getString("fusedusername");
            String tusedusername = item.getString("tusedusername");
            String flabel1 = item.getString("flabel1");
            String tlabel1 = item.getString("tlabel1");

            if (ToolUtil.isNotEmpty(tclassidstatus) && "true".equals(tclassidstatus)) {
                ct = ct + "【资产类别】字段由 \"" + fclassfullname + "\" 变更为 \"" + tclassfullname + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tmodelstatus) && "true".equals(tmodelstatus)) {
                ct = ct + "【规格类型】字段由 \"" + fmodel + "\" 变更为 \"" + tmodel + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tsnstatus) && "true".equals(tsnstatus)) {
                ct = ct + "【序列】字段由 \"" + fsn + "\" 变更为 \"" + tsn + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tzcsourcestatus) && "true".equals(tzcsourcestatus)) {
                ct = ct + "【来源】字段由 \"" + fzcsourcestr + "\" 变更为 \"" + tzcsourcestr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tzccntstatus) && "true".equals(tzccntstatus)) {
                ct = ct + "【数量】字段由 \"" + fzccnt + "\" 变更为 \"" + tzccnt + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tsupplierstatus) && "true".equals(tsupplierstatus)) {
                ct = ct + "【供应商】字段由 \"" + fsupplierstr + "\" 变更为 \"" + tsupplierstr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tbrandstatus) && "true".equals(tbrandstatus)) {
                ct = ct + "【品牌】字段由 \"" + fbrandstr + "\" 变更为 \"" + tbrandstr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tbuytimestatus) && "true".equals(tbuytimestatus)) {
                ct = ct + "【采购日期】字段由 \"" + fbuytimestr + "\" 变更为 \"" + tbuytimestr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tlocstatus) && "true".equals(tlocstatus)) {
                ct = ct + "【区域】字段由 \"" + flocstr + "\" 变更为 \"" + tlocstr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tusefullifestatus) && "true".equals(tusefullifestatus)) {
                ct = ct + "【使用周期】字段由 \"" + fusefullifestr + "\" 变更为 \"" + tusefullifestr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tusefullifestatus) && "true".equals(tusefullifestatus)) {
                ct = ct + "【使用周期】字段由 \"" + fusefullifestr + "\" 变更为 \"" + tusefullifestr + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tusedcompanyidstatus) && "true".equals(tusedcompanyidstatus)) {
                ct = ct + "【使用公司】字段由 \"" + fusedcompanyname + "\" 变更为 \"" + tusedcompanyname + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tpartidstatus) && "true".equals(tpartidstatus)) {
                ct = ct + "【使用部门】字段由 \"" + fpartname + "\" 变更为 \"" + tpartname + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tuseduseridstatus) && "true".equals(tuseduseridstatus)) {
                ct = ct + "【使用人】字段由 \"" + fusedusername + "\" 变更为 \"" + tusedusername + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tlabel1status) && "true".equals(tlabel1status)) {
                ct = ct + "【标签1】字段由 \"" + flabel1 + "\" 变更为 \"" + tlabel1 + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tconfdescstatus) && "true".equals(tconfdescstatus)) {
                ct = ct + "【配置描述】字段由 \"" + fconfdesc + "\" 变更为 \"" + tconfdesc + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tlocdtlstatus) && "true".equals(tlocdtlstatus)) {
                ct = ct + "【位置】字段由 \"" + flocdtl + "\" 变更为 \"" + tlocdtl + "\" ;";
            }

            if (ToolUtil.isNotEmpty(tunitstatus) && "true".equals(tunitstatus)) {
                ct = ct + "【计量单位】字段由 \"" + funit + "\" 变更为 \"" + tunit + "\" ;";
            }

        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }


    public R fillChangeTypeZJ(ResChangeItem entity) {
        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();
        String sql = "select " + ZcCommonService.resSqlbody + " t.* , item.*,t.uuid zcuuid from res t,res_residual_item item where item.dr='0' and t.id=item.resid and item.uuid=? and item.resid=? ";
        Rcd rs = db.uniqueRecord(sql, busuuid, resid);
        if (rs != null) {
            String bnetworth = rs.getString("bnetworth");
            String anetworth = rs.getString("anetworth");
            String lossprice = rs.getString("lossprice");
            String buyprice = rs.getString("buyprice");
            String baccumulateddepreciation = rs.getString("baccumulateddepreciation");
            ct = "【资产净值】字段由 \"" + bnetworth + "\" 变更为 \"" + anetworth + "\";";
            ct = ct + "采购单价:" + buyprice + ";变更前累计折旧:" + baccumulateddepreciation + ";本次折旧价:" + lossprice;
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }

    public R fillChangeTypeBF(ResChangeItem entity) {

        String busuuid = entity.getBusuuid();
        String ct = "";
        ct = "【状态】变更为 \"报废\"";
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }

    public R fillChangeTypeDB(ResChangeItem entity) {
        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();

        String sql = "select\n" +
                "(select route_name from hrm_org_part where node_id=b.fcompid) fusedcompanyname,\n" +
                "(select route_name from hrm_org_part where node_id=b.tousedcompid) tusedcompanyname,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.floc) flocstr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.toloc) tlocstr,\n" +
                "  b.*\n" +
                "from res_allocate_item b where dr='0' and b.busuuid=? and b.resid=?";
        Rcd rs = db.uniqueRecord(sql, busuuid, resid);
        if (rs != null) {
            String fusedcompanyname = rs.getString("fusedcompanyname");
            String tusedcompanyname = rs.getString("tusedcompanyname");
            String flocstr = rs.getString("flocstr");
            String tlocstr = rs.getString("tlocstr");
            String flocdtl = rs.getString("flocdtl");
            String tolocdtl = rs.getString("tolocdtl");
            ct = "确认调拨!【使用公司】字段由 \"" + fusedcompanyname + "\" 变更为 \"" + tusedcompanyname + "\"";
            ct = ct + ";【区域】字段由 \"" + flocstr + "\" 变更为 \"" + tlocstr + "\"";
            ct = ct + ";【位置】字段由 \"" + flocdtl + "\" 变更为 \"" + tolocdtl + "\"";
            ct = ct + ";【状态】字段由 \"调拨中\" 变更为 \"闲置\"";
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }


    public R fillChangeTypeZY(ResChangeItem entity) {
        String ct = "";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();

        entity.setFillct("1");
        entity.setCt(ct);
        // ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }


    public R fillChangeTypeLY(ResChangeItem entity) {
        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();

        R res = resCollectionreturnService.selectData(busuuid, resid);
        JSONArray res_arr = res.queryDataToJSONArray();
        if (res_arr.size() == 1) {
            ct = "";
            JSONObject item = res_arr.getJSONObject(0);
            String fusedcomp = item.getString("fcompfullname");
            String tusedcomp = item.getString("tcompfullname");
            String fpart = item.getString("fpartfullame");
            String tpart = item.getString("tpartfullame");
            String fuser = item.getString("fusedusername");
            String tuser = item.getString("tusedusername");
            String floc = item.getString("flocstr");
            String tloc = item.getString("tlocstr");
            String flocdtl = item.getString("flocdtl");
            String tlocdtl = item.getString("tlocdtl");

            ct = "【使用公司】字段由 \"" + fusedcomp + "\" 变更为 \"" + tusedcomp + "\"";
            ct = ct + ";【使用部门】字段由 \"" + fpart + "\" 变更为 \"" + tpart + "\"";
            ct = ct + ";【使用人】字段由 \"" + fuser + "\" 变更为 \"" + tuser + "\"";
            ct = ct + ";【区域】字段由 \"" + floc + "\" 变更为 \"" + tloc + "\"";
            ct = ct + ";【位置】字段由 \"" + flocdtl + "\" 变更为 \"" + tlocdtl + "\"";
            ct = ct + ";【状态】字段由 \"闲置\" 变更为 \"在用\"";
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }


    public R fillChangeTypeTK(ResChangeItem entity) {

        String ct = "无";
        String busuuid = entity.getBusuuid();
        String resid = entity.getResid();

        R res = resCollectionreturnService.selectData(busuuid, resid);
        JSONArray res_arr = res.queryDataToJSONArray();
        if (res_arr.size() == 1) {
            ct = "";
            JSONObject item = res_arr.getJSONObject(0);
            String fusedcomp = item.getString("fcompfullname");
            String tusedcomp = item.getString("tcompfullname");
            String fpart = item.getString("fpartfullame");
            String tpart = item.getString("tpartfullame");
            String fuser = item.getString("fusedusername");
            String tuser = item.getString("tusedusername");
            String floc = item.getString("flocstr");
            String tloc = item.getString("tlocstr");
            String flocdtl = item.getString("flocdtl");
            String tlocdtl = item.getString("tlocdtl");

            ct = "【使用公司】字段由 \"" + fusedcomp + "\" 变更为 \"" + tusedcomp + "\"";
            ct = ct + ";【使用部门】字段由 \"" + fpart + "\" 变更为 \"" + tpart + "\"";
            ct = ct + ";【使用人】字段由 \"" + fuser + "\" 变更为 \"" + tuser + "\"";
            ct = ct + ";【区域】字段由 \"" + floc + "\" 变更为 \"" + tloc + "\"";
            ct = ct + ";【位置】字段由 \"" + flocdtl + "\" 变更为 \"" + tlocdtl + "\"";
            ct = ct + ";【状态】字段由 \"在用\" 变更为 \"退库\"";
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }


    public R fillChangeTypeJY(ResChangeItem entity) {
        String busuuid = entity.getBusuuid();
        QueryWrapper<ResLoanreturnItem> ew = new QueryWrapper<ResLoanreturnItem>();
        ew.and(i -> i.eq("busuuid", busuuid).eq("resid", entity.getResid()));
        ResLoanreturnItem item = ResLoanreturnItemServiceImpl.getOne(ew);
        String ct = "无";
        if (item != null) {
            String recycle = item.getFrecycle();
            String recyclestr = ZcRecycleEnum.parseCode(recycle);
            ct = "【状态】由 \"" + recyclestr + "\" 变更为 \"借用\"";
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }

    public R fillChangeTypeGH(ResChangeItem entity) {

        String busuuid = entity.getBusuuid();
        QueryWrapper<ResLoanreturnItem> ew = new QueryWrapper<ResLoanreturnItem>();
        ew.and(i -> i.eq("busuuid", busuuid).eq("resid", entity.getResid()));
        ResLoanreturnItem item = ResLoanreturnItemServiceImpl.getOne(ew);
        String ct = "无";
        if (item != null) {
            String recycle = item.getFrecycle();
            String recyclestr = ZcRecycleEnum.parseCode(recycle);
            ct = "【状态】由 \"借用\" 变更为 \"" + recyclestr + "\"";
        }
        entity.setFillct("1");
        entity.setCt(ct);
        ResChangeItemServiceImpl.saveOrUpdate(entity);
        return R.SUCCESS_OPER();
    }

}
