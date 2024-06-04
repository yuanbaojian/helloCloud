package com.ybj.llm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author : yuanbaojian
 * @date : 2024/5/23 19:28
 */

@Service
public class SseService {


    public SseEmitter getSseEmitter() {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.onCompletion(() -> {
            System.out.println("onCompletion");
        });
        sseEmitter.onError( (e)->{
            System.out.println("e = " + e);
        });
        return sseEmitter;
    }

}
