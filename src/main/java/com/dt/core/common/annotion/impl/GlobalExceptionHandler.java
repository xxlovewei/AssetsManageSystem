package com.dt.core.common.annotion.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.util.ToolUtil;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * @Description:捕获所有Controller中@RequestMapping注解的方法执行过程中抛出的Exception
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public JSONObject handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		// 未完成,后期需要判断是否是ajax请求
		
		
		System.out.println(ExceptionUtils.getFullStackTrace(ex));
		String msg = ExceptionUtils.getRootCauseMessage(ex);
		if (ToolUtil.isEmpty(msg)) {
			msg = "Server is error!";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", false);
		jsonObject.put("message", msg);
		return jsonObject;
	}
}
