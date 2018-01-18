package com.dt.module.om.term.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.module.om.term.entity.Machine;
import com.dt.module.om.term.websocket.SftpClient;

import ch.ethz.ssh2.SFTPException;

/**
 * @author: algernonking
 * @date: 2018年1月18日 上午9:06:42
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class SftpWindowController extends BaseController {
	protected Map<String, Object> sftpsession;

	@RequestMapping("/sftp/exeCommand.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "执行sftp的命令")
	public ResData exeCommand(String cmd, String cmdParam, String fileFileName, String permissions) {

		ToolUtil.printRequestMap(super.getHttpServletRequest());
		SftpClient sftp = getSftpClient(getUserId());
		if (sftp != null && sftp.isConnected()) {
			try {

				switch (cmd) {
				case "cd":
					sftp.changeDirectory(cmdParam);
					break;
				case "rm":
					sftp.deleteFile(cmdParam);
					break;
				case "mkdir":
					sftp.mkDir(cmdParam);
					break;
				// case "upload": sftp.uploadFile(file, fileFileName , session); break;
				case "attr":
					sftp.setAttributes(fileFileName, Integer.valueOf("" + permissions, 8));
					break;
				}
				System.out.println("start to ls");
				String json = JSON.toJSONString(sftp.ls());
				json = new String(json.getBytes("GBK"), "UTF-8");
				return ResData.SUCCESS_OPER(JSONArray.parse(json));

			} catch (SFTPException ex) {
				return ResData.FAILURE("权限不够，操作失败！");
			} catch (IOException e) {
				e.printStackTrace();
				return ResData.FAILURE("执行命令错误");
			}

		} else {
			return ResData.FAILURE("连接错误");
		}

	}

	private SftpClient getSftpClient(String user_id) {
		return (SftpClient) sftpsession.get(user_id);
	}

	@RequestMapping("/sftp/downloadFile.do")
	@Acl(value = Acl.TYPE_DENY, info = "使用sftp下载文件")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String fileFileName = "";
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String[] values = request.getParameterValues(name);
			if (values != null && values.length > 0) {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < values.length; i++) {
					builder.append(values[i] + "");
				}
				if (name.equals("fileFileName")) {
					fileFileName = builder.toString();
				}
			}
		}
		SftpClient sftp = getSftpClient(getUserId());

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + java.net.URLEncoder.encode(fileFileName, "UTF-8"));
		try {
			InputStream inStream = sftp.downloadFile(fileFileName);
			OutputStream myout = response.getOutputStream();

			byte[] buff = new byte[1024];
			int len = -1;
			while ((len = inStream.read(buff)) != -1) {
				myout.write(buff, 0, len);
			}
			myout.close();
			inStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/sftp/connectSftp.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "sftp连接")
	public ResData connectSftp(String id) {
		String user_id = getUserId();
		Machine m = (Machine) super.getSession().getAttribute("currentMachine");
		if (ToolUtil.isEmpty(user_id)) {
			return ResData.FAILURE_NOT_LOGIN();
		}
		if (ToolUtil.isEmpty(m)) {
			return ResData.FAILURE("为选择Machine");
		}
		SftpClient sftp = new SftpClient(m, "");
		if (sftp != null && !sftp.isConnected()) {
			return ResData.FAILURE("无法登录");
		}
		try {
			String json = JSON.toJSONString(sftp.ls());
			json = new String(json.getBytes("GBK"), "UTF-8");
			if (sftpsession == null) {
				sftpsession = new HashMap<String, Object>();
			}
			sftpsession.put(user_id, sftp);
			return ResData.SUCCESS_OPER(JSONArray.parse(json));
			// inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
			// put the sftp client into session
		} catch (IOException e) {
			return ResData.FAILURE("Machine连接失败");
		}
	}
}