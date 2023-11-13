package com.lily_stu.spring.depinjection;

import org.springframework.stereotype.Repository;

@Repository
public class BookDAO extends BaseDAO<Book>{
    @Override
    public void hi() {
        System.out.println("BookDAO hi~");
    }
}
