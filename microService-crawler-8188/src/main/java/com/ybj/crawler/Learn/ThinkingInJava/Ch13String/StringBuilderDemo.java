package com.ybj.crawler.Learn.ThinkingInJava.Ch13String;

import org.testng.annotations.Test;

/**
 * @Author StringBuilderDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class StringBuilderDemo {

    @Test
    public void test(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello");
        stringBuilder.append("world");
        String string = stringBuilder.toString();
        System.out.println("string = " + string);
    }

}
