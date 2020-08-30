package com.ybj.crawler.Learn.thread.safe;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * @Author DeadLockDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

class HoldLock implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + " 自己持有 + "+ lockA + " 尝试获得 "+ lockB);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + " 自己持有 + "+ lockB + " 尝试获得 "+ lockA);

            }
        }
    }
}


public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "A";
        String lockB = "B";


        // 会产生死锁
        new Thread(new HoldLock(lockA, lockB),"Thread1").start();
        new Thread(new HoldLock(lockB, lockA),"Thread2").start();
    }
}
