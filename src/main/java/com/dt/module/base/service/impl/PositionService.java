package com.dt.module.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2018年5月16日 上午7:43:31
 * @Description: TODO
 */
@Service
public class PositionService extends BaseService {

	private static Logger _log = LoggerFactory.getLogger(PositionService.class);

	public R regeocoding(String lat, String lng, String type) {
		if (ToolUtil.isEmpty(type)) {
			type = "111";
		}
		JSONObject r = HttpKit
				.sendGetWithResp("http://gc.ditu.aliyun.com/regeocoding?l=29.87386,121.55027&type=" + type);
		return R.SUCCESS_OPER(JSONObject.parseObject(r.getString("result")));
	}

	public R queryZone(String lat, String lng) {
		if (ToolUtil.isOneEmpty(lat, lng)) {
			return R.FAILURE("经纬度异常");
		}
		String url = "http://recode.ditu.aliyun.com/dist_query?l=" + lat + "," + lng;
		JSONObject r = JSONObject.parseObject(HttpKit.sendGetWithResp(url).getString("result"));
		if (!"0".equals(r.getString("report"))) {
			return R.FAILURE("无数据");
		}
		String adcode = r.getString("ad_code");
		JSONObject res = new JSONObject();
		Rcd qux = db.uniqueRecord("select * from sys_qud_qux where guobm=?", adcode);
		// 区县
		if (qux == null) {
			return R.FAILURE("无数据");
		}
		res.put("qux", qux.getString("id"));
		res.put("quxmc", qux.getString("mingc"));
		// 城市
		Rcd chengs = db.uniqueRecord("select * from sys_qud_chengs where id=?", qux.getString("chengs_id"));
		if (chengs == null) {
			return R.FAILURE("无数据");
		}
		res.put("chengs", chengs.getString("id"));
		res.put("chengsmc", chengs.getString("mingc"));
		// 省
		Rcd shengf = db.uniqueRecord("select * from sys_qud_shengf where id=?", chengs.getString("shengf_id"));
		if (shengf == null) {
			return R.FAILURE("无数据");
		}
		res.put("shengf", shengf.getString("id"));
		res.put("shengfmc", shengf.getString("mingc"));
		_log.info("lat:" + lat + ",lng:" + lng + ",res:" + res.toJSONString());
		return R.SUCCESS_OPER(res);
	}

	/* 输入经纬度返回行政编码 */
	public static void main(String[] args) {
		PositionService r = new PositionService();
		System.out.println(r.regeocoding("29.87386", "121.55027", "111"));
		// System.out.println(r.getZone("29.87386", "121.55027"));
		// TODO Auto-generated method stub

	}
}
