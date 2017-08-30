package com.dt.core.shiro;

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
import com.dt.module.base.entity.ShiroUser;
import com.dt.module.base.entity.User;
import com.dt.module.base.service.ShiroService;
import com.dt.module.base.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger _log = LoggerFactory.getLogger(ShiroDbRealm.class);

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UserService userService = UserService.me();
		IShiro shiroService = ShiroService.me();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		_log.info("Action:" + "登录认证,token:" + token);
		User user = userService.getUser(token.getUsername());
		ShiroUser shiroUser = shiroService.shiroUser(user);
		SimpleAuthenticationInfo info = shiroService.info(shiroUser, user, super.getName());
		return info;
	}
	/**
	 * 权限认证,SecurityUtils.getSubject().isPermitted（）时调用,一般@RequiresPermissions会调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		_log.info("Action:" + "权限认证");
		IShiro shiroService = ShiroService.me();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		System.out.println(shiroUser.id);
		List<String> roleList = shiroUser.getRoleList();
		Set<String> permissionSet = new HashSet<String>();
		Set<String> roleNameSet = new HashSet<String>();
		// 处理每个角色的权限
		for (String roleId : roleList) {
			System.out.println("role_id:"+roleId);
			List<String> permissions = shiroService.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (ToolUtil.isNotEmpty(permission)) {
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
	//
	// /**
	// * 设置认证加密方式
	// */
	// @Override
	// public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
	// HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
	// md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
	// md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
	// super.setCredentialsMatcher(md5CredentialsMatcher);
	// }
}
