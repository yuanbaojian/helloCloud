package com.ybj.crawler.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IpBeanControllerTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void saveIpBeanToDB() {}

    @Test
    void getAllIp() {}

    @Test
    void getValidIp() {
        Set validIpSet = redisTemplate.opsForSet().members("validIpSet");
        System.out.println("validIpSet = " + validIpSet);
    }
}