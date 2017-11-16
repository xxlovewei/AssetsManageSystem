package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
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
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "商城获取公告")
	public ResData queryNotice() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return noticeService.queryNotice(ps, NoticeService.TYPE_MALL, "Y", null);
	}

}
