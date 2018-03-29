package com.dt.core.cache;
/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.concurrent.Callable;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

import com.dt.core.tool.lang.SpringContextUtil;

/**
 * {@link Cache} implementation on top of an {@link Ehcache} instance.
 *
 * @author Costin Leau
 * @author Juergen Hoeller
 * @author Stephane Nicoll
 * @since 3.1
 */
public class CustomizedEhCacheCache implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedEhCacheCache.class);

	private final Ehcache cache;

	// 格式cacahename#5#2
	// #expiredtime 0注解层面上永未不过期(最近还要看其他配置),当有值是,优先级最高
	// #refreshtime 0离快过期时刷新数据
	private long expiredtime = 0;
	private long refreshtime = 0;

	private CacheSupport getCacheSupport() {

		return SpringContextUtil.getBean(CacheSupport.class);

	}

	/**
	 * Create an {@link CustomizedEhCacheCache} instance.
	 * 
	 * @param ehcache
	 *            backing Ehcache instance
	 */
	public CustomizedEhCacheCache(Ehcache ehcache) {

		Assert.notNull(ehcache, "Ehcache must not be null");
		Status status = ehcache.getStatus();
		Assert.isTrue(Status.STATUS_ALIVE.equals(status),
				"An 'alive' Ehcache is required - current cache is " + status.toString());
		this.cache = ehcache;
	}

	public CustomizedEhCacheCache(Ehcache ehcache, long et, long rt) {
		expiredtime = et;
		refreshtime = rt;
		Assert.notNull(ehcache, "Ehcache must not be null");
		Status status = ehcache.getStatus();
		Assert.isTrue(Status.STATUS_ALIVE.equals(status),
				"An 'alive' Ehcache is required - current cache is " + status.toString());
		this.cache = ehcache;

	}

	@Override
	public final String getName() {
		return this.cache.getName();
	}

	@Override
	public final Ehcache getNativeCache() {
		return this.cache;
	}

	@Override
	public ValueWrapper get(Object key) {
		Element element = lookup(key);
		return toValueWrapper(element);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		Element element = lookup(key);
		if (element != null) {
			return (T) element.getObjectValue();
		} else {
			this.cache.acquireWriteLockOnKey(key);
			try {
				element = lookup(key); // One more attempt with the write lock
				if (element != null) {
					return (T) element.getObjectValue();
				} else {
					return loadValue(key, valueLoader);
				}
			} finally {
				this.cache.releaseWriteLockOnKey(key);
			}
		}

	}

	private <T> T loadValue(Object key, Callable<T> valueLoader) {
		T value;
		try {
			value = valueLoader.call();
		} catch (Throwable ex) {
			throw new ValueRetrievalException(key, valueLoader, ex);
		}
		put(key, value);
		return value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Class<T> type) {
		Element element = this.cache.get(key);
		Object value = (element != null ? element.getObjectValue() : null);
		if (value != null && type != null && !type.isInstance(value)) {
			throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
		}
		return (T) value;
	}

	@Override
	public void put(Object key, Object value) {
		this.cache.put(new Element(key, value));
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Element existingElement = this.cache.putIfAbsent(new Element(key, value));
		return toValueWrapper(existingElement);
	}

	@Override
	public void evict(Object key) {
		this.cache.remove(key);
	}

	@Override
	public void clear() {
		this.cache.removeAll();
	}

	private Element lookup(Object key) {
		System.out.println(this.cache.get(key));
		return this.cache.get(key);
	}

	private ValueWrapper toValueWrapper(Element element) {

		if (element == null) {
			return null;
		}

		element.setTimeToLive((int) expiredtime);
		Long expired = (element.getExpirationTime() - element.getLastAccessTime()) / 1000;
		System.out.println(expired + "," + refreshtime);
		// 判断是否要刷新
		if (refreshtime > 0 && expired != null && expired > 0 && expired <= refreshtime) {
			ThreadTaskHelper.run(new Runnable() {
				@Override
				public void run() {
					// 重新加载数据
					logger.info("refresh " + cache.getName() + " key:" + element.getKey());
					CustomizedEhCacheCache.this.getCacheSupport().refreshCacheByKey(cache.getName(),
							element.getKey().toString());
				}
			});
		}
		return new SimpleValueWrapper(element.getObjectValue());
	}

}
