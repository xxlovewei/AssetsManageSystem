package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
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
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "获取公告")
	public R queryNoticeById(String id) {
		return noticeService.queryNoticeById(id);
	}

	
	@RequestMapping(value = "/notice/saveNotice.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "保存公告")
	public R saveNotice() {

		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return noticeService.addNotice(ps, getUserId());
		} else {
			return noticeService.updateNotice(ps);
		}
	}

	@RequestMapping(value = "/notice/delNotice.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "删除公告")
	public R delNotice(String id) {
		return noticeService.deleteNotice(id);
	}

}
