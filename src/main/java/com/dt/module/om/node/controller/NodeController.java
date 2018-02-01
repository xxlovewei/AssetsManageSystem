package com.dt.module.om.node.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.dao.util.TypedHashMap;
import com.dt.module.om.node.service.NodeService;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;

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
	public ResData queryNodeById(String id) {
		return nodeService.queryNodeById(id);
	}

	@RequestMapping("/node/saveNode.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "保存节点")
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
	@Acl(value = Acl.TYPE_DENY, info = "查询节点")
	public ResData queryNode() {
		return nodeService.queryNode((TypedHashMap<String, Object>) HttpKit.getRequestParameters());
	}

	@RequestMapping("/node/queryNodeHost.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据条件查询主机")
	public ResData queryNodeHost() {
		return nodeService.queryNodeHost((TypedHashMap<String, Object>) HttpKit.getRequestParameters());
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
