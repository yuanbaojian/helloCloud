package com.ybj.crawler.Learn.thread.Sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Atom
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class AtomIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
        System.out.println("atomicInteger.incrementAndGet() = " + atomicInteger.incrementAndGet());

        boolean result1 = atomicInteger.compareAndSet(101, 12);
        boolean result2 = atomicInteger.weakCompareAndSet(101, 12);
        System.out.println("atomicInteger.get() = " + atomicInteger.get());

        atomicInteger.getAndIncrement();

        System.out.println("result1 = " + result1);
    }


}
