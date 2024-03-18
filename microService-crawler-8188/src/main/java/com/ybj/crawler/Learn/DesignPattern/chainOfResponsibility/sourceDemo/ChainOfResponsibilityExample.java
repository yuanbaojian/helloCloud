package com.ybj.crawler.Learn.DesignPattern.chainOfResponsibility.sourceDemo;

public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            System.out.println("Exception caught in main: " + e.getMessage());
        }
    }

    public static void method1() throws Exception {
        try {
            method2();
        } catch (Exception e) {
            System.out.println("Exception caught in method1: " + e.getMessage());
            throw e;
        }
    }

    public static void method2() throws Exception {
        try {
            // 这里模拟一个可能抛出异常的操作
            int result = 1 / 0;
        } catch (Exception e) {
            System.out.println("Exception caught in method2: " + e.getMessage());
            throw e;
        }
    }
}
