package com.ybj.rabbit.demo.routing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RoutingSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hello, time is " + LocalDateTime.now().toString();
        System.out.println("路由信息为 : " + context);
        this.rabbitTemplate.convertAndSend("directs","info","发送key为info的路由信息");
    }

}