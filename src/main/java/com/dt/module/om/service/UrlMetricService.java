package com.dt.module.om.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年4月25日 下午1:16:22
 * @Description: TODO
 */
@Service
public class UrlMetricService extends BaseService {

	public R queryUrlMetrics(String is_running, String status) {
		String sql = "select * from mn_url_metric where dr=0";
		if (ToolUtil.isNotEmpty(is_running)) {
			sql = sql + " and is_running=" + is_running;
		}
		if (ToolUtil.isNotEmpty(status)) {
			sql = sql + " and status='" + status + "'";
		}
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public R queryUrlMetricsWithData() {
		String bsql = " select a.*,b.status resp_status,b.inserttime,b.resp_time"
				+ "   from (select * from mn_url_metric where dr=0) a                            "
				+ "   left join (select *                             "
				+ "                from mn_url_touch                  "
				+ "               where (node, inserttime) in         "
				+ "                     (select node, max(inserttime) "
				+ "                        from mn_url_touch          "
				+ "                       group by node)) b           "
				+ "     on a.node = b.node and a.dr=0                 ";

		return R.SUCCESS_OPER(db.query(bsql).toJsonArrayWithJsonObject());
	}

	public R addUrlMetric(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("mn_url_metric");
		me.setIf("node", db.getUUID());
		me.setIf("name", ps.getString("name"));
		me.setIf("is_running", ToolUtil.toInt(ps.getString("is_running"), 1));
		me.setIf("url", ps.getString("url"));
		me.setIf("status", ps.getString("status", "1"));
		me.setIf("dr", 0);
		me.setIf("interval_time", ToolUtil.toInt(ps.getString("interval_time"), 5));
		me.setIf("threshold", ToolUtil.toInt(ps.getString("threshold"), 0));
		me.setIf("curcnt", 1);
		me.setIf("maxwarn", ps.getString("maxwarn"));
		me.setIf("mark", ps.getString("mark"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R deleteUrlMetric(String node) {
		Update me = new Update("mn_url_metric");
		me.setIf("dr", 1);
		me.where().and("node=?", node);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R updateUrlMetric(TypedHashMap<String, Object> ps) {
		Update me = new Update("mn_url_metric");
		me.setIf("name", ps.getString("name"));
		me.setIf("is_running", ToolUtil.toInt(ps.getString("is_running"), 1));
		me.setIf("url", ps.getString("url"));
		me.setIf("status", ps.getString("status", "1"));
		me.setIf("interval_time", ToolUtil.toInt(ps.getString("interval_time"), 5));
		me.setIf("threshold", ToolUtil.toInt(ps.getString("threshold"), 0));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("maxwarn", ps.getString("maxwarn"));
		me.where().and("node=?", ps.getString("node"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R queryUrlMetricById(String node) {
		return R.SUCCESS_OPER(db.uniqueRecord("select * from mn_url_metric where node=?", node).toJsonObject());
	}
}
