package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread.Sync;

/**
 * @Author EvenGenerator
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
