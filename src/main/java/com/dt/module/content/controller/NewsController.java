package com.dt.module.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.content.service.NewsService;

import net.sf.json.JSONObject;

/**
 * @author: algernonking
 * @date: 2017年8月11日 下午1:00:33
 * @Description: TODO
 * pageSize
 * pageIndex
 * sdate
 * edate
 * sort
 */
@Controller
@RequestMapping(value = "/api")
public class NewsController extends BaseController {
	@Autowired
	NewsService newsService;

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
		return null;
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
}
