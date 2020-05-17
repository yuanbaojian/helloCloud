package com.ybj.crawler.Learn.thread;


/**
 * 继承Thread， 重写run方法
 * 可以用start  也可以用run
 * start() 可以启动一个新线程，run()不能
 *
 * @author yuanbaojian
 * @date 2020/3/19
 * @time 22:26
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        //给Thread类的name赋值
        super(name);
    }

    @Override
    public void run() {
        System.out.println("MyThread - START " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread - END "+Thread.currentThread().getName());
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static void main(String[] args) {
        MyThread test = new MyThread("ybj");
        test.start();
    }

}