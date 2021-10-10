package com.ybj.crawler.Learn.Java8Feature.functionInterface.function;


import org.junit.Test;

public class demo {
    public static void main(String[] args) {
        GreetingService greetingService = new GreetingService() {
            @Override
            public void say(String message) {
                System.out.println(" hello " + message);
            }
        };
        greetingService.say("llll");

        /*
        1. 定义一个实现类
        2. 重写方法
        3. 进行调用
        * */
        show(new GreetingServiceImpl());

        /*
        * 1. 不定义实现类
        * 2. 上匿名函数
        * */
        show(new GreetingService() {
            @Override
            public void say(String message) {
                System.out.println(" hello " + message);
            }
        });

        /*
        * 直接用lambda表达式
        * */
        show(a -> System.out.println(" hello " + a));

    }


    /**
     * 函数式接口：将接口作为方法的参数或返回值
     * @param greetingService
     */
    public static void show(GreetingService greetingService){
        greetingService.say("ybj");
    }

}
