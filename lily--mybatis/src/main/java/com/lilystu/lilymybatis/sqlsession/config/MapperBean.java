package com.lilystu.lilymybatis.sqlsession.config;

import java.util.List;

/**
 * 包含接口信息和接口方法信息
 */
public class MapperBean {
    private String interfaceName; //接口名
    private List<Function> list; //接口下所有方法
}
