package com.lilystu.springboot;

import com.lilystu.springboot.servlet.Servlet_;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.http.HttpServlet;

@SpringBootApplication
//@ServletComponentScan(basePackages = {"com.lilystu.springboot"})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc =
                SpringApplication.run(Application.class, args);
//        ioc.close();//会触发监听器的方法contextDestroyed
        ioc.getBean(HttpServlet.class);
        System.out.println("ioc");
    }
}
