package com.dt.module.zc.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
import com.dt.module.cmdb.service.IResService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.impl.SysProcessDataService;
import com.dt.module.zc.entity.ResCBasicinformationItem;
import com.dt.module.zc.entity.ResChangeItem;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.entity.ResCollectionreturnItem;
import com.dt.module.zc.service.IResChangeItemService;
import com.dt.module.zc.service.IResCollectionreturnItemService;
import com.dt.module.zc.service.IResCollectionreturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResCollectionreturnService extends BaseService {


    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";
    public static String STATUS_CANCEL = "cancel";

    @Autowired
    IResChangeItemService ResChangeItemServiceImpl;


    @Autowired
    FlowDataService flowDataService;

    @Autowired
    IResCollectionreturnService ResCollectionreturnServiceImpl;

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResCollectionreturnItemService ResCollectionreturnItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    @Lazy
    ZcChangeService zcChangeService;

    //开始流程
    public R startFlow(String pinst, String uuid, String ifsp) {
        if ("1".equals(ifsp)) {
            UpdateWrapper<ResCollectionreturn> ups = new UpdateWrapper<ResCollectionreturn>();
            ups.set("pinst", pinst);
            ups.set("status", SysProcessDataService.PSTATUS_DTL_INAPPROVAL);
            ups.eq("busuuid", uuid);
            ResCollectionreturnServiceImpl.update(ups);
        } else if ("0".equals(ifsp)) {
            UpdateWrapper<ResCollectionreturn> ups = new UpdateWrapper<ResCollectionreturn>();
            ups.set("status", SysProcessDataService.PSTATUS_DTL_FINISH_NO_APPROVAL);
            ups.eq("busuuid", uuid);
            ResCollectionreturnServiceImpl.update(ups);
        }
        return R.SUCCESS_OPER();
    }

    //结束流程
    public R finishFlow(String busid, String status) {
        if (SysProcessDataService.PSTATUS_DTL_FAILED.equals(status)) {
            return cancelLy(busid, SysProcessDataService.PSTATUS_DTL_FAILED);
        } else if (SysProcessDataService.PSTATUS_DTL_SUCCESS.equals(status)) {
            return sureLY(busid);
        } else {
            return R.FAILURE_NO_DATA();
        }
    }

    //取消领用,流程失败，或者取消
    public R cancelLy(String busid, String status) {
        //更新RES数据
        String sql2 = "update res_collectionreturn_item a,res b set \n" +
                "b.loc=a.tloc," +
                "b.used_company_id=a.tusedcompanyid," +
                "b.part_id=a.tpartid," +
                "b.used_userid=a.tuseduserid," +
                "b.locdtl=a.tlocdtl," +
                "b.inprocess='0'," +
                "b.inprocessuuid=''," +
                "b.inprocesstype='', " +
                "b.uuidly=a.busuuid " +
                "where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, busid);
        UpdateWrapper<ResCollectionreturn> ups = new UpdateWrapper<ResCollectionreturn>();
        ups.set("status", status);
        ups.eq("busuuid", busid);
        ResCollectionreturnServiceImpl.update(ups);
        return R.SUCCESS_OPER();
    }

    //确认领用
    public R sureLY(String busid) {
        UpdateWrapper<ResCollectionreturn> ups = new UpdateWrapper<ResCollectionreturn>();
        ups.set("status", SysProcessDataService.PSTATUS_DTL_SUCCESS);
        ups.eq("busuuid", busid);
        ResCollectionreturnServiceImpl.update(ups);

        //保存变更前RES数据
        String sql = " update res_collectionreturn_item a,res b set \n" +
                "   a.fusedcompanyid=b.used_company_id\n" +
                " , a.fpartid=b.part_id\n" +
                " , a.fuseduserid=b.used_userid\n" +
                " , a.floc=b.loc\n" +
                " , a.flocdtl=b.locdtl\n" +
                "   where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql, busid);

        //更新RES数据
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
        db.execute(sql2, busid);

        //记录资产变更
        ArrayList<ResChangeItem> cols = new ArrayList<ResChangeItem>();
        QueryWrapper<ResCollectionreturnItem> ew = new QueryWrapper<ResCollectionreturnItem>();
        ew.and(i -> i.eq("busuuid", busid));
        List<ResCollectionreturnItem> items = ResCollectionreturnItemServiceImpl.list(ew);
        for (int i = 0; i < items.size(); i++) {
            ResChangeItem e = new ResChangeItem();
            e.setBusuuid(busid);
            e.setResid(items.get(i).getResid());
            e.setType(ZcCommonService.ZC_BUS_TYPE_LY);
            e.setMark("资产领用");
            e.setFillct("0");
            e.setCdate(new Date());
            cols.add(e);
        }
        ResChangeItemServiceImpl.saveBatch(cols);
        //  fillChangeCt();
        return R.SUCCESS_OPER();
    }

    //插入领用/退库
    public R insertOrUpdate(ResCollectionreturn entity, String items) {
        String type = entity.getBustype();
        if (ZcCommonService.ZC_BUS_TYPE_LY.equals(type)) {
            return lyinsertOrUpdate(entity, items);
        } else if (ZcCommonService.ZC_BUS_TYPE_TK.equals(type)) {
            return tkinsertOrUpdate(entity, items);
        } else {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
    }

    //插入领用
    public R lyinsertOrUpdate(ResCollectionreturn entity, String items) {
        String id = entity.getId();
        String uuid = "";
        entity.setBustype(ZcCommonService.ZC_BUS_TYPE_LY);
        //获取UUID
        if (ToolUtil.isNotEmpty(id)) {
            //修改单据
            uuid = entity.getBusuuid();
            if (!SysProcessDataService.PSTATUS_APPLY.equals(entity.getStatus())) {
                return R.FAILURE("当前状态不允许修改");
            }
            //可能数据有变动，先解锁当前的数据,后面会重新加锁
            String sql2 = "update res_collectionreturn_item a,res b set b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql2, uuid);
        } else {
            //生产单据
            uuid = zcService.createUuid(ZcCommonService.UUID_LY);
            entity.setProcessuserid(this.getUserId());
            entity.setProcessusername(this.getUserName());

            //设置流程申请
            entity.setBusuuid(uuid);
            //等待申请
            entity.setStatus(SysProcessDataService.PSTATUS_APPLY);
        }


        JSONArray items_arr = JSONArray.parseArray(items);
        ArrayList<ResCollectionreturnItem> list = new ArrayList<ResCollectionreturnItem>();
        for (int i = 0; i < items_arr.size(); i++) {
            ResCollectionreturnItem e = new ResCollectionreturnItem();
            e.setResid(items_arr.getJSONObject(i).getString("id"));
            e.setBusdate(entity.getBusdate());
            e.setReturndate(entity.getReturndate());
            e.setBusuuid(uuid);
            e.setCruserid(entity.getCruserid());
            e.setCrusername(entity.getCrusername());
            e.setProcessuserid(entity.getProcessuserid());
            e.setProcessusername(entity.getProcessusername());
            e.setTusedcompanyid(entity.getTusedcompanyid());
            e.setTpartid(entity.getTpartid());
            e.setTuseduserid(entity.getTuseduserid());
            e.setTloc(entity.getTloc());
            e.setTlocdtl(entity.getTlocdtl());
            e.setIsreturn("0");
            list.add(e);
        }

        //删除item数据,重新保存
        QueryWrapper<ResCollectionreturnItem> qw = new QueryWrapper<ResCollectionreturnItem>();
        String finalUuid = uuid;
        qw.and(i -> i.eq("busuuid", finalUuid));
        ResCollectionreturnItemServiceImpl.remove(qw);
        ResCollectionreturnItemServiceImpl.saveOrUpdateBatch(list);
        ResCollectionreturnServiceImpl.saveOrUpdate(entity);
        //可能数据有变动，先解锁当前的数据,后面会重新加锁,
//        String sql2 = "update res_collectionreturn_item a,res b set b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
//        db.execute(sql2, uuid);

        String sql3 = " update res_collectionreturn_item a,res b set b.inprocess='1',b.inprocessuuid='" + uuid + "',b.inprocesstype='" + ZcCommonService.ZC_BUS_TYPE_LY + "' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql3, uuid);
//        //临时锁定
//        if (!ResCollectionreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
//        }
//        if (ResCollectionreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
        //    zcChangeService.zcLyConfirm(uuid);
//        }
        return R.SUCCESS_OPER();
    }

    //插入退库
    public R tkinsertOrUpdate(ResCollectionreturn entity, String items) {
        String id = entity.getId();
        String uuid = "";
        entity.setBustype(ZcCommonService.ZC_BUS_TYPE_TK);

        //获取UUID
        if (ToolUtil.isNotEmpty(id)) {
            uuid = entity.getBusuuid();
            //解锁之前的数据,
            String sql2 = "update res_collectionreturn_item a,res b set \n" +
                    "  b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql2, uuid);
        } else {
            uuid = zcService.createUuid(ZcCommonService.UUID_TK);
            //当前方案设置结束流程
            entity.setProcessuserid(this.getUserId());
            entity.setProcessusername(this.getUserName());
            entity.setBusuuid(uuid);
            entity.setStatus(ResCollectionreturnService.STATUS_SUCCESS);
        }
        JSONArray items_arr = JSONArray.parseArray(items);
        ArrayList<ResCollectionreturnItem> list = new ArrayList<ResCollectionreturnItem>();
        for (int i = 0; i < items_arr.size(); i++) {
            ResCollectionreturnItem e = new ResCollectionreturnItem();
            e.setResid(items_arr.getJSONObject(i).getString("id"));
            e.setBusdate(entity.getBusdate());
            e.setReturndate(entity.getReturndate());
            e.setRreturndate(entity.getRreturndate());
            e.setBusuuid(uuid);
            e.setCruserid(entity.getCruserid());
            e.setCrusername(entity.getCrusername());
            e.setProcessuserid(entity.getProcessuserid());
            e.setProcessusername(entity.getProcessusername());
            e.setTusedcompanyid(entity.getTusedcompanyid());
            e.setTpartid("");
            e.setTuseduserid("");
            e.setTloc(entity.getTloc());
            e.setTlocdtl(entity.getTlocdtl());
            e.setIsreturn("1");

            list.add(e);
        }

        //先保存item数据,清除历史
        QueryWrapper<ResCollectionreturnItem> qw = new QueryWrapper<ResCollectionreturnItem>();
        String finalUuid = uuid;
        qw.and(i -> i.eq("busuuid", finalUuid));
        ResCollectionreturnItemServiceImpl.remove(qw);
        ResCollectionreturnItemServiceImpl.saveOrUpdateBatch(list);
        //保存单据数据
        //锁定单据中的数据
        String sql2 = "update res_collectionreturn_item a,res b set \n" +
                "  b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
        db.execute(sql2, uuid);
        ResCollectionreturnServiceImpl.saveOrUpdate(entity);


        //临时锁定
        if (!ResCollectionreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
            String sql3 = " update res_collectionreturn_item a,res b set \n" +
                    "  b.inprocess='1',b.inprocessuuid='" + uuid + "',b.inprocesstype='" + ZcCommonService.ZC_BUS_TYPE_TK + "' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql3, uuid);
        }

        if (ResCollectionreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
            zcChangeService.zcTkConfirm(uuid);
        }
        return R.SUCCESS_OPER();

    }

    public R selectByUuid(String uuid) {
        return selectData(uuid, null);
    }


    public R selectData(String uuid, String resid) {
        JSONObject res = new JSONObject();
        String sql2 = "select " + ZcCommonService.resSqlbody + " t.*," +
                "(select name from sys_user_info where user_id=b.create_by) createusername,\n" +
                "(select route_name from hrm_org_part where node_id=b.tusedcompanyid) tcompfullname,\n" +
                "(select node_name from hrm_org_part where node_id=b.tusedcompanyid) tcompname,\n" +
                "(select route_name from hrm_org_part where node_id=b.tpartid) tpartfullame,\n" +
                "(select node_name from hrm_org_part where node_id=b.tpartid) tpartname,\n" +
                "(select name from sys_user_info where user_id=b.tuseduserid) tusedusername,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tloc) tlocstr,\n" +
                "(select route_name from hrm_org_part where node_id=b.fusedcompanyid) fcompfullname,\n" +
                "(select node_name from hrm_org_part where node_id=b.fusedcompanyid) fcompname,\n" +
                "(select route_name from hrm_org_part where node_id=b.fpartid) fpartfullame,\n" +
                "(select node_name from hrm_org_part where node_id=b.fpartid) fpartname,\n" +
                "(select name from sys_user_info where user_id=b.fuseduserid) fusedusername,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.floc) flocstr,\n" +
                "date_format(busdate,'%Y-%m-%d') busdatestr,\n" +
                "date_format(returndate,'%Y-%m-%d') returndatestr,\n" +
                "date_format(rreturndate,'%Y-%m-%d') rreturndatestr,\n" +
                "b.*\n" +
                "from res_collectionreturn_item b,res t where b.dr='0' and t.dr='0' " +
                "and t.id=b.resid\n" +
                "and b.busuuid=?";
        if (ToolUtil.isNotEmpty(resid)) {
            sql2 = sql2 + " and resid='" + resid + "'";
        }
        Rcd rsone = db.uniqueRecord("select * from res_collectionreturn where dr='0' and busuuid=?", uuid);
        res = ConvertUtil.OtherJSONObjectToFastJSONObject(rsone.toJsonObject());
        RcdSet rs = db.query(sql2, uuid);
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }

}
