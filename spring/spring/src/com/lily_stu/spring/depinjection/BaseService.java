package com.lily_stu.spring.depinjection;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T> {
    @Autowired
    BaseDAO<T> baseDAO;

    public void hi(){
        baseDAO.hi();
    }
}
