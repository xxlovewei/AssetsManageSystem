package com.dt.module.base.service.impl;

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
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ContentService;
import com.dt.module.base.service.NewsService;;

/**
 * @author: algernonking
 * @date: 2017年8月11日 下午1:00:33
 * @Description: TODO pageSize pageIndex sdate edate sort
 */
@Controller
@RequestMapping(value = "/api")
public class NewsController extends BaseController {
	@Autowired
	NewsService newsService;
	@Autowired
	ContentService contentService;

	/**
	 * @Description: 根据ID查找新闻
	 */
	@RequestMapping(value = "/news/queryNewsById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "根据id查找新闻")
	public R queryNewsById(String id) {
		return null;
	}

	/**
	 * @Description: 删除新闻
	 */
	@RequestMapping(value = "/news/deleteNews.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "删除新闻")
	public R deleteNews(String id) {
		return newsService.deleteNews(id);
	}

	/**
	 * @Description: 发布新闻
	 */
	@RequestMapping(value = "/news/publishNews.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "发布新闻")
	public R publishNews() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return newsService.addNews(ps);
		} else {
			return newsService.updateNews(ps);
		}
	}

	/**
	 * @Description: 新闻数量
	 */
	@RequestMapping(value = "/news/queryCount.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询新闻数量")
	public R queryNews() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int value = newsService.queryTotalCount(ps);
		JSONObject res = new JSONObject();
		res.put("total", value);
		return R.SUCCESS_OPER(res);
	}

	/**
	 * @Description: 查询新闻页数
	 */
	@RequestMapping(value = "/news/queryPage.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "查询新闻页数")
	public R queryPage(String pageSize) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int value = newsService.queryTotalCount(ps, ConvertUtil.toInt(pageSize, -1));
		JSONObject res = new JSONObject();
		res.put("total", value);
		return R.SUCCESS_OPER(res);
	}

	/**
	 * @Description: 查询新闻分页,用于datatable
	 */
	@RequestMapping(value = "/news/queryNewsByPage.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "分页查询新闻")
	public R queryNewsByDatatable(String start, String length, String pageSize, String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");

		R rsdata = newsService.queryNews(ps, pagesize, pageindex);
		int count = contentService.queryContentCount(ps, ContentService.TYPE_NEWS);
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
