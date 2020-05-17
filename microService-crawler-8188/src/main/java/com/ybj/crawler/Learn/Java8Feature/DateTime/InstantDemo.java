package com.ybj.crawler.Learn.Java8Feature.DateTime;

import org.testng.annotations.Test;

import java.time.Instant;

public class InstantDemo {

    @Test
    public void test(){
        Instant instant=Instant.now();
        System.out.println("instant = " + instant);
    }
}
