package com.dt.module.zc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowDataService extends BaseService {

    @Autowired
    SysUfloProcessService sysUfloProcessService;

    public R queryFlowDataByBusId(String uuid) {
        JSONObject res = new JSONObject();
        Rcd rs = db.uniqueRecord("select * from sys_process_data where dr='0' and busid=?", uuid);
        if (ToolUtil.isNotEmpty(rs)) {
            res = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
            R r = sysUfloProcessService.loadProcessTaskinfo(rs.getString("processinstanceid"));
            res.put("taskinfodata", r.queryDataToJSONArray());
        }
        return R.SUCCESS_OPER(res);
    }

    public R queryFlowDataByBusid(String uuid) {
        Rcd rs = db.uniqueRecord("select * from sys_process_data where dr='0' and busid=?", uuid);
        return R.SUCCESS_OPER(rs.toJsonObject());
    }

    public R queryFlowTaskInfoByBusid(String uuid) {
        JSONObject res = new JSONObject();
        Rcd rs = db.uniqueRecord("select * from sys_process_data where dr='0' and busid=?", uuid);
        if (ToolUtil.isNotEmpty(rs)) {
            res = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
            R r = sysUfloProcessService.loadProcessTaskinfo(rs.getString("processinstanceid"));
            res.put("taskinfodata", r.queryDataToJSONArray());
        }
        return R.SUCCESS_OPER(res);
    }
}
