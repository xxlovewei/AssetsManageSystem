package com.dt.module.zc.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: algernonking
 * @date: Oct 21, 2019 7:48:08 PM
 * @Description: TODO
 */
@Service
public class ZcCommonService extends BaseService {


    public static String RECYCLE_IDLE = "idle";
    public static String RECYCLE_BORROW = "borrow";
    public static String RECYCLE_ALLOCATION = "allocation";
    public static String RECYCLE_REPAIR = "repair";
    public static String RECYCLE_INUSE = "inuse";
    public static String RECYCLE_STOPUSE = "stopuse";
    public static String RECYCLE_SCRAP = "scrap";


    public static String DATARANGE_REPAIR = "repair";
    //领用
    public static String DATARANGE_LY = "LY";
    //退库
    public static String DATARANGE_TK = "Tk";
    //借用
    public static String DATARANGE_JY = "JY";
    //调拨
    public static String DATARANGE_DB = "DB";
    //报废
    public static String DATARANGE_BF = "BF";
    //折旧
    public static String DATARANGE_ZJ = "ZJ";
    //变更
    public static String DATARANGE_CG = "CG";
    //所有
    public static String DATARANGE_ALL = "all";

    public static String UUID_ZC = "ZC";
    public static String UUID_LY = "LY";
    public static String UUID_TK = "TK";
    public static String UUID_JY = "JY";
    public static String UUID_ZY = "ZY";
    public static String UUID_BF = "BF";
    public static String UUID_BX = "BX";
    public static String UUID_DB = "DB";
    public static String UUID_ZJ = "ZJ";
    //耗材入库
    public static String UUID_HCRK = "HCRK";
    //耗材出库
    public static String UUID_HCCK = "HCCK";
    //耗材调拨
    public static String UUID_HCDB = "HCDB";
    //备件入库
    public static String UUID_BJRK = "BJRK";
    //备件出库
    public static String UUID_BJCK = "BJCK";

    //财务变更
    public static String UUID_CGCW = "CGCW";
    //维保比昂
    public static String UUID_CGWB = "CGWB";
    //基本信息变更
    public static String UUID_CGJB = "CGJB";

    //领用
    public static String ZC_BUS_TYPE_LY = "LY";
    //退库
    public static String ZC_BUS_TYPE_TK = "TK";
    //借用
    public static String ZC_BUS_TYPE_JY = "JY";
    //转移
    public static String ZC_BUS_TYPE_ZY = "ZY";
    //调拨
    public static String ZC_BUS_TYPE_DB = "DB";
    //报废
    public static String ZC_BUS_TYPE_BF = "BF";
    //折旧
    public static String ZC_BUS_TYPE_ZJ = "ZJ";
    //财务变更
    public static String ZC_BUS_TYPE_CGCW = "CGCW";
    //维保变更
    public static String ZC_BUS_TYPE_CGWB = "CGWB";
    //基本变更
    public static String ZC_BUS_TYPE_CGJB = "CGJB";


    //维修状态:维修中、维修结束、报废
    public static String BX_STATUS_UNDERREPAIR = "underrepair";
    public static String BX_STATUS_FINSH = "finish";
    public static String BX_STATUS_CANCEL = "cancel";

    public static String CATEGORY_ZC = "3";
    public static String CATEGORY_HC = "7";
    public static String CATEGORY_BJ = "8";
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
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.usefullife) usefullifestr,"
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
            + " (select name from sys_user_info where user_id=t.used_userid) used_username,"
            + "  date_format(lastdepreciationdate,'%Y-%m-%d %H:%i') lastdepreciationdatestr,";
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
            + " (select name from sys_dict_item where dr='0' and dict_item_id=t.usefullife) usefullifestr,"
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
            + "  date_format(lastinventorytime,'%Y-%m-%d %H:%i') lastinventorytimestr,"
            + "  date_format(wbout_date,'%Y-%m-%d')  wbout_datestr,"
            + "  date_format(buy_time,'%Y-%m-%d') buy_timestr ,"
            + "  case when t.changestate = 'reviewed' then '已复核' when t.changestate = 'insert' then '待核(录入)' when t.changestate = 'updated'  then '待核(已更新)' else '未知' end reviewstr ,";
    @Autowired
    IResService ResServiceImpl;
    @Autowired
    IResActionItemService ResActionItemServiceImpl;


}
