package com.ybj.crawler.Learn.ThinkingInJava.Ch19_Enum.replaceIf;

import org.testng.annotations.Test;

/**
 * @Author Calculate
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Calculate {

    @Test
    public void test(){
        int result = OperatorEnum.valueOf("ADD").apply(1, 3);
        System.out.println("result = " + result);
    }

}
