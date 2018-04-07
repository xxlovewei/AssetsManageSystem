package com.dt.module.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.util.TypedHashMap;

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

}
