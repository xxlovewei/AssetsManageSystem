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
public class DeployDtDevMac {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		String tomcatOnlyPort = "18004";
		String dir = "/opt/tomcat/tomcat_dt/webapps";
		String filename = "dt";
		String rfile = dir + "/" + filename + ".war";
		String fstr = "/opt/" + filename + ".war";

		SftpClient sftp = new SftpClient();
		Machine m = new Machine("localhost", "121.43.168.125", "root", "3UZNCxDF4kfouE", 59991);
		sftp.connect(m, "upload");
		sftp.changeDirectory("/tmp");
		File f = new File(fstr);
		try {
			sftp.uploadFile(f, filename + ".war", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RemoteShellExecutor executor = new RemoteShellExecutor("121.43.168.125", "root", "3UZNCxDF4kfouE", 59991);
	//	executor.exec("/usr/bin/cp " + rfile + " /tmp/shop." + filename + ".bak --backup").print();

		// 停应用
		executor.exec("/usr/sbin/lsof -i:" + tomcatOnlyPort + " |awk '{print $2}' |grep -v PID|xargs kill -9 {} ")
				.print();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 删除
		executor.exec("rm -rf " + rfile).print();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 覆盖
		executor.exec("mv /tmp/" + filename + ".war " + dir + "/").print();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("nohup sh " + dir + "/../bin/startup.sh;sleep 40 &");
		executor.exec("nohup sh " + dir + "/../bin/startup.sh;sleep 40 &").print();

	}

}
