package com.ybj.crawler.Learn.thread.completionService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceDemoInChat {
    public static void main(String[] args) {
        // 模拟长时间执行任务
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 100;
        });

        // 异步执行一个转换任务
        CompletableFuture<String> future2 = future1.thenApply(result -> {
            return "result is " + result;
        });

        // 组合两个异步操作的结果
        CompletableFuture<String> combineFuture = future2.thenCombine(CompletableFuture.supplyAsync(() -> "这是另外一个任务的结果"),
                (result1, result2) -> result1 + result2);


        try {
            String combineResult = combineFuture.get();
            System.out.println("combineResult = " + combineResult);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        // 模拟处理异常场景
        CompletableFuture<String> exceptionFuture = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("出现异常");
            }
            return "执行完毕";
        }).exceptionally(ex -> {
            return "发生异常情况" + ex;
        });

        try {
            String exceptionResult = exceptionFuture.get();
            System.out.println("exceptionResult = " + exceptionResult);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
