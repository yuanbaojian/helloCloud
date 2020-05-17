package com.ybj.crawler.Learn.thread.Sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock对象 显示的加锁解锁
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LockDemo implements Runnable {

    private int count = 10;
    private Object o = new Object();
    Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        LockDemo syncDemo1 = new LockDemo();
        for(int i = 0; i < 10000 ; i++) {
            new Thread(syncDemo1, "-线程").start();
        }
    }

    public void test1(){

    }

    @Override
    public  void run() {
        lock.lock();
        count++;
        System.out.println(Thread.currentThread().getName() + "  count ="  + count);
        lock.unlock();
    }
}
