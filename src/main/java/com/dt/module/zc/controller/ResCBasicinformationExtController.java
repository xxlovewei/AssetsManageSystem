package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.zc.entity.ResCBasicinformation;
import com.dt.module.zc.entity.ResCBasicinformationItem;
import com.dt.module.zc.service.IResCBasicinformationItemService;
import com.dt.module.zc.service.IResCBasicinformationService;
import com.dt.module.zc.service.impl.ResCFinanceService;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/api/zc/resCBasicinformation/ext")
public class ResCBasicinformationExtController extends BaseController {


    @Autowired
    ZcService zcService;

    @Autowired
    ZcChangeService zcChangeService;


    @Autowired
    IResCBasicinformationService ResCBasicinformationServiceImpl;


    @Autowired
    IResCBasicinformationItemService ResCBasicinformationItemServiceImpl;

    @ResponseBody
    @Acl(info = "插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insert.do")
    public R insert(ResCBasicinformation entity, String items) throws ParseException {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        String buytimestr = ps.getString("tbuytimestr");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(buytimestr);
        entity.setTbuytime(date);
        String uuid = zcService.createUuid(ZcCommonService.ZC_BUS_TYPE_CGJB);
        entity.setStatus(ResCFinanceService.STATUS_SUCCESS);
        entity.setBusuuid(uuid);
        ArrayList<ResCBasicinformationItem> list = new ArrayList<ResCBasicinformationItem>();
        ArrayList<Res> reslist = new ArrayList<Res>();
        JSONArray items_arr = JSONArray.parseArray(items);
        for (int i = 0; i < items_arr.size(); i++) {
            ResCBasicinformationItem e = new ResCBasicinformationItem();
            e.setBusuuid(uuid);
            e.setResid(items_arr.getJSONObject(i).getString("id"));
            e.setTbrand(entity.getTbrand());
            e.setTbuytime(entity.getTbuytime());
            e.setTclassid(entity.getTclassid());
            e.setTloc(entity.getTloc());
            e.setTmodel(entity.getTmodel());
            e.setTpartid(entity.getTpartid());
            e.setTsn(entity.getTsn());
            e.setTsupplier(entity.getTsupplier());
            e.setTusedcompanyid(entity.getTusedcompanyid());
            e.setTusefullife(entity.getTusefullife());
            e.setTuseduserid(entity.getTuseduserid());
            e.setTzccnt(entity.getTzccnt());
            e.setTzcsource(entity.getTzcsource());

            e.setTlabel1(entity.getTlabel1());
            e.setTunit(entity.getTunit());
            e.setTconfdesc(entity.getTconfdesc());
            e.setTlocdtl(entity.getTlocdtl());

            e.setTclassidstatus(entity.getTclassidstatus());
            e.setTmodelstatus(entity.getTmodelstatus());
            e.setTsnstatus(entity.getTsnstatus());
            e.setTzcsourcestatus(entity.getTzcsourcestatus());
            e.setTzccntstatus(entity.getTzccntstatus());
            e.setTsupplierstatus(entity.getTsupplierstatus());
            e.setTbrandstatus(entity.getTbrandstatus());
            e.setTbuytimestatus(entity.getTbuytimestatus());
            e.setTlocstatus(entity.getTlocstatus());
            e.setTusefullifestatus(entity.getTusefullifestatus());
            e.setTusedcompanyidstatus(entity.getTusedcompanyidstatus());
            e.setTpartidstatus(entity.getTpartidstatus());
            e.setTuseduseridstatus(entity.getTuseduseridstatus());

            e.setTlabel1status(entity.getTlabel1status());
            e.setTlocdtlstatus(entity.getTlocdtlstatus());
            e.setTunitstatus(entity.getTunitstatus());
            e.setTconfdescstatus(entity.getTconfdescstatus());

            list.add(e);
        }
        System.out.println(entity);
        ResCBasicinformationServiceImpl.save(entity);
        ResCBasicinformationItemServiceImpl.saveBatch(list);
        zcChangeService.zcCGJBSureChange(uuid);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        String sql = "select\n" +
                " (select name from sys_user_info where user_id=b.create_by) createusername," +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tzcsource) tzcsourcestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tsupplier) tsupplierstr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tusefullife) tusefullifestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tloc) tlocstr,\n" +

                "(select node_name from hrm_org_part where node_id=b.tpartid) tpartnamestr," +
                "(select node_name from hrm_org_part where node_id=b.tusedcompanyid) tusedcompanynamestr," +
                "(select name from sys_user_info where user_id=b.tuseduserid) tusedusernamestr," +

                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tbrand) tbrandstr,\n" +
                "(select route_name from ct_category where dr='0' and id=b.tclassid) tclassfullname,\n" +
                "date_format(tbuytime,'%Y-%m-%d') tbuytimestr,b.* \n" +
                "from res_c_basicinformation b where dr='0' order by create_time desc";
        System.out.println(sql);
        RcdSet rs = db.query(sql);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByUuid.do")
    public R selectByUuid(String uuid) {

        String sql = "select " + ZcCommonService.resSqlbody + " t.* ,b.*,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tzcsource) tzcsourcestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tsupplier) tsupplierstr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tusefullife) tusefullifestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tloc) tlocstr,\n" +

                "(select node_name from hrm_org_part where node_id=b.tpartid) tpartnamestr," +
                "(select node_name from hrm_org_part where node_id=b.tusedcompanyid) tusedcompanynamestr," +
                "(select name from sys_user_info where user_id=b.tuseduserid) tusedusernamestr," +

                "(select name from sys_dict_item where dr='0' and dict_item_id=b.tbrand) tbrandstr,\n" +
                "(select route_name from ct_category where dr='0' and id=b.tclassid) tclassfullname,\n" +
                "date_format(tbuytime,'%Y-%m-%d') tbuytimestr ,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fzcsource) fzcsourcestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fsupplier) fsupplierstr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fusefullife) fusefullifestr,\n" +
                "(select name from sys_dict_item where dr='0' and dict_item_id=b.floc) flocstr,\n" +

                "(select node_name from hrm_org_part where node_id=b.fpartid) fpartnamestr," +
                "(select node_name from hrm_org_part where node_id=b.fusedcompanyid) fusedcompanynamestr," +
                "(select name from sys_user_info where user_id=b.fuseduserid) fusedusernamestr," +

                "(select name from sys_dict_item where dr='0' and dict_item_id=b.fbrand) fbrandstr,\n" +
                "(select route_name from ct_category where dr='0' and id=b.fclassid) fclassfullname,\n" +
                "date_format(fbuytime,'%Y-%m-%d') fbuytimestr \n" +
                "from res t,res_c_basicinformation_item b where t.id=b.resid and t.dr='0' and b.dr='0' and b.busuuid=?";
        RcdSet rs = db.query(sql, uuid);
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }
}
