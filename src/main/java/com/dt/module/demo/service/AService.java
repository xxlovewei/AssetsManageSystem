package com.dt.module.demo.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;

;

/**
 * @author: jinjie
 * @date: 2018年3月16日 上午8:31:37
 * @Description: TODO
 */
@Service
public class AService extends BaseService {

	public R tt() {

		return R.FAILURE_OPER();
	}

	public R test() {
		return R.SUCCESS_OPER(R.SUCCESS_OPER(db.query("select 1 from dual").toJsonArrayWithJsonObject()));
	}
}
