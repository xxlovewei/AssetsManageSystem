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
	private String type = R.TYPE_JSON;
	private Boolean clearAttach = false;
	private int code = BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode();
	private String message = BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage();
	private boolean success;
	private Object data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getClearAttach() {
		return clearAttach;
	}

	public void setClearAttach(Boolean clearAttach) {
		this.clearAttach = clearAttach;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean isFailed() {
		return !success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

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

	public R(boolean success, int code, String msg, Object data) {
		this.success = success;
		this.code = code;
		this.message = msg;
		this.data = data;
	}

	public R setData(Object data) {
		this.data = data;
		return this;
	}

	public Object getData() {
		return data;
	}

	/************************* 操作成功 ***************************/
	public static R SUCCESS(String message, Object data) {
		return new R(true, 0, message, data);
	}

	public static R SUCCESS(String message) {
		return new R(true, 0, message, null);
	}

	public static R SUCCESS(String message, int code, Object data) {
		return new R(true, code, message, data);
	}

	public static R SUCCESS() {
		return new R(true, BaseCodeMsgEnum.SUCCESS_DEF_MSG.getCode(), BaseCodeMsgEnum.SUCCESS_DEF_MSG.getMessage(),
				null);
	}

	public static R SUCCESS_OPER(Object data) {
		return new R(true, BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode(), BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage(),
				data);
	}

	public static R SUCCESS_OPER() {
		return new R(true, BaseCodeMsgEnum.SUCCESS_OPER_MSG.getCode(), BaseCodeMsgEnum.SUCCESS_OPER_MSG.getMessage(),
				null);
	}

	public static R SUCCESS_SAVE() {
		return new R(true, BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getCode(), BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getMessage(),
				null);
	}


	public static R SUCCESS_SAVE(Object data) {
		return new R(true, BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getCode(), BaseCodeMsgEnum.SUCCESS_SAVE_MSG.getMessage(),
				data);
	}
	
	/************************* 操作失败 ***************************/
	public static R FAILURE(String message, Object data) {
		return new R(false, BaseCodeMsgEnum.FAILED_DEF_MSG.getCode(), message, data);
	}

	public static R FAILURE(String message) {
		return new R(false, BaseCodeMsgEnum.FAILED_DEF_MSG.getCode(), message, null);
	}

	public static R FAILURE(String message, int code, Object data) {
		return new R(false, code, message, data);
	}

	public static R FAILURE() {
		return new R(false, BaseCodeMsgEnum.FAILED_DEF_MSG.getCode(), BaseCodeMsgEnum.FAILED_DEF_MSG.getMessage(),
				null);
	}

	public static R FAILURE_OPER() {
		return new R(false, BaseCodeMsgEnum.FAILED_OPER_MSG.getCode(), BaseCodeMsgEnum.FAILED_OPER_MSG.getMessage(),
				null);
	}

	public static R FAILURE_USER_QUERY() {
		return new R(false, BaseCodeMsgEnum.USER_QUERY_FAILED.getCode(), BaseCodeMsgEnum.USER_QUERY_FAILED.getMessage(),
				null);
	}

	public static R FAILURE_USER_NOT_EXISTED() {
		return new R(false, BaseCodeMsgEnum.USER_NOT_EXISTED.getCode(), BaseCodeMsgEnum.USER_NOT_EXISTED.getMessage(),
				null);
	}

	public static R FAILURE_NO_DATA() {
		return new R(false, BaseCodeMsgEnum.FAILED_NO_DATA_MSG.getCode(),
				BaseCodeMsgEnum.FAILED_NO_DATA_MSG.getMessage(), null);

	}

	public static R FAILURE_ERRREQ_PARAMS() {
		return new R(false, BaseCodeMsgEnum.REQ_PARAM_ERROR.getCode(), BaseCodeMsgEnum.REQ_PARAM_ERROR.getMessage(),
				null);
	}

	public static R FAILURE_SYS_PARAMS() {
		return new R(false, BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getCode(), BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getMessage(),
				null);
	}

	public static R FAILURE_OPER(Object data) {
		return new R(false, BaseCodeMsgEnum.FAILED_OPER_MSG.getCode(), BaseCodeMsgEnum.FAILED_OPER_MSG.getMessage(),
				null);
	}

	public static R FAILURE_SAVE() {
		return new R(false, BaseCodeMsgEnum.FAILED_SAVE_MSG.getCode(), BaseCodeMsgEnum.FAILED_SAVE_MSG.getMessage(),
				null);
	}
	
	public static R FAILURE_SAVE(Object data) {
		return new R(false, BaseCodeMsgEnum.FAILED_SAVE_MSG.getCode(), BaseCodeMsgEnum.FAILED_SAVE_MSG.getMessage(),
				data);
	}

	public static R FAILURE_NOT_LOGIN() {
		return new R(false, BaseCodeMsgEnum.USER_NOT_LOGIN.getCode(), BaseCodeMsgEnum.USER_NOT_LOGIN.getMessage(),
				null);
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
			// ob肯定是JSONArray
			JSONObject json = new JSONObject();
			json.put("code", code);
			json.put("success", success);
			json.put("message", message);
			if (obj instanceof JSONArray) {
				json.put("data", ((JSONArray) (obj)));
			}else{
				//未识别,则将原来的数据放回去
				json.put("data", data);
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
