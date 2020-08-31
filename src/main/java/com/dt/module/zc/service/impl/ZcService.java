package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.busenum.ZcCategoryEnum;
import com.dt.module.base.busenum.ZcRecycleEnum;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.ct.entity.CtCategory;
import com.dt.module.ct.service.ICtCategoryService;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.service.IResAllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.tools.Tool;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Service
public class ZcService extends BaseService {

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResActionItemService ResActionItemServiceImpl;

    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    @Autowired
    ICtCategoryService CtCategoryServiceImpl;

    @Autowired
    ZcChangeService zcChangeService;


    //@Cacheable(value = CacheConfig.CACHE_PUBLIC_80_10,key="'qf'+#uid")
    public R queryDictFast(String uid, String zchccat, String comppart, String comp, String belongcomp, String dicts, String parts, String partusers, String subclass, String classroot, String zccatused) {

        JSONObject res = new JSONObject();
        String[] dict_arr = dicts.split(",");
        for (int i = 0; i < dict_arr.length; i++) {
            String sql = "select * from sys_dict_item where dict_id=? and dr='0' order by sort";
            String cls = dict_arr[i];
            if ("zcother".equals(dict_arr[i])) {
                sql = "select * from sys_dict_item where dict_id=? and dr='0' and code<>'menu' order by sort";
                cls = "devclass";
            }
            RcdSet rs = db.query(sql, cls);
            res.put(dict_arr[i], ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));
        }

        if (ToolUtil.isNotEmpty(subclass)) {
            RcdSet partrs = db.query(
                    "select id dict_item_id,name from ct_category where dr='0' and parent_id=? and type='goods' order by od", subclass);
            res.put("btype", ConvertUtil.OtherJSONObjectToFastJSONArray(partrs.toJsonArrayWithJsonObject()));
        }

        if (ToolUtil.isNotEmpty(classroot)) {
            String subsql = " t.type='goods' and isaction='Y' and t.dr='0' and t.root=? and t.node_level>1 ";
            RcdSet partrs = db.query("select id dict_item_id,route_name name,name sname from ct_category t where  "
                    + subsql + " order by route", classroot);
            res.put("btype", ConvertUtil.OtherJSONObjectToFastJSONArray(partrs.toJsonArrayWithJsonObject()));
        }


        // 所有用户
        if (ToolUtil.isNotEmpty(partusers) && "Y".equals(partusers)) {
            RcdSet partuserrs = db
                    .query("select a.user_id,a.name from sys_user_info a,hrm_org_employee b ,hrm_org_part c where\n"
                            + "  a.empl_id=b.empl_id and a.dr='0' and b.dr='0' and c.node_id=b.node_id");
            res.put("partusers", ConvertUtil.OtherJSONObjectToFastJSONArray(partuserrs.toJsonArrayWithJsonObject()));
        }

        RcdSet comprs = db.query("select node_id id, route_name name from hrm_org_part where dr='0' and type='comp' order by node_id");


        if (ToolUtil.isNotEmpty(comp) && "Y".equals(comp)) {
            res.put("comp", ConvertUtil.OtherJSONObjectToFastJSONArray(comprs.toJsonArrayWithJsonObject()));
        }

        if (ToolUtil.isNotEmpty(belongcomp) && "Y".equals(belongcomp)) {
            res.put("belongcomp", ConvertUtil.OtherJSONObjectToFastJSONArray(comprs.toJsonArrayWithJsonObject()));
        }

        //所有部门
        if (ToolUtil.isNotEmpty(comppart) && "Y".equals(comppart)) {
            JSONObject tmp = new JSONObject();
            for (int i = 0; i < comprs.size(); i++) {
                RcdSet partrs = db
                        .query("select node_id partid,route_name name from hrm_org_part where org_id=1 and dr='0' and parent_id=? order by route", comprs.getRcd(i).getString("id"));
                tmp.put(comprs.getRcd(i).getString("id"), ConvertUtil.OtherJSONObjectToFastJSONArray(partrs.toJsonArrayWithJsonObject()));
            }
            res.put("comppart", tmp);
        }


        if (ToolUtil.isNotEmpty(parts) && "Y".equals(parts)) {
            RcdSet partrs = db
                    .query("select node_id partid,route_name name from hrm_org_part where org_id=1 and dr='0' and type='part' order by route");
            res.put("parts", ConvertUtil.OtherJSONObjectToFastJSONArray(partrs.toJsonArrayWithJsonObject()));
        }

        if (ToolUtil.isNotEmpty(zccatused) && "Y".equals(zccatused)) {
            RcdSet partrs = db
                    .query("select a.id,concat(b.name,'/',a.route_name) name from ct_category a,ct_category_root b where a.type='goods' and a.root=b.id and a.dr='0' and a.id in (select distinct class_id from res where dr='0')\n" +
                            "order by a.root ,a.route_name");
            res.put("zccatused", ConvertUtil.OtherJSONObjectToFastJSONArray(partrs.toJsonArrayWithJsonObject()));
        }

        if (ToolUtil.isNotEmpty(zchccat) && "Y".equals(zchccat)) {
            RcdSet partrs = db
                    .query("select * from ct_category where root='" + ZcCategoryEnum.CATEGORY_HC.getValue() + "' and dr='0' and type='goods'");
            res.put("zchccat", ConvertUtil.OtherJSONObjectToFastJSONArray(partrs.toJsonArrayWithJsonObject()));
        }


        return R.SUCCESS_OPER(res);
    }


    private String createUuid5() {
        return UUID.randomUUID().toString().substring(9, 23).toUpperCase();
    }

    public String createUuid(String type) {
        int cnt = 5;
        String id = createUuid5();
        int i = 0;
        if (type.equals(ZcCommonService.UUID_ZC)) {
            for (i = 0; i < cnt; i++) {
                QueryWrapper<Res> ew = new QueryWrapper<Res>();
                String finalId = type + id;
                ew.and(j -> j.eq("uuid", finalId));
                Res rs = ResServiceImpl.getOne(ew);
                if (rs == null) {
                    break;
                } else {
                    id = createUuid5();
                }
            }
            if (i > cnt - 1) {
                return "";
            } else {
                return type + id;
            }
        } else if (type.equals(ZcCommonService.UUID_BX)) {
            for (i = 0; i < cnt; i++) {
                QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
                String finalId = type + id;
                ew.and(j -> j.eq("busuuid", finalId));
                ResActionItem rs = ResActionItemServiceImpl.getOne(ew);
                if (rs == null) {
                    break;
                } else {
                    id = createUuid5();
                }
            }
            if (i > cnt - 1) {
                return "";
            } else {
                return type + id;
            }
        } else if (type.equals(ZcCommonService.UUID_DB)) {
            for (i = 0; i < cnt; i++) {
                QueryWrapper<ResAllocate> ew = new QueryWrapper<ResAllocate>();
                String finalId = type + id;
                ew.and(j -> j.eq("uuid", finalId));
                ResAllocate rs = ResAllocateServiceImpl.getOne(ew);
                if (rs == null) {
                    break;
                } else {
                    id = createUuid5();
                }
            }
            if (i > cnt - 1) {
                return "";
            } else {
                return type + id;
            }
        } else if (type.equals(ZcCommonService.UUID_CGCW)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_CGWB)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_CGJB)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_HCRK)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_HCDB)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_HCCK)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_BF)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_ZJ)) {
            id = createUuid5();
            return type + id;
        } else if (type.equals(ZcCommonService.UUID_LY) || type.equals(ZcCommonService.UUID_TK) || type.equals(ZcCommonService.UUID_JY)
                || type.equals(ZcCommonService.UUID_ZY)) {
            for (i = 0; i < cnt; i++) {
                QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
                String finalId = type + id;
                ew.and(j -> j.eq("busuuid", finalId));
                ResActionItem rs = ResActionItemServiceImpl.getOne(ew);
                if (rs == null) {
                    break;
                } else {
                    id = createUuid5();
                }
            }
            if (i > cnt - 1) {
                return "";
            } else {
                return type + id;
            }
        }

        return "";

    }

    private String buildZcDataRange(String datarange) {
        String sql = "";
        if (ZcCommonService.DATARANGE_REPAIR.equals(datarange)) {
            //维修:闲置,在用
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle in ('" + ZcRecycleEnum.RECYCLE_IDLE.getValue() + "','" + ZcRecycleEnum.RECYCLE_INUSE.getValue() + "')";
        } else if (ZcCommonService.DATARANGE_LY.equals(datarange)) {
            //领用:闲置
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle in ('" + ZcRecycleEnum.RECYCLE_IDLE.getValue() + "')";
        } else if (ZcCommonService.DATARANGE_TK.equals(datarange)) {
            //退库:在用
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle in ('" + ZcRecycleEnum.RECYCLE_INUSE.getValue() + "')";
        } else if (ZcCommonService.DATARANGE_JY.equals(datarange)) {
            //借用:闲置,在用
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle in ('" + ZcRecycleEnum.RECYCLE_IDLE.getValue() + "','" + ZcRecycleEnum.RECYCLE_INUSE.getValue() + "')";
        } else if (ZcCommonService.DATARANGE_DB.equals(datarange)) {
            //调拨:闲置
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle in ('" + ZcRecycleEnum.RECYCLE_IDLE.getValue() + "')";
        } else if (ZcCommonService.DATARANGE_BF.equals(datarange)) {
            //报废:闲置,在用
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle in ('" + ZcRecycleEnum.RECYCLE_IDLE.getValue() + "','" + ZcRecycleEnum.RECYCLE_INUSE.getValue() + "')";
        } else if (ZcCommonService.DATARANGE_ZJ.equals(datarange)) {
            //折旧:不选报废
            sql = sql + " and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle<>'" + ZcRecycleEnum.RECYCLE_SCRAP.getValue() + "'";
        } else if (ZcCommonService.DATARANGE_CG.equals(datarange)) {
            //变更:不选报废
            sql = sql + " and inprocess='0' and category='" + ZcCategoryEnum.CATEGORY_ZC.getValue() + "' and recycle<>'" + ZcRecycleEnum.RECYCLE_SCRAP.getValue() + "'";
        } else if (ZcCommonService.DATARANGE_ALL.equals(datarange)) {
        }
        return sql;
    }

    public String buildQueryResAllGetdatalSql(String belongcomp, String comp, String part, String datarange, String classroot, String class_id, String wb, String env, String recycle, String loc, String search, TypedHashMap<String, Object> ps) {

        // 获取属性数据
        String isscrap = ps.getString("isscrap");
        String attrsql = "select * from res_attrs where catid=? and dr='0'";
        RcdSet attrs_rs = db.query(attrsql, class_id);
        String ids = ps.getString("ids");

        String sql = "select";
        //扩展属性
        if (attrs_rs != null) {
            for (int i = 0; i < attrs_rs.size(); i++) {
                // 拼接sql
                String valsql = "";
                if (attrs_rs.getRcd(i).getString("inputtype").equals("inputint")) {
                    // valsql = " cast( attrvalue as signed integer)";
                    valsql = " attrvalue+0";
                } else if (attrs_rs.getRcd(i).getString("inputtype").equals("inputstr")) {
                    valsql = "attrvalue";
                } else {
                    valsql = "attrvalue";
                }
                sql = sql + " (select " + valsql
                        + " from res_attr_value i where i.dr=0 and i.resid=t.id and i.attrid='"
                        + attrs_rs.getRcd(i).getString("id") + "') \"" + attrs_rs.getRcd(i).getString("attrcode")
                        + "\",  ";
            }
        }
        sql = sql + ZcCommonService.resSqlbody + " t.* from res t where dr=0 ";

        if (ToolUtil.isNotEmpty(classroot)) {
            //获取多个类型
            sql = sql + " and class_id in (select id from ct_category t where t.dr='0' and t.root='" + classroot + "' and t.node_level>1)";
        }
        //获取分类以下全部数据
        String class_id_parents = ps.getString("class_id_parents");
        if (ToolUtil.isNotEmpty(class_id_parents) && !"all".equals(class_id_parents)) {
            sql = sql + " and class_id in (select id from ct_category where 1=1 and (id='" + class_id_parents + "' or route like '%" + class_id_parents + "-%'))";
        }
        //类别
        if (ToolUtil.isNotEmpty(class_id) && !"all".equals(class_id)) {
            sql = sql + " and class_id in (select id from ct_category  where dr='0' and (id='" + class_id
                    + "' or parent_id='" + class_id + "')) ";
        }
        //区域
        if (ToolUtil.isNotEmpty(loc) && !"all".equals(loc)) {
            sql = sql + " and loc='" + loc + "'";
        }
        //环境
        if (ToolUtil.isNotEmpty(env) && !"all".equals(env)) {
            sql = sql + " and env='" + env + "'";
        }
        //维保
        if (ToolUtil.isNotEmpty(wb) && !"all".equals(wb)) {
            sql = sql + " and wb='" + wb + "'";
        }
        //状态
        if (ToolUtil.isNotEmpty(recycle) && !"all".equals(recycle)) {
            sql = sql + " and recycle='" + recycle + "'";
        }
        //使用公司
        if (ToolUtil.isNotEmpty(comp)) {
            sql = sql + " and used_company_id='" + comp + "'";
        }
        //所属公司
        if (ToolUtil.isNotEmpty(belongcomp)) {
            sql = sql + " and belong_company_id='" + belongcomp + "'";
        }
        //使用部门
        if (ToolUtil.isNotEmpty(part)) {
            sql = sql + " and part_id='" + part + "'";
        }
        //使用部门组织以下全部数据
        String part_parents = ps.getString("part_parents");
        if (ToolUtil.isNotEmpty(part_parents)) {
            sql = sql + " and part_id in (select node_id from hrm_org_part where (node_id='" + part_parents + "' or route like '%" + part_parents + "-%'))";
        }
        //仓库
        if (ToolUtil.isNotEmpty(ps.getString("warehouse"))) {
            sql = sql + " and warehouse='" + ps.getString("warehouse") + "'";
        }
        //资产数
        if (ToolUtil.isNotEmpty(ps.getString("zcnumber"))) {
            sql = sql + " and zc_cnt>" + ps.getString("zcnumber");
        }
        //类目
        if (ToolUtil.isNotEmpty(ps.getString("category"))) {
            sql = sql + " and category='" + ps.getString("category") + "'";
        }
        //机架
        if (ToolUtil.isNotEmpty(ps.getString("rack"))) {
            sql = sql + " and rack='" + ps.getString("rack") + "'";
        }
        //默认不显示报废数据,报废数据则,isscrap=1
        if (ToolUtil.isNotEmpty(isscrap) && "1".equals(isscrap)) {
            sql = sql + " and isscrap='1'";
        } else {
            sql = sql + " and isscrap='0'";
        }

        //ids
        JSONArray ids_arr = JSONArray.parseArray(ids);
        if (ToolUtil.isNotEmpty(ids_arr) && ids_arr.size() > 0) {
            String idsstr = " and t.id in (";
            for (int i = 0; i < ids_arr.size(); i++) {
                idsstr = idsstr + "'" + ids_arr.getString(i) + "',";
            }
            idsstr = idsstr + "',-1')";
            sql = sql + idsstr;
        }

        //idle,inuse,scrap,borrow,repair,stopuse,allocation
        if (ToolUtil.isNotEmpty(datarange)) {
            sql = sql + buildZcDataRange(datarange);
        }
        String ressql = "";
        if (ToolUtil.isNotEmpty(search)) {
            ressql = "select * from (" + sql + ") end where (rack like '%" + search + "%' or fs1 like '%" + search + "%' or mark like '%" + search
                    + "%' or uuid like '%" + search + "%' or model like '%" + search + "%'  or  sn like '%" + search
                    + "%' or classrootname like '%" + search + "%' or locstr like '%" + search + "%' or  supplierstr like '%" + search + "%' or part_fullname like '%" + search + "%')";
        } else {
            ressql = sql;
        }
        ressql = ressql + " order by update_time desc,loc,rack,frame";
        return ressql;
    }

    // 根据ClassId获取数据,优先判断multiclassroot,在获取class_id
    public R queryResAllGetData(String belongcomp, String comp, String part, String datarange, String classroot, String class_id, String wb, String env, String recycle, String loc, String search, TypedHashMap<String, Object> ps) {
        String sql = this.buildQueryResAllGetdatalSql(belongcomp, comp, part, datarange, classroot, class_id, wb, env, recycle, loc, search, ps);
        RcdSet rs2 = db.query(sql);
        return R.SUCCESS_OPER(rs2.toJsonArrayWithJsonObject());
    }


    public void checkWbMethod() {
        // 转脱保
        String sql1 = "update  res set wb='invalid' where id in (\n" + "    select t.id from (\n" + "      select id\n"
                + "      from res\n"
                + "      where wbout_date is not null and dr = 0 and    (wb <> 'invalid' or wb is null)   and wb_auto = '1'\n"
                + "            and wbout_date < now()\n" + "    ) t\n" + ")";
        db.execute(sql1);
        // 转在保
        String sql2 = "update  res set wb='valid' where id in (\n" + "    select t.id from (\n" + "  select id\n"
                + "  from res\n"
                + "  where wbout_date is not null and dr = 0 and (wb <> 'valid' or wb is null)  and wb_auto = '1'\n"
                + "        and wbout_date > now())t\n" + "\n" + ")";
        db.execute(sql2);

    }

    public String computeWb(String cur_wb, String wb_auto, String wbout_date_f) {
        Date date = new Date(); // 获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
        String wbcompute = cur_wb;
        if (ToolUtil.isNotEmpty(wb_auto) && "1".equals(wb_auto) && ToolUtil.isNotEmpty(wbout_date_f)) {
            try {
                Date wboutdate = simpleDateFormat.parse(wbout_date_f + " 01:00:00");
                if (date.getTime() > wboutdate.getTime()) {
                    // 脱保
                    wbcompute = "invalid";
                } else {
                    // 未脱保
                    wbcompute = "valid";
                }
            } catch (ParseException px) {
                px.printStackTrace();
            }
        }
        return wbcompute;
    }


    public R queryResAllByUUID(String uuid) {
        Rcd rs = db.uniqueRecord("select * from res t where dr=0 and uuid=?", uuid);
        if (rs == null) {
            return R.FAILURE_NO_DATA();
        }
        return queryResAllById(rs.getString("id"));
    }

    public void queryZcAttrWithValue2(String catid, String resid) {

    }

    public RcdSet queryZcAttrWithValue(String catid, String resid) {
        CtCategory ct = CtCategoryServiceImpl.getById(catid);
        String route = ct.getRoute();
        String attrsql = "select\n" +
                "  a.*,\n" +
                "  b.attrvalue\n" +
                "from (\n" +
                "       select t.*\n" +
                "       from res_attrs t\n" +
                "       where ifinheritable = '1' and dr = '0' and catid <> ? and catid in (" + route.replaceAll("-", ",") + ")\n" +
                "       union all (select *\n" +
                "                  from res_attrs\n" +
                "                  where dr = '0' and catid = ?\n" +
                "                  order by sort)\n" +
                "     ) a left join (select *\n" +
                "                    from res_attr_value\n" +
                "                    where resid = ? and dr = '0') b on a.id = b.attrid\n";
        RcdSet attrs = db.query(attrsql, catid, catid, resid);
        return attrs;
    }

    public R queryResAllById(String id) {
        JSONObject data = new JSONObject();
        String class_id = "";
        Rcd rs = db.uniqueRecord("select * from res t where dr=0 and id=?", id);
        if (rs != null) {
            class_id = rs.getString("class_id");
        }
        // 获取属性数据
        RcdSet attrs = queryZcAttrWithValue(class_id, id);
        data.put("extattr", ConvertUtil.OtherJSONObjectToFastJSONArray(attrs.toJsonArrayWithJsonObject()));
        // 获取res数据
        if (ToolUtil.isNotEmpty(id)) {
            String sql = "select";
            RcdSet attrs_rs = queryZcAttrWithValue(class_id, id);
            for (int i = 0; i < attrs_rs.size(); i++) {
                // 拼接sql
                String valsql = "";
                if ("inputint".equals(attrs_rs.getRcd(i).getString("inputtype"))) {
                    // valsql = " cast( attrvalue as signed integer)";
                    valsql = " attrvalue+0";
                } else if ("inputstr".equals(attrs_rs.getRcd(i).getString("inputtype"))) {
                    valsql = "attrvalue";
                } else {
                    valsql = "attrvalue";
                }
                sql = sql + " (select " + valsql
                        + " from res_attr_value i where i.dr=0 and i.resid=t.id and i.attrid='"
                        + attrs_rs.getRcd(i).getString("id") + "') \"" + attrs_rs.getRcd(i).getString("attrcode")
                        + "\",  ";
            }
            sql = sql + ZcCommonService.resSqlbody + " t.* from res t where dr=0 and id=?";
            Rcd rs2 = db.uniqueRecord(sql, id);
            if (rs2 != null) {
                data.put("data", ConvertUtil.OtherJSONObjectToFastJSONObject(rs2.toJsonObject()));
            }
        }
        // 获取更新记录
        RcdSet urs = db.query(
                " select * from (\n" +
                        "select\n" +
                        "  (select name from sys_user_info where user_id=t.create_by)create_uname,\n" +
                        "  t.* from  res_change_item t where dr='0' and t.resid=? order by create_time desc) tab limit 300\n",
                id);
        data.put("updatadata", ConvertUtil.OtherJSONObjectToFastJSONArray(urs.toJsonArrayWithJsonObject()));

        // 获取故障登记表
        RcdSet grs = db.query(
                " select * from (\n" +
                        "select b.* from res_repair_item a ,res_repair b where a.repairid=b.id and a.dr='0' and a.resid=? and b.dr='0'\n" +
                        "  order by create_time desc)tab limit 300",
                id);
        data.put("faultdata", ConvertUtil.OtherJSONObjectToFastJSONArray(grs.toJsonArrayWithJsonObject()));

        return R.SUCCESS_OPER(data);

    }


    public R addRes(TypedHashMap<String, Object> ps) {
        Date date = new Date(); // 获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
        String nowtime = simpleDateFormat.format(date);

        String recycle = ps.getString("recycle");
        String id = ps.getString("id");
        String class_id = ps.getString("class_id");

        String sql = "";
        Insert ins = new Insert("res_history");

        //判断资产编码是否正确
        Rcd rs = null;
        if (ToolUtil.isEmpty(class_id)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        } else {
            rs = db.uniqueRecord("select * from ct_category where dr='0' and id=?", class_id);
            if (rs == null) {
                return R.FAILURE("资产别名称编码不存在,Code:" + class_id);
            }
        }

        // 自动计算脱保情况
        String wbcompute = computeWb(ps.getString("wb"), ps.getString("wb_auto"), ps.getString("wbout_date_f"));
        String uuid = "";
        if (ToolUtil.isEmpty(id)) {
            ins.set("oper_type", "入库");
            Insert me = new Insert("res");
            id = db.getUUID();
            me.set("id", id);
            uuid = createUuid(ZcCommonService.UUID_ZC);

            if (ToolUtil.isEmpty(uuid)) {
                return R.FAILURE("未生产有效编号,请稍后重新重试!");
            }
            me.set("uuid", uuid);
            me.set("dr", "0");
            me.setIf("update_time", nowtime);
            me.setIf("update_by", this.getUserId());
            me.setIf("create_time", nowtime);
            me.setIf("create_by", this.getUserId());
            me.setIf("class_id", class_id);
            me.setIf("sn", ps.getString("sn"));
            me.setIf("mark", ps.getString("mark"));
            me.setIf("maintain_userid", ps.getString("maintain_userid"));
            me.setIf("headuserid", ps.getString("headuserid"));
            me.setIf("rank", ps.getString("rank"));
            me.setIf("loc", ps.getString("loc"));
            me.setIf("locshow", ps.getString("locshow"));
            me.setIf("type", ps.getString("type"));
            me.setIf("category", rs.getString("root"));
            me.setIf("status", ps.getString("status"));
            me.setIf("env", ps.getString("env"));
            me.setIf("risk", ps.getString("risk"));
            me.setIf("recycle", ps.getString("recycle"));
            me.setIf("ip", ps.getString("ip"));
            me.setIf("frame", ps.getString("frame"));
            me.setIf("brand", ps.getString("brand"));
            me.setIf("wb", wbcompute);
            me.setIf("confdesc", ps.getString("confdesc"));
            me.setIf("rack", ps.getString("rack"));
            me.setIf("model", ps.getString("model"));
            me.setIf("buy_time", ps.getString("buy_time_f") == null ? null : ps.getString("buy_time_f") + " 01:00:00");
            me.setIf("changestate", "insert");
            me.setIf("net_worth", ps.getString("net_worth", "0"));
            me.setIf("buy_price", ps.getString("buy_price", "0"));

            me.setIf("part_id", "none".equals(ps.getString("part_id")) ? 0 : ps.getString("part_id"));
            me.setIf("mgr_part_id", "none".equals(ps.getString("mgr_part_id")) ? 0 : ps.getString("mgr_part_id"));
            me.setIf("used_userid", ps.getString("used_userid"));
            me.setIf("locdtl", ps.getString("locdtl"));
            me.setIf("wb_auto", ps.getString("wb_auto"));
            me.setIf("wbout_date",
                    ps.getString("wbout_date_f") == null ? null : ps.getString("wbout_date_f") + " 01:00:00");
            me.setIf("fs1", ps.getString("fs1"));
            me.setIf("fs2", ps.getString("fs2"));
            me.setIf("fs3", ps.getString("fs3"));
            me.setIf("fs4", ps.getString("fs4"));
            me.setIf("fs5", ps.getString("fs5"));
            me.setIf("fs6", ps.getString("fs6"));
            me.setIf("fs7", ps.getString("fs7"));
            me.setIf("fs20", ps.getString("fs20"));
            me.setIf("zc_cnt", ps.getString("zc_cnt", "1"));
            me.setIf("img", ps.getString("img"));
            me.setIf("attach", ps.getString("attach"));
            me.setIf("supplier", ps.getString("supplier"));
            me.setIf("wbsupplier", ps.getString("wbsupplier"));
            me.setIf("zcsource", ps.getString("zcsource"));
            me.setIf("wbct", ps.getString("wbct"));
            me.setIf("belong_part_id", ps.getString("belong_part_id"));
            me.setIf("belong_company_id", ps.getString("belong_company_id"));
            me.setIf("used_company_id", ps.getString("used_company_id"));
            me.setIf("unit_price", ps.getString("unit_price"));
            me.setIf("warehouse", ps.getString("warehouse"));
            me.setIf("batchno", ps.getString("batchno"));
            me.setIf("usefullife", ps.getString("usefullife"));
            me.setIf("unit", ps.getString("unit"));
            me.setIf("isscrap", ps.getString("isscrap"));

            sql = me.getSQL();
        } else {
            Update me = new Update("res");
            me.set("class_id", class_id);
            me.setIf("sn", ps.getString("sn"));
            me.setIf("mark", ps.getString("mark"));
            me.setIf("maintain_userid", ps.getString("maintain_userid"));
            me.setIf("headuserid", ps.getString("headuserid"));
            me.setIf("rank", ps.getString("rank"));
            me.setIf("loc", ps.getString("loc"));
            me.setIf("locshow", ps.getString("locshow"));
            me.setIf("status", ps.getString("status"));
            me.setIf("env", ps.getString("env"));
            me.setIf("risk", ps.getString("risk"));
            me.setIf("type", ps.getString("type"));
            me.setIf("recycle", ps.getString("recycle"));
            me.setIf("ip", ps.getString("ip"));
            me.setIf("frame", ps.getString("frame"));
            me.setIf("wb", wbcompute);
            me.setIf("confdesc", ps.getString("confdesc"));
            me.setIf("rack", ps.getString("rack"));
            me.setIf("model", ps.getString("model"));
            me.setIf("brand", ps.getString("brand"));
            me.setIf("buy_time", ps.getString("buy_time_f") == null ? null : ps.getString("buy_time_f") + " 01:00:00");
            me.setIf("changestate", "updated");
            me.setIf("buy_price", ps.getString("buy_price", "0"));
            me.setIf("net_worth", ps.getString("net_worth", "0"));
            me.setIf("part_id", "none".equals(ps.getString("part_id")) ? 0 : ps.getString("part_id"));
            me.setIf("mgr_part_id", "none".equals(ps.getString("mgr_part_id")) ? 0 : ps.getString("mgr_part_id"));
            me.setIf("used_userid", ps.getString("used_userid"));
            me.setIf("locdtl", ps.getString("locdtl"));
            me.setIf("wb_auto", ps.getString("wb_auto"));
            me.setIf("wbout_date",
                    ps.getString("wbout_date_f") == null ? null : ps.getString("wbout_date_f") + " 01:00:00");
            me.setIf("fs1", ps.getString("fs1"));
            me.setIf("fs2", ps.getString("fs2"));
            me.setIf("fs3", ps.getString("fs3"));
            me.setIf("fs4", ps.getString("fs4"));
            me.setIf("fs5", ps.getString("fs5"));
            me.setIf("fs6", ps.getString("fs6"));
            me.setIf("fs7", ps.getString("fs7"));
            me.setIf("fs20", ps.getString("fs20"));
            me.setIf("zc_cnt", ps.getString("zc_cnt"));
            me.setIf("img", ps.getString("img"));
            me.setIf("attach", ps.getString("attach"));
            me.setIf("supplier", ps.getString("supplier"));
            me.setIf("wbsupplier", ps.getString("wbsupplier"));
            me.setIf("zcsource", ps.getString("zcsource"));
            me.setIf("wbct", ps.getString("wbct"));
            me.setIf("belong_part_id", ps.getString("belong_part_id"));
            me.setIf("belong_company_id", ps.getString("belong_company_id"));
            me.setIf("used_company_id", ps.getString("used_company_id"));
            me.setIf("unit_price", ps.getString("unit_price"));
            me.setIf("warehouse", ps.getString("warehouse"));
            me.setIf("batchno", ps.getString("batchno"));
            me.setIf("usefullife", ps.getString("usefullife"));
            me.setIf("unit", ps.getString("unit"));
            me.where().and("id=?", id);
            sql = me.getSQL();

            Rcd source_recycle_rs = db.uniqueRecord(" select recycle from res where id=?", id);
            if (ToolUtil.isNotEmpty(recycle) && source_recycle_rs != null) {
                // 获取当前的recycle
                String source_recycle = source_recycle_rs.getString("recycle");
                if (source_recycle == null || source_recycle.equals(recycle)) {
                    ins.set("oper_type", "更新");
                } else {
                    String act = db
                            .uniqueRecord(" select name,dict_item_id from sys_dict_item where dict_item_id=? ", recycle)
                            .getString("name");
                    ins.set("oper_type", "操作-" + act);
                }

            } else {
                ins.set("oper_type", "更新");
            }
        }
        db.execute(sql);
        // 更新记录表
        ins.set("id", db.getUUID());
        ins.set("res_id", id);
        ins.set("oper_time", nowtime);
        ins.set("oper_user", this.getUserId());
        ins.set("fullct", ps.toString());
        db.execute(ins);

        // 更新其他属性，属性值、
        String attrvals = ps.getString("attrvals");
        Update del = new Update("res_attr_value");
        del.set("dr", "1");
        del.where().and("resid=?", id);
        db.execute(del);
        if (ToolUtil.isNotEmpty(attrvals)) {
            JSONArray valsarr = JSONArray.parseArray(attrvals);
            for (int i = 0; i < valsarr.size(); i++) {
                Insert me = new Insert("res_attr_value");
                me.set("id", db.getUUID());
                me.set("resid", id);
                me.set("dr", "0");
                me.setIf("attrid", valsarr.getJSONObject(i).getString("id"));
                me.setIf("attrvalue", valsarr.getJSONObject(i).getString("attrvalue"));
                db.execute(me);
            }
        }

        if (ToolUtil.isEmpty(id)) {
            zcChangeService.zcRkConfirm(uuid);
        }

        return R.SUCCESS_OPER();
    }

    public R queryZcColCtlShow() {
        JSONArray res = new JSONArray();
        //zccolctl,zccolctlcommon
        Rcd rs1 = db.uniqueRecord("select * from sys_params where id='zccolctl'");
        Rcd rs2 = db.uniqueRecord("select * from sys_params where id='zccolctlcommon'");
        if (rs1 == null) {
            Insert ins1 = new Insert("sys_params");
            ins1.set("dr", "0");
            ins1.set("type", "system");
            ins1.set("id", "zccolctl");
            ins1.set("name", "前端入库资产列显示");
            ins1.set("value", "{}");
            db.execute(ins1);
            rs1 = db.uniqueRecord("select * from sys_params where id='zccolctl'");
        }
        if (rs2 == null) {
            Insert ins2 = new Insert("sys_params");
            ins2.set("dr", "0");
            ins2.set("type", "system");
            ins2.set("id", "zccolctlcommon");
            ins2.set("name", "前端常用资产列显示");
            ins2.set("value", "{}");
            db.execute(ins2);
            rs2 = db.uniqueRecord("select * from sys_params where id='zccolctlcommon'");
        }
        String rs1ct = rs1.getString("value");
        JSONObject rs1obj = JSONObject.parseObject(rs1ct);
        rs1obj.put("zccolparname", rs1.getString("name"));
        rs1obj.put("zccolparid", rs1.getString("id"));
        res.add(rs1obj);

        String rs2ct = rs2.getString("value");
        JSONObject rs2obj = JSONObject.parseObject(rs2ct);
        rs2obj.put("zccolparname", rs2.getString("name"));
        rs2obj.put("zccolparid", rs2.getString("id"));
        res.add(rs2obj);
        return R.SUCCESS_OPER(res);
    }

    public R modifyZcColCtlShow(String id, String json) {
        Update ups = new Update("sys_params");
        ups.set("dr", "0");
        ups.setIf("value", json);
        ups.where().andIf("id=?", id);
        db.execute(ups);
        return R.SUCCESS_OPER();
    }

    public R queryZcColCtlById(String id) {
        Rcd rs2 = db.uniqueRecord("select * from sys_params where id=?", id);
        return R.SUCCESS_OPER(rs2.toJsonObject());
    }

    public R fastProcessItemCheck(String type, String items) {

        String sql = "select count(1) cnt from res where dr='0' ";
        if (ZcCommonService.ZC_BUS_TYPE_CGCW.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_CG);
        } else if (ZcCommonService.ZC_BUS_TYPE_CGJB.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_CG);
        } else if (ZcCommonService.ZC_BUS_TYPE_CGWB.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_CG);
        } else if (ZcCommonService.ZC_BUS_TYPE_DB.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_DB);
        } else if (ZcCommonService.ZC_BUS_TYPE_BX.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_REPAIR);
        } else if (ZcCommonService.ZC_BUS_TYPE_BF.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_BF);
        } else if (ZcCommonService.ZC_BUS_TYPE_LY.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_LY);
        } else if (ZcCommonService.ZC_BUS_TYPE_TK.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_TK);
        } else if (ZcCommonService.ZC_BUS_TYPE_JY.equals(type)) {
            sql = sql + buildZcDataRange(ZcCommonService.DATARANGE_JY);
        }
        JSONArray items_arr = JSONArray.parseArray(items);
        if (ToolUtil.isNotEmpty(items_arr) && items_arr.size() > 0) {
            String idsstr = " and id in (";
            for (int i = 0; i < items_arr.size(); i++) {
                idsstr = idsstr + "'" + items_arr.getString(i) + "',";
            }
            idsstr = idsstr + "',-1')";
            sql = sql + idsstr;
        }
        if (!db.uniqueRecord(sql).getString("cnt").equals(items_arr.size() + "")) {
            return R.FAILURE("所选项中要包含不符合要求的数据");
        }
        return R.SUCCESS_OPER();
    }

}
