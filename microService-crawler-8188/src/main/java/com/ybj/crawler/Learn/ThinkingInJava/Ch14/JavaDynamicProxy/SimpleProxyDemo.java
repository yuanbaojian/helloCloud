package com.ybj.crawler.Learn.ThinkingInJava.Ch14.JavaDynamicProxy;

/**
 * @Author SimpleProxyDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SimpleProxyDemo {

    static void consumer(Interface iface){
        iface.dosomething();
        iface.somethingElse("from simpleProxy");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
