package com.lilystu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @Author: lily
 * @Version: 1.0
 */
public class Jedis_ {

    //连接redis
    //注意：密码验证，远程连接bind，防火墙
    @Test
    public void con() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        String s = jedis.ping();
        System.out.println("连接成功 ping 返回结果=" + s);
        jedis.close();
    }

    @Test
    public void key() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.ttl("k2"));
        System.out.println(jedis.get("k3"));
        jedis.close();
    }

    @Test
    public void string() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        jedis.mset("k1", "s1","k2", "s2","k3", "s3");

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.ttl("k2"));
        System.out.println(jedis.mget("k3","k1"));
        jedis.close();
    }

    @Test
    public void list() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        jedis.lpush("list1", "s1", "s2", "s3");

        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println(list1);
        jedis.close();
    }

    @Test
    public void set() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        jedis.sadd("set1", "good", "morning", "!");
        jedis.sadd("set1", "happy", "apple", "!");

        Set<String> set1 = jedis.smembers("set1");
        System.out.println(set1);
        jedis.close();
    }

    @Test
    public void hash() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        jedis.hset("hash1", "name", "李白");
        jedis.hset("hash1", "age", "30");
        List<String> name = jedis.hmget("hash1", "name","age");
        System.out.println(name);
        jedis.close();
    }

    @Test
    public void zset() {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.auth("foobared");
        jedis.zadd("hero", 1, "关羽");
        jedis.zadd("hero", 2, "张飞");
        jedis.zadd("hero", 3, "赵云");
        jedis.zadd("hero", 4, "马超");
        jedis.zadd("hero", 5, "黄忠");
        Set<String> name = jedis.zrange("hero", 0,-1);
        System.out.println(name);
        jedis.close();
    }

}
