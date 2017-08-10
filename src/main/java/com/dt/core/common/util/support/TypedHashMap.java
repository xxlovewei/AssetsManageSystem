package com.dt.core.common.util.support;

import java.util.Date;
import java.util.HashMap;

import com.dt.core.common.dao.DataParser;

public class TypedHashMap<K, V> extends HashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataParser dp = new DataParser();

	public TypedHashMap() {
	}
	public TypedHashMap(HashMap<K, V> map) {
		if (map == null)
			return;
		for (K key : map.keySet()) {
			V value = map.get(key);
			this.put(key, value);
		}
	}
	public Integer getInt(V key) {
		return dp.parseInteger(this.get(key));
	}
	public Integer getInt(V key, Integer value) {
		if (this.containsKey(key)) {
			return dp.parseInteger(this.get(key));
		} else {
			return value;
		}
	}
	public Integer[] getIntArray(V key) {
		try {
			return (Integer[]) this.get(key);
		} catch (Exception e) {
			return null;
		}
	}
	public String getString(V key) {
		return dp.parseString(this.get(key));
	}
	public String getString(V key, String defaultStr) {
		if (this.containsKey(key)) {
			return dp.parseString(this.get(key));
		} else {
			return defaultStr;
		}
	}
	public String[] getStringArray(V key) {
		try {
			return (String[]) this.get(key);
		} catch (Exception e) {
			return null;
		}
	}
	public Float getFloat(V key) {
		return dp.parseFloat(this.get(key));
	}
	public Double getDouble(V key) {
		return dp.parseDouble(this.get(key));
	}
	public Date getDate(V key) {
		return dp.parseDate(this.get(key));
	}
	public Boolean getBoolean(V key) {
		return dp.parseBoolean(this.get(key));
	}
	public Long getLong(V key) {
		return dp.parseLong(this.get(key));
	}
	public Short getShort(V key) {
		return dp.parseShort(this.get(key));
	}
	/**
	 * 名值对转换
	 */
	@SuppressWarnings("rawtypes")
	public static TypedHashMap toMap(Object... psarr) {
		TypedHashMap<String, Object> ps = new TypedHashMap<String, Object>();
		for (int i = 0; i < psarr.length; i++) {
			String p = psarr[i] + "";
			i++;
			if (i >= psarr.length) {
				ps.put(p, null);
				break;
			}
			Object v = psarr[i];
			ps.put(p, v);
		}
		return ps;
	}
}
