package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread.Sync;

/**
 * @Author IntGenerator
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public abstract class IntGenerator {

    // volatile  可见性
    private volatile boolean canceled = false;

    public abstract  int next();

    public void cancel() {
        this.canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }
}
