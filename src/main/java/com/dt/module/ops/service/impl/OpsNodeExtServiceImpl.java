package com.dt.module.ops.service.impl;


/** 
 * @author: algernonking
 * @date: Jan 24, 2020 2:02:52 PM 
 * @Description: TODO 
 */
public class OpsNodeExtServiceImpl {

	public static String sql = "select\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.runenv and dict_id = 'sysenv' )   riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.syslevel and dict_id = 'syslevel' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.busitype and dict_id = 'systype' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.loc and dict_id = 'sysloc' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.os and dict_id = 'sysos' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.db and dict_id = 'sysdb' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.dbdtl and dict_id = 'sysdbdtl' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.execenv and dict_id = 'sysexecenv' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.monitor and dict_id = 'sysmonitor' ) riskstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.monitor and dict_id = 'syspwdstrategy' ) riskstr,\n"
			+ "t.*\n" + "from ops_node t where dr=0 ";
}

