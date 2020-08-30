package com.ybj.crawler.Learn.thread.GetResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author CompletableFutureDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    completableFuture.complete("hello");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            String result = completableFuture.get();
            System.out.println("completableFuture.get() = " + result );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
