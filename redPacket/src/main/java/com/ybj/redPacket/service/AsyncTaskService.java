package com.ybj.redPacket.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @description
 * @create 2017-02-23 上午12:00
 * @email gxz04220427@163.com
 */
@Slf4j
@Service
public class AsyncTaskService {

    /**
    * <p> 没有返回结果， 这个就不用那么麻烦， 不需要等结束再往下执行吧？？</p>
     * @param i
     * @return void
     * @author yuanbaojian
     * @date 2020/6/10
     * @time 14:13
     */
    @Async
    public void executeAsyncTask(int i) throws InterruptedException {
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }

    @Async
    public CompletableFuture<String> executeAsyncTaskWithResult(String param) throws InterruptedException {
        Thread.sleep(1000);
        log.info("线程" + Thread.currentThread().getName() + " 执行异步任务：" + param);
        return CompletableFuture.completedFuture(param);
    }

    @SneakyThrows
    public void normal(String param) {
        Thread.sleep(100);
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + param);
    }

    @Async
    public void executeAsyncTaskInner() {
        try {
            for (int i = 0; i < 500; i++) {
                this.executeAsyncTask(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
    * <p>虽然不返回参数，但是使用引用返回</p>
     * @param
     * @param stringList
     * @param i
     * @return void
     * @author yuanbaojian
     * @date 2020/6/10
     * @time 13:17
     */
    @Async
    public void executeAsyncTaskWithRef(List<String> stringList, int i) throws InterruptedException {
        Thread.sleep(500);
        stringList.add(String.valueOf(i));
        log.info("已加入 " + i);
    }

    @Async
    public int executeAsyncTaskWithResult2(String valueOf) {
        return Integer.parseInt(valueOf);
    }
}