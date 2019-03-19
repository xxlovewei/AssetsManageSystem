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
				+ "   replace(replace( replace( replace( t2.name,'Free',''),'(percentage)',''),'disk ',''),' on ',':') metricname \n"
				+ " from history t1,items t2,hosts t3\n"
				+ " where  t3.status=0 and  t3.available=1 and t3.hostid=t2.hostid and t1.itemid=t2.itemid and (t1.itemid,t1.clock) in (\n"
				+ "select\n" + "  a.itemid,max(h.clock)  from items a,history h\n" + "where key_ like 'vfs.fs%'\n"
				+ "and a.templateid is null\n" + "and a.itemid=h.itemid\n"
				+ "and a.name like '%percentage%' group by itemid) order by used desc";
		if (ToolUtil.isEmpty(id)) {
		} else {
		}
		JSONObject res = new JSONObject();
		String hzsql = "select  count(distinct hostid) cnt, min(rtime) minrtime,max(rtime)maxrtime from (" + sql
				+ ")t ";
		Rcd rs = zb.uniqueRecord(hzsql);
		if (rs != null) {
			res = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
			res.put("data", ConvertUtil.OtherJSONObjectToFastJSONArray(zb.query(sql).toJsonArrayWithJsonObject()));
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

		String dsql = "select b.host ,c.itemid,(100-d.value) value, d.clock from host_inventory a,hosts b,items c,history d\n" + 
				"where a.hostid=b.hostid\n" + 
				"and c.hostid=a.hostid and  c.key_='system.cpu.util[,idle]'\n" + 
				"and d.clock>unix_timestamp(CONCAT(DATE_FORMAT(curdate()-1,'%Y-%m-%d'),' 08:00:00')) \n" + 
				"and d.itemid=c.itemid order by  host,clock";
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

	public R queryResourceByMetric(String node_id, String metric_id, String data_interval) {
		JSONObject res = new JSONObject();
		res.put("node_id", node_id);
		res.put("metric_id", metric_id);
		// res.put("data_interval", data_interval);
		String dinterval = data_interval;
		String[] colsarr = null;
		JSONArray[] columnsdata = null;

		JSONObject rs = null;
		if (!ToolUtil.isEmpty(rs)) {
			String showtype = rs.getString("showtype");
			String chartdatatype = rs.getString("chartdatatype");
			res.put("showtype", showtype);
			String ds_value = rs.getString("ds_value");
			// String ds = rs.getString("ds");
			String cols = rs.getString("cols");
			String charttype = rs.getString("chartopt");
			if (ToolUtil.isEmpty(dinterval)) {
				dinterval = ConvertUtil.toInt(rs.getString("data_interval"), 3) + "";
			}
			// System.out.println("dinterval" + dinterval);
			JSONObject chartOpt = JSONObject.parseObject(charttype);
			String dsql = "";

			// 生成highchart的数据
			if (showtype.equals("chart")) {
				// 初始化cols空间
				if (chartdatatype.equals("direct")) {
					colsarr = cols.split(",");
					// 取所有数据
					dsql = "select " + cols + " , inserttime from " + ds_value + " where node='" + node_id + "'";
				} else if (chartdatatype.equals("indata")) {
					// lvname,value
					if (cols.split(",").length != 2) {
						return R.SUCCESS_OPER(res);
					}
					String col = cols.split(",")[0];
					String col_v = cols.split(",")[1];
					String tempsql = "select distinct " + col + " from " + ds_value + " where node='" + node_id
							+ "' and inserttime>sysdate-45";
					RcdSet tmprs = db.query(tempsql);
					if (tmprs.size() == 0) {
						return R.SUCCESS_OPER(res);
					}
					colsarr = new String[tmprs.size()];
					dsql = "select  ";
					for (int i = 0; i < tmprs.size(); i++) {
						String cols_v = tmprs.getRcd(i).getString(col);
						colsarr[i] = cols_v;
						dsql = dsql + " nvl((select  decode(" + col_v + ",null,0," + col_v + ") " + col + " from "
								+ ds_value + " b where a.node=b.node and a.inserttime=b.inserttime and " + col + "='"
								+ cols_v + "'),0)  \"" + cols_v + "\" ,";
					}
					dsql = dsql + " inserttime from  " + ds_value + " a where node='" + node_id + "'";

				}

				columnsdata = new JSONArray[colsarr.length];
				for (int i = 0; i < colsarr.length; i++) {
					columnsdata[i] = new JSONArray();
				}
				// 取数
				String sql = "select tab.*,trunc((inserttime-to_date('1970-01-01','yyyy-mm-dd'))*24*60*60*1000,1) intertime from ("
						+ dsql + ") tab where 1=1 ";
				if (ToolUtil.isNotEmpty(dinterval)) {
					sql = sql + " and inserttime>sysdate-" + dinterval;
				}
				sql = sql + " order by inserttime";
				RcdSet drs = db.query(sql);
				for (int i = 0; i < drs.size(); i++) {
					BigDecimal a = ((BigDecimal) drs.getRcd(i).getBigDecimal("intertime"));
					for (int j = 0; j < colsarr.length; j++) {
						BigDecimal b = ((BigDecimal) drs.getRcd(i).getBigDecimal(colsarr[j]));
						columnsdata[j].add(new BigDecimal[] { a.setScale(2, BigDecimal.ROUND_HALF_UP),
								b.setScale(2, BigDecimal.ROUND_HALF_UP) });
					}
				}
				/* 生成series数据 */
				JSONArray sd = new JSONArray();
				for (int j = 0; j < colsarr.length; j++) {
					JSONObject seriesobj = new JSONObject();
					seriesobj.put("name", colsarr[j]);
					seriesobj.put("data", columnsdata[j]);
					sd.add(seriesobj);
				}
				chartOpt.put("series", sd);
				/* 封装option */
				res.put("option", chartOpt);

			} else if (showtype.equals("table")) {
				// 生成table数据
			}

		}
		return R.SUCCESS_OPER(res);

	}
}
