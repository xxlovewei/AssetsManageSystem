package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ZcReportService extends BaseService {

    //公司部门汇总
    public R queryPartUsedReport(String catid) {

        String sql = "select\n" +
                "  node_id        part_id,\n" +
                "  route_name     part_fullname,\n" +
                "  node_name      part_name,\n" +
                "  case when b.cnt is null\n" +
                "    then 0\n" +
                "  else b.cnt end zc_cnt\n" +
                "from (select *\n" +
                "      from hrm_org_part\n" +
                "      where org_id = '1' and dr = '0') a left join (select\n" +
                "                                                      part_id,\n" +
                "                                                      count(1) cnt\n" +
                "                                                    from <#RES#> r\n" +
                "                                                    where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "' \n" +
                "                                                    group by part_id) b on a.node_id = b.part_id\n" +
                "union all\n" +
                "select\n" +
                "  '-1'         part_id,\n" +
                "  '未设置组织或组织异常' part_fullname,\n" +
                "  '未设置组织或组织异常' part_name,\n" +
                "  count(1)     zc_cnt\n" +
                "from <#RES#> r\n" +
                "where dr='0' and category='" + ZcCommonService.CATEGORY_ZC + "' and part_id not in (select node_id\n" +
                "                      from hrm_org_part\n" +
                "                      where org_id = '1' and dr = '0') or part_id is null\n";

        String rssql = "";
        if (ToolUtil.isNotEmpty(catid)) {
            rssql = sql.replaceAll("<#RES#>", "(select a.*,b.id catid from res a,ct_category b where a.class_id=b.id and b.id='" + catid + "'  ) ");
        } else {
            rssql = sql.replaceAll("<#RES#>", "res");
        }
        return R.SUCCESS_OPER(db.query("select * from (" + rssql + ")tab order by part_fullname ").toJsonArrayWithJsonObject());
    }

    //分类使用
    public R queryCatReport() {
        String sql = "select\n" +
                "  (select route_name\n" +
                "   from ct_category\n" +
                "   where id = t.class_id) catname,\n" +
                "  (select b.name\n" +
                "   from ct_category a,ct_category_root b\n" +
                "   where a.id = t.class_id and a.root=b.id) catrootname,\n" +
                "    (select code\n" +
                "   from ct_category\n" +
                "   where id = t.class_id) catcode,\n" +
                "  t.*\n" +
                "from (\n" +
                "       select\n" +
                "         class_id,\n" +
                "         count(1) cnt\n" +
                "       from res\n" +
                "       where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "' \n" +
                "       group by class_id\n" +
                "     ) t order by 1,2";

        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    //资产分类
    public R queryCatUsedReport() {

        String sql = "select\n" +
                "  t.*,\n" +
                "  (select code\n" +
                "   from sys_dict_item\n" +
                "   where dict_item_id = t.recycle) code,\n" +
                "    (select route_name\n" +
                "   from ct_category i\n" +
                "   where i.id=t.class_id) catname,\n" +
                "     (select ii.name\n" +
                "   from ct_category i,ct_category_root ii\n" +
                "   where i.id=t.class_id and i.root=ii.id) catrootname\n" +
                "from (\n" +
                "       select\n" +
                "         class_id,\n" +
                "         recycle,\n" +
                "         count(1) cnt\n" +
                "       from res t \n" +
                "       where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "' \n" +
                "       group by class_id, recycle) t\n";
        System.out.println(sql);
        RcdSet rs = db.query(sql);

        HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
        for (int i = 0; i < rs.size(); i++) {
            String class_id = rs.getRcd(i).getString("class_id");
            JSONObject obj = null;
            if (map.containsKey(class_id)) {
                obj = map.get(class_id);
            } else {
                obj = new JSONObject();
                obj.put("catname", rs.getRcd(i).getString("catname"));
                obj.put("catrootname", rs.getRcd(i).getString("catrootname"));
                obj.put(ZcCommonService.RECYCLE_INUSE, "0");
                obj.put(ZcCommonService.RECYCLE_ALLOCATION, "0");
                obj.put(ZcCommonService.RECYCLE_BORROW, "0");
                obj.put(ZcCommonService.RECYCLE_IDLE, "0");
                obj.put(ZcCommonService.RECYCLE_REPAIR, "0");
                obj.put(ZcCommonService.RECYCLE_SCRAP, "0");
                obj.put(ZcCommonService.RECYCLE_STOPUSE, "0");
            }
            obj.put(rs.getRcd(i).getString("code"), rs.getRcd(i).getString("cnt"));
            map.put(class_id, obj);
        }
        JSONArray arr = new JSONArray();
        for (String key : map.keySet()) {
            arr.add(map.get(key));
        }
        return R.SUCCESS_OPER(arr);
    }

    //到期
    public R queryExpredReport(String catid) {

        return R.SUCCESS_OPER();
    }

    //清理清单
    public R queryCleaninglistReport(String catid) {

        return R.SUCCESS_OPER();
    }

    //维保到期
    public R queryWbExpredReport(String day) {

        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0' and category='" + ZcCommonService.CATEGORY_ZC + "' and wbout_date<= date_add(curdate(), INTERVAL " + day + " DAY)";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }


    //维保到期
    public R queryEmployeeUsedReport() {
        String sql = " select\n" +
                "                (select name from sys_user_info where user_id=t.used_userid) username,\n" +
                "                (select mail from sys_user_info where user_id=t.used_userid) usermail,\n" +
                "                (select tel from sys_user_info where user_id=t.used_userid) usertel,\n" +
                "                (select route_name  from hrm_org_employee a,hrm_org_part b,sys_user_info c where c.user_id=t.used_userid and a.node_id=b.node_id and c.empl_id=a.empl_id) part_fullname,\n" +
                "                t.*\n" +
                "                        from (\n" +
                "                                select\n" +
                "                                used_userid,\n" +
                "                                count(1) cnt\n" +
                "                                from res a\n" +
                "                                where dr = '0' and category='" + ZcCommonService.CATEGORY_ZC + "'\n" +
                "                                group by used_userid\n" +
                "                        ) t";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());

    }
}
