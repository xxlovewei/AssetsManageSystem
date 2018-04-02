package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.dt.core.cache.CustomizedEhCacheCache;
import com.dt.core.cache.CustomizedEhCacheCacheManager;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.DateTimeKit;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

/**
 * @author: algernonking
 * @date: 2018年3月30日 下午6:06:12
 * @Description: TODO
 */
@Service
public class EhCacheService {
	private static CacheManager cacheManager = null;

	public static CacheManager initCacheManager() {
		try {
			if (cacheManager == null)
				cacheManager = CacheManager.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheManager;
	}

	public R removeCacheKey(String cache, String key) {
		if (ToolUtil.isOneEmpty(cache, key)) {
			return R.FAILURE_NO_DATA();
		}
		CustomizedEhCacheCache c = new CustomizedEhCacheCache(initCacheManager().getEhcache(cache));
		c.evict(key);
		return R.SUCCESS_OPER();
	}

	public R queryCustomizedEhCacheCacheManagerCacheKeys(String cache) {
		if (ToolUtil.isEmpty(cache)) {
			return R.FAILURE_NO_DATA();
		}

		JSONArray res = new JSONArray();
		CustomizedEhCacheCache c = new CustomizedEhCacheCache(initCacheManager().getEhcache(cache));
		for (int i = 0; i < c.getAllKeys().size(); i++) {
			// 捕捉瞬间key失效报错问题
			try {
				Element el = c.getKey(c.getAllKeys().get(i).toString());
				if (!ToolUtil.isEmpty(el)) {
					JSONObject e = new JSONObject();
					e.put("key", c.getAllKeys().get(i));
					e.put("hit", el.getHitCount());
					e.put("ctime",
							DateTimeKit.format(new Date(el.getCreationTime()), DateTimeKit.NORM_DATETIME_PATTERN));
					e.put("accesstime",
							DateTimeKit.format(new Date(el.getLastAccessTime()), DateTimeKit.NORM_DATETIME_PATTERN));
					e.put("ttl", el.getTimeToLive());
					e.put("tti", el.getTimeToIdle());
					res.add(e);
				}
			} catch (Exception e) {
				System.out.println("not a number");
			}
		}
		return R.SUCCESS_OPER(res);
	}

	public R queryCustomizedEhCacheCacheManagerCaches() {
		JSONArray res = new JSONArray();
		ArrayList<String> cachenames = CustomizedEhCacheCacheManager.cachenames;
		for (int i = 0; i < cachenames.size(); i++) {
			JSONObject e = new JSONObject();
			e.put("id", cachenames.get(i));
			e.put("name", cachenames.get(i));
			res.add(e);
		}
		return R.SUCCESS_OPER(res);
	}

	@SuppressWarnings("static-access")
	public static CacheManager initCacheManager(String path) {
		try {
			if (cacheManager == null) {
				cacheManager = CacheManager.getInstance().create(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheManager;
	}

}
