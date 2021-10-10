package com.ybj.crawler.Learn.Java8Feature.functionInterface.logDemo;

import java.util.Comparator;

public class Demo3Comparator {
    public static void main(String[] args) {
        String[] array = {"aa","bbb","ccc"};


    }

    public static Comparator<String> getComparator(){
        /*
        匿名内部类
        * */
        new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        };

        /*
        * lambda表达式
        * */
        return (String o1 , String o2) ->  o2.length() - o1.length();
    }
}
