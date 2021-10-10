package com.ybj.redPacket.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Slf4j
@SpringBootTest
class RedPacketControllerTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void getNumberByDistributed() {
        // String redPacketNumber = stringRedisTemplate.opsForValue().get("redPacketNumber");
        // log.info("数量为{}",redPacketNumber);

        String vauleId = UUID.randomUUID().toString();
        String redPacket = "redPacket";
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(redPacket, vauleId, 60, TimeUnit.SECONDS);
        log.info("数据设置成功 {}", aBoolean);
        Boolean aBoolean1 = stringRedisTemplate.opsForValue().setIfAbsent(redPacket, vauleId, 60, TimeUnit.SECONDS);
        log.info("数据设置成功 {}", aBoolean1);
    }

    @Test
    public void ZsetTest(){
        ZSetOperations<String, String> setOperations = stringRedisTemplate.opsForZSet();
        setOperations.add("key1","value1",1);
        setOperations.add("key1","value2",3);
        setOperations.add("key1","value3",3.1);
        setOperations.add("key1","value4",2.9);
        setOperations.add("key-email","value5",2);
        Set<String> range = setOperations.range("key-email", 1, 8);
        //统计key对应的数量
        setOperations.zCard("key1");
    }
}