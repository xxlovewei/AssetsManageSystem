package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年11月23日 下午10:07:25
 * @Description: TODO
 */
@Service
public class WxUserService extends BaseService {

	public ResData existUserByOpenId(String open_id) {
		String sql = "select open_id,user_id,pwd from sys_user_info where open_id=?";
		Rcd rs = db.uniqueRecord(sql, open_id);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		}else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}
}
