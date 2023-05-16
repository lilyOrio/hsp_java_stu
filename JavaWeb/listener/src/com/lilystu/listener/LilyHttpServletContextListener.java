package com.lilystu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author lily
 * @version 1.0
 * 1.当web应用启动时，就会产生ServletContextEvent事件，会调用监听器对应事件处理方法
 * contextInitialized，同时传递事件对象
 * 2.tomcat怎么知道这个监听器LilyHttpServletContextListener的存在？
 * 因为我们需要再web.xml文件配置
 * <listener>
 * <listener-class>com.lilystu.listener.LilyHttpServletContextListener</listener-class>
 * </listener>
 */
public class LilyHttpServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println("LilyHttpServletContextListener 监听到：" +
                servletContext + "创建。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println("LilyHttpServletContextListener 监听到：" +
                servletContext + "销毁。。。");
    }
}
