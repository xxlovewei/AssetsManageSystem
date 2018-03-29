package com.dt.module.wx.service;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.R;
import com.dt.core.wx.ps.entity.WxApp;

/**
 * @author: jinjie
 * @date: 2018年3月29日 上午11:51:41
 * @Description: TODO
 */
@Service
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class WxService {

	@Value("${wx.appId}")
	public String appIdconf;

	@Value("${wx.secret}")
	public String secretconf;

	private static Logger _log = LoggerFactory.getLogger(WxService.class);

	/**
	 * @Description:微信公众号获取配置
	 */
	private static Map<String, AccessToken> tokens = new HashMap<String, AccessToken>();
	private static Map<String, AccessTicket> tickets = new HashMap<String, AccessTicket>();

	public R queryAccessToken() {
		return queryWxToken(appIdconf, secretconf, false);
	}

	public R queryWxToken(String appId, String secret, boolean ifnew) {
		JSONObject r = new JSONObject();
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret="
				+ secret;
		String token = "";
		// 无效
		if (!ifnew) {
			// 从缓存中获取
			AccessToken at = tokens.get(appId);
			if (at == null || (System.currentTimeMillis() - at.getCtime()) / 1000 > at.getExpiresIn() * 0.9) {
				// 无效,后续重新请求
				ifnew = true;
			} else {
				token = at.getToken();
				_log.info("access_token(mem):" + at.getToken());
				r.put("access_token", token);
				return R.SUCCESS_OPER(r);
			}
		}

		if (ifnew) {
			JSONObject json = WxApp.httpRequest(url, "GET", null);
			token = json.getString("access_token");
			AccessToken t = new AccessToken();
			t.setCtime(System.currentTimeMillis());
			t.setExpiresIn(json.getIntValue("expires_in"));
			t.setToken(token);
			tokens.put(appId, t);
			_log.info("access_token(new):" + token);
		}

		r.put("access_token", token);
		return R.SUCCESS_OPER(r);
	}

	public R queryMapTickets() {
		JSONArray res = new JSONArray();
		Set<String> set = tickets.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			AccessTicket value = (AccessTicket) tickets.get(key);
			JSONObject e = new JSONObject();
			e.put("access_token", key);
			e.put("data", value.toJsonObject());
			res.add(e);
		}
		return R.SUCCESS_OPER(res);
	}

	public R queryWxTicket(String access_token, boolean ifnew) {
		JSONObject r = new JSONObject();
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
		String ticket = "";
		// 无效
		if (!ifnew) {
			// 从缓存中获取
			AccessTicket at = tickets.get(access_token);
			if (at == null || (System.currentTimeMillis() - at.getCtime()) / 1000 > at.getExpiresIn() * 0.9) {
				// 无效,后续重新请求
				ifnew = true;
			} else {
				ticket = at.getTicket();
				_log.info("access_ticket(mem):" + at.getTicket());
				r.put("access_ticket", ticket);
				return R.SUCCESS_OPER(r);
			}
		}

		if (ifnew) {
			JSONObject json = WxApp.httpRequest(url, "GET", null);
			ticket = json.getString("ticket");
			AccessTicket t = new AccessTicket();
			t.setCtime(System.currentTimeMillis());
			t.setExpiresIn(json.getIntValue("expires_in"));
			t.setTicket(ticket);
			tickets.put(access_token, t);
			_log.info("access_ticket(new):" + ticket);
		}

		r.put("access_ticket", ticket);
		return R.SUCCESS_OPER(r);
	}

	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;

	}

}
