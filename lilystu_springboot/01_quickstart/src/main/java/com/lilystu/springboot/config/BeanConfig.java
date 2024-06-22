package com.lilystu.springboot.config;

import com.lilystu.springboot.bean.Cat;
import com.lilystu.springboot.bean.Dog;
import com.lilystu.springboot.bean.Monster;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

//@Configuration 标识这是一个配置类: 等价配置文件
//当一个类被@Configuration标识 这个类也会被注入容器
//配置类可以有多个, 就和Spring 可以有多个ioc 配置文件是一个道理.但是方法名不能一样，因为bean的id默认就是方法名
/**
 * 1. proxyBeanMethods：代理bean 的方法
 * (1) Full(proxyBeanMethods = true)、【保证每个@Bean 方法被调用多少次返回的组件都是单实例的, 是代理方式】--默认
 * (2) Lite(proxyBeanMethods = false)【每个@Bean 方法被调用多少次返回的组件都是新创建的, 是非代理方式】
 * (3) 特别说明: proxyBeanMethods 是在调用@Bean 方法才生效，因此，需要先获取BeanConfig 组件，再调用方法
 * 而不是直接通过SpringBoot 主程序得到的容器来获取bean, 注意观察直接通过ioc.getBean() 获取Bean, proxyBeanMethods 值并没有生效
 * (4) 如何选择: 组件依赖必须使用Full 模式默认。如果不需要组件依赖使用Lite 模
 * (5) Lite 模也称为轻量级模式，因为不检测依赖关系，运行速度快
 */
@Configuration/*(proxyBeanMethods = false)*/
@Import({Dog.class, Cat.class})//意@Import 方式注入的组件, 默认组件的名字就是全类名
//@ConditionalOnBean(name = "monster_nmw")
public class BeanConfig {

    /**
     * 1. @Bean : 给容器中添加组件
     * 2. monster01() : 默认方法名作为组件的id
     * 3. Monster: 返回类型就是组件类型, 返回的值就是new Monster(100, "牛魔王", 500,"芭蕉扇")
     * 4. @Bean("monster_nmw"): 重新指定组件的id = “monster_nmw”，默认id是方法名
     * 5. 配置类里面使用@Bean 标注在方法上给容器注册组件，默认是单实例的
     */
    @Bean/*("monster_nmw")*///一定要写上，不然这个bean不会被注入到容器
//    @Scope("prototype")//多例模式
    public Monster monster01(){
        return new Monster(100, "牛魔王", 500, "芭蕉扇");
    }

    /**
     * @Conditional 应用实例
     * 1. 要求: 演示在SpringBoot, 如何通过@ConditionalOnBean 来注入组件
     * 2. 只有在容器中有name = monster_nmw 组件时，才注入dog01
     *
     * * 3. @ConditionalOnBean(name = "monster_nmw") 也可以放在配置类名处，
     * * 则表示对该配置类中所有要注入
     * * 的组件都进行条件约束
     */
    @Bean
//    @ConditionalOnBean(name = "monster_nmw")
    public Dog dog01() {
        return new Dog();
    }
}
