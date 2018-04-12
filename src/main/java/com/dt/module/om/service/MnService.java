package com.dt.module.om.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2018年4月6日 上午8:47:38
 * @Description: TODO
 */
@Service
public class MnService extends BaseService {

	@Autowired
	MappingTextService mappingTextService;

	public R addMnService(TypedHashMap<String, Object> ps) {
		return mappingTextService.addMappingText(ps, MappingTextService.TYPE_MN_SERVICE);
	}

	public R updateMnService(TypedHashMap<String, Object> ps) {
		return mappingTextService.updateMappingText(ps, MappingTextService.TYPE_MN_SERVICE);
	}

	public R delMnService(String id) {
		return mappingTextService.delMappingText(id, MappingTextService.TYPE_MN_SERVICE);
	}

	public R queryMnServiceNodes(String id) {
		return R.SUCCESS_OPER(db
				.query("select b.id service_id,b.status showstatus,a.* from om_node a,mn_service b where a.id=b.node_id and a.deleted='N' and b.id=?",
						id)
				.toJsonArrayWithJsonObject());

	}

	public R queryMnService() {
		return mappingTextService.queryMappingTextByType(MappingTextService.TYPE_MN_SERVICE);
	}

	public R queryMnServiceById(String id) {
		return mappingTextService.queryMappingTextById(id, MappingTextService.TYPE_MN_SERVICE);
	}

	public R mnServiceNeedAddNodes(String id) {
		String sql = " select b.id,b.type,b.smalltype,b.name,b.ip from om_node b where id not in(select node_id from mn_service where is_delete='N' and id=?)";
		return R.SUCCESS_OPER(db.query(sql, id).toJsonArrayWithJsonObject());
	}

	public R mnServiceAddNodes(String id, String node_ids) {

		JSONArray r = JSONArray.parseArray(node_ids);
		ArrayList<SQL> sqls = new ArrayList<SQL>();
		for (int i = 0; i < r.size(); i++) {
			String node_id = r.getString(i);
			if (ToolUtil.isNotEmpty(node_id) && ToolUtil
					.isEmpty(db.uniqueRecord("select * from mn_service where id=? and node_id=?", id, node_id))) {
				Insert me = new Insert("mn_service");
				me.set("id", id);
				me.setIf("node_id", node_id);
				me.setIf("type", "node");
				me.setIf("status", "Y");
				me.setIf("mark", "");
				me.set("is_delete", "N");
				sqls.add(me);
			}
		}
		if (sqls.size() > 0) {
			db.executeSQLList(sqls);
		}
		return R.SUCCESS_OPER();
	}

	public R mnServiceAddNode(TypedHashMap<String, Object> ps) {
		Rcd rs = db.uniqueRecord("select * from mn_service where id=? and node_id=?", ps.getString("id"),
				ps.getString("node_id"));
		if (rs == null) {
			Insert me = new Insert("mn_service");
			me.set("id", ps.getString("id", ""));
			me.setIf("node_id", ps.getString("node_id", ""));
			me.setIf("type", ps.getString("type", ""));
			me.setIf("status", ps.getString("status", ""));
			me.setIf("mark", ps.getString("mark"));
			me.set("is_delete", "N");
			db.execute(me);
			return R.SUCCESS_OPER();
		} else {
			return R.FAILURE_OPER();
		}

	}

	public R mnServiceDelNode(String id, String node_id) {
		if (ToolUtil.isOneEmpty(id, node_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		Delete me = new Delete("mn_service");
		me.where().and("id=?", id).and("node_id=?", node_id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R mnServiceUpdateNode(TypedHashMap<String, Object> ps) {
		Update me = new Update("mn_service");
		me.setIf("type", ps.getString("type", ""));
		me.setIf("status", ps.getString("status", ""));
		me.setIf("mark", ps.getString("mark"));
		me.where().and("id=?", ps.getString("id")).and("node_id=?", ps.getString("node_id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R mnServiceQueryNodesById(String id) {
		RcdSet rs = db.query("select * from mn_service where is_delete='N' and id=?", id);
		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

	public R mnServiceQueryNodeById(String id, String node_id) {
		Rcd rs = db.uniqueRecord("select * from mn_service where  is_delete='N' and id=? and node_id=?", id, node_id);
		return R.SUCCESS_OPER(rs.toJsonObject());
	}

	public R queryServiceNodeMetric(String ser_id, String node_id) {
		String sql = " select '" + ser_id + "' service_id, '" + node_id
				+ "' node_id,ta.*,decode(tb.data_interval,null,ta.data_interval,tb.data_interval) di,"
				+ " decode(tb.v_a_v,null,ta.v_a_v,tb.v_a_v) node_v_a_v ,"
				+ " decode(tb.is_show,null,'Y',tb.is_show) is_show  "
				+ " from ( select b.*,'templ' mtype from mn_metric_group a,mn_metric_define b,om_node c where a.metric_id=b.id "
				+ " and c.templid=a.id and c.id=? and b.is_delete='N' " + "  ) ta  " + " left join(  "
				+ " select b.* from mn_service a,mn_service_node_metric b where a.id=b.service_id  "
				+ " and a.node_id=b.node_id and a.is_delete='N'  " + " and a.id=? and a.node_id=? " + " )tb  "
				+ " on ta.id=tb.metric_id  ";
		System.out.println(sql);
		return R.SUCCESS_OPER(db.query(sql, node_id, ser_id, node_id).toJsonArrayWithJsonObject());
	}

	public R queryServiceNodeMetricWithCache(String ser_id, String node_id) {
		String sql = " select  'metric' dtype ,'" + ser_id + "' service_id, '" + node_id
				+ "' node_id, ta.mtype,ta.id,ta.name,decode(tb.data_interval,null,ta.data_interval,tb.data_interval) di, "
				+ " decode(tb.is_show,null,'Y',tb.is_show) is_show  "
				+ " from ( select b.*,'templ' mtype from mn_metric_group a,mn_metric_define b,om_node c where a.metric_id=b.id "
				+ " and c.templid=a.id and c.id=? and b.is_delete='N' " + "  ) ta  " + " left join(  "
				+ " select b.* from mn_service a,mn_service_node_metric b where a.id=b.service_id  "
				+ " and a.node_id=b.node_id and a.is_delete='N'  " + " and a.id=? and a.node_id=? " + " )tb  "
				+ " on ta.id=tb.metric_id where decode(tb.is_show,null,'Y',tb.is_show) ='Y' ";
		return R.SUCCESS_OPER(db.query(sql, node_id, ser_id, node_id).toJsonArrayWithJsonObject());
	}

	/* 当模版中当的度量删除时,同时去删除服务节点中的度量 */
	public R delServiceNodeMetric(String templid, String metric_id) {

		String sql = "delete from mn_service_node_metric " + " where (service_id,node_id,metric_id) in "
				+ " (select b.id,b.node_id,'" + metric_id
				+ "' metric_id from om_node a,mn_service b where templid=? and a.id=b.node_id) " + " and mtype='templ'";
		db.execute(sql, templid);
		return R.SUCCESS_OPER();

	}

	public R saveServiceNodeMetric(TypedHashMap<String, Object> ps) {

		String service_id = ps.getString("service_id");
		String node_id = ps.getString("node_id");
		String metric_id = ps.getString("metric_id");
		String is_show = ps.getString("is_show");
		String mtype = ps.getString("mtype");
		String di = ps.getString("di");
		String v_a_v = ps.getString("v_a_v");
		int dv = ToolUtil.toInt(di, 3);
		if (ToolUtil.isOneEmpty(service_id, node_id, metric_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		RcdSet rs = db.query("select * from mn_service_node_metric where service_id=? and node_id=? and metric_id=?",
				service_id, node_id, metric_id);
		if (rs.size() == 0) {
			// 新插入
			Insert me = new Insert("mn_service_node_metric");
			me.set("id", db.getUUID());
			me.set("service_id", service_id);
			me.set("node_id", node_id);
			me.set("metric_id", metric_id);
			me.setIf("is_show", is_show);
			me.setIf("mtype", mtype);
			me.setIf("v_a_v", v_a_v);
			me.set("data_interval", dv);
			db.execute(me);
		} else if (rs.size() >= 2) {
			Delete dl = new Delete("mn_service_node_metric");
			dl.where().and("service_id=?", service_id).and("node_id=?", node_id).and("metric_id=?", metric_id);
			Insert me = new Insert("mn_service_node_metric");
			me.set("id", db.getUUID());
			me.set("service_id", service_id);
			me.set("node_id", node_id);
			me.set("metric_id", metric_id);
			me.setIf("is_show", is_show);
			me.setIf("mtype", mtype);
			me.set("data_interval", dv);
			me.setIf("v_a_v", v_a_v);
			db.executes(dl, me);
		} else {
			Update me = new Update("mn_service_node_metric");
			me.setIf("is_show", is_show);
			me.setIf("mtype", mtype);
			me.set("data_interval", dv);
			me.setIf("v_a_v", v_a_v);
			me.where().and("id=?", rs.getRcd(0).getString("id"));
			db.execute(me);
		}
		return R.SUCCESS_OPER();
	}

}
