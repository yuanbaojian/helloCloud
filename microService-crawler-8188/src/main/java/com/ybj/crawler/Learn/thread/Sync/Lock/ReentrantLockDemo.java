package com.ybj.crawler.Learn.thread.Sync.Lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ReentrantLockDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ReentrantLockDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //非公平锁
        Lock nonFairLock = new ReentrantLock();
        //公平锁
        Lock fairLock = new ReentrantLock(true);

        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();


        // 可重入锁测试（Synchronized）
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                reentrantLockDemo.syncMethod1();
            }
        });executorService.submit(new Runnable() {
            @Override
            public void run() {
                reentrantLockDemo.syncMethod2();
            }
        });
    }

    @Test
    public void test(){
        ReentrantLock reentrantLock = new ReentrantLock(false);
        for (int i = 0; i < 5; i++) {
            try {
                reentrantLock.lock();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("reentrantLock = " + reentrantLock);

    }





    private synchronized void syncMethod1() {
        System.out.println(Thread.currentThread().getName() + " syncMethod1  " );
        syncMethod2();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syncMethod2() {
        System.out.println(Thread.currentThread().getName() + " syncMethod2 " );
    }
}
