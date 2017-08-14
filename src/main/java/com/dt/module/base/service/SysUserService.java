package com.dt.module.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月14日 上午7:44:33
 * @Description: TODO
 */
@Service
public class SysUserService extends BaseService {
	@Autowired
	private UserService userService;

	/**
	 * @Description: 新增用户
	 */
	public ResData addSysUser(TypedHashMap<String, Object> ps) {
		return userService.addUser(ps, UserService.USER_TYPE_SYS);
	}
	/**
	 * @Description: 删除用户
	 */
	public ResData deleteSysUser(String user_id) {
		return userService.deleteUser(user_id);
	}
	/**
	 * @Description: 修改用户
	 */
	public ResData updateSysUser(TypedHashMap<String, Object> ps) {
		return userService.updateUser(ps, UserService.USER_TYPE_SYS);
	}
	/**
	 * @Description: 根据user_id查找
	 */
	public ResData queryUserById(String user_id) {
		return userService.queryUserById(user_id);
	}
}
