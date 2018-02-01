package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.dao.util.TypedHashMap;
import com.dt.module.base.service.ParamsService;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: 2017年8月6日 下午3:22:48
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class ParamsController {
	@Autowired
	ParamsService paramsService;

	@RequestMapping(value = "/params/queryParams.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryParams() {
		return paramsService.queryParams();
	}
	@RequestMapping(value = "/params/queryParamsById.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW)
	public ResData queryParamsById(String id) {
		return paramsService.queryParamsById(id);
	}
	@RequestMapping(value = "/params/saveParams.do")
	@Res
	@Acl
	public ResData saveParams() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return paramsService.addParams(ps);
		} else {
			return paramsService.updateParams(ps);
		}
	}
	@RequestMapping(value = "/params/deleteParams.do")
	@Res
	@Acl
	public ResData deleteParams(String id) {
		return paramsService.deleteParams(id);
	}
}
