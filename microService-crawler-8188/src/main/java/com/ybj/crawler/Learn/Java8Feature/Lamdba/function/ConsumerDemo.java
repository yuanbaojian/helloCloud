package com.ybj.crawler.Learn.Java8Feature.Lamdba.function;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @Author ConsumerDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ConsumerDemo {
    @Test
    public void test(){
        Consumer consumer = (Consumer<String>) s -> System.out.println("s = " + s);
        consumer.accept("hello world");
    }
}
