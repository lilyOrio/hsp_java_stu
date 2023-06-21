package com.lilystu.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class DataUtils {

    //将方法，封装到静态方法，方便使用
    // <T>声明泛型
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            //map==>request.getParameterMap
            //底层使用反射，表单提交的name属性值要与Bean的属性名一致
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    //将字符串转成整数,否则返回默认值
    public static int parseInt(String val, int defaultVal) {

        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println(val + " 格式不正确...");
        }
        return defaultVal;
    }


    //将字符串转成BigDecimal,否则返回默认值
    public static BigDecimal parseBigDecimal(String val, BigDecimal defaultVal) {

        try {
            return new BigDecimal(val);
        } catch (NumberFormatException e) {
            System.out.println(val + " 格式不正确...");
        }
        return defaultVal;
    }
}
