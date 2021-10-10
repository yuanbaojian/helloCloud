package com.ybj.crawler.Learn.Java8Feature.functionInterface.logDemo;

import org.assertj.core.error.ShouldAccept;
import org.assertj.core.error.ShouldBeWritable;

public class LoggerDemo {

    public static void main(String[] args) {
        String msg1 = " 11";
        String msg2 = " 22";
        String msg3 = " 33";
        show(2,msg1+msg2+msg3);
    }


    static void show(int level, String message){
        if(level ==1 ){
            System.out.println("message = " + message);
        }
    }
}
