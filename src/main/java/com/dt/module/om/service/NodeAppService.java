package com.dt.module.om.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.om.util.RemoteShellExecutor;
import com.dt.module.om.util.RemoteShellResult;

/**
 * @author: algernonking
 * @date: 2017年12月27日 上午8:22:40
 * @Description: TODO
 */
@Service
public class NodeAppService extends BaseService {

	public ResData addNodeApp(TypedHashMap<String, Object> ps) {

		Insert me = new Insert("om_node_app");
		me.set("id", db.getUUID());
		me.set("deleted", "N");
		me.setIf("node_id", ps.getString("node_id", ""));
		me.setIf("type", ps.getString("type"));
		me.setIf("ip", ps.getString("ip"));
		me.setIf("name", ps.getString("name"));
		me.setIf("type", ps.getString("type"));
		me.setIf("username", ps.getString("username"));
		me.setIf("pwd", ps.getString("pwd"));
		me.setIf("pwdmd5", ps.getString("pwdmd5"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("isvalid", ps.getString("isvalid", "N"));
		me.setIf("status", ps.getString("status"));
		me.setIf("od", ps.getString("od", "1"));
		me.setIf("cmd_start", ps.getString("cmd_start"));
		me.setIf("cmd_stop", ps.getString("cmd_stop"));
		me.setIf("cmd_status", ps.getString("cmd_status"));
		me.setIf("use_host_login", ps.getString("use_host_login", "N"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData updateNodeApp(TypedHashMap<String, Object> ps) {

		Update me = new Update("om_node_app");
		me.setIf("type", ps.getString("type"));
		me.setIf("name", ps.getString("name"));
		me.setIf("ip", ps.getString("ip"));
		me.setIf("type", ps.getString("type"));
		me.setIf("username", ps.getString("username"));
		me.setIf("pwd", ps.getString("pwd"));
		me.setIf("pwdmd5", ps.getString("pwdmd5"));
		me.setIf("mark", ps.getString("mark"));
		me.setIf("isvalid", ps.getString("isvalid"));
		me.setIf("od", ps.getString("od"));
		me.setIf("cmd_start", ps.getString("cmd_start"));
		me.setIf("cmd_stop", ps.getString("cmd_stop"));
		me.setIf("use_host_login", ps.getString("use_host_login", "N"));
		me.setIf("cmd_status", ps.getString("cmd_status"));
		me.where().and("id=?", ps.getString("id", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData deleteNodeApp(String id) {
		Update me = new Update("om_node_app");
		me.setIf("deleted", "Y");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData queryNodeApp(TypedHashMap<String, Object> ps) {
		return ResData.SUCCESS();
	}

	public ResData queryNodeAppById(String node_id) {
		return ResData.SUCCESS_OPER(db.query("select * from om_node_app where deleted='N' and node_id=?", node_id)
				.toJsonArrayWithJsonObject());
	}

	public ResData queryNodeAppByNodeId(String id) {
		return ResData.SUCCESS_OPER(
				db.uniqueRecord("select * from om_node_app where deleted='N' and id=?", id).toJsonObject());
	}

	public ResData startNodeApp(String id) {
		return null;
	}

	public ResData stopNodeApp(String id) {
		return null;
	}

	public ResData statusNodeApp(String id) {
		return null;
	}

	public ResData queryNodeAppType() {

		
		return null;
	}

	public ResData executeNodeAppCommand(String id, String cmd) {
		Rcd rs = db.uniqueRecord("select * from om_node_app where deleted='N' and id=?", id);
		if (rs == null) {
			return ResData.FAILURE("无该节点");
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

		RemoteShellExecutor executor = new RemoteShellExecutor(ip, username, pwd, ConvertUtil.toInt(port));
		RemoteShellResult shell_rs = executor.exec(cmd);
		if (shell_rs.code == 0) {
			return ResData.SUCCESS("成功", shell_rs.result);
		} else {
			return ResData.FAILURE(shell_rs.result.toString());
		}
	}
}