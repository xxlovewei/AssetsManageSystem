package com.dt.module.zbx.controller;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.zbx.service.impl.HostGroupService;
import com.dt.module.zbx.service.impl.HostService;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/zbx/hostgroup")
public class HostGroupController extends BaseController {

    @Autowired
    HostGroupService hostGroupService;

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/getAllHostGroups.do")
    public R getAllHostGroups(String groups) {
        return hostGroupService.getAllHostGroups(groups);
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/getAllHostGroupsList.do")
    public R getAllHostGroupsList(String groups) {
        return hostGroupService.getAllHostGroupsList(groups);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/getAllHostGroupsListFormatTree.do")
    public R getAllHostGroupsListFormatTree(String groups) {
        return hostGroupService.getAllHostGroupsListFormatTree(groups);
    }

}
