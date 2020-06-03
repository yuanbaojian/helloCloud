package com.ybj.rabbit.demo.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloReceiver {


    @RabbitHandler
    public void process(String message) {
        System.out.println("RabbitListener 中接收到的 message = " + message);
    }

}