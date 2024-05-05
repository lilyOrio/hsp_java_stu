package com.lilystu.lilymybatis.sqlsession.config;

import java.util.List;

/**
 * 包含接口信息和接口方法信息
 */
public class MapperBean {
    private String interfaceName; //接口名
    private List<Function> list; //接口下所有方法

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MapperBean{" +
                "interfaceName='" + interfaceName + '\'' +
                ", list=" + list +
                '}';
    }
}
