package com.lilystu.springboot.config;

import com.lilystu.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new LoginInterceptor())
                //指定拦截规则
                .addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns("/","/login","/image/**");// /image/** 放行图片资源，不然会影响界面显示
                                                                //无需在image前添加static包，因为在static下的静态资源可以直接访问
    }

}
