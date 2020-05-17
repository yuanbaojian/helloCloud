package com.ybj.crawler.Learn.thread.Sync;

/**
 * @Author SyncDemo1
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SyncDemo1  implements Runnable {

    private int count = 10;
    private Object o = new Object();



    public static void main(String[] args) {
        SyncDemo1 syncDemo1 = new SyncDemo1();
        for(int i = 0; i < 100 ; i++) {
            new Thread(syncDemo1, "-线程").start();
        }
    }

    public void test1(){

    }

    @Override
    public  void run() {
        synchronized (o){
            count++;
            System.out.println(Thread.currentThread().getName() + "  count ="  + count);
        }
    }
}
