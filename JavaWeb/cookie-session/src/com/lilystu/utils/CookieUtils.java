package com.lilystu.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static Cookie getCookieByName(String name, Cookie[] cookies) {
        if (name == null || "".equals(name) || cookies == null || cookies.length == 0){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())){
                return cookie;
            }
        }
            return null;
    }

    public static Cookie updateCookie(String name,Cookie[] cookies,String value){
        Cookie cookie = getCookieByName(name, cookies);
        if (cookie != null){
            cookie.setValue(value);
            return cookie;
        }else {
            return null;
        }
    }
}
