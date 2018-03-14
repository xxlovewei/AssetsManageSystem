package com.dt.core.shiro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dt.core.shiro.ShiroUser;
import com.dt.core.shiro.inter.IShiro;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.module.base.entity.User;
import com.dt.module.base.service.UserService;
import com.dt.module.db.DB;

@Service
public class ShiroServiceImpl implements IShiro {
	@Autowired
	DB db;

	public static IShiro me() {
		return SpringContextUtil.getBean(IShiro.class);
	}

	@Override
	public User user(String account) {
		return new User();
	}

	@Override
	public ShiroUser shiroUser(User user) {
		ShiroUser shiroUser = new ShiroUser();
		shiroUser.setId(user.getUserId()); // 账号id
		shiroUser.setAccount(user.getAccount());// 账号
		shiroUser.setName(user.getName());
		List<String> roleList = new ArrayList<String>();
		List<String> roleNameList = new ArrayList<String>();
		// 角色集合
		HashMap<String, String> rmap = user.getRolsSet();
		Iterator<?> iter = rmap.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			roleList.add((String) key);
			roleNameList.add((String) val);
		}
		shiroUser.setRoleList(roleList);
		shiroUser.setRoleNames(roleNameList);
		return shiroUser;
	}

	@Override
	public List<String> findPermissionsByRoleId(String roleId) {
		return UserService.me().findPermissionsByRoleId(roleId);
	}

	@Override
	public String findRoleNameByRoleId(String roleId) {
		return UserService.me().findRoleNameByRoleId(roleId);
	}

	@Override
	public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
		String credentials = user.getPassword();
		// 密码加盐处理
		String source = user.getSalt();
		ByteSource credentialsSalt = new Md5Hash(source);

		// 参数:用户名,数据库中密码,username+sale,realmName
		return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
	}
}
