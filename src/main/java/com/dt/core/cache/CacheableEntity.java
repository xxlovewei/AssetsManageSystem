package com.dt.core.cache;

/**
 * @author: jinjie
 * @date: 2018年4月3日 下午1:14:45
 * @Description: TODO
 */
public class CacheableEntity {

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String value = "";
	private String key = "";

	public CacheableEntity(String value_in, String key_in) {
		value = value_in;
		key = key_in;
	}

}
