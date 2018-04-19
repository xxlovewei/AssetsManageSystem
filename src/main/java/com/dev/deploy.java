package com.dev;

import java.io.File;
import java.io.IOException;
import com.dt.module.om.term.entity.Machine;
import com.dt.module.om.term.websocket.SftpClient;

/**
 * @author: jinjie
 * @date: 2018年4月19日 下午2:38:43
 * @Description: TODO
 */
public class deploy {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		String dir = "/opt/tomcat/apache-tomcat-8.0.45/webapps";
		String fstr = "d:\\dt.war";
		SftpClient sftp = new SftpClient();
		Machine m = new Machine("localhost", "121.43.168.125", "root", "3UZNCxDF4kfouE", 59991);
		sftp.connect(m, "upload");
		sftp.changeDirectory(dir);
		File f = new File(fstr);
		try {
			sftp.uploadFile(f, "dt.war", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fstr + " deploy success on" + dir);
	}

}
