package com.dt.module.hrm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.UserService;
import com.dt.module.hrm.service.EmplService;

@Controller
public class EmplController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private EmplService emplService;

	@RequestMapping("/hrm/employeeAdd.do")
	@Res
	@Acl
	public ResData employeeAdd() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return userService.addUser(ps, UserService.USER_TYPE_EMPL);
	}
	@RequestMapping("/hrm/employeeUpdate.do")
	@Res
	@Acl
	public ResData employeeUpdate() {
		return userService.updateUser(HttpKit.getRequestParameters(), UserService.USER_TYPE_EMPL);
	}
	@RequestMapping("/hrm/employeeQueryList.do")
	@Res
	@Acl
	public ResData employeeQueryList() {
		return emplService.queryEmplList(HttpKit.getRequestParameters());
	}
	@RequestMapping("/hrm/employeeQueryById.do")
	@Res
	@Acl
	public ResData employeeQueryById(String empl_id) {
		return emplService.queryEmplById(empl_id);
	}
	@RequestMapping("/hrm/employeeDelete.do")
	@Res
	@Acl
	@Transactional
	public ResData employeeDelete(String empl_id) throws IOException {
		String user_id = userService.getUserId(empl_id);
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE("用户user_id不存在");
		}
		return userService.deleteUser(user_id);
	}
}
