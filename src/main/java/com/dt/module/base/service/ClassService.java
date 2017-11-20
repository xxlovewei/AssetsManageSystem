package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Delete;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.SQL;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年11月17日 上午8:19:03
 * @Description: 单独分类的服务
 */
@Service
public class ClassService extends BaseService {

	public static String CLASS_TYPE_MICROSHOP_INDEX = "microshopindex";
	public static String MODULE_TYPE_MALL = "mall";

	public ResData addClass(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_ct_class");
		me.set("class_id", db.getUUID());
		me.set("is_delete", "N");
		me.setIf("type", ps.getString("TYPE"));
		me.setIf("name", ps.getString("NAME"));
		me.setIf("is_used", ps.getString("IS_USED"));
		me.setIf("mainimg", ps.getString("MAINIMG"));
		me.setIf("od", ps.getString("OD"));
		me.setIf("mark", ps.getString("MARK"));
		me.setIf("module", ps.getString("MODULE"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData deleteClass(String class_id) {
		Update me = new Update("sys_ct_class");
		me.set("is_delete", "Y");
		me.where().and("class_id=?", class_id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData updateClass(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_ct_class");
		me.setIf("name", ps.getString("NAME"));
		me.setIf("mainimg", ps.getString("MAINIMG"));
		me.setIf("is_used", ps.getString("IS_USED"));
		me.setIf("od", ps.getString("OD"));
		me.setIf("mark", ps.getString("MARK"));
		me.where().and("class_id=?", ps.getString("CLASS_ID"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData queryClass(String class_id, String type, String is_used) {
		String sql = "select * from sys_ct_class where is_delete='N' ";
		if (ToolUtil.isNotEmpty(type)) {
			sql += " and type='" + type + "' ";
		}
		if (ToolUtil.isNotEmpty(is_used)) {
			sql += " and is_used='" + is_used + "' ";
		}
		sql += " order by od";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public ResData queryClassById(String class_id) {

		Rcd rs = db.uniqueRecord("select * from sys_ct_class where is_delete='N' and class_id=?", class_id);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}

	public ResData addClassItem(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_ct_class_item");
		me.set("id", db.getUUID());
		me.setIf("class_id", ps.getString("CLASS_ID"));
		me.setIf("is_used", ps.getString("IS_USED"));
		me.setIf("value", ps.getString("VALUE"));
		me.setIf("od", ps.getString("OD"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData addClassItems(TypedHashMap<String, Object> ps) {
		// IDS
		String ids = ps.getString("IDS");
		if (ToolUtil.isEmpty(ids)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		JSONArray idsarr = (JSONArray) JSONArray.parse(ids);
		List<SQL> sqls = new ArrayList<SQL>();
		for (int i = 0; i < idsarr.size(); i++) {
			Insert me = new Insert("sys_ct_class_item");
			me.set("id", db.getUUID());
			me.setIf("class_id", ps.getString("CLASS_ID"));
			me.setIf("is_used", "Y");
			me.setIf("value", idsarr.getString(i));
			me.setIf("od", ps.getString("OD"));
			System.out.println(me.getSQL());
			sqls.add(me);
		}
		if (sqls.size() > 0) {
			db.batchExecute(sqls);
		}

		return ResData.SUCCESS_OPER();
	}

	public ResData deleteClassItem(String id) {
		Delete me = new Delete();
		me.from(" sys_ct_class_item ");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData updateClassItem(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_ct_class_item");
		me.setIf("is_used", ps.getString("IS_USED"));
		me.setIf("value", ps.getString("VALUE"));
		me.setIf("od", ps.getString("OD"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData queryClassItem(String class_id, String is_used) {

		String sql = "select * from sys_ct_class where 1=1";
		if (ToolUtil.isNotEmpty(class_id)) {
			sql += " and type='" + class_id + "' ";
		}
		if (ToolUtil.isNotEmpty(is_used)) {
			sql += " and type='" + is_used + "' ";
		}
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public ResData queryClassItemById(String id) {
		Rcd rs = db.uniqueRecord("select * from sys_ct_class where class_id=?", id);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}
}
