package com.ybj.crawler.Learn.thread.Sync.Lock;

import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 *
 **/

class Mycahe{

    private volatile Map<String, String> map = new ConcurrentHashMap<>();

    //可重入的读写锁
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
    * <p>
     *     写操作
     *     要保证：
     *     1.原子性 2.独占性
    * </p>
     * @param key
     * @param value
     * @return void
     * @author yuanbaojian
     * @date 2020/8/21
     * @time 11:46
     */
    public void put(String key, String value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入 " + value );
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成 " );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantReadWriteLock.writeLock().unlock();
    }

    /**
    * <p>读操作</p>
     * @param key
     * @return void
     * @author yuanbaojian
     * @date 2020/8/21
     * @time 12:49
     */
    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取 " );
            TimeUnit.SECONDS.sleep(1);
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成 " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantReadWriteLock.readLock().unlock();

    }
}
public class ReadLockDemo {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Mycahe mycahe = new Mycahe();
        for(int i = 0; i < 11; i++) {
            String finalI = String.valueOf(i);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    mycahe.put(finalI, finalI);
                }
            });
        }
        for(int i = 0; i < 11; i++) {
            String finalI = String.valueOf(i);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    mycahe.get(finalI);
                }
            });
        }
    }
}
