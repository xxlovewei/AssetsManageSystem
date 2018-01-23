package com.dt.core.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.dt.module.db.DB;

/**
 * @author: algernonking
 * @date: 2017年8月26日 上午8:08:57
 * @Description: TODO
 */
public class BaseSC {
	@Autowired
	public DB db = null;
}
