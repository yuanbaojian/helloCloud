package com.ybj.rabbit.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloPulsarController {
    @Autowired
    private PulsarProducer producer;
    @Autowired
    private PulsarConsumer consumer;

    @GetMapping("/pulsar/send")
    public String send() {
        try {
            // 发送消息
            producer.send("Hello, Pulsar!");
            return "Send message success.";
        } catch (Exception e) {
            log.error("Send message failed.", e);
            return "Send message failed.";
        }
    }
}
