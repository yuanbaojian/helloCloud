package com.ybj.spring.controller;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {

    public static void main(String[] args) {
        String string = "hello";
        ApplicationControllerTest applicationControllerTest1 = new ApplicationControllerTest();
        ApplicationControllerTest applicationControllerTest2 = new ApplicationControllerTest();
        System.out.println(ClassLayout.parseInstance(applicationControllerTest1).toPrintable());
        System.out.println(ClassLayout.parseInstance(applicationControllerTest2).toPrintable());
    }
}