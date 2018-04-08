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
		return R.SUCCESS_OPER(db.query(
				"select b.id service_id,b.status showstatus,a.* from om_node a,mn_service b where a.id=b.node_id and a.deleted='N' and b.id=?",
				id).toJsonArrayWithJsonObject());

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

}
