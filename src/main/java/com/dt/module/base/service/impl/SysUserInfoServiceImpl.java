package com.dt.module.base.service.impl;

import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.entity.SysMenus;
import com.dt.module.base.entity.SysRegion;
import com.dt.module.base.entity.SysUserInfo;
import com.dt.module.base.mapper.SysUserInfoMapper;
import com.dt.module.base.service.ISysUserInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

	// 显示我的菜单
	public List<SysMenus> listMyMenus(String user_id) {
		return this.baseMapper.listMyMenus(user_id);
	}

	// 修改用户密码
	public R modifyPassword(String user_id, String pwd) {
		if (ToolUtil.isOneEmpty(user_id, pwd)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		UpdateWrapper<SysUserInfo> ew = new UpdateWrapper<SysUserInfo>();
		ew.and(i -> i.eq("user_id", user_id));
		SysUserInfo user = new SysUserInfo();
		user.setPwd(pwd);
		baseMapper.update(user, ew);
		return R.SUCCESS_OPER();

	}

}
