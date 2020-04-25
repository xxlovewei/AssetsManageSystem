package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.service.IResAllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class ZcService extends BaseService{

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    IResActionItemService ResActionItemServiceImpl;

    @Autowired
    IResAllocateService ResAllocateServiceImpl;

    private String createUuid5(){
        return  UUID.randomUUID().toString().substring(9, 23).toUpperCase();
    }

    public String createUuid(String type) {
        int cnt = 5;
        String id = createUuid5();
        int i = 0;
        if (type.equals(ZcCommonService.UUID_ZC)) {
            for (i = 0; i< cnt; i++) {
                QueryWrapper<Res> ew = new QueryWrapper<Res>();
                String finalId =type+id;
                ew.and(j -> j.eq("uuid", finalId));
                Res rs=ResServiceImpl.getOne(ew);
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
                String finalId = type+id;
                ew.and(j -> j.eq("busuuid", finalId));
                ResActionItem rs=ResActionItemServiceImpl.getOne(ew);
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
        else if (type.equals(ZcCommonService.UUID_DB)) {
            for (i = 0; i < cnt; i++) {
                QueryWrapper<ResAllocate> ew = new QueryWrapper<ResAllocate>();
                String finalId = type+id;
                ew.and(j -> j.eq("uuid", finalId));
                ResAllocate rs=ResAllocateServiceImpl.getOne(ew);
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
        else if (type.equals(ZcCommonService.UUID_LY) || type.equals(ZcCommonService.UUID_JY)
                || type.equals(ZcCommonService.UUID_ZY)) {
            for (i = 0; i < cnt; i++) {
                QueryWrapper<ResActionItem> ew = new QueryWrapper<ResActionItem>();
                String finalId = type+id;
                ew.and(j -> j.eq("busuuid", finalId));
                ResActionItem rs=ResActionItemServiceImpl.getOne(ew);
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



    // 根据ClassId获取数据,优先判断multiclassroot,在获取class_id
    public R queryResAllGetData(String belongcomp,String comp,String datarange,String classroot, String class_id, String wb, String env, String recycle, String loc, String search) {

        // 获取属性数据
        String attrsql = "select * from res_class_attrs where class_id=? and dr='0'";
        RcdSet attrs_rs = db.query(attrsql, class_id);
        String sql = "select";
        if (attrs_rs != null) {
            for (int i = 0; i < attrs_rs.size(); i++) {
                // 拼接sql
                String valsql = "";
                if (attrs_rs.getRcd(i).getString("attr_type").equals("number")) {
                    // "to_number(attr_value)";
                    valsql = " cast( attr_value as SIGNED INTEGER)";
                } else if (attrs_rs.getRcd(i).getString("attr_type").equals("string_arr")) {
                    valsql = "attr_value";
                } else {
                    valsql = "attr_value";
                }
                sql = sql + " (select " + valsql
                        + " from res_attr_value i where i.dr=0 and i.res_id=t.id and i.attr_id='"
                        + attrs_rs.getRcd(i).getString("attr_id") + "') \"" + attrs_rs.getRcd(i).getString("attr_code")
                        + "\",  ";
            }
        }
        sql = sql + ZcCommonService.resSqlbody + " t.* from res t where dr=0  ";

        if(ToolUtil.isNotEmpty(classroot)){
            //获取多个类型
            String subsql=" t.dr='0' and t.root='"+classroot+"' and t.route not like '46%' and t.node_level>1 ";
            sql = sql + " and class_id in (select id from ct_category t where " +subsql+")";
        }

        if(ToolUtil.isNotEmpty(class_id)&& !"all".equals(class_id)){
            sql = sql + " and class_id in (select id from ct_category  where dr='0' and ( id='" + class_id
                    + "' or parent_id='" + class_id + "')) ";
        }

//
//        if (ToolUtil.isNotEmpty(class_id) && !"all".equals(class_id)) {
//            if (class_id.equals("multiclass")) {
//                //获取多个类型
//                String subsql=" t.dr='0' and t.root='3' and t.route not like '46%' and t.node_level>1 ";
//                sql = sql + " and class_id in (select id from ct_category t where " +subsql+")";
//            } else {
//                //获取一个类型
//                sql = sql + " and class_id in (select id from ct_category  where dr='0' and ( id='" + class_id
//                        + "' or parent_id='" + class_id + "')) ";
//            }
//
//        }

        if (ToolUtil.isNotEmpty(loc) && !"all".equals(loc)) {
            sql = sql + " and loc='" + loc + "'";
        }

        if (ToolUtil.isNotEmpty(env) && !"all".equals(env)) {
            sql = sql + " and env='" + env + "'";
        }

        if (ToolUtil.isNotEmpty(wb) && !"all".equals(wb)) {
            sql = sql + " and wb='" + wb + "'";
        }

        if (ToolUtil.isNotEmpty(recycle) && !"all".equals(recycle)) {
            sql = sql + " and recycle='" + recycle + "'";
        }

        if(ToolUtil.isNotEmpty(comp)){
            sql = sql + " and used_company_id='" + comp + "'";
        }
        if(ToolUtil.isNotEmpty(belongcomp)){
            sql = sql + " and belong_company_id='" + belongcomp + "'";
        }

        //idle,inuse,scrap,borrow,repair,stopuse,allocation
        if(ToolUtil.isNotEmpty(datarange)){
            if(ZcCommonService.DATARANGE_REPAIR.equals(datarange)){
                sql = sql + " and recycle in ('"+ZcCommonService.RECYCLE_IDLE+"','"+ZcCommonService.RECYCLE_INUSE+"')";
            }else if(ZcCommonService.DATARANGE_LY.equals(datarange)){
                sql = sql + " and recycle in ('"+ZcCommonService.RECYCLE_IDLE+"')";
            }else if(ZcCommonService.DATARANGE_JY.equals(datarange)){
                sql = sql +  " and recycle in ('"+ZcCommonService.RECYCLE_IDLE+"','"+ZcCommonService.RECYCLE_INUSE+"')";
            }else if(ZcCommonService.DATARANGE_DB.equals(datarange)){
                sql = sql +  " and recycle in ('"+ZcCommonService.RECYCLE_IDLE+"','"+ZcCommonService.RECYCLE_INUSE+"')";
            }

        }

        if (ToolUtil.isNotEmpty(search)) {
            sql = sql + " and  (rack like '%" + search + "%' or fs1 like '%" + search + "%' or mark like '%" + search
                    + "%' or uuid like '%" + search + "%' or model like '%" + search + "%'  or  sn like '%" + search
                    + "%' )";
        }

        sql = sql + " order by update_time desc,loc,rack,frame ";

        RcdSet rs2 = db.query(sql);

        return R.SUCCESS_OPER(rs2.toJsonArrayWithJsonObject());
    }

    public R batchUpdateRes(TypedHashMap<String, Object> ps) {
        Date date = new Date(); // 获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
        String nowtime = simpleDateFormat.format(date);

        String ids = ps.getString("ids");
        JSONArray ids_arr = JSONArray.parseArray(ids);
        List<SQL> sqls = new ArrayList<SQL>();
        for (int i = 0; i < ids_arr.size(); i++) {
            Update me = new Update("res");

            if (ToolUtil.isNotEmpty(ps.getString("ifrecycleSel")) && "Y".equals(ps.getString("ifrecycleSel"))) {
                me.setIf("recycle", ps.getString("recycleSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifriskSel")) && "Y".equals(ps.getString("ifriskSel"))) {
                me.setIf("risk", ps.getString("riskSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifenvSel")) && "Y".equals(ps.getString("ifenvSel"))) {
                me.setIf("env", ps.getString("envSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifwbSel")) && "Y".equals(ps.getString("ifwbSel"))) {
                me.setIf("wb", ps.getString("wbSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifusedPartSel")) && "Y".equals(ps.getString("ifusedPartSel"))) {
                me.setIf("part_id", ps.getString("partSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifusedUserSel")) && "Y".equals(ps.getString("ifusedUserSel"))) {
                me.setIf("used_userid", ps.getString("usedunameSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("iftbComputeSel")) && "Y".equals(ps.getString("iftbComputeSel"))) {
                me.setIf("wb_auto", ps.getString("tbSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("iflocSel")) && "Y".equals(ps.getString("iflocSel"))) {
                me.setIf("loc", ps.getString("locSel"));
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifbuySel")) && "Y".equals(ps.getString("ifbuySel"))) {
                me.setIf("buy_time",
                        ps.getString("buy_time_f") == null ? null : ps.getString("buy_time_f") + " 01:00:00");
            }

            if (ToolUtil.isNotEmpty(ps.getString("ifTbSel")) && "Y".equals(ps.getString("ifTbSel"))) {
                me.setIf("wbout_date",
                        ps.getString("wbout_date_f") == null ? null : ps.getString("wbout_date_f") + " 01:00:00");

            }

            me.setIf("changestate", "updated");
            me.setIf("update_time", nowtime);
            me.setIf("update_by", this.getUserId());
            me.where().and("id=?", ids_arr.getString(i));
            sqls.add(me);

            Insert ins = new Insert("res_history");
            ins.set("oper_type", "批量更新");
            ins.set("id", db.getUUID());
            ins.set("res_id", ids_arr.getString(i));
            ins.set("oper_time", nowtime);
            ins.set("oper_user", this.getUserId());
            ins.set("fullct", "略");
            sqls.add(ins);
        }

        // 批量计算
        db.executeSQLList(sqls);
        checkWbMethod();
        return R.SUCCESS_OPER();

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
                System.out.println(date);
            } catch (ParseException px) {
                px.printStackTrace();
            }
        }
        return wbcompute;
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
        if (ToolUtil.isEmpty(class_id)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        } else {
            Rcd rs = db.uniqueRecord("select * from ct_category where dr='0' and id=?", class_id);
            if (rs == null) {
                return R.FAILURE("资产别名称编码不存在,Code:" + class_id);
            }
        }

        // 自动计算脱保情况
        String wbcompute = computeWb(ps.getString("wb"), ps.getString("wb_auto"), ps.getString("wbout_date_f"));

        if (ToolUtil.isEmpty(id)) {
            ins.set("oper_type", "入库");

            Insert me = new Insert("res");
            id = db.getUUID();
            me.set("id", id);
            String uuid = createUuid(ZcCommonService.UUID_ZC);
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
            me.setIf("zc_category", ps.getString("zc_category"));
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
            me.setIf("zc_category", ps.getString("zc_category"));
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
                    ins.set("oper_type", "动作-" + act);
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
        del.where().and("res_id=?", id);
        db.execute(del);
        if (ToolUtil.isNotEmpty(attrvals)) {
            JSONArray valsarr = JSONArray.parseArray(attrvals);
            for (int i = 0; i < valsarr.size(); i++) {
                Insert me = new Insert("res_attr_value");
                me.set("id", db.getUUID());
                me.set("res_id", id);
                me.set("dr", "0");
                me.setIf("attr_id", valsarr.getJSONObject(i).getString("attr_id"));
                me.setIf("attr_value", valsarr.getJSONObject(i).getString("attr_value"));
                db.execute(me);
            }

        }
        return R.SUCCESS_OPER();
    }

}
