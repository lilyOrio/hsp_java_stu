package com.lily_stu.spring.component;

import org.springframework.stereotype.Component;

/**
 *用来标识该类是一个组件，是一个通用标识
 *  在默认情况下类名首字母小写会作为对象的id 也可以在注释后面自定义id
 */
@Component(value = "lilyMyComponent")
public class MyComponent {
}
