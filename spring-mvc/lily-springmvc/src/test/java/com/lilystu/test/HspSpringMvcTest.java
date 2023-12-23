package com.lilystu.test;

import com.lilystu.lilyspringmvc.xml.XMLPaser;
import org.junit.Test;

public class HspSpringMvcTest {
    @Test
    public void readXML(){
        String basePackage = XMLPaser.getbasePackage("lilyspringmvc.xml");
        System.out.println(basePackage);
    }
}
