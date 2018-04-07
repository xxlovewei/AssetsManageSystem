package com.dt.module.om.controller;

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
import com.dt.module.om.service.MnService;

/**
 * @author: algernonking
 * @date: 2018年4月6日 下午8:02:16
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class MnController extends BaseController {
	@Autowired
	MnService mnService;

	@RequestMapping("/mn/saveService.do")
	@ResponseBody
	@Acl(info = "添加更新service", value = Acl.ACL_DENY)
	public R saveService() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		if (ToolUtil.isEmpty(ps.getString("id"))) {
			return mnService.addMnService(ps);
		} else {
			return mnService.updateMnService(ps);
		}

	}

	@RequestMapping("/mn/mnServiceAddNodes.do")
	@ResponseBody
	@Acl(info = "service添加adds", value = Acl.ACL_DENY)
	public R mnServiceAddNodes(String id, String node_ids) {
		return mnService.mnServiceAddNodes(id, node_ids);
	}

	@RequestMapping("/mn/mnServiceNeedAddNodes.do")
	@ResponseBody
	@Acl(info = "查询可以添加的nodes", value = Acl.ACL_DENY)
	public R mnServiceNeedAddNodes(String id) {
		return mnService.mnServiceNeedAddNodes(id);
	}

	@RequestMapping("/mn/delService.do")
	@ResponseBody
	@Acl(info = "删除service", value = Acl.ACL_DENY)
	public R delService(String id) {
		return mnService.delMnService(id);
	}

	@RequestMapping("/mn/queryServicById.do")
	@ResponseBody
	@Acl(info = "查询service", value = Acl.ACL_DENY)
	public R queryServicById(String id) {
		return mnService.queryMnServiceById(id);
	}

	@RequestMapping("/mn/queryServics.do")
	@ResponseBody
	@Acl(info = "查询service", value = Acl.ACL_ALLOW)
	public R queryServics(String id) {
		return mnService.queryMnService();
	}

	@RequestMapping("/mn/mnServiceAddNode.do")
	@ResponseBody
	@Acl(info = "添加node到service", value = Acl.ACL_DENY)
	public R mnServiceAddNode(String id) {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return mnService.mnServiceAddNode(ps);
	}

	@RequestMapping("/mn/mnServiceDelNode.do")
	@ResponseBody
	@Acl(info = "从service中删除node", value = Acl.ACL_DENY)
	public R mnServiceAddNode(String id, String node_id) {
		return mnService.mnServiceDelNode(id, node_id);
	}

	@RequestMapping("/mn/mnServiceQueryNodesById.do")
	@ResponseBody
	@Acl(info = "从service中查询nodes", value = Acl.ACL_DENY)
	public R mnServiceQueryNodesById(String id) {
		return mnService.mnServiceQueryNodesById(id);
	}

	@RequestMapping("/mn/mnServiceQueryNodeById.do")
	@ResponseBody
	@Acl(info = "从service中查询node", value = Acl.ACL_DENY)
	public R mnServiceQueryNodeById(String id, String node_id) {
		return mnService.mnServiceQueryNodeById(id, node_id);
	}

	@RequestMapping("/mn/queryMnServiceNodes.do")
	@ResponseBody
	@Acl(info = "从service中查询nodes", value = Acl.ACL_DENY)
	public R queryMnServiceNodes(String id) {
		return mnService.queryMnServiceNodes(id);
	}

}
