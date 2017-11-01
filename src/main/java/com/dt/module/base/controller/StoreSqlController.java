package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
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
public class StoreSqlController {
	@Autowired
	StoreSqlService storeSqlService;

	@RequestMapping(value = "/store/commandQuery.do")
	@Res
	@Acl
	public ResData commandQuery() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		return storeSqlService.commandQuery(ps);
	}
}
