package com.dt.core.common.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 作者 Lank
 * @version 创建时间：2017年8月1日 下午12:24:52 类说明
 */
public class BaseResult implements java.io.Serializable {
	private static final long serialVersionUID = -1950834705338436194L;

	public static JSONObject JSON_RETURN_NOT_LOGIN() {
		JSONObject r = new JSONObject();
		r.put("success", false);
		r.put("code", BaseCodeMsgEnum.SUCCESS_DEF_MSG.getCode());
		r.put("message", BaseCodeMsgEnum.USER_NOT_LOGIN.getMessage());
		return r;
	}

	public static JSONObject JSON_RETURN_NO_PERMITION() {
		JSONObject r = new JSONObject();
		r.put("success", false);
		r.put("code", BaseCodeMsgEnum.PERMITION_NOT.getCode());
		r.put("message", BaseCodeMsgEnum.PERMITION_NOT.getMessage());
		return r;
	}
}
