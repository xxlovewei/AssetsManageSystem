package com.dt.core.common.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 作者 Lank
 * @version 创建时间：2017年8月1日 下午12:24:52 类说明
 */
public class BaseResult {
	public static JSONObject JSON_RETURN_NOT_LOGIN() {
		JSONObject r = new JSONObject();
		r.put("success", false);
		r.put("code", "299");
		r.put("message", "you are not login.");
		return r;
	}
	public static JSONObject JSON_RETURN_NO_PERMITION() {
		JSONObject r = new JSONObject();
		r.put("success", false);
		r.put("message", "you are no permission");
		return r;
	}
}
