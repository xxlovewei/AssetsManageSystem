package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.StoreSqlService;

/**
 * @author: algernonking
 * @date: Nov 1, 2017 8:50:10 AM
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class StoreSqlController extends BaseController {
	@Autowired
	StoreSqlService storeSqlService;

	@RequestMapping(value = "/store/queryStoreSql.do")
	@ResponseBody
	@Acl
	public R queryStoreSql() {
		return storeSqlService.queryStoreSql(null);
	}
	@RequestMapping(value = "/store/queryStoreSqlById.do")
	@ResponseBody
	@Acl
	public R queryStoreSqlById(String store_id) {
		if (ToolUtil.isEmpty(store_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		return storeSqlService.queryStoreSqlById(store_id);
	}
	@RequestMapping(value = "/store/saveStoreSql.do")
	@ResponseBody
	@Acl
	public R saveStoreSql() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("store_id");
		if (ToolUtil.isEmpty(id)) {
			return storeSqlService.addStoreSql(ps, null);
		} else {
			return storeSqlService.updateStoreSql(ps, null);
		}
	}
	@RequestMapping(value = "/store/deleteStoreSql.do")
	@ResponseBody
	@Acl
	public R deleteStoreSql(String store_id) {
		if (ToolUtil.isEmpty(store_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		return storeSqlService.deleteStoreSql(store_id);
	}
	@RequestMapping(value = "/store/commandAction.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R commandAction() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return storeSqlService.commandAction(ps, getUserId(), StoreSqlService.ACL_USER);
	}
	@RequestMapping(value = "/store/commandActionForPublic.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_ALLOW)
	public R commandActionForPublic() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return storeSqlService.commandAction(ps, getUserId(), StoreSqlService.ACL_PUBLIC);
	}
}
