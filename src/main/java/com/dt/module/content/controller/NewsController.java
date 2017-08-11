package com.dt.module.content.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.util.UuidUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月11日 下午1:00:33
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class NewsController extends BaseController {
	public ResData queryNews(String sdate, String edate, String type, String page_size, String pagenum) {
		return null;
	}
	public ResData queryNewById(String id) {
		return null;
	}
	public ResData deleteNew(String id) {
		return null;
	}
	/**
	 * @Description:发布新闻
	 */
	public ResData addNew(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("CT_CONTENT");
		me.set("ID", UuidUtil.getUUID());
		me.set("DELETED", "N");
		me.setIf("PROFILE", ps.getString("PROFILE", " "));
		me.setIf("MPIC", ps.getString("MPIC", " "));
		me.setIf("BODY", ps.getString("BODY", " "));
		me.setIf("MARK", ps.getString("mark", " "));
		me.setIf("TITLE", ps.getString("mark", " "));
		me.setIf("MPIC_LOC", ps.getString("MPIC_LOC", "center"));
		me.setIf("IS_TOP", ps.getString("IS_TOP", "0"));// 0不置顶,1置顶
		me.setIf("URLTYPE", ps.getString("URLTYPE", "local"));// jump跳转,local本地
		return null;
	}
	public ResData updateNew(String id) {
		return null;
	}
}
