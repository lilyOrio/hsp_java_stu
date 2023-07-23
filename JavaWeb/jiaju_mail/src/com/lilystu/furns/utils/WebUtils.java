package com.lilystu.furns.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lily
 * @version 1.0
 */
public class WebUtils {
    public static String FURN_IMG_DIRECTORY = "assets/images/product-image";
    public static Boolean isAjaxRequest(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
