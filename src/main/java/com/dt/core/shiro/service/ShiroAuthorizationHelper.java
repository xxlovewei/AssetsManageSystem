package com.dt.core.shiro.service;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.common.base.BaseCommon;
import com.dt.core.shiro.ShiroUser;

import net.sf.ehcache.Element;

import org.apache.shiro.cache.ehcache.EhCacheManager;

/**
 * @author Dylan
 * @time 2014年1月8日
 */
public class ShiroAuthorizationHelper {

	/** 
	 *  
	 */
	private static EhCacheManager cacheManager;

	private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationHelper.class);

	/**
	 * 清除用户的授权信息
	 * 
	 * @param username
	 */
	public static String authorizationCache = "authorizationCache";
	public static String passwordRetryCache = "passwordRetryCache";
	public static String authenticationCache = "authenticationCache";

	/* 权限信息 */
	public static void clearAuthorizationInfo(ShiroUser user) {
		Cache<Object, Object> cache = cacheManager.getCache(authorizationCache);
		Object v = cache.get(user);
		cache.remove(user.toString());
	}

	/* 认证信息 */
	public static void clearAuthenticationInfo(ShiroUser user) {
		Cache<Object, Object> cache = cacheManager.getCache(authenticationCache);
		cache.remove(user.id);
	}

	public static void showCache() {

		Cache<Object, Object> cache = cacheManager.getCache(authorizationCache);
		BaseCommon.print(authorizationCache);
		BaseCommon.print("size:" + cache.size());
		for (Object key : cache.keys()) {
			BaseCommon.print("key:" + key + ",val:" + cache.get(key));
		}

		Cache<Object, Object> cache2 = cacheManager.getCache(passwordRetryCache);
		BaseCommon.print(passwordRetryCache);
		BaseCommon.print("size:" + cache2.size());
		for (Object key : cache2.keys()) {
			BaseCommon.print("key:" + key + ",val:" + cache2.get(key));
		}

		Cache<Object, Object> cache3 = cacheManager.getCache(authenticationCache);
		BaseCommon.print(authenticationCache);
		BaseCommon.print("size:" + cache3.size());
		for (Object key : cache3.keys()) {
			BaseCommon.print("key:" + key + ",val:" + cache3.get(key));
		}
	}

	/**
	 * 清除session(认证信息)
	 * 
	 * @param JSESSIONID
	 */
	public static void clearAuthenticationInfo(Serializable JSESSIONID) {
		if (log.isDebugEnabled()) {
			log.debug("clear the session " + JSESSIONID);
		}
		// shiro-activeSessionCache
		// 为shiro自义cache名，该名在org.apache.shiro.session.mgt.eis.CachingSessionDAO抽象类中定义
		// 如果要改变此名，可以将名称注入到sessionDao中，例如注入到CachingSessionDAO的子类EnterpriseCacheSessionDAO类中
		Cache<Object, Object> cache = cacheManager.getCache("shiro-activeSessionCache");
		cache.remove(JSESSIONID);
	}

	public static EhCacheManager getCacheManager() {
		return cacheManager;
	}

	public static void setCacheManager(EhCacheManager cacheManager) {
		ShiroAuthorizationHelper.cacheManager = cacheManager;
	}

}