package com.ybj.crawler.controller;

import com.ybj.crawler.model.IpBean;
import com.ybj.crawler.service.IpBeanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IpBeanControllerTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IpBeanService ipBeanService;

    @Test
    void saveIpBeanToDB() {}

    @Test
    void getAllIp() {}

    @Test
    void getValidIp() {
        Set validIpSet = redisTemplate.opsForSet().members("validIpSet");
        System.out.println("validIpSet = " + validIpSet);
    }

    @Test
    public void test() throws IOException {
        List<IpBean> ipFromWeb = ipBeanService.getIpFromWeb();
        Long testIpList = redisTemplate.opsForList().leftPushAll("testIpList", ipFromWeb);
    }

    @Test
    public void test2(){
        List testIpList = redisTemplate.opsForList().range("testIpList", 1, 600);
        System.out.println("testIpList = " + testIpList);
    }
}