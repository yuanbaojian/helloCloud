package com.ybj.crawler.annotation;

import java.util.concurrent.TimeUnit;

public @interface AvoidRepeatableSubmit {

    /**
     * 防止重复提交时间
     *
     * @return
     */
    long timeout() default 2L;

    /**
     * 防止重复提交时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 过滤的参数
     *
     * @return
     */
    String[] excludeParams() default {};

}

