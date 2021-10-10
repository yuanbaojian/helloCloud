package com.ybj.crawler.Learn.Java8Feature.functionInterface.supplier;

import java.util.function.Supplier;

public class demo {
    public static void main(String[] args) {
        Object string = getString(() -> "hello");
        System.out.println("string = " + string);
    }

    static Object getString(Supplier<?> supplier){
        return supplier.get();
    }
}
