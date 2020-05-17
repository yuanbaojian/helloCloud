package com.ybj.crawler.Learn.CleanCode.Ch7;

import org.testng.annotations.Test;

/**
 * @Author NotReturnNull
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class NotReturnNull {

    public static int addition(int number1,int number2){
        return number1+number2;
    }

    @Test
    public void testReturnNull(){
        if(getResult()!=null){
            System.out.println("不为空");
        } else{
            System.out.println("为空");
        }
    }

    public static Integer getResult(){
        return null;
    }
}
