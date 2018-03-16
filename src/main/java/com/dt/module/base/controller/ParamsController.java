package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.ParamsService;

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
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW,info="查询参数")
	public R queryParams() {
		return paramsService.queryParams();
	}
	@RequestMapping(value = "/params/queryParamsById.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW,info="根据Id查询参数")
	public R queryParamsById(String id) {
		return paramsService.queryParamsById(id);
	}
	@RequestMapping(value = "/params/saveParams.do")
	@ResponseBody
	@Acl(info="保存参数")
	public R saveParams() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return paramsService.addParams(ps);
		} else {
			return paramsService.updateParams(ps);
		}
	}
	@RequestMapping(value = "/params/deleteParams.do")
	@ResponseBody
	@Acl(info="删除参数")
	public R deleteParams(String id) {
		return paramsService.deleteParams(id);
	}
}
