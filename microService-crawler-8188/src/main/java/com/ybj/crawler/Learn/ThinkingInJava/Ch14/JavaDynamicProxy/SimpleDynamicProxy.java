package com.ybj.crawler.Learn.ThinkingInJava.Ch14.JavaDynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @Author SimpleDynamicProxy
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SimpleDynamicProxy {

    public static void consumer(Interface iface){
        iface.dosomething();;
        iface.somethingElse("hello from simpleDynamicProxy");
    }

    public static void main(String[] args) {

        RealObject realObject = new RealObject();
        consumer(realObject);

        //动态代理
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader() ,
                new Class[]{Interface.class},
                new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
