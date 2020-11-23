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
import com.dt.module.base.busenum.ZcCategoryEnum;
import com.dt.module.base.busenum.ZcRecycleEnum;
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
    public R dashboard(String search) {

        String sql = "select   " +
                "  (select count(1) from sys_process_data where dr='0' and bustype='LY' and pstatus='running') lywaitcnt,   " +
                "  (select count(1) from sys_process_data where dr='0' and bustype='JY' and pstatus='running') jywaitcnt,   " +
                "  (select count(1) from sys_process_data where dr='0' and bustype='DB' and pstatus='running') dbwaitcnt,   " +
                "  (select count(1) from res where dr='0' and wbout_date<curdate()) wboutcnt,   " +
                "  (select count(1) from res_repair where dr='0' and fstatus='wait') bxcnt,   " +
                "  (select count(1) from res where dr='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "') zccnt,   " +
                "  (select sum(net_worth) from res where dr='0' and recycle<>'scrap' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "') zcnetworth   ";

        JSONObject res = ConvertUtil.OtherJSONObjectToFastJSONObject(db.uniqueRecord(sql).toJsonObject());
        //资产状态
        String sql3 = "select   " +
                "  tab.*,   " +
                "  case when name2 is null   " +
                "    then '未知'   " +
                "  else name2 end name   " +
                "from (   " +
                "       select   " +
                "         t.*,   " +
                "         (select name   " +
                "          from sys_dict_item   " +
                "          where dict_item_id = t.recycle) name2   " +
                "       from (   " +
                "              select   " +
                "                recycle,   " +
                "                count(1) cnt   " +
                "              from res    " +
                "              where dr = '0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "'   " +
                "              group by recycle) t  order by 2 desc   " +
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
        String sql4 = "select   " +
                "  tab.*,   " +
                "  case when name2 is null   " +
                "    then '未设置'   " +
                "  else name2 end name   " +
                "from (   " +
                "       select   " +
                "         t.*,   " +
                "         (select node_name   " +
                "          from hrm_org_part   " +
                "          where node_id = t.part_id) name2   " +
                "       from (   " +
                "              select   " +
                "                part_id,   " +
                "                count(1) cnt   " +
                "              from res   " +
                "              where dr = '0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "'   " +
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
        String sql5 = "select   " +
                "  tab.*,   " +
                "  case when name2 is null   " +
                "    then '未设置'   " +
                "  else name2 end name   " +
                "from (   " +
                "       select   " +
                "         t.*,   " +
                "         (select  name   " +
                "          from ct_category   " +
                "          where id = t.class_id) name2   " +
                "       from (   " +
                "              select   " +
                "                class_id,   " +
                "                count(1) cnt   " +
                "              from res   " +
                "              where dr = '0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "'   " +
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
    @RequestMapping(value = "/queryUsedPartReport.do")
    public R queryUsedPartReport() {
        String sql = "\n" +
                "select\n" +
                "part_id,\n" +
                "case\n" +
                "when part_id is  null\n" +
                "then '未分配'\n" +
                "else part_fullname end part_fullname,\n" +
                "case\n" +
                "when part_id is  null\n" +
                "then '未分配'\n" +
                "else part_name end part_name,\n" +
                "zc_cnt\n" +
                "from (\n" +
                "select\n" +
                "(select route_name from hrm_org_part where node_id=t.part_id) part_fullname,\n" +
                "(select node_name from hrm_org_part where node_id=t.part_id) part_name,\n" +
                "t.*\n" +
                "from (select part_id,count(1) zc_cnt from res where dr='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' group by part_id) t\n" +
                "order by 1) end";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryUsedCompReport.do")
    public R queryUsedCompReport() {
        String sql = "select\n" +
                "case\n" +
                "when used_company_id is  null\n" +
                "then '未分配'\n" +
                "else comp_fullname end comp_fullname,\n" +
                "case\n" +
                "when used_company_id is  null\n" +
                "then '未分配'\n" +
                "else comp_name end comp_name,\n" +
                "zc_cnt\n" +
                "from (\n" +
                "select\n" +
                "(select route_name from hrm_org_part where node_id=t.used_company_id) comp_fullname,\n" +
                "(select node_name from hrm_org_part where node_id=t.used_company_id) comp_name,\n" +
                "t.*\n" +
                "from (select used_company_id,count(1) zc_cnt from res where dr='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' group by used_company_id) t\n" +
                "order by 1) end\n";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryBelongCompReport.do")
    @Transactional
    public R queryBelongCompReport() {
        String sql = "select\n" +
                "case\n" +
                "when belong_company_id is  null\n" +
                "then '未分配'\n" +
                "else belongcomp_fullname end belongcomp_fullname,\n" +
                "case\n" +
                "when belong_company_id is  null\n" +
                "then '未分配'\n" +
                "else belongcomp_name end belongcomp_name,\n" +
                "zc_cnt\n" +
                "from (\n" +
                "select\n" +
                "(select route_name from hrm_org_part where node_id=t.belong_company_id) belongcomp_fullname,\n" +
                "(select node_name from hrm_org_part where node_id=t.belong_company_id)  belongcomp_name,\n" +
                "t.*\n" +
                "from (select belong_company_id,count(1) zc_cnt from res where dr='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' group by belong_company_id) t\n" +
                "order by 1) end\n";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


    //资产总值汇总表,报废不计入
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryZcTotalAssets.do")
    public R queryZcTotalAssets() {
        String sql = "   " +
                "select   " +
                "  (select route_name from ct_category where dr='0' and id=t.class_id) classname,   " +
                "   t.*   " +
                "from (   " +
                "  select   " +
                "   " +
                "    class_id,   " +
                "    sum(zc_cnt)                           tcnt,   " +
                "    sum(buy_price * zc_cnt)               tbuyprice,   " +
                "    sum(net_worth * zc_cnt)               tnetworth,   " +
                "    sum(accumulateddepreciation * zc_cnt) taccumulateddepreciation   " +
                "  from res   " +
                "  where dr = '0' and recycle<>'scrap' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "'   " +
                "  group by class_id   " +
                ")t order by 1";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryAssetsCategory.do")
    public R queryAssetsCategory() {
        String sql = "\n" +
                "select\n" +
                "(select a.name from ct_category_root a,ct_category b where a.id=b.root and b.id=t.class_id) classrootname,\n" +
                "(select route_name from ct_category where  dr='0' and id=t.class_id) classfullname,\n" +
                "t.*\n" +
                "from (select class_id,count(1) zc_cnt from res where dr='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' group by class_id) t\n" +
                "order by 2";
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
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "'";
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
        String sql = "select " + ZcCommonService.resSqlbody + "b.busdate,b.crusername,b.processuserid,b.processusername,b.returndate,b.isreturn,t.* from res t ,res_collectionreturn_item b where t.recycle='" + ZcRecycleEnum.RECYCLE_INUSE.getValue() + "' and t.dr='0'   " +
                " and b.isreturn='0' and t.id=b.resid and b.dr='0' " +
                " and returndate<= date_add(curdate(), INTERVAL " + day + " DAY)" +
                " order by returndate ";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryGhZcExpire.do")
    public R queryGhZcExpire(String day) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String sql = "select " + ZcCommonService.resSqlbody + "b.busdate, b.lrusername,b.returndate,b.isreturn," +
                " (select route_name from hrm_org_employee aa,hrm_org_part bb where aa.node_id=bb.node_id and empl_id=(select empl_id from sys_user_info where user_id=b.lruserid) limit 1 ) lruserorginfo," +
                " t.* from res t ,res_loanreturn_item b where b.isreturn='0' and t.recycle='" + ZcRecycleEnum.RECYCLE_BORROW.getValue() + "' and t.dr='0'   " +
                " and t.id=b.resid and b.dr='0' " +
                " and b.returndate<= date_add(curdate(), INTERVAL " + day + " DAY)" +
                " order by b.returndate ";
        System.out.println(sql);
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryZcBfReport.do")
    public R queryZcBfReport() {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String sql = "select " + ZcCommonService.resSqlbody +
                " t.* from res t where t.dr='0' and t.recycle='" + ZcRecycleEnum.RECYCLE_SCRAP.getValue() + "'";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


}