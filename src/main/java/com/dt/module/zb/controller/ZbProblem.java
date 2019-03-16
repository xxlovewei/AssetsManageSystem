package com.dt.module.zb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.db.ZB;

/**
 * @author: algernonking
 * @date: Mar 16, 2019 2:32:12 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ZbProblem extends BaseController {

	@Autowired
	ZB zb;

	@RequestMapping("/zb/queryProblemItems.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProblemItems(String ack) {
		// status:0启用 1停用
		if (ToolUtil.isEmpty(ack)) {
		}
		String sql = "select d.hostid ,e.name,c.* from functions a, triggers b ,problem c ,items d,hosts e\n"
				+ "where a.triggerid=b.triggerid and c.objectid=b.triggerid and a.itemid=d.itemid\n"
				+ "and e.hostid=d.hostid and c.source=0";
		
		return R.SUCCESS_OPER(zb.query(sql).toJsonArrayWithJsonObject());
	}
	
	public static String problemhzsql="select\n" + 
			"  ifnull(t2.warn,0)warn,\n" + 
			"  ifnull(t2.average,0)average,\n" + 
			"  ifnull(t2.high,0)high,\n" + 
			"  ifnull(t2.disaster,0)disaster,\n" + 
			"  h.hostid hostid, h.host,h.name from hosts h left join (\n" + 
			"select hostid,sum(warn)warn,sum(average)average,sum(high)high,sum(disaster)disaster from (\n" + 
			"select\n" + 
			"    CASE severity WHEN 2 THEN cnt ELSE 0 END warn ,\n" + 
			"    CASE severity WHEN 3 THEN cnt ELSE 0 END average ,\n" + 
			"    CASE severity WHEN 4 THEN cnt ELSE 0 END high ,\n" + 
			"    CASE severity WHEN 5 THEN cnt ELSE 0 END disaster ,\n" + 
			"  dchange.*\n" + 
			"from (\n" + 
			"select hostid,severity,count(severity) cnt from (\n" + 
			"select d.hostid,e.name hostname,c.* from functions a, triggers b ,problem c ,items d,hosts e\n" + 
			"where a.triggerid=b.triggerid and c.objectid=b.triggerid and a.itemid=d.itemid\n" + 
			"and e.hostid=d.hostid and c.source=0 and c.acknowledged=0 ) t\n" + 
			"group by hostid,severity ) dchange)hz group by hostid\n" + 
			") t2 on h.hostid=t2.hostid where 1=1   <#HOST#>  \n" + 
			//"and h.hostid in (10265,10270,10269)\n" + 
			"order by 4 desc ,3 desc,2 desc,1 desc" ;
 
	@RequestMapping("/zb/queryProblemHzByHostGroup.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryProblemHzByHostGroup(String gid ) {
		String sql2="";
		if(ToolUtil.isEmpty(gid)) {
			sql2=problemhzsql.replaceAll("<#HOST#>", " and h.host not like 'Template%' and h.host not like '{#%' ");
		}else {
			sql2=problemhzsql.replaceAll("<#HOST#>", " and h.hostid in (select bb.hostid from hosts_templates aa,hosts bb where aa.hostid=bb.hostid and aa.templateid in(select  a.hostid  from hosts a,hosts_groups b where a.hostid=b.hostid and b.groupid="+gid+") )");
		}
		return R.SUCCESS_OPER(zb.query(sql2).toJsonArrayWithJsonObject());
	}
//	Possible values: 
//		0 - event created by a trigger; 
//		3 - internal event.
//
//	Possible values for internal events: 
//	0 - trigger; 
//	4 - item; 
//	5 - LLD rule.

//	Acknowledge state for problem. 
//
//	Possible values: 
//	0 - not acknowledged; 
//	1 - acknowledged.

//	Problem current severity. 
//
//	Possible values: 
//	0 - not classified; 
//	1 - information; 
//	2 - warning; 
//	3 - average; 
//	4 - high; 
//	5 - disaster.
}
