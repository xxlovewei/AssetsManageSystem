package com.dt.module.zb.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Aug 6, 2019 12:56:18 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api")
public class ZbSmsController extends BaseController {

	@Value("${sms.exec}")
	public String smsExec;
	
	@RequestMapping("/zb/sms.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW)
	public R sms(String tel, String ct,String title) {
		System.out.println("Tel:" + tel + ",Ct:" + ct+",title:"+title);
		if (ToolUtil.isOneEmpty(tel, ct)) {
			return R.FAILURE_NO_DATA();
		}
		exeCmd2(smsExec, tel, ct);
		return R.SUCCESS_OPER();
	}

	public static void main(String[] args) {

		String a = "1572699845";
		String b = "我是 一整个 测试";
	 
		exeCmd2("/tmp/a.sh" ,a,b);

		 
	}

	public static void exeCmd2(String command, String par1, String par2) {
		Process process = null;
		Runtime runTime = Runtime.getRuntime();
		String[] commands = new String[] { command, par1, par2 };
		try {
			process = runTime.exec(commands);
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void exeCmd(String command) {
		Process process = null;

		try {
			System.out.println(command);
			process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String data = "";
			while ((data = reader.readLine()) != null) {
				System.out.println(data);
			}

			int exitValue = process.waitFor();

			if (exitValue != 0) {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
