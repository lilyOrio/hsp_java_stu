package com.lilystu.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author lily
 * @version 1.0
 */
public class LilyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(10);
        System.out.println("LilyHttpSessionListener 监听到："+
                httpSessionEvent.getSession()+"创建。。。");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("LilyHttpSessionListener 监听到："+
                httpSessionEvent+"销毁。。。");
    }
}
