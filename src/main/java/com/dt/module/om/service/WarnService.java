package com.dt.module.om.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: jinjie
 * @date: 2018年4月11日 上午11:40:52
 * @Description: TODO
 */

@Service

public class WarnService extends BaseService {
	@Autowired
	MailService mailService;

	// private static Logger _log = LoggerFactory.getLogger(WarnService.class);

	public static WarnService me() {
		return SpringContextUtil.getBean(WarnService.class);
	}

	String wdatasql = " select t.*, (select ip from om_node where id=t.node_id) ip,(select name from mn_mapping_text where id=t.service_id) service_name, "
			+ " (select name from om_node where id=t.node_id) node_name "
			+ "  from mn_metric_warn_rec t where is_delete='N' ";

	/* 坚持有需要service,node,metric和度量检测信息 */
	public String basesql = " select ta.* from (                                                                        "
			+ " select 'metric' source,a.service_id,a.node_id ,a.metric_id,b.v_a,                         "
			+ " decode(b.v_a_m,null,'>',b.v_a_m) v_a_m,                                                   "
			+ " decode(a.v_a_v,null,b.v_a_v,a.v_a_v) v_a_v,                                               "
			+ " decode(a.data_interval,null,b.data_interval,a.data_interval) dl,                          "
			+ " b.name metric_name,                                                                        "
			+ " b.is_warn                                                            "
			+ " from mn_service_node_metric a,mn_metric_define b                                          "
			+ " where mtype<>'templ' and a.metric_id=b.id                                                 "
			+ " union all                                                                                 "
			+ " select 'templ' source, t1.service_id,t1.node_id,t1.metric_id,t1.v_a,                      "
			+ " decode(t1.v_a_m,null,'>',t1.v_a_m) v_a_m,                                                 "
			+ " decode(t2.v_a_v,null,t1.v_a_v,t2.v_a_v ) v_a_v,                                           "
			+ " decode(t2.data_interval,null,t1.data_interval,t2.data_interval) dl,                       "
			+ " metric_name,is_warn                                                                               "
			+ " from(                                                                                     "
			+ " select b.id service_id,a.id node_id, a.name nodename,c.metric_id,d.*,d.name metric_name   "
			+ " from om_node a,mn_metric_group c ,mn_service b,mn_metric_define d                         "
			+ " where a.templid=c.id and a.deleted='N' and d.id=c.metric_id and d.is_delete='N'           "
			+ " and b.node_id=a.id and b.is_delete='N') t1 left join mn_service_node_metric t2            "
			+ " on (t1.service_id=t2.service_id and t1.metric_id=t2.metric_id and t1.node_id=t2.node_id)  "
			+ " )ta,mn_mapping_text tb where ta.service_id=tb.id and tb.is_delete='N'                     "
			+ " and v_a_v is not null and v_a is not null                                                 ";

	public RcdSet queryNeedWarnMetrics() {
		String sql = "select distinct metric_id from (" + basesql + ") where is_warn='Y' ";
		return db.query(sql);
	}

	public R queryWarnDataForDashboard(String day) {
		// int d=ToolUtil.toInt(day,7);
		RcdSet rs = db.query(wdatasql);
		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	public R deleteWarnData(String id) {
		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		} else {
			Update me = new Update("mn_metric_warn_rec");
			me.set("is_delete", "Y");
			me.where().and("id=?", id);
			db.execute(me);
			return R.SUCCESS_OPER();
		}
	}

	public boolean queryWarnMetric() {
		HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
		RcdSet metricsrs = queryNeedWarnMetrics();
		for (int i = 0; i < metricsrs.size(); i++) {
			String mid = metricsrs.getRcd(i).getString("metric_id");
			Rcd minfo = db.uniqueRecord(
					" select t.*,decode(v_a_m,null,'>',v_a_m) vam from mn_metric_define t where v_a is not null and ds_value is not null and id=?",
					mid);
			if (minfo == null) {
				break;
			}
			String oper = MetricService.checkOper(minfo.getString("vam"));
			String tab = minfo.getString("ds_value");
			String col = minfo.getString("v_a");

			// 相同节点,相同时间,相同度量相同阀值只告警一次,4小时内的数据
			String wsql = "with tab as( " + basesql + " )  "
					+ " select tb.node||to_char(tb.inserttime,'yyyy-mm-dd hh:mi:ss')||ta.v_a_v||metric_id uuid,ta.*,"
					+ " tb." + col + " value,to_char(tb.inserttime,'yyyy-mm-dd hh:mi:ss') itime from tab ta," + tab
					+ " tb  where metric_id=? and ta.node_id=tb.node  " + " and tb." + col + oper
					+ "ta.v_a_v and inserttime>sysdate-1/6";
			try {
				RcdSet wdata = db.query(wsql, mid);
				if (wdata.size() > 0) {
					for (int j = 0; j < wdata.size(); j++) {
						// System.out.println(wdata.getRcd(j).toJsonObject());
						String uuid = MD5Util.encrypt(wdata.getRcd(j).getString("uuid"));
						map.put(uuid, ConvertUtil.OtherJSONObjectToFastJSONObject(wdata.getRcd(j).toJsonObject()));
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		List<SQL> sqls = new ArrayList<SQL>();
		if (map.size() > 0) {
			for (String key : map.keySet()) {
				JSONObject e = map.get(key);
				Rcd rcdrs = db.uniqueRecord("select * from mn_metric_warn_rec where id=?", key);
				if (rcdrs == null) {
					Insert me = new Insert("mn_metric_warn_rec");
					me.set("id", key);
					me.setIf("service_id", e.getString("service_id"));
					me.setIf("node_id", e.getString("node_id"));
					me.setIf("metric_id", e.getString("metric_id"));
					me.setIf("v_a", e.getString("v_a"));
					me.setIf("v_a_v", e.getString("v_a_v"));
					me.setIf("value", e.getString("value"));
					me.setIf("metric_name", e.getString("metric_name"));
					me.setIf("inserttime", e.getString("itime"));
					me.setSE("createtime", DbUtil.getDbDateString(db.getDBType()));
					me.set("is_delete", "N");
					me.set("is_process", "N");
					me.set("wtype", "mn_node");
					sqls.add(me);
				}
			}
		}
		if (sqls.size() > 0) {
			db.executeSQLList(sqls);
		}

		List<SQL> psqls = new ArrayList<SQL>();
		String mailsql = "select * from (" + wdatasql + ") where is_process='N' and is_delete='N' ";
		RcdSet rs = db.query(mailsql);
		if (rs.size() > 0) {
			String htmlfill = "<table  border=\"1\"><thead><tr><th>节点</th><th>度量</th><th>阀值</th><th>当前</th><th>日期</th></tr></thead><tbody>";
			for (int i = 0; i < rs.size(); i++) {
				Update ups = new Update("mn_metric_warn_rec");
				ups.set("is_process", "Y");
				ups.where().and("id=?", rs.getRcd(i).getString("id"));
				psqls.add(ups);
				String sname = rs.getRcd(i).getString("service_name");
				String nodename = "";
				if (ToolUtil.isEmpty(sname)) {
					nodename = rs.getRcd(i).getString("node_name");
				} else {
					nodename = "(" + sname + ")" + rs.getRcd(i).getString("node_name");
				}
				htmlfill = htmlfill + "<tr>";
				htmlfill = htmlfill + "<td>" + nodename + "</td>";
				htmlfill = htmlfill + "<td>" + rs.getRcd(i).getString("metric_name") + "</td>";
				htmlfill = htmlfill + "<td>" + rs.getRcd(i).getString("v_a_v") + "</td>";
				htmlfill = htmlfill + "<td>" + rs.getRcd(i).getString("value") + "</td>";
				htmlfill = htmlfill + "<td>" + rs.getRcd(i).getString("inserttime") + "</td>";
				htmlfill = htmlfill + "</tr>";
			}
			htmlfill = htmlfill + "</tbody></table>";
			R r = mailService.sendMail(htmlfill, "来自度量数据");
			if (r.isSuccess()) {
				db.executeSQLList(psqls);
			}
			return true;
		}
		return false;
	}

	public R queryUrlMetricWarnLastData() {
		String sql = "select * from mn_url_touch_warn where(node,inserttime) in ("
				+ "select node,max(inserttime) from mn_url_touch_warn where dr=0 group by node)";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public R deleteUrlMetricWarnData(String node, String id) {
		if (id == null) {
			id = "";
		}
		if (ToolUtil.isEmpty(node)) {
			return R.FAILURE();
		}
		String sql = " update mn_url_touch_warn set dr=1 where node='" + node + "' and  " + " inserttime<= "
				+ " decode((select inserttime from mn_url_touch_warn where id='" + id + "'), " + " null,sysdate+1, "
				+ " (select inserttime from mn_url_touch_warn where id='" + id + "')) ";
		db.execute(sql);
		return R.SUCCESS_OPER();
	}

	@Cacheable(value = CacheConfig.CACHE_USER_180_60, key = "'qUrlMetricDataF'+#node+#type")
	public R queryUrlMetricWarnDataForChart(String type, String node) {
		int t = 180;
		String bsql = "select * from mn_url_metric where node=?";
		JSONObject res = new JSONObject();
		Rcd rs = db.uniqueRecord(bsql, node);
		if (ToolUtil.isNotEmpty(rs)) {
			res.put("name", rs.getString("name"));
		}
		if (type.equals("status")) {
			res.put("col", "状态码");
		} else {
			res.put("col", "响应时间");
		}
		String sql = "select trunc((inserttime-to_date('1970-01-01','yyyy-mm-dd'))*24*60*60*1000,1) itime,t.* from mn_url_touch_warn t where node=? and inserttime>sysdate-"
				+ t + " order by inserttime";
		RcdSet drs = db.query(sql, node);
		JSONArray data = new JSONArray();
		if (type.equals("status")) {
			for (int i = 0; i < drs.size(); i++) {
				JSONArray e = new JSONArray();
				e.add(((BigDecimal) drs.getRcd(i).getBigDecimal("itime")));
				e.add(drs.getRcd(i).getInteger("status"));
				data.add(e);
			}
		} else {
			for (int i = 0; i < drs.size(); i++) {
				JSONArray e = new JSONArray();
				e.add(((BigDecimal) drs.getRcd(i).getBigDecimal("itime")));
				e.add(drs.getRcd(i).getInteger("resp_time"));
				data.add(e);
			}
		}
		res.put("data", data);

		return R.SUCCESS_OPER(res);
	}

}
