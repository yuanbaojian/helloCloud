package com.ybj.crawler.Learn.Java8Feature.functionInterface.logDemo;

public class Demo3Runnable {
    public static void main(String[] args) {
        startThread(() -> System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName()));
    }
    /*
    * Runnable就是一个函数式接口，只有一个抽象方法
    * */
    static void startThread(Runnable runnable){
        new Thread(runnable).start();
    }
}
