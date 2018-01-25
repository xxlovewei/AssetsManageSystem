package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.base.service.NoticeService;
import com.dt.tool.lang.TypedHashMap;
import com.dt.tool.util.DBUtil;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2017年11月16日 上午9:40:54
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class MallNoticeController extends BaseController {

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "/mallnotice/queryNotice.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "商城获取公告")
	public ResData queryNotice(String is_show, String start, String length, String pageSize, String pageIndex) {

		JSONObject respar = DBUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		ResData rsdata = noticeService.queryNotice(ps, NoticeService.TYPE_MALL, is_show, null, pagesize, pageindex);
		int pageCnt = noticeService.queryNoticeCount(ps, NoticeService.TYPE_MALL, is_show, null);

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
