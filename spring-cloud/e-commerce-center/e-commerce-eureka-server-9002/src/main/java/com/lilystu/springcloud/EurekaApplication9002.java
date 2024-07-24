package com.lilystu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer 表示该程序,作为 Eureka Server
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication9002.class, args);
    }
}
