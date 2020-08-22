package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.zc.entity.ResLoanreturn;
import com.dt.module.zc.entity.ResLoanreturnItem;
import com.dt.module.zc.service.IResLoanreturnItemService;
import com.dt.module.zc.service.IResLoanreturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Service
public class ResLoanreturnService extends BaseService {
    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";
    public static String STATUS_CANCEL = "cancel";
    public static String STATUS_APPLY = "apply";
    public static String STATUS_APPROVAL = "approval";

    public static String BUS_STATUS_JY = "JY";
    public static String BUS_STATUS_GH = "GH";


    @Autowired
    IResLoanreturnService ResLoanreturnServiceImpl;

    @Autowired
    IResLoanreturnItemService ResLoanreturnItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    ZcChangeService zcChangeService;


    public R zcJy(ResLoanreturn entity, String items) {

        String id = entity.getId();
        String uuid = "";
        entity.setBusstatus(this.BUS_STATUS_JY);
        //获取UUID
        if (ToolUtil.isNotEmpty(id)) {
            uuid = entity.getBusuuid();
            //解锁之前的数据,
            String sql2 = "update res_loanreturn_item a,res b set \n" +
                    "  b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql2, uuid);
        } else {
            uuid = zcService.createUuid(ZcCommonService.UUID_JY);
            //当前方案设置结束流程
            entity.setBusuuid(uuid);
            entity.setStatus(ResLoanreturnService.STATUS_SUCCESS);
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
            e.setIsreturn("0");
            list.add(e);
        }

        //先保存item数据,清除历史
        QueryWrapper<ResLoanreturnItem> qw = new QueryWrapper<ResLoanreturnItem>();
        String finalUuid = uuid;
        qw.and(i -> i.eq("busuuid", finalUuid));
        ResLoanreturnItemServiceImpl.remove(qw);
        ResLoanreturnItemServiceImpl.saveOrUpdateBatch(list);

        //锁定单据中的数据
        String sql2 = "update res_loanreturn_item a,res b set \n" +
                "  b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        ResLoanreturnServiceImpl.saveOrUpdate(entity);


        //临时锁定
        if (!ResLoanreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
            String sql3 = " update res_loanreturn_item a,res b set \n" +
                    "  b.inprocess='1',b.inprocessuuid='" + uuid + "',b.inprocesstype='" + ZcCommonService.ZC_BUS_TYPE_JY + "' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql3, uuid);
        }

        if (ResLoanreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
            zcChangeService.zcJyConfirm(uuid);
        }
        return R.SUCCESS_OPER();
    }

    public R zcGh(String id, String rreturndate, String rprocessuserid, String rprocessusername) {
        ResLoanreturn entity = ResLoanreturnServiceImpl.getById(id);
        String status = entity.getStatus();
        String busstatus = entity.getBusstatus();
        if (!STATUS_SUCCESS.equals(status)) {
            return R.FAILURE("当前单据办理状态不可做归还操作");
        }
        if (BUS_STATUS_GH.equals(busstatus)) {
            return R.FAILURE("当前单据业务状态不可做归还操作");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
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


}
