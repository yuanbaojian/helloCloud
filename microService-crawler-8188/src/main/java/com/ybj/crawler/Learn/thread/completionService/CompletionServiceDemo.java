package com.ybj.crawler.Learn.thread.completionService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceDemo {
    public static void main(String[] args)
    {
        batchTaskDefect();
    }
    private static void batchTaskDefect()
    {
        // 定义ExecutorService
        ExecutorService executor = Executors.newCachedThreadPool();
        // 定义批量异步任务，每个异步任务耗时不相等
        final List<Callable<Integer>> tasks = Arrays.asList(
                () ->
                {
                    // 模拟耗时30秒
                    sleep(3);
                    System.out.println("Task 3 completed done.");
                    return 3;
                },
                () ->
                {
                    // 模拟耗时10秒
                    sleep(1);
                    System.out.println("Task 1 completed done.");
                    return 1;
                },
                () ->
                {
                    // 模拟耗时20秒
                    sleep(2);
                    System.out.println("Task 2 completed done.");
                    return 2;
                }
        );
        try
        {
            // 批量提交执行异步任务，该方法会阻塞等待所有的Future返回
            List<Future<Integer>> futures =
                    executor.invokeAll(tasks);
            futures.forEach(future ->
            {
                try
                {
                    System.out.println(future.get());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    // 休眠方法
    private static void sleep(long seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e)
        {
            // ignore
        }
    }
}
