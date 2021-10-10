package com.ybj.crawler.Learn.Java8Feature.functionInterface.supplier;

import java.util.*;
import java.util.function.Supplier;

public class demo2 {

    static int getMax(Supplier<Integer> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        int[] array = { 1, 2,3,4,5,6};
        List<String> list = new LinkedList<>();
        Map map = new HashMap();
        Object orDefault = map.getOrDefault("3", "2");
        Comparator comparator = null;
        list.sort(Comparator.comparingInt(String::length));
    }
}
