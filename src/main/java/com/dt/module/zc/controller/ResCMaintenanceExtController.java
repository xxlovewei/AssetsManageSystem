package com.dt.module.zc.controller;


import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.zc.entity.ResCMaintenance;
import com.dt.module.zc.entity.ResCMaintenanceItem;
import com.dt.module.zc.service.IResCMaintenanceItemService;
import com.dt.module.zc.service.IResCMaintenanceService;
import com.dt.module.zc.service.impl.ResCMaintenanceService;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/api/zc/resCMaintenance/ext")
public class ResCMaintenanceExtController extends BaseController {


    @Autowired
    IResCMaintenanceService ResCMaintenanceServiceImpl;

    @Autowired
    IResCMaintenanceItemService ResCMaintenanceItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    ZcChangeService zcChangeService;


    @ResponseBody
    @Acl(info = "插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insert.do")
    public R insert(ResCMaintenance entity, String items) throws ParseException {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String uuid = zcService.createUuid(ZcCommonService.ZC_BUS_TYPE_CGWB);
        entity.setStatus(ResCMaintenanceService.STATUS_SUCCESS);
        entity.setBusuuid(uuid);
        ArrayList<ResCMaintenanceItem> list = new ArrayList<ResCMaintenanceItem>();
        ArrayList<Res> reslist = new ArrayList<Res>();
        JSONArray items_arr = JSONArray.parseArray(items);
        for (int i = 0; i < items_arr.size(); i++) {
            ResCMaintenanceItem e = new ResCMaintenanceItem();
            e.setBusuuid(uuid);
            e.setResid(items_arr.getJSONObject(i).getString("id"));
            e.setTwb(entity.getTwb());
            e.setTwbauto(entity.getTwbauto());
            e.setTwbct(entity.getTwbct());
            e.setTwboutdate(entity.getTwboutdate());
            e.setTwbsupplier(entity.getTwbsupplier());

            e.setTwbstatus(entity.getTwbstatus());
            e.setTwbsupplierstatus(entity.getTwbsupplierstatus());
            e.setTwbautostatus(entity.getTwbautostatus());
            e.setTwbctstatus(entity.getTwbctstatus());
            e.setTwboutdatestatus(entity.getTwboutdatestatus());
            list.add(e);
        }
        ResCMaintenanceServiceImpl.save(entity);
        ResCMaintenanceItemServiceImpl.saveBatch(list);
        zcChangeService.zcCGWBSureChange(uuid);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        String sql = "select t.*,\n" +
                "(select name from sys_user_info where user_id=t.create_by) createusername," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=t.twb)twbstr," +
                "date_format(twboutdate,'%Y-%m-%d') twboutdatestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=t.twbsupplier) twbsupplierstr\n" +
                "from res_c_maintenance t where dr='0' order by create_time desc";
        RcdSet rs = db.query(sql);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {

        String sql = "select " + ZcCommonService.resSqlbody + " t.*,b.*,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fwb)fwbstr," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.twb)twbstr," +
                "date_format(b.twboutdate,'%Y-%m-%d') twboutdatestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.twbsupplier) twbsupplierstr,\n" +
                "date_format(b.fwboutdate,'%Y-%m-%d') fwboutdatestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fwbsupplier) fwbsupplierstr\n" +
                "from res_c_maintenance_item b ,res t where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";
        RcdSet rs = db.query(sql, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

}
