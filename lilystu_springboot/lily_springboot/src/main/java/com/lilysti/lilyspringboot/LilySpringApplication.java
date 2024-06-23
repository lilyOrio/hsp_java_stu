package com.lilysti.lilyspringboot;

import org.apache.catalina.startup.Tomcat;

public class LilySpringApplication {

    //创建Tomcat对象，并关联Spring容器，以及启动tomcat
    public static void run(){
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(9090);
            //让tomcat将请求转发到Spring web 容器中，需要关联容器
            //"/lilyboot"就是我们项目的application context，就是原来配置tomcat时指定的application context
            //第二个参数指定项目目录
            tomcat.addWebapp("/lilyboot","D:\\code\\java\\hsp_java_stu\\lilystu_springboot\\lily_springboot");
            tomcat.start();
            System.out.println("=========9090等待请求接入==========");
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
