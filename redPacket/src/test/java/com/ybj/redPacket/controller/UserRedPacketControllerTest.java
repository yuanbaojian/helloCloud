package com.ybj.redPacket.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class UserRedPacketControllerTest {

    @Test
    void grapRedPacket() throws IOException {
        final int i=0;
        ExecutorService service= Executors.newFixedThreadPool(20000);
        for(int j = 0; j < 100000; j++) {
            int finalJ = j;
            service.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                }
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
