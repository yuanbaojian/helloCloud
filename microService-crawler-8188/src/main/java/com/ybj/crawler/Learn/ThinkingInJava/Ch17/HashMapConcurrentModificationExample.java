package com.ybj.crawler.Learn.ThinkingInJava.Ch17;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class HashMapConcurrentModificationExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        // 初始化 HashMap
        for (int i = 0; i < 10; i++) {
            map.put(i, "value" + i);
        }

        new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                map.put(i, "value" + i);
                try {
                    Thread.sleep(10); // 模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                for (Integer key : map.keySet()) {
                    System.out.println(key + " => " + map.get(key));
                    Thread.sleep(10); // 模拟耗时操作
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Detected concurrent modification");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
