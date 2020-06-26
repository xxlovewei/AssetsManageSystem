package com.dt.module.zc.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Oct 21, 2019 7:48:08 PM
 * @Description: TODO
 */
@Service
public class ZcCommonService extends BaseService {


    public static String RECYCLE_IDLE="idle";
    public static String RECYCLE_BORROW="borrow";
    public static String RECYCLE_ALLOCATION="allocation";
    public static String RECYCLE_REPAIR="repair";
    public static String RECYCLE_INUSE="inuse";
    public static String RECYCLE_STOPUSE="stopuse";
    public static String RECYCLE_SCRAP="scrap";


    public static String DATARANGE_REPAIR="repair";
    public static String DATARANGE_LY="LY";
    public static String DATARANGE_JY="JY";
    public static String DATARANGE_DB="DB";
    public static String DATARANGE_BF="BF";
    public static String DATARANGE_ALL="all";


    public static String UUID_ZC = "ZC";
    public static String UUID_LY = "LY";
    public static String UUID_JY = "JY";
    public static String UUID_ZY = "ZY";
    public static String UUID_BF = "BF";
    public static String UUID_BX = "BX";
    public static String UUID_DB = "DB";

    public static String UUID_HCRK = "HCRK";
    public static String UUID_HCCK = "HCCK";
    public static String UUID_HCDB = "HCDB";

    public static String UUID_BJRK = "BJRK";
    public static String UUID_BJCK = "BJCK";

    public static String ZC_BUS_TYPE_LY = "LY";
    public static String ZC_BUS_TYPE_JY = "JY";
    public static String ZC_BUS_TYPE_ZY = "ZY";
    public static String ZC_BUS_TYPE_DB = "DB";


    //维修状态:维修中、维修结束、报废
    public static String BX_STATUS_UNDERREPAIR = "underrepair";
    public static String BX_STATUS_FINSH = "finish";
    public static String BX_STATUS_CANCEL = "cancel";

    public static String CATEGORY_ZC = "3";
    public static String CATEGORY_HC = "7";
    public static String CATEGORY_BJ = "8";

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResActionItemService ResActionItemServiceImpl;


    public static String resHcSqlbody = " (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) locstr,"

            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.env) envstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.risk) riskstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.brand) brandstr,"
            + " (select name from sys_user_info where user_id=t.create_by) create_username,"
            + " (select name from sys_user_info where user_id=t.update_by) update_username,"
            + " (select name from sys_user_info where user_id=t.review_userid) review_username,"
            + " (select name from ct_category where dr='0' and id=t.class_id) classname,"
            + " (select a.name from ct_category_root a,ct_category b where a.id=b.root and b.id=t.class_id) classrootname,"
            + " (select route_name from ct_category where  dr='0' and id=t.class_id) classfullname,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.type) typename,"

            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.zcsource) zcsourcestr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) warehousestr,"
            + " (select model from ct_category where dr='0' and id=t.class_id) ctmodel,"
            + " (select id from ct_category where dr='0' and id=t.class_id) ctid,"
            + " (select unit from ct_category where dr='0' and id=t.class_id) ctunit,"
            + " (select mark from ct_category where dr='0' and id=t.class_id) ctmark,"
            + " (select unitprice from ct_category where dr='0' and id=t.class_id) ctunitprice,"
            + " (select upcnt from ct_category where dr='0' and id=t.class_id) ctupcnt,"
            + " (select downcnt from ct_category where dr='0' and id=t.class_id) ctdowncnt,"
            + " (select brandmark from ct_category where dr='0' and id=t.class_id) ctbrandmark,"

            + " (select node_name from hrm_org_part where node_id=t.used_company_id) comp_name,"
            + " (select route_name from hrm_org_part where node_id=t.used_company_id) comp_fullname,"
            + " (select node_name from hrm_org_part where node_id=t.part_id) part_name,"
            + " (select route_name from hrm_org_part where node_id=t.part_id) part_fullname,"
            + " (select node_name from hrm_org_part where node_id=t.belong_company_id) belongcom_name,"
            + " (select route_name from hrm_org_part where node_id=t.belong_company_id) belongcom_fullname,"
            + " (select route_name from hrm_org_part where node_id=t.mgr_part_id) mgr_part_fullname,"
            + " (select route_name from hrm_org_part where node_id=t.mgr_part_id) mgr_part_name,"
            + " (select name from sys_user_info where user_id=t.used_userid) used_username,";



    public static String resSqlbody = " (select name from sys_dict_item where dr='0' and dict_item_id=t.loc) locstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.recycle) recyclestr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.env) envstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.risk) riskstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.brand) brandstr,"
            + " (select name from sys_user_info where user_id=t.create_by) create_username,"
            + " (select name from sys_user_info where user_id=t.update_by) update_username,"
            + " (select name from sys_user_info where user_id=t.review_userid) review_username,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.wb) wbstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.rack) rackstr,"
            + " (select name from ct_category where dr='0' and id=t.class_id) classname,"
            + " (select a.name from ct_category_root a,ct_category b where a.id=b.root and b.id=t.class_id) classrootname,"
            + " (select route_name from ct_category where  dr='0' and id=t.class_id) classfullname,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.type) typename,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.wb_auto) wb_autostr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.wbsupplier) wbsupplierstr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.zcsource) zcsourcestr,"
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.supplier) supplierstr,"

            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.warehouse) warehousestr,"


            + " (select id from ct_category where dr='0' and id=t.class_id) ctid,"
            + " (select model from ct_category where dr='0' and id=t.class_id) ctmodel,"
            + " (select unit from ct_category where dr='0' and id=t.class_id) ctunit,"
            + " (select mark from ct_category where dr='0' and id=t.class_id) ctmark,"
            + " (select unitprice from ct_category where dr='0' and id=t.class_id) ctunitprice,"
            + " (select upcnt from ct_category where dr='0' and id=t.class_id) ctupcnt,"
            + " (select downcnt from ct_category where dr='0' and id=t.class_id) ctdowncnt,"
            + " (select brandmark from ct_category where dr='0' and id=t.class_id) ctbrandmark,"

            + " (select node_name from hrm_org_part where node_id=t.used_company_id) comp_name,"
            + " (select route_name from hrm_org_part where node_id=t.used_company_id) comp_fullname,"
            + " (select node_name from hrm_org_part where node_id=t.part_id) part_name,"
            + " (select route_name from hrm_org_part where node_id=t.part_id) part_fullname,"
            + " (select node_name from hrm_org_part where node_id=t.belong_company_id) belongcom_name,"
            + " (select route_name from hrm_org_part where node_id=t.belong_company_id) belongcom_fullname,"
            + " (select route_name from hrm_org_part where node_id=t.mgr_part_id) mgr_part_fullname,"
            + " (select route_name from hrm_org_part where node_id=t.mgr_part_id) mgr_part_name,"
            + " (select name from sys_user_info where user_id=t.used_userid) used_username,"

            + "  date_format(wbout_date,'%Y-%m-%d')  wbout_datestr,"
            +"   date_format(lastinventorytime,'%Y-%m-%d %H:%i') lastinventorytimestr,"
            + "  date_format(buy_time,'%Y-%m-%d') buy_timestr ,"
            + "  case when t.changestate = 'reviewed' then '已复核' when t.changestate = 'insert' then '待核(录入)' when t.changestate = 'updated'  then '待核(已更新)' else '未知' end reviewstr ,";




}
