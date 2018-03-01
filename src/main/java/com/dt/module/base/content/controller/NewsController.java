package com.dt.module.base.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.ResData;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.content.service.ContentService;
import com.dt.module.base.content.service.NewsService;;

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
	@Res
	@Acl
	public ResData queryNewsById(String id) {
		return null;
	}
	/**
	 * @Description: 删除新闻
	 */
	@RequestMapping(value = "/news/deleteNews.do")
	@Res
	@Acl
	public ResData deleteNews(String id) {
		return newsService.deleteNews(id);
	}
	/**
	 * @Description: 发布新闻
	 */
	@RequestMapping(value = "/news/publishNews.do")
	@Res
	@Acl
	public ResData publishNews() {
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
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryNews() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int value = newsService.queryTotalCount(ps);
		JSONObject res = new JSONObject();
		res.put("total", value);
		return ResData.SUCCESS_OPER(res);
	}
	/**
	 * @Description: 查询新闻页数
	 */
	@RequestMapping(value = "/news/queryPage.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryPage(String pageSize) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int value = newsService.queryTotalCount(ps, ConvertUtil.toInt(pageSize, -1));
		JSONObject res = new JSONObject();
		res.put("total", value);
		return ResData.SUCCESS_OPER(res);
	}
 
	
	/**
	 * @Description: 查询新闻分页,用于datatable
	 */
	@RequestMapping(value = "/news/queryNewsByPage.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryNewsByDatatable(String start, String length, String pageSize, String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		
		ResData rsdata = newsService.queryNews(ps, pagesize, pageindex);
		int count = contentService.queryContentCount(ps, ContentService.TYPE_NEWS);
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
