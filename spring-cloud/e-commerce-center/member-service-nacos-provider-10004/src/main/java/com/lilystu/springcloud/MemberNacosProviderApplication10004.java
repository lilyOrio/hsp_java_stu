package com.lilystu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient 引入的是 Nacos 发现注解
@EnableDiscoveryClient
@SpringBootApplication
public class MemberNacosProviderApplication10004 {
    public static void main(String[] args) {
        SpringApplication.run(MemberNacosProviderApplication10004.class, args);
    }
}
