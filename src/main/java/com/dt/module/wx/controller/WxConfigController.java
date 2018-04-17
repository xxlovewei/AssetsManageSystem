package com.dt.module.wx.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.module.wx.service.CoreService;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.wx.service.WxConfigService;
import com.dt.module.wx.service.WxService;

/**
 * @author: jinjie
 * @date: 2018年3月22日 上午8:42:21
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class WxConfigController extends BaseController {

	@Autowired
	private CoreService coreService;

	@Autowired
	private WxConfigService wxConfigService;

	@Autowired
	private WxService wxService;

	@ResponseBody
	@Acl(info = "查询所有的tickets数据", value = Acl.ACL_ALLOW)
	@RequestMapping("/wx/queryMapTickets.do")
	public R queryMapTickets() {
		return wxService.queryMapTickets();
	}

	@ResponseBody
	@Acl(info = "从本地配置中获取app的token数据", value = Acl.ACL_ALLOW)
	@RequestMapping("/wx/getAccessToken.do")
	public R getAccessToken() {
		return wxService.queryAccessToken();
	}

	@ResponseBody
	@Acl(info = "从本地配置中获取app配置信息", value = Acl.ACL_ALLOW)
	@RequestMapping("/wx/getConfig.do")
	public R getConfig(String url) {
		return wxConfigService.queryWxConfig(url);
	}

	@Acl(info = "验证签名", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/core.do", method = RequestMethod.GET)
	public void coreGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCommon.print("coreGet");
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (WxService.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;

	}

	@Acl(info = "消息响应", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/core.do", method = RequestMethod.POST)
	public void corePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BaseCommon.print("corePost");
		// 消息的接收、处理、响应
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息
		String respXml = coreService.processRequest(request);
		// 响应消息

		if (respXml != null) {
			PrintWriter out = response.getWriter();
			out.print(respXml);
			out.close();
		}
	}

}
