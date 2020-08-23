package com.dt.module.zc.controller;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.module.zc.entity.ResCollectionreturn;
import com.dt.module.zc.entity.ResLoanreturn;
import com.dt.module.zc.service.IResLoanreturnItemService;
import com.dt.module.zc.service.IResLoanreturnService;
import com.dt.module.zc.service.impl.ResLoanreturnService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc/resLoanreturn/ext")
public class ResLoanreturnExtController extends BaseController {

    @Autowired
    IResLoanreturnService ResLoanreturnServiceImpl;

    @Autowired
    IResLoanreturnItemService ResLoanreturnItemServiceImpl;


    @Autowired
    ResLoanreturnService resLoanreturnService;

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/zcjy.do")
    public R zcjy(ResLoanreturn entity, String items) {
        return resLoanreturnService.zcJy(entity, items);
    }

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/zcgh.do")
    public R zcgh(String id, String rreturndate, String rprocessuserid, String rprocessusername) {
        return resLoanreturnService.zcGh(id, rreturndate, rprocessuserid, rprocessusername);
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        String sql = "select\n" +
                "(select name from sys_user_info where user_id=b.create_by) createusername,\n" +
                "date_format(busdate,'%Y-%m-%d') busdatestr,\n" +
                "date_format(rreturndate,'%Y-%m-%d') rreturndatestr,\n" +
                "date_format(returndate,'%Y-%m-%d') returndatestr,\n" +
                "(select route_name from hrm_org_employee aa,hrm_org_part bb where aa.node_id=bb.node_id and empl_id=(select empl_id from sys_user_info where user_id=b.lruserid) limit 1 ) lruserorginfo," +
                "b.*" +
                "from res_loanreturn b where dr='0' order by create_time desc";
        RcdSet rs = db.query(sql);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {
        return resLoanreturnService.selectByUuid(uuid);
    }

}
