package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.entity.ResAllocateItem;
import com.dt.module.zc.entity.ResChangeItem;
import com.dt.module.zc.entity.ResScrape;
import com.dt.module.zc.service.IResAllocateItemService;
import com.dt.module.zc.service.IResAllocateService;
import com.dt.module.zc.service.IResChangeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-04-25
 */
@Service
public class ResAllocateService extends BaseService {

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    @Autowired
    IResAllocateItemService ResAllocateItemServiceImpl;

    @Autowired
    IResChangeItemService ResChangeItemServiceImpl;

    @Autowired
    ZcService zcService;

    public R startDbFlow(String pinst, String uuid, String ifsp) {
        if ("1".equals(ifsp)) {
            UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
            ups.set("pinst", pinst);
            ups.set("status", SysProcessDataService.PSTATUS_DTL_INAPPROVAL);
            ups.eq("uuid", uuid);
            ResAllocateServiceImpl.update(ups);
        } else if ("0".equals(ifsp)) {
            UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
            ups.set("status", SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
            ups.eq("uuid", uuid);
            ResAllocateServiceImpl.update(ups);
            sureDb(uuid, SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
        }
        return R.SUCCESS_OPER();
    }

    public R cancelDb(String busid, String status) {
        String sql2 = "update res_allocate_item a,res b set " +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='' " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, busid);
        UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
        ups.set("status", status);
        ups.eq("uuid", busid);
        ResAllocateServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    public R sureDb(String busid, String status) {

        UpdateWrapper<ResAllocate> ups = new UpdateWrapper<ResAllocate>();
        ups.set("status", status);
        ups.eq("uuid", busid);
        ResAllocateServiceImpl.update(ups);
        String sql2 = "update res_allocate_item a,res b set a.update_by='" + this.getUserId() + "'";
        sql2 = sql2 + ",b.recycle=a.frecycle";
        sql2 = sql2 + ",b.locdtl=a.tolocdtl";
        sql2 = sql2 + ",b.loc=a.toloc";
        sql2 = sql2 + ",b.inprocess='0'";
        sql2 = sql2 + ",b.inprocessuuid=''";
        sql2 = sql2 + ",b.inprocesstype=''";
        sql2 = sql2 + ",a.acttime=now()";
        sql2 = sql2 + ",b.used_company_id=a.tousedcompid";
        sql2 = sql2 + " where a.resid=b.id and a.busuuid=? and a.dr='0'";
        db.execute(sql2, busid);

        String sql4 = "update res_allocate a set acttime=now() where uuid=?";
//        db.execute(sql3, busid);
        db.execute(sql4, busid);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResAllocateItem> qw = new QueryWrapper<ResAllocateItem>();
        qw.and(i -> i.eq("busuuid", busid));
        List<ResAllocateItem> items = ResAllocateItemServiceImpl.list(qw);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(busid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_DB);
            e.setCt("资产调拨");
            e.setFillct("1");
            e.setCreateBy(this.getUserId());
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }


    public R createDb(ResAllocate entity, String items) {
        String id = "";
        String uuid = "";
        if (ToolUtil.isNotEmpty(entity.getId())) {
            id = entity.getId();
            uuid = entity.getUuid();
        } else {
            ArrayList<ResAllocateItem> cols = new ArrayList<ResAllocateItem>();
            uuid = zcService.createUuid(ZcCommonService.UUID_DB);
            entity.setUuid(uuid);
            entity.setStatus(SysProcessDataService.PSTATUS_APPLY);
            ResAllocateServiceImpl.saveOrUpdate(entity);

//            QueryWrapper<ResAllocate> ew = new QueryWrapper<ResAllocate>();
//            String finalUuid = uuid;
//            ew.and(i -> i.eq("uuid", finalUuid));
//            ResAllocate dbobj = ResAllocateServiceImpl.getOne(ew);

            JSONArray arr = JSONArray.parseArray(items);
            for (int i = 0; i < arr.size(); i++) {
                ResAllocateItem e = new ResAllocateItem();
                e.setBusuuid(uuid);
                e.setResid(arr.getJSONObject(i).getString("id"));
                e.setFcompid(entity.getFcompid());
                e.setFrecycle(arr.getJSONObject(i).getString("recycle"));
                e.setFcompname(entity.getFcompname());
                e.setBusdate(entity.getBusdate());
//                e.setFloc(entity.getFloc());
//                e.setFlocname(entity.getFlocname());
//                e.setFlocdtl(entity.getFlocdtl());
                e.setTousedcompid(entity.getTousedcompid());
                e.setTousedcompname(entity.getTousedcompname());
                e.setToloc(entity.getToloc());
                e.setTolocname(entity.getTolocname());
                e.setTolocdtl(entity.getTolocdtl());
                cols.add(e);

                UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
                ups.set("inprocess", "1");
                ups.set("inprocessuuid", uuid);
                ups.set("inprocesstype", ZcCommonService.DATARANGE_DB);
                ups.eq("id", arr.getJSONObject(i).getString("id"));
                ResServiceImpl.update(ups);
            }
            ResAllocateItemServiceImpl.saveBatch(cols);
        }
        return R.SUCCESS_OPER();

    }


    //结束流程
    public R finishDbFlow(String busid, String status) {
        if (SysProcessDataService.PSTATUS_DTL_FAILED.equals(status)) {
            return cancelDb(busid, SysProcessDataService.PSTATUS_DTL_FAILED);
        } else if (SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(status)) {
            return sureDb(busid, SysProcessDataService.PSTATUS_DTL_SUCCESS);
        } else {
            return R.FAILURE_NO_DATA();
        }
    }


}
