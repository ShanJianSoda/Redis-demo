package com.chen.springredispart;

import com.chen.springredispart.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringRedisPartApplicationTests {

    //手动配置
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name", "李四");
        System.out.println("name: " + redisTemplate.opsForValue().get("name"));
    }

    @Test
    void testUser() {
        User user = new User("chen", "123456");
        redisTemplate.opsForValue().set("user:1", user);
        redisTemplate.opsForValue().set("user:2", new User("HUI", "Ye"));
        System.out.println("user1: " + redisTemplate.opsForValue().get("user:1"));
        System.out.println("user2: " + redisTemplate.opsForValue().get("user:2"));
    }


    //使用stringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testStringTemplate() throws JsonProcessingException {
        User user = new User("Xing", "123456");
        // 手动序列化
        String jsonStr = mapper.writeValueAsString(user);
        // 写入
        stringRedisTemplate.opsForValue().set("user:3", jsonStr);
        // 查询
        String val = stringRedisTemplate.opsForValue().get("user:3");
        System.out.println("user:3 = " + val);
        //反序列化
        User readValue = mapper.readValue(val, User.class);
        System.out.println(readValue.toString());
    }
}
