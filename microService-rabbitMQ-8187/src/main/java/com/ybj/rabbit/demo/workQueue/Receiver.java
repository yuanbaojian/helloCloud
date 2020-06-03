package com.ybj.rabbit.demo.workQueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queuesToDeclare = @Queue("workQueue"))
    public void process(String message) {
        System.out.println("RabbitListener1 中接收到的 message = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("workQueue"))
    public void process2(String message) {
        System.out.println("RabbitListener2 中接收到的 message = " + message);
    }

}