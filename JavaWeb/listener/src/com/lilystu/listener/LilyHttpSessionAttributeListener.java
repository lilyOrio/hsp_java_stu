package com.lilystu.listener;

import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author lily
 * @version 1.0
 */
public class LilyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("LilyHttpSessionAttributeListener 监听到" +
                httpSessionBindingEvent.getName() + " = " +
                httpSessionBindingEvent.getValue() + "属性添加");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("LilyHttpSessionAttributeListener 监听到" +
                httpSessionBindingEvent.getName() + " = " +
                httpSessionBindingEvent.getValue() + "属性删除");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("LilyHttpSessionAttributeListener 监听到" +
                httpSessionBindingEvent.getName() + " = " +
                httpSessionBindingEvent.getValue() + "属性修改");
    }

}
