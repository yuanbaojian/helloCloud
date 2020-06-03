package com.ybj.rabbit.demo.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoutingReceiver {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, // 临时队列
                    exchange = @Exchange(value = "publishSubscribe", type = "fanout")
            )
    })
    public void process(String message) {
        System.out.println("RabbitListener1 中接收到的 订阅message = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "publishSubscribe", type = "fanout")
            )
    })
    public void process2(String message) {
        System.out.println("RabbitListener2 中接收到的 订阅message = " + message);
    }

}