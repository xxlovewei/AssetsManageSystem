package com.dt.module.base.service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;

/**
 * @author: jinjie
 * @date: 2018年3月20日 下午2:09:06
 * @Description: TODO
 */
public class MonitorService extends BaseService {

	public R sayHello() {
		return R.SUCCESS("hello");
	}

	public R getInfo() {
		return R.SUCCESS();
	}

}
