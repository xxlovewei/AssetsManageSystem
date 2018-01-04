package com.dt.module.base.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月5日 下午10:34:48
 * @Description: TODO
 */
@Service
public class RegionService extends BaseService {
	/**
	 * @Description: 获取所有节点数据
	 */
	@Cacheable(value="users", key="#id")  
	public ResData queryRegion() {
		
		String sql = "select * from sys_region";
		RcdSet rs = db.query(sql);
		JSONArray res = new JSONArray();
		for (int i = 0; i < rs.size(); i++) {
			JSONObject e = new JSONObject();
			e.put("id", rs.getRcd(i).getString("id"));
			e.put("parent", rs.getRcd(i).getString("parentid").equals("0") ? "#" : rs.getRcd(i).getString("parentid"));
			e.put("text", rs.getRcd(i).getString("name"));
			res.add(e);
		}
		return ResData.SUCCESS_OPER(res);
	}
	/**
	 * @Description: 获取节点数据
	 */
	public ResData queryRegionById(String id) {
		String sql = "select * from sys_region where parentid=?";
		return ResData.SUCCESS_OPER(db.query(sql, id).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description:获取省份数据
	 */
	public ResData queryShengF(String[] exclude) {
		String sql = "select * from sys_qud_shengf";
		ResData res = new ResData();
		res.setClearStatus(true);
		res.setData(db.query(sql).toJsonArrayWithJsonObject());
		return res;
	}
	/**
	 * @Description:获取城市数据
	 */
	public ResData queryChengS(String sfid, String[] exclude) {
		String sql = "select * from sys_qud_chengs ";
		if (ToolUtil.isEmpty(sfid)) {
			sql = sql + " where 1=0";
		} else {
			sql = sql + " where shengf_id='" + sfid + "'";
		}
		ResData res = new ResData();
		res.setClearStatus(true);
		res.setData(db.query(sql).toJsonArrayWithJsonObject());
		return res;
	}
	/**
	 * @Description:获取区县数据
	 */
	public ResData queryQuX(String csid, String[] exclude) {
		String sql = "select * from sys_qud_qux";
		if (ToolUtil.isEmpty(csid)) {
			sql = sql + " where 1=0";
		} else {
			sql = sql + " where chengs_id='" + csid + "'";
		}
		ResData res = new ResData();
		res.setClearStatus(true);
		res.setData(db.query(sql).toJsonArrayWithJsonObject());
		return res;
	}
}
