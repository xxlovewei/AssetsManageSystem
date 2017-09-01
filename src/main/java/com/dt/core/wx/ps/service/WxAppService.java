package com.dt.core.wx.ps.service;

import java.util.HashMap;
import com.dt.core.wx.ps.entity.WxApp;

/**
 * @author: algernonking
 * @date: 2017年9月1日 下午4:56:10
 * @Description: TODO
 */
public class WxAppService {
	private static HashMap<String, WxApp> apps = new HashMap<String, WxApp>();

	public void init() {
	}
	public static void main(String[] args) {
		WxApp a = new WxApp();
		a.setAppid("wx8fc3340c90ec5d53");
		a.setSecret("f6cea94ef73b19db97320a36b3fb36b4");
		a.setName("测试公众号A");
		apps.put(a.getAppid(), a);
		WxApp b = new WxApp();
		b.setAppid("wx8fc3340c90ec5d53");
		b.setSecret("f6cea94ef73b19db97320a36b3fb36b4");
		b.setName("测试公众号B");
		apps.put(b.getAppid(), b);
		
		System.out.println(a.getAccesstoken());
	}
	//
	// WxApp appa=new WxApp();
	// appa.setAppid("1");
}
