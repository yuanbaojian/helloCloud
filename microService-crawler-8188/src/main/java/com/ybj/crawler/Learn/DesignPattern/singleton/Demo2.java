package com.ybj.crawler.Learn.DesignPattern.singleton;

/**
* <p>懒汉模式
 * 需要用才实例化
 * 但是线程不安全</p>
 * @author yuanbaojian
 * @date 2020/5/11
 * @time 20:39
 */
public class Demo2 {

    private Demo2(){}

    private static Demo2 demo = null;

    public Demo2 getInstance(){
        if(demo == null){
            demo = new Demo2();
        }
        return demo;
    }
}
