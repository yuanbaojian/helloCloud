package com.ybj.llm.util;

import com.google.common.util.concurrent.RateLimiter;

public class TokenBucketRateLimiter {

    // 每秒10个令牌
    private final RateLimiter rateLimiter = RateLimiter.create(1);


    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter token =  new TokenBucketRateLimiter();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            boolean acquire = token.tryAcquire();
            System.out.println("acquire = " + acquire);
        }
    }
}