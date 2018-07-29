package com.dt.module.base.service;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dt.core.common.base.R;
import com.dt.module.base.entity.SysMenus;
import com.dt.module.base.entity.SysUserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

	List<SysMenus> listMyMenus(String user_id);
	R saveDefMenus(String user_id, String id); 
	R selectByOpenId(String open_id);
    R modifyPassword(String user_id, String pwd);
    R addUser(SysUserInfo user);
    List<HashMap<String, Object>> listUserRoles(String user_id);
	
 
}
