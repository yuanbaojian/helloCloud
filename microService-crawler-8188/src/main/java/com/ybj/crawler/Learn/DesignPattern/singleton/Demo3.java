package com.ybj.crawler.Learn.DesignPattern.singleton;

/**
 * @Author Demo3
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Demo3 {
    private Demo3(){}

    private static Demo3 demo = null;

//    加上synchronized可以线程安全，但是效率降低
    public synchronized Demo3 getInstance(){
        if(demo == null){
            demo = new Demo3();
        }
        return demo;
    }
}
