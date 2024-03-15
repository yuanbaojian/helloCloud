package com.ybj.crawler.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class PulsarConsumer implements MessageListener<String>
{
    private Consumer<String> consumer;

    @Override
    public void received(Consumer<String> consumer, Message<String> message)
    {


        try {
            // 创建 Pulsar
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();
            // 创建消息消费者
            consumer = client.newConsumer(Schema.STRING)
                    .topic("persistent://public/default/my-topic")
                    .subscriptionName("my-subscription")
                    .messageListener(this)
                    .subscribe();
            // 处理消息
            log.info(">>> pulsar received message: " + message.getValue());
            // 标记消息已被消费
            consumer.acknowledge(message);
        }
        catch (Exception e) {
            // 处理异常
            consumer.negativeAcknowledge(message);
        }
    }

    @PreDestroy
    public void close()
            throws Exception
    {
        // 关闭消息消费者
        consumer.close();
    }
}
