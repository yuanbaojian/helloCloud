package com.ybj.redPacket.controller;

import com.ybj.crawler.utils.NetWorkUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

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
                    String pageContent = NetWorkUtils.getPageContent("http://localhost:8188/userRedPacket/grapRedPacket?redPacketId=1&userId=11", "GET");
                    System.out.println( LocalDateTime.now()+  "  " + finalJ + " 次抢红包 " +pageContent);
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
