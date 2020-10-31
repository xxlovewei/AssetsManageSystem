package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.busenum.ZcRecycleEnum;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.entity.ResChangeItem;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.entity.ResLoanreturn;
import com.dt.module.zc.entity.ResLoanreturnItem;
import com.dt.module.zc.service.IResChangeItemService;
import com.dt.module.zc.service.IResLoanreturnItemService;
import com.dt.module.zc.service.IResLoanreturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ResLoanreturnService extends BaseService {

    public static String BUS_STATUS_JY = "JY";
    public static String BUS_STATUS_GH = "GH";


    @Autowired
    IResChangeItemService ResChangeItemServiceImpl;

    @Autowired
    IResLoanreturnService ResLoanreturnServiceImpl;

    @Autowired
    IResLoanreturnItemService ResLoanreturnItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    @Lazy
    ZcChangeService zcChangeService;

    //开始流程
    public R startLyFlow(String pinst, String uuid, String ifsp) {
        if ("1".equals(ifsp)) {
            UpdateWrapper<ResLoanreturn> ups = new UpdateWrapper<ResLoanreturn>();
            ups.set("pinst", pinst);
            ups.set("status", SysProcessDataService.PSTATUS_DTL_INAPPROVAL);
            ups.eq("busuuid", uuid);
            ResLoanreturnServiceImpl.update(ups);
        } else if ("0".equals(ifsp)) {
            UpdateWrapper<ResLoanreturn> ups = new UpdateWrapper<ResLoanreturn>();
            ups.set("status", SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
            ups.eq("busuuid", uuid);
            ResLoanreturnServiceImpl.update(ups);
            sureJy(uuid, SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
        }
        return R.SUCCESS_OPER();
    }


    //取消领用,流程失败，或者取消
    public R cancelJy(String busid, String status) {

        //更新RES数据
        String sql2 = "update res_loanreturn_item a,res b set " +
//                "b.loc=a.tloc," +
//                "b.used_company_id=a.tusedcompanyid," +
//                "b.part_id=a.tpartid," +
//                "b.used_userid=a.tuseduserid," +
//                "b.locdtl=a.tlocdtl," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='' " +
//                "b.uuidly=a.busuuid " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, busid);
        UpdateWrapper<ResLoanreturn> ups = new UpdateWrapper<ResLoanreturn>();
        ups.set("status", status);
        ups.eq("busuuid", busid);
        ResLoanreturnServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //确认领用
    public R sureJy(String busid, String status) {
        //保存变更前数据
        UpdateWrapper<ResLoanreturn> ups = new UpdateWrapper<ResLoanreturn>();
        ups.set("status", status);
        ups.eq("busuuid", busid);
        ResLoanreturnServiceImpl.update(ups);

        String sql = "update res_loanreturn_item a,res b set " +
                "   a.frecycle=b.recycle " +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, busid);

        //更新数据
        String sql2 = "update res_loanreturn_item a,res b set " +
                "b.recycle='" + ZcRecycleEnum.RECYCLE_BORROW.getValue() + "'," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='', " +
                "b.uuidjy=a.busuuid " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, busid);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResLoanreturnItem> ew = new QueryWrapper<ResLoanreturnItem>();
        ew.and(i -> i.eq("busuuid", busid));
        List<ResLoanreturnItem> items = ResLoanreturnItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(busid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_JY);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产借用");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }


    //结束流程
    public R finishJyFlow(String busid, String status) {
        if (SysProcessDataService.PSTATUS_DTL_FAILED.equals(status)) {
            return cancelJy(busid, SysProcessDataService.PSTATUS_DTL_FAILED);
        } else if (SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(status)) {
            return sureJy(busid, SysProcessDataService.PSTATUS_DTL_SUCCESS);
        } else {
            return R.FAILURE_NO_DATA();
        }
    }

    //资产借用生产单据
    public R zcJy(ResLoanreturn entity, String items) {

        String id = entity.getId();
        String uuid = "";
        entity.setBusstatus(this.BUS_STATUS_JY);

        //获取UUID
        if (ToolUtil.isNotEmpty(id)) {
            uuid = entity.getBusuuid();
            if (!SysProcessDataService.PSTATUS_APPLY.equals(entity.getStatus())) {
                return R.FAILURE("当前状态不允许修改");
            }
            //可能数据有变动，先解锁当前的数据,后面会重新加锁
            String sql2 = "update res_loanreturn_item a,res b set b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql2, uuid);
        } else {
            uuid = zcService.createUuid(ZcCommonService.UUID_JY);
            //当前方案设置结束流程
            entity.setBusuuid(uuid);
            entity.setStatus(SysProcessDataService.PSTATUS_APPLY);
        }
        JSONArray items_arr = JSONArray.parseArray(items);
        ArrayList<ResLoanreturnItem> list = new ArrayList<ResLoanreturnItem>();
        for (int i = 0; i < items_arr.size(); i++) {
            ResLoanreturnItem e = new ResLoanreturnItem();
            e.setResid(items_arr.getJSONObject(i).getString("id"));
            e.setBusdate(entity.getBusdate());
            e.setReturndate(entity.getReturndate());
            e.setBusuuid(uuid);
            e.setLruserid(entity.getLruserid());
            e.setLrusername(entity.getLrusername());
            e.setTusedcompanyid(entity.getTusedcompanyid());
            e.setTpartid(entity.getTpartid());
            e.setTloc(entity.getTloc());
            e.setTlocdtl(entity.getTlocdtl());
            e.setReturndate(entity.getReturndate());
            e.setIsreturn("0");
            list.add(e);
        }

        //先保存item数据,清除历史
        QueryWrapper<ResLoanreturnItem> qw = new QueryWrapper<ResLoanreturnItem>();
        String finalUuid = uuid;
        qw.and(i -> i.eq("busuuid", finalUuid));
        ResLoanreturnItemServiceImpl.remove(qw);
        ResLoanreturnItemServiceImpl.saveOrUpdateBatch(list);
        ResLoanreturnServiceImpl.saveOrUpdate(entity);
        //锁定单据中的数据
        String sql2 = "update res_loanreturn_item a,res b set b.inprocess='1',b.inprocessuuid='" + uuid + "',b.inprocesstype='" + ZcCommonService.ZC_BUS_TYPE_JY + "' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);


        return R.SUCCESS_OPER();
    }

    public R zcGh(String id, String rreturndate, String rprocessuserid, String rprocessusername) {
        ResLoanreturn entity = ResLoanreturnServiceImpl.getById(id);
        String status = entity.getStatus();
        String busstatus = entity.getBusstatus();
        if (BUS_STATUS_GH.equals(busstatus) || SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(status) || SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL.equals(status)) {
        } else {
            return R.FAILURE("当前单据办理状态不可做归还操作");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(rreturndate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setBusstatus(BUS_STATUS_GH);
        entity.setRprocessuserid(rprocessuserid);
        entity.setRprocessusername(rprocessusername);
        entity.setRreturndate(date);
        ResLoanreturnServiceImpl.saveOrUpdate(entity);

        UpdateWrapper<ResLoanreturnItem> ups = new UpdateWrapper<ResLoanreturnItem>();
        ups.set("isreturn", "1");
        ups.set("rreturndate", rreturndate);
        ups.eq("busuuid", entity.getBusuuid());
        ResLoanreturnItemServiceImpl.update(ups);
        zcChangeService.zcGhConfirm(entity.getBusuuid());
        return R.SUCCESS_OPER();

    }


    public R selectByUuid(String uuid) {
        return selectData(uuid, null);
    }

    public R selectData(String uuid, String resid) {
        JSONObject res = new JSONObject();
        String sql2 = "select " + ZcCommonService.resSqlbody + " t.*," +
                "(select name from sys_user_info where user_id=b.create_by) createusername, " +
                "date_format(busdate,'%Y-%m-%d') busdatestr, " +
                "date_format(returndate,'%Y-%m-%d') returndatestr, " +
                "date_format(rreturndate,'%Y-%m-%d') rreturndatestr, " +
                "(select route_name from hrm_org_employee aa,hrm_org_part bb where aa.node_id=bb.node_id and empl_id=(select empl_id from sys_user_info where user_id=b.lruserid) limit 1 ) lruserorginfo," +
                "b.* " +
                "from res_loanreturn_item b,res t where b.dr='0' and t.dr='0' " +
                "and t.id=b.resid " +
                "and b.busuuid=?";
        if (ToolUtil.isNotEmpty(resid)) {
            sql2 = sql2 + " and resid='" + resid + "'";
        }
        Rcd rsone = db.uniqueRecord("select * from res_loanreturn where dr='0' and busuuid=?", uuid);
        res = ConvertUtil.OtherJSONObjectToFastJSONObject(rsone.toJsonObject());
        RcdSet rs = db.query(sql2, uuid);
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }

}
