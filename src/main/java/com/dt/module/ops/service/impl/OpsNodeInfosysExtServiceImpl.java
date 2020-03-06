package com.dt.module.ops.service.impl;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-03-06
 */
@Service
public class OpsNodeInfosysExtServiceImpl  extends BaseService {

	public static String sql = "select\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.type and dict_id = 'sysinfotype' ) typestr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.opsmethod and dict_id = 'sysinfoops' )   opsmethodstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.devmethod and dict_id = 'sysinfodev' ) devmethodstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.grade and dict_id = 'sysinfograde' ) gradestr,\n"
			+ "t.*\n" + "from ops_node_infosys t where dr=0 ";


	public R selecList(String search) {
		String sql = OpsNodeInfosysExtServiceImpl.sql;
		if (ToolUtil.isNotEmpty(search)) {
		}
		sql = sql + " order by name";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	
	
	
}
