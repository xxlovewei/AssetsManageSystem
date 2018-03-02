package com.dt.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseCodeMsgEnum;
import com.dt.core.common.base.BaseCommon;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger _log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * @Description:捕获所有Controller中@RequestMapping注解的方法执行过程中抛出的Exception
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public JSONObject handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		BaseCommon.print(ExceptionUtils.getFullStackTrace(ex));
		_log.info("source from ajax!");
		String msg = ExceptionUtils.getRootCauseMessage(ex) == null ? "" : ExceptionUtils.getRootCauseMessage(ex);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", false);
		jsonObject.put("code", BaseCodeMsgEnum.SYSTEM_CONF_ERROR.getCode());
		jsonObject.put("message", msg);
		return jsonObject;
	}
}
