package com.itheima.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 04/02/2025 21:54
 * @Description :
 */
@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedis() {
        // save data to redis
        redisTemplate.opsForValue().set("name", "Tommy");
        // get data from redis
        String name = redisTemplate.opsForValue().get("name");

        System.out.println(name);
    }
}
