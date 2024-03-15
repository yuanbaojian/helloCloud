package com.ybj.crawler.pulsar;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.stereotype.Service;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class PulsarProducer {
    private Producer<String> producer;

    @PostConstruct
    public void init() throws Exception {
        log.info(">>> pulsar init");
        // 创建 Pulsar 客户端
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        // 创建消息生产者
        producer = client.newProducer(Schema.STRING)
                .topic("persistent://public/default/my-topic")
                .create();
        log.info(">>> pulsar init end");
    }

    public void send(String message) throws Exception {

        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        // 创建消息生产者
        producer = client.newProducer(Schema.STRING)
                .topic("persistent://public/default/my-topic")
                .create();
        // 发送消息
        MessageId send = producer.send(message);
        log.info(">>> pulsar send message result : " + JSONUtil.toJsonStr(send));
    }

    @PreDestroy
    public void close() throws Exception {
        // 关闭消息生产者
        producer.close();
    }
}
