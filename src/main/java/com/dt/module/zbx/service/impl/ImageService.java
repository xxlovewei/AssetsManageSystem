package com.dt.module.zbx.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends BaseService {

    @Autowired
    ZabbixUtilService zabbixUtilService;

    public R getOne(String itemid, String start, String end) {

        return R.SUCCESS_OPER();
    }
}
