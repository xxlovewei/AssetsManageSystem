package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.FundService;
import com.dt.module.base.service.UserService;

/**
 * @author: algernonking
 * @date: 2018年5月25日 上午7:55:36
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class MyAccountController extends BaseController {

	@Autowired
	FundService fundService;

	@Autowired
	UserService userService;

	@ResponseBody
	@Acl(info = "查询我到提现记录", value = Acl.ACL_USER)
	@RequestMapping(value = "/user/queryMyFundTix.do")
	public R queryMyFundTix(String cnt) {
		return fundService.queryMyFundTix(null, cnt);
	}

	@ResponseBody
	@Acl(info = "查询提现记录", value = Acl.ACL_DENY)
	@RequestMapping(value = "/user/queryFundTix.do")
	public R queryFundTix(String user_id, String cnt) {
		return fundService.queryFundTix(user_id, null, cnt);
	}

	@ResponseBody
	@Acl(info = "查询我的资金变动情况", value = Acl.ACL_USER)
	@RequestMapping(value = "/user/queryMyFundChange.do")
	public R queryMyFundChange() {
		return fundService.queryMyFundChange();
	}

	@ResponseBody
	@Acl(info = "查询我的资料", value = Acl.ACL_USER)
	@RequestMapping(value = "/user/queryMyInfoDtl.do")
	public R queryMyInfoDtl(String addaddr, String addtixamount, String addjscard) {
		JSONObject res = userService.queryUserById(getUserId());
		res.put("pwd", "******");
		return R.SUCCESS_OPER(res);
	}

}
