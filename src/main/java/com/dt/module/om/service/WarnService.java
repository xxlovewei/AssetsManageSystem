package com.dt.module.om.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.alibaba.fastjson.JSONObject;
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

	public static WarnService me() {
		return SpringContextUtil.getBean(WarnService.class);
	}

	String wdatasql = " select t.*, (select ip from om_node where id=t.node_id) ip,(select name from mn_mapping_text where id=t.service_id) service_name, "
			+ " (select name from om_node where id=t.node_id) node_name   "
			+ "  from mn_metric_warn_rec t  where is_delete='N'  ";

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
			String wsql = "with tab as( " + basesql + " )  "
					+ " select tb.node||to_char(tb.inserttime,'yyyy-mm-dd hh:mi:ss')||ta.v_a_v||metric_id uuid,ta.*,"
					+ " tb." + col + " value,to_char(tb.inserttime,'yyyy-mm-dd hh:mi:ss') itime from tab ta," + tab
					+ " tb  where metric_id=? and ta.node_id=tb.node  " + " and tb." + col + oper
					+ "ta.v_a_v and inserttime>sysdate-1/6";
			System.out.println("wsql\n+" + wsql);
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
				// transaction.Rollback();//回滚
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
					sqls.add(me);
				}
			}
		}
		if (sqls.size() > 0) {
			db.executeSQLList(sqls);
			return true;
		}
		return false;

	}
}
