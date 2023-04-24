package com.lilystu.test;

public abstract class ParentTest {
    public abstract void hi();
    public void aa(){
        hi();
        this.bb();
    }
    public void bb(){
        System.out.println("bb~~~");
    }
}
