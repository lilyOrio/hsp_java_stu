package com.lilysti.lilyspringboot;

import com.lilysti.lilyspringboot.config.LilyConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 容器初始化器
 *
 * 1.此类相当于Spring容器
 * 2.此类会 加载/关联Spring容器的配置--使用注解方式
 * 3.完成Spring容器配置的bean的创建，依赖注入
 * 4.创建前端控制器DispatcherServlet，并让其持有容器
 * 5.当前端控制器持有容器，就可以进行分发请求，SpringMVC机制
 * 6.此类的方法onStartup是由Tomcat调用，并传入ServletContext
 */
public class LilyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("onStartup......");
        //加载Spring web application configuration，相当于一个容器
        AnnotationConfigWebApplicationContext ac =
                new AnnotationConfigWebApplicationContext();
        ac.register(LilyConfig.class);//将配置类注册到容器中，该配置类会指定需要扫描的包
        ac.refresh();//完成bean的创建和配置

        //创建前端控制器，并持有容器，以便进行请求的映射分发
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        //将前端控制器添加到servletContext上下文，以便被tomcat使用
        //返回一个registration
        ServletRegistration.Dynamic registration =
                servletContext.addServlet("app", dispatcherServlet);
        //当tomcat启动时，加载dispatcherServlet，即开机自启动
        registration.setLoadOnStartup(1);
        //设置 拦截请求映射 url-pattern
        //   / 和 /* ==》 区别：/会覆盖tomcat默认的DefaultServlet，当其他url匹配不上时会调用，而/*不会覆盖
        registration.addMapping("/");
    }
}
