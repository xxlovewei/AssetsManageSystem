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

import java.util.HashMap;
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
	public R saveDefMenus(String user_id, String id) {
		if (ToolUtil.isOneEmpty(user_id, id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		UpdateWrapper<SysUserInfo> ew = new UpdateWrapper<SysUserInfo>();
		ew.and(i -> i.eq("user_id", user_id));
		SysUserInfo user = new SysUserInfo();
		user.setSystem(id);
		baseMapper.update(user, ew);
		return R.SUCCESS_OPER();
	}

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

	/*
	 * @see
	 * com.dt.module.base.service.ISysUserInfoService#listByOpenId(java.lang.String)
	 */
	@Override
	public R selectByOpenId(String open_id) {
		// TODO Auto-generated method stub
		if (ToolUtil.isEmpty(open_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		QueryWrapper<SysUserInfo> ew = new QueryWrapper<SysUserInfo>();
		ew.and(i -> i.eq("open_id", open_id));
		Integer c = baseMapper.selectCount(ew);
		if (c == 1) {
			return R.SUCCESS_OPER(baseMapper.selectOne(ew));
		} else if (c == 0) {
			return R.FAILURE("不存在");
		} else {
			return R.FAILURE("存在多个");
		}

	
	}

	/*
	 * (non Javadoc)
	 * @see com.dt.module.base.service.ISysUserInfoService#listUserRoles(java.lang.
	 * String)
	 */
	@Override
	public List<HashMap<String, Object>> listUserRoles(String user_id) {
		// TODO Auto-generated method stub
		System.out.println(this.baseMapper.listUserRoles(user_id));
		return this.baseMapper.listUserRoles(user_id);
	}

}
