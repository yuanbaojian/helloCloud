package com.ybj.llm.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ybj.llm.service.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author : yuanbaojian
 * @date : 2024/5/21 16:54
 */
@RestController
public class TestController {

    @Autowired
    SseService sseService;

    // 写个简单Post 接口
    @GetMapping("/test")
    public String testPost(String name) {
        return "Hello, " + name;
    }

    @GetMapping("/testForParam")
    @SentinelResource(value = "/testForParam-hotKey")
    public String testForParam(String name) {
        return "Hello, " + name;
    }



}

