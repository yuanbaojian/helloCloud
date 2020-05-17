package com.ybj.crawler.Learn.ThinkingInJava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Annotation
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Annotation {

    @myAnnotation
    public void test(){

    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface myAnnotation{
    String name() default "hello";
}
