package com.chen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class CreateDemo {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        //建立链接
        jedis = new Jedis("192.168.201.129", 6379);
        //设置密码
        jedis.auth("123456");
        //选择库
        jedis.select(0);
    }

    @Test
    void testString() {
        //插入数据
        String result = jedis.set("name", "张三");
        System.out.println("result: " + result);
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    void testHash() {
        Long count = jedis.hset("heima:user:1", "id", "1");
        System.out.println("count: " + count);

        String result = jedis.hget("heima:user:1", "name");
        System.out.println("result: " + result);

        System.out.println("tmphashtable:name" + jedis.hget("tmphashtable", "name"));
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}