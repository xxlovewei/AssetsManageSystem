package com.dt.module.hrm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.hrm.service.EmplOrgService;
import com.dt.module.hrm.service.EmplService;

@Controller
@RequestMapping("/api")
public class EmplOrgController extends BaseController {
	@Autowired
	private EmplOrgService emplOrgService;
	@Autowired
	private EmplService emplService;

	@Res
	@Acl
	@RequestMapping("/hrm/orgNodeDelete.do")
	public R orgNodeDelete(String node_id) {
		return emplOrgService.deleteEmplOrg(node_id);
	}
	@RequestMapping("/hrm/queryEmplByOrg.do")
	@Res
	@Acl
	public R queryEmplByOrg(String node_id) throws IOException {
		return emplService.queryEmplByOrg(node_id);
	}
	@RequestMapping("/hrm/orgNodeSave.do")
	@Res
	@Acl
	@Transactional
	public R orgNodeSave() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("node_id");
		if (ToolUtil.isEmpty(id)) {
			return emplOrgService.addEmplOrg(ps);
		} else {
			return emplOrgService.updateEmplOrg(ps);
		}
	}
	@RequestMapping("/hrm/orgNodeQuery.do")
	@Res
	@Acl
	public R orgNodeQuery(String node_id) {
		return emplOrgService.queryEmplOrgById(node_id);
	}
	@RequestMapping("/hrm/orgNodeTreeQuery.do")
	@Res
	@Acl
	public R orgNodeTreeQuery(String org_id) {
		return emplOrgService.queryEmplOrgNodeTree(org_id);
	}
	@RequestMapping("/hrm/orgQueryLevelList.do")
	@Res
	@Acl
	public R orgQueryLevelList() {
		return emplOrgService.queryEmplOrgLevelList();
	}
	@RequestMapping("/hrm/orgQuery.do")
	@Res
	@Acl
	public R orgQuery() {
		return emplOrgService.queryEmplOrg();
	}
}
