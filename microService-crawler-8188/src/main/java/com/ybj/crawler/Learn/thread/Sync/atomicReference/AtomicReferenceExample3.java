package com.ybj.crawler.Learn.thread.Sync.atomicReference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用AtomicReference实现非阻塞
 **/
public class AtomicReferenceExample3 {

    static AtomicReference<DebitCard> debitCardRef = new AtomicReference(new DebitCard("ybj", 0));

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                        DebitCard dc = debitCardRef.get();

                        DebitCard newDC = new DebitCard(dc.getAccount(),dc.getAmount() + 10);

                        if(debitCardRef.compareAndSet(dc, newDC)){
                            System.out.println(Thread.currentThread().getName() +" newDC = " + newDC);
                        }

                        // try{
                        //     Thread.sleep(20);
                        // }catch (Exception e){
                        //     e.printStackTrace();
                        // }
                    }
            });
        }
    }
}
