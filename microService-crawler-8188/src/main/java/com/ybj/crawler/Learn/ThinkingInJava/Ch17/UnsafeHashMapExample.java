package com.ybj.crawler.Learn.ThinkingInJava.Ch17;

import java.util.HashMap;
import java.util.Map;

public class UnsafeHashMapExample {

    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, String> map = new HashMap<>();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, String.valueOf(i));
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        // 等待两个线程执行完毕
        thread1.join();
        thread2.join();

        // 输出map的大小
        System.out.println("Map size: " + map.size());
    }
}
