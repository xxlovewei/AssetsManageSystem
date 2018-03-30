package com.dt.module.base.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.dt.core.cache.CustomizedEhCacheCache;
import com.dt.core.cache.CustomizedEhCacheCacheManager;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;

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
			System.out.println("cacheManager" + cacheManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheManager;
	}

	public R queryCustomizedEhCacheCacheManagerCacheKeys(String cache) {
		if (ToolUtil.isEmpty(cache)) {
			return R.FAILURE_NO_DATA();
		}
		JSONArray res = new JSONArray();
		CustomizedEhCacheCache c = new CustomizedEhCacheCache(initCacheManager().getEhcache(cache));
		for (int i = 0; i < c.getAllKeys().size(); i++) {
			Element el = c.getKey(c.getAllKeys().get(i).toString());
			JSONObject e = new JSONObject();
			e.put("key", c.getAllKeys().get(i));
			e.put("hit", el.getHitCount());
			e.put("ctime", el.getCreationTime());
			e.put("accesstime", el.getLastAccessTime());
			e.put("ttl", el.getTimeToLive());
			res.add(e);
		}
		return R.SUCCESS_OPER(res);
	}

	public R queryCustomizedEhCacheCacheManagerCaches() {
		JSONArray res = new JSONArray();
		ArrayList<String> cachenames = CustomizedEhCacheCacheManager.cachenames;
		for (int i = 0; i < cachenames.size(); i++) {
			JSONObject e = new JSONObject();
			e.put("value", cachenames.get(i));
			res.add(e);
		}
		//System.currentTimeMillis()
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
