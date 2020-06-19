package com.ybj.redPacket.controller;


import com.ybj.api.model.JsonResult;
import com.ybj.redPacket.service.AsyncTaskService;
import com.ybj.redPacket.service.UserRedPacketServiceI;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Function;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-06-08
 */
@Slf4j
@RestController
@RequestMapping("/userRedPacket")
public class UserRedPacketController {

    int number = 0;

    @Autowired
    UserRedPacketServiceI userRedPacketService;

    @Autowired
    AsyncTaskService asyncTaskService;

    @GetMapping("/grapRedPacket")
    public JsonResult grapRedPacket(String redPacketId, String userId){
        int result = userRedPacketService.grapRedPacketByPessimisticLock(redPacketId, userId);
        if(result == 0){
            return  JsonResult.fail("抢红包失败");
        } else{
            return JsonResult.ok("抢红包成功");
        }
    }

    @GetMapping("/test")
    public JsonResult test(String redPacketId, String userId){
        String name = Thread.currentThread().getName();
        System.out.println("线程名 = " + name);
        return JsonResult.ok("抢红包成功");
    }

    @GetMapping("/testWithOutResult")
    public JsonResult test1(String redPacketId, String userId){
        LocalDateTime start = LocalDateTime.now();
        try {
            for(int i = 0; i < 500; i++) {
                asyncTaskService.executeAsyncTask(i);
            }
        } catch (InterruptedException e) {
            log.error("线程失败");
        }

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        String name = Thread.currentThread().getName();
        System.out.println("request 请求 线程名 = " + name);
        System.out.println("duration.toMillis() = " + duration.toMillis());
        return JsonResult.ok("抢红包成功");
    }

    @GetMapping("/testWithOutResult")
    public void test12(String redPacketId, String userId){
        LocalDateTime start = LocalDateTime.now();
        try {
            for(int i = 0; i < 500; i++) {
                asyncTaskService.executeAsyncTask(i);
            }
        } catch (InterruptedException e) {
            log.error("线程失败");
        }

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        String name = Thread.currentThread().getName();
        System.out.println("request 请求 线程名 = " + name);
        System.out.println("duration.toMillis() = " + duration.toMillis());
    }


    @GetMapping("/testInner")
    public JsonResult testInner(String redPacketId, String userId){
        LocalDateTime start = LocalDateTime.now();
        asyncTaskService.executeAsyncTaskInner();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        String name = Thread.currentThread().getName();
        System.out.println("request 请求 线程名 = " + name);
        System.out.println("duration.toMillis() = " + duration.toMillis());
        return JsonResult.ok("抢红包成功");
    }

    @GetMapping("/testWithRef")
    public JsonResult testWithRef(String redPacketId, String userId){
        LocalDateTime start = LocalDateTime.now();
        List<String> stringList = new LinkedList<>();
        for(int i = 0; i < 1000; i++) {
            try {
                asyncTaskService.executeAsyncTaskWithRef(stringList,i);
            } catch (InterruptedException e) {
                log.error("线程报错");
            }
        }
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        String name = Thread.currentThread().getName();
        System.out.println("request 请求 线程名 = " + name);
        System.out.println("duration.toMillis() = " + duration.toMillis());
        System.out.println("stringList = " + stringList.toString());
        return JsonResult.ok("抢红包成功");
    }

    @SneakyThrows
    @GetMapping("/testWithResult")
    public JsonResult testWithResult(String redPacketId, String userId){
        LocalDateTime start = LocalDateTime.now();
        List<Future<String>> futureList = new ArrayList<>();
        try {
            for(int i = 0; i < 500; i++) {
                CompletableFuture<String> stringFuture = asyncTaskService.executeAsyncTaskWithResult(String.valueOf(i));
                futureList.add(stringFuture);
            }
        } catch (Exception e) {
            log.error("线程失败");
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();

        log.info("------------------线程执行完毕，开始计算执行时间---------------------");
        for (Future<String> stringFuture : futureList) {
            System.out.println("stringFuture.get() = " + stringFuture.get());
        }
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        String name = Thread.currentThread().getName();
        System.out.println("request 请求 线程名 = " + name);
        System.out.println("duration.toMillis() = " + duration.toMillis());
        // System.out.println("stringList = " + futureList.toString());
        log.info("------------------线程执行完毕，计算执行时间结束---------------------");
        return JsonResult.ok("抢红包成功");
    }


    /**
    * <p>进行测试， 测试多线程能不能直接返回对象</p>
     * @param redPacketId
     * @param userId
     * @return void
     * @author yuanbaojian
     * @date 2020/6/18
     * @time 15:25
     */
    @SneakyThrows
    @GetMapping("/testWithResult2")
    public void testWithResult2(String redPacketId, String userId){

        try {
            for(int i = 0; i < 500; i++) {
                int number = asyncTaskService.executeAsyncTaskWithResult2(String.valueOf(i));
                System.out.println("number = " + number);
            }
        } catch (Exception e) {
            log.error("线程失败");
        }

    }


}

