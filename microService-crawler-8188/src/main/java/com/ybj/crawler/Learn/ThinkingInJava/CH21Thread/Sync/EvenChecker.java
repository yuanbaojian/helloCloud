package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread.Sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author EvenChecker
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class EvenChecker implements Runnable{

    private IntGenerator generator;

    private final int id;

    public EvenChecker(IntGenerator generator, int ident) {
        this.generator = generator;
        this.id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val +  " 不是偶数");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator gp , int count){
        System.out.println("开始啦 ， 按 ctrl + c 退出" );
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(gp,i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator gp){
        test(gp, 10);
    }


}
