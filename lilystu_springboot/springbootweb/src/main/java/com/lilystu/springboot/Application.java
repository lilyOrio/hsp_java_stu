package com.lilystu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc =
                SpringApplication.run(Application.class, args);
        /*
        private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
							new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
            访问这些文件夹下的静态文件：
            http://localhost:10001/1.jpeg
            注意事项：
            1.先尝试去匹配有没有对应的controller，再去匹配静态资源
            2.可以修改访问静态资源的前缀,可以避免和controller冲突: 配置文件设置spring.mvc.static-path-pattern=/lily/**
                如：http://localhost:10001/lily/1.jpeg
            3.改变静态资源路径

         */

    }
}
