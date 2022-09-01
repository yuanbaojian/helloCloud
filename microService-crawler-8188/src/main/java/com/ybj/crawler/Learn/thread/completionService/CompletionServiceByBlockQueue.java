package com.ybj.crawler.Learn.thread.completionService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceByBlockQueue {
    static ExecutorService executor = Executors.newCachedThreadPool();

    // 定义CompletionService使用ExecutorService
    static CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

    // 定义同样的任务
    static final List<Callable<Integer>> tasks = Arrays.asList(
            () ->
            {
                sleep(3);
                System.out.println("Task 30 completed done.");
                return 3;
            },
            () ->
            {
                sleep(1);
                System.out.println("Task 10 completed done.");
                return 1;
            },
            () ->
            {
                sleep(2);
                System.out.println("Task 20 completed done.");
                return 2;
            }
    );

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

    public static void main(String[] args) {
        // 提交所有异步任务
        tasks.forEach(completionService::submit);
        for (int i = 0; i < tasks.size(); i++)
        {
            try
            {
                // 从completionService中获取已完成的Future，take方法会阻塞
                System.out.println(completionService.take().get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }



}
