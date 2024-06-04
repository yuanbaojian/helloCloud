package com.ybj.auth.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import okhttp3.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;

@RestController
public class SseController {

    @GetMapping(value = "/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getMessage(){
        SseEmitter sseEmitter = new SseEmitter();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate( ()->{
            try {
                sseEmitter.send(System.currentTimeMillis(), MediaType.TEXT_PLAIN);
            } catch (Exception e) {
                sseEmitter.completeWithError(e);
                executorService.shutdown();
            }

        },0,1,java.util.concurrent.TimeUnit.SECONDS);
        return  sseEmitter;
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter events() {
        SseEmitter emitter = new SseEmitter();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("see time = " + System.currentTimeMillis());
                emitter.send(System.currentTimeMillis() +"", MediaType.TEXT_PLAIN);
            } catch (IOException e) {
                emitter.completeWithError(e);
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);

        return emitter;
    }


    @GetMapping(value = "/sse-demo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter();

        // 创建 OkHttpClient 客户端
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        // 创建 HTTP 请求
        Request request = new Request.Builder()
                .url("YOUR_API_URL_HERE")
                .build();

        // 发送 HTTP 请求，并处理返回的事件流数据
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, Response response) {
                if (!response.isSuccessful()) {
                    emitter.completeWithError(new Exception("Unexpected response: " + response));
                    return;
                }
                EventSources.Builder eventSourceBuilder = new EventSources.Builder(new okhttp3.sse.EventSourceListener() {
                    @Override
                    public void onEvent(EventSources eventSource, String id, String type, String data) {
                        try {
                            emitter.send(data);
                        } catch (Exception e) {
                            emitter.completeWithError(e);
                        }
                    }

                    @Override
                    public void onFailure(EventSource eventSource, Throwable t, Response response) {
                        emitter.completeWithError(t);
                    }
                }, response.body().source());

                EventSource eventSource = eventSourceBuilder.build();
                emitter.onCompletion(eventSource::cancel);
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }

}
