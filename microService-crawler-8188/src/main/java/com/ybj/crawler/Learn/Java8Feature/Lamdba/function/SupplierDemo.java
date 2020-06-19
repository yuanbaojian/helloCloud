package com.ybj.crawler.Learn.Java8Feature.Lamdba.function;

import java.util.function.Supplier;

/**
 * @Author SupplierDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        };
        Object o = supplier.get();
        System.out.println("o = " + o);
    }
}
