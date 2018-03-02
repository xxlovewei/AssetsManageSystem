package com.dt.core.common.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class R extends BaseResult {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	public static String TYPE_JSON = "json";
	public static String TYPE_TEXT = "text";
	public static String TYPE_HTML = "html";
	public String TYPE_VALUE = R.TYPE_JSON;
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

	public R setMessage(String message) {
		this.message = message;
		return this;
	}

	public R(boolean success) {
		this.success = success;
	}

	public R() {
		this.success = true;
	}

	protected Object data;

	public R setData(Object data) {
		this.data = data;
		return this;
	}

	public Object getData() {
		return data;
	}

	/************************* 操作成功 ***************************/

	public static R SUCCESS(String message, Object data) {
		R r = new R();
		r.setSuccess(true);
		r.setCode(0);
		r.setMessage(message);
		r.setData(data);
		return r;
	}

	public static R SUCCESS(String message) {
		R r = new R();
		r.setSuccess(true);
		r.setCode(0);
		r.setMessage(message);
		return r;
	}

	public static R SUCCESS(String message, int code, Object data) {
		R r = new R();
		r.setSuccess(true);
		r.setCode(code);
		r.setData(data);
		r.setMessage(message);
		return r;
	}

	public static R SUCCESS() {
		R r = new R();
		r.setSuccess(true);
		r.setCode(BaseCodeMsgEnum.SUCCESS_DEF_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_DEF_MSG.getMessage());
		return r;
	}

	public static R SUCCESS_OPER(Object data) {
		R r = new R();
		r.setSuccess(true);
		r.setData(data);
		r.setCode(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage());
		return r;
	}

	public static R SUCCESS_OPER() {
		R r = new R();
		r.setSuccess(true);
		r.setCode(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage());
		return r;
	}

	public static R SUCCESS_SAVE() {
		R r = new R();
		r.setSuccess(true);
		r.setCode(BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getMessage());
		return r;
	}

	/************************* 操作失败 ***************************/
	public static R FAILURE(String message, Object data) {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_DEF_MSG.getCode());
		r.setMessage(message);
		r.setData(data);
		return r;
	}

	public static R FAILURE(String message) {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_DEF_MSG.getCode());
		r.setMessage(message);
		return r;
	}

	public static R FAILURE(String message, int code, Object data) {
		R r = new R();
		r.setSuccess(false);
		r.setCode(code);
		r.setData(data);
		r.setMessage(message);
		return r;
	}

	public static R FAILURE() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_DEF_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_DEF_MSG.getMessage());
		return r;
	}

	public static R FAILURE_OPER() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_OPER_MSG.getMessage());
		return r;
	}

	public static R FAILURE_USER_QUERY() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.USER_QUERY_FAILED.getCode());
		r.setMessage(BaseCodeMsgEnum.USER_QUERY_FAILED.getMessage());
		return r;
	}

	public static R FAILURE_USER_NOT_EXISTED() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.USER_NOT_EXISTED.getCode());
		r.setMessage(BaseCodeMsgEnum.USER_NOT_EXISTED.getMessage());
		return r;
	}

	public static R FAILURE_NODATA() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_NO_DATA_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_NO_DATA_MSG.getMessage());
		return r;
	}

	public static R FAILURE_ERRREQ_PARAMS() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.REQ_PARAM_ERROR.getCode());
		r.setMessage(BaseCodeMsgEnum.REQ_PARAM_ERROR.getMessage());
		return r;
	}

	public static R FAILURE_SYS_PARAMS() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getCode());
		r.setMessage(BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getMessage());
		return r;
	}

	public static R FAILURE_OPER(Object data) {
		R r = new R();
		r.setSuccess(false);
		r.setData(data);
		r.setCode(BaseCodeMsgEnum.FAILED_OPER_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_OPER_MSG.getMessage());
		return r;
	}

	public static R FAILURE_SAVE() {
		R r = new R();
		r.setSuccess(false);
		r.setCode(BaseCodeMsgEnum.FAILED_SAVE_MSG.getCode());
		r.setMessage(BaseCodeMsgEnum.FAILED_SAVE_MSG.getMessage());
		return r;
	}

	public static R FAILURE_NOT_LOGIN() {
		R r = new R();
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
			if (data instanceof R) {
				return ((R) data).asJsonStr();
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
			if (data instanceof R) {
				json.put("data", ((R) data).asJsonStr());
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

	@Override
	public String toString() {
		asJsonStr();
		if (TYPE_VALUE.equals(TYPE_JSON)) {
			return asJsonStr();
		} else {
			return "";
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
