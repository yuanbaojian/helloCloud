package com.ybj.crawler.Learn.DesignPattern.singleton;

public class EnumSingleton {

    public static void main(String[] args) {
        Singleton.INSTANCE.doSomething();

    }
}

enum Singleton {
    INSTANCE,
    TWO;
    public void doSomething() {
        System.out.println("do something");
    }
}