package com.ybj.crawler.Learn.DesignPattern.singleton;

/**
 * @Author DoubleCheck
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DoubleCheck {

//    保证可见性（立即刷新到主存，其他线程可见）
    private static volatile DoubleCheck singleton;

//    双重检测锁
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
