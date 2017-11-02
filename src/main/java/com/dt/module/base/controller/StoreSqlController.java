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
	@Res
	@Acl
	public ResData queryStoreSql() {
		return storeSqlService.queryStoreSql(null);
	}
	@RequestMapping(value = "/store/queryStoreSqlById.do")
	@Res
	@Acl
	public ResData queryStoreSqlById(String store_id) {
		if (ToolUtil.isEmpty(store_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		return storeSqlService.queryStoreSqlById(store_id);
	}
	@RequestMapping(value = "/store/saveStoreSql.do")
	@Res
	@Acl
	public ResData saveStoreSql() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("STORE_ID");
		if (ToolUtil.isEmpty(id)) {
			return storeSqlService.addStoreSql(ps, null);
		} else {
			return storeSqlService.updateStoreSql(ps, null);
		}
	}
	@RequestMapping(value = "/store/deleteStoreSql.do")
	@Res
	@Acl
	public ResData deleteStoreSql(String store_id) {
		if (ToolUtil.isEmpty(store_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		return storeSqlService.deleteStoreSql(store_id);
	}
	@RequestMapping(value = "/store/commandAction.do")
	@Res
	@Acl
	public ResData commandAction() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return storeSqlService.commandAction(ps, getUserId(), StoreSqlService.ACL_USER);
	}
	@RequestMapping(value = "/store/commandActionForPublic.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData commandActionForPublic() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return storeSqlService.commandAction(ps, getUserId(), StoreSqlService.ACL_PUBLIC);
	}
}
