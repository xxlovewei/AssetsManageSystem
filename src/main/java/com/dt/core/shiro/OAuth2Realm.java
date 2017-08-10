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
import org.springframework.stereotype.Component;

import com.dt.core.common.util.ToolUtil;
import com.dt.module.base.entity.User;
import com.dt.module.base.service.UserService;

@Component
public class OAuth2Realm extends AuthorizingRealm {
 

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
//       
//        Long userId = user.getUserId();
//
//        //用户权限列表
//        Set<String> permsSet = shiroService.getUserPermissions(userId);
//
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permsSet);
//        return info;
//        
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

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
 //       String accessToken = (String) token.getPrincipal();

//        //根据accessToken，查询用户信息
//        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
//        //token失效
//        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
//            throw new IncorrectCredentialsException("token失效，请重新登录");
//        }
//
//        //查询用户信息
//        SysUserEntity user = shiroService.queryUser(tokenEntity.getUserId());
//        //账号锁定
//        if(user.getStatus() == 0){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
//
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
//        return info;
        
    	System.out.println("login check");
		UserService userver = UserService.me();
		IShiro shiroService = ShiroService.me();
	 
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 获取用户,根据系统ID
		User user = userver.getUser(token.getUsername());
		// 获取shiro用户
		ShiroUser shiroUser = shiroService.shiroUser(user);
		
		System.out.println(token.getUsername());
		System.out.println("super.getName()"+super.getName());
		
		SimpleAuthenticationInfo info = shiroService.info(shiroUser, user, super.getName());
		return info;
        
        
    }
}
