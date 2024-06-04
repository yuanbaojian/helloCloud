package com.ybj.llm.util;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.BucketConfiguration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class FixedWindowRateLimiter {

//    private final Bucket4j bucket4j = Bucket4j.builder()
//            .addLimit(BucketConfiguration.builder()
//                    .addLimit(Bandwidth.simple(10, Duration.ofSeconds(60)))
//                    .build())
//            .build();
//
//    public boolean tryConsume(String key) {
//        return bucket4j.getBucket(key).tryConsume(1);
//    }
}