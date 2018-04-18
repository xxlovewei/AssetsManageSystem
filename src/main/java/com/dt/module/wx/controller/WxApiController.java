package com.dt.module.wx.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.wx.service.CoreService;
import com.dt.module.wx.service.WxService;

/**
 * @author: jinjie
 * @date: 2018年4月18日 上午8:57:49
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class WxApiController extends BaseController {

	@Autowired
	private WxService wxService;

	@Autowired
	private CoreService coreService;

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
		if (wxService.checkSignature(signature, timestamp, nonce)) {
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

	@ResponseBody
	@Acl(info = "创建菜单", value = Acl.ACL_DENY)
	@RequestMapping(value = "/wx/createMenu.do")
	public R createMenu() {
		return wxService.createMenu();
	}

	@ResponseBody
	@Acl(info = "查询菜单", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/queryMenu.do")
	public R queryMenu() {
		return wxService.queryMenu();
	}

	@ResponseBody
	@Acl(info = "查询用户信息", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/wx/queryUserInfo.do")
	public R queryUserInfo(String open_id) {
		return wxService.queryUserInfo(open_id);
	}
}
