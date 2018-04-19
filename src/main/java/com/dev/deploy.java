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
		String dir = "/opt/apache-tomcat-8.0.36/webapps";
		String fstr = "d:\\tyh.war";
		SftpClient sftp = new SftpClient();
		Machine m = new Machine("localhost", "192.168.188.18", "root", "Youngor8222", 22);
		sftp.connect(m, "upload");
		sftp.changeDirectory(dir);
		File f = new File(fstr);
		try {
			sftp.uploadFile(f, "tyh.war", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fstr + " deploy success on" + dir);
	}

}
