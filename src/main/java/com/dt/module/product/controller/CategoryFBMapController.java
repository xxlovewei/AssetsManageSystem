package com.dt.module.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.base.BaseController;
import com.dt.module.product.service.CategoryFBMapService;

/**
 * @author: algernonking
 * @date: 2017年11月16日 下午3:41:02
 * @Description: 前后台映射
 */
@Controller
@RequestMapping("/api")
public class CategoryFBMapController extends BaseController {
	@Autowired
	CategoryFBMapService categoryFBMapService;

	
}
