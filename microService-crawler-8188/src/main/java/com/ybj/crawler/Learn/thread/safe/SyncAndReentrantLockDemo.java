package com.ybj.crawler.Learn.thread.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SyncAndReentrantLockDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
class ShareResource{
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    private void print(){
        lock.lock();
        try{
            //判断
            while (number !=1 ){
                c1.await();
            }
            //通知
            for(int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + number);
            }
            number = 2;
            c2.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {


}
