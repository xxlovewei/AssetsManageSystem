package com.dt.module.zc.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import org.springframework.stereotype.Service;

@Service
public class ResCFinanceService extends BaseService {

    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";
    public static String STATUS_CANCEL = "cancel";
    public static String STATUS_APPLY = "apply";
    public static String STATUS_APPROVAL = "approval";

    public R selectByUuid(String uuid) {
        return selectData(uuid, null);
    }

    public R selectData(String uuid, String resid) {
        String sql2 = "select " + ZcCommonService.resSqlbody + " t.* ,b.*,   " +
                "(select route_name from hrm_org_part where node_id=b.fbelongcomp) fbelongcompfullname,   " +
                "(select node_name from hrm_org_part where node_id=b.fbelongcomp) fbelongcompname,   " +
                "(select route_name from hrm_org_part where node_id=b.tbelongcomp) tbelongcompfullname,   " +
                "(select node_name from hrm_org_part where node_id=b.tbelongcomp) tbelongcompname   " +
                "from res t, res_c_finance_item b where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";
        if (ToolUtil.isNotEmpty(resid)) {
            sql2 = sql2 + " and resid='" + resid + "'";
        }
        RcdSet rs = db.query(sql2, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }
}
