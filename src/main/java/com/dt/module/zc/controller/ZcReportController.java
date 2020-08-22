package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
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
    @RequestMapping(value = "/dashboard.do")
    @Transactional
    public R dashboard(String search) {

        String sql = "select\n" +
                "  (select count(1) from sys_process_data where dr='0' and bustype='LY' and pstatus='running') lywaitcnt,\n" +
                "  (select count(1) from sys_process_data where dr='0' and bustype='JY' and pstatus='running') jywaitcnt,\n" +
                "  (select count(1) from sys_process_data where dr='0' and bustype='DB' and pstatus='running') dbwaitcnt,\n" +
                "  (select count(1) from res where dr='0' and wbout_date<curdate()) wboutcnt,\n" +
                "  (select count(1) from res_repair where dr='0' and fstatus='wait') bxcnt,\n" +
                "  (select count(1) from res where dr='0' and category='" + ZcCommonService.CATEGORY_ZC + "') zccnt,\n" +
                "  (select sum(net_worth) from res where dr='0' and recycle<>'scrap' and category='" + ZcCommonService.CATEGORY_ZC + "') zcnetworth\n";

        JSONObject res = ConvertUtil.OtherJSONObjectToFastJSONObject(db.uniqueRecord(sql).toJsonObject());
        //资产状态
        String sql3 = "select\n" +
                "  tab.*,\n" +
                "  case when name2 is null\n" +
                "    then '未知'\n" +
                "  else name2 end name\n" +
                "from (\n" +
                "       select\n" +
                "         t.*,\n" +
                "         (select name\n" +
                "          from sys_dict_item\n" +
                "          where dict_item_id = t.recycle) name2\n" +
                "       from (\n" +
                "              select\n" +
                "                recycle,\n" +
                "                count(1) cnt\n" +
                "              from res \n" +
                "              where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "'\n" +
                "              group by recycle) t  order by 2 desc\n" +
                "     ) tab";
        RcdSet s3 = db.query(sql3);
        JSONArray meta_arr = new JSONArray();
        JSONArray data_arr = new JSONArray();
        for (int i = 0; i < s3.size(); i++) {
            JSONArray meta = new JSONArray();
            meta.add(i);
            meta.add(s3.getRcd(i).getString("name"));
            meta_arr.add(meta);

            JSONArray data = new JSONArray();
            data.add(i);
            data.add(s3.getRcd(i).getInteger("cnt"));
            data_arr.add(data);

        }
        res.put("chart_meta", meta_arr);
        res.put("chart_data", data_arr);

        //部门
        String sql4 = "select\n" +
                "  tab.*,\n" +
                "  case when name2 is null\n" +
                "    then '未设置'\n" +
                "  else name2 end name\n" +
                "from (\n" +
                "       select\n" +
                "         t.*,\n" +
                "         (select node_name\n" +
                "          from hrm_org_part\n" +
                "          where node_id = t.part_id) name2\n" +
                "       from (\n" +
                "              select\n" +
                "                part_id,\n" +
                "                count(1) cnt\n" +
                "              from res\n" +
                "              where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "'\n" +
                "              group by part_id) t) tab order by 2 desc";
        RcdSet s4 = db.query(sql4);
        JSONArray partmeta_arr = new JSONArray();
        JSONArray partdata_arr = new JSONArray();
        for (int i = 0; i < s4.size(); i++) {
            JSONArray meta = new JSONArray();
            meta.add(i);
            meta.add(s4.getRcd(i).getString("name"));
            partmeta_arr.add(meta);

            JSONArray data = new JSONArray();
            data.add(i);
            data.add(s4.getRcd(i).getInteger("cnt"));
            partdata_arr.add(data);

        }
        res.put("part_chart_meta", partmeta_arr);
        res.put("part_chart_data", partdata_arr);


        //资产分类
        String sql5 = "select\n" +
                "  tab.*,\n" +
                "  case when name2 is null\n" +
                "    then '未设置'\n" +
                "  else name2 end name\n" +
                "from (\n" +
                "       select\n" +
                "         t.*,\n" +
                "         (select  name\n" +
                "          from ct_category\n" +
                "          where id = t.class_id) name2\n" +
                "       from (\n" +
                "              select\n" +
                "                class_id,\n" +
                "                count(1) cnt\n" +
                "              from res\n" +
                "              where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "'\n" +
                "              group by class_id) t) tab order by 2 desc";
        RcdSet s5 = db.query(sql5);
        JSONArray catmeta_arr = new JSONArray();
        JSONArray catdata_arr = new JSONArray();
        for (int i = 0; i < s5.size(); i++) {
            JSONArray meta = new JSONArray();
            meta.add(i);
            meta.add(s5.getRcd(i).getString("name"));
            catmeta_arr.add(meta);

            JSONArray data = new JSONArray();
            data.add(i);
            data.add(s5.getRcd(i).getInteger("cnt"));
            catdata_arr.add(data);

        }
        res.put("cat_chart_meta", catmeta_arr);
        res.put("cat_chart_data", catdata_arr);

        return R.SUCCESS_OPER(res);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryPartUsedByPart.do")
    @Transactional
    public R queryPartUsedByPart() {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String part_id = ps.getString("part_id");
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0' and category='" + ZcCommonService.CATEGORY_ZC + "'";
        if ("-1".equals(part_id)) {
            sql = sql + " and part_id not in (select node_id from hrm_org_part where org_id='1') or part_id is null";
        } else {
            sql = sql + " and part_id='" + part_id + "'";
        }
        sql = sql + " order by class_id";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    //资产总值汇总表,报废不计入
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryZcTotalAssets.do")
    @Transactional
    public R queryZcTotalAssets() {
        String sql = "\n" +
                "select\n" +
                "  (select route_name from ct_category where dr='0' and id=t.class_id) classname,\n" +
                "   t.*\n" +
                "from (\n" +
                "  select\n" +
                "\n" +
                "    class_id,\n" +
                "    sum(zc_cnt)                           tcnt,\n" +
                "    sum(buy_price * zc_cnt)               tbuyprice,\n" +
                "    sum(net_worth * zc_cnt)               tnetworth,\n" +
                "    sum(accumulateddepreciation * zc_cnt) taccumulateddepreciation\n" +
                "  from res\n" +
                "  where dr = '0' and recycle<>'scrap' and category='" + ZcCommonService.CATEGORY_ZC + "'\n" +
                "  group by class_id\n" +
                ")t order by 1";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


    //公司部门汇总表
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
        String userid = ps.getString("userid");
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0' and category='" + ZcCommonService.CATEGORY_ZC + "'";
        if (ToolUtil.isNotEmpty(userid)) {
            sql = sql + " and used_userid='" + userid + "'";
        } else {
            sql = sql + " and used_userid is null";
        }
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryTkZcExpire.do")
    public R queryTkZcExpire(String day) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String sql = "select " + ZcCommonService.resSqlbody + " b.crusername,b.processuserid,b.processusername,b.returndate,b.isreturn,t.* from res t ,res_collectionreturn_item b where t.recycle='" + ZcCommonService.RECYCLE_INUSE + "' and t.dr='0'\n" +
                "and t.id=b.resid and b.dr='0' " +
                " and returndate<= date_add(curdate(), INTERVAL " + day + " DAY)" +
                " order by returndate ";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryGhZcExpire.do")
    public R queryGhZcExpire(String day) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String sql = "select " + ZcCommonService.resSqlbody + " b.lrusername,b.returndate,b.isreturn,t.* from res t ,res_loanreturn_item b where t.recycle='" + ZcCommonService.RECYCLE_BORROW + "' and t.dr='0'\n" +
                "and t.id=b.resid and b.dr='0' " +
                " and b.returndate<= date_add(curdate(), INTERVAL " + day + " DAY)" +
                " order by b.returndate ";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


}