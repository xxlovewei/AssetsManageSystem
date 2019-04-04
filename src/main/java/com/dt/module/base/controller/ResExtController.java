package com.dt.module.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;

/**
 * @author: algernonking
 * @date: Dec 31, 2018 7:32:04 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/res")
public class ResExtController extends BaseController {

	private String createUuid() {

		int cnt = 30;
		String id = MD5Util.encrypt(db.getUUID()).toUpperCase().substring(0, 10);
		int i = 0;
		for (i = 0; i < cnt; i++) {
			Rcd rs = db.uniqueRecord("select * from res where uuid=?", id);
			if (rs == null) {
				break;
			} else {
				id = MD5Util.encrypt(db.getUUID()).toUpperCase().substring(0, 10);
			}
		}
		if (i > cnt - 1) {
			return "";
		} else {
			return id;
		}
	}

	@ResponseBody
	@Acl(info = "新增Res", value = Acl.ACL_DENY)
	@RequestMapping(value = "/addResCustom.do")
	public R addResCustom() {

		// addResCustom
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		String sql = "";
		if (ToolUtil.isEmpty(id)) {
			Insert me = new Insert("res");
			id = db.getUUID();
			me.set("id", id);
			String uuid = createUuid();
			if (ToolUtil.isEmpty(uuid)) {
				return R.FAILURE("未产生有效编号,请重试!");
			}
			me.set("uuid", uuid);
			me.set("sn", ps.getString("sn"));
			me.setIf("name", ps.getString("name"));
			me.setIf("describe", ps.getString("describe"));
			me.setIf("maintain_userid", ps.getString("maintain_userid"));
			me.setIf("headuserid", ps.getString("headuserid"));
			me.setIf("pinp", ps.getString("pinp"));
			me.setIf("loc", ps.getString("loc"));
			me.set("dr", "0");
			me.set("class_id", ps.getString("class_id"));
			me.setIf("status", ps.getString("status"));
			me.setIf("env", ps.getString("env"));
			me.setIf("mainlevel", ps.getString("mainlevel"));
			me.setIf("version", ps.getString("version"));
			me.setIf("img", ps.getString("img"));
			me.setIf("company", ps.getString("company"));
			sql = me.getSQL();
		} else {
			Update me = new Update("res");
			me.set("sn", ps.getString("sn"));
			me.setIf("name", ps.getString("name"));
			me.setIf("describe", ps.getString("describe"));
			me.setIf("maintain_userid", ps.getString("maintain_userid"));
			me.setIf("headuserid", ps.getString("headuserid"));
			me.setIf("pinp", ps.getString("pinp"));
			me.setIf("loc", ps.getString("loc"));
			me.set("dr", "0");
			me.set("class_id", ps.getString("class_id"));
			me.setIf("status", ps.getString("status"));
			me.setIf("env", ps.getString("env"));
			me.setIf("mainlevel", ps.getString("mainlevel"));
			me.setIf("version", ps.getString("version"));
			me.setIf("img", ps.getString("img"));
			me.setIf("company", ps.getString("company"));
			me.where().and("id=?", id);
			sql = me.getSQL();
		}
		db.execute(sql);
		// 更新其他属性，属性值
		String attrvals = ps.getString("attrvals");
		Update del = new Update("res_attr_value");
		del.set("dr", "1");
		del.where().and("res_id=?", id);
		db.execute(del);
		if (ToolUtil.isNotEmpty(attrvals)) {
			JSONArray valsarr = JSONArray.parseArray(attrvals);
			for (int i = 0; i < valsarr.size(); i++) {
				Insert me = new Insert("res_attr_value");
				me.set("id", db.getUUID());
				me.set("res_id", id);
				me.set("dr", "0");
				me.setIf("attr_id", valsarr.getJSONObject(i).getString("attr_id"));
				me.setIf("attr_value", valsarr.getJSONObject(i).getString("attr_value"));
				db.execute(me);
			}

		}

		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResAllById.do")
	public R queryResAllById(String id) {
		Rcd rs = null;

		// 获取class_id
		JSONObject data = new JSONObject();
		if (ToolUtil.isNotEmpty(id)) {
			String sql = "select * from res t where dr=0 and id=?";
			rs = db.uniqueRecord(sql, id);

		}

		String class_id = rs.getString("class_id");

		// 获取属性数据
		RcdSet attrs = null;
		String attrsql = "select * from res_class_attrs where class_id='" + class_id + "' and dr='0'";
		attrs = db.query(attrsql);
		data.put("attr", ConvertUtil.OtherJSONObjectToFastJSONArray(attrs.toJsonArrayWithJsonObject()));

		// 获取res数据
		if (ToolUtil.isNotEmpty(id)) {
			String sql = "select";
			RcdSet attrs_rs = db.query(attrsql);

			// 如果包含一对多，则将一对多保存至dataarr
			JSONArray kvdataarr = new JSONArray();
			for (int i = 0; i < attrs_rs.size(); i++) {
				// 拼接sql
				String valsql = "";
				if (attrs_rs.getRcd(i).getString("attr_type").equals("number")) {
					// "to_number(attr_value)";
					valsql = " cast( attr_value as SIGNED INTEGER)";
				} else if (attrs_rs.getRcd(i).getString("attr_type").equals("string_arr")) {
					kvdataarr.add(attrs_rs.getRcd(i).getString("attr_code"));
					valsql = "attr_value";
				} else {
					valsql = "attr_value";
				}

				sql = sql + " (select " + valsql
						+ " from res_attr_value i where i.dr=0 and i.res_id=t.id and i.attr_id='"
						+ attrs_rs.getRcd(i).getString("attr_id") + "') \"" + attrs_rs.getRcd(i).getString("attr_code")
						+ "\",  ";
			}
			sql = sql + " (select name from sys_dict_item where dict_item_id=t.pinp ) pinpstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.loc ) locstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.status  ) statusstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.env  ) envstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.mainlevel  ) mainlevelstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.company   ) companystr,"
					+ " (select name from sys_dict_item where dict_item_id=t.pinp  ) pinpstr2,"
					+ " t.* from res t where dr=0  and id='" + id + "' ";
			Rcd rs2 = db.uniqueRecord(sql);

			if (rs2 != null) {
				data.put("data", ConvertUtil.OtherJSONObjectToFastJSONObject(rs2.toJsonObject()));
				// 获取kv一对多数据
				for (int i = 0; i < kvdataarr.size(); i++) {

					RcdSet trs = db.query("select * from res_attr_values where res_id='" + id + "' and  attr_value_id='"
							+ rs2.getString(kvdataarr.getString(i)) + "'");
					data.put(kvdataarr.getString(i),
							ConvertUtil.OtherJSONObjectToFastJSONArray(trs.toJsonArrayWithJsonObject()));
				}
			}

		}
		return R.SUCCESS_OPER(data);
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResByNodeForUser.do")
	public R queryResByNodeForUser(String ip) {
		String sql = "select\n" + "(select count(1) from res_attr_value t2 where t2.res_id=t.id)ucnt,t.*\n"
				+ "from res t ,res_class tc where t.class_id=tc.class_id and tc.class_code='xtlist'\n";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResValueByNodeForUser.do")
	public R queryResValueByNodeForUser(String id) {
		String sql = "select * from res_attr_value where res_id='" + id + "'";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

}
