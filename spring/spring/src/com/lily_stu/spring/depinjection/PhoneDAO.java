package com.lily_stu.spring.depinjection;

import org.springframework.stereotype.Repository;

@Repository
public class PhoneDAO extends BaseDAO<Phone>{
    @Override
    public void hi() {
        System.out.println("PhoneDAO hi~");
    }
}
