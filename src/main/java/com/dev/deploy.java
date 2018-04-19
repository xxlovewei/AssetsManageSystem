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
		// TODO Auto-generated method stub
		SftpClient sftp = new SftpClient();
		// String name, String hostname, String username, String password,
		// Integer port
		Machine m = new Machine("localhost", "192.168.188.18", "root", "Youngor8222", 22);
		sftp.connect(m, "upload");
		System.out.println(sftp.getCurrentCatalog());
		sftp.changeDirectory("/opt/apache-tomcat-8.0.36/webapps");
		System.out.println(sftp.getCurrentCatalog());
		File f = new File("d:\\a.txt");
		try {
			sftp.uploadFile(f, "a.txt", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
