package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.dao.sql.Delete;
import com.dt.dao.sql.Insert;
import com.dt.dao.sql.SQL;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Nov 4, 2017 8:08:34 PM
 * @Description: TODO
 */
@Service
public class ModuleItemMapService extends BaseService {
	public ResData updateModuleItem(String module_id, String items) {
		List<SQL> sqls = new ArrayList<SQL>();
		Delete d = new Delete();
		d.from("sys_modules_item");
		d.where().and("module_id=?", module_id);
		sqls.add(d);
		JSONArray items_arr = JSONArray.parseArray(items);
		for (int i = 0; i < items_arr.size(); i++) {
			Insert me = new Insert("sys_modules_item");
			me.set("module_item_id", db.getUUID());
			me.set("module_id", module_id);
			me.setIf("ct", items_arr.getJSONObject(i).getString("url"));
			me.set("status", "Y");
			me.set("type", "url");
			sqls.add(me);
		}
		db.executeSQLList(sqls);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @date: Nov 4, 2017 8:08:34 PM
	 * @Description:查询所有api,其中已经在模块中则选中
	 */
	public ResData queryModuleItem(String module_id) {
		if (ToolUtil.isEmpty(module_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		String sql = "select * from ( " + "select " + "'Y' selected, " + "a.module_item_id, " + "a.status, "
				+ "a.type, " + "a.ct url, " + "b.mark, " + "b.ctacl, "
				+ "case when b.id is null then '0' else '1' end urlstatuscode, "
				+ "case when b.id is null then '不存在' else '存在' end urlstatus, " + "case "
				+ "when b.ctacl is null then '未知' " + "when b.ctacl ='allow' then '公共访问' "
				+ "when b.ctacl ='common' then '用户认证' " + "when b.ctacl ='deny' then '权限认证' "
				+ "else '未知' end ctacltext "
				+ "from sys_modules_item a left join sys_api b on a.ct=b.ct where module_id='" + module_id + "' "
				+ "union all " + "select " + "'N' selected, " + "'' module_item_id, " + "'' status, " + "'url' type, "
				+ "b.ct url, " + "b.mark, " + "b.ctacl, " + "'0' urlstatuscode, " + "'存在' urlstatus, " + "case "
				+ "when b.ctacl is null then '未知' " + "when b.ctacl ='allow' then '公共访问' "
				+ "when b.ctacl ='common' then '用户认证' " + "when b.ctacl ='deny' then '权限认证' "
				+ "else '未知' end ctacltext "
				+ "from sys_api b where ctacl not in ('allow') and ct not in ( select ct from sys_modules_item where module_id='"
				+ module_id + "') order by status,ctacl desc,url) ";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
}
