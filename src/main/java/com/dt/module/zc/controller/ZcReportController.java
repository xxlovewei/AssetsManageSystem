package com.dt.module.zc.controller;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: algernonking
 * @date: Dec 2, 2019 2:31:20 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/zc/report")
public class ZcReportController extends BaseController {
    @Autowired
    ZcReportService zcReportService;


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryPartUsedByPart.do")
    @Transactional
    public R queryPartUsedByPart() {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String part_id=ps.getString("part_id");
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0'";
        if ("-1".equals(part_id)) {
            sql = sql + " and part_id not in (select node_id from hrm_org_part where org_id='1') or part_id is null";
        } else {
            sql = sql + " and part_id='" + part_id + "'";
        }
        sql = sql + " order by class_id";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryPartUsedReport.do")
    public R queryPartUsedReport(String catid) {
        return zcReportService.queryPartUsedReport(catid);
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryCatReport.do")
    public R queryUsedReport() {

        return zcReportService.queryCatReport();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryCatUsedReport.do")
    public R queryCatUsedeport() {

        return zcReportService.queryCatUsedReport();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryExpredReport.do")
    public R queryExpredReport() {

        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryCleaninglistReport.do")
    public R queryCleaninglistReport() {

        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryWbExpredReport.do")
    public R queryWbExpredReport(String day) {

        return zcReportService.queryWbExpredReport(day);
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryEmployeeUsedReport.do")
    public R queryEmployeeUsedReport() {
        return zcReportService.queryEmployeeUsedReport();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryEmployeeUsedByUser.do")
    public R queryEmployeeUsedByUser() {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String userid=ps.getString("userid");
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0'";
        if(ToolUtil.isNotEmpty(userid)){
            sql=sql+" and used_userid='"+userid+"'";
        }
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }
}