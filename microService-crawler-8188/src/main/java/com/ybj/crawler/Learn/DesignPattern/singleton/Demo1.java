package com.ybj.crawler.Learn.DesignPattern.singleton;

import org.junit.Test;

/**
* <p>饿汉模式
 * 上来就实例化</p>
 * @author yuanbaojian
 * @date 2020/5/11
 * @time 20:36
 */
public class Demo1 {

//  构造函数私有化，使得只能自身调用
    private Demo1(){

    }

    private static Demo1 demo1 = new Demo1();

    public static Demo1 getInstance(){
        return demo1;
    }

    @Test
    public void test(){
        Demo1 instance3 = new Demo1();
        Demo1 instance1 = Demo1.getInstance();
        Demo1 instance2 = Demo1.getInstance();
        System.out.println(instance1.equals(instance2));
    }

}
