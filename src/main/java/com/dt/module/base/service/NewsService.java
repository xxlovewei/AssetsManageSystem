package com.dt.module.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月11日 下午5:25:44
 * @Description: TODO
 */
@Service
public class NewsService extends BaseService {
	@Autowired
	ContentService contentService;

	/**
	 * @Description: 添加新闻
	 */
	public R addNews(TypedHashMap<String, Object> ps) {
		return contentService.addContent(ps, ContentService.TYPE_NEWS);
	}

	/**
	 * @Description: 修改新闻
	 */
	public R updateNews(TypedHashMap<String, Object> ps) {
		return contentService.updateContent(ps);
	}

	/**
	 * @Description: 删除新闻
	 */
	public R deleteNews(String id) {
		return contentService.deleteContent(id, ContentService.TYPE_NEWS);
	}

	/**
	 * @Description: 根据ID查找新闻
	 */
	public R queryNewsById(String id) {
		return contentService.queryContentById(id);
	}

	/**
	 * @Description: 新闻数量
	 */
	public int queryTotalCount(TypedHashMap<String, Object> ps) {
		return contentService.queryContentCount(ps, ContentService.TYPE_NEWS);
	}

	/**
	 * @Description: 新闻页数
	 */
	public int queryTotalCount(TypedHashMap<String, Object> ps, int pageSize) {
		return contentService.queryContentPageCount(ps, ContentService.TYPE_NEWS, pageSize);
	}

	/**
	 * @Description: 查找新闻
	 */
	public R queryNews(TypedHashMap<String, Object> ps, int pageSize, int pageIndex) {
		return contentService.queryContentPage(ps, pageSize, pageIndex, ContentService.TYPE_NEWS);
	}
}
