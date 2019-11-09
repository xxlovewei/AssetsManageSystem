package com.dt.module.cmdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.cmdb.service.impl.ResExtService;

/**
 * @author: algernonking
 * @date: Oct 30, 2019 5:42:36 AM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/base/res/rep/")
public class ResExtReportController extends BaseController {

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryZcTjByOrg.do")
	@Transactional
	public R queryZcTjByOrg(String search) {
		String sql = "select node_id part_id,route_name part_fullname,\n"
				+ "  case when b.cnt is null then 0 else b.cnt end zc_cnt from hrm_org_part a left join\n"
				+ "  ( select part_id,count(1) cnt from res where dr='0' group by part_id) b\n"
				+ "  on a.node_id=b.part_id and  a.org_id='1'\n" + "union all\n"
				+ "select '-1' part_id,'未设置组织' part_fullname ,count(1) zc_cnt from res where part_id not in (select node_id from hrm_org_part where org_id='1')\n"
				+ "or part_id is null\n";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryZcTjByOrgId.do")
	@Transactional
	public R queryZcTjByOrgId(String part_id) {

		String sql = "select  " + ResExtService.resSqlbody + " t.* from res t where dr='0'";
		if ("-1".equals(part_id)) {
			sql = sql + " and part_id not in (select node_id from hrm_org_part where org_id='1') or part_id is null";

		} else {
			sql = sql + " and part_id='" + part_id + "'";
		}
		sql = sql + " order by class_id";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

}
