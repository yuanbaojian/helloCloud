package com.ybj.crawler.redis;

import org.redisson.RedissonRateLimiter;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonLock {

    @Autowired
    private RedissonClient redissonClient;

    private RRateLimiter rateLimiter;

    public void lock(String key) {
        rateLimiter = redissonClient.getRateLimiter(key);
        //  每秒生成 10个 token， 令牌桶容量为 1
        rateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.SECONDS);

        boolean b = rateLimiter.tryAcquire();

        redissonClient.getSemaphore("test").tryAcquire();
        RedissonRateLimiter rateLimiter = new RedissonRateLimiter(redissonClient);
    }

}
