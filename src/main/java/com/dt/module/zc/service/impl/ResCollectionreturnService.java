package com.dt.module.zc.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.zc.entity.ResCBasicinformationItem;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.entity.ResCollectionreturnItem;
import com.dt.module.zc.service.IResCollectionreturnItemService;
import com.dt.module.zc.service.IResCollectionreturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResCollectionreturnService extends BaseService {


    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";
    public static String STATUS_CANCEL = "cancel";
    public static String STATUS_APPLY = "apply";
    public static String STATUS_APPROVAL = "approval";
    public static String TYPE_LY = "ly";
    public static String TYPE_TK = "tk";

    @Autowired
    IResCollectionreturnService ResCollectionreturnServiceImpl;

    @Autowired
    IResCollectionreturnItemService ResCollectionreturnItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    @Lazy
    ZcChangeService zcChangeService;

    public R insertOrUpdate(ResCollectionreturn entity, String items) {
        String type = entity.getBustype();
        if (TYPE_LY.equals(type)) {
            return this.lyinsertOrUpdate(entity, items);
        } else if (TYPE_TK.equals(type)) {
            return this.tkinsertOrUpdate(entity, items);
        } else {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
    }

    public R lyinsertOrUpdate(ResCollectionreturn entity, String items) {

        String id = entity.getId();
        String uuid = "";
        entity.setBustype(TYPE_LY);
        //获取UUID
        if (ToolUtil.isNotEmpty(id)) {
            uuid = entity.getBusuuid();
            //解锁之前的数据,
            String sql2 = "update res_collectionreturn_item a,res b set \n" +
                    "  b.inprocess='0',b.inprocessuuid='',b.inprocesstype='' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql2, uuid);
        } else {
            uuid = zcService.createUuid(ZcCommonService.UUID_LY);
            entity.setProcessuserid(this.getUserId());
            entity.setProcessusername(this.getUserName());
            //当前方案设置结束流程
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
                    "  b.inprocess='1',b.inprocessuuid='" + uuid + "',b.inprocesstype='" + ZcCommonService.ZC_BUS_TYPE_LY + "' where a.resid=b.id and a.busuuid=? and b.dr='0' and a.dr='0'";
            db.execute(sql3, uuid);
        }

        if (ResCollectionreturnService.STATUS_SUCCESS.equals(entity.getStatus())) {
            zcChangeService.zcLyConfirm(uuid);
        }
        return R.SUCCESS_OPER();
    }

    public R tkinsertOrUpdate(ResCollectionreturn entity, String items) {
        String id = entity.getId();
        String uuid = "";
        entity.setBustype(TYPE_TK);

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
        RcdSet rs = db.query(sql2, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

}
