package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.module.zc.entity.ResInspection;
import com.dt.module.zc.entity.ResInspectionPlan;
import com.dt.module.zc.service.IResInspectionService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc/resInspection/ext")
public class ResInspectionExtController extends BaseController {

    @Autowired
    IResInspectionService ResInspectionServiceImpl;

    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectById.do")
    public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        ResInspection obj = ResInspectionServiceImpl.getById(id);
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select b.status inspectstatus,b.mark inspectmark," + ZcCommonService.resSqlbody + " t.* from res t,res_inspection_pitem b where t.id=b.resid and t.dr='0' and b.dr='0' and b.busid=?";
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, obj.getBusid()).toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }

}
