package com.example.demo;

import com.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * MongoDBTest
 * zhangyd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class RedisTest {

    private final RedisTemplate redisTemplate;

    RedisTest(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    public void test() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.publish("zhangyd".getBytes(),"hello world".getBytes());
        redisTemplate.convertAndSend("zhangyd","hello world");
    }

}
