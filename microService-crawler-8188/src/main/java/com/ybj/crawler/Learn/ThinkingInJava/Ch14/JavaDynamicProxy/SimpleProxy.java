package com.ybj.crawler.Learn.ThinkingInJava.Ch14.JavaDynamicProxy;

/**
 * @Author SimpleProxy
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void dosomething() {
        System.out.println("代理中");
        proxied.dosomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("代理中");
        proxied.somethingElse(arg);
    }
}
