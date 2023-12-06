package com.lily_stu.spring.tx;

import com.lily_stu.spring.tx.dao.GoodsDao;
import com.lily_stu.spring.tx.service.GoodsService;
import com.lily_stu.spring.tx.service.MultiplyTxService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxTest {
    /**
     * 查询商品的价格
     */
    @Test
    public void queryPriceByIdTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        System.out.println("查询价格");
        GoodsDao dao = ioc.getBean(GoodsDao.class);
        Float price = dao.queryPriceById(1);
        System.out.println(" 1 号商品的价格= " + price);
    }

    /**
     * 修改用户余额(购买商品后)
     */
    @Test
    public void updateBalanceTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao bean = ioc.getBean(GoodsDao.class);
        bean.updateBalance(1, 30.5f);
        System.out.println("====修改余额成功====");
    }

    /**
     * 测试修改商品的库存量
     */
    @Test
    public void updateAmountTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsDao bean = ioc.getBean(GoodsDao.class);
        bean.updateAmount(1, 2);
        System.out.println("====修改商品库存量成功====");
    }

    /**
     * 测试购买商品(使用了声明式事务)
     */
    @Test
    public void buyGoodsByTxTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsService bean = ioc.getBean(GoodsService.class);
        //使用buyGoodsByTx()
        bean.buyGoodsByTx(1, 2, 1);
        System.out.println("====购买商品成功====");
    }

    /**
     * 测试购买商品(使用了声明式事务， 测试事务的传播机制)
     */
    @Test
    public void buyGoodsByMulTxTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        MultiplyTxService bean = ioc.getBean(MultiplyTxService.class);
        bean.multiTxTest();
        System.out.println("------ok--------");
    }

    /**
     * 测试购买商品(使用了声明式事务， 测试事务隔离级别)
     */
    @Test
    public void buyGoodsByTxISOLATIONTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsService bean = ioc.getBean(GoodsService.class);
        bean.buyGoodsByTxISOLATION(1, 1, 1);
        System.out.println("------ok--------");
    }

    /**
     * 测试购买商品(使用了声明式事务， 测试事务超时回滚)
     */
    @Test
    public void buyGoodsByTxTimeoutTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");
        GoodsService bean = ioc.getBean(GoodsService.class);
        bean.buyGoodsByTxTimeout(1, 1, 1);
        System.out.println("------ok--------");
    }
}
