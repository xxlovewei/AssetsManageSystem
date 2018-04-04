package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.annotion.Acl;
import com.dt.core.cache.CacheSupportImpl;
import com.dt.core.cache.ThreadTaskHelper;
import com.dt.core.common.base.R;
import com.dt.module.base.service.CacheService;
import com.dt.module.base.service.SystemService;

/**
 * @author: algernonking
 * @date: 2017年11月7日 下午8:19:58
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class SystemController {
	@Autowired
	SystemService systemService;

	@Autowired
	CacheService cacheService;

	@RequestMapping(value = "/system/getOnlineSession.do")
	@ResponseBody
	@Acl(info = "查询在线session", value = Acl.ACL_DENY)
	public R getOnlineSession() {
		return systemService.queryOnLineSession();
	}

	@RequestMapping(value = "/system/queryMsg.do")
	@ResponseBody
	@Acl(info = "查询消息", value = Acl.ACL_ALLOW)
	public R queryMsg() {
		return systemService.queryMsg();
	}

	@RequestMapping(value = "/system/queryCacheName.do")
	@ResponseBody
	@Acl(info = "查询CacheName", value = Acl.ACL_DENY)
	public R queryCustomizedEhCacheCacheManagerCaches() {
		return cacheService.queryCacheCacheManagerCaches();
	}

	@RequestMapping(value = "/system/queryCacheKeys.do")
	@ResponseBody
	@Acl(info = "查询CacheName", value = Acl.ACL_DENY)
	public R queryCacheKeys(String cache) {
		return cacheService.queryCustomizedEhCacheCacheManagerCacheKeys(cache);
	}

	@RequestMapping(value = "/system/removeCacheKey.do")
	@ResponseBody
	@Acl(info = "删除CacheKey", value = Acl.ACL_DENY)
	public R removeCacheKey(String cache, String key) {
		return cacheService.removeCacheKey(cache, key);
	}

	@Autowired
	private CacheSupportImpl cacheSupportImpl;

	@RequestMapping(value = "/system/refreshCache.do")
	@ResponseBody
	@Acl(info = "删除CacheKey", value = Acl.ACL_DENY)
	public R refreshCache(String key, String cache) {
		ThreadTaskHelper.run(new Runnable() {
			@Override
			public void run() {
				cacheSupportImpl.refreshCacheByKey(cache, key);
			}
		});
		return R.SUCCESS_OPER();
	}

}
