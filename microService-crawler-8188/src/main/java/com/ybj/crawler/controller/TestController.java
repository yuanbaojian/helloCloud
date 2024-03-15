package com.ybj.crawler.controller;

import com.ybj.api.model.JsonResult;
import com.ybj.crawler.service.IpBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Future;

/**
 * @Author TestController
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    IpBeanService ipBeanService;




    @GetMapping("/hello")
    public String test(){

        return "hello";
    }

    @GetMapping("/testWithResult")
    public JsonResult testWithResult(String redPacketId, String userId){
        LocalDateTime start = LocalDateTime.now();

        try {
            for(int i = 0; i < 5000; i++) {
                Future<String> stringFuture = ipBeanService.executeAsyncTaskWithResult(String.valueOf(i));
                String s = stringFuture.get();
                System.out.println("返回的结果是 " + s);
            }
        } catch (Exception e) {
            log.error("线程失败");
        }
        log.info("------------------线程执行完毕，开始计算执行时间---------------------");
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        String name = Thread.currentThread().getName();
        System.out.println("request 请求 线程名 = " + name);
        System.out.println("duration.toMillis() = " + duration.toMillis());
        log.info("------------------线程执行完毕，计算执行时间结束---------------------");
        return JsonResult.ok("抢红包成功");
    }

    @GetMapping("/retryable")
    public JsonResult testRetryable() throws Exception {;
        return JsonResult.ok(ipBeanService.testRetryable());
    }
}
