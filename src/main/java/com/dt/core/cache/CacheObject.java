package com.dt.core.cache;

import java.lang.reflect.Method;

/**
 * @author: jinjie
 * @date: 2018年4月3日 下午1:19:37
 * @Description: TODO
 */
public class CacheObject {

	private Object invokedBean;
	private Method invokedMethod;
	private Object[] invocationArguments;
	private CacheableEntity CacheableEntity;

	public CacheObject(Object invokedBean, Method invokedMethod, Object[] invocationArguments,
			CacheableEntity CacheableEntity) {
		this.invokedBean = invokedBean;
		this.invokedMethod = invokedMethod;
		this.invocationArguments = invocationArguments;
		this.CacheableEntity = CacheableEntity;
	}

	public Object getInvokedBean() {
		return invokedBean;
	}

	public void setInvokedBean(Object invokedBean) {
		this.invokedBean = invokedBean;
	}

	public Method getInvokedMethod() {
		return invokedMethod;
	}

	public void setInvokedMethod(Method invokedMethod) {
		this.invokedMethod = invokedMethod;
	}

	public Object[] getInvocationArguments() {
		return invocationArguments;
	}

	public void setInvocationArguments(Object[] invocationArguments) {
		this.invocationArguments = invocationArguments;
	}

	public CacheableEntity getCacheableEntity() {
		return CacheableEntity;
	}

	public void setCacheableEntity(CacheableEntity cacheableEntity) {
		CacheableEntity = cacheableEntity;
	}
}
