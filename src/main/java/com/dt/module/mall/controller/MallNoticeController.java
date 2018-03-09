package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.NoticeService;

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
	@ResponseBody
	@Acl(value = Acl.TYPE_ALLOW, info = "商城获取公告")
	public R queryNotice(String is_show, String start, String length, String pageSize, String pageIndex) {

		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		R rsdata = noticeService.queryNotice(ps, NoticeService.TYPE_MALL, is_show, null, pagesize, pageindex);
		int pageCnt = noticeService.queryNoticeCount(ps, NoticeService.TYPE_MALL, is_show, null);

		JSONArray data = rsdata.queryDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", pageCnt);
		retrunObject.put("iTotalDisplayRecords", pageCnt);
		retrunObject.put("data", data);
		R res = new R();
		res.setClearAttach(true);
		res.setData(retrunObject);
		return res;

	}

}
