package com.ybj.rabbit.demo.workQueue;

import com.ybj.rabbit.demo.hello.HelloSender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SenderTest {

    @Autowired
    Sender sender;

    @Test
    void send() {
        System.out.println(" start to send message ");
        for(int i = 0; i < 10; i++) {
            sender.send();
        }
    }
}