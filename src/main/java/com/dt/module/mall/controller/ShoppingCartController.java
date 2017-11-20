package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.module.mall.service.ShoppingCartService;

/** 
 * @author: algernonking
 * @date: 2017年11月20日 下午12:08:48 
 * @Description: TODO 
 */
@Controller
@RequestMapping("/api")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
}

