package com.dt.module.om.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年4月8日 上午8:58:28
 * @Description: TODO
 */
@Service
public class ResourceService extends BaseService {

	@Autowired
	MetricGroupService metricGroupService;
	@Autowired
	MetricService metricService;
	@Autowired
	MnService mnService;
	@Cacheable(value = CacheConfig.CACHE_PUBLIC_45_10, key = "'qRM'+#node_id+#metric_id+#data_interval")
	public R queryResourceByMetric(String node_id, String metric_id, String data_interval) {
		JSONObject res = new JSONObject();
		res.put("node_id", node_id);
		res.put("metric_id", metric_id);
		// res.put("data_interval", data_interval);
		String dinterval = data_interval;
		String[] colsarr = null;
		JSONArray[] columnsdata = null;

		JSONObject rs = metricService.queryMetricDataWithCache(metric_id);
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

	@Cacheable(value = CacheConfig.CACHE_PUBLIC_45_10, key = "'mn_queryMenus'")
	public R queryMenus() {

		// 查询肯定存在node的service
		String sersql = "select a.*,b.name service_name,b.od,c.od,c.name node_name from mn_service a,mn_mapping_text b ,om_node c  "
				+ " where a.id=b.id and a.status='Y' and b.status='Y' " + " and c.deleted='N' " + " and a.node_id=c.id "
				+ "and b.is_delete='N' and b.type='" + MappingTextService.TYPE_MN_SERVICE + "' order by b.od,c.od";

		RcdSet serrs = db.query(sersql);
		JSONArray res = new JSONArray();
		String serid = "";
		JSONObject ser = new JSONObject();
		JSONArray nodes = new JSONArray();
		for (int i = 0; i < serrs.size(); i++) {
			if (!serid.equals(serrs.getRcd(i).getString("id")) && ToolUtil.isNotEmpty(serid)) {
				// 不相等,将之前的数据添加进去
				ser.put("children", nodes);
				res.add(ser);
				nodes = new JSONArray();
			}
			// node
			JSONObject e = new JSONObject();
			e.put("id", serrs.getRcd(i).getString("node_id"));
			e.put("name", serrs.getRcd(i).getString("node_name"));
			e.put("ser_id", serrs.getRcd(i).getString("id"));
			e.put("dtype", "node");
			nodes.add(e);
			// 服务
			ser = new JSONObject();
			ser.put("id", serrs.getRcd(i).getString("id"));
			ser.put("name", serrs.getRcd(i).getString("service_name"));
			ser.put("dtype", "service");
			serid = serrs.getRcd(i).getString("id");
		}
		if (nodes.size() > 0) {
			ser.put("children", nodes);
			res.add(ser);
		}
		// 添加度量数据
		for (int i = 0; i < res.size(); i++) {
			JSONArray nodearr = res.getJSONObject(i).getJSONArray("children");
			
			//mnService
			for (int j = 0; j < nodearr.size(); j++) {
				String node_id=nodearr.getJSONObject(j).getString("id");
				String ser_id=nodearr.getJSONObject(j).getString("ser_id");
				System.out.println(node_id+","+ser_id);
				R metrics=mnService.queryServiceNodeMetricWithCache(ser_id, node_id);
//				Rcd nrs = db.uniqueRecord("select templid from om_node where id=?",
//						nodearr.getJSONObject(j).getString("id"));
//				if (ToolUtil.isEmpty(nrs)) {
//					continue;
//				}
//				String templid = nrs.getString("templid");
//				if (ToolUtil.isNotEmpty(templid)) {
					if (metrics.isSuccess()) {
						JSONArray metricarr = metrics.queryDataToJSONArray();
						res.getJSONObject(i).getJSONArray("children").getJSONObject(j).put("children", metricarr);
					}
//				}
			}
		}
		String jsonStr = JSON.toJSONString(res, SerializerFeature.DisableCircularReferenceDetect);
		return R.SUCCESS_OPER(JSONArray.parseArray(jsonStr));
	}
}
