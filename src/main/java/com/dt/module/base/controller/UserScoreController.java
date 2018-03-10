package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.service.UserScoreService;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:45:38
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class UserScoreController extends BaseController {

	@Autowired
	private UserScoreService userScoreService;

	//暂未分页
	@RequestMapping("/user/queryScoreDtl.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "获取积分详情况")
	public R queryScoreDtl() {
		userScoreService.queryScore(getUserId());
		return R.SUCCESS_OPER();
	}

	@RequestMapping("/user/qdScore.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "签到")
	public R qdScore() {
		int v = userScoreService.queryTodayFlagCount(getUserId(), "qd");
		if (v > 0) {
			return R.FAILURE("已经签到");	
		} else {
			userScoreService.addScore(getUserId(), 1, "签到", "qd");
			return R.SUCCESS("签到成功");
		}

	}

}
