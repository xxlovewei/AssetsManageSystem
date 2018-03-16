package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
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
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "选择加入分类单产品")
	public R queryClassProdNotSel(String cat_id, String class_id, String start, String length, String pageSize,
			String pageIndex) {

		if (ToolUtil.isOneEmpty(cat_id, class_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");

		R rsdata = classProdService.queryClassProdNotSel(cat_id, class_id, pagesize, pageindex);
		int count = classProdService.queryClassProdNotSelCount(cat_id, class_id);
		JSONArray data = rsdata.queryDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", count);
		retrunObject.put("iTotalDisplayRecords", count);
		retrunObject.put("data", data);
		R res = new R();
		res.setClearAttach(true);
		res.setData(retrunObject);
		return res;

	}

	@RequestMapping("/class/queryClassProd.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询分类单产品")
	public R queryClassProd(String withoutcount, String class_id, String start, String length, String pageSize,
			String pageIndex) {

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();

		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");

		R rsdata = classProdService.queryClassProd(ps, class_id, pagesize, pageindex);
		int count = 0;
		if (ToolUtil.isNotEmpty(withoutcount) && withoutcount.equals("Y")) {
			count = 0;
		} else {
			count = classProdService.queryClassProdCount(ps, class_id);
		}
		JSONArray data = rsdata.queryDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", count);
		retrunObject.put("iTotalDisplayRecords", count);
		retrunObject.put("data", data);
		R res = new R();
		res.setClearAttach(true);
		res.setData(retrunObject);
		return res;

	}

}
