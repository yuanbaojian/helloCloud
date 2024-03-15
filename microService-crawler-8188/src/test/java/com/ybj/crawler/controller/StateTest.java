package com.ybj.crawler.controller;

import com.ybj.crawler.Learn.DesignPattern.state.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class StateTest {

    @Autowired
    OrderService orderService;


    @Test
    public  void test() {
        Thread.currentThread().setName("主线程");
        orderService.create();
        orderService.create();
        // orderService.pay(1);
        // new Thread("客户线程") {
        //     @Override
        //     public void run() {
        //         orderService.deliver(1);
        //         orderService.receive(1);
        //     }
        // }.start();
        //
        // orderService.pay(2);
        // orderService.deliver(2);
        // orderService.receive(2);

        orderService.pay(1);
        orderService.deliver(1);
        orderService.receive(1);

        System.out.println("全部订单状态：" + orderService.getOrders());
    }


}