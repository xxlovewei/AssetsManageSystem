package com.dt.core.common.annotion.impl;

import java.util.List;

import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import com.dt.core.common.annotion.Req;
import com.dt.core.common.annotion.Res;

public class ResReqResolver extends AbstractMessageConverterMethodProcessor {
	public ResReqResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Req.class);
	}
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object arg = readWithMessageConverters(webRequest, parameter, parameter.getGenericParameterType());
		String name = Conventions.getVariableNameForParameter(parameter);
		WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
		if (arg != null) {
			validateIfApplicable(binder, parameter);
			if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
				throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
			}
		}
		mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
		return arg;
	}
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.getMethodAnnotation(Res.class) != null;
	}
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);
		System.out.println("returnType:"+returnType);
		System.out.println("returnValue:"+returnValue);
		
		writeWithMessageConverters(returnValue, returnType, webRequest);
	}
}
