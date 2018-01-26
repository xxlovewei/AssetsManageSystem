package com.dt.tool.util.exception;

import com.dt.tool.util.support.StrKit;

/** 
 * @author: algernonking
 * @date: 2018年1月26日 上午9:43:52 
 * @Description: TODO 
 */
public class UtilException extends RuntimeException{
	private static final long serialVersionUID = 8247610319171014183L;

	public UtilException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}
	
	public UtilException(String message) {
		super(message);
	}
	
	public UtilException(String messageTemplate, Object... params) {
	 
		super(StrKit.format(messageTemplate, params));
	}
	
	public UtilException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public UtilException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrKit.format(messageTemplate, params), throwable);
	}
}

