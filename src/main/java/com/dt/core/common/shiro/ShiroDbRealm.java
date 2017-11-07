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
		_log.info("###################Action 登录认证#################Username:" + token.getUsername() + ",Host:"
				+ token.getHost() + ",IsRememberMe:" + token.isRememberMe());
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
			// _log.info("角色ID:" + roleId);
			List<String> permissions = shiroService.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (ToolUtil.isNotEmpty(permission)) {
						System.out.println("add:" + permission);
						permissionSet.add(permission);
					}
				}
			}
			// if (i == 0) {
			// //兼容之前的赋权，后期在处理
			// permissionSet.add("/api/dict/saveDict.do");
			// permissionSet.add("/api/dict/queryByDictId.do");
			// permissionSet.add("/api/dict/saveDictItem.do");
			// permissionSet.add("/api/dict/deleteDict.do");
			// permissionSet.add("/api/dict/deleteDictItem.do");
			// permissionSet.add("/api/dict/queryDictItem.do");
			// permissionSet.add("/api/dict/queryDictItemById.do");
			// permissionSet.add("/api/file/fileConfQuery.do");
			// permissionSet.add("/api/file/fileConfQueryById.do");
			// permissionSet.add("/api/file/fileConfSave.do");
			// permissionSet.add("/api/file/fileConfDelete.do");
			// permissionSet.add("/api/menu/treeNodeRoleMap.do");
			// permissionSet.add("/api/menu/treeRoleChecked.do");
			// permissionSet.add("/api/menu/addNode.do");
			// permissionSet.add("/api/menu/deleteNode.do");
			// permissionSet.add("/api/menu/updateNode.do");
			// permissionSet.add("/api/menu/treeTop.do");
			// permissionSet.add("/api/menu/treeDataDirect.do");
			// permissionSet.add("/api/params/saveParams.do");
			// permissionSet.add("/api/params/deleteParams.do");
			// permissionSet.add("/api/params/queryParamsById.do");
			// permissionSet.add("/api/role/roleQuery.do");
			// permissionSet.add("/api/role/roleQueryFormatKV.do");
			// permissionSet.add("/api/role/roleSave.do");
			// permissionSet.add("/api/role/roleQueryById.do");
			// permissionSet.add("/api/role/roleDelete.do");
			// permissionSet.add("/api/store/queryStoreSql.do");
			// permissionSet.add("/api/store/queryStoreSqlById.do");
			// permissionSet.add("/api/store/deleteStoreSql.do");
			// permissionSet.add("/api/store/saveStoreSql.do");
			// permissionSet.add("/api/user/queryRole.do");
			// permissionSet.add("/api/user/userDelete.do");
			// permissionSet.add("/api/user/userSave.do");
			// permissionSet.add("/api/user/userQueryById.do");
			// permissionSet.add("/api/user/userRoleChange.do");
			// permissionSet.add("/api/user/userQueryByGroup.do");
			// permissionSet.add("/api/user/getUserMenus.do");
			// permissionSet.add("/api/user/saveCommonSetting.do");
			// permissionSet.add("/api/user/changePwd.do");
			// permissionSet.add("/api/user/queryUserGroupById.do");
			// permissionSet.add("/api/user/queryGroup.do");
			// permissionSet.add("/api/user/deleteGroup.do");
			// permissionSet.add("/api/user/saveUserGroupById.do");
			// permissionSet.add("/api/user/queryAccessLog.do");
			// permissionSet.add("/api/user/queryLogin.do");
			// permissionSet.add("/api/user/queryReceivingAddrById.do");
			// permissionSet.add("/api/user/delReceivingAddr.do");
			// permissionSet.add("/api/user/queryReceivingAddr.do");
			// permissionSet.add("/api/user/saveReceivingAddr.do");
			// permissionSet.add("/api/user/setDefReceivingAddr.do");
			// permissionSet.add("/api/company/updateCompany.do");
			// permissionSet.add("/api/ctCategroy/queryRootCategory.do");
			// permissionSet.add("/api/ctCategroy/deleteCategory.do");
			// permissionSet.add("/api/ctCategroy/queryCategoryTreeList.do");
			// permissionSet.add("/api/ctCategroy/queryCategoryById.do");
			// permissionSet.add("/api/ctCategroy/queryCategory.do");
			// permissionSet.add("/api/ctCategroy/updateCategory.do");
			// permissionSet.add("/api/ctCategroy/addCategory.do");
			// permissionSet.add("/api/news/deleteNews.do");
			// permissionSet.add("/api/news/queryNewsById.do");
			// permissionSet.add("/api/news/publishNews.do");
			// permissionSet.add("/api/hrm/employeeAdd.do");
			// permissionSet.add("/api/hrm/employeeUpdate.do");
			// permissionSet.add("/api/hrm/employeeQueryList.do");
			// permissionSet.add("/api/hrm/employeeQueryById.do");
			// permissionSet.add("/api/hrm/employeeDelete.do");
			// permissionSet.add("/api/hrm/queryEmplByOrg.do");
			// permissionSet.add("/api/hrm/orgQuery.do");
			// permissionSet.add("/api/hrm/orgNodeDelete.do");
			// permissionSet.add("/api/hrm/orgNodeSave.do");
			// permissionSet.add("/api/hrm/orgNodeQuery.do");
			// permissionSet.add("/api/hrm/orgNodeTreeQuery.do");
			// permissionSet.add("/api/hrm/orgQueryLevelList.do");
			// permissionSet.add("/api/brand/brandQueryById.do");
			// permissionSet.add("/api/brand/brandSave.do");
			// permissionSet.add("/api/brand/brandDelete.do");
			// permissionSet.add("/api/shop/deleteShop.do");
			// permissionSet.add("/api/shop/queryShop.do");
			// permissionSet.add("/api/shop/queryShopById.do");
			// permissionSet.add("/api/shop/saveShop.do");
			// permissionSet.add("/api/shop/queryMyShop.do");
			// permissionSet.add("/api/categoryB/catAttrValueDel.do");
			// permissionSet.add("/api/categoryB/catAttrValueUpdate.do");
			// permissionSet.add("/api/categoryB/catAttrAdd.do");
			// permissionSet.add("/api/categoryB/catAttrDel.do");
			// permissionSet.add("/api/categoryB/catAttrUpdate.do");
			// permissionSet.add("/api/categoryB/update.do");
			// permissionSet.add("/api/categoryB/rename.do");
			// permissionSet.add("/api/categoryB/prodPublishCatList.do");
			// permissionSet.add("/api/categoryB/delete.do");
			// permissionSet.add("/api/categoryB/catAttrValueQuery.do");
			// permissionSet.add("/api/categoryB/catAttrQueryById.do");
			// permissionSet.add("/api/categoryB/catAttrValueAdd.do");
			// permissionSet.add("/api/categoryB/add.do");
			// permissionSet.add("/api/categoryB/prodPublishCatAttrList.do");
			// permissionSet.add("/api/categoryF/rename.do");
			// permissionSet.add("/api/categoryF/rootCatAdd.do");
			// permissionSet.add("/api/categoryF/rootCatDel.do");
			// permissionSet.add("/api/categoryF/rootCatUpdate.do");
			// permissionSet.add("/api/categoryF/queryTreeList.do");
			// permissionSet.add("/api/categoryF/add.do");
			// permissionSet.add("/api/product/prodModifySaleAttr.do");
			// permissionSet.add("/api/product/prodModifyBaseAttr.do");
			// permissionSet.add("/api/product/prodDelete.do");
			// permissionSet.add("/api/product/prodQueryByCat.do");
			// permissionSet.add("/api/product/prodQueryBySpu.do");
			// permissionSet.add("/api/product/prodPublish.do");
			// permissionSet.add("/api/product/getProdPics.do");
			// permissionSet.add("/api/product/updateProdPics.do");
			// permissionSet.add("/api/product/prodOffOn.do");
			// permissionSet.add("/api/product/prodDescartes.do");
			// permissionSet.add("/api/prod/prodDelete.do");
			// permissionSet.add("/api/prod/prodAdd.do");
			// permissionSet.add("/api/prod/produUpdate.do");
			// permissionSet.add("/api/schedule/removejob.do");
			// permissionSet.add("/api/schedule/pausejob.do");
			// permissionSet.add("/api/schedule/resumejob.do");
			// permissionSet.add("/api/schedule/queryJobs.do");
			// permissionSet.add("/api/schedule/runonce.do");
			// permissionSet.add("/api/schedule/enablejob.do");
			// permissionSet.add("/api/schedule/disablejob.do");
			// permissionSet.add("/api/module/queryModuleItemMap.do");
			// permissionSet.add("/api/module/updateModuleItemMap.do");
			// }
			// i++;
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
