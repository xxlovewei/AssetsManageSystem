package com.dt.module.base.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/selectListResExd.do")
	public R selectList(String classId) {
		QueryWrapper<Res> ew = new QueryWrapper<Res>();
		ew.and(i -> i.eq("class_id", classId));
		return R.SUCCESS_OPER(ResServiceImpl.list(ew));
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/batchWork.do")
	public R batchWork(String sql) {
		if (ToolUtil.isEmpty(sql)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		db.execute(sql);
		return R.SUCCESS_OPER();
	}

	private String createUuid() {

		int cnt = 30;
		String id = UUID.randomUUID().toString().substring(9, 23).toUpperCase();
		int i = 0;
		for (i = 0; i < cnt; i++) {
			Rcd rs = db.uniqueRecord("select * from res where uuid=?", id);
			if (rs == null) {
				break;
			} else {
				id = UUID.randomUUID().toString().substring(9, 23).toUpperCase();
			}
		}
		if (i > cnt - 1) {
			return "";
		} else {
			return id;
		}
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/res/addfaultdevice.do")
	@Transactional
	public R faultdevice(String resid, String reason, String mark, String files) {

		if (ToolUtil.isOneEmpty(resid, reason)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		Date date = new Date(); // 获取一个Date对象
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
		String nowtime = simpleDateFormat.format(date);
		Insert ins = new Insert("res_fault");
		String uid = db.getUUID();
		ins.set("id", uid);
		ins.set("res_id", resid);
		ins.set("uuid", this.createUuid());
		ins.setIf("mark", mark);
		ins.setIf("reason", reason);
		ins.setIf("oper_user", this.getUserId());
		ins.setIf("oper_time", nowtime);
		db.execute(ins);

		if (ToolUtil.isNotEmpty(files)) {
			String[] files_arr = files.split("#");
			for (int i = 0; i < files_arr.length; i++) {
				Insert me = new Insert("res_fault_file");
				me.set("id", db.getUUID());
				me.setIf("faultid", uid);
				me.setIf("fileid", files_arr[i]);
				db.execute(me);
			}
		}

		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryDictFast.do")
	@Transactional
	public R queryDictFast(String dicts) {

		JSONObject res = new JSONObject();
		String[] dict_arr = dicts.split(",");
		for (int i = 0; i < dict_arr.length; i++) {
			RcdSet rs = db.query("select * from sys_dict_item where dict_id=? and dr='0' order by sort", dict_arr[i]);
			res.put(dict_arr[i], ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));
		}

		return R.SUCCESS_OPER(res);
	}

	@ResponseBody
	@Acl(info = "新增Res", value = Acl.ACL_USER)
	@RequestMapping(value = "/addResCustom.do")
	@Transactional
	public R addResCustom() {

		Date date = new Date(); // 获取一个Date对象
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
		String nowtime = simpleDateFormat.format(date);
		// addResCustom
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		String sql = "";

		Insert ins = new Insert("res_history");

		if (ToolUtil.isEmpty(id)) {
			Insert me = new Insert("res");
			id = db.getUUID();
			me.set("id", id);
			String uuid = createUuid();
			if (ToolUtil.isEmpty(uuid)) {
				return R.FAILURE("未产生有效编号,请重试!");
			}
			me.set("uuid", uuid);
			me.setIf("sn", ps.getString("sn"));
			me.setIf("name", ps.getString("name"));
			me.setIf("mark", ps.getString("mark"));
			me.setIf("maintain_userid", ps.getString("maintain_userid"));
			me.setIf("headuserid", ps.getString("headuserid"));
			me.setIf("rank", ps.getString("rank"));
			me.setIf("loc", ps.getString("loc"));
			me.setIf("locshow", ps.getString("locshow"));
			me.set("dr", "0");
			me.set("class_id", ps.getString("class_id"));
			me.setIf("status", ps.getString("status"));
			me.setIf("env", ps.getString("env"));
			me.setIf("risk", ps.getString("risk"));
			me.setIf("type", ps.getString("type"));
			me.setIf("recycle", ps.getString("recycle"));
			me.setIf("ip", ps.getString("ip"));
			me.setIf("frame", ps.getString("frame"));
			me.setIf("brand", ps.getString("brand"));
			me.setIf("wb", ps.getString("wb"));
			me.setIf("confdesc", ps.getString("confdesc"));
			me.setIf("rack", ps.getString("rack"));
			me.setIf("model", ps.getString("model"));
			me.setIf("buy_time", ps.getString("buy_time") + " 12:00:00");
			me.setIf("changestate", "updated");
			me.setIf("create_time", nowtime);
			me.setIf("create_by", this.getUserId());

			ins.set("oper_type", "入库");
			sql = me.getSQL();
		} else {
			Update me = new Update("res");
			me.setIf("sn", ps.getString("sn"));
			me.setIf("name", ps.getString("name"));
			me.setIf("mark", ps.getString("mark"));
			me.setIf("maintain_userid", ps.getString("maintain_userid"));
			me.setIf("headuserid", ps.getString("headuserid"));
			me.setIf("rank", ps.getString("rank"));
			me.setIf("loc", ps.getString("loc"));
			me.setIf("locshow", ps.getString("locshow"));
			me.set("dr", "0");
			me.set("class_id", ps.getString("class_id"));
			me.setIf("status", ps.getString("status"));
			me.setIf("env", ps.getString("env"));
			me.setIf("risk", ps.getString("risk"));
			me.setIf("type", ps.getString("type"));
			me.setIf("recycle", ps.getString("recycle"));
			me.setIf("ip", ps.getString("ip"));
			me.setIf("frame", ps.getString("frame"));
			me.setIf("wb", ps.getString("wb"));
			me.setIf("confdesc", ps.getString("confdesc"));
			me.setIf("rack", ps.getString("rack"));
			me.setIf("model", ps.getString("model"));
			me.setIf("brand", ps.getString("brand"));
			me.setIf("buy_time", ps.getString("buy_time") + " 12:00:00");
			me.setIf("update_time", nowtime);
			me.setIf("changestate", "updated");
			me.setIf("update_by", this.getUserId());

			if ("scrap".equals(ps.getString("recycle"))) {
				ins.set("oper_type", "报废");
			} else if ("stop".equals(ps.getString("recycle"))) {
				ins.set("oper_type", "停用");
			} else if ("undercarriage".equals(ps.getString("recycle"))) {
				ins.set("oper_type", "下架");
			} else {
				ins.set("oper_type", "更新");
			}
			me.where().and("id=?", id);
			sql = me.getSQL();

		}
		db.execute(sql);

		ins.set("id", db.getUUID());
		ins.set("res_id", id);
		ins.set("oper_time", nowtime);
		ins.set("oper_user", this.getUserId());
		ins.set("fullct", ps.toString());
		db.execute(ins);

		// 更新其他属性，属性值、
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

	public R queryResAllByClassGetData(String id, String wb, String env, String recycle, String loc, String search) {

		// 获取属性数据
		String attrsql = "select * from res_class_attrs where class_id=? and dr='0'";
		RcdSet attrs_rs = db.query(attrsql, id);
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
		sql = sql + " (select name from sys_dict_item where dict_item_id=t.type ) typestr,"
				+ " (select name from sys_dict_item where dict_item_id=t.loc ) locstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.recycle ) recyclestr,"
				+ " (select name from sys_dict_item where dict_item_id=t.env  ) envstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.risk  ) riskstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.brand  ) brandstr,"
				+ " (select name from sys_user_info where user_id=t.create_by  ) create_username,"
				+ " (select name from sys_user_info where user_id=t.update_by  ) update_username,"
				+ " (select name from sys_user_info where user_id=t.review_userid  ) review_username,"
				+ " (select name from sys_dict_item where dict_item_id=t.wb  ) wbstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.rack  ) rackstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.class_id  ) classname,"
				+ " (select name from sys_dict_item where dict_item_id=t.type  ) typename,"
				+ "date_format(buy_time,'%Y-%m-%d') buy_timestr , t.* from res t where dr=0  ";

		if (ToolUtil.isNotEmpty(loc) && !"all".equals(id)) {
			sql = sql + " and class_id='" + id + "'";
		}

		if (ToolUtil.isNotEmpty(loc) && !"all".equals(loc)) {
			sql = sql + " and loc='" + loc + "'";
		}

		if (ToolUtil.isNotEmpty(env) && !"all".equals(env)) {
			sql = sql + " and env='" + env + "'";
		}

		if (ToolUtil.isNotEmpty(wb) && !"all".equals(wb)) {
			sql = sql + " and wb='" + wb + "'";
		}

		if (ToolUtil.isNotEmpty(recycle) && !"all".equals(recycle)) {
			sql = sql + " and recycle='" + recycle + "'";
		}

		if (ToolUtil.isNotEmpty(search)) {
			sql = sql + " and  (uuid like '%" + search + "%' or model like '%" + search + "%'  or  sn like '%" + search
					+ "%' )";
		}

		RcdSet rs2 = db.query(sql);

		return R.SUCCESS_OPER(rs2.toJsonArrayWithJsonObject());
	}

	public R queryResAllGetData(String id, String wb, String env, String recycle, String loc, String search) {
		String sql = "select";
		sql = sql + " (select name from sys_dict_item where dict_item_id=t.type ) typestr,"
				+ " (select name from sys_dict_item where dict_item_id=t.loc ) locstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.recycle ) recyclestr,"
				+ " (select name from sys_dict_item where dict_item_id=t.env  ) envstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.risk  ) riskstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.brand  ) brandstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.wb  ) wbstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.rack  ) rackstr,"
				+ " (select name from sys_dict_item where dict_item_id=t.class_id  ) classname,"
				+ " (select name from sys_dict_item where dict_item_id=t.type  ) typename,"
				+ " (select name from sys_user_info where user_id=t.create_by  ) create_username,"
				+ " (select name from sys_user_info where user_id=t.update_by  ) update_username,"
				+ " (select name from sys_user_info where user_id=t.review_userid  ) review_username,"
				+ "date_format(buy_time,'%Y-%m-%d') buy_timestr , t.* from res t where dr=0  ";

		if (ToolUtil.isNotEmpty(id) && !"all".equals(id)) {
			sql = sql + " and class_id='" + id + "'";
		}

		if (ToolUtil.isNotEmpty(loc) && !"all".equals(loc)) {
			sql = sql + " and loc='" + loc + "'";
		}

		if (ToolUtil.isNotEmpty(env) && !"all".equals(env)) {
			sql = sql + " and env='" + env + "'";
		}

		if (ToolUtil.isNotEmpty(wb) && !"all".equals(wb)) {
			sql = sql + " and wb='" + wb + "'";
		}

		if (ToolUtil.isNotEmpty(recycle) && !"all".equals(recycle)) {
			sql = sql + " and recycle='" + recycle + "'";
		}

		if (ToolUtil.isNotEmpty(search)) {
			sql = sql + " and  (uuid like '%" + search + "%' or model like '%" + search + "%'  or  sn like '%" + search
					+ "%' )";
		}

		RcdSet rs2 = db.query(sql);

		return R.SUCCESS_OPER(rs2.toJsonArrayWithJsonObject());
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResAllByClass.do")
	public R queryResAllByClass(String id, String wb, String env, String recycle, String loc, String search) {

		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		return queryResAllByClassGetData(id, wb, env, recycle, loc, search);

	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResAll.do")
	public R queryResAll(String id, String wb, String env, String recycle, String loc, String search) {

		return queryResAllGetData(id, wb, env, recycle, loc, search);

	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResFaultById.do")
	public R queryResFaultById(String id) {

		JSONObject res = new JSONObject();
		Rcd rs = db.uniqueRecord("select * from res_fault where id=?", id);
		if (rs != null) {
			res = ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
			// 获取attatch
			RcdSet rrs = db.query("select * from res_fault_file where faultid=?", id);
			res.put("attachdata", ConvertUtil.OtherJSONObjectToFastJSONArray(rrs.toJsonArrayWithJsonObject()));
		}

		return R.SUCCESS_OPER(res);
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResAllById.do")
	public R queryResAllById(String id, String classId) {

		JSONObject data = new JSONObject();

		if (ToolUtil.isEmpty(id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		String class_id = "";
		Rcd rs = db.uniqueRecord("select * from res t where dr=0 and id=?", id);
		if (rs != null) {
			class_id = rs.getString("class_id");
		} else {
			class_id = classId;
		}

		// 获取class_id
		if (ToolUtil.isEmpty(class_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		// 获取属性数据
		RcdSet attrs = null;
		String attrsql = "select * from res_class_attrs where class_id=? and dr='0'";
		attrs = db.query(attrsql, class_id);
		data.put("attr", ConvertUtil.OtherJSONObjectToFastJSONArray(attrs.toJsonArrayWithJsonObject()));

		// 获取res数据
		if (ToolUtil.isNotEmpty(id)) {
			String sql = "select";
			RcdSet attrs_rs = db.query(attrsql, class_id);

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
			sql = sql + " (select name from sys_dict_item where dict_item_id=t.type ) typestr,"
					+ " (select name from sys_dict_item where dict_item_id=t.loc ) locstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.recycle ) recyclestr,"
					+ " (select name from sys_dict_item where dict_item_id=t.env  ) envstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.risk  ) riskstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.brand  ) brandstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.wb  ) wbstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.rack  ) rackstr,"
					+ " (select name from sys_dict_item where dict_item_id=t.class_id  ) classname,"
					+ " (select name from sys_dict_item where dict_item_id=t.type  ) typename,"
					+ " (select name from sys_user_info where user_id=t.create_by  ) create_username,"
					+ " (select name from sys_user_info where user_id=t.update_by  ) update_username,"
					+ " (select name from sys_user_info where user_id=t.review_userid  ) review_username,"
					+ "date_format(buy_time,'%Y-%m-%d') buy_timestr , t.* from res t where dr=0  and id=?";

			Rcd rs2 = db.uniqueRecord(sql, id);
			if (rs2 != null) {
				data.put("data", ConvertUtil.OtherJSONObjectToFastJSONObject(rs2.toJsonObject()));
				// 获取kv一对多数据
				for (int i = 0; i < kvdataarr.size(); i++) {
					RcdSet trs = db.query("select * from res_attr_values where res_id=? and  attr_value_id=?", id,
							rs2.getString(kvdataarr.getString(i)));
					data.put(kvdataarr.getString(i),
							ConvertUtil.OtherJSONObjectToFastJSONArray(trs.toJsonArrayWithJsonObject()));
				}
			}

		}

		// 获取更新记录
		RcdSet urs = db.query(
				"select a.*,b.name from res_history a ,sys_user_info b where res_id=? and a.oper_user=b.user_id order by oper_time desc limit 50",
				id);
		data.put("updatadata", ConvertUtil.OtherJSONObjectToFastJSONArray(urs.toJsonArrayWithJsonObject()));

		// 获取故障登记表
		RcdSet grs = db.query(
				"select a.*,b.name, (select count(1) from res_fault_file where a.id=faultid) attach_cnt from res_fault a ,sys_user_info b where a.oper_user=b.user_id order by oper_time desc limit 50");
		data.put("faultdata", ConvertUtil.OtherJSONObjectToFastJSONArray(grs.toJsonArrayWithJsonObject()));

		return R.SUCCESS_OPER(data);
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResByNodeForUser.do")
	public R queryResByNodeForUser(String ip, String classCode) {
		String sql = "select\n" + "(select count(1) from res_attr_value t2 where t2.res_id=t.id)ucnt,t.*\n"
				+ "from res t ,res_class tc where t.class_id=tc.class_id and tc.class_code=?\n";
		return R.SUCCESS_OPER(db.query(sql, classCode).toJsonArrayWithJsonObject());
	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/queryResAllUsers.do")
	public R queryResAllUsers(String status, String search, String type, String classCode, String attrCode) {

		if (ToolUtil.isOneEmpty(classCode, attrCode)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		String sql = " select a.* from res a,res_class b where a.class_id=b.class_id and b.dr=0 and a.dr=0 and b.class_code='"
				+ classCode + "' ";

		if (ToolUtil.isNotEmpty(search)) {
			sql = sql + " and (a.ip like '%" + search + "%' or a.name like '%" + search + "%')";
		}
		sql = sql + " order by a.name ";
		JSONArray res = new JSONArray();
		RcdSet rs = db.query(sql);
		res = ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());
		for (int i = 0; i < res.size(); i++) {
			String res_id = res.getJSONObject(i).getString("id");
			String usersql = "select * from res_attr_values where res_id=? and attr_id in (select attr_id from res_class_attrs a,res_class b  where a.dr='0' and b.dr='0' and a.attr_code='"
					+ attrCode + "' and a.class_id=b.class_id and b.class_code='" + classCode + "') and dr=0";
			if (ToolUtil.isNotEmpty(status)) {
				if (status.equals("enable")) {
					usersql = usersql + " and status='enable'";
				} else if (status.equals("disable")) {
					usersql = usersql + " and status='disable'";
				}
			}

			if (ToolUtil.isNotEmpty(type)) {
				if (type.equals("all")) {

				} else if (type.equals("work")) {
					usersql = usersql + " and type in ('admin','yw','db','app') ";
				} else if (type.equals("unknow")) {
					usersql = usersql + " and type='" + type + "'";
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
	@Transactional
	public R addResNode(String id, String ip, String name, String classCode, String attrCode) {
		if (ToolUtil.isOneEmpty(ip, classCode, attrCode)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		if (ToolUtil.isEmpty(name)) {
			name = ip;
		}
		Rcd crs = db.uniqueRecord("select * from res_class where dr=0 and class_code='" + classCode + "'");
		if (crs == null) {
			return R.FAILURE("没有对应的类型");
		}

		String classId = crs.getString("class_id");
		Rcd attrrs = db.uniqueRecord(
				"select * from res_class_attrs where class_id=? and attr_code='" + attrCode + "' and dr=0", classId);
		if (attrrs == null) {
			return R.FAILURE("没有对应的属性");
		}
		String attr_id = attrrs.getString("attr_id");

		Res entity = new Res();
		String uid = db.getUUID();
		if (ToolUtil.isEmpty(id)) {
			entity.setId(uid);
		} else {
			entity.setId(id);
		}
		entity.setClassId(classId);
		entity.setName(name);
		entity.setIp(ip);
		ResServiceImpl.saveOrUpdate(entity);

		// 插入attr_value
		if (ToolUtil.isEmpty(id)) {
			ResAttrValue rav = new ResAttrValue();
			rav.setAttrId(attr_id);
			rav.setAttrValue(attr_id);
			rav.setResId(uid);
			ResAttrValueServiceImpl.save(rav);
		}
		return R.SUCCESS_OPER();
	}

	// root,inter,db,app,yw,unknow
	public String autoChosenUserType(String user) {

		if (ToolUtil.isEmpty(user)) {
			return "unknow";
		}
		user = user.toLowerCase();
		if (user.equals("root") || user.equals("administrator") || user.equals("admin")) {
			return "admin";
		}

		if (user.equals("jinj") || user.equals("shouqw") || user.equals("zhangjj")) {
			return "yw";
		}

		if (user.indexOf("mongodb") >= 0 || user.indexOf("db2inst") >= 0 || user.indexOf("oracle") >= 0
				|| user.indexOf("postgres") >= 0 || user.indexOf("mysql") >= 0 || user.indexOf("dasusr") >= 0
				|| user.indexOf("db2fenc") >= 0) {
			return "db";
		}

		if (user.indexOf("apache") >= 0 || user.indexOf("nginx") >= 0 || user.indexOf("tomcat") >= 0
				|| user.indexOf("weblogic") >= 0 || user.indexOf("grafana") >= 0 || user.indexOf("haproxy") >= 0
				|| user.indexOf("influxdb") >= 0) {
			return "app";
		}

		if (user.equals("wwwrun") || user.equals("suse-ncc") || user.equals("polkituser") || user.equals("news")
				|| user.equals("messagebus") || user.equals("man") || user.equals("gdm") || user.equals("at")
				|| user.equals("guest") || user.equals("bin") || user.equals("daemon") || user.equals("adm")
				|| user.equals("lp") || user.equals("sync") || user.equals("shutdown") || user.equals("halt")
				|| user.equals("mail") || user.equals("uucp") || user.equals("uuidd") || user.equals("operator")
				|| user.equals("games") || user.equals("gopher") || user.equals("ftp") || user.equals("nobody")
				|| user.equals("dbus") || user.equals("vcsa") || user.equals("abrt") || user.equals("ntp")
				|| user.equals("haldaemon") || user.equals("ntp") || user.equals("saslauth") || user.equals("postfix")
				|| user.equals("sshd") || user.equals("tcpdump") || user.equals("nscd") || user.equals("rtkit")
				|| user.equals("pulse") || user.equals("avahi-autoipd") || user.equals("rpc")
				|| user.equals("systemd-network") || user.equals("nfsnobody") || user.equals("polkitd")
				|| user.equals("chrony") || user.equals("rpcuser") || user.equals("sys") || user.equals("lpd")
				|| user.equals("invscout") || user.equals("snapp") || user.equals("ipsec") || user.equals("pconsole")
				|| user.equals("esaadmin") || user.equals("atc") || user.equals("amdc") || user.equals("pac")
				|| user.equals("atc2") || user.equals("listen") || user.equals("nuucp") || user.equals("smtp")
				|| user.equals("noaccess") || user.equals("nobody4") || user.equals("nobody")
				|| user.indexOf("window") >= 0 || user.indexOf("systemd-") >= 0) {
			return "inter";
		}

		return "unknow";
	}

	public R addResBySingleNode(String data, String classCode, String attrCode) {

		System.out.println("data:\n" + data);
		System.out.println("classCode:\n" + classCode);
		System.out.println("attrCode:\n" + attrCode);

		if (ToolUtil.isOneEmpty(data, classCode, attrCode)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		JSONObject obj = JSONObject.parseObject(data);
		if (ToolUtil.isEmpty(obj) || ToolUtil.isEmpty(obj.getString("ip"))) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		String ip = obj.getString("ip");
		String name = "";
		if (ToolUtil.isEmpty(obj.getString("name"))) {
			name = ip;
		} else {
			name = obj.getString("name");
		}

		// 检查是否有类型
		Rcd crs = db.uniqueRecord("select * from res_class where dr=0 and class_code='" + classCode + "'");
		if (crs == null) {
			return R.FAILURE("没有对应的类型");
		}
		String classId = crs.getString("class_id");
		Rcd attrrs = db.uniqueRecord(
				"select * from res_class_attrs where class_id=? and attr_code='" + attrCode + "' and dr=0", classId);
		if (attrrs == null) {
			return R.FAILURE("没有对应的属性");
		}
		String attrId = attrrs.getString("attr_id");

		JSONArray listdata = obj.getJSONArray("list");

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
			rav.setAttrId(attrId);
			rav.setAttrValue(attrId);
			rav.setResId(uid);
			ResAttrValueServiceImpl.save(rav);

			// 插入attr_values,按照需求将标记为update更新用户列表
			for (int i = 0; i < listdata.size(); i++) {
				String act = listdata.getJSONObject(i).getString("act");
				String user = listdata.getJSONObject(i).getString("user");
				// 首次全部插入
				if (act != null) {
					ResAttrValues ent = new ResAttrValues();
					ent.setAttrValue(user);
					ent.setAttrValueId(attrId);
					ent.setAttrId(attrId);
					ent.setResId(uid);
					String type = autoChosenUserType(user);
					ent.setType(type);
					if (type.equals("inter")) {
						ent.setStatus("disable");
					} else {
						ent.setStatus(listdata.getJSONObject(i).getString("status"));
					}
					ResAttrValuesServiceImpl.save(ent);
					System.out.println("user:" + user + ",type:" + type + ",status:" + ent.getStatus());
				}

			}
		} else {
			// 如果ip存在,则更新该IP所在条目,更新name
			uid = nrs.getString("id");
			Res resent = new Res();
			resent.setName(name);
			resent.setId(uid);
			ResServiceImpl.saveOrUpdate(resent);
			// 节点已经存在插入attr_values,按照需求更新用户列表
			for (int i = 0; i < listdata.size(); i++) {
				String act = listdata.getJSONObject(i).getString("act");
				String user = listdata.getJSONObject(i).getString("user");
				if (act != null) {
					Rcd udrs = db.uniqueRecord(
							"select * from res_attr_values where dr='0' and res_id=? and attr_id=? and attr_value=? ",
							uid, attrId, user);
					ResAttrValues ent = new ResAttrValues();
					ent.setAttrValue(user);
					ent.setAttrValueId(attrId);
					ent.setAttrId(attrId);
					ent.setResId(uid);
					String type = autoChosenUserType(user);
					if (udrs == null) {
						// 新增用户
						ent.setType(type);
						if (type.equals("inter")) {
							ent.setStatus("disable");
						} else {
							ent.setStatus(listdata.getJSONObject(i).getString("status"));
						}
						System.out.println("addUser");
						ResAttrValuesServiceImpl.save(ent);
					} else {
						// 判断update或delete
						if ("update".equals(act)) {
							System.out.println("updateUser");
							// 内置全部强制修改成停用
							if (type.equals("inter")) {
								ent.setStatus("disable");
							} else {
								ent.setStatus(listdata.getJSONObject(i).getString("status"));
							}
							ResAttrValuesServiceImpl.saveOrUpdate(ent);
						} else if ("delete".equals(act)) {
							System.out.println("updateUser");
							ResAttrValuesServiceImpl.removeById(udrs.getString("id"));

						}
					}

					System.out.println("user:" + user + ",type:" + type + ",status:" + ent.getStatus());

				}

			}

		}
		return R.SUCCESS_OPER();

	}

	@ResponseBody
	@Acl(info = "查询Res", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/addUserBySingleNode.do")
	@Transactional
	public R addUserBySingleNode(String data, String classCode, String attrCode) {
		return addResBySingleNode(data, "xtlist", "userlist");
	}

}
