package com.ybj.crawler.Learn.thread.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author CycliBarrierDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CycliBarrierDemo {



    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println(" 全部结束 " ));
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 3; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 执行前 " );
                        //await()要放到最后面， 否则会阻塞
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
