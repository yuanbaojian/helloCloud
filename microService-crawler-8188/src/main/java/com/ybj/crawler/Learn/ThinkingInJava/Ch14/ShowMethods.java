package com.ybj.crawler.Learn.ThinkingInJava.Ch14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author ShowMethods
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ShowMethods {

    public static void main(String[] args) {
        if(args.length < 1){
            System.exit(0);
        }
        int lines=0;
        try {
            Class<?> c=Class.forName(args[0]);
            Method[] methods=c.getMethods();
            Constructor[] constructors=c.getConstructors();
            System.out.println("constructors = " + constructors);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
