package com.lily_stu.spring.aop.homework02;

public class MyCal implements Cal {
    @Override
    public int cal1(int n) {
        System.out.println(n);
        if (n == 1){
            return 1;
        }
        return cal1(n - 1) + n;
    }

    @Override
    public int cal2(int n) {
        System.out.println(n);
        if (n == 1){
            return 1;
        }
        return cal2(n - 1) * n;
    }


}
