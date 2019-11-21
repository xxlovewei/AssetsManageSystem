package com.dt.module.cmdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.tool.util.ConvertUtil;
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
	@RequestMapping(value = "/dashboard.do")
	@Transactional
	public R dashboard(String search) {
		JSONObject res = new JSONObject();

		String sum = db.uniqueRecord("select round(sum(buy_price)/10000,1) v from res where dr='0'").getString("v");
		res.put("total_sum", sum);
		String cnt = db.uniqueRecord("select count(1) v from res where dr='0'").getString("v");
		res.put("total_count", cnt);
		String sql = "  select " + ResExtService.resSqlbody
				+ " t.* from res t where dr='0' and  wbout_date<now() limit 10";
		RcdSet rs = db.query(sql);
		res.put("res_tb_notice", ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));

		String sql2 = "select " + ResExtService.resSqlbody
				+ " t.* ,a.f_reason ,a.f_processtime  from res_fault a,res t where a.f_res_id=t.id and a.dr='0' and t.dr='0' limit 10";
		RcdSet rs2 = db.query(sql2);
		res.put("res_fault", ConvertUtil.OtherJSONObjectToFastJSONArray(rs2.toJsonArrayWithJsonObject()));

		//
		String sql3 = "\n" + "select\n" + "  b.name,\n" + "    class_id,\n" + "    count(1) cnt\n"
				+ "  from res t,sys_dict_item b\n" + "  where t.dr = '0' and b.dr='0' and t.class_id=b.dict_item_id\n"
				+ "  group by b.name ,class_id\n";

		RcdSet s3 = db.query(sql3);
		JSONArray meta_arr = new JSONArray();
		JSONArray data_arr = new JSONArray();
		for (int i = 0; i < s3.size(); i++) {
			JSONArray meta = new JSONArray();
			meta.add(i);
			meta.add(s3.getRcd(i).getString("name"));
			meta_arr.add(meta);

			JSONArray data = new JSONArray();
			data.add(i);
			data.add(s3.getRcd(i).getInteger("cnt"));
			data_arr.add(data);

		}

		res.put("chart_meta", meta_arr);
		res.put("chart_data", data_arr);
		System.out.println(res.toJSONString());
		return R.SUCCESS_OPER(res);
	}

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
