package com.lily_stu.spring.bean;

import java.util.List;

/**
 * @author lily
 * @version 1.0
 */
public class BookStore {
    private List<String> bookList;

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }
    public BookStore(){
    }
}
