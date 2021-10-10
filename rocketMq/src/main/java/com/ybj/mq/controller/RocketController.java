package com.ybj.mq.controller;

import com.ybj.mq.util.MQEnvIsolationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Api(value = "mq接口")
@RestController
public class RocketController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @ApiOperation(value = "发送mq")
    @GetMapping("/rocket/send")
    public String rocketSend() {
        GenericMessage<String> mqMessage = new GenericMessage<>("这是从生产者中发出的消息");
        SendResult sendResult = rocketMQTemplate.syncSend(MQEnvIsolationUtils.assembleDestination("rocket-topic-2"), mqMessage, 7000);
        return sendResult.toString();
    }


    // 延时消息，RocketMQ支持这几个级别的延时消息，不能自定义时长
    // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
    @GetMapping("/rocket/delayMsg/send")
    public String rocketDelayMsgSend() {
        LocalDateTime currentTime = LocalDateTime.now();
        rocketMQTemplate.syncSend("rocket-topic-2:tag-2", MessageBuilder.withPayload(currentTime.toString()).build(), 2000, 3);
        return currentTime.toString();
    }
}
