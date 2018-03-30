package com.dt.core.cache;

/**
 * @author: algernonking
 * @date: 2018年3月28日 下午7:34:24
 * @Description: TODO
 */
public class CacheConfig {

	// 2小时
	public static final String CACHE_PUBLIC = "public";
	public static final String CACHE_PUBLIC_5_2 = "public#5#2";
	public static final String CACHE_PUBLIC_7200_300 = "public#7200#300";
	public static final String CACHE_PUBLIC_86400_3600 = "public#86400#3600";

	// 用户数据
	public static final String CACHE_USER = "user";
	public static final String CACHE_USER_180_60 = "user#180#60";

	public static final String CACHE_WX_CONF = "wxconf";
	public static final String CACHE_WX_CONF_300_180 = "wxconf#300#180";
}
