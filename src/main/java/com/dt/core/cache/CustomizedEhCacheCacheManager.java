package com.dt.core.cache;

import java.awt.List;
import java.util.ArrayList;

/*
 * Copyright 2002-2015 the original author or authors.
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

import java.util.Collection;
import java.util.LinkedHashSet;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;

import com.dt.core.tool.util.ToolUtil;

/**
 * CacheManager backed by an EhCache {@link net.sf.ehcache.CacheManager}.
 *
 * @author Costin Leau
 * @author Juergen Hoeller
 * @author Stephane Nicoll
 * @since 3.1
 */
public class CustomizedEhCacheCacheManager extends EhCacheCacheManager {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedEhCacheCacheManager.class);

	private String separator = "#";

	public static ArrayList<String> cachenames = new ArrayList<String>();

	public net.sf.ehcache.CacheManager cacheManager;

	/**
	 * Create a new EhCacheCacheManager, setting the target EhCache CacheManager
	 * through the {@link #setCacheManager} bean property.
	 */
	public CustomizedEhCacheCacheManager() {
	}

	/**
	 * Create a new EhCacheCacheManager for the given backing EhCache CacheManager.
	 * 
	 * @param cacheManager
	 *            the backing EhCache {@link net.sf.ehcache.CacheManager}
	 */
	public CustomizedEhCacheCacheManager(net.sf.ehcache.CacheManager cacheManager_in) {
		cacheManager = cacheManager_in;
	}

	/**
	 * Set the backing EhCache {@link net.sf.ehcache.CacheManager}.
	 */
	public void setCacheManager(net.sf.ehcache.CacheManager cacheManager_in) {
		cacheManager = cacheManager_in;
	}

	/**
	 * Return the backing EhCache {@link net.sf.ehcache.CacheManager}.
	 */
	public net.sf.ehcache.CacheManager getCacheManager() {
		return cacheManager;
	}

	@Override
	public void afterPropertiesSet() {
		if (getCacheManager() == null) {
			setCacheManager(EhCacheManagerUtils.buildCacheManager());
		}
		super.afterPropertiesSet();

	}

	@Override
	protected Collection<Cache> loadCaches() {
		logger.info("loadCaches");
		Status status = getCacheManager().getStatus();
		if (!Status.STATUS_ALIVE.equals(status)) {
			throw new IllegalStateException(
					"An 'alive' EhCache CacheManager is required - current cache is " + status.toString());
		}

		String[] names = getCacheManager().getCacheNames();
		Collection<Cache> caches = new LinkedHashSet<Cache>(names.length);
		for (String name : names) {
			logger.info("name:" + name);
			cachenames.add(name);
			caches.add(new CustomizedEhCacheCache(getCacheManager().getEhcache(name)));
		}
		return caches;
	}

	@Override
	protected Cache getMissingCache(String name) {
		// Check the EhCache cache again (in case the cache was added at
		// runtime)

		long expiredtime = 0;
		long refreshtime = 0;
		String[] cacheParams = name.split(separator);
		String cacheName = cacheParams[0];

		if (ToolUtil.isEmpty(cacheName)) {
			return null;
		}
		if (cacheParams.length > 1) {
			expiredtime = Long.parseLong(cacheParams[1]);
		}
		if (cacheParams.length > 2) {
			refreshtime = Long.parseLong(cacheParams[2]);
		}
		Ehcache ehcache = getCacheManager().getEhcache(cacheName);
		if (ehcache != null) {
			return new CustomizedEhCacheCache(ehcache, expiredtime, refreshtime);
		}
		return null;
	}

}
