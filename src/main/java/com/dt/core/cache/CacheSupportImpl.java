package com.dt.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

import com.dt.core.tool.util.ToolUtil;
import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 手动刷新缓存实现类
 */
@Component
public class CacheSupportImpl implements CacheSupport, InvocationRegistry {

	private static final Logger logger = LoggerFactory.getLogger(CacheSupportImpl.class);

	/**
	 * 记录容器与所有执行方法信息
	 */
	private Map<String, Set<CachedInvocation>> cacheToInvocationsMap;

	@Autowired
	private CacheManager cacheManager;

	private void refreshCache(CachedInvocation invocation, String cacheName) {

		boolean invocationSuccess;
		Object computed = null;
		try {
			computed = invoke(invocation);
			invocationSuccess = true;
		} catch (Exception ex) {
			invocationSuccess = false;
		}
		if (invocationSuccess) {
			if (cacheToInvocationsMap.get(cacheName) != null) {
				cacheManager.getCache(cacheName).put(invocation.getKey(), computed);
			}
		}
	}

	private Object invoke(CachedInvocation invocation)
			throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		final MethodInvoker invoker = new MethodInvoker();
		invoker.setTargetObject(invocation.getTargetBean());
		invoker.setArguments(invocation.getArguments());
		invoker.setTargetMethod(invocation.getTargetMethod().getName());
		invoker.prepare();
		return invoker.invoke();
	}

	@PostConstruct
	public void initialize() {
		cacheToInvocationsMap = new ConcurrentHashMap<String, Set<CachedInvocation>>(
				cacheManager.getCacheNames().size());
		for (final String cacheName : cacheManager.getCacheNames()) {
			cacheToInvocationsMap.put(cacheName, new CopyOnWriteArraySet<CachedInvocation>());
		}
	}

	@Override
	public void registerInvocation(Object targetBean, Method targetMethod, Object[] arguments,
			Set<String> annotatedCacheNames) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : arguments) {
			sb.append(obj.toString());
		}
		Object key = sb.toString();
		final CachedInvocation invocation = new CachedInvocation(key, targetBean, targetMethod, arguments);
		for (final String cacheName : annotatedCacheNames) {
			String[] cacheParams = cacheName.split("#");
			String realCacheName = cacheParams[0];
			if (!cacheToInvocationsMap.containsKey(realCacheName)) {
				this.initialize();
			}
			cacheToInvocationsMap.get(realCacheName).add(invocation);
		}
	}

	@Override
	public void removeCacheByKey(String cacheName, String cachekey) {
		logger.info("removeCacheByKey:" + cacheName + ",cacheKey:" + cachekey);
		if (cacheToInvocationsMap.get(cacheName) != null && ToolUtil.isNotEmpty(cachekey)) {
			Set<CachedInvocation> sets = cacheToInvocationsMap.get(cacheName);
			ArrayList<CachedInvocation> d = new ArrayList<CachedInvocation>();

			for (final CachedInvocation invocation : sets) {
				if (invocation.getKey().toString().equals(cachekey)) {
					d.add(invocation);
				}
			}
			for (CachedInvocation invocation : d) {
				sets.remove(invocation);
			}
		} else {
			logger.info("Cache name:" + cacheName + " not exists");
		}
	}

	private void removeCacheByKeyLeft(String cacheName, String cachekey) {
		logger.info("removeCacheByKey:" + cacheName + ",cacheKey:" + cachekey);
		if (cacheToInvocationsMap.get(cacheName) != null && ToolUtil.isNotEmpty(cachekey)) {
			Set<CachedInvocation> sets = cacheToInvocationsMap.get(cacheName);
			ArrayList<CachedInvocation> d = new ArrayList<CachedInvocation>();
			for (final CachedInvocation invocation : sets) {
				if (invocation.getKey().toString().equals(cachekey)) {
					d.add(invocation);
				}
			}
			if (d.size() >= 2) {
				for (int i = 0; i < d.size() - 1; i++) {
					sets.remove(d.get(i));
				}
			}
		} else {
			logger.info("Cache name:" + cacheName + " not exists");
		}
	}

	@Override
	public void refreshCache(String cacheName) {
		logger.info("refreshCache" + cacheName);
		this.refreshCacheByKey(cacheName, null);
	}

	@Override
	public void refreshCacheByKey(String cacheName, String cacheKey) {
		logger.info("refreshCacheName:" + cacheName + ",cacheKey:" + cacheKey);
		if (cacheToInvocationsMap.get(cacheName) != null && ToolUtil.isNotEmpty(cacheKey)) {
			removeCacheByKeyLeft(cacheName, cacheKey);
			for (final CachedInvocation invocation : cacheToInvocationsMap.get(cacheName)) {
				if (invocation.getKey().toString().equals(cacheKey)) {
					logger.info("Action refreshCache.");
					refreshCache(invocation, cacheName);
				}
			}
		} else {
			logger.info("Cache name:" + cacheName + " not exists");
		}
	}

}