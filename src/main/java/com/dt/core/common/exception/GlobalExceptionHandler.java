package com.dt.core.common.exception;

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
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * @Description:捕获所有Controller中@RequestMapping注解的方法执行过程中抛出的Exception
	 */
	private static Logger _log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public JSONObject handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(ExceptionUtils.getFullStackTrace(ex));
		if (HttpKit.isAjax(request)) {
			_log.info("source from ajax!");
			String msg = ExceptionUtils.getRootCauseMessage(ex);
			if (ToolUtil.isEmpty(msg)) {
				msg = "Server is error!";
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", false);
			jsonObject.put("message", msg);
			return jsonObject;
		} else {
			_log.info("source from others!");
			return new JSONObject();
		}
	}
}
