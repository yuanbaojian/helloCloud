package com.ybj.crawler.Learn.thread.Sync.synchronizedDemo;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author StaticSynchronizedDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class StaticSynchronizedDemo {

    //静态方法
    public static void staticMethod1(){
        System.out.println("this is staticMethod1 " );
    }

    //静态 同步方法  (代码块)
    public static  synchronized  void staticSynchronizedMethod1() throws InterruptedException {
        System.out.println("this is staticSynchronizedMethod1  " );
        Thread.sleep(10000);
    }

    //静态 同步方法 （代码段）
    public static   void staticSynchronizedMethod2() throws InterruptedException {
        synchronized (StaticSynchronizedDemo.class){
            System.out.println("this is staticSynchronizedMethod  " );
            Thread.sleep(10000);
        }
    }

    public synchronized void synchronizedMethod1() throws InterruptedException {
        System.out.println("this is synchronizedMethod1 " );
        Thread.sleep(10000);
    }
    public synchronized void synchronizedMethod2() throws InterruptedException {
        System.out.println("this is synchronizedMethod2 " );
        Thread.sleep(10000);
    }

    public void test1() throws InterruptedException {
        System.out.println("this is test1 " );
        Thread.sleep(10000);
    }

    public void test2() throws InterruptedException {
        System.out.println("this is test2 " );
        Thread.sleep(10000);
    }

    // 主函数也是static
    public static void main(String[] args) throws InterruptedException {
        StaticSynchronizedDemo staticSynchronizedDemo1 = new StaticSynchronizedDemo();
        StaticSynchronizedDemo staticSynchronizedDemo2 = new StaticSynchronizedDemo();
        // StaticSynchronizedDemo.staticSynchronizedMethod1();
        // staticSynchronizedDemo.method();


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                staticSynchronizedDemo1.synchronizedMethod1();
            }
        });executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                staticSynchronizedDemo2.synchronizedMethod2();
            }
        });
    }
}
