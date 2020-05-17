package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Atomic
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class AtomicIntegerTest implements Runnable{

    private AtomicInteger i =  new AtomicInteger(1);

    public int getValue(){
        return i.get();
    }

    private void evenIncrement(){
        i.addAndGet(2);
    }


    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 500);
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        executorService.execute(ait);
        while(true){
            int val = ait.getValue();
            if(val % 2 !=0){
                System.out.println("val = " + val);
                System.exit(0);
            }
        }
    }
}
