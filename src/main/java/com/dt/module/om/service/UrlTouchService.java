package com.dt.module.om.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.cache.ThreadTaskHelper;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
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

	public R touchUrl(String node, String url, String metric_id) {
		JSONObject r = HttpKit.sendGetWithResp(url);
		_log.info("touchUrl,node:" + node + ",url:" + url);
		if (ToolUtil.isOneEmpty(node, url, metric_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Insert me = new Insert("mn_url_touch");
		me.setIf("id", db.getUUID());
		me.setIf("metric_id", metric_id);
		me.setIf("node", node);
		me.setIf("resp_time", r.getString("response_time"));
		me.setIf("status", r.getString("code"));
		me.setIf("body", r.getString("result"));
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
