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
 * @date: Mar 16, 2019 6:32:28 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ZbOsMetric extends BaseController {

	@Autowired
	ZB zb;

	@RequestMapping("/zb/getCpuUsed.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getCpuUsed(String type) {

		// 输出cpu使用率统计
		String sql = "select t1.*,t3.* from (\n" + "select\n"
				+ "itemid,max(100-value ) cpuused_max,avg(100-value) cpuused_avg\n"
				+ "from history t where 1=1 <#TIME#>  and itemid in\n"
				+ "    (select itemid from items where key_ ='system.cpu.util[,idle]')\n"
				+ "group by itemid) t1,items t2,hosts t3 where t1.itemid=t2.itemid and t3.hostid=t2.hostid";

		if (type.equals("yestoday")) {
			sql = sql.replaceAll("<#TIME#>",
					" and clock>unix_timestamp(CONCAT(DATE_FORMAT(curdate()-1,'%Y-%m-%d'),' 08:00:00')) and clock<unix_timestamp(CONCAT(DATE_FORMAT(curdate(),'%Y-%m-%d'),' 08:00:00')) ");
		} else if (type.equals("monthbefore")) {

		} else {
			sql = sql.replaceAll("<#TIME#>", "");
		}
		return R.SUCCESS_OPER(zb.query(sql).toJsonArrayWithJsonObject());
	}

	@RequestMapping("/zb/getFsUsed.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getFsUsed(String id) {
		// 输出cpu使用率统计
		String sql = "select (100-t1.value) used,from_unixtime(t1.clock,'%Y-%m-%d %H:%i:%S') rtime,t3.name,t3.hostid,t3.host,\n"
				+ "   replace(replace( replace( replace( t2.name,'Free',''),'(percentage)',''),'disk ',''),' on ',':') metricname \n" + " from history t1,items t2,hosts t3\n"
				+ " where  t3.hostid=t2.hostid and t1.itemid=t2.itemid and (t1.itemid,t1.clock) in (\n" + "select\n"
				+ "  a.itemid,max(h.clock)  from items a,history h\n" + "where key_ like 'vfs.fs%'\n"
				+ "and a.templateid is null\n" + "and a.itemid=h.itemid\n"
				+ "and a.name like '%percentage%' group by itemid) order by used desc";

		if (ToolUtil.isEmpty(id)) {

		} else {

		}
		return R.SUCCESS_OPER(zb.query(sql).toJsonArrayWithJsonObject());
	}
}
