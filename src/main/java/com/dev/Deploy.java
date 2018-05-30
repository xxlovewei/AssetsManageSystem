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
public class Deploy {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		String dir = "/opt/tomcat/apache-tomcat-8.0.45/webapps";
		String filename = "dt";
		String fstr = "d:\\" + filename + ".war";
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
		System.out.println("mv /tmp/" + filename + ".war " + dir + "/");
		executor.exec("mv /tmp/" + filename + ".war " + dir + "/").print();
		System.out.println(fstr + " deploy success on" + dir);
	}

}
