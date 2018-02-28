package com.dt.core.annotion.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseCodeMsgEnum;
import com.dt.core.common.base.BaseResult;

public class ResData extends BaseResult {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	public static String TYPE_JSON = "json";
	public static String TYPE_TEXT = "text";
	public static String TYPE_HTML = "html";
	public String TYPE_VALUE = ResData.TYPE_JSON;
	private Boolean clearStatus = false;
	private int code = 0;

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

	/************************* 操作成功 ***************************/

	public static ResData SUCCESS(String message, Object data) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setCode(0);
		r.setMessage(message);
		r.setData(data);
		return r;
	}

	public static ResData SUCCESS(String message) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setCode(0);
		r.setMessage(message);
		return r;
	}
	public static ResData SUCCESS(String message,int code,Object data) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setCode(code);
		r.setData(data);
		r.setMessage(message);
		return r;
	}

	public static ResData SUCCESS() {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setCode(BaseCodeMsgEnum.SUCCESS_DEF_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_DEF_MSG.getMessage());
		return r;
	}

	public static ResData SUCCESS_OPER(Object data) {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setData(data);
		r.setCode(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage());
		return r;
	}

	public static ResData SUCCESS_OPER() {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setCode(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage());
		return r;
	}

	public static ResData SUCCESS_SAVE() {
		ResData r = new ResData();
		r.setSuccess(true);
		r.setCode(BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getMessage());
		return r;
	}

	/************************* 操作失败 ***************************/
	public static ResData FAILURE(String message, Object data) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_DEF_MSG.getCode());
		r.setMessage(message);
		r.setData(data);
		return r;
	}

	public static ResData FAILURE(String message) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_DEF_MSG.getCode());
		r.setMessage(message);
		return r;
	}
	
	public static ResData FAILURE(String message,int code,Object data) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(code);
		r.setData(data);
		r.setMessage(message);
		return r;
	}

	public static ResData FAILURE() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_DEF_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_DEF_MSG.getMessage());
		return r;
	}

	public static ResData FAILURE_OPER() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_OPER_MSG.getMessage());
		return r;
	}

	public static ResData FAILURE_USER_QUERY() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.USER_QUERY_FAILED.getCode());
		r.setMessage(BaseCodeMsgEnum.USER_QUERY_FAILED.getMessage());
		return r;
	}

	public static ResData FAILURE_USER_NOT_EXISTED() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.USER_NOT_EXISTED.getCode());
		r.setMessage(BaseCodeMsgEnum.USER_NOT_EXISTED.getMessage());
		return r;
	}

	
	public static ResData FAILURE_NODATA() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_NO_DATA_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_NO_DATA_MSG.getMessage());
		return r;
	}

	public static ResData FAILURE_ERRREQ_PARAMS() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.REQ_PARAM_ERROR.getCode());
		r.setMessage(BaseCodeMsgEnum.REQ_PARAM_ERROR.getMessage());
		return r;
	}

	public static ResData FAILURE_SYS_PARAMS() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getCode());
		r.setMessage(BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getMessage());
		return r;
	}

	public static ResData FAILURE_OPER(Object data) {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setData(data);
		r.setCode(BaseCodeMsgEnum.FAILED_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_OPER_MSG.getMessage());
		return r;
	}

	public static ResData FAILURE_SAVE() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_SAVE_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_SAVE_MSG.getMessage());
		return r;
	}

	public static ResData FAILURE_NOT_LOGIN() {
		ResData r = new ResData();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.USER_NOT_LOGIN.getCode());
		r.setMessage(BaseCodeMsgEnum.USER_NOT_LOGIN.getMessage());
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
 
	public String asJsonStr() {
		if (clearStatus) {
			if (data instanceof ResData) {
				return ((ResData) data).asJsonStr();
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
			json.put("code", code);
			json.put("success", success);
			json.put("message", message);
			if (data instanceof ResData) {
				json.put("data", ((ResData) data).asJsonStr());
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
	

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
