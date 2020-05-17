package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author LiftOff
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LiftOff implements  Runnable{

    protected  int countDown = 10;
    private static int taskCount = 0;
    private final int id =taskCount++;

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public LiftOff() {
    }

    public String status(){
        return String.format("# %s (%s)", id, countDown>0?countDown:"liftOff!");
    }

    @Override
    public void run() {
        while (countDown-- >0){
            System.out.println(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
        System.out.println("执行完毕-----------------------");


        //测试类不能启用多线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
            Thread.sleep(1000);
        }
        executorService.shutdown();
        System.out.println("全部执行完毕");
    }


    @Test
    public void test(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
