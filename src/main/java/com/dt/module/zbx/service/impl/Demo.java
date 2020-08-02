package com.dt.module.zbx.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Demo {

    public static void main(String[] args) throws Exception {


        ZabbixApi zabbixApi = new DefaultZabbixApi("http://47.92.240.43:15211//api_jsonrpc.php");
        zabbixApi.init();
        boolean login = zabbixApi.login("admin", "zabbix");

        String curtimestr = Long.toString(System.currentTimeMillis() / 1000L);
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(30, ChronoUnit.DAYS);
        Long stime = now.atZone(ZoneOffset.UTC).toEpochSecond();
        String stimestr = Long.toString(stime);
        Request request = RequestBuilder.newBuilder().method("hostgroup.create")
                .paramEntry("name", "extend")
                .build();

        System.out.println(zabbixApi.call(request));
    }
}
