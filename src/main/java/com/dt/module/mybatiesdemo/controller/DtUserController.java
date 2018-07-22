package com.dt.module.mybatiesdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.mall.service.OrderService;
import com.dt.module.mybatiesdemo.service.IDtUserService;

/** 
 * @author: algernonking
 * @date: 2018年7月22日 上午9:09:32 
 * @Description: TODO 
 */
@Controller
@RequestMapping("/api")
public class DtUserController  extends BaseController {

	@Autowired
	IDtUserService dtUserService;
	
	@RequestMapping(value = "/mydemo/queryByid.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW, info = "创建订单")
	public R createOrder(String name) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		dtUserService.deleteUserByUsername(name);
		return R.SUCCESS_OPER();
	}
}

