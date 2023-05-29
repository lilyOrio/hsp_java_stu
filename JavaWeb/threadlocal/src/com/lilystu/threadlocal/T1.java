package com.lilystu.threadlocal;

public class T1 {
    //创建ThreadLocal对象
    public static ThreadLocal<Object> threadLocal1 = new ThreadLocal<>();
    public static void main(String[] args) {
//在一个线程中共享数据，并且这个数据是线程安全的
        /*1.在T1中起一个线程t0
          2.放一个数据
          3.在线程t0中调用其它（service）类的方法
          4.在service中再调用dao类的方法，同样还是在线程t0
          假定有一个dog对象，在这三个类中可以通过t0线程获取这个dog对象，且是同一个dog对象
         */
        new Thread(new Task()).start();//在主线程中起了一个新的线程
    }

    public static class Task implements Runnable {

        @Override
        public void run() {
            Dog dog = new Dog();
            Pig pig = new Pig();
//            给threadLocal中放入对象
            threadLocal1.set(dog);
            /*
            set方法中经历了什么呢？
            public void set(T value) {
                Thread t = Thread.currentThread();//获取当前线程，关联到当前线程
                ThreadLocalMap map = getMap(t);//通过当前线程对象获取ThreadLocalMap对象
                                               //ThreadLocalMap是ThreadLocal的静态内部类
                if (map != null)
                    map.set(this, value);//this表示当前threadLocal对象，value 表示需要共享的数据
                                         //如果同一个threadLocal对象再set一次则会，替换掉原来的value
                                         //总结：一个threadLocal对象只可以存放一个数据
                else
                    createMap(t, value);//没有map就创建一个，和当前线程t关联
    }
             */
            System.out.println("Task 在run 方法中 线程=" + Thread.currentThread().getName() +
                    ", dog = " + dog);
            new T1Service().update();
        }
    }
}
