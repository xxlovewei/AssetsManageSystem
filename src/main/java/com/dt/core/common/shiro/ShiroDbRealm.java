package com.dt.core.common.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.common.util.ToolUtil;
import com.dt.module.base.entity.User;
import com.dt.module.base.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger _log = LoggerFactory.getLogger(ShiroDbRealm.class);

	// 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
	// private UserService userService = new UserServiceImpl();
	/**
	 * 提供账户信息返回认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UserService userService = UserService.me();
		IShiro shiroService = ShiroService.me();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		_log.info("###################Action 登录认证#################Username:" + token.getUsername() + ",Host:" + token.getHost() + ",IsRememberMe:"
				+ token.isRememberMe());
		User user = userService.getUser(token.getUsername());
		ShiroUser shiroUser = shiroService.shiroUser(user);
		SimpleAuthenticationInfo info = shiroService.info(shiroUser, user, super.getName());
		return info;
	}
	/**
	 * 权限认证,SecurityUtils.getSubject().isPermitted（）时调用,一般@RequiresPermissions会调用
	 */
	/**
	 * 提供用户信息返回权限信息
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
			_log.info("角色ID:" + roleId);
			List<String> permissions = shiroService.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (ToolUtil.isNotEmpty(permission)) {
						permissionSet.add(permission);
					}
				}
			}
			permissionSet.add("/api/hrm/employeeQueryList.do");
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
