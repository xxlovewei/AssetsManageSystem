package com.dt.module.om.node.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.ResData;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.db.DB;
import com.dt.module.om.util.RemoteShellExecutor;
import com.dt.module.om.util.RemoteShellResult;

/**
 * @author: algernonking
 * @date: 2017年12月21日 下午3:15:41
 * @Description: TODO
 */
@Service
public class NodeService extends BaseService {

	public static String LOGIN_TYPE_SSH = "ssh";
	public static String NODE_TYPE_HOST = "host";
	public static String NODE_SMALL_TYPE_HOST_LINUX = "linux";
	public static String NODE_SMALL_TYPE_HOST_WINDOW = "window";
	public static String NODE_SMALL_TYPE_HOST_AIX = "aix";
 
 

	public ResData addNode(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("om_node");
		me.set("id", db.getUUID());
		me.setIf("type", ps.getString("type", ""));
		me.setIf("smalltype", ps.getString("smalltype", ""));
		me.setIf("name", ps.getString("name", ""));
		me.setIf("operator", ps.getString("operator", ""));
		me.setIf("ip", ps.getString("ip", "").trim());
		me.setIf("username", ps.getString("username", "").trim());
		String pwd = ps.getString("pwd", "0");
		me.setIf("pwd", pwd);
		me.setIf("pwdmd5", MD5Util.encrypt(pwd));
		me.setIf("logintype", ps.getString("logintype", ""));
		me.setIf("port", ps.getString("port", ""));
		me.setIf("isvalid", ps.getString("isvalid", "N"));
		me.setIf("isrunning", ps.getString("isrunning", "N"));
		me.setIf("deleted", "N");
		me.setIf("od", ps.getString("od", "1"));
		me.setIf("mark", ps.getString("mark", ""));
		me.setSE("cdate", DbUtil.getDBDateString(DB.instance().getDBType()));
		me.setSE("mdate", DbUtil.getDBDateString(DB.instance().getDBType()));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData updateNode(TypedHashMap<String, Object> ps) {
		Update me = new Update("om_node");
		me.setIf("name", ps.getString("name", ""));
		me.setIf("operator", ps.getString("operator", ""));
		me.setIf("ip", ps.getString("ip", "").trim());
		me.setIf("smalltype", ps.getString("smalltype", ""));
		String md5pwd = ps.getString("pwdmd5");
		// 存在pwdmd5，如果pwdmd5有变化,则把pwdmd5用pwd当作密码去保存
		if (ToolUtil.isNotEmpty(ps.getString("pwdmd5"))) {
			if (!md5pwd.equals(queryNodePwdMd5(ps.getString("id", "")))) {
				me.setIf("pwd", md5pwd);
				me.setIf("pwdmd5", MD5Util.encrypt(md5pwd));
			}
		}
		me.setSE("cdate", DbUtil.getDBDateString(DB.instance().getDBType()));
		me.setSE("mdate", DbUtil.getDBDateString(DB.instance().getDBType()));
		me.setIf("username", ps.getString("username", "").trim());
		me.setIf("logintype", ps.getString("logintype", ""));
		me.setIf("port", ps.getString("port", ""));
		me.setIf("isvalid", ps.getString("isvalid", "N"));
		me.setIf("od", ps.getString("od", "1"));
		me.setIf("mark", ps.getString("mark", ""));
		me.where().and("id=?", ps.getString("id", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	private String queryNodePwdMd5(String id) {

		Rcd rs = db.uniqueRecord("select pwdmd5 from om_node where id=?", id);
		if (rs == null) {
			return "";
		} else {
			return rs.getString("pwdmd5");
		}
	}

	public ResData queryNodeById(String id) {
		return ResData
				.SUCCESS_OPER(db.uniqueRecord("select * from om_node where deleted='N' and id=?", id).toJsonObject());
	}

	public ResData queryNodeHost(TypedHashMap<String, Object> ps) {
		ps.remove("type");
		ps.put("type", NODE_TYPE_HOST);
		return queryNode(ps);
	}

	 

	public ResData queryNode(TypedHashMap<String, Object> ps) {
		String sql = "select * from om_node where deleted='N' ";
		if (ToolUtil.isNotEmpty(ps.getString("type"))) {
			sql += "and type='" + ps.getString("type") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("smalltype"))) {
			sql += "and smalltype='" + ps.getString("smalltype") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("logintype"))) {
			sql += "and logintype='" + ps.getString("logintype") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("isrunning"))) {
			sql += "and isrunning='" + ps.getString("isrunning") + "'";
		}

		if (ToolUtil.isNotEmpty(ps.getString("isvalid"))) {
			sql += "and isvalid='" + ps.getString("isvalid") + "'";
		}
		if (ToolUtil.isNotEmpty(ps.getString("belongtoid"))) {
			sql += "and belongtoid='" + ps.getString("belongtoid") + "'";
		}

		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public ResData deleteNode(String id) {
		Update me = new Update("om_node");
		me.setIf("deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData executeHostNodeCommand(String id, String cmd) {
		Rcd rs = db.uniqueRecord("select * from om_node where deleted='N' and id=?", id);
		if (rs == null) {
			return ResData.FAILURE("无该节点");
		}
		
 
		String logintype = rs.getString("logintype");
		if (ToolUtil.isEmpty(logintype)) {
			return ResData.FAILURE("无登录方式");
		}


		if (ToolUtil.isEmpty(cmd) || ToolUtil.isEmpty(cmd.trim())) {
			return ResData.FAILURE("无执行命令");
		}

		String port = rs.getString("port");
		if (ToolUtil.isEmpty(port)) {
			return ResData.FAILURE("无端口");
		}

		String ip = rs.getString("ip");
		if (ToolUtil.isEmpty(ip)) {
			return ResData.FAILURE("无Ip地址");
		}

		String username = rs.getString("username");
		if (ToolUtil.isEmpty(username)) {
			return ResData.FAILURE("无用户名");
		}
		String pwd = rs.getString("pwd");
		if (ToolUtil.isEmpty(pwd)) {
			return ResData.FAILURE("无密码");
		}
		
		if(logintype.equals(LOGIN_TYPE_SSH)) {
			RemoteShellExecutor executor = new RemoteShellExecutor(ip, username, pwd, ConvertUtil.toInt(port));
			RemoteShellResult shell_rs=executor.exec(cmd);
			if(shell_rs.code==0) {
				return ResData.SUCCESS("成功", shell_rs.result);
			}else {
				return ResData.FAILURE(shell_rs.result.toString());
			}
			
		}
		return ResData.FAILURE("请选择ssh登录,当前仅支持ssh");
		
	}

}
