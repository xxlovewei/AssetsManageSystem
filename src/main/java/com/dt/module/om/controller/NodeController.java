package com.dt.module.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.om.service.NodeService;

/**
 * @author: algernonking
 * @date: 2017年12月21日 下午4:53:18
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class NodeController extends BaseController {
	@Autowired
	private NodeService nodeService;

	@RequestMapping("/node/queryNodeById.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据Id查询节点")
	public ResData deleteShop(String id) {
		return nodeService.queryNodeById(id);
	}

	@RequestMapping("/node/saveNode.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据Id查询节点")
	public ResData saveNode() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return nodeService.addNode(ps);
		} else {
			return nodeService.updateNode(ps);
		}
	}

	@RequestMapping("/node/queryNode.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据Id查询节点")
	public ResData queryNode() {
		return nodeService.queryNode((TypedHashMap<String, Object>) HttpKit.getRequestParameters());
	}

	@RequestMapping("/node/queryNodeHost.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据条件查询主机")
	public ResData queryNodeHost() {
		return nodeService.queryNodeHost((TypedHashMap<String, Object>) HttpKit.getRequestParameters());
	}

	@RequestMapping("/node/queryNodeDb.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据条件查询数据库")
	public ResData queryNodeDb() {
		return nodeService.queryNodeDb((TypedHashMap<String, Object>) HttpKit.getRequestParameters());
	}

	@RequestMapping("/node/queryNodeApp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据条件查询应用")
	public ResData queryNodeApp() {
		return nodeService.queryNodeDb((TypedHashMap<String, Object>) HttpKit.getRequestParameters());
	}
	

	@RequestMapping("/node/deleteNode.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "删除节点")
	public ResData deleteNode(String id) {
		return nodeService.deleteNode(id);
	}
	

	@RequestMapping("/node/executeHostNodeCommand.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "节点执行命令")
	public ResData executeHostNodeCommand(String id,String cmd) {
		return nodeService.executeHostNodeCommand(id, cmd);
	}

	
	
}
