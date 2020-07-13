package com.ybj.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* <p>AOP注解类
 * 打印方法的入参和描述
 * </p>
 * @return
 * @author yuanbaojian
 * @date 2020/7/4
 * @time 8:33
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetParamLog {

    String description() default  "";
}
