package com.dt.core.common.wx.ps.entity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.wx.ps.service.WeiXX509TrustManager;
import com.dt.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年9月1日 下午4:57:30
 * @Description: TODO
 */
public class WxApp {
	private String appid = "";
	private String name = "";
	private String secret = "";
	private String accesstoken = "";
	private Long lastaccesstoken = 0L;
	@SuppressWarnings("unused")
	private String type = "";
	private final static String access_token_ps_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=#<APPID>#&secret=#<SECRET>#";
	private static Logger _log = LoggerFactory.getLogger(WxApp.class);

	public void SetAppiSecret() {
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getAccesstoken() {
		_log.info("name:" + name + ",appid:" + appid);
		Long curTime = Calendar.getInstance().getTimeInMillis();
		if (ToolUtil.isEmpty(accesstoken) || curTime - lastaccesstoken > 6000) {
			String requestUrl = access_token_ps_url.replaceAll("#<APPID>#", appid).replaceAll("#<SECRET>#", secret);
			_log.info("request:" + requestUrl);
			JSONObject res = httpRequest(requestUrl, "GET", null);
			if (res.containsKey("access_token")) {
				accesstoken = res.getString("access_token");
				lastaccesstoken = curTime;
			}
		}
		return accesstoken;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
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
			// System.out.println(buffer.toString());
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			// log.error("https request error:{}", e);
		}
		return jsonObject;
	}
}
