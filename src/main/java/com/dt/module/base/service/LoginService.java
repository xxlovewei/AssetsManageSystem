package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;

/**
 * @author: algernonking
 * @date: 2017年8月7日 上午7:58:00
 * @Description:
 */
@Service
public class LoginService extends BaseService {
	/**
	 * @Description: 所有都登录最终统一转成user_id去判断
	 */
	public void login() {
	}
	/**
	 * @Description: 退出登录
	 */
	public void logout() {
	}
	/**
	 * @Description: 检查登录状态
	 */
	public void loginCheck() {
	}
}
