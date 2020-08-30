package com.ybj.user.service;

import com.rabbitmq.client.Channel;
import com.sun.media.jfxmedia.logging.Logger;
import com.ybj.user.model.Message;
import com.ybj.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Slf4j
@Component
public class RoutingReceiver {
    int number = 0;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MessageServiceI messageService;

    @Autowired
    UserServiceI userService;

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, // 临时队列
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"redPacket"}
            )
    })
    public void process2(String message) {
        System.out.println("RabbitListener2 中接收到的 路由message = " + message);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("312ybj@Gmail.com");
        msg.setFrom("1793870688@qq.com");
        msg.setSubject("springboot test mail");
        msg.setText(message);
        javaMailSender.send(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "redPacketQueue", durable = "true"),
            exchange = @Exchange(value = "redPacket_exchange", ignoreDeclarationExceptions = "true"),
            key = "topic.redPacket")
    )
    public void processOrder(Map data, Channel channel , org.springframework.amqp.core.Message messageObject) throws IOException {
        System.out.println("接收端的data = " + data);
        String uuId = (String) data.get("uuId");
        String userId = (String) data.get("userId");
        String messageId = (String) data.get("messageId");
        Message message = messageService.getById(uuId);
        //防止数据库过慢
        if(message == null){
            for(int i = 0; i < 3; i++) {
                message = messageService.getById(uuId);
            }
        }
        //生产者回滚了，消费者手动确认
        if(message == null){
            channel.basicAck(messageObject.getMessageProperties().getDeliveryTag(),false);
        } else{
            // 如果数据过慢， 会导致消息发送到了，但是数据库没插入数据， 导致message为null
            if(message.getStatus() == 0){
                message.setStatus(1);
                User user = userService.getById(userId);
                if(user.getBalance() == null){
                    user.setBalance(10);
                } else{
                    user.setBalance( user.getBalance() + 10);
                }
                number++;
                log.info("number的值为{}",number);
                // 消费报错，进行重试
                while(number<4){
                    int num = 1 / 0;
                }
                boolean updateResult = userService.updateById(user);
                System.out.println("用户余额提示结果 = " + updateResult);
                messageService.updateById(message);
                //手动ack，保证消息的消费完成
                channel.basicAck(messageObject.getMessageProperties().getDeliveryTag(),false);
            }
        }

    }



    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "redPacketCompensationQueue", durable = "true"),
            exchange = @Exchange(value = "redPacket_exchange", ignoreDeclarationExceptions = "true"),
            key = "topic.redPacketCompensation")
    )
    public void Compensation(Map data, Channel channel , org.springframework.amqp.core.Message messageObject) throws IOException {
        System.out.println("接收端的data = " + data);
        String uuId = (String) data.get("uuId");
        String userId = (String) data.get("userId");
        Message message = messageService.getById(uuId);
        // 如果数据过慢， 会导致消息发送到了，但是数据库没插入数据， 导致message为null
        if(message.getStatus() == 0){
            message.setStatus(1);
            User user = userService.getById(userId);
            if(user.getBalance() == null){
                user.setBalance(10);
            } else{
                user.setBalance( user.getBalance() + 10);
            }
            number++;
            log.info("number的值为{}",number);
            // 消费报错，进行重试
            while(number<4){
                int num = 1 / 0;
            }
            boolean updateResult = userService.updateById(user);
            System.out.println("用户余额提示结果 = " + updateResult);
            messageService.updateById(message);
            //手动ack，保证消息的消费完成
            channel.basicAck(messageObject.getMessageProperties().getDeliveryTag(),false);
        }
    }

}