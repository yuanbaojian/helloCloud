package com.ybj.crawler.Learn.ThinkingInJava.Ch18FILE;

import org.testng.annotations.Test;

public class GetBytes {

    @Test
    public void getBytes(){
        String name="abc";

        byte[] bytes = name.getBytes();
        for (byte aByte : bytes) {
          System.out.println("aByte = " + aByte);
        }
    }

}
