package com.dt.module.hrm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.ResData;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.hrm.service.EmplService;

@Controller
@RequestMapping("/api")
public class EmplController extends BaseController {
	 
	@Autowired
	private EmplService emplService;

	@RequestMapping("/hrm/employeeAdd.do")
	@Res
	@Acl
	public ResData employeeAdd() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return emplService.addEmployee(ps);
	}
	@RequestMapping("/hrm/employeeUpdate.do")
	@Res
	@Acl
	public ResData employeeUpdate() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return emplService.updateEmployee(ps);
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
		return emplService.delEmployee(empl_id);
	}
}
