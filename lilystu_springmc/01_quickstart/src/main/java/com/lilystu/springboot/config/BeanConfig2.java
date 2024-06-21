package com.lilystu.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// @Configuration//标识这是一个配置类: 等价配置文件
@Configuration(proxyBeanMethods = false)
//导入beans.xml
@ImportResource(locations = {"classpath:beans.xml"})
public class BeanConfig2 {

}
