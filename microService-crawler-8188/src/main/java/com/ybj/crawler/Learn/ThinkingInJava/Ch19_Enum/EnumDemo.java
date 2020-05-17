package com.ybj.crawler.Learn.ThinkingInJava.Ch19_Enum;

import org.testng.annotations.Test;

/**
 * @Author EnumDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class EnumDemo {

    public enum test{
        yellow,green,blue
    }

    @Test
    public void test(){
        System.out.println(test.blue);
    }

}
