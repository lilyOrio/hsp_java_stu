package com.lilystu.lilyspringmvc.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XMLPaser {
    public static String getbasePackage(String file) {
        try {
            SAXReader saxReader = new SAXReader();
            InputStream resourceAsStream =
                    XMLPaser.class.getClassLoader().getResourceAsStream(file);
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            Element componentScan = rootElement.element("component-scan");
            Attribute attribute = componentScan.attribute("base-package");
            String basePackage = attribute.getText();
            return basePackage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
