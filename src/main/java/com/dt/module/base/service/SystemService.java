package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.ResData;

/**
 * @author: algernonking
 * @date: 2017年8月7日 上午8:25:06
 * @Description: 系统服务
 */
@Service
public class SystemService extends BaseService {

	public ResData queryOnLineSession() {
		String sql = "select a.*,b.user_name,b.user_type,b.name,b.nickname from sys_session a left join sys_user_info b on a.user_id=b.user_id order by lastaccess desc";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

}
