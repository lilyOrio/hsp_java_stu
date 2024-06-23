package com.lilysti.lilyspringboot;

import org.apache.catalina.startup.Tomcat;

public class LilySpringApplication {

    //创建Tomcat对象，并关联Spring容器，以及启动tomcat
    public static void run(){
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(9090);
            tomcat.start();
            System.out.println("=========9090等待请求接入==========");
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
