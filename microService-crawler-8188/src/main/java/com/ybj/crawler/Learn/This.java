package com.ybj.crawler.Learn;

/**
 * @Description  this  指的是当前对象，  eg:this.id 取的是当前的id属性
 * @Date $ $
 * @Param $
 * @return $
 **/
public class This {

    public void test1(){
        System.out.println("this in test1 = " + this);
    }
    public void test2(){
        System.out.println("this in test2 = " + this);
    }


    public static void main(String[] args) {
        This test = new This();
        test.test1();
        test.test2();
    }
}
