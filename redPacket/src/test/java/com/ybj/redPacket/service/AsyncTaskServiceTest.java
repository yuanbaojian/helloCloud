package com.ybj.redPacket.service;

import com.ybj.redPacket.redPacket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest(classes = redPacket.class)
@RunWith(SpringRunner.class)
class AsyncTaskServiceTest {

    @Autowired
    AsyncTaskService asyncTaskService;

    @Test
    public void contextLoads() {
    }


    @Test
    void executeAsyncTaskWithResult() throws ExecutionException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        for(int i = 0; i < 5000; i++) {
            Future<String> stringFuture = asyncTaskService.executeAsyncTaskWithResult(String.valueOf(i));
            while(true){
                if(stringFuture.isDone()){
                    String result = stringFuture.get();
                    log.info("result 为 " + result);
                    break;
                }
            }
        }
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("duration.toMillis() = " + duration.toMillis());
    }


    /**
    * <p>多线程， 必须debug才能计算出
     * 8个线程大概要44毫秒</p>
     * @param 
     * @return void
     * @author yuanbaojian 
     * @date 2020/6/9
     * @time 16:17
     */
    @Test
    public void executeAsyncTask() {
        LocalDateTime start = LocalDateTime.now();
        // for(int i = 0; i < 1000; i++) {
            try {
                asyncTaskService.executeAsyncTask(1);
                asyncTaskService.executeAsyncTask(1);
                asyncTaskService.executeAsyncTask(1);
                asyncTaskService.executeAsyncTask(1);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.warn("thread failed " + 1);
            }
        // }
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("duration.toMillis() = " + duration.toMillis());
    }

  public static void main(String[] args) {
      AsyncTaskService asyncTaskService1 = new AsyncTaskService();
      LocalDateTime start = LocalDateTime.now();
      for(int i = 0; i < 1000; i++) {
          try {
              asyncTaskService1.executeAsyncTask(i);
          } catch (InterruptedException e) {
              log.warn("thread failed " + i);
          }
      }
      LocalDateTime end = LocalDateTime.now();
      Duration duration = Duration.between(start, end);
      System.out.println("duration.toMillis() = " + duration.toMillis());
  }

    /**
    * <p> 单线程 main，走完需要136毫秒</p>
     * @param 
     * @return void
     * @author yuanbaojian 
     * @date 2020/6/9
     * @time 16:13
     */
    @Test
    void normal() {
        LocalDateTime start = LocalDateTime.now();
        for(int i = 0; i < 5000; i++) {
            asyncTaskService.normal(String.valueOf(i));
        }
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("duration.toMillis() = " + duration.toMillis());
    }

    @Test
    public void test2(){
        Map map = new HashMap();
        System.out.println(map);
    }
}