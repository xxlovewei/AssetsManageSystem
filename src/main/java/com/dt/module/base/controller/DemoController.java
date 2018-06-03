package com.dt.module.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2018年5月26日 下午2:37:40
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class DemoController extends BaseController {

	@RequestMapping(value = "/demo/query.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER)
	public R query(String size, String index) {
		RcdSet rs = db.queryPage("select * from sys_user_info ", ToolUtil.toInt(size), ToolUtil.toInt(index));
		System.out.println("getCurrentPageIndex" + rs.getCurrentPageIndex());
		System.out.println("getPageCount" + rs.getPageCount());
		System.out.println("getTotalRowCount" + rs.getTotalRowCount());
		
		
		System.out.println(rs.getCurrentPageIndex());

		return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
	}

}
