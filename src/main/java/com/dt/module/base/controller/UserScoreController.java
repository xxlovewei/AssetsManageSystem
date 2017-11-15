package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
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
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "获取积分详情况")
	public ResData queryScoreDtl() {
		userScoreService.queryScore(getUserId());
		return ResData.SUCCESS_OPER();
	}

	@RequestMapping("/user/qdScore.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "签到")
	public ResData qdScore() {
		int v = userScoreService.queryTodayFlagCount(getUserId(), "qd");
		if (v > 0) {
			return ResData.FAILURE("已经签到");	
		} else {
			userScoreService.addScore(getUserId(), 1, "签到", "qd");
			return ResData.SUCCESS("签到成功");
		}

	}

}
