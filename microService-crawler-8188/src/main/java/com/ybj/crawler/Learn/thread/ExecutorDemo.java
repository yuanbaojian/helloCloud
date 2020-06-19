package com.ybj.crawler.Learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ExecutorDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 200; i++) {
            service.execute(() -> {
                System.out.println("线程名为----" + Thread.currentThread().getName());
            });
        }
        service.shutdown();
    }

}
