package com.dt.module.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
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

	public R queryResourceByMetric(String node_id,String metric_id,String data_interval) {
		JSONObject res=new JSONObject();
		res.put("node_id", node_id);
		res.put("metric_id", metric_id);
		res.put("data_interval", data_interval);
		res.put("showtype", "chart");
		
		return R.SUCCESS_OPER(res);
	}

	@JSONField(serialize = false)
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
			for (int j = 0; j < nodearr.size(); j++) {
				Rcd nrs = db.uniqueRecord("select templid from om_node where id=?",
						nodearr.getJSONObject(j).getString("id"));
				if (ToolUtil.isEmpty(nrs)) {
					continue;
				}
				String templid = nrs.getString("templid");
				if (ToolUtil.isNotEmpty(templid)) {
					R nmetricrs = metricGroupService.queryMetricGroupMetricsWithFastCache(templid);
					if (nmetricrs.isSuccess()) {
						JSONArray metricarr = nmetricrs.queryDataToJSONArray();
						res.getJSONObject(i).getJSONArray("children").getJSONObject(j).put("children", metricarr);
					}
				}
			}
		}
		String jsonStr = JSON.toJSONString(res, SerializerFeature.DisableCircularReferenceDetect);
		return R.SUCCESS_OPER(JSONArray.parseArray(jsonStr));
	}
}
