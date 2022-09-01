package com.ybj.crawler.Learn.DesignPattern;

public class SingletonDesign {

    private static volatile SingletonDesign singletonDesign = null;

    public static SingletonDesign getSingleton(){
        if(singletonDesign==null){
            synchronized (SingletonDesign.class){
                if(singletonDesign==null){
                    singletonDesign = new SingletonDesign();
                }
            }
        }
       return singletonDesign;
    }

}
