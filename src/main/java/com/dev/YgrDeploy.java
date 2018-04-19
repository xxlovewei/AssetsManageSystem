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
public class YgrDeploy {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		String dir = "/home/mnapp/apache-tomcat-8.0.50/webapps";
		String fstr = "d:\\dt.war";
		SftpClient sftp = new SftpClient();
		Machine m = new Machine("localhost", "192.168.188.209", "root", "YGRGC321", 8522);
		sftp.connect(m, "upload");
		sftp.changeDirectory("/tmp");
		File f = new File(fstr);
		try {
			sftp.uploadFile(f, "dt.war", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RemoteShellExecutor executor = new RemoteShellExecutor("192.168.188.209", "root", "YGRGC321", 8522);
		System.out.println("mv /tmp/dt.war " + dir + "/");
		executor.exec("mv /tmp/dt.war " + dir + "/").print();
		System.out.println(fstr + " deploy success on" + dir);
	}

}
