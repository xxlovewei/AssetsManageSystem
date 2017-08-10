package com.dt.core.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.dt.core.db.DB;

/**
 * @author: algernonking
 * @date: 2017年8月3日 上午6:27:47
 * @Description: TODO
 */
public class BaseService {
	
	@Autowired
	public DB db = null;
	
	
}
