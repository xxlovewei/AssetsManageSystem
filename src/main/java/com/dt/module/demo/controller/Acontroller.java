package com.dt.module.demo.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.module.db.SCM;
import com.dt.module.demo.service.AService;
import com.dt.module.wx.service.WxService;

/**
 * @author: jinjie
 * @date: 2018年3月9日 上午8:24:34
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class Acontroller extends BaseController {

	@Autowired
	private AService aService;

	@Autowired
	public SCM scm = null;

	public static Map<String, Object> getWxConfig(String locurl) {
		Map<String, Object> ret = new HashMap<String, Object>();

		String appId = "wx8fc3340c90ec5d53"; // 必填，公众号的唯一标识
		String secret = "f6cea94ef73b19db97320a36b3fb36b4";

		String requestUrl = locurl;
		String access_token = "";
		String jsapi_ticket = "";
		String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
		String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret="
				+ secret;
		JSONObject json = WxService.httpRequest(url, "GET", null);
		BaseCommon.print(json.toJSONString());
		if (json != null) {
			// 要注意，access_token需要缓存
			access_token = json.getString("access_token");
			url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
			json = WxService.httpRequest(url, "GET", null);
			if (json != null) {
				jsapi_ticket = json.getString("ticket");
			}
		}
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		BaseCommon.print(jsapi_ticket);
		String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ requestUrl;
		BaseCommon.print(sign);
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
		BaseCommon.print(ret.toString());
		return ret;
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

	@RequestMapping("/demo/getJsTicket.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_API, info = "demo")
	@ResponseBody
	public R weixin(String url) {
		BaseCommon.print(url);
		Map<String, Object> ret = getWxConfig(url);

		JSONObject r = new JSONObject();
		r.put("signature", ret.get("signature"));
		r.put("appId", ret.get("appId"));
		r.put("nonceStr", ret.get("nonceStr"));
		r.put("timestamp", ret.get("timestamp"));
		return R.SUCCESS_OPER(r);
	}

	@RequestMapping("/demo/db.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_API, info = "demo")
	@ResponseBody
	@Transactional
	public R scm() {
		return aService.test();

	}

	@RequestMapping("/demo/scmdb2.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_API, info = "demo")
	@ResponseBody
	@Transactional("transactionManagerScm")
	public R scm2() {
		Insert ins1 = new Insert("dt_a");
		ins1.set("id", "a");
		scm.execute(ins1);

		Insert ins2 = new Insert("dt_a");
		ins2.set("id2", "a");
		scm.execute(ins2);

		return R.SUCCESS_OPER(scm.query("select * from T where rownum<10").toJsonArrayWithJsonObject());
	}

	@RequestMapping("/demo/thy.html")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW, info = "demo")
	public String thy() {
		BaseCommon.print("thy.html");
		return "thy.html";

	}

	@RequestMapping("/demo/jsp.jsp")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW, info = "demo")
	public String jsp() {
		BaseCommon.print("jsp.jsp");
		return jsp("jsp");
	}

	@RequestMapping("/demo/api4.do")
	@Acl(value = Acl.ACL_DENY, type = Acl.TYPE_VIEW, info = "demo")
	@ResponseBody
	public R api4() {
		BaseCommon.print("api");
		return R.SUCCESS();

	}

	@RequestMapping("/demo/api.do")
	@Acl(value = Acl.ACL_ALLOW, type = Acl.TYPE_VIEW, info = "demo")
	@ResponseBody
	public R api() {
		BaseCommon.print("api");
		return R.SUCCESS();

	}

	@RequestMapping("/demo/api2.do")
	@Acl(value = Acl.ACL_ALLOW, info = "demo")
	@ResponseBody
	public R api2() {
		BaseCommon.print("api");
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/demo/api3.do")
	@Acl(value = Acl.ACL_ALLOW, info = "demo")
	@ResponseBody
	public String api23() {
		BaseCommon.print("api3");
		return R.SUCCESS_OPER().asJsonStr();
	}
}
