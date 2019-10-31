package com.dt.module.cmdb.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Oct 21, 2019 7:48:08 PM
 * @Description: TODO
 */
@Service
public class ResExtService extends BaseService {

	public static String resSqlbody = " (select name from sys_dict_item where dict_item_id=t.type ) typestr,"
			+ " (select name from sys_dict_item where dict_item_id=t.loc ) locstr,"
			+ " (select name from sys_dict_item where dict_item_id=t.recycle ) recyclestr,"
			+ " (select name from sys_dict_item where dict_item_id=t.env  ) envstr,"
			+ " (select name from sys_dict_item where dict_item_id=t.risk  ) riskstr,"
			+ " (select name from sys_dict_item where dict_item_id=t.brand  ) brandstr,"
			+ " (select name from sys_user_info where user_id=t.create_by  ) create_username,"
			+ " (select name from sys_user_info where user_id=t.update_by  ) update_username,"
			+ " (select name from sys_user_info where user_id=t.review_userid  ) review_username,"
			+ " (select name from sys_user_info where user_id=t.used_userid  ) used_username,"
			+ " (select node_name from hrm_org_part where node_id=t.part_id  ) part_name,"
			+ " (select route_name from hrm_org_part where node_id=t.part_id  ) part_fullname,"
			+ " (select route_name from hrm_org_part where node_id=t.mgr_part_id  ) mgr_part_name,"
			+ " (select route_name from hrm_org_part where node_id=t.mgr_part_id  ) mgr_part_fullname,"
			+ " (select name from sys_dict_item where dict_item_id=t.wb  ) wbstr,"
			+ " (select name from sys_dict_item where dict_item_id=t.rack  ) rackstr,"
			+ " (select name from sys_dict_item where dict_item_id=t.class_id  ) classname,"
			+ " (select name from sys_dict_item where dict_item_id=t.type  ) typename,"
			+ "  date_format(wbout_date,'%Y-%m-%d')  wbout_datestr,"
			+ "  date_format(buy_time,'%Y-%m-%d') buy_timestr ,"
			+ "  case when t.wb_auto = '1' then '自动'  else '手动' end wb_autostr, "
			+ "  case when t.changestate = 'reviewed' then '已复核' when t.changestate = 'insert' then '待核(录入)' when t.changestate = 'updated'  then '待核(已更新)' else '未知' end reviewstr ,";

	// 根据ClassId获取数据
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
		sql = sql + resSqlbody + " t.* from res t where dr=0  ";

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

		sql = sql + " order by update_time desc,loc,rack,frame ";

		RcdSet rs2 = db.query(sql);

		return R.SUCCESS_OPER(rs2.toJsonArrayWithJsonObject());
	}

	public R addRes(TypedHashMap<String, Object> ps) {
		Date date = new Date(); // 获取一个Date对象
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
		String nowtime = simpleDateFormat.format(date);
		// addResCustom

		String id = ps.getString("id");
		String sql = "";
		String recycle = ps.getString("recycle");
		Insert ins = new Insert("res_history");

		// 计算资产名称，默认将class_name填写资产名称
		String zcname = ps.getString("name");
		if (ToolUtil.isEmpty(ps.getString("name")) && ToolUtil.isNotEmpty(ps.getString("class_id"))) {
			Rcd rs = db.uniqueRecord(" select * from sys_dict_item where dict_id='devclass' and dict_item_id=?",
					ps.getString("class_id"));
			if (rs != null) {
				zcname = rs.getString("name");
			}
		}

		// 自动计算脱保情况
		String wbcompute = ps.getString("wb");
		if (ToolUtil.isNotEmpty(ps.getString("wb_auto")) && "1".equals(ps.getString("wb_auto"))
				&& ToolUtil.isNotEmpty(ps.getString("wbout_date_f"))) {
			try {
				Date wboutdate = simpleDateFormat.parse(ps.getString("wbout_date_f") + " 01:00:00");
				if (date.getTime() > wboutdate.getTime()) {
					// 脱保
					wbcompute = "invalid";
				} else {
					// 未脱保
					wbcompute = "valid";
				}
				System.out.println(date);
			} catch (ParseException px) {
				px.printStackTrace();
			}
		}

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
			me.setIf("name", zcname);
			me.setIf("mark", ps.getString("mark"));
			me.setIf("maintain_userid", ps.getString("maintain_userid"));
			me.setIf("headuserid", ps.getString("headuserid"));
			me.setIf("rank", ps.getString("rank"));
			me.setIf("loc", ps.getString("loc"));
			me.setIf("locshow", ps.getString("locshow"));
			me.set("dr", "0");
			// 大类：服务器，存储
			me.set("class_id", ps.getString("class_id"));
			// 小类：x86，小机
			me.setIf("type", ps.getString("type"));
			// 资产类型：电子设备，其他
			me.setIf("zc_category", ps.getString("zc_category"));
			me.setIf("status", ps.getString("status"));
			me.setIf("env", ps.getString("env"));
			me.setIf("risk", ps.getString("risk"));
			me.setIf("recycle", ps.getString("recycle"));
			me.setIf("ip", ps.getString("ip"));
			me.setIf("frame", ps.getString("frame"));
			me.setIf("brand", ps.getString("brand"));
			me.setIf("wb", wbcompute);
			me.setIf("confdesc", ps.getString("confdesc"));
			me.setIf("rack", ps.getString("rack"));
			me.setIf("model", ps.getString("model"));
			me.setIf("update_time", nowtime);
			me.setIf("update_by", this.getUserId());
			me.setIf("buy_time", ps.getString("buy_time_f") == null ? null : ps.getString("buy_time_f") + " 12:00:00");
			me.setIf("changestate", "insert");
			me.setIf("create_time", nowtime);
			me.setIf("create_by", this.getUserId());
			me.setIf("net_worth", ps.getString("net_worth", "0"));
			me.setIf("buy_price", ps.getString("buy_price", "0"));
			me.setIf("part_id", "none".equals(ps.getString("part_id")) ? 0 : ps.getString("part_id"));
			me.setIf("mgr_part_id", "none".equals(ps.getString("mgr_part_id")) ? 0 : ps.getString("mgr_part_id"));
			me.setIf("used_userid", ps.getString("used_userid"));
			me.setIf("locdtl", ps.getString("locdtl"));
			me.setIf("wb_auto", ps.getString("wb_auto"));
			me.setIf("wbout_date",
					ps.getString("wbout_date_f") == null ? null : ps.getString("wbout_date_f") + " 12:00:00");
			ins.set("oper_type", "入库");
			sql = me.getSQL();
		} else {
			Update me = new Update("res");
			me.setIf("sn", ps.getString("sn"));
			me.setIf("name", zcname);
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
			me.setIf("wb", wbcompute);
			me.setIf("confdesc", ps.getString("confdesc"));
			me.setIf("rack", ps.getString("rack"));
			me.setIf("model", ps.getString("model"));
			me.setIf("brand", ps.getString("brand"));
			me.setIf("buy_time", ps.getString("buy_time_f") == null ? null : ps.getString("buy_time_f") + " 01:00:00");

			me.setIf("changestate", "updated");

			me.setIf("buy_price", ps.getString("buy_price", "0"));
			me.setIf("net_worth", ps.getString("net_worth", "0"));
			me.setIf("part_id", "none".equals(ps.getString("part_id")) ? 0 : ps.getString("part_id"));
			me.setIf("mgr_part_id", "none".equals(ps.getString("mgr_part_id")) ? 0 : ps.getString("mgr_part_id"));
			me.setIf("used_userid", ps.getString("used_userid"));
			me.setIf("zc_category", ps.getString("zc_category"));
			me.setIf("locdtl", ps.getString("locdtl"));
			me.setIf("wb_auto", ps.getString("wb_auto"));
			me.setIf("wbout_date",
					ps.getString("wbout_date_f") == null ? null : ps.getString("wbout_date_f") + " 01:00:00");

			Rcd source_recycle_rs = db.uniqueRecord(" select recycle from res where id=?", id);
			if (ToolUtil.isNotEmpty(recycle) && source_recycle_rs != null) {
				// 获取当前的recycel
				String source_recycle = source_recycle_rs.getString("recycle");
				if (source_recycle == null || source_recycle.equals(recycle)) {
					ins.set("oper_type", "更新");
				} else {
					String act = db
							.uniqueRecord(" select name,dict_item_id from sys_dict_item where dict_item_id=? ", recycle)
							.getString("name");
					ins.set("oper_type", "动作-" + act);
				}

			} else {
				ins.set("oper_type", "更新");
			}

			me.where().and("id=?", id);
			sql = me.getSQL();

		}

		db.execute(sql);

		// 更新记录表
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

	public String createUuid() {

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
}