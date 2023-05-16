package com.lilystu.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * @author lily
 * @version 1.0
 */
public class LilyServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("LilyServletContextAttributeListener 监听到 添加：" +
                servletContextAttributeEvent.getName() + " = "
        + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("LilyServletContextAttributeListener 监听到 删除：" +
                servletContextAttributeEvent.getName() + " = "
                + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("LilyServletContextAttributeListener 监听到 修改：" +
                servletContextAttributeEvent.getName() + " = "
                + servletContextAttributeEvent.getValue());
    }
}
