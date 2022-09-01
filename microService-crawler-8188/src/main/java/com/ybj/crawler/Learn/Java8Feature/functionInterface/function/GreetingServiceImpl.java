package com.ybj.crawler.Learn.Java8Feature.functionInterface.function;

public class GreetingServiceImpl implements GreetingService{
    @Override
    public void say(String message) {
        System.out.println(" hello  " + message);
    }
}
