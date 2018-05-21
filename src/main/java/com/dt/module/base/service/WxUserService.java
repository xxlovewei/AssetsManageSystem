package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年11月23日 下午10:07:25
 * @Description: TODO
 */
@Service
public class WxUserService extends BaseService {

	public R queryUserByOpenId(String open_id) {
		if (ToolUtil.isEmpty(open_id)) {
			return R.FAILURE("open_id不存在");
		}
		String sql = "select * from sys_user_info where deleted='N' and open_id=?";
		Rcd rs = db.uniqueRecord(sql, open_id);
		if (ToolUtil.isEmpty(rs)) {
			return R.FAILURE_NO_DATA();
		} else {
			return R.SUCCESS_OPER(rs.toJsonObject());
		}
	}

}
