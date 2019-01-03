package com.dev;

import java.io.File;
import java.io.IOException;
import com.dt.module.om.term.entity.Machine;
import com.dt.module.om.term.websocket.SftpClient;
import com.dt.module.om.util.RemoteShellExecutor;

/**
 * @author: jinjie
 * @date: 2018年4月19日 下午2:38:43
 * @Description: TODO
 */
public class DeployDtMac {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		String tomcatOnly = "tomcat_dtdev";
		String dir = "/opt/tomcat/tomcat_dtdev/webapps";
		String filename = "dt";
		String fstr = "/opt/" + filename + ".war";

		SftpClient sftp = new SftpClient();
		Machine m = new Machine("localhost", "39.105.191.22", "root", "Mm15888Mm15888", 22);
		sftp.connect(m, "upload");
		sftp.changeDirectory("/tmp");
		File f = new File(fstr);
		try {
			sftp.uploadFile(f, filename + ".war", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RemoteShellExecutor executor = new RemoteShellExecutor("39.105.191.22", "root", "Mm15888Mm15888", 22);
		// 停应用
		executor.exec("ps -ef|grep " + tomcatOnly + "|grep -v grep |awk '{print $2}' | xargs kill -9 ").print();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 删除
		executor.exec("rm -rf " + dir + "/" + filename).print();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 覆盖
		executor.exec("mv /tmp/" + filename + ".war " + dir + "/").print();
		System.out.println("nohup sh " + dir + "/../bin/startup.sh;sleep 30 &");
		executor.exec("nohup sh " + dir + "/../bin/startup.sh;sleep 30 &").print();
	}

}
