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
	public static String TYPE_XML = "xml";
	public String type = R.TYPE_JSON;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private Boolean clearAttach = false;
	private int code = BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode();

	public Boolean getClearAttach() {
		return clearAttach;
	}

	public void setClearAttach(Boolean clearAttach) {
		this.clearAttach = clearAttach;
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

	private String message = BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage();

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

	private Object data;

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

	public String getDataToString() {
		return data.toString();
	}

	public String asJsonStr() {
		Object obj = asJson();
		if (obj instanceof JSONObject) {
			return ((JSONObject) (obj)).toJSONString();
		} else if (obj instanceof JSONArray) {
			return ((JSONArray) (obj)).toJSONString();
		}
		return obj.toString();
	}

	/**
	 * @Description:返回JSONObject或JSONArray
	 */
	public Object asJson() {
		if (clearAttach) {
			if (data instanceof org.json.JSONArray) {
				return JSONArray.parseArray(((org.json.JSONArray) (data)).toString());
			} else if (data instanceof org.json.JSONObject) {
				return JSONObject.parseObject(((org.json.JSONObject) (data)).toString());
			} else if (data instanceof JSONObject || data instanceof JSONArray) {
				return data;
			} else {
				return data;
			}
		} else {
			JSONObject json = new JSONObject();
			json.put("code", code);
			json.put("success", success);
			json.put("message", message);
			if (data instanceof org.json.JSONObject) {
				json.put("data", JSONObject.parseObject(((org.json.JSONObject) (data)).toString()));
			} else if (data instanceof org.json.JSONArray) {
				json.put("data", JSONArray.parseArray(((org.json.JSONArray) (data)).toString()));
			} else {
				json.put("data", data);
			}
			return json;
		}
	}

	/**
	 * @Description:返回JSONObject,clearAttach无效
	 */
	public JSONObject asJsonObject() {
		Object obj = asJson();
		if (obj instanceof JSONObject) {
			return (JSONObject) obj;
		} else {
			// obj是JSONArray
			JSONObject json = new JSONObject();
			json.put("code", code);
			json.put("success", success);
			json.put("message", message);
			if (obj instanceof JSONArray) {
				json.put("data", ((JSONArray) (obj)));
			}
			return json;
		}
	}

	/**
	 * @Description:返回JSONObject,clearAttach无视
	 */
	public JSONArray asJsonArray() {
		return getDataToJSONArray();
	}

	@Override
	public String toString() {
		asJsonStr();
		if (type.equals(TYPE_JSON)) {
			return asJsonStr();
		} else {
			return asJsonStr();
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
