package com.dt.module.cmdb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: algernonking
 * @date: Dec 31, 2018 7:32:04 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/base")
public class ResExtController extends BaseController {


    @Autowired
    ZcService zcService;


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/batchWork.do")
    public R batchWork(String sql) {
        if (ToolUtil.isEmpty(sql)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
        db.execute(sql);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/deleteByIds.do")
    public R deleteByIds(String ids) {
        if (ToolUtil.isEmpty(ids)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
        Date date = new Date(); // 获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
        String nowtime = simpleDateFormat.format(date);
        JSONArray ids_arr = JSONArray.parseArray(ids);
        if (ids_arr.size() > 10000) {
            return R.FAILURE("不得超过1000个");
        }
        ArrayList<SQL> sqls = new ArrayList<SQL>();
        for (int i = 0; i < ids_arr.size(); i++) {
            String id = ids_arr.getString(i);
            Update me = new Update("res");
            me.set("dr", "1");
            me.setIf("update_time", nowtime);
            me.setIf("update_by", this.getUserId());
            me.where().and("id=?", id);
            sqls.add(me);
        }
        db.executeSQLList(sqls);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/needreview.do")
    @Transactional
    public R needreview(String search) {

        // reviewed(已复核),insert(待核(录入)),updated(待核(已更新))
        String sql = "select ";
        sql = sql + ZcCommonService.resSqlbody + " t.* from res t where dr=0  and changestate<>'reviewed'";
        if (ToolUtil.isNotEmpty(search)) {
            sql = sql + " and  (rack like '%" + search + "%' or fs1 like '%" + search + "%' or mark like '%" + search
                    + "%' or uuid like '%" + search + "%' or model like '%" + search + "%'  or  sn like '%" + search
                    + "%' )";
        }
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/review.do")
    @Transactional
    public R review(String ids) {
        Date date = new Date(); // 获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建一个格式化日期对象
        String nowtime = simpleDateFormat.format(date);
        List<SQL> sqls = new ArrayList<SQL>();
        JSONArray ids_arr = JSONArray.parseArray(ids);

        for (int i = 0; i < ids_arr.size(); i++) {
            Update me = new Update("res");
            me.set("changestate", "reviewed");
            me.setIf("review_userid", this.getUserId());
            me.setIf("review_date", nowtime);
            me.where().and("id=?", ids_arr.getString(i));
            sqls.add(me);

            Insert ins = new Insert("res_history");
            ins.set("oper_type", "复核操作");
            ins.set("id", db.getUUID());
            ins.set("res_id", ids_arr.getString(i));
            ins.set("oper_time", nowtime);
            ins.set("oper_user", this.getUserId());
            sqls.add(ins);
        }
        db.executeSQLList(sqls);
        return R.SUCCESS_OPER();
    }


    @ResponseBody
    @Acl(info = "批量新增Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/batchUpdateRes.do")
    @Transactional
    public R batchUpdateRes() {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "新增Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/addResCustom.do")
    @Transactional
    public R addResCustom() {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        return zcService.addRes(ps);
    }

    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/queryResAllByClass.do")
    public R queryResAllByClass(String comp, String belongcomp, String datarange, String part, String classroot, String class_id, String wb, String env, String recycle, String loc, String search) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        return zcService.queryResAllGetData(belongcomp, comp, part, datarange, classroot, class_id, wb, env, recycle, loc, search, ps);
    }

    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/queryPageResAllByClass.do")
    public R queryPageResAllByClass(String start, String length, @RequestParam(value = "pageSize", required = true, defaultValue = "10") String pageSize, @RequestParam(value = "pageIndex", required = true, defaultValue = "1") String pageIndex, String comp, String belongcomp, String part, String datarange, String classroot, String class_id, String wb, String env, String recycle, String loc, String search) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
        if (ToolUtil.isEmpty(respar)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
        int pagesize = respar.getIntValue("pagesize");
        int pageindex = respar.getIntValue("pageindex");
        String sql = zcService.buildQueryResAllGetdatalSql(belongcomp, comp, part, datarange, classroot, class_id, wb, env, recycle, loc, search, ps);
        String sqlcnt = "select count(1) value from (" + sql + ") tab";
        int count = db.uniqueRecord(sqlcnt).getInteger("value");
        JSONObject retrunObject = new JSONObject();
        retrunObject.put("iTotalRecords", count);
        retrunObject.put("iTotalDisplayRecords", count);
        retrunObject.put("success", true);
        retrunObject.put("code", 200);
        retrunObject.put("data", ConvertUtil.OtherJSONObjectToFastJSONArray(
                db.query(DbUtil.getDBPageSql(db.getDBType(), sql, pagesize, pageindex)).toJsonArrayWithJsonObject()));
        return R.clearAttachDirect(retrunObject);
    }

    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/queryPageResAll.do")
    public R queryResAllForPage(String start, String length, @RequestParam(value = "pageSize", required = true, defaultValue = "10") String pageSize, @RequestParam(value = "pageIndex", required = true, defaultValue = "1") String pageIndex, String comp, String belongcomp, String part, String datarange, String classroot, String class_id, String wb, String env, String recycle, String loc, String search) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
        if (ToolUtil.isEmpty(respar)) {
            return R.FAILURE_REQ_PARAM_ERROR();
        }
        int pagesize = respar.getIntValue("pagesize");
        int pageindex = respar.getIntValue("pageindex");
        String sql = zcService.buildQueryResAllGetdatalSql(belongcomp, comp, part, datarange, classroot, class_id, wb, env, recycle, loc, search, ps);
        System.out.println(sql);
        String sqlcnt = "select count(1) value from (" + sql + ") tab";
        int count = db.uniqueRecord(sqlcnt).getInteger("value");
        JSONObject retrunObject = new JSONObject();
        retrunObject.put("iTotalRecords", count);
        retrunObject.put("iTotalDisplayRecords", count);
        retrunObject.put("success", true);
        retrunObject.put("code", 200);
        retrunObject.put("data", ConvertUtil.OtherJSONObjectToFastJSONArray(
                db.query(DbUtil.getDBPageSql(db.getDBType(), sql, pagesize, pageindex)).toJsonArrayWithJsonObject()));
        return R.clearAttachDirect(retrunObject);
    }

    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/queryResAll.do")
    public R queryResAll(String comp, String belongcomp, String part, String datarange, String classroot, String class_id, String wb, String env, String recycle, String loc, String search) {
        TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
        return zcService.queryResAllGetData(belongcomp, comp, part, datarange, classroot, class_id, wb, env, recycle, loc, search, ps);
    }


    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/queryResAllById.do")
    public R queryResAllById(String id) {
        return zcService.queryResAllById(id);
    }

    @ResponseBody
    @Acl(info = "查询Res", value = Acl.ACL_USER)
    @RequestMapping(value = "/res/queryResAllByUUID.do")
    public R queryResAllByUUID(String uuid) {
        return zcService.queryResAllByUUID(uuid);
    }


//	@ResponseBody
//	@Acl(info = "查询Res", value = Acl.ACL_USER)
//	@RequestMapping(value = "/res/queryResByNodeForUser.do")
//	public R queryResByNodeForUser(String ip, String classCode) {
//		String sql = "select (select count(1) from res_attr_value t2 where t2.res_id=t.id)ucnt,t.* "
//				+ "from res t ,res_class tc where t.class_id=tc.class_id and tc.class_code=?";
//		return R.SUCCESS_OPER(db.query(sql, classCode).toJsonArrayWithJsonObject());
//	}

//	@ResponseBody
//	@Acl(info = "查询Res", value = Acl.ACL_USER)
//	@RequestMapping(value = "/res/queryResAllUsers.do")
//	public R queryResAllUsers(String status, String search, String type, String classCode, String attrCode) {
//
//		if (ToolUtil.isOneEmpty(classCode, attrCode)) {
//			return R.FAILURE_REQ_PARAM_ERROR();
//		}
//		String sql = " select a.* from res a,res_class b where a.class_id=b.class_id and b.dr=0 and a.dr=0 and b.class_code='"
//				+ classCode + "' ";
//
//		if (ToolUtil.isNotEmpty(search)) {
//			sql = sql + " and (a.ip like '%" + search + "%' or a.name like '%" + search + "%')";
//		}
//		sql = sql + " order by a.name ";
//		JSONArray res = new JSONArray();
//		RcdSet rs = db.query(sql);
//		res = ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());
//		for (int i = 0; i < res.size(); i++) {
//			String res_id = res.getJSONObject(i).getString("id");
//			String usersql = "select * from res_attr_value where res_id=? and attr_id in (select attr_id from res_class_attrs a,res_class b  where a.dr='0' and b.dr='0' and a.attr_code='"
//					+ attrCode + "' and a.class_id=b.class_id and b.class_code='" + classCode + "') and dr=0";
//			if (ToolUtil.isNotEmpty(status)) {
//				if (status.equals("enable")) {
//					usersql = usersql + " and status='enable'";
//				} else if (status.equals("disable")) {
//					usersql = usersql + " and status='disable'";
//				}
//			}
//
//			if (ToolUtil.isNotEmpty(type)) {
//				if (type.equals("all")) {
//
//				} else if (type.equals("work")) {
//					usersql = usersql + " and type in ('admin','yw','db','app') ";
//				} else if (type.equals("unknow")) {
//					usersql = usersql + " and type='" + type + "'";
//				}
//			}
//
//			RcdSet urs = db.query(usersql, res_id);
//			res.getJSONObject(i).put("users",
//					ConvertUtil.OtherJSONObjectToFastJSONArray(urs.toJsonArrayWithJsonObject()));
//		}
//		return R.SUCCESS_OPER(res);
//	}

//	@ResponseBody
//	@Acl(info = "查询Res", value = Acl.ACL_USER)
//	@RequestMapping(value = "/res/queryResValueByNodeForUser.do")
//	public R queryResValueByNodeForUser(String id) {
//		String sql = "select * from res_attr_value where res_id='" + id + "'";
//		return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
//	}
//
//	@ResponseBody
//	@Acl(info = "", value = Acl.ACL_USER)
//	@RequestMapping(value = "/addResNode.do")
//	@Transactional
//	public R addResNode(String id, String ip, String name, String classCode, String attrCode) {
//		if (ToolUtil.isOneEmpty(ip, classCode, attrCode)) {
//			return R.FAILURE_REQ_PARAM_ERROR();
//		}
//		if (ToolUtil.isEmpty(name)) {
//			name = ip;
//		}
//		Rcd crs = db.uniqueRecord("select * from res_class where dr=0 and class_code='" + classCode + "'");
//		if (crs == null) {
//			return R.FAILURE("没有对应的类型");
//		}
//
//		String classId = crs.getString("class_id");
//		Rcd attrrs = db.uniqueRecord(
//				"select * from res_class_attrs where class_id=? and attr_code='" + attrCode + "' and dr=0", classId);
//		if (attrrs == null) {
//			return R.FAILURE("没有对应的属性");
//		}
//		String attr_id = attrrs.getString("attr_id");
//
//		Res entity = new Res();
//		String uid = db.getUUID();
//		if (ToolUtil.isEmpty(id)) {
//			entity.setId(uid);
//		} else {
//			entity.setId(id);
//		}
//		entity.setClassId(classId);
//		entity.setName(name);
//		entity.setIp(ip);
//		ResServiceImpl.saveOrUpdate(entity);
//
//		// 插入attr_value
//		if (ToolUtil.isEmpty(id)) {
//			ResAttrValue rav = new ResAttrValue();
//			rav.setAttrId(attr_id);
//			rav.setAttrValue(attr_id);
//			rav.setResId(uid);
//			ResAttrValueServiceImpl.save(rav);
//		}
//		return R.SUCCESS_OPER();
//	}

    // root,inter,db,app,yw,unknow
//	public String autoChosenUserType(String user) {
//
//		if (ToolUtil.isEmpty(user)) {
//			return "unknow";
//		}
//		user = user.toLowerCase();
//		if (user.equals("root") || user.equals("administrator") || user.equals("admin")) {
//			return "admin";
//		}
//
//		if (user.equals("jinj") || user.equals("shouqw") || user.equals("zhangjj")) {
//			return "yw";
//		}
//
//		if (user.indexOf("mongodb") >= 0 || user.indexOf("db2inst") >= 0 || user.indexOf("oracle") >= 0
//				|| user.indexOf("postgres") >= 0 || user.indexOf("mysql") >= 0 || user.indexOf("dasusr") >= 0
//				|| user.indexOf("db2fenc") >= 0) {
//			return "db";
//		}
//
//		if (user.indexOf("apache") >= 0 || user.indexOf("nginx") >= 0 || user.indexOf("tomcat") >= 0
//				|| user.indexOf("weblogic") >= 0 || user.indexOf("grafana") >= 0 || user.indexOf("haproxy") >= 0
//				|| user.indexOf("influxdb") >= 0) {
//			return "app";
//		}
//
//		if (user.equals("wwwrun") || user.equals("suse-ncc") || user.equals("polkituser") || user.equals("news")
//				|| user.equals("messagebus") || user.equals("man") || user.equals("gdm") || user.equals("at")
//				|| user.equals("guest") || user.equals("bin") || user.equals("daemon") || user.equals("adm")
//				|| user.equals("lp") || user.equals("sync") || user.equals("shutdown") || user.equals("halt")
//				|| user.equals("mail") || user.equals("uucp") || user.equals("uuidd") || user.equals("operator")
//				|| user.equals("games") || user.equals("gopher") || user.equals("ftp") || user.equals("nobody")
//				|| user.equals("dbus") || user.equals("vcsa") || user.equals("abrt") || user.equals("ntp")
//				|| user.equals("haldaemon") || user.equals("ntp") || user.equals("saslauth") || user.equals("postfix")
//				|| user.equals("sshd") || user.equals("tcpdump") || user.equals("nscd") || user.equals("rtkit")
//				|| user.equals("pulse") || user.equals("avahi-autoipd") || user.equals("rpc")
//				|| user.equals("systemd-network") || user.equals("nfsnobody") || user.equals("polkitd")
//				|| user.equals("chrony") || user.equals("rpcuser") || user.equals("sys") || user.equals("lpd")
//				|| user.equals("invscout") || user.equals("snapp") || user.equals("ipsec") || user.equals("pconsole")
//				|| user.equals("esaadmin") || user.equals("atc") || user.equals("amdc") || user.equals("pac")
//				|| user.equals("atc2") || user.equals("listen") || user.equals("nuucp") || user.equals("smtp")
//				|| user.equals("noaccess") || user.equals("nobody4") || user.equals("nobody")
//				|| user.indexOf("window") >= 0 || user.indexOf("systemd-") >= 0) {
//			return "inter";
//		}
//
//		return "unknow";
//	}

//	public R addResBySingleNode(String data, String classCode, String attrCode) {
//

//
//		if (ToolUtil.isOneEmpty(data, classCode, attrCode)) {
//			return R.FAILURE_REQ_PARAM_ERROR();
//		}
//
//		JSONObject obj = JSONObject.parseObject(data);
//		if (ToolUtil.isEmpty(obj) || ToolUtil.isEmpty(obj.getString("ip"))) {
//			return R.FAILURE_REQ_PARAM_ERROR();
//		}
//
//		String ip = obj.getString("ip");
//		String name = "";
//		if (ToolUtil.isEmpty(obj.getString("name"))) {
//			name = ip;
//		} else {
//			name = obj.getString("name");
//		}
//
//		// 检查是否有类型
//		Rcd crs = db.uniqueRecord("select * from res_class where dr=0 and class_code='" + classCode + "'");
//		if (crs == null) {
//			return R.FAILURE("没有对应的类型");
//		}
//		String classId = crs.getString("class_id");
//		Rcd attrrs = db.uniqueRecord(
//				"select * from res_class_attrs where class_id=? and attr_code='" + attrCode + "' and dr=0", classId);
//		if (attrrs == null) {
//			return R.FAILURE("没有对应的属性");
//		}
//		String attrId = attrrs.getString("attr_id");
//
//		JSONArray listdata = obj.getJSONArray("list");
//
//		Rcd nrs = db.uniqueRecord("select * from res where class_id=? and dr=0 and ip=?", classId, ip);
//		String uid = "";
//		if (nrs == null) {
//			// 判断节点是否存在,全部重新插入
//			Res entity = new Res();
//			uid = db.getUUID();
//			entity.setId(uid);
//			entity.setClassId(classId);
//			entity.setName(name);
//			entity.setIp(ip);
//			ResServiceImpl.save(entity);
//
//			// 插入attr_value
//			ResAttrValue rav = new ResAttrValue();
//			rav.setAttrId(attrId);
//			rav.setAttrValue(attrId);
//			rav.setResId(uid);
//			ResAttrValueServiceImpl.save(rav);
//
//			// 插入attr_values,按照需求将标记为update更新用户列表
//			for (int i = 0; i < listdata.size(); i++) {
//				String act = listdata.getJSONObject(i).getString("act");
//				String user = listdata.getJSONObject(i).getString("user");
//				// 首次全部插入
//				if (act != null) {
//					ResAttrValues ent = new ResAttrValues();
//					ent.setAttrValue(user);
//					ent.setAttrValueId(attrId);
//					ent.setAttrId(attrId);
//					ent.setResId(uid);
//					String type = autoChosenUserType(user);
//					ent.setType(type);
//					if (type.equals("inter")) {
//						ent.setStatus("disable");
//					} else {
//						ent.setStatus(listdata.getJSONObject(i).getString("status"));
//					}
//					ResAttrValuesServiceImpl.save(ent);
//				 }
//
//			}
//		} else {
//			// 如果ip存在,则更新该IP所在条目,更新name
//			uid = nrs.getString("id");
//			Res resent = new Res();
//			resent.setName(name);
//			resent.setId(uid);
//			ResServiceImpl.saveOrUpdate(resent);
//			// 节点已经存在插入attr_values,按照需求更新用户列表
//			for (int i = 0; i < listdata.size(); i++) {
//				String act = listdata.getJSONObject(i).getString("act");
//				String user = listdata.getJSONObject(i).getString("user");
//				if (act != null) {
//					Rcd udrs = db.uniqueRecord(
//							"select * from res_attr_value where dr='0' and res_id=? and attr_id=? and attr_value=? ",
//							uid, attrId, user);
//					ResAttrValues ent = new ResAttrValues();
//					ent.setAttrValue(user);
//					ent.setAttrValueId(attrId);
//					ent.setAttrId(attrId);
//					ent.setResId(uid);
//					String type = autoChosenUserType(user);
//					if (udrs == null) {
//						// 新增用户
//						ent.setType(type);
//						if (type.equals("inter")) {
//							ent.setStatus("disable");
//						} else {
//							ent.setStatus(listdata.getJSONObject(i).getString("status"));
//						}
//
//						ResAttrValuesServiceImpl.save(ent);
//					} else {
//						// 判断update或delete
//						if ("update".equals(act)) {
//
//							// 内置全部强制修改成停用
//							if (type.equals("inter")) {
//								ent.setStatus("disable");
//							} else {
//								ent.setStatus(listdata.getJSONObject(i).getString("status"));
//							}
//							ResAttrValuesServiceImpl.saveOrUpdate(ent);
//						} else if ("delete".equals(act)) {
//
//							ResAttrValuesServiceImpl.removeById(udrs.getString("id"));
//
//						}
//					}
//
//
//				}
//
//			}
//
//		}
//		return R.SUCCESS_OPER();
//
//	}
//
//	@ResponseBody
//	@Acl(info = "查询Res", value = Acl.ACL_USER)
//	@RequestMapping(value = "/addUserBySingleNode.do")
//	@Transactional
//	public R addUserBySingleNode(String data, String classCode, String attrCode) {
//		return addResBySingleNode(data, "xtlist", "userlist");
//	}

}
