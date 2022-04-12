package com.ybj.crawler.Learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ThreadLocalDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ThreadLocalDemo {

    String localVariable = "hello";

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(()->{
                String variable = threadLocalDemo.localVariable;
                variable += finalI;
                System.out.println(Thread.currentThread().getName() + "   " + variable);
            });
        }
    }
}
