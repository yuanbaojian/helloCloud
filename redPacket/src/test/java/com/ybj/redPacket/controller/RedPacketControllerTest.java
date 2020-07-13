package com.ybj.redPacket.controller;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
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
}