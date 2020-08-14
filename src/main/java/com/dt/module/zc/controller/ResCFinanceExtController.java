package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.zc.entity.ResCFinance;
import com.dt.module.zc.entity.ResCFinanceItem;
import com.dt.module.zc.service.IResCFinanceItemService;
import com.dt.module.zc.service.IResCFinanceService;
import com.dt.module.zc.service.impl.ResCFinanceService;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
@RequestMapping("/api/zc/resCFinance/ext")
public class ResCFinanceExtController extends BaseController {

    @Autowired
    IResCFinanceService ResCFinanceServiceImpl;


    @Autowired
    IResCFinanceItemService ResCFinanceItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    ZcChangeService zcChangeService;

    @ResponseBody
    @Acl(info = "插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insert.do")
    public R insert(ResCFinance entity, String items) {
        String uuid = zcService.createUuid(ZcCommonService.UUID_CGCW);
        entity.setStatus(ResCFinanceService.STATUS_SUCCESS);
        entity.setBusuuid(uuid);
        ArrayList<ResCFinanceItem> list = new ArrayList<ResCFinanceItem>();
        ArrayList<Res> reslist = new ArrayList<Res>();
        JSONArray items_arr = JSONArray.parseArray(items);
        for (int i = 0; i < items_arr.size(); i++) {
            ResCFinanceItem e = new ResCFinanceItem();
            e.setBusuuid(uuid);
            e.setTbelongcomp(entity.getTbelongcomp());
            e.setTbelongpart(entity.getTbelongpart());
            e.setTbuyprice(entity.getTbuyprice());
            e.setTnetworth(entity.getTnetworth());
            e.setTresidualvalue(entity.getTresidualvalue());
            e.setTaccumulateddepreciation(entity.getTaccumulateddepreciation());

            e.setTbelongcompstatus(entity.getTbelongcompstatus());
            e.setTbelongpartstatus(entity.getTbelongpartstatus());
            e.setTbuypricestatus(entity.getTbuypricestatus());
            e.setTnetworthstatus(entity.getTnetworthstatus());
            e.setTresidualvaluestatus(entity.getTresidualvaluestatus());
            e.setTaccumulatedstatus(entity.getTaccumulatedstatus());

            e.setResid(items_arr.getJSONObject(i).getString("id"));
            list.add(e);
        }
        ResCFinanceServiceImpl.save(entity);
        ResCFinanceItemServiceImpl.saveBatch(list);
        zcChangeService.zcCGCWSureChange(uuid);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        String sql = "select\n" +
                " (select name from sys_user_info where user_id=b.create_by) createusername," +
                "(select route_name from hrm_org_part where node_id=b.tbelongcomp) tbelongcom_fullname,\n" +
                "(select node_name from hrm_org_part where node_id=b.tbelongcomp) tbelongcom_name,\n" +
                "b.*\n" +
                "from  res_c_finance b where dr='0' order by create_time desc";
        RcdSet rs = db.query(sql);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {

        String sql = "select " + ZcCommonService.resSqlbody + " t.* ,b.*,\n" +
                "(select route_name from hrm_org_part where node_id=b.fbelongcomp) fbelongcom_fullname,\n" +
                "(select node_name from hrm_org_part where node_id=b.fbelongcomp) fbelongcom_name,\n" +
                "(select route_name from hrm_org_part where node_id=b.tbelongcomp) tbelongcom_fullname,\n" +
                "(select node_name from hrm_org_part where node_id=b.tbelongcomp) tbelongcom_name\n" +
                "from res t, res_c_finance_item b where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";
        RcdSet rs = db.query(sql, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

}
