package com.lilystu.springboot.config;

import com.lilystu.springboot.bean.Furn;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// @Configuration//标识这是一个配置类: 等价配置文件
@Configuration(proxyBeanMethods = false)
//导入beans.xml
@ImportResource(locations = {"classpath:beans.xml"})

//@EnableConfigurationProperties(Furn.class)解读
//1、开启Furn 配置绑定功能
//2、把Furn 组件自动注册到容器中
//3、需要把bean类上的@Component注释掉
//@EnableConfigurationProperties(Furn.class)
public class BeanConfig2 {

}
