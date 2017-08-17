package com.dt.module.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.content.service.NewsService;
import com.dt.module.content.service.ContentService;;

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
		String id = ps.getString("ID");
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
	@Acl(value = "allow")
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
	@Acl(value = "allow")
	public ResData queryPage(String pageSize) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int value = newsService.queryTotalCount(ps, ConvertUtil.toInt(pageSize, -1));
		JSONObject res = new JSONObject();
		res.put("total", value);
		return ResData.SUCCESS_OPER(res);
	}
	/**
	 * @Description: 查询新闻
	 */
	@RequestMapping(value = "/news/queryNews.do")
	@Res
	@Acl(value = "allow")
	public ResData queryNews(String pageSize, String pageIndex) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return newsService.queryNews(ps, ConvertUtil.toInt(pageSize, -1), ConvertUtil.toInt(pageIndex, -1));
	}
	/**
	 * @Description: 查询新闻分页,用于datatable
	 */
	@RequestMapping(value = "/news/queryNewsByDatatable.do")
	@Res
	@Acl(value = "allow")
	public ResData queryNewsByDatatable(String start, String length) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		int startV = ConvertUtil.toInt(start);
		int lengthV = ConvertUtil.toInt(length);
		int pageIndex = startV / lengthV + 1;
		System.out.println("pageSize" + length);
		System.out.println("pageIndex" + pageIndex);
		ResData rsdata = newsService.queryNews(ps, ConvertUtil.toInt(length, -1), ConvertUtil.toInt(pageIndex, -1));
		int pageCnt = contentService.queryContentPageCount(ps, ContentService.TYPE_NEWS, ConvertUtil.toInt(length, -1));
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
