package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.base.service.UserGroupService;
import com.dt.util.ToolUtil;
import com.dt.util.support.HttpKit;
import com.dt.util.support.TypedHashMap;

/** 
 * @author: algernonking
 * @date: 2017年8月8日 下午7:16:59 
 * @Description: TODO 
 */
@Controller
@RequestMapping("/api")
public class UserGroupController extends BaseController{
	@Autowired
	private UserGroupService userGroupService;
	
	@RequestMapping("/user/queryGroup.do")
	@Res
	@Acl
	public ResData queryGroup() {
		return userGroupService.queryUserGroup();
	}
	
	@RequestMapping("/user/deleteGroup.do")
	@Res
	@Acl
	public ResData deleteGroup(String group_id) {
		return userGroupService.deleteUserGroup(group_id);
	}
	
	@RequestMapping("/user/queryUserGroupById.do")
	@Res
	@Acl
	public ResData queryUserGroupById(String group_id) {
		return userGroupService.queryUserGroupById(group_id);
	}
	
	
	@RequestMapping("/user/saveUserGroupById.do")
	@Res
	@Acl
	public ResData saveUserGroupById(String group_id) {
		
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("group_id");
		if (ToolUtil.isEmpty(id)) {
			return userGroupService.addUserGroup(ps);
		} else {
			return userGroupService.updateUserGroup(ps);
		}
	}
}

