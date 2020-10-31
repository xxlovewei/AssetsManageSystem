package com.dt.module.zc.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import org.springframework.stereotype.Service;

@Service
public class ResCMaintenanceService extends BaseService {
    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";
    public static String STATUS_CANCEL = "cancel";
    public static String STATUS_APPLY = "apply";
    public static String STATUS_APPROVAL = "approval";


    public R selectByUuid(String uuid) {
        return selectData(uuid, null);
    }

    public R selectData(String uuid, String resid) {
        String sql2 = "select " + ZcCommonService.resSqlbody + " t.*,b.*,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fwb)fwbstr," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.twb)twbstr," +
                "date_format(b.twboutdate,'%Y-%m-%d') twboutdatestr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.twbsupplier) twbsupplierstr,   " +
                "date_format(b.fwboutdate,'%Y-%m-%d') fwboutdatestr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fwbsupplier) fwbsupplierstr   " +
                "from res_c_maintenance_item b ,res t where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";
        if (ToolUtil.isNotEmpty(resid)) {
            sql2 = sql2 + " and resid='" + resid + "'";
        }
        RcdSet rs = db.query(sql2, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }
}
