package com.ybj.crawler.Learn.DesignPattern.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author LisonCompany
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LisonCompany implements InvocationHandler {

    //代理的对象
    private Object factory;

    public LisonCompany(Object factory) {
        this.factory = factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(factory.getClass().getClassLoader() , factory.getClass().getInterfaces() , this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        //反射调用对象的方法
        Object ret = method.invoke(factory, args);
        doSomethingAfter();
        return ret;
    }

    private void doSomethingAfter() {
        System.out.println("完善的售后");
    }

    private void doSomethingBefore() {
        System.out.println("良好的售前");
    }
}
