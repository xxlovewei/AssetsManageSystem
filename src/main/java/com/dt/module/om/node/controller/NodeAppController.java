package com.dt.module.om.node.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.om.node.service.NodeAppService;

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
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "根据Id查询应用节点")
	public R queryNodeAppById(String id) {
		return nodeAppService.queryNodeAppById(id);
	}

	@RequestMapping("/node/queryNodeAppByNodeId.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "根据节点Id查询应用节点")
	public R queryNodeAppByNodeId(String node_id) {
		return nodeAppService.queryNodeAppById(node_id);
	}

	@RequestMapping("/node/saveNodeApp.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "保存应用节点")
	public R saveNodeApp() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("id");
		if (ToolUtil.isEmpty(id)) {
			return nodeAppService.addNodeApp(ps);
		} else {
			return nodeAppService.updateNodeApp(ps);
		}
	}

	@RequestMapping("/node/deleteNodeApp.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "删除应用节点")
	public R deleteNodeApp(String id) {
		return nodeAppService.deleteNodeApp(id);
	}

	@RequestMapping("/node/startNodeApp.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "启动应用节点")
	public R startNodeApp(String id) {
		return nodeAppService.startNodeApp(id);
	}

	@RequestMapping("/node/stopNodeApp.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "启动应用节点")
	public R stopNodeApp(String id) {
		return nodeAppService.stopNodeApp(id);
	}

	@RequestMapping("/node/statusNodeApp.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "查询应用节点状态")
	public R statusNodeApp(String id) {
		return nodeAppService.statusNodeApp(id);
	}
}
