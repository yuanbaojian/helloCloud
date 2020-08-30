package com.ybj.algorithm.leetcode.thread;

import java.util.Scanner;

/**
 * @Author Foo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
class Foo {

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        while(true){
            Foo foo = new Foo();
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            String numberString = input.substring(1, input.length()-1);
            String[] numberArray = numberString.split(",");

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
            System.out.println("numberString = " + numberString);
        }

    }
}
