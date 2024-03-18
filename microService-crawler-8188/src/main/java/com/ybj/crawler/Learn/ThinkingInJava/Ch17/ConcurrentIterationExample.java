package com.ybj.crawler.Learn.ThinkingInJava.Ch17;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentIterationExample {

    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, String> map = new HashMap<>();


        // 添加元素到HashMap
        for (int i = 0; i < 1000; i++) {
            map.put(i, String.valueOf(i));
        }




    }
}
