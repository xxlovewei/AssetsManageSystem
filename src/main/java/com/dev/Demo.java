package com.dev;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;

import cn.hutool.core.util.IdUtil;
import com.dt.core.cache.ThreadTaskHelper;
import com.dt.module.om.term.entity.Machine;
import com.dt.module.om.term.websocket.SftpClient;

import java.io.File;
import java.io.IOException;


public class Demo {


    public static void main(String[] args) {


        for (int i=0;i<30;i++) {

            int v=i;
            ThreadTaskHelper.run(new Runnable() {
                @Override
                public void run( ) {
                    Demo d=new Demo();
                    d.action(v);
                }
            });

        }

    }

    public void action(int v){



     //   int v=11;
        System.out.println("thread:"+v);
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
            sftp.changeDirectory("/tmp"+"/"+ filename + ".war" + v);
            sftp.uploadFile(f, filename + ".war", null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        }


}
