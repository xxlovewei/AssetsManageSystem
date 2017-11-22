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
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.mall.service.MallProdService;

/**
 * @author: algernonking
 * @date: 2017年11月17日 下午1:53:22
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class MallClassProdController extends BaseController {

	@Autowired
	private MallProdService classProdService;

	@RequestMapping("/class/queryClassProdNotSel.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "选择加入分类单产品")
	public ResData queryClassProdNotSel(String cat_id, String class_id, String start, String length, String pageSize,
			String pageIndex) {

		if (ToolUtil.isOneEmpty(cat_id, class_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}

		JSONObject respar = DBUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");

		ResData rsdata = classProdService.queryClassProdNotSel(cat_id, class_id, pagesize, pageindex);
		int count = classProdService.queryClassProdNotSelCount(cat_id, class_id);
		JSONArray data = rsdata.getDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", count);
		retrunObject.put("iTotalDisplayRecords", count);
		retrunObject.put("data", data);
		ResData res = new ResData();
		res.setClearStatus(true);
		res.setData(retrunObject);
		return res;

	}

	@RequestMapping("/class/queryClassProd.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "查询分类单产品")
	public ResData queryClassProd(String withoutcount, String class_id, String start, String length, String pageSize,
			String pageIndex) {

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		 
		JSONObject respar = DBUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");

		ResData rsdata = classProdService.queryClassProd(ps, class_id, pagesize, pageindex);
		int count = 0;
		if (ToolUtil.isNotEmpty(withoutcount) && withoutcount.equals("Y")) {
			count = 0;
		} else {
			count = classProdService.queryClassProdCount(ps, class_id);
		}
		JSONArray data = rsdata.getDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", count);
		retrunObject.put("iTotalDisplayRecords", count);
		retrunObject.put("data", data);
		ResData res = new ResData();
		res.setClearStatus(true);
		res.setData(retrunObject);
		return res;

	}

}