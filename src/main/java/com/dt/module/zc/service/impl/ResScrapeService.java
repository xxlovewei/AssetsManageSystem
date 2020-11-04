package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.busenum.ZcRecycleEnum;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.entity.*;
import com.dt.module.zc.service.IResChangeItemService;
import com.dt.module.zc.service.IResScrapeItemService;
import com.dt.module.zc.service.IResScrapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResScrapeService extends BaseService {

    @Autowired
    ZcService zcService;

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResChangeItemService ResChangeItemServiceImpl;

    @Autowired
    IResScrapeService ResScrapeServiceImpl;

    @Autowired
    IResScrapeItemService ResScrapeItemServiceImpl;

    public R startBfFlow(String pinst, String uuid, String ifsp) {
        if ("1".equals(ifsp)) {
            UpdateWrapper<ResScrape> ups = new UpdateWrapper<ResScrape>();
            ups.set("pinst", pinst);
            ups.set("status", SysProcessDataService.PSTATUS_DTL_INAPPROVAL);
            ups.eq("uuid", uuid);
            ResScrapeServiceImpl.update(ups);
        } else if ("0".equals(ifsp)) {
            UpdateWrapper<ResScrape> ups = new UpdateWrapper<ResScrape>();
            ups.set("status", SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
            ups.eq("uuid", uuid);
            ResScrapeServiceImpl.update(ups);
            sureBf(uuid, SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
        }
        return R.SUCCESS_OPER();
    }
    public R cancelBf(String busid, String status) {
        //更新RES数据
        String sql2 = "update res_scrape_item a,res b set " +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='' " +
                "where a.resid=b.id and a.uuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, busid);
        UpdateWrapper<ResScrape> ups = new UpdateWrapper<ResScrape>();
        ups.set("status", status);
        ups.eq("uuid", busid);
        ResScrapeServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    public R createBf(ResScrape entity, String busitimestr, String items) throws ParseException {
        ArrayList<ResScrapeItem> cols = new ArrayList<ResScrapeItem>();
        String id = entity.getId();
        String uuid = "";
        if (ToolUtil.isEmpty(id)) {
            uuid = zcService.createUuid(ZcCommonService.UUID_BF);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(busitimestr);
            entity.setBusidate(date);
            entity.setUuid(uuid);
            entity.setStatus(SysProcessDataService.PSTATUS_APPLY);
            if (ToolUtil.isEmpty(entity.getProcessuserid())) {
                entity.setProcessuserid(this.getUserId());
            }
            if (ToolUtil.isEmpty(entity.getProcessusername())) {
                entity.setProcessusername(this.getUserName());
            }
            JSONArray itemsarr = JSONArray.parseArray(items);
            for (int i = 0; i < itemsarr.size(); i++) {
                UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
                ups.set("inprocess", "1");
                ups.set("inprocessuuid", uuid);
                ups.set("inprocesstype", ZcCommonService.DATARANGE_BF);
                ups.eq("id", itemsarr.getJSONObject(i).getString("id"));
                ResServiceImpl.update(ups);
                ResScrapeItem e = new ResScrapeItem();
                e.setUuid(uuid);
                e.setResid(itemsarr.getJSONObject(i).getString("id"));
                e.setPrestatus(itemsarr.getJSONObject(i).getString("status"));
                cols.add(e);
            }
            entity.setCnt(new BigDecimal(cols.size()));
            ResScrapeItemServiceImpl.saveBatch(cols);
            ResScrapeServiceImpl.save(entity);
        } else {
            return R.FAILURE("参数有误");
        }
        return R.SUCCESS_OPER();
    }


    public R sureBf(String busid, String status) {

        //保存变更前数据
        UpdateWrapper<ResScrape> ups = new UpdateWrapper<ResScrape>();
        ups.set("status", status);
        ups.eq("uuid", busid);
        ResScrapeServiceImpl.update(ups);

        //更新数据
        String sql2 = "update res_scrape_item a,res b set " +
                "b.recycle='" + ZcRecycleEnum.RECYCLE_SCRAP.getValue() + "'," +
                "b.inprocess='0'," +
                "b.isscrap='1'," +
                "b.uuidbf=a.uuid," +
                "b.scrapdate=a.create_time," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='' " +
                "where a.resid=b.id and a.uuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, busid);

        //记录资产变更
        QueryWrapper<ResScrapeItem> ew = new QueryWrapper<ResScrapeItem>();
        ew.and(i -> i.eq("uuid", busid));
        List<ResScrapeItem> items = ResScrapeItemServiceImpl.list(ew);
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(busid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_BF);
            e.setFillct("0");
            e.setCdate(new Date());
            e.setMark("资产报废");
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        return R.SUCCESS_OPER();
    }

    //结束流程
    public R finishBfFlow(String busid, String status) {
        if (SysProcessDataService.PSTATUS_DTL_FAILED.equals(status)) {
            return cancelBf(busid, SysProcessDataService.PSTATUS_DTL_FAILED);
        } else if (SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(status)) {
            return sureBf(busid, SysProcessDataService.PSTATUS_DTL_SUCCESS);
        } else {
            return R.FAILURE_NO_DATA();
        }
    }
}
