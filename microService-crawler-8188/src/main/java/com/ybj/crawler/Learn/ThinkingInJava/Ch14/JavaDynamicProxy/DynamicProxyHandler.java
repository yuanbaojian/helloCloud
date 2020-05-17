package com.ybj.crawler.Learn.ThinkingInJava.Ch14.JavaDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author DynamicProxyHandler
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class DynamicProxyHandler implements InvocationHandler {

    //代理对象
    private Object proxied;

    //带参构造器
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.format("-----proxy:   %s   %s ", proxy.getClass(),method );
        if(args != null){
            for (Object arg : args) {
                System.out.println("arg = " + arg);
            }
        }
        return method.invoke(proxied , args);
    }
}
