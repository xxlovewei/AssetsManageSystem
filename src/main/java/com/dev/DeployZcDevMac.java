package com.dev;

import com.dt.module.om.term.entity.Machine;
import com.dt.module.om.term.websocket.SftpClient;
import com.dt.module.om.util.RemoteShellExecutor;

import java.io.File;
import java.io.IOException;

/**
 * @author: algernonking
 * @date: 2018年4月19日 下午2:38:43
 * @Description: TODO
 */
public class DeployZcDevMac {

    /**
     * @param args
     * @Title: main
     * @Description: TODO
     * @return: void
     */

    public static void main(String[] args) {

        String tomcatOnlyPort = "3037";
        String dir = "/opt/tomcat/tomcat_shopuat/webapps";
        String filename = "dt-2.1.0";
        String rfile = dir + "/" + filename + ".war";
        String fstr = "/Users/lank/.m2/repository/com/dt/dt/2.1.0/" + filename + ".war";
        PropertiesUtil p;
        String pwd = "";
        try {
            p = new PropertiesUtil("/Users/lank/conf.properties");
            pwd = p.readValue("zc.rootpwd");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        SftpClient sftp = new SftpClient();
        Machine m = new Machine("localhost", "39.105.191.22", "root", pwd, 12500);
        sftp.connect(m, "upload");
        sftp.changeDirectory("/tmp");
        File f = new File(fstr);
        try {
            sftp.uploadFile(f, filename + ".war", null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RemoteShellExecutor executor = new RemoteShellExecutor("39.105.191.22", "root", pwd, 12500);
        // executor.exec("/usr/bin/cp " + rfile + " /tmp/shop." + filename + ".bak
        // --backup").print();

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
        executor.exec("rm -rf /opt/tomcat/tomcat_shopuat/temp").print();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 覆盖
        executor.exec("mv /tmp/" + filename + ".war " + dir + "/dt.war").print();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("nohup sh " + dir + "/../bin/startup.sh;sleep 40 &");
        executor.exec("nohup sh " + dir + "/../bin/startup.sh;sleep 40 &").print();

    }

}
