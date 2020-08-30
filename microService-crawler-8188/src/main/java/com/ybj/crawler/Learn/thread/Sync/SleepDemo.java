package com.ybj.crawler.Learn.thread.Sync;

/**
 * @Author SleepDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SleepDemo {

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            testSleep();
            System.out.println(i + "执行完毕");
        }
    }

    private static void testSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
