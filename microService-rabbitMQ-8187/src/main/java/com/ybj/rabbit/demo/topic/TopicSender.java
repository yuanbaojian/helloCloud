package com.ybj.rabbit.demo.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TopicSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hello, time is " + LocalDateTime.now().toString();
        System.out.println("动态路由信息为 : " + context);
        this.rabbitTemplate.convertAndSend("topics","user.save","user.save 动态路由消息");
    }

}