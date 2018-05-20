package com.dt.module.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.base.BaseController;

/**
 * @author: algernonking
 * @date: 2018年5月20日 上午7:57:51
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ProdController extends BaseController {
	public static String ITME_TYPE_OFFLINE = "offine";// 下架
	public static String ITME_TYPE_ONLINE = "online";// 出售中
	public static String ITME_TYPE_FINISH = "finish";// 已出售
	

}
