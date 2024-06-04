package com.ybj.llm.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ybj.llm.service.SseService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;


/**
 * @author : yuanbaojian
 * @date : 2024/5/21 16:54
 */
@RestController
public class ChatController {

    @Autowired
    SseService sseService;



    @GetMapping("/stream")
    public SseEmitter stream() {
        final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onError( (e)->{
            System.out.println("e = " + e);
        });
        emitter.onCompletion( ()->{
            System.out.println(">>> onCompletion");
        });

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    // 模拟数据流，每秒发送一次数据
                    Thread.sleep(1000);
                    emitter.send(SseEmitter.event().data("New message at " + LocalDateTime.now()));
                }
                emitter.complete();

            } catch (IOException e) {
                // 如果发生IOException，说明客户端已经断开连接
                emitter.complete();
            } catch (InterruptedException e) {
                // 线程中断异常，通常不需要特殊处理
                Thread.currentThread().interrupt();
            }
        }).start();

        return emitter;
    }


    @GetMapping(value = "/http", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String http() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    @GetMapping(value = "/sseBySimpleHttp", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sseBySimpleHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> {
            System.out.println(">>> onCompletion");
        });
        emitter.onError(e -> {
            System.out.println("e = " + e);
        });
        emitter.onTimeout(() -> {
            System.out.println(">>> onTimeout");
        });

        new Thread(() -> {
            Request request = new Request.Builder()
                    .url("https://www.baidu.com/")
                    .build();
            Response response = null;
            try {
                response = okHttpClient.newCall(request).execute();
                assert response.body() != null;
                InputStream inputStream = response.body().byteStream();
                // 其实这个逻辑就是一次性把所有数据拿到，再依次进行数据返回
                while (true) {
                    int read = inputStream.read();
                    if (read == -1) {
                        break;
                    }
                    emitter.send(String.valueOf((char) read));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
        return emitter;
    }


    @GetMapping(value = "/sseByEventSource", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sseByEventSource() {
        OkHttpClient okHttpClient = new OkHttpClient();

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> {
            System.out.println(">>> onCompletion");
        });
        emitter.onError(e -> {
            System.out.println("e = " + e);
        });
        emitter.onTimeout(() -> {
            System.out.println(">>> onTimeout");
        });

        //定义request
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
//        EventSource eventSource = new RealEventSource.Builder(okHttpClient, request);
        return emitter;
    }
}

