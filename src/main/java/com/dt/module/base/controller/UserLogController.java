package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.UserLogService;

/**
 * @author: algernonking
 * @date: Oct 26, 2017 10:06:21 AM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class UserLogController extends BaseController {
	@Autowired
	private UserLogService userLogService;

	@RequestMapping("/user/queryAccessLog.do")
	@Res
	@Acl
	public ResData queryAccessLog(String start, String length, String pageSize, String pageIndex) {
		JSONObject respar = DBUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		ResData rsdata = userLogService.queryAccessLog(getUserId(), ps, pagesize, pageindex);
		int pageCnt = userLogService.queryAccessLogPageCount(getUserId(), ps, pagesize);
		JSONArray data = rsdata.getDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", pageCnt);
		retrunObject.put("iTotalDisplayRecords", pageCnt);
		retrunObject.put("data", data);
		ResData res = new ResData();
		res.setClearStatus(true);
		res.setData(retrunObject);
		return res;
	}
}
