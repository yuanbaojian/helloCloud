package com.ybj.crawler.Learn.thread.errors;

import java.util.concurrent.*;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        // 设置未捕获异常处理器
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Uncaught Exception in thread '" + t.getName() + "': " + e.getMessage());
            }
        });
    }

    // ... 其他必要的方法实现 ...
}

class ThreadPoolExceptionHandlingDemo {
    public static void main(String[] args) {
        ExecutorService executorService = new CustomThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Test Exception");
            }
        });

        executorService.shutdown();
    }
}
