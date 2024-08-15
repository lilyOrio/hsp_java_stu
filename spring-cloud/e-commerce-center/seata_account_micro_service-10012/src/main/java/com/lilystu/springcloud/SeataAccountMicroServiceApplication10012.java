package com.lilystu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//注意, 需要取消数据源的自动配置， #而是使用 seata 数据源代理, DataSourceProxyConfig
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class SeataAccountMicroServiceApplication10012 {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountMicroServiceApplication10012.class,args);
    }
}
