package com.dt.core.common.annotion.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseResult;

public class ResData extends BaseResult {
	public static String TYPE_JSON = "json";
	public static String TYPE_TEXT = "text";
	public static String TYPE_HTML = "html";
	public String TYPE_VALUE = ResData.TYPE_JSON;
	private Boolean clearStatus = false;

	public Boolean getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(Boolean clearStatus) {
		this.clearStatus = clearStatus;
	}

	private boolean success;

	public boolean isSuccess() {
		return success;
	}
	public boolean isFailed() {
		return !success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	private String message;

	public String getMessage() {
		return this.message;
	}
	public ResData setMessage(String message) {
		this.message = message;
		return this;
	}
	public ResData(boolean success) {
		this.success = success;
	}
	public ResData() {
		this.success = true;
	}

	protected Object data;

	public ResData setData(Object data) {
		this.data = data;
		return this;
	}
	public Object getData() {
		return data;
	}
	public static ResData SUCCESS(String message, Object data) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setMessage(message);
		r.setData(data);
		return r;
	}
	public static ResData SUCCESS(Object data) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setMessage("操作成功");
		r.setData(data);
		return r;
	}
	public static ResData SUCCESS(String message) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setMessage(message);
		return r;
	}
	public static ResData SUCCESS() {
		ResData r = new ResData();
		r.setSuccess(true);
		return r;
	}
	public static ResData FAILURE(String message, Object data) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage(message);
		r.setData(data);
		return r;
	}
	public static ResData FAILURE(String message) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage(message);
		return r;
	}
	public static ResData FAILURE() {
		ResData r = new ResData();
		r.setSuccess(false);
		return r;
	}
	public static ResData FAILURE_OPER() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage("操作失败");
		return r;
	}
	public static ResData FAILURE_NODATA() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage("无数据");
		return r;
	}
	public static ResData FAILURE_ERRREQ_PARAMS() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage("参数不正确");
		return r;
	}
	public static ResData FAILURE_SYS_PARAMS() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage("系统配置错误");
		return r;
	}
	public static ResData FAILURE_OPER(Object data) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setData(data);
		r.setMessage("操作失败");
		return r;
	}
	public static ResData FAILURE_SAVE() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setMessage("保存失败");
		return r;
	}
	public static ResData SUCCESS_OPER(Object data) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setData(data);
		r.setMessage("操作成功");
		return r;
	}
	public static ResData SUCCESS_OPER() {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setMessage("操作成功");
		return r;
	}
	public static ResData SUCCESS_SAVE() {
		ResData r = new ResData();
		r.setMessage("保存成功");
		r.setSuccess(true);
		return r;
	}

	public JSONArray getDataToJSONArray() {
		if (data instanceof org.json.JSONArray) {
			return JSONArray.parseArray(((org.json.JSONArray) (data)).toString());
		} else if (data instanceof JSONArray) {
			return (JSONArray) (data);
		} else {
			return new JSONArray();
		}
	}
	public JSONObject getDataToJSONObject() {
		if (data instanceof org.json.JSONObject) {
			return JSONObject.parseObject(((org.json.JSONObject) (data)).toString());
		} else if (data instanceof JSONObject) {
			return (JSONObject) (data);
		} else {
			return new JSONObject();
		}
	}
	public String asJson() {
		if (clearStatus) {
			if (data instanceof ResData) {
				return ((ResData) data).asJson();
			} else if (data instanceof org.json.JSONArray) {
				return ((org.json.JSONArray) (data)).toString();
			} else if (data instanceof org.json.JSONObject) {
				return ((org.json.JSONObject) (data)).toString();
			} else if (data instanceof JSONObject) {
				return ((JSONObject) data).toJSONString();
			} else if (data instanceof JSONArray) {
				return ((JSONArray) data).toJSONString();
			} else {
				return data.toString();
			}
		} else {
			JSONObject json = new JSONObject();
			json.put("success", success);
			json.put("message", message);
			if (data instanceof ResData) {
				json.put("data", ((ResData) data).asJson());
			} else if (data instanceof org.json.JSONObject) {
				json.put("data", JSONObject.parseObject(((org.json.JSONObject) (data)).toString()));
			} else if (data instanceof org.json.JSONArray) {
				json.put("data", JSONArray.parseArray(((org.json.JSONArray) (data)).toString()));
			} else {
				json.put("data", data);
			}
			return json.toJSONString();
		}
	}
}
