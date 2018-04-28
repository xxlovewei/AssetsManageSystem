package com.dt.module.om.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.cache.CacheConfig;
import com.dt.core.cache.ThreadTaskHelper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2018年4月25日 上午9:18:58
 * @Description: TODO
 */
@Service
public class UrlTouchService extends BaseService {
	@Autowired
	UrlMetricService urlMetricService;

	public static UrlTouchService me() {
		return SpringContextUtil.getBean(UrlTouchService.class);
	}

	private static Logger _log = LoggerFactory.getLogger(UrlTouchService.class);

	public R touchUrlExample() {
		return executeAllUrls();
	}

	// @Cacheable(value = CacheConfig.CACHE_PUBLIC_45_10, key =
	// "'qUrlMetricData'+#node+#type+#time")
	public R queryTouchMetricData(String node, String type, String time) {
		int t = ToolUtil.toInt(time, 3);
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
		// System.out.println("T");
		// Long a = System.currentTimeMillis();
		String sql = "select trunc((inserttime-to_date('1970-01-01','yyyy-mm-dd'))*24*60*60*1000,1) itime,t.status,t.resp_time from mn_url_touch t where node=? and inserttime>sysdate-"
				+ t + " order by inserttime";
		RcdSet drs = db.query(sql, node);
		// Long s = System.currentTimeMillis();
		// System.out.println("sql exe" + (s - a));
		StringBuffer strs = new StringBuffer("[");
		if (type.equals("status")) {
			for (int i = 0; i < drs.size(); i++) {
				strs.append(
						"[" + drs.getRcd(i).getBigDecimal("itime") + "," + drs.getRcd(i).getInteger("status") + "]");
			}
		} else {
			for (int i = 0; i < drs.size(); i++) {
				strs.append(
						"[" + drs.getRcd(i).getBigDecimal("itime") + "," + drs.getRcd(i).getInteger("resp_time") + "]");
			}
		}
		strs.append("]");
		// System.out.println("111");
		res.put("data", JSONArray.parse(strs.toString()));
		// Long e = System.currentTimeMillis();
		// System.out.println(e - s);
		return R.SUCCESS_OPER(res);
	}

	public R executeAllUrls() {
		JSONArray rs = urlMetricService.queryUrlMetrics("1", null).queryDataToJSONArray();
		String nodes = "(";
		String nodesClear = "(";
		for (int i = 0; i < rs.size(); i++) {
			JSONObject e = rs.getJSONObject(i);
			String node = e.getString("node");
			String url = e.getString("url");
			int interval_time = ToolUtil.toInt(e.getString("interval_time"), 5);
			int curcnt = ToolUtil.toInt(e.getString("curcnt"), 1);
			if (curcnt < interval_time) {
				nodes = nodes + "'" + node + "',";
			} else {
				nodesClear = nodesClear + "'" + node + "',";
				ThreadTaskHelper.run(new Runnable() {
					@Override
					public void run() {
						touchUrl(node, url, "metric");
					}
				});
			}
		}
		nodes = nodes + "'')";
		nodesClear = nodesClear + "'')";
		String upsclear = "update mn_url_metric set curcnt=1 where node in " + nodesClear;
		String upsadd = "update mn_url_metric set curcnt=curcnt+1 where node in " + nodes;
		db.executes(upsclear, upsadd);
		return R.SUCCESS_OPER();
	}

	public R checkUrlMetricDataForwarnning() {
		String sql = "insert into mn_url_touch_warn "
				+ " select a.id,b.node,b.name,b.url,a.id,a.status,a.resp_time,b.threshold,a.inserttime,0,0,0 from ( "
				+ " select * from mn_url_touch where (node,inserttime) "
				+ " in(select node, max(inserttime) from mn_url_touch where inserttime>sysdate-1/2 group by node"
				+ " ))a,mn_url_metric b  " + " where a.node=b.node and b.is_running=1 and threshold>0 "
				+ " and(a.status<>200 or resp_time>threshold) and a.id not in(  "
				+ " select id from mn_url_touch_warn where inserttime>sysdate-1/2)";
		db.execute(sql);
		return R.SUCCESS_OPER();
	}

	public R buildWarnningBody() {
		// 半天内
		return R.SUCCESS_OPER();
	}

	public R touchUrl(String node, String url, String metric_id) {
		JSONObject r = null;
		if (ToolUtil.isOneEmpty(node, url, metric_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		try {
			_log.info("touchUrl,node:" + node + ",url:" + url);
			r = HttpKit.sendGetWithResp(url);
		} catch (Exception e) {
			r = new JSONObject();
			r.put("result", e.getMessage());
			r.put("code", 404);
			r.put("response_time", 100);
		} finally {
			if (ToolUtil.isEmpty(r)) {
				r.put("result", "unknow");
				r.put("code", 404);
				r.put("response_time", 100);
			}
		}
		//System.out.println(r.toJSONString());
		Insert me = new Insert("mn_url_touch");
		me.setIf("id", db.getUUID());
		me.setIf("metric_id", metric_id);
		me.setIf("node", node);
		me.setIf("resp_time", r.getString("response_time"));
		me.setIf("status", r.getString("code"));
		String rstr = r.getString("result");
		if (ToolUtil.isNotEmpty(rstr) && rstr.length() > 2000) {
			me.setIf("body", rstr.substring(0, 2000));
		} else {
			me.setIf("body", rstr);
		}
		me.setIf("producetime", "");
		me.setSE("inserttime", DbUtil.getDbDateString(db.getDBType()));
		db.execute(me);
		return R.SUCCESS();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<String, String>();
		JSONObject r = HttpKit.sendGetWithResp(
				"http://dt.yuxianshengolp.com/wx/api/mn/queryResourceByMetric.do?metric_id=c5d1f43a-2131-44b0-a0e6-4283c36c4463&node_id=.crmrk");
		System.out.println(r.getString("code"));
		System.out.println(r.getString("result"));
		System.out.println(HttpKit.sendGet(
				"http://dt.yuxianshengolp.com/dt/api/mn/queryResourceByMetric.do?metric_id=c5d1f43a-2131-44b0-a0e6-4283c36c4463&node_id=.crmrk",
				param));
		JSONObject r2s = HttpKit.sendGetWithResp("http://www.baidu.com");

	}

}
