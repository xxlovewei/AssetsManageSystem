package com.dt.module.om.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年4月6日 上午8:23:39
 * @Description: TODO
 */
@Service
public class MetricService extends BaseService {

	public static String SHOW_TYPE_CHART = "chart";
	public static String SHOW_TYPE_TABLE = "table";

	public static String DS_TAB = "dstab";

	public R addMetric(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("mn_metric_define");
		me.set("id", db.getUUID());
		me.setIf("name", ps.getString("name"));
		me.setIf("ds", ps.getString("ds"));
		me.setIf("showtype", ps.getString("showtype"));
		me.setIf("chartopt", ps.getString("chartopt"));
		me.setIf("cols", ps.getString("cols"));
		me.setIf("ds_value", ps.getString("ds_value"));
		me.setIf("status", ps.getString("status"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("chartdatatype", ps.getString("chartdatatype"));// direct 直接按照字段取书,indata 从该字段的distinct取数
		me.setIf("is_delete", "N");
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R updateMetric(TypedHashMap<String, Object> ps) {
		Update me = new Update("mn_metric_define");
		me.setIf("ds", ps.getString("ds"));
		me.setIf("name", ps.getString("name"));
		me.setIf("ds_value", ps.getString("ds_value"));
		me.setIf("showtype", ps.getString("showtype"));
		me.setIf("chartopt", ps.getString("chartopt"));
		me.setIf("cols", ps.getString("cols"));
		me.setIf("status", ps.getString("status"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("chartdatatype", ps.getString("chartdatatype"));
		me.where().and("id=?", ps.getString("id", ""));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R delMetric(String id) {
		Update me = new Update("mn_metric_define");
		me.setIf("is_delete", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R queryMetric() {
		return R.SUCCESS_OPER(
				db.query("select * from mn_metric_define where is_delete='N'").toJsonArrayWithJsonObject());
	}

	public R queryMetricById(String id) {
		return R.SUCCESS_OPER(db.uniqueRecord("select * from mn_metric_define where id=?", id).toJsonObject());
	}

	@Cacheable(value = CacheConfig.CACHE_PUBLIC_80_10, key = "'mnqmd_'+#id")
	public JSONObject queryMetricDataWithCache(String id) {
		Rcd rs = db.uniqueRecord("select * from mn_metric_define where id=?", id);
		if (ToolUtil.isNotEmpty(rs)) {
			return ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
		}
		return null;
	}

	public MetricEntity queryMetricEntityById(String id) {
		Rcd r = db.uniqueRecord("select * from mn_metric_define where id=?", id);
		if (ToolUtil.isEmpty(r)) {
			return null;
		}

		MetricEntity e = new MetricEntity();
		e.setChartopt(r.getString("chartopt"));
		e.setCols(r.getString("cols"));
		e.setDs(r.getString("ds"));
		e.setId(r.getString("id"));
		e.setMark(r.getString("mark"));
		e.setName(r.getString("name"));
		return e;

	}
}
