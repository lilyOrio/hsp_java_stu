package com.lilysti.lilyspringboot.config;


import com.lilysti.lilyspringboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lilysti.lilyspringboot")//指定扫描的包
public class LilyConfig {

    @Bean
    public Monster monster(){
        return new Monster();
    }
}
