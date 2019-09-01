package com.dt.module.zb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.db.ZB;

/**
 * @author: algernonking
 * @date: Mar 15, 2019 8:14:35 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ZBGroupService extends BaseController {

	@Autowired
	ZB zb;

	@RequestMapping("/zb/queryGroupItems.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryGroupItems(String gid) {
		// status:0启用 1停用
		if (ToolUtil.isEmpty(gid)) {
			return R.SUCCESS_NO_DATA();
		}
		String sql = "select bb.* from hosts_templates aa,hosts bb where aa.hostid=bb.hostid and aa.templateid in(select  a.hostid  from hosts a,hosts_groups b where a.hostid=b.hostid and b.groupid=?)";
		return R.SUCCESS_OPER(zb.query(sql, gid).toJsonArrayWithJsonObject());
	}

	@RequestMapping("/product/queryGroupHz.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R queryGroupHz() {

		JSONArray res = new JSONArray();

		return R.SUCCESS_OPER();
	}
	// public
//	select * from hosts_templates aa  where templateid in(select  a.hostid  from hosts a,hosts_groups b where a.hostid=b.hostid and b.groupid=10)

}
