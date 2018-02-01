package com.dt.module.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.sql.Update;
import com.dt.dao.util.TypedHashMap;
import com.dt.tool.util.ToolUtil;

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
		JSONObject res = userService.queryUserById(user_id);
		if (ToolUtil.isEmpty(res)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(res);
		}
	}
	/**
	 * @Description: 保存用户通用设置数据
	 */
	public ResData saveCommonSettings(String user_id, TypedHashMap<String, Object> ps) {
		String system = ps.getString("system");
		if (ToolUtil.isNotEmpty(system)) {
			Update systemups = new Update("sys_user_info");
			systemups.set("system", system);
			systemups.where().and("user_id=?", user_id);
			db.execute(systemups);
		}
		return ResData.SUCCESS_OPER();
	}
}
