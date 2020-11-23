package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.zc.service.impl.ZcService;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/api/zc/my")
public class MyController extends BaseController {

    @Autowired
    ZcService zcService;


    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryMyResAll.do")
    public R queryPageResAllByClass(String start, String length, @RequestParam(value = "pageSize", required = true, defaultValue = "10") String pageSize, @RequestParam(value = "pageIndex", required = true, defaultValue = "1") String pageIndex) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
        if (ToolUtil.isEmpty(respar)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
        int pagesize = respar.getIntValue("pagesize");
        int pageindex = respar.getIntValue("pageindex");
        ps.putIfAbsent("used_userid", this.getUserId());
        String sql = zcService.buildQueryResAllGetdatalSql(ps);
        String sqlcnt = "select count(1) value from (" + sql + ") tab";
        int count = db.uniqueRecord(sqlcnt).getInteger("value");
        JSONObject retrunObject = new JSONObject();
        retrunObject.put("iTotalRecords", count);
        retrunObject.put("iTotalDisplayRecords", count);
        retrunObject.put("success", true);
        retrunObject.put("code", 200);
        retrunObject.put("data", ConvertUtil.OtherJSONObjectToFastJSONArray(
                db.query(DbUtil.getDBPageSql(db.getDBType(), sql, pagesize, pageindex)).toJsonArrayWithJsonObject()));
        return R.clearAttachDirect(retrunObject);
    }
}
