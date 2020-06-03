package com.ybj.rabbit.demo.publishSubscribe;

import com.ybj.rabbit.demo.workQueue.Sender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PublishSenderTest {
    @Autowired
    PublishSender publishSender;

    @Test
    void send() {
        System.out.println(" start to send message ");
        publishSender.send();
    }
}