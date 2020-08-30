package com.ybj.crawler.Learn.ThinkingInJava.Ch3;

import io.swagger.models.auth.In;
import org.testng.annotations.Test;

public class CastingNumber {

    @Test
    public void test(){
        boolean result = 'a' == 97;
        System.out.println("result = " + result);
    }


    @Test
    public void casting(){
        double number=1.7;
        System.out.println("Math.round(number) = " + Math.round(number));
    }

    @Test
    public void MaxInt(){
        int number=Integer.MAX_VALUE;
        int big=number*4;
        System.out.println("big = " + big);
    }

    /*** 只跳出当前循环
     * @param
     * @return void
     * @author baojian.yuan
     * @date 2019/11/6
     */
    @Test
    public void breakFor(){
        int number1=10;
        int number2=10;
        for (int i = 0; i < number1; i++) {
            for(int j = 0; j < number2; j++) {
                if( j>=0 ){
                    break;
                }
            }
            System.out.println("i = " + i);
        }
    }

    @Test
    public void test3(){
        Integer integer = 33;
        Integer integer1 = 33;
        Integer integer2 = new Integer(33);
        System.out.println("integer1.equals(integer2) = " + integer1.equals(integer2));
        Class<? extends Integer> aClass = integer.getClass();
        Class<? extends Integer> aClass1 = integer1.getClass();
        System.out.println("aClass1.equals(aClass) = " + aClass1.equals(aClass));
    }

}
