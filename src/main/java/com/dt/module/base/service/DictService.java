package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.Rcd;
import com.dt.dao.sql.Insert;
import com.dt.dao.sql.Update;
import com.dt.dao.util.TypedHashMap;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月5日 下午8:57:56
 * @Description: TODO
 */
@Service
public class DictService extends BaseService {
	/**
	 * @Description: 删除字典
	 */
	public ResData deleteDict(String id) {
		Update ups = new Update("sys_dict");
		ups.set("deleted", "Y");
		ups.where().and("dict_id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	public ResData addDict(TypedHashMap<String, Object> ps) {
		 
	 
		Insert me = new Insert("sys_dict");
		me.set("dict_id", ToolUtil.getUUID());
		me.setIf("name", ps.getString("name", ""));
		me.setIf("mark", ps.getString("mark", ""));
		me.setIf("status", ps.getString("status", "N"));
		me.setIf("deleted","N");
		me.set("dict_level", ps.getString("dict_level"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	  
	}
	/**
	 * @Description: 更新字典
	 */
	public ResData updateDict(TypedHashMap<String, Object> ps) {
		if (!ps.containsKey("dict_id")) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update ups = new Update("sys_dict");
		ups.setIf("name", ps.getString("name", ""));
		ups.setIf("mark", ps.getString("mark", ""));
		ups.setIf("status", ps.getString("status", "N"));
		ups.set("dict_level", ps.getString("dict_level"));
		ups.where().and("dict_id=?", ps.getString("dict_id"));
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 查询所有字典
	 */
	public ResData queryDict() {
		String sql = "select * from sys_dict where deleted='N' ";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:查询某个字典
	 */
	public ResData queryDictById(String id) {
		String sql = "select * from sys_dict where dict_id=?  ";
		Rcd rs=db.uniqueRecord(sql,id);
		if(ToolUtil.isEmpty(rs)){
			return ResData.FAILURE_NODATA();
		}else{
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		} 
	}
	/**
	 * @Description:删除字典项
	 */
	public ResData deleteDictItem(String id) {
		String sql = "delete from sys_dict_item where dict_item_id=?";
		db.execute(sql, id);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:新增字典项
	 */
	public ResData addDictItem(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_dict_item");
		me.set("dict_id", ps.getString("dict_id"));
		me.set("dict_item_id", ToolUtil.getUUID());
		me.setIf("name", ps.getString("name"));
		me.setIf("sort", ps.getString("sort"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("code", ps.getString("code"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:修改字典项
	 */
	public ResData updateDictItem(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_dict_item");
		me.setIf("name", ps.getString("name"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("sort", ps.getString("sort"));
		me.setIf("code", ps.getString("code"));
		me.where().and("dict_item_id=?", ps.getString("dict_item_id"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:查询字典项
	 */
	public ResData queryDictItem(String id) {
		return ResData
				.SUCCESS_OPER(db.query("select * from sys_dict_item where dict_id=? order by sort", id).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:修改某个字典项
	 */
	public ResData queryDictItemById(String dict_item_id) {
		Rcd rs = db.uniqueRecord("select * from sys_dict_item where dict_item_id=?", dict_item_id);
		if (rs == null) {
			return ResData.FAILURE_NODATA();
		}
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}
}
