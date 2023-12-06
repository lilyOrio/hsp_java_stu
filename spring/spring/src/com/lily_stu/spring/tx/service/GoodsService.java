package com.lily_stu.spring.tx.service;

import com.lily_stu.spring.tx.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    /**
     * 购买商品(使用spring 的声明式事务)
     * 默认事务是传播属性:REQUIRED
     * Propagation.REQUIRES_NEW:表示事物之间独立，事件二失败了不会影响事件一
     * @param user_id
     * @param goods_id
     * @param num
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyGoodsByTx(int user_id,int goods_id,int num){
        //查询价格
        Float price = goodsDao.queryPriceById(goods_id);
        //购买商品，更新用户余额
        goodsDao.updateBalance(user_id,price*num);
        //更新库存
        goodsDao.updateAmount(goods_id,num);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyGoodsByTx02(int user_id,int goods_id,int num){
        //查询价格
        Float price = goodsDao.queryPriceById02(goods_id);
        //购买商品，更新用户余额
        goodsDao.updateBalance02(user_id,price*num);
        //更新库存
        goodsDao.updateAmount02(goods_id,num);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW/* ,isolation = Isolation.READ_COMMITTED*/)
    public void buyGoodsByTxISOLATION(int user_id, int goods_id, int num) {
        //查询到商品价格
        Float goods_price = goodsDao.queryPriceById(goods_id);
        System.out.println("第一次读取的价格= " + goods_price);
        //测试一下隔离级别，在同一个事务中，查询一下价格
        goods_price = goodsDao.queryPriceById(goods_id);
        System.out.println("第二次读取的价格= " + goods_price);
    }

    @Transactional(timeout = 2)
    public void buyGoodsByTxTimeout(int user_id, int goods_id, int num) {
        //查询到商品价格
        Float goods_price = goodsDao.queryPriceById02(goods_id);
        //购买商品，减去余额
        goodsDao.updateBalance02(user_id, goods_price * num);
        System.out.println("====超时start====");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====超时end====");
        //更新库存
        goodsDao.updateAmount02(goods_id, num);
    }
}
