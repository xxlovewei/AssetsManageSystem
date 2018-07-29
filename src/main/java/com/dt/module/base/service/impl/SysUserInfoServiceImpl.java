package com.dt.module.base.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Update;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.bus_enum.userTypeEnum;
import com.dt.module.base.entity.SysMenus;
import com.dt.module.base.entity.SysUserInfo;
import com.dt.module.base.mapper.SysUserInfoMapper;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.db.DB;

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

	@Autowired
	DB db;

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
	 * 
	 * @see com.dt.module.base.service.ISysUserInfoService#listUserRoles(java.lang.
	 * String)
	 */
	@Override
	public List<HashMap<String, Object>> listUserRoles(String user_id) {
		// TODO Auto-generated method stub
		System.out.println(this.baseMapper.listUserRoles(user_id));
		return this.baseMapper.listUserRoles(user_id);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: addUser
	 * 
	 * @Description: TODO
	 * 
	 * @param user
	 * 
	 * @return
	 * 
	 * @see
	 * com.dt.module.base.service.ISysUserInfoService#addUser(com.dt.module.base.
	 * entity.SysUserInfo)
	 */
	@Override
	public R addUser(SysUserInfo user) {
		// TODO Auto-generated method stub
		String user_type = user.getUserType();
		if (ToolUtil.isEmpty(user_type)) {
			return R.FAILURE("请选择用户类型");
		}

		// 密码
		if (ToolUtil.isEmpty(user.getPwd())) {
			user.setPwd(ToolUtil.getUUID());
		}

		// id
		if (ToolUtil.isEmpty(user.getEmplId())) {
			user.setEmplId(ToolUtil.getUUID());
		}

		// username
		if (ToolUtil.isEmpty(user.getUserName())) {
			user.setEmplId(ToolUtil.getUUID());
		} else {
			QueryWrapper<SysUserInfo> queryWrapper = new QueryWrapper<SysUserInfo>();
			queryWrapper.eq("user_name", user.getUserName());
			int c = this.baseMapper.selectCount(queryWrapper);
			if (c > 0) {
				return R.FAILURE("登录名重复,请重新输入");
			}
		}
		if (userTypeEnum.SYSTEM.getValue().equals(user_type)) {
		} else if (userTypeEnum.EMPL.getValue().equals(user_type)) {
			R r = getEmplNextId();
			if (r.isFailed()) {
				return r;
			}
			user.setEmplId(r.getData().toString());
		} else if (userTypeEnum.CRM.getValue().equals(user_type)) {
		} else if (userTypeEnum.WX.getValue().equals(user_type)) {
		}

		this.baseMapper.insert(user);
		return R.SUCCESS_OPER();
	}

	public R getEmplNextId() {

		Rcd seqrs = db.uniqueRecord(
				"select case when value is null then '50' else value end seq from sys_params where id='sys_empl_no'");
		if (ToolUtil.isEmpty(seqrs)) {
			return R.FAILURE("获取员工编号错误,无法生成员工.");
		}
		String empl_id = (ConvertUtil.toInt(seqrs.getString("seq")) + 1) + "";
		Update me = new Update("sys_params");
		me.set("value", empl_id);
		me.where().and("id=?", "sys_empl_no");
		db.execute(me);
		return R.SUCCESS_OPER(ConvertUtil.formatIntToString(empl_id, 6, 100));
	}

}
