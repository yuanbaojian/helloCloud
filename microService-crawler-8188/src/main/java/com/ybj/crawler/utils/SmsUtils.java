package com.ybj.crawler.utils;


import org.openjdk.jol.info.ClassLayout;

public class SmsUtils {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
