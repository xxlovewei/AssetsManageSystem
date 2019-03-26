package com.dt.module.zb.controller;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ConvertUtil;
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

	@RequestMapping("/zb/getCpuUsedByType.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getCpuUsedByType(String type) {

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
		System.out.println("getCpuUsedByTypesql:\n"+sql);
		return R.SUCCESS_OPER(zb.query(sql).toJsonArrayWithJsonObject());
	}

	 
	
	
	@RequestMapping("/zb/getMemUsed.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getMemUsed(String top) {
		if(ToolUtil.isEmpty(top)) {
			top="100";
		}
		// 输出mem使用率统计
		String sql = "select t6.*,case t6.total when 0 then 0 else round((1-t6.available/t6.total)*100 ,2) end  used from (\n" + 
				"select t3.hostid,t3.name,\n" + 
				"max((CASE t2.key_  WHEN 'vm.memory.size[total]' THEN value else 0 END) )AS 'total',\n" + 
				"max((CASE t2.key_  WHEN 'vm.memory.size[available]' THEN value else 0 END)) AS 'available'\n" + 
				"from history_uint t1 ,items t2,hosts t3\n" + 
				"where\n" + 
				"  t1.clock>unix_timestamp(date_sub(now(),interval 2 hour)) and t3.status = 0 and t3.available = 1\n" + 
				"  and t1.itemid=t2.itemid\n" + 
				"  and t2.hostid=t3.hostid\n" + 
				"  and (t1.itemid,t1.clock) in(\n" + 
				"select itemid,max(clock) from history_uint where clock>unix_timestamp(date_sub(now(),interval 2 hour)) and  itemid in (\n" + 
				"select itemid from items t2,hosts t3 where key_ in ('vm.memory.size[available]','vm.memory.size[total]')\n" + 
				"and  t3.hostid = t2.hostid )group by itemid) group by t3.hostid,t3.name )t6 order by used desc";

		System.out.println("memsql:\n"+"select * from ("+sql+")fk limit "+top);
		return R.SUCCESS_OPER(zb.query("select * from ("+sql+")fk limit "+top).toJsonArrayWithJsonObject());
	}
	
	
	@RequestMapping("/zb/getCpuUsed.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getCpuUsed(String top) {
		if(ToolUtil.isEmpty(top)) {
			top="100";
		}
		// 输出cpu使用率统计
		String sql = "select\n" + 
				"  t3.hostid,\n" + 
				"  t3.host,\n" + 
				"  t3.name,\n" + 
				"  from_unixtime(t1.clock,'%Y-%m-%d %H:%i:%S') rtime,\n" + 
				"  (100-t1.value) used\n" + 
				"from  items t2, hosts t3, history t1\n" + 
				"where t1.clock>unix_timestamp(date_sub(now(),interval 2 hour)) and t1.itemid = t2.itemid and t3.hostid = t2.hostid and key_ = 'system.cpu.util[,idle]'\n" + 
				"      and t3.status = 0 and t3.available = 1 and (t1.itemid,t1.clock)\n" + 
				"in (\n" + 
				"select itemid,max(clock) from history where\n" + 
				"itemid in (\n" + 
				"  select t2.itemid from\n" + 
				" items t2, hosts t3\n" + 
				"where  t3.hostid = t2.hostid\n" + 
				"      and t3.status = 0 and t3.available = 1  and key_ = 'system.cpu.util[,idle]'\n" + 
				") and  clock>unix_timestamp(date_sub(now(),interval 2 hour)) \n" + 
				"group by itemid) order by used desc ";
		System.out.println("cpusql:\n"+"select * from ("+sql+")fk limit "+top);
		return R.SUCCESS_OPER(zb.query("select * from ("+sql+")fk limit "+top).toJsonArrayWithJsonObject());
	}
	
	
	@RequestMapping("/zb/getFsUsed.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R getFsUsed(String id,String top) {
		if(ToolUtil.isEmpty(top)) {
			top="100";
		}
		// 输出cpu使用率统计
		String sql = "select (100-t1.value) used,from_unixtime(t1.clock,'%Y-%m-%d %H:%i:%S') rtime,t3.name,t3.hostid,t3.host,\n"
				+ "   replace(replace( replace( replace( t2.name,'Free',''),'(percentage)',''),'disk ',''),' on ',':') metricname \n"
				+ " from history t1,items t2,hosts t3\n"
				+ " where  t1.clock>unix_timestamp(CONCAT(DATE_FORMAT(curdate()-3,'%Y-%m-%d'),' 08:00:00')) and  t3.status=0 and  t3.available=1 and t3.hostid=t2.hostid and t1.itemid=t2.itemid and (t1.itemid,t1.clock) in (\n"
				+ "select\n" + "  a.itemid,max(h.clock)  from items a,history h\n" + "where  h.clock>unix_timestamp(CONCAT(DATE_FORMAT(curdate()-3,'%Y-%m-%d'),' 08:00:00'))   and  key_ like 'vfs.fs%'\n"
				+ "and a.templateid is null\n" + "and a.itemid=h.itemid\n"
				+ "and a.name like '%percentage%' group by itemid) order by used desc";
		if (ToolUtil.isEmpty(id)) {
		} else {
		}
		JSONObject res = new JSONObject();
		String hzsql = "select  count(distinct hostid) cnt, min(rtime) minrtime,max(rtime)maxrtime from (" + sql
				+ ")t ";
		System.out.println("fssql:\n"+sql);
		Rcd rs = zb.uniqueRecord(hzsql);
		if (rs != null) {
			res = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
			res.put("data", ConvertUtil.OtherJSONObjectToFastJSONArray(zb.query("select * from ("+sql+")fk limit "+top).toJsonArrayWithJsonObject()));
		} else {
			res.put("cnt", 0);
			res.put("minrtime", "");
		}
		return R.SUCCESS_OPER(res);
	}

	@RequestMapping("/zb/queryResourceByCpu.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryResourceByCpu(String data_interval) {

		JSONObject res = new JSONObject();
		String hsql = "select distinct b.host from host_inventory a,hosts b where b.status=0 and b.available>0";
		RcdSet hrs = zb.query(hsql);
		JSONObject temphost = new JSONObject();
		for (int i = 0; i < hrs.size(); i++) {
			temphost.put(hrs.getRcd(i).getString("host"), new JSONArray());
		}

		String dsql = "select b.host ,c.itemid,(100-d.value) value, d.clock*1000 clock from host_inventory a,hosts b,items c,history d\n" + 
				"where a.hostid=b.hostid\n" + 
				"and c.hostid=a.hostid and  c.key_='system.cpu.util[,idle]'\n" + 
				"and d.clock>unix_timestamp(CONCAT(DATE_FORMAT(curdate()-1,'%Y-%m-%d'),' 08:00:00')) \n" + 
				"and d.itemid=c.itemid order by  host,clock desc";
		RcdSet hdata = zb.query(dsql);
		for (int j = 0; j < hdata.size(); j++) {
			BigDecimal a = ((BigDecimal) hdata.getRcd(j).getBigDecimal("clock"));
			BigDecimal b = ((BigDecimal) hdata.getRcd(j).getBigDecimal("value"));
			try { 
				temphost.getJSONArray(hdata.getRcd(j).getString("host")).add(new BigDecimal[] {
						a.setScale(2, BigDecimal.ROUND_HALF_UP), b.setScale(2, BigDecimal.ROUND_HALF_UP) });
			} catch (Exception e) {

			} finally {

			}

		}

		JSONArray sdata = new JSONArray();
		Iterator<String> keys = temphost.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject e = new JSONObject();
			e.put("name", key);
			e.put("data", temphost.getJSONArray(key));
			sdata.add(e);

		}
		res.put("seriesdata", sdata);
		return R.SUCCESS_OPER(res);
	}

	 
 
}
