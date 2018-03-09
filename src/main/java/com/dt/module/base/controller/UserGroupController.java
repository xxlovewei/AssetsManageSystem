package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.UserGroupService;

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
	@ResponseBody
	@Acl
	public R queryGroup() {
		return userGroupService.queryUserGroup();
	}
	
	@RequestMapping("/user/deleteGroup.do")
	@ResponseBody
	@Acl
	public R deleteGroup(String group_id) {
		return userGroupService.deleteUserGroup(group_id);
	}
	
	@RequestMapping("/user/queryUserGroupById.do")
	@ResponseBody
	@Acl
	public R queryUserGroupById(String group_id) {
		return userGroupService.queryUserGroupById(group_id);
	}
	
	
	@RequestMapping("/user/saveUserGroupById.do")
	@ResponseBody
	@Acl
	public R saveUserGroupById(String group_id) {
		
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("group_id");
		if (ToolUtil.isEmpty(id)) {
			return userGroupService.addUserGroup(ps);
		} else {
			return userGroupService.updateUserGroup(ps);
		}
	}
}

