package com.ybj.rabbit.demo.publishSubscribe;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublishSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hello 发布/订阅, time is " + LocalDateTime.now().toString();
        System.out.println("PublishSender : " + context);
        this.rabbitTemplate.convertAndSend("publishSubscribe","", context);
    }

}