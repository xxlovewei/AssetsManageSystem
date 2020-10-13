package com.dev;


import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.base.service.impl.ServerMonitorService;
import com.dt.module.om.term.entity.Machine;
import com.dt.module.om.term.websocket.SftpClient;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Demo {

    private static void printComputerSystem(final ComputerSystem computerSystem) {

    }

    public static void main(String[] args) {

        String name = null;
        try {
            name = java.net.URLEncoder.encode("&a=12&adf=1212&c=测试", "UTF-8");
            System.out.println(name);
            name = java.net.URLEncoder.encode(name, "UTF-8");
            System.out.println(name);
            name = java.net.URLDecoder.decode(name, "UTF-8");
            System.out.println(name);
            System.out.println(java.net.URLDecoder.decode(name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // System.out.println(ServerMonitorService.createUniqueSn());
//        StringBuffer params = new StringBuffer();
//        try {
//            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
////            params.append("name=" + URLEncoder.encode("&", "utf-8"));
////            params.append("&");
//            params.append("age=24");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }

//

//
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        SecurityManager securityManager = factory.getInstance();
//
//        SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);
//
//        JcaCipherService jcaCipherService =new DefaultBlockCipherService("RSA") ;
//        jcaCipherService.decrypt("UOK4yfsmoavRDq+5NiYuMh2s2KC9GBzUCra4MGIx+7ERbdb0IRGsQZxDza7kir/OAupq18Vzm8cZzaHoKeC/TA==".getBytes(), "MIIBVwIBADANBgkqhk".getBytes());
//


//        for (int i=0;i<30;i++) {
//
//            int v=i;
//            ThreadTaskHelper.run(new Runnable() {
//                @Override
//                public void run( ) {
//                    Demo d=new Demo();
//                    d.action(v);
//                }
//            });
//
//        }

    }

    public void action(int v) {


        //   int v=11;
        System.out.println("thread:" + v);
        String dir = "/tmp";
        String filename = "dt";
        String rfile = dir + "/" + filename + ".war";
        String fstr = "/tmp";
        PropertiesUtil p;
        String pwd = "";


        SftpClient sftp = new SftpClient();
        Machine m = new Machine("39.105.191.22", "39.105.191.22", "root", pwd, 12500);
        sftp.connect(m, "upload");
        sftp.changeDirectory("/tmp");

        try {
            System.out.println("mkdir" + filename + ".war" + v);
            sftp.mkDir(filename + ".war" + v);
            File f = new File(fstr + "/" + filename + ".war" + v);
            sftp.changeDirectory("/tmp" + "/" + filename + ".war" + v);
            sftp.uploadFile(f, filename + ".war", null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}
