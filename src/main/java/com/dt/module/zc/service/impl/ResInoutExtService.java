package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.zc.entity.ResInout;
import com.dt.module.zc.service.IResInoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-05-25
 */
@Service
public class ResInoutExtService extends BaseService {


    @Autowired
    IResInoutService ResInoutServiceImpl;


    public R selectSafetyStore() {
        String sql = "select\n" +
                "  b.*,\n" +
                "  t.*,\n" +
                "  case when zc_cnt < downcnt\n" +
                "    then '低于下限'\n" +
                "  when zc_cnt > upcnt\n" +
                "    then '超过上限'\n" +
                "  else '未知'\n" +
                "  end msg\n" +
                "from (\n" +
                "       select\n" +
                "         class_id,\n" +
                "         sum(zc_cnt) zc_cnt\n" +
                "       from res\n" +
                "       where dr = '0' and category = '" + ZcCommonService.CATEGORY_HC + "'\n" +
                "       group by class_id) t,\n" +
                "  ct_category b\n" +
                "where t.class_id = b.id and upcnt > 0 and downcnt > 0\n" +
                "      and (zc_cnt < downcnt or zc_cnt > upcnt)\n";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());

    }

    public R selectHcCk(String action) {

        String sql = "select\n" +
                "   (select node_name from hrm_org_part where node_id=t.compid) outbelongcompname,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) outlocstr,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) outwarehousestr,\n" +
                "   (select node_name from hrm_org_part where node_id=t.belongcompid) inbelongcompname,\n" +
                "   (select node_name from hrm_org_part where node_id=t.usedcompid) inusedcompname,\n" +
                "   (select node_name from hrm_org_part where node_id=t.usedpartid) inpartname,\n" +
                "   (select name from sys_user_info where user_id=t.useduserid) inusername,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) inlocstr,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) inwarehousestr,\n" +
                "   t.*\n" +
                "from res_inout t where dr='0' and action='" + action + "' order by create_time desc";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());

    }

    public R selectHcDbDataById(String id) {
        String sql = "select\n" +
                "   (select node_name from hrm_org_part where node_id=t.compid) outbelongcompname,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) outlocstr,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) outwarehousestr,\n" +
                "   (select node_name from hrm_org_part where node_id=t.usedcompid) inusedcompname,\n" +
                "   (select node_name from hrm_org_part where node_id=t.usedpartid) inpartname,\n" +
                "   (select name from sys_user_info where user_id=t.useduserid) inusername,\n" +
                "   (select node_name from hrm_org_part where node_id=t.belongcompid) inbelongcompname,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) inlocstr,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) inwarehousestr,\n" +
                "   t.*\n" +
                "from res_inout t where dr='0' and id=?";
        Rcd rcd = db.uniqueRecord(sql, id);
        JSONObject r = ConvertUtil.OtherJSONObjectToFastJSONObject(rcd.toJsonObject());
        String sql2 = "\n" +
                "select\n" +
                "  (select node_name from hrm_org_part where node_id=t.belong_company_id) belongcom_name,\n" +
                "  (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) locstr,\n" +
                "  (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) warehousestr,\n" +
                "  (select id from ct_category where dr='0' and id=t.class_id) ctid,\n" +
                "  (select model from ct_category where dr='0' and id=t.class_id) ctmodel,\n" +
                "  (select name from ct_category where dr='0' and id=t.class_id) classname,\n" +
                "  (select unit from ct_category where dr='0' and id=t.class_id) ctunit,\n" +
                "  (select mark from ct_category where dr='0' and id=t.class_id) ctmark,\n" +
                "  (select unitprice from ct_category where dr='0' and id=t.class_id) ctunitprice,\n" +
                "  (select upcnt from ct_category where dr='0' and id=t.class_id) ctupcnt,\n" +
                "  (select downcnt from ct_category where dr='0' and id=t.class_id) ctdowncnt,\n" +
                "  (select brandmark from ct_category where dr='0' and id=t.class_id) ctbrandmark,\n" +
                "  (select name from sys_dict_item where dr='0' and dict_item_id=t.supplier) supplierstr,\n" +
                "t.*\n" +
                "from res_inout_item t where dr='0' and uuid=?";
        r.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql2, rcd.getString("uuid")).toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(r);
    }


    public R selectHcOutDataById(String id) {
        String sql = "select\n" +
                "   (select node_name from hrm_org_part where node_id=t.compid) outbelongcompname,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) outlocstr,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) outwarehousestr,\n" +
                "   (select node_name from hrm_org_part where node_id=t.usedcompid) inusedcompname,\n" +
                "   (select node_name from hrm_org_part where node_id=t.usedpartid) inpartname,\n" +
                "   (select name from sys_user_info where user_id=t.useduserid) inusername,\n" +
                "   (select node_name from hrm_org_part where node_id=t.belongcompid) inbelongcompname,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) inlocstr,\n" +
                "   (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) inwarehousestr,\n" +
                "   t.*\n" +
                "from res_inout t where dr='0' and id=?";
        Rcd rcd = db.uniqueRecord(sql, id);
        JSONObject r = ConvertUtil.OtherJSONObjectToFastJSONObject(rcd.toJsonObject());
        String sql2 = "\n" +
                "select\n" +
                "  (select node_name from hrm_org_part where node_id=t.belong_company_id) belongcom_name,\n" +
                "  (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) locstr,\n" +
                "  (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) warehousestr,\n" +
                "  (select id from ct_category where dr='0' and id=t.class_id) ctid,\n" +
                "  (select model from ct_category where dr='0' and id=t.class_id) ctmodel,\n" +
                "  (select name from ct_category where dr='0' and id=t.class_id) classname,\n" +
                "  (select unit from ct_category where dr='0' and id=t.class_id) ctunit,\n" +
                "  (select mark from ct_category where dr='0' and id=t.class_id) ctmark,\n" +
                "  (select unitprice from ct_category where dr='0' and id=t.class_id) ctunitprice,\n" +
                "  (select upcnt from ct_category where dr='0' and id=t.class_id) ctupcnt,\n" +
                "  (select downcnt from ct_category where dr='0' and id=t.class_id) ctdowncnt,\n" +
                "  (select brandmark from ct_category where dr='0' and id=t.class_id) ctbrandmark,\n" +
                "  (select name from sys_dict_item where dr='0' and dict_item_id=t.supplier) supplierstr,\n" +
                "t.*\n" +
                "from res_inout_item t where dr='0' and uuid=?";
        r.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql2, rcd.getString("uuid")).toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(r);
    }

    public R selectHcInDataById(String id) {

        ResInout in = ResInoutServiceImpl.getById(id);
        String uuid = in.getUuid();
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(in, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select\n" +
                "\n" +
                "   (select node_name from hrm_org_part where node_id=t.belong_company_id) belongcom_name,\n" +
                "  (select name\n" +
                "   from sys_dict_item\n" +
                "   where dr = '0' and dict_item_id = t.warehouse) warehousestr,\n" +
                "  (select brandmark\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            brandmark,\n" +
                "  (select model\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            model,\n" +
                "  (select name\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            class_name,\n" +
                "  (select id\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctid,\n" +
                "  (select unit\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            unit,\n" +
                " (select name from sys_dict_item where dr='0' and dict_item_id=t.supplier) supplierstr," +
                "  (select mark\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            remark,\n" +
                "  (select unitprice\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            unitprice,\n" +
                "  (select upcnt\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            upcnt,\n" +
                "  date_format(buy_time,'%Y-%m-%d') buy_timestr ," +
                "  (select downcnt\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            downcnt,\n" +
                "  (select name\n" +
                "   from sys_dict_item\n" +
                "   where dr = '0' and dict_item_id = t.loc)       locstr,\n" +
                "  t.*\n" +
                "from res_inout_item t where dr='0' and uuid=?";
        res.put("items", db.query(sql, uuid).toJsonArrayWithJsonObject());
        return R.SUCCESS_OPER(res);
    }

    public R selectHcTj(String loc) {
        String sql = "select\n" +
                "  (select name\n" +
                "   from sys_dict_item\n" +
                "   where dr = '0' and dict_item_id = t.warehouse) warehousestr,\n" +
                "  (select model\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctmodel,\n" +

                "  (select name\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctname,\n" +

                "  (select id\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctid,\n" +
                "  (select unit\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctunit,\n" +
                "  (select mark\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctmark,\n" +
                "  (select unitprice\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctunitprice,\n" +
                "  (select upcnt\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctupcnt,\n" +
                "  (select downcnt\n" +
                "   from ct_category\n" +
                "   where dr = '0' and id = t.class_id)            ctdowncnt,\n" +
                "  (select name\n" +
                "   from sys_dict_item\n" +
                "   where dr = '0' and dict_item_id = t.loc)       locstr,\n" +
                "  t.*\n" +
                "from (\n" +
                "       select\n" +
                "         class_id,\n" +
                "         loc,\n" +
                "         warehouse,\n" +
                "         sum(zc_cnt) zc_cnt\n" +
                "       from res\n" +
                "       where dr = '0' and category = '" + ZcCommonService.CATEGORY_HC + "'\n" +
                "       group by class_id, loc, warehouse\n" +
                "       order by 1, 2, 3) t where 1=1 ";
        if (ToolUtil.isNotEmpty(loc)) {
            sql = sql + " and loc='" + loc + "'";
        }
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }
}
