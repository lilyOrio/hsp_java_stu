package com.lilystu.springboot.config;

import com.lilystu.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration 标识这是一个配置类: 等价配置文件
@Configuration
public class BeanConfig {


    /**
     * 1. @Bean : 给容器中添加组件
     * 2. monster01() : 默认方法名作为组件的id
     * 3. Monster: 返回类型就是组件类型, 返回的值就是new Monster(100, "牛魔王", 500,"芭蕉扇")
     * 4. @Bean("monster_nmw"): 重新指定组件的id = “monster_nmw”
     * 5. 配置类里面使用@Bean 标注在方法上给容器注册组件，默认是单实例的
     */
    @Bean
    public Monster monster01(){
        return new Monster(100, "牛魔王", 500, "芭蕉扇");
    }
}
