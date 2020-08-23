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
    public static String STATUS_APPLY = "apply";
    public static String STATUS_APPROVAL = "approval";

    public R selectByUuid(String uuid) {
        return selectData(uuid, null);
    }

    public R selectData(String uuid, String resid) {

        String sql2 = "select " + ZcCommonService.resSqlbody + " t.* ,b.*,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tzcsource) tzcsourcestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tsupplier) tsupplierstr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tusefullife) tusefullifestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tloc) tlocstr,\n" +
                "(select node_name from hrm_org_part where node_id=b.tpartid) tpartname," +
                "(select node_name from hrm_org_part where node_id=b.tusedcompanyid) tusedcompanyname," +
                "(select name from sys_user_info where user_id=b.tuseduserid) tusedusername," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tbrand) tbrandstr,\n" +
                "(select route_name from ct_category where dr='0' and id=b.tclassid) tclassfullname,\n" +
                "date_format(tbuytime,'%Y-%m-%d') tbuytimestr ,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fzcsource) fzcsourcestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fsupplier) fsupplierstr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fusefullife) fusefullifestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.floc) flocstr,\n" +
                "(select node_name from hrm_org_part where node_id=b.fpartid) fpartname," +
                "(select node_name from hrm_org_part where node_id=b.fusedcompanyid) fusedcompanyname," +
                "(select name from sys_user_info where user_id=b.fuseduserid) fusedusername," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fbrand) fbrandstr,\n" +
                "(select route_name from ct_category where dr='0' and id=b.fclassid) fclassfullname,\n" +
                "date_format(fbuytime,'%Y-%m-%d') fbuytimestr \n" +
                "from res t,res_c_basicinformation_item b where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";

        if (ToolUtil.isNotEmpty(resid)) {
            sql2 = sql2 + " and resid='" + resid + "'";
        }
        RcdSet rs = db.query(sql2, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }
}
