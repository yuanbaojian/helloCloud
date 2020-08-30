package com.ybj.rabbit.service;

import com.rabbitmq.client.Channel;
import com.ybj.rabbit.config.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class RoutingReceiver2 {

    @RabbitListener(queues = Constants.topicQueue)
    public void processOrder(Map data, Channel channel , Message message) throws IOException {
        System.out.println("接收者中的data = " + data.toString());
        // 手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}