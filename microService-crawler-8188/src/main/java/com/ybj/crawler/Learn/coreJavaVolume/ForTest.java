package com.ybj.crawler.Learn.coreJavaVolume;

import org.testng.annotations.Test;

public class ForTest {

  /** 尽量用整形*/
  @Test
  public void test() {
        for (double x=0; x!=10; x+=0.1){
      System.out.println("x = " + x);
        }
    }

}
