package com.lilysti.springboot.config;

import com.lilysti.springboot.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.@Configuration 标识一个配置类，需要放在SpringBoot扫描包下
 * 2.会给该类的对象会被注入到容器中
 * 3.充当Spring的配置文件
 * 4.在该类中可以使用@Bean注入其它组件
 */
@Configuration
public class Config {

    /**
     * @Bean 会向容器中注入一个Dog对象，该对象的id默认是方法名
     */
    @Bean
    public Dog dog(){
        return new Dog();
    }
}
