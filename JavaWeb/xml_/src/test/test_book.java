package test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class test_book {

    @Test
    public void test() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/test/book.xml"));
        List<Element> books = document.getRootElement().elements("book");
        for (Element book : books) {
            String id = book.attributeValue("id");
            String name = book.element("name").getText();
            String author = book.element("author").getText();
            float price = Float.parseFloat(book.element("price").getText());
            BookBean bookBean = new BookBean(id, name, author, price);
            System.out.println(bookBean);
        }
    }
}
