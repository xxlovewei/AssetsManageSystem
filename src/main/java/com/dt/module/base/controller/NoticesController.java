package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.NoticeService;

/**
 * @author: algernonking
 * @date: 2017年11月16日 上午11:14:23
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class NoticesController extends BaseController {

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "/notice/queryNoticeById.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "获取公告")
	public ResData queryNoticeById(String id) {
		return noticeService.queryNoticeById(id);
	}

	
	@RequestMapping(value = "/notice/saveNotice.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "保存公告")
	public ResData saveNotice() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("ID");
		if (ToolUtil.isEmpty(id)) {
			return noticeService.addNotice(ps, getUserId());
		} else {
			return noticeService.updateNotice(ps);
		}
	}

	@RequestMapping(value = "/notice/delNotice.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "删除公告")
	public ResData delNotice(String id) {
		return noticeService.deleteNotice(id);
	}

}