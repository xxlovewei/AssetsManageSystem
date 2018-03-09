package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.ModuleItemMapService;

@Controller
@RequestMapping(value = "/api")
public class ModuleController extends BaseController {
	@Autowired
	ModuleItemMapService moduleItemMapService;

	@ResponseBody
	@Acl
	@RequestMapping(value = "/module/queryModuleItemMap.do")
	public R queryModuleItem(String module_id) {
		return moduleItemMapService.queryModuleItem(module_id);
	}
	@ResponseBody
	@Acl
	@RequestMapping(value = "/module/updateModuleItemMap.do")
	public R updateModuleItemMap(String module_id, String items) {
		return moduleItemMapService.updateModuleItem(module_id, items);
	}
}
