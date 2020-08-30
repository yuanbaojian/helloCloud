package com.ybj.crawler.Learn.thread.safe;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ContainerNotSafeDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        for(int i = 1; i < 30; i++) {
            // executorService2.submit(()->
            // {
            //     list.add(UUID.randomUUID().toString().substring(0, 8));
            //     System.out.println(Thread.currentThread().getName() + " list = " + list.size());
            // });
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + " list = " + list.size());
            },String.valueOf(i)).start();

        }


    }


    /**
    * <p>
     *     java.lang.UnsupportedOperationException
    * </p>
     * @param
     * @return void
     * @author yuanbaojian
     * @date 2020/8/21
     * @time 18:14
     */
    @Test
    public void test(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        for (Integer integer : integers) {
            integers.add(integer + 1);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        List<String> list = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 300; i++) {
            executorService.submit(()->
            {
                String uuId = UUID.randomUUID().toString();
                list.add(uuId.substring(0, 8));
                System.out.println("list = " + list.size());
            });
        }
        Thread.sleep(10001);
    }
}
