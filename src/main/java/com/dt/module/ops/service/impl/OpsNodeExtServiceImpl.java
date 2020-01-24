package com.dt.module.ops.service.impl;


/** 
 * @author: algernonking
 * @date: Jan 24, 2020 2:02:52 PM 
 * @Description: TODO 
 */
public class OpsNodeExtServiceImpl {

	public static String sql = "select\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.runenv and dict_id = 'sysenv' )   sysenvstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.syslevel and dict_id = 'syslevel' ) syslevelstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.busitype and dict_id = 'systype' ) systypestr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.loc and dict_id = 'sysloc' ) syslocstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.os and dict_id = 'sysos' ) sysosstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.osdtl and dict_id = 'sysosdtl' ) sysosdtlstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.db and dict_id = 'sysdb' ) sysdbstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.dbdtl and dict_id = 'sysdbdtl' ) sysdbdtlstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.execenv and dict_id = 'sysexecenv' ) sysexecenvstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.monitor and dict_id = 'sysmonitor' ) sysmonitorstr,\n"
			+ "(select name from sys_dict_item where dr='0' and dict_item_id=t.pwdstrategy and dict_id = 'syspwdstrategy' ) syspwdstrategystr,\n"
			+ "t.*\n" + "from ops_node t where dr=0 ";
}

