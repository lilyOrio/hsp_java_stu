package com.lilystu.lilymybatis.sqlsession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
