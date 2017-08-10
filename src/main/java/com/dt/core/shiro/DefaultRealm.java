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

import com.dt.core.common.util.ToolUtil;
import com.dt.module.base.entity.User;
import com.dt.module.base.service.UserService;

public class DefaultRealm extends AuthorizingRealm{  
    
    
	  
 
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
				throws AuthenticationException {
			System.out.println("login check");
			UserService userver = UserService.me();
			IShiro shiroService = ShiroService.me();
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			// 获取用户,根据系统ID
			User user = userver.getUser(token.getUsername());
			// 获取shiro用户
			ShiroUser shiroUser = shiroService.shiroUser(user);
			
			System.out.println(token.getUsername());
			System.out.println("FFFFFFsuper.getName()"+super.getName());
			
			SimpleAuthenticationInfo info = shiroService.info(shiroUser, user, super.getName());
			return info;
		}

		/**
		 * 权限认证
		 */
		@Override
		protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
			IShiro shiroService = ShiroService.me();

			ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
			List<String> roleList = shiroUser.getRoleList();

			Set<String> permissionSet = new HashSet<String>();
			Set<String> roleNameSet = new HashSet<String>();

			// 处理每个角色的权限
			for (String roleId : roleList) {
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
  
}  