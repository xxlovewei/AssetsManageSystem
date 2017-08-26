package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.RcdSet;

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
}
