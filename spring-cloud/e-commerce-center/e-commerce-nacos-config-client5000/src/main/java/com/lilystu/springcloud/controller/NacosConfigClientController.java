package com.lilystu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //@RefreshScope 是 springcloud 的原生注解，实现配置信息自动刷新
public class NacosConfigClientController {

    /**
     * 1.client 会拉取 Nacos Server 的 e-commerce-nacos-config-client-dev.yaml
     * config:
     * ip: "122.11.11.11"
     * namw: "你好，lily"
     * 2.@Value("${config.ip}") 会将 config.ip 赋给 configIp
     */

    @Value("${config.ip}")
    private String configIp;
    @Value("${config.name}")
    private String configName;

    @GetMapping("/nacos/config/ip")
    public String getConfigIp() {
        return configIp;
    }

    @GetMapping("/nacos/config/name")
    public String getConfigName() {
        return configName;
    }
}
