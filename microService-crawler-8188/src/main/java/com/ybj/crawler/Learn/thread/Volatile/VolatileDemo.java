package com.ybj.crawler.Learn.thread.Volatile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class MyData{

    //如果不加volatile修饰，那么会一致在while中遍历
    public volatile   int number = 0;

    public void add1(){
        number = 60;
    }
    public synchronized void add2(){
        number++;
    }

}

public class VolatileDemo {
    

    public static void main(String[] args) {
        // visibility();
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyData myData = new MyData();
        for(int i = 0; i < 20; i++) {
            executorService.submit(()->{
                for(int j = 0; j < 100; j++) {
                    myData.add2();
                }
            });
        }
        /*
        默认两个线程，一个main，一个gc
        * */
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        //正确值是 20*1000 = 20000 ; 但每次myData.number的值都不定， 永远比20000小
        System.out.println( Thread.currentThread().getName()  + " finally value is " + myData.number);

    }


    /**
    * <p>
     *     1. 测试可见性
     *
     *     volatile保证可见性，通知其他线程，主存中的变量已经修改
    * </p>
     * @param
     * @return void
     * @author yuanbaojian
     * @date 2020/8/24
     * @time 16:06
     */
    public static void visibility() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyData myData = new MyData();
        executorService.submit(()->{
           System.out.println( Thread.currentThread().getName()  + " come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add1();
            System.out.println( Thread.currentThread().getName()  + " number changed");
        });
        // main线程中的判断, 如果主存中的number刷新，但是main线程的工作内存没有刷新， 那么会一致死循环下去
        while (myData.number == 0){
        }
        System.out.println( Thread.currentThread().getName()  + " mission completed");
    }
}
