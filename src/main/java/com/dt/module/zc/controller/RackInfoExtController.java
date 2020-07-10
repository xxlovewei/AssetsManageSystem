package com.dt.module.zc.controller;


import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.zc.service.IResAllocateService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zc/rack/ext")
public class RackInfoExtController extends BaseController {


    @Autowired
    ZcService zcService;


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryRackInfoTreeByDcId.do")
    public R queryRackInfoTreeByDcId(@RequestParam(value = "id", required = true, defaultValue = "") String id) {

        String sql = "select\n" +
                "  b.name         text,\n" +
                "  b.dict_item_id id,\n" +
                "  'rack'         type,\n" +
                "  '1'            parent,\n" +
                "  a.loc          dc,\n" +
                "  a.cnt          cnt\n" +
                "from (select\n" +
                "        rack,\n" +
                "        loc,\n" +
                "        count(1) cnt\n" +
                "      from res\n" +
                "      where dr = '0' and loc = ?\n" +
                "      group by rack, loc) a,\n" +
                "  sys_dict_item b\n" +
                "where a.rack = b.dict_item_id\n" +
                "order by 1\n";
        return R.SUCCESS_OPER(db.query(sql, id).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryZcByRackId.do")
    public R queryZcByRackId(String rack, String dcid) {
        TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
        return zcService.queryResAllGetData(null, null, null, null, null, null, null, null, null, dcid, null, ps);

    }


}
