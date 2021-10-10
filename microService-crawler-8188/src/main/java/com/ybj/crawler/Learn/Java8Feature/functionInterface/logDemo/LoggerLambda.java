package com.ybj.crawler.Learn.Java8Feature.functionInterface.logDemo;

public class LoggerLambda {

    public static void main(String[] args) {
        String msg1 = " 11";
        String msg2 = " 22";
        String msg3 = " 33";
        /*
        * 只有满足 level ==1 才会调用messageBuilder中的方法
        * 因为msg1 是main方法中定义的变量，所以lambda能够访问到
        * 所以lambda中没有入参
        * */
        show(1, () -> msg1 + msg2 + msg3);
    }

    static void show(int level,MessageBuilder messageBuilder){
        if(level ==1){
            System.out.println("messageBuilder = " + messageBuilder.buildMessage());
        }
    }
}
