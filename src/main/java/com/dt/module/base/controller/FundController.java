package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.module.base.service.FundService;

/**
 * @author: algernonking
 * @date: 2018年5月24日 下午6:17:27
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class FundController extends BaseController {

	@Autowired
	FundService fundService;

	@ResponseBody
	@Acl(info = "查询我到提现记录", value = Acl.ACL_USER)
	@RequestMapping(value = "/user/queryMyFundTix.do")
	public R queryMyFundTix() {
		return fundService.queryMyFundTix(null);
	}

	@ResponseBody
	@Acl(info = "提现操作", value = Acl.ACL_USER)
	@RequestMapping(value = "/user/actionMyTix.do")
	public R actionMyTix(String je) {
		Double jev = ConvertUtil.toDouble(je, 0.00);

		return  null;
	}

}
