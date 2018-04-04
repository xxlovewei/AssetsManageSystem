package com.dt.module.base.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月5日 下午10:34:48
 * @Description: TODO
 */
@Service
public class RegionService extends BaseService {
	/**
	 * @Description: 获取所有节点数据,不再使用这个API
	 */
	@Cacheable(value = CacheConfig.CACHE_PUBLIC_3h_1h, key = "'region_queryRegion'")
	public R queryRegion() {
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

		return R.SUCCESS_OPER(res);
	}

	/**
	 * @Description: 获取节点数据
	 */
	@Cacheable(value = CacheConfig.CACHE_PUBLIC_3h_1h, key = "'region_queryRegionALL'")
	public R queryRegionALL() {

		JSONObject data = new JSONObject();
		// 省份
		String sfsql = "select id,mingc from sys_qud_shengf";
		RcdSet sf = db.query(sfsql);
		data.put("shengf", ConvertUtil.OtherJSONObjectToFastJSONArray(sf.toJsonArrayWithJsonObject()));
		// 市区
		String cssql = "select distinct shengf_id from sys_qud_chengs ";
		RcdSet sfv = db.query(cssql);
		JSONObject sfvobj = new JSONObject();
		for (int i = 0; i < sfv.size(); i++) {
			String sfid = sfv.getRcd(i).getString("shengf_id");
			String cssqlv = "select id,mingc from sys_qud_chengs where shengf_id=?";
			RcdSet csdata = db.query(cssqlv, sfid);
			sfvobj.put(sfid, ConvertUtil.OtherJSONObjectToFastJSONArray(csdata.toJsonArrayWithJsonObject()));
		}
		data.put("chengs", sfvobj);
		// 区域
		String quysql = "select distinct chengs_id from sys_qud_qux";
		RcdSet cityv = db.query(quysql);
		JSONObject quyobj = new JSONObject();
		for (int i = 0; i < cityv.size(); i++) {
			String cityid = cityv.getRcd(i).getString("chengs_id");
			String quysqlv = "select id,mingc from sys_qud_qux where chengs_id=?";
			RcdSet quydata = db.query(quysqlv, cityid);
			quyobj.put(cityid, ConvertUtil.OtherJSONObjectToFastJSONArray(quydata.toJsonArrayWithJsonObject()));
		}
		data.put("quy", quyobj);
		return R.clearAttachDirect(data);
	}

	/**
	 * @Description: 获取节点数据
	 */
	@Cacheable(value = CacheConfig.CACHE_PUBLIC + "#30#25",key="'abcd_'+#id")
	public R queryRegionById(String id) {
		System.out.println("test");
		String sql = "select * from sys_region where parentid=?";
		return R.SUCCESS_OPER(
				ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, id).toJsonArrayWithJsonObject()));
	}

	/**
	 * @Description:获取省份数据
	 */
	@Cacheable(value = CacheConfig.CACHE_PUBLIC + "#10#2")
	public R queryShengF(String[] exclude) {
		String sql = "select * from sys_qud_shengf";
		R res = new R();
		res.setClearAttach(true);
		res.setData(ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql).toJsonArrayWithJsonObject()));
		return res;
	}

	/**
	 * @Description:获取城市数据
	 */
	@Cacheable(value = CacheConfig.CACHE_PUBLIC_3h_1h)
	public R queryChengS(String sfid, String[] exclude) {
		String sql = "select * from sys_qud_chengs ";
		if (ToolUtil.isEmpty(sfid)) {
			sql = sql + " where 1=0";
		} else {
			sql = sql + " where shengf_id='" + sfid + "'";
		}
		R res = new R();
		res.setClearAttach(true);
		res.setData(db.query(sql).toJsonArrayWithJsonObject());
		return res;
	}

	/**
	 * @Description:获取区县数据
	 */
	@Cacheable(value = CacheConfig.CACHE_PUBLIC_3h_1h)
	public R queryQuX(String csid, String[] exclude) {
		String sql = "select * from sys_qud_qux";
		if (ToolUtil.isEmpty(csid)) {
			sql = sql + " where 1=0";
		} else {
			sql = sql + " where chengs_id='" + csid + "'";
		}
		R res = new R();
		res.setClearAttach(true);
		res.setData(db.query(sql).toJsonArrayWithJsonObject());
		return res;
	}
}
