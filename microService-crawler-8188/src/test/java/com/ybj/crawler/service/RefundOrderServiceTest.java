package com.ybj.crawler.service;

import com.netflix.discovery.converters.Auto;
import com.ybj.crawler.entity.RefundOrder;
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
class RefundOrderServiceTest {

    @Autowired
    RefundOrderService refundOrderService;

    @Test
    public void test(){
        RefundOrder byId = refundOrderService.getByIdThroughCache("30000062813182825445420851200");
        System.out.println("byId = " + byId);
    }

}