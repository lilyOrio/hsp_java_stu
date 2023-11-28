package com.lilystu.spring.component;

import com.lilystu.spring.annotation.AutoWire;
import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.annotation.Scope;
import com.lilystu.spring.proesse.InitializingBean;

@Component("bookService")
@Scope("prototype")
public class BookService implements InitializingBean {
    @AutoWire
    private BookDao mBookDao;
    public void ok(){
        System.out.print("BookService 调用 mBookDao.hi");
        mBookDao.hi();
    }

    public void init() {
        System.out.println("BookService init ...");
    }
}
