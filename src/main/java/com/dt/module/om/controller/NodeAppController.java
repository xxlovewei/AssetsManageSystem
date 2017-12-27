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
import com.dt.module.om.service.NodeAppService;

/**
 * @author: algernonking
 * @date: 2017年12月27日 上午8:35:04
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class NodeAppController extends BaseController {

	@Autowired
	private NodeAppService nodeAppService;

	@RequestMapping("/node/queryNodeAppById.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据Id查询应用节点")
	public ResData queryNodeAppById(String id) {
		return nodeAppService.queryNodeAppById(id);
	}

	@RequestMapping("/node/queryNodeAppByNodeId.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "根据节点Id查询应用节点")
	public ResData queryNodeAppByNodeId(String node_id) {
		return nodeAppService.queryNodeAppById(node_id);
	}

	@RequestMapping("/node/saveNodeApp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "保存应用节点")
	public ResData saveNodeApp() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return nodeAppService.addNodeApp(ps);
		} else {
			return nodeAppService.updateNodeApp(ps);
		}
	}

	@RequestMapping("/node/deleteNodeApp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "删除应用节点")
	public ResData deleteNodeApp(String id) {
		return nodeAppService.deleteNodeApp(id);
	}

	@RequestMapping("/node/startNodeApp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "启动应用节点")
	public ResData startNodeApp(String id) {
		return nodeAppService.startNodeApp(id);
	}

	@RequestMapping("/node/stopNodeApp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "启动应用节点")
	public ResData stopNodeApp(String id) {
		return nodeAppService.stopNodeApp(id);
	}

	@RequestMapping("/node/statusNodeApp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "查询应用节点状态")
	public ResData statusNodeApp(String id) {
		return nodeAppService.statusNodeApp(id);
	}
}
