package com.ybj.rabbit.demo.workQueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hello, time is " + LocalDateTime.now().toString();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("workQueue", context);
    }

}