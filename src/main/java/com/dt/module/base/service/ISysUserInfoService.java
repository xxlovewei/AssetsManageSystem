package com.dt.module.base.service;

import com.dt.module.base.entity.SysMenus;
import com.dt.module.base.entity.SysUserInfo;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

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
	
}
