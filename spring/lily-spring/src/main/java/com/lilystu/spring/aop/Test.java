package com.lilystu.spring.aop;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
/**
 * 4. 老韩解读
 * 1) AOP 底层是基于BeanPostProcessor 机制的.
 * 2) 即在Bean 创建好后，根据是否需要AOP 处理，决定返回代理对象，还是原生Bean
 * 3) 在返回代理对象时，就可以根据要代理的类和方法来返回
 * 4) 其实这个机制并不难，本质就是在BeanPostProcessor 机制+ 动态代理技术
 * 5) 下面我们就准备自己来实现AOP 机制, 这样小伙伴们就不在觉得AOP 神秘，通透很多了.
 */
public class Test {
}
