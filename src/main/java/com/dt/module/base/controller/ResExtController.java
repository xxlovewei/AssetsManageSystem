package com.dt.module.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.dt.module.base.entity.Res;
import com.dt.module.base.entity.ResAttrValue;
import com.dt.module.base.entity.ResAttrValues;
import com.dt.module.base.service.IResAttrValueService;
import com.dt.module.base.service.IResAttrValuesService;
import com.dt.module.base.service.IResService;

/**
 * @author: algernonking
 * @date: Dec 31, 2018 7:32:04 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/base")
public class ResExtController extends BaseController {

	@Autowired
	IResAttrValuesService ResAttrValuesServiceImpl;

	@Autowired
	IResAttrValueService ResAttrValueServiceImpl;

	@Autowired
	IResService ResServiceImpl;

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
	@RequestMapping(value = "/queryResAllByClass.do")
	public R queryResAllByClass(String id) {

		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		// 获取属性数据
		String attrsql = "select * from res_class_attrs where class_id='" + id + "' and dr='0'";
		RcdSet attrs_rs = db.query(attrsql);
		String sql = "select";
		for (int i = 0; i < attrs_rs.size(); i++) {
			// 拼接sql
			String valsql = "";
			if (attrs_rs.getRcd(i).getString("attr_type").equals("number")) {
				// "to_number(attr_value)";
				valsql = " cast( attr_value as SIGNED INTEGER)";
			} else if (attrs_rs.getRcd(i).getString("attr_type").equals("string_arr")) {
				valsql = "attr_value";
			} else {
				valsql = "attr_value";
			}

			sql = sql + " (select " + valsql + " from res_attr_value i where i.dr=0 and i.res_id=t.id and i.attr_id='"
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
				+ " t.* from res t where dr=0  and class_id='" + id + "' ";
		RcdSet rs2 = db.query(sql);

		return R.SUCCESS_OPER(rs2.toJsonArrayWithJsonObject());
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
	public R queryResByNodeForUser(String ip,String classCode) {
		String sql = "select\n" + "(select count(1) from res_attr_value t2 where t2.res_id=t.id)ucnt,t.*\n"
				+ "from res t ,res_class tc where t.class_id=tc.class_id and tc.class_code='"+classCode+"'\n";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResAllUsers.do")
	public R queryResAllUsers(String status, String search, String type,String classCode,String attrCode) {

		if(ToolUtil.isOneEmpty(classCode,attrCode)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		String sql = " select a.* from res a,res_class b where a.class_id=b.class_id and b.dr=0 and a.dr=0 and b.class_code='"+classCode+"' ";

		if (ToolUtil.isNotEmpty(search)) {
			sql = sql + " and (a.ip like '%" + search + "%' or a.name like '%"+search+"%')";
		}
		sql = sql + " order by name ";
		JSONArray res = new JSONArray();
		RcdSet rs = db.query(sql);
		res = ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());
		for (int i = 0; i < res.size(); i++) {
			String res_id = res.getJSONObject(i).getString("id");
			String usersql = "select * from res_attr_values where res_id=? and attr_id in (select attr_id from res_class_attrs a,res_class b  where a.dr='0' and b.dr='0' and a.attr_code='"+attrCode+"' and a.class_id=b.class_id and b.class_code='"+classCode+"') and dr=0";
			if (ToolUtil.isNotEmpty(status)) {
				if (status.equals("enable")) {
					usersql = usersql + " and status='enable'";
				} else if (status.equals("disable")) {
					usersql = usersql + " and status='disable'";
				}
			}

			if (ToolUtil.isNotEmpty(type)) {
				if (type.equals("admin")) {
					usersql = usersql + " and type='admin'";
				} else if (type.equals("yw")) {
					usersql = usersql + " and type='yw'";
				}
			}

			RcdSet urs = db.query(usersql, res_id);
			res.getJSONObject(i).put("users",
					ConvertUtil.OtherJSONObjectToFastJSONArray(urs.toJsonArrayWithJsonObject()));
		}
		return R.SUCCESS_OPER(res);
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResValueByNodeForUser.do")
	public R queryResValueByNodeForUser(String id) {
		String sql = "select * from res_attr_value where res_id='" + id + "'";
		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_DENY)
	@RequestMapping(value = "/addResNode.do")
	@Transactional()
	public R addResNode(String ip, String name,String classCode,String attrCode) {
		if (ToolUtil.isOneEmpty(ip,classCode,attrCode)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		if (ToolUtil.isEmpty(name)) {
			name = ip;
		}
		Rcd crs = db.uniqueRecord("select * from res_class where dr=0 and class_code='"+classCode+"'");
		if (crs == null) {
			return R.FAILURE("没有对应的类型");
		}

		String classId = crs.getString("class_id");
		Rcd attrrs = db.uniqueRecord("select * from res_class_attrs where class_id=? and attr_code='"+attrCode+"' and dr=0",
				classId);
		if(attrrs==null) {
			return R.FAILURE("没有对应的属性"); 
		}
		String attr_id = attrrs.getString("attr_id");

		Res entity = new Res();
		String uid = db.getUUID();
		entity.setId(uid);
		entity.setClassId(classId);
		entity.setName(name);
		entity.setIp(ip);
		ResServiceImpl.save(entity);
 
		
		// 插入attr_value
		ResAttrValue rav = new ResAttrValue();
		rav.setAttrId(attr_id);
		rav.setAttrValue(attr_id);
		rav.setResId(uid);
		ResAttrValueServiceImpl.save(rav);

		return R.SUCCESS_OPER();
	}
	
	
	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/addResBySingleNode.do")
	public R addResBySingleNode(String ip, String name, String users,String classCode,String attrCode) {

		if (ToolUtil.isOneEmpty(ip, users,classCode,attrCode)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		if (ToolUtil.isEmpty(name)) {
			name = ip;
		}

		JSONArray usrarr = JSONArray.parseArray(users);
		if (usrarr.size() == 0) {
			return R.FAILURE("无用户");
		}

		Rcd crs = db.uniqueRecord("select * from res_class where dr=0 and class_code='"+classCode+"'");
		if (crs == null) {
			return R.FAILURE("没有对应的类型");
		}

		String classId = crs.getString("class_id");
		Rcd attrrs = db.uniqueRecord("select * from res_class_attrs where class_id=? and attr_code='"+attrCode+"' and dr=0",
				classId);
		if(attrrs==null) {
			return R.FAILURE("没有对应的属性"); 
		}
		String attr_id = attrrs.getString("attr_id");

		Rcd nrs = db.uniqueRecord("select * from res where class_id=? and dr=0 and ip=?", classId, ip);
		String uid = "";
		if (nrs == null) {
			// 判断节点是否存在,全部重新插入
			Res entity = new Res();
			uid = db.getUUID();
			entity.setId(uid);
			entity.setClassId(classId);
			entity.setName(name);
			entity.setIp(ip);
			ResServiceImpl.save(entity);

			// 插入attr_value
			ResAttrValue rav = new ResAttrValue();
			rav.setAttrId(attr_id);
			rav.setAttrValue(attr_id);
			rav.setResId(uid);
			ResAttrValueServiceImpl.save(rav);

			// 插入attr_values,按照需求更新用户列表
			for (int i = 0; i < usrarr.size(); i++) {
				ResAttrValues ent = new ResAttrValues();
				ent.setAttrValue(usrarr.getJSONObject(i).getString("user"));
				ent.setAttrValueId(attr_id);
				ent.setAttrId(attr_id);
				ent.setResId(uid);
				ent.setStatus(usrarr.getJSONObject(i).getString("status"));
				ResAttrValuesServiceImpl.save(ent);
			}
		} else {
			// 节点已经存在m插入attr_values,按照需求更新用户列表
			uid = nrs.getString("id");
			for (int i = 0; i < usrarr.size(); i++) {
				System.out.println(usrarr.getJSONObject(i).toJSONString());
				Rcd udrs = db.uniqueRecord(
						"select * from res_attr_values where dr='0' and res_id=? and attr_id=? and attr_value=? ", uid,
						attr_id, usrarr.getJSONObject(i).getString("user"));
				ResAttrValues ent = new ResAttrValues();
				ent.setAttrValue(usrarr.getJSONObject(i).getString("user"));
				ent.setAttrValueId(attr_id);
				ent.setAttrId(attr_id);
				ent.setResId(uid);
				ent.setStatus(usrarr.getJSONObject(i).getString("status"));
				if (udrs != null) {
					ent.setId(udrs.getString("id"));
				}
				ResAttrValuesServiceImpl.saveOrUpdate(ent);
			}

		}
		return R.SUCCESS_OPER();
	}

}
