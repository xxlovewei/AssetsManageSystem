package com.dt.module.om.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年4月6日 上午8:46:08
 * @Description: TODO
 */
@Service
public class MetricGroupService extends BaseService {
	@Autowired
	MappingTextService mappingTextService;

	public static String SHOW_TYPE_CHART = "chart";

	public static String SHOW_TYPE_TABLE = "table";

	public static String DS_TAB = "dstab";

	public R addMetricGroup(TypedHashMap<String, Object> ps) {
		return mappingTextService.addMappingText(ps, MappingTextService.TYPE_METRIC_GROUP);
	}

	public R updateMetricGroup(TypedHashMap<String, Object> ps) {
		return mappingTextService.updateMappingText(ps, MappingTextService.TYPE_METRIC_GROUP);
	}

	public R delMetricGroup(String id) {
		return mappingTextService.delMappingText(id, MappingTextService.TYPE_METRIC_GROUP);
	}

	public R queryMetricGroup() {
		return mappingTextService.queryMappingTextByType(MappingTextService.TYPE_METRIC_GROUP);
	}

	public R queryMetricGroupById(String id) {
		return mappingTextService.queryMappingTextById(id, MappingTextService.TYPE_METRIC_GROUP);
	}

	public R metricGroupAddMetric(String id, String metric_id) {
		Insert me = new Insert("mn_metric_group");
		me.set("id", id);
		me.set("metric_id", metric_id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R metricGroupDelMetric(String id, String metric_id) {
		Delete me = new Delete("mn_metric_group");
		me.where().and("id=?", id).and("metric_id=?", metric_id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R metricGroupClearMetric(String id) {
		Delete me = new Delete("mn_metric_group");
		me.where().and("id=?", id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/*
	 * */
	public R metricGroupNeedMetrics(String id) {
		RcdSet rs = db.query(
				"select * from mn_metric_define where is_delete='N' and id not in (select metric_id from mn_metric_group where id=? )",
				id);
		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	public R metricGroupAddMetrics(String id, String ids) {

		JSONArray r = JSONArray.parseArray(ids);
		ArrayList<SQL> sqls = new ArrayList<SQL>();
		for (int i = 0; i < r.size(); i++) {
			String mid = r.getString(i);
			if (ToolUtil.isNotEmpty(mid) && ToolUtil
					.isEmpty(db.uniqueRecord("select * from mn_metric_group where id=? and metric_id=?", id, mid))) {
				Insert me = new Insert("mn_metric_group");
				me.setIf("id", id);
				me.setIf("metric_id", mid);
				sqls.add(me);
			}
		}
		if (sqls.size() > 0) {
			db.executeSQLList(sqls);
		}
		return R.SUCCESS_OPER();

	}

	public R queryMetricGroupMetrics(String id) {

		return R.SUCCESS_OPER(db.query(
				"select a.* ,b.id grou_id from mn_metric_define a, mn_metric_group b where a.id=b.metric_id and a.is_delete='N' and b.id=?",
				id).toJsonArrayWithJsonObject());
	}

	@Cacheable(value = CacheConfig.CACHE_PUBLIC + "#30#8", key = "'qMGM=WithFastCache_'+#id")
	public R queryMetricGroupMetricsWithFastCache(String id) {
		return R.SUCCESS_OPER(ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(
				"select 'metric' dtype,a.* ,b.id grou_id from mn_metric_define a, mn_metric_group b where a.status='Y' and a.id=b.metric_id and a.is_delete='N' and b.id=?",
				id).toJsonArrayWithJsonObject()));
	}

	public R delMetricGroupMetric(String id, String mid) {

		Delete me = new Delete("mn_metric_group");
		me.where().and("id=?", id).and("metric_id=?", mid);
		db.execute(me);
		return R.SUCCESS_OPER();
	}
}
