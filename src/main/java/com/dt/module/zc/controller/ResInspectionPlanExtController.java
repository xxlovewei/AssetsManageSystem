package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.zc.entity.ResInspectionPitem;
import com.dt.module.zc.entity.ResInspectionPlan;
import com.dt.module.zc.entity.ResInspectionUser;
import com.dt.module.zc.service.IResInspectionPitemService;
import com.dt.module.zc.service.IResInspectionPlanService;
import com.dt.module.zc.service.IResInspectionUserService;
import com.dt.module.zc.service.impl.ResInspectPlanService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/zc/resInspectionPlan/ext")
public class ResInspectionPlanExtController extends BaseController {
    @Autowired
    IResInspectionPlanService ResInspectionPlanServiceImpl;

    @Autowired
    IResInspectionPitemService ResInspectionPitemServiceImpl;

    @Autowired
    IResInspectionUserService ResInspectionUserServiceImpl;

    @Autowired
    ResInspectPlanService resInspectPlanService;

    @Autowired
    ZcService zcService;

    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/create.do")
    public R create(ResInspectionPlan entity, String items) {
        JSONArray itemarr = JSONArray.parseArray(items);
        if (itemarr == null || itemarr.size() == 0) {
            return R.FAILURE_NO_DATA();
        }
        String busid;
        if (ToolUtil.isEmpty(entity.getId())) {
            busid = zcService.createUuid(ZcCommonService.UUID_XJ);
            entity.setBusid(busid);
        } else {
            busid = entity.getBusid();
        }
        List<ResInspectionPitem> list = new ArrayList<ResInspectionPitem>();
        for (int i = 0; i < itemarr.size(); i++) {
            ResInspectionPitem e = new ResInspectionPitem();
            e.setType("plan");
            e.setBusid(busid);
            e.setResid(itemarr.getJSONObject(i).getString("id"));
            list.add(e);
        }
        QueryWrapper<ResInspectionPitem> qw = new QueryWrapper<ResInspectionPitem>();
        qw.eq("busid", busid);
        ResInspectionPitemServiceImpl.remove(qw);
        ResInspectionPitemServiceImpl.saveBatch(list);

        JSONArray userarr = JSONArray.parseArray(entity.getActionusers());
        List<ResInspectionUser> list2 = new ArrayList<ResInspectionUser>();
        for (int j = 0; j < userarr.size(); j++) {
            ResInspectionUser e = new ResInspectionUser();
            e.setBusid(busid);
            e.setUserid(userarr.getJSONObject(j).getString("user_id"));
            list2.add(e);
        }
        QueryWrapper<ResInspectionUser> qw2 = new QueryWrapper<ResInspectionUser>();
        qw.eq("busid", busid);
        ResInspectionUserServiceImpl.remove(qw2);
        ResInspectionUserServiceImpl.saveBatch(list2);

        return R.SUCCESS_OPER(ResInspectionPlanServiceImpl.saveOrUpdate(entity));
    }

    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectById.do")
    public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        ResInspectionPlan obj = ResInspectionPlanServiceImpl.getById(id);
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where id in (select resid from res_inspection_pitem where dr='0' and busid=?)";
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, obj.getBusid()).toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }

    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/createInspection.do")
    public R createInspection(String busid) {
        return resInspectPlanService.createInspection(busid);
    }


}
