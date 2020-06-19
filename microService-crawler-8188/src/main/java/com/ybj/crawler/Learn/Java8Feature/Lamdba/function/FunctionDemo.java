package com.ybj.crawler.Learn.Java8Feature.Lamdba.function;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @Author FunctionDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class FunctionDemo {
    @Test
    public void test(){
        Function function = new Function<String, Integer>() {
            @Override
            public Integer apply(String param) {
                return param.length();
            }
        };
        Integer hello = (Integer) function.apply("hello");
        System.out.println("hello = " + hello);

    }
}
