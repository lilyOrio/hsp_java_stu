package com.lilystu.lilymybatis.sqlsession;

import com.lilystu.lilymybatis.sqlsession.config.Function;
import com.lilystu.lilymybatis.sqlsession.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 读取与解析配置信息，并返回处理后的Environment
 */
public class LilyConfiguration {
    private static final ClassLoader loader = ClassLoader.getSystemClassLoader();

    public Connection build(String resource) {
        try {
            InputStream stream = loader.getResourceAsStream(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(stream);
            Element rootElement = document.getRootElement();
            return evalDataSource(rootElement);
        } catch (DocumentException e) {
            throw new RuntimeException("error occured while evaling xml " +
                    resource);
        }
    }

    private Connection evalDataSource(Element rootElement) {
        if (!"database".equals(rootElement.getName())) {
            throw new RuntimeException("root should be <database>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;

        //获取属性节点
        for (Object item : rootElement.elements("property")) {
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("[database]: <property> should contain name and value");
            }
            //赋值
            switch (name) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                default:
                    throw new RuntimeException("[database]: <property> unknown name");
            }
        }

        Connection connection = null;
        try {
            Class.forName(driverClassName);
            //建立数据库链接
            assert url != null;
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //获取property 属性的值,如果有value 值,则读取没有设置value,则读取内容
    private String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    public MapperBean readMapper(String path){
        MapperBean mapperBean = new MapperBean();
        try {
            InputStream inputStream = loader.getResourceAsStream(path);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            //把mapper 节点的nameSpace 值存为接口名
            mapperBean.setInterfaceName(rootElement.attributeValue("nameSpace").trim());
            //用来存储方法的List
            List<Function> list = new ArrayList<Function>();
            for (Iterator rootIter = rootElement.elementIterator();rootIter.hasNext();){
                Function fun = new Function(); //用来存储一条方法的信息
                Element e = (Element) rootIter.next();
                String sqltype = e.getName().trim();
                String funcName = e.attributeValue("id").trim();
                String sql = e.getText().trim();
                String resultType = e.attributeValue("resultType").trim();
                fun.setSqltype(sqltype);
                fun.setFuncName(funcName);
                Object newInstance = null;
                try {
                    newInstance = Class.forName(resultType).newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException instantiationException) {
                    instantiationException.printStackTrace();
                }
                fun.setResultType(newInstance);
                fun.setSql(sql);
                list.add(fun);
            }
            mapperBean.setList(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapperBean;
    }
}
