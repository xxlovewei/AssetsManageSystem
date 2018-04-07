package com.dt.module.om.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2018年4月6日 上午8:51:45
 * @Description: TODO
 */
@Service
public class MappingTextService extends BaseService {

	public static String TYPE_METRIC_GROUP = "metric_group";
	public static String TYPE_MN_SERVICE = "mn_service";

	public R addMappingText(TypedHashMap<String, Object> ps, String type) {
		Insert me = new Insert("mn_mapping_text");
		me.set("id", db.getUUID());
		me.setIf("name", ps.getString("name"));
		me.setIf("type", type);
		me.setIf("is_delete", "N");
		me.setIf("status", ps.getString("status"));
		me.setIf("od", ps.getString("od"));
		me.setIf("mark", ps.getString("mark"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R updateMappingText(TypedHashMap<String, Object> ps, String type) {
		Update me = new Update("mn_mapping_text");
		me.setIf("name", ps.getString("name"));
		me.setIf("status", ps.getString("status"));
		me.setIf("od", ps.getString("od"));
		me.setIf("mark", ps.getString("mark"));
		me.where().and("id=?", ps.getString("id", "")).and("type=?", type);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R delMappingText(String id, String type) {
		Update me = new Update("mn_mapping_text");
		me.setIf("is_delete", "Y");
		me.where().and("id=?", id).and("type=?", type);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	public R queryMappingText() {
		return R.SUCCESS_OPER(
				db.query("select * from mn_mapping_text where is_delete='N'").toJsonArrayWithJsonObject());
	}

	public R queryMappingTextById(String id, String type) {
		return R.SUCCESS_OPER(
				db.uniqueRecord("select * from mn_mapping_text where is_delete='N' and id=? and type=? order by od", id,
						type).toJsonObject());
	}

	public R queryMappingTextByType(String type) {
		return R.SUCCESS_OPER(db.query("select * from mn_mapping_text where is_delete='N' and type=? order by od", type)
				.toJsonArrayWithJsonObject());
	}

}
