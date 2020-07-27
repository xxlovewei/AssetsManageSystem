package com.dt.module.zbx.controller;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.module.ops.service.IOpsNodeService;
import com.dt.module.zbx.service.impl.DashBoardService;
import com.dt.module.zbx.service.impl.HostService;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zbx/host")
public class HostController extends BaseController {

    @Autowired
    HostService hostService;

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/hostList.do")
    public R hostList(String hosts) {
        return hostService.hostList(hosts);
    }


}
