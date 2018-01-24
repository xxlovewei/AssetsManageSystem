package com.dt.module.om.term.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.module.om.node.service.NodeService;
import com.dt.module.om.term.entity.Machine;

/**
 * @author: algernonking
 * @date: 2018年1月17日 下午12:16:49
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class TerminalController extends BaseController {

	@Autowired
	private NodeService nodeService;

	@RequestMapping("/term/setCurrentMachine.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "设置当前机器")
	public ResData setCurrentMachine(String id) {
		ResData res = nodeService.queryNodeById(id);
		JSONObject e = res.getDataToJSONObject();
		if (res.isSuccess()) {
			Machine machine = new Machine(e.getString("name"), e.getString("ip"), e.getString("username"),
					e.getString("pwd"), e.getInteger("port"));
			super.getSession().setAttribute("currentMachine", machine);

		} else {
			return res;
		}
		e.remove("pwd");
		return ResData.SUCCESS_OPER(e);
	}
}
