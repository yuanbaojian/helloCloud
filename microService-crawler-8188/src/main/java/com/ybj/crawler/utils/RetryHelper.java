package com.ybj.crawler.utils;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;
import com.ybj.crawler.config.LoggingRetryListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * 重试工具类
 * @author lp211200227
 */
@Slf4j
public class RetryHelper {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class RetryResult<T> {

        private boolean invokeSuccess;

        private String errorCode;
        private String errorMsg;

        private T result;


    }

    /**
     * 对于guava#retry的简单封装
     *
     * @param supplier  需要重试的func
     * @param predicate 判定【是否要】重试条件的谓词 ， Predicate#不通过#false表明不需要重试，代表业务结果满意; 否则相反
     * @param <T>       业务对象
     * @return 结果
     */
    public static <T> RetryResult<T> executeWithRetry(Supplier<T> supplier,
                                                      Predicate<T> predicate,
                                                      Predicate<Throwable> excpetionPredicate,
                                                      Integer intervalSeconds,
                                                      Integer maxAttempts
                                                      ) {

        Retryer<T> retryer = RetryerBuilder
                .<T>newBuilder()
                .retryIfException(excpetionPredicate)
                .retryIfResult(predicate)
                .withWaitStrategy(WaitStrategies.fixedWait(intervalSeconds, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(maxAttempts))
                .withRetryListener(new LoggingRetryListener())
                .build();

        try {

            T result = retryer.call(supplier::get);

            RetryResult response = new RetryResult();
            response.setInvokeSuccess(Boolean.TRUE);
            response.setResult(result);

            return response;

        } catch (RetryException e) {

            log.error("call RetryHelper find fail : ", e);

            RetryResult response = new RetryResult();
            response.setInvokeSuccess(Boolean.FALSE);
            response.setErrorMsg(e.getMessage());
            return response;

        } catch (Exception e) {

            log.error("call RetryHelper find exception : ", e);

            RetryResult response = new RetryResult();
            response.setInvokeSuccess(Boolean.FALSE);

            return response;

        }
    }

    public static void main(String[] args) {
        RetryResult<String> stringRetryResult = RetryHelper.executeWithRetry(() ->
                        error2("")
                , Objects::isNull,
                Exception.class::isInstance,
                1, 2);
        log.info("stringRetryResult:{}", stringRetryResult.getResult());

    }

    public static int error(int a, int b) {
        log.info("执行方法");
        return a/b;
    }

    public static String error2(String a) {
        log.info("执行方法 error2");
        return a.substring(1);
    }

}
