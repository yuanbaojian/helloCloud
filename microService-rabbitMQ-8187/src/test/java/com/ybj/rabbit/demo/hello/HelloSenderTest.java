package com.ybj.rabbit.demo.hello;

import com.ybj.rabbit.demo.hello.HelloSender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HelloSenderTest {

    @Autowired
    HelloSender helloSender;

    @Test
    void send() {
        System.out.println(" start to send message ");
        helloSender.send();
    }
}
