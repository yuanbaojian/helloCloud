package com.ybj.crawler.Learn.thread.Sync.atomicReference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author AtomicReferenceExample1
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class AtomicReferenceExample2 {

    static volatile DebitCard debitCard = new DebitCard("ybj",0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //加锁保证共享数据的安全性
                    synchronized (debitCard.getClass()){
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
                }
            });
        }
    }
}
