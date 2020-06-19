package com.ybj.crawler.Learn.Java8Feature.Lamdba.function;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

/**
 * @Author PredicateDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class PredicateDemo {

    @Test
    public void test(){
        Predicate predicate = new Predicate<String>() {
            @Override
            public boolean test(String  param) {
                return param.isEmpty();
            }
        };
        boolean isEmpty = predicate.test("hello");
        System.out.println("isEmpty = " + isEmpty);
    }
}
