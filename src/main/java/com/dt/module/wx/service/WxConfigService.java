package com.dt.module.wx.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.wx.ps.entity.WxApp;

/**
 * @author: jinjie
 * @date: 2018年3月22日 上午8:42:07
 * @Description: TODO
 */
@Service
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class WxConfigService extends BaseService {

	@Value("${wx.appId}")
	private String appId;

	@Value("${wx.secret}")
	private String secret;
	private static Logger _log = LoggerFactory.getLogger(WxConfigService.class);

	/**
	 * @Description:微信公众号获取配置
	 */
	public R queryWxConfig(String url) {
		return queryWxConfig(appId, secret, url);
	}

	/**
	 * @Description:微信公众号获取配置,从数据库中
	 */
	public R queryWxConfigByIdFromDb(String appId, String url) {
		return R.FAILURE("未实现");
	}

	public R queryWxToken(String appId, String secret, boolean ifnew) {
		JSONObject r = new JSONObject();
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret="
				+ secret;
		String token = "";
		if (ifnew) {
			JSONObject json = WxApp.httpRequest(url, "GET", null);
			token = json.getString("access_token");
			_log.info("access_token(new):" + token);
		}
		r.put("access_token", token);
		_log.info("access_token:" + token);
		return R.SUCCESS_OPER(r);
	}

	/**
	 * @Description:微信公众号获取配置
	 */
	private R queryWxConfig(String appId, String secret, String locurl) {
		_log.info("queryWxConfig:" + appId);
		Map<String, Object> ret = new HashMap<String, Object>();
		String requestUrl = locurl;
		String access_token = "";
		String jsapi_ticket = "";
		String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
		String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
		String url = "";
		R tokenr = queryWxToken(appId, secret, true);
		if (tokenr.isSuccess()) {
			access_token = tokenr.queryDataToJSONObject().getString("access_token");
			url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
			JSONObject json = WxApp.httpRequest(url, "GET", null);
			if (json != null) {
				jsapi_ticket = json.getString("ticket");
			}
		} else {
			return tokenr;
		}

		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		_log.info("jsapi_ticket:" + jsapi_ticket);
		String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ requestUrl;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(sign.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("appId", appId);
		ret.put("timestamp", timestamp);
		ret.put("nonceStr", nonceStr);
		ret.put("signature", signature);
		JSONObject r = new JSONObject();
		r.put("signature", ret.get("signature"));
		r.put("appId", ret.get("appId"));
		r.put("nonceStr", ret.get("nonceStr"));
		r.put("timestamp", ret.get("timestamp"));
		_log.info(r.toJSONString());
		return R.SUCCESS_OPER(r);

	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;

	}
}
