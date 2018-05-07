package com.dt.module.wx.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.wx.util.WeiXX509TrustManager;

/**
 * @author: jinjie
 * @date: 2018年3月29日 上午11:51:41
 * @Description: TODO
 */
@Service
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class WxService extends BaseService {

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
				if (ToolUtil.isEmpty(token)) {
					return R.FAILURE_NO_DATA();
				}
				r.put("access_token", token);
				return R.SUCCESS_OPER(r);
			}
		}

		if (ifnew) {
			JSONObject json = httpRequest(url, "GET", null);
			token = json.getString("access_token");
			AccessToken t = new AccessToken();
			t.setCtime(System.currentTimeMillis());
			t.setExpiresIn(json.getIntValue("expires_in"));
			t.setToken(token);
			tokens.put(appId, t);
			_log.info("access_token(new):" + token);
		}
		if (ToolUtil.isEmpty(token)) {
			return R.FAILURE_NO_DATA();
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
			JSONObject json = httpRequest(url, "GET", null);
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

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	public boolean checkSignature(String signature, String timestamp, String nonce) {
		R tr = queryAccessToken();
		if (tr.isFailed()) {
			return false;
		}
		String token = tr.queryDataToJSONObject().getString("access_token");
		System.out.println(token);
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	public R queryMenu() {
		R trs = queryAccessToken();
		if (trs.isFailed()) {
			return R.FAILURE();
		}
		String token = trs.queryDataToJSONObject().getString("access_token");
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token;
		JSONObject json = httpRequest(url, "GET", null);
		return R.SUCCESS_OPER(json);

	}

	public R queryWxApps() {
		return R.SUCCESS_OPER(db.query("select * from wx_apps where dr=0").toJsonArrayWithJsonObject());
	}

	public R queryUserInfo(String open_id) {
		R trs = queryAccessToken();
		if (trs.isFailed()) {
			return R.FAILURE();
		}
		String token = trs.queryDataToJSONObject().getString("access_token");
		String url = " https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + open_id;
		JSONObject json = httpRequest(url, "GET", null);
		return R.SUCCESS_OPER(json);

	}

	public R saveWxApp(TypedHashMap<String, Object> ps) {
		Update me = new Update("wx_apps");
		me.set("dr", 0);
		me.setIf("name", ps.getString("name"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("app_id", ps.getString("app_id"));
		me.setIf("menu", ps.getString("menu"));
		me.where().and("id=?", ps.getString("id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R deleteWxAppById(String id) {
		Delete me = new Delete("wx_apps");
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();

	}

	public R queryWxAppById(String id) {

		Rcd rs = db.uniqueRecord("select * from wx_apps where id=? and dr=0", id);
		if (rs != null) {
			return R.SUCCESS_OPER(rs.toJsonObject());
		}
		return R.SUCCESS_OPER();

	}

	public R queryWxAppByAppId(String appid) {

		Rcd rs = db.uniqueRecord("select * from wx_apps where app_id=? and dr=0", appid);
		if (rs != null) {
			return R.SUCCESS_OPER(rs.toJsonObject());
		}
		return R.SUCCESS_OPER();

	}

	/*
	 * 当前模式只支持配置一个Appid
	 */
	public R syncMenuFromWxWithConf() {
		return syncMenuFromWx(appIdconf, secretconf);
	}

	public R syncMenuFromWxWithId(String id) {
		Rcd rs = db.uniqueRecord("select * from wx_apps where id=?", id);
		if (rs == null || rs.getString("app_id") == null) {
			return R.FAILURE_NO_DATA();
		}
		String appid = rs.getString("app_id");
		if (appid.equals(appIdconf)) {
			return syncMenuFromWx(appIdconf, secretconf);
		} else {
			return R.FAILURE("appid配置不匹配");
		}

	}

	public R syncMenuFromWx(String appid, String sec) {
		R tr = queryWxToken(appid, sec, false);
		if (tr.isFailed()) {
			return tr;
		}
		String token = tr.queryDataToJSONObject().getString("access_token");
		_log.info("token:" + token);
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token;
		JSONObject json = httpRequest(url, "GET", null);
		JSONObject menu = json.getJSONObject("menu");
		if (ToolUtil.isEmpty(menu)) {
			return R.FAILURE_NO_DATA();
		}
		_log.info("menu:" + menu.toJSONString());
		Update me = new Update("wx_apps");
		me.set("menu", menu.toJSONString());
		me.where().and("app_id=?", appid);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R createMenuToWx(String id) {

		Rcd rs = db.uniqueRecord("select * from wx_apps where id=?", id);

		if (rs == null || rs.getString("app_id") == null) {
			return R.FAILURE_NO_DATA();
		}
		String appid = rs.getString("app_id");
		if (!appid.equals(appIdconf)) {
			return R.FAILURE("appid配置不匹配");
		}

		String menu = rs.getString("menu");
		if (ToolUtil.isEmpty(menu)) {
			return R.FAILURE_NO_DATA();
		}

		JSONObject mobj = JSONObject.parseObject(menu);
		if (ToolUtil.isEmpty(mobj)) {
			return R.FAILURE_NO_DATA();
		}
		R trs = queryAccessToken();
		if (trs.isFailed()) {
			return R.FAILURE();
		}
		String token = trs.queryDataToJSONObject().getString("access_token");
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
		_log.info("to create menu,url:" + url + ",body:" + menu);
		JSONObject json = httpRequest(url, "POST", menu);
		_log.info("result:" + json.toJSONString());
		return R.SUCCESS_OPER();
	}

	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WeiXX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			// log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WxService s = new WxService();
		s.syncMenuFromWx("wx8fc3340c90ec5d53", "f6cea94ef73b19db97320a36b3fb36b4");

	}
}
