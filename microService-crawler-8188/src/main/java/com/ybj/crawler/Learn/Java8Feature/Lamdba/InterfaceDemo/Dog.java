package com.ybj.crawler.Learn.Java8Feature.Lamdba.InterfaceDemo;

public class Dog implements Animal {
    @Override
    public void move(String name) {
        System.out.println(name + "是一只狗," +"用腿跑");
    }

}
