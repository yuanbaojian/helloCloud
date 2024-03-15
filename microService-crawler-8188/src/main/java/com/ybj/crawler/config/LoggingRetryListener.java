package com.ybj.crawler.config;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingRetryListener implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        log.info("Retry attempt # {} successful, {}", attempt.getAttemptNumber(), attempt);

    }
}
