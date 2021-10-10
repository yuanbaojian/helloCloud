package com.ybj.algorithm.leetcode.thread;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Foo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Foo {

    public Foo() {

    }

    private CountDownLatch second = new CountDownLatch(1);

    private CountDownLatch third = new CountDownLatch(1);

    /**
     * 传入一个runnable对象， 这个线程可以运行任务
     * @param printFirst
     * @throws InterruptedException
     */
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        second.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        third.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        while(true){
            Foo foo = new Foo();
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            // String numberString = input.substring(1, input.length());
            String[] numberArray = input.split(",");

            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(" one " );
                }
            };
            Runnable runnable2 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(" two " );
                }
            };
            Runnable runnable3 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(" three " );
                }
            };

            for (String number : numberArray) {
                if(number.equals("1")){
                    foo.first(runnable1);
                } else if(number.equals("2")){
                    foo.first(runnable2);
                }else if(number.equals("3")){
                    foo.first(runnable3);
                }
            }
            // System.out.println("numberString = " + numberString);
        }

    }
}
