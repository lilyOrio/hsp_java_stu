package com.lilystu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomizationBean {
    @Bean
    //赋予RestTemplate负载均衡的能力, [即选 择 MEMBER-SERVICE-PROVIDER 某一个服务访问]
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
