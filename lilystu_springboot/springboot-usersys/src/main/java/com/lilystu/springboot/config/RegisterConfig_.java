package com.lilystu.springboot.config;

import com.lilystu.springboot.servlet.Filter_;
import com.lilystu.springboot.servlet.Listener_;
import com.lilystu.springboot.servlet.Servlet_;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RegisterConfig_ {

    @Bean
    public ServletRegistrationBean servlet_(){
        return new ServletRegistrationBean(new Servlet_(),"/servlet01","/servlet02");
    }

    @Bean
    public FilterRegistrationBean Filter_(){
        Filter_ filter_ = new Filter_();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter_);
        registrationBean.setUrlPatterns(Arrays.asList("/css/*","/image/*"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean listener_(){
        return new ServletListenerRegistrationBean(new Listener_());
    }


}
