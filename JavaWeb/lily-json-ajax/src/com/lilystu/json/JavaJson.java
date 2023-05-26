package com.lilystu.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaJson {
    public static void main(String[] args) {
        //一、演示javabean 和 json 字符串的转换
        //创建gson对象，作为一个工具对象使用
        Gson gson = new Gson();
        Book book1 = new Book(1, "三体");
        //引入Gson jar包
        //1.演示吧javabean ==》 json字符串
        String strBook1 = gson.toJson(book1);
        System.out.println(strBook1);

        //Book.class 指定将json字符串转换成Book对象
        //底层使用了反射机制
        Book jsonStrToBook = gson.fromJson(strBook1, Book.class);
        System.out.println(jsonStrToBook);

        //演示List对象和json字符串的转换
        List<Book> books = new ArrayList<>();
        books.add(new Book(2,"三国演义"));
        books.add(new Book(3,"红楼梦"));
        String booksStr = gson.toJson(books);//底层遍历、拼接（按json格式）、返回字符串
        System.out.println(booksStr);
//        json字符串 ==> list  需要提供一个类TypeToken
        /*1.去掉{}会报错，提示：'TypeToken()' has protected access in 'com.google.gson.reflect.TypeToken'
            因为TypeToken的无参构造器是protected，直接调用会报错
          2.为什么加了一个{}就可以了呢？
            因为这里涉及到匿名内部类的知识，当我们这样使用的时候，这个类型已经不是TypeToken了，而是它的子类对象且这个对象是个匿名内部类，
            这个匿名内部类是有自己的（隐式的）无参构造器，当执行子类的无参构造器时，子类无参构造器中有一个默认的super()
         */
        /*
        *匿名内部类的基本语法：
            new 类/接口 (参数列表){//参数列表指定使用哪个构造器
                类体
            }
         */
        Type type = new TypeToken<List<Book>>(){}.getType();//？为什么要加{}==》匿名内部类，构造器执行过程
        System.out.println(type.getClass());//
        System.out.println(type);//返回类型的完整路径==》java.util.List<com.lilystu.json.Book>
        List<Book> books2 = gson.fromJson(booksStr,type);
        System.out.println(books2);

        //        json字符串 ==> Map

        Map<String, Book> bookHashMap = new HashMap<>();
        bookHashMap.put("key1",new Book(1,"西游记"));
        bookHashMap.put("key2",new Book(2,"水浒传"));

        String str = gson.toJson(bookHashMap);
        System.out.println(str);

        Map<String, Book> o = gson.fromJson(str, new TypeToken<Map<String, Book>>() {
        }.getType());
        System.out.println(o);

    }
}
