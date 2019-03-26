package com.dt.module.zb.controller;

import com.alibaba.fastjson.JSONObject;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;

/**
 * @author: algernonking
 * @date: Mar 23, 2019 10:33:17 PM
 * @Description: TODO
 */
public class Demo {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ZabbixUtil z=new ZabbixUtil("Admin", "oracleoracle","http://121.43.168.125/api_jsonrpc.php");
			System.out.println(z.getHostList());
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		DefaultZabbixApi zabbixApi = new DefaultZabbixApi("http://121.43.168.125/api_jsonrpc.php");
		 
		// init方法中创建CloseableHttpClient客户端
		zabbixApi.init();
		// 进行权限验证
		boolean loginResult = zabbixApi.login("Admin", "oracleoracle");
		if (!loginResult) {
			System.out.println("login fail");
		}else {
			System.out.println("login success");
			Request request = RequestBuilder.newBuilder().method("trigger.get")
	                .paramEntry("output", new String[]{"description", "priority", "lastchange"})
	                .paramEntry("selectHosts", new String[]{"host", "name", "hostid"})
	                .paramEntry("selectDependencies", "extend")
	                .paramEntry("expandData", "host")
	                .paramEntry("skipDependent", "1")
	                .paramEntry("monitored", "1")
	                .paramEntry("active", "1")
	                .paramEntry("expandDescription", "1")
	                .paramEntry("sortfield", "priority")
	                .paramEntry("sortorder", "DESC")
	  
	                .build();
 
			
			JSONObject resJson = zabbixApi.call(request);
			System.out.println(resJson.toJSONString());
			
			Request request2 = RequestBuilder.newBuilder().method("host.get").build();
			
			JSONObject resJson2 = zabbixApi.call(request2);
			System.out.println(resJson2.toJSONString());
		}
	}

}
