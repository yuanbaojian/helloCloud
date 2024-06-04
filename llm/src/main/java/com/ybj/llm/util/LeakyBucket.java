package com.ybj.llm.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;  
import java.util.concurrent.TimeUnit;  
import java.util.concurrent.atomic.AtomicInteger;  
  
public class LeakyBucket {  
    private final AtomicInteger tokens = new AtomicInteger(0); // 桶中的令牌数  
    private final int bucketCapacity; // 桶的容量  
    private final int leakRatePerSecond; // 每秒漏出的令牌数  
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
  
    public LeakyBucket(int bucketCapacity, int leakRatePerSecond) {  
        this.bucketCapacity = bucketCapacity;  
        this.leakRatePerSecond = leakRatePerSecond;  
        startLeaking();  
    }  
  
    private void startLeaking() {  
        scheduler.scheduleAtFixedRate(() -> {  
            int currentTokens = tokens.get();  
            if (currentTokens > 0) {  
                tokens.decrementAndGet(); // 每秒漏出一个令牌  
            }  
        }, 1, 1, TimeUnit.SECONDS);  
    }  
  
    public synchronized boolean tryConsume() {  
        int currentTokens = tokens.get();  
        if (currentTokens < bucketCapacity) {  
            tokens.incrementAndGet(); // 尝试加入一个令牌到桶中  
            return true; // 允许请求通过  
        } else {  
            return false; // 桶已满，拒绝请求  
        }  
    }  
  
    public void shutdown() {  
        scheduler.shutdown();  
        try {  
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {  
                scheduler.shutdownNow();  
            }  
        } catch (InterruptedException e) {  
            scheduler.shutdownNow();  
            Thread.currentThread().interrupt();  
        }  
    }  
  
    public static void main(String[] args) throws InterruptedException {  
        LeakyBucket leakyBucket = new LeakyBucket(5, 1); // 容量为5，每秒漏出1个令牌  
  
        // 模拟请求  
        for (int i = 0; i < 10; i++) {  
            boolean allowed = leakyBucket.tryConsume();  
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "allowed" : "denied"));  
            Thread.sleep(500); // 模拟请求的间隔时间  
        }  
  
        leakyBucket.shutdown(); // 关闭调度器，释放资源  
    }  
}