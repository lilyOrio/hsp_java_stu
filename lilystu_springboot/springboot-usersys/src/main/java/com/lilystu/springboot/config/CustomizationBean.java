package com.lilystu.springboot.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * TOMCAT 配置类
 */
//@Component
//配置文件，配置的比较全
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory servlet) {
        servlet.setPort(10001);
    }
}
