package com.dt.module.zb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.db.ZB;

/**
 * @author: algernonking
 * @date: Aug 3, 2019 8:41:00 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ReportData extends BaseController {

	@Autowired
	ZB zb;

	@RequestMapping("/zb/queryAllHostByGroupID.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryAllHostByGroupID(String groupid) {
		if (ToolUtil.isEmpty(groupid)) {
			
			Rcd urs=db.uniqueRecord("select * from sys_params where dr='0' and id='zb_node_host' ");
			if(urs==null) {
				return R.FAILURE_NO_DATA();
			}
			groupid=urs.getString("value");
		}

		String sql = "\n" + "select\n" + " h.host,\n" + " h.name,\n"
				+ "(select max(a.value) from history_uint a, items b where a.clock>unix_timestamp(date_sub(now(), interval 2 hour) )  and b.hostid= t.hostid and a.itemid=b.itemid and key_='vm.memory.size[total]' ORDER BY a.clock DESC LIMIT 3 ) total_mem_v,\n"
				+ "(select max(a.value) from history_uint a, items b where a.clock>unix_timestamp(date_sub(now(), interval 2 hour) )  and b.hostid= t.hostid and a.itemid=b.itemid and key_='system.swap.size[,total]' ORDER BY a.clock DESC LIMIT 3 ) total_swapmem_v,\n"
				+ "(select max(a.value) from history_str a, items b  where a.clock>unix_timestamp(date_sub(now(), interval 12 hour) ) and b.hostid= t.hostid and a.itemid=b.itemid and key_='system.hostname' ORDER BY a.clock DESC LIMIT 3 ) system_hostname_v,\n"
				+ "(select max(a.value) from history_uint a, items b where a.clock>unix_timestamp(date_sub(now(), interval 12 hour) ) and b.hostid= t.hostid and a.itemid=b.itemid and key_='system.uptime' ORDER BY a.clock DESC LIMIT 3 ) system_uptime_v,\n"
				+ " (select max(a.value) from history_str a, items b where a.clock>unix_timestamp(date_sub(now(), interval 2 hour) ) and b.hostid= t.hostid and a.itemid=b.itemid and key_='system.uname' ORDER BY a.clock DESC LIMIT 3 ) system_name_v,\n"
				+ " (select max(a.value) from history_str a, items b where a.clock>unix_timestamp(date_sub(now(), interval 2 hour) ) and b.hostid= t.hostid and a.itemid=b.itemid and key_='agent.version' ORDER BY a.clock DESC LIMIT 3 ) agent_version_v,\n"
				+ " t.* from hosts_templates t,hosts h where h.hostid=t.hostid and t.templateid=?\n";

		RcdSet rs = zb.query(sql, groupid);

		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());

	}
}
