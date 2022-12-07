package com.ybj.crawler.Learn.ThinkingInJava.IO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class SerializableDemoTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {
        stringRedisTemplate.opsForValue().set("hello","world");
        System.out.println("stringRedisTemplate = " + stringRedisTemplate);
        // SerializableDemo.User user = new SerializableDemo.User("张三",15,"earth");
        // redisTemplate.opsForValue().setIfAbsent("userDemo5",user);

        SerializableDemo.User userDemo = (SerializableDemo.User) redisTemplate.opsForValue().get("userDemo5");
        System.out.println("userDemo = " + userDemo);

        // String jsonStr = "{\"name\":\"张三\",\"age\":15,\"address\":\"earth\"}";
        //
        // // System.out.println("userDemo = " + userDemo);
        // SerializableDemo.User user2 = objectMapper.readValue(jsonStr, SerializableDemo.User.class);
        // System.out.println("user2 = " + user2);
        //我怎么弄个报错的啊123


    }

}