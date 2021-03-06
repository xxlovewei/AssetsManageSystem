package com.dt.module.zc.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import org.springframework.stereotype.Service;

@Service
public class ResCBasicinformationService extends BaseService {

    public static String STATUS_SUCCESS = "success";
    public static String STATUS_FAILED = "failed";
    public static String STATUS_CANCEL = "cancel";
//    public static String STATUS_APPLY = "apply";
//    public static String STATUS_APPROVAL = "approval";

    public R selectByUuid(String uuid) {
        return selectData(uuid, null);
    }

    public R selectData(String uuid, String resid) {

        String sql2 = "select " + ZcCommonService.resSqlbody + " t.* ,b.*,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tzcsource) tzcsourcestr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tsupplier) tsupplierstr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tusefullife) tusefullifestr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tloc) tlocstr,   " +
                "(select node_name from hrm_org_part where node_id=b.tpartid) tpartname," +
                "(select node_name from hrm_org_part where node_id=b.tusedcompanyid) tusedcompanyname," +
                "(select name from sys_user_info where user_id=b.tuseduserid) tusedusername," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tbrand) tbrandstr,   " +
                "(select route_name from ct_category where dr='0' and id=b.tclassid) tclassfullname,   " +
                "date_format(tbuytime,'%Y-%m-%d') tbuytimestr,   " +
                "date_format(fbuytime,'%Y-%m-%d') fbuytimestr,    " +
                "date_format(tfd1,'%Y-%m-%d') tfd1str,   " +
                "date_format(ffd1,'%Y-%m-%d') ffd1str,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fzcsource) fzcsourcestr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fsupplier) fsupplierstr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fusefullife) fusefullifestr,   " +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.floc) flocstr,   " +
                "(select node_name from hrm_org_part where node_id=b.fpartid) fpartname," +
                "(select node_name from hrm_org_part where node_id=b.fusedcompanyid) fusedcompanyname," +
                "(select name from sys_user_info where user_id=b.fuseduserid) fusedusername," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fbrand) fbrandstr,   " +
                "(select route_name from ct_category where dr='0' and id=b.fclassid) fclassfullname   " +
                "from res t,res_c_basicinformation_item b where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";

        if (ToolUtil.isNotEmpty(resid)) {
            sql2 = sql2 + " and resid='" + resid + "'";
        }
        RcdSet rs = db.query(sql2, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }
}
