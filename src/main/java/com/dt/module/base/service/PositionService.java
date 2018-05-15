package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2018年5月16日 上午7:43:31
 * @Description: TODO
 */
@Service
public class PositionService extends BaseService {

	public R regeocoding(String lat, String lng, String type) {
		if (ToolUtil.isEmpty(type)) {
			type = "111";
		}
		JSONObject r = HttpKit
				.sendGetWithResp("http://gc.ditu.aliyun.com/regeocoding?l=29.87386,121.55027&type=" + type);
		return R.SUCCESS_OPER(JSONObject.parseObject(r.getString("result")));
	}

	/* 输入经纬度返回行政编码 */
	public static void main(String[] args) {
		PositionService r = new PositionService();
		System.out.println(r.regeocoding("29.87386", "121.55027", "111"));
		// TODO Auto-generated method stub

	}
}
