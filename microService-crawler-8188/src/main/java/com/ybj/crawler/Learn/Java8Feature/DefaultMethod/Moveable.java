package com.ybj.crawler.Learn.Java8Feature.DefaultMethod;

public interface Moveable {

    default void move() {
        System.out.println("I 'm  moving");
    }

}
