package com.dt.module.base.content.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;

/**
 * @author: algernonking
 * @date: 2017年8月11日 下午12:49:18
 * @Description: TODO
 */
@Service
public class ContentRootCategoryService extends BaseService {
	/**
	 * @Description: 查询主节点
	 */
	public R queryRootCategory() {
		return R.SUCCESS_OPER(db.query("select * from ct_category_root where deleted='N' order by od").toJsonArrayWithJsonObject());
	}
}
