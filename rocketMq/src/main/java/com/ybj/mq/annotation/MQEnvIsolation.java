package com.ybj.mq.annotation;

import java.lang.annotation.*;


/**
 * 消息隔离注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MQEnvIsolation {

}
