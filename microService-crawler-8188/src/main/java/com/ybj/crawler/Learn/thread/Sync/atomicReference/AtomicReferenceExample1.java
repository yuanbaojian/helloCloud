package com.ybj.crawler.Learn.thread.Sync.atomicReference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 只使用volatile，不能保证线程安全
 * **/
public class AtomicReferenceExample1 {

    static volatile DebitCard debitCard = new DebitCard("ybj",0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    DebitCard dc = debitCard;

                    DebitCard newDC = new DebitCard(dc.getAccount(),dc.getAmount() + 10);
                    System.out.println("newDC = " + newDC);
                    debitCard = newDC;

                    try{
                        Thread.sleep(20);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
