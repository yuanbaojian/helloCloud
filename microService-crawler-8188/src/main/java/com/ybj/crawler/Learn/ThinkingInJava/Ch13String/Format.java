package com.ybj.crawler.Learn.ThinkingInJava.Ch13String;

import org.testng.annotations.Test;

/**
 * @Author Format
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Format {

    @Test
    public void testFormat(){
        String name = "zhangsan";
        System.out.format("hello %s", name);
    }

    @Test
    public void testPrintf(){
        String name = "zhangsan";
        System.out.printf("hello %s", name);
    }

    @Test
    public void test(){
        String name = "张三";
        String format = String.format("hello %s", name);
        System.out.println("format = " + format);
    }
}
