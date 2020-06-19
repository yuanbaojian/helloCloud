package com.ybj.rabbit.demo.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, // 临时队列
                    exchange = @Exchange(name = "topics", type = "topic"),
                    key = { "user.*"}
            )
    })
    public void process(String message) {
        System.out.println("RabbitListener1 动态路由消息 message = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, // 临时队列
                    exchange = @Exchange(name = "topics", type = "topic"),
                    key = {"#"}
            )
    })
    public void process2(String message) {
        System.out.println("RabbitListener2 动态路由消息 message = " + message);
    }

}