package com.dt.core.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.module.base.entity.User;
import com.dt.module.base.service.UserService;
import com.dt.tool.util.ToolUtil;

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger _log = LoggerFactory.getLogger(ShiroDbRealm.class);
 
	/**
	 * 提供账户信息返回认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
 
		UserService userService = UserService.me();
		IShiro shiroService = ShiroService.me();
		// authcToken 中储存着输入的用户名和密码
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		_log.info("###################Action 登录认证#################");
		_log.info("Username:" + token.getUsername());
		// 从数据库中获取密码
		User user = userService.getUser(token.getUsername());
		if (ToolUtil.isEmpty(user.userId)) {
			throw new UnknownAccountException();//// 没找到帐号
		}

		if (user.getIsLocked()) {
			throw new LockedAccountException(); // 帐号锁定
		}

		ShiroUser shiroUser = shiroService.shiroUser(user);
		// 进行对比,成功返回info,shiroUser
		SimpleAuthenticationInfo info = shiroService.info(shiroUser, user, super.getName());
		return info;
	}

	/**
	 * 提供用户信息返回权限信息,SecurityUtils.getSubject().isPermitted（）时调用,一般@RequiresPermissions会调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		_log.info("###################Action 权限认证#################");
		IShiro shiroService = ShiroService.me();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		List<String> roleList = shiroUser.getRoleList();
		Set<String> permissionSet = new HashSet<String>();
		Set<String> roleNameSet = new HashSet<String>();
		// 处理每个角色的权限
		for (String roleId : roleList) {
			// _log.info("角色ID:" + roleId);
			List<String> permissions = shiroService.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (ToolUtil.isNotEmpty(permission)) {
						//_log.info(permission);
						permissionSet.add(permission);
					}
				}
			}
			String roleName = shiroService.findRoleNameByRoleId(roleId);
			roleNameSet.add(roleName);
		}
		// 将权限名称提供给info
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionSet);
		info.addRoles(roleNameSet);
		return info;
	}
}
