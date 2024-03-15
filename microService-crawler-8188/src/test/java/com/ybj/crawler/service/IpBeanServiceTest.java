package com.ybj.crawler.service;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
class IpBeanServiceTest {

    @Autowired
    IpBeanService ipBeanService;

    @Test
    void testRetryable() throws Exception {
        Boolean aBoolean = ipBeanService.testRetryable();
        log.info("aBoolean = " + aBoolean);
    //     查询最近30天的sql
    }
}