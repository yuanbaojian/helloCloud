package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread.Sync;

/**
 * @Author EvenGenerator
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
