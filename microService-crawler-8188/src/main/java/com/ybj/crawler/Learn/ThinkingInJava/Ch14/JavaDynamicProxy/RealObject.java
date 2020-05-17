package com.ybj.crawler.Learn.ThinkingInJava.Ch14.JavaDynamicProxy;

/**
 * @Author RealObject
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class RealObject implements Interface {
    @Override
    public void dosomething() {
        System.out.println("do something");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("do somethingElse  " + arg);
    }
}
