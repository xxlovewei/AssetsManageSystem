package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.util.ConvertUtil;

/**
 * @author: algernonking
 * @date: 2017年8月10日 下午7:42:50
 * @Description: TODO
 */
@Service
public class MenuRootService extends BaseService {
	/**
	 * @Description: 添加菜单
	 */
	public ResData addMenuRoot() {
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 更新菜单
	 */
	public ResData updateMenuRoot() {
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 删除菜单
	 */
	public ResData deleteMenuRoot() {
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 查询菜单
	 */
	public ResData queryMenuRoot() {
		return ResData.SUCCESS_OPER( db.query("select * from sys_menus order by sort").toJsonArrayWithJsonObject());
	}
}
