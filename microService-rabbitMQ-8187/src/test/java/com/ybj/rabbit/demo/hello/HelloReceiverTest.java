package com.ybj.rabbit.demo.hello;

import com.ybj.rabbit.demo.hello.HelloReceiver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HelloReceiverTest {

   @Autowired
   HelloReceiver receiver;

    @Test
    void process() {
        System.out.println(" 接受信息 " );
        receiver.process("hello");
    }
}