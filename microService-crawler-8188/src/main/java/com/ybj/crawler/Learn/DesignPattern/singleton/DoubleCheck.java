package com.ybj.crawler.Learn.DesignPattern.singleton;

/**
 * @Author DoubleCheck
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DoubleCheck {

    // 使用volatile ，避免指令重排
    private static volatile DoubleCheck singleton;

    // 双重检测锁
    public static  DoubleCheck getInstance(){
        if(singleton == null){
            synchronized (DoubleCheck.class){
                if(singleton == null){
                    singleton = new DoubleCheck();
                }
            }
        }
        return singleton;
    }
}
