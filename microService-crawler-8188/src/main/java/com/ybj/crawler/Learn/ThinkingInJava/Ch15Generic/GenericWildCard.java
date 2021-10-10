package com.ybj.crawler.Learn.ThinkingInJava.Ch15Generic;

import lombok.Data;

/**
 * @Owner yuanbaojian
 * @Team
 */
@Data
public class GenericWildCard <T>{

    T first;

    public static void showBoxByWildCard(GenericWildCard<? extends Number> numberGenericWildCard){
        Number first = numberGenericWildCard.getFirst();
        System.out.println("first = " + first);
    }

    public static <T> void showFirstByGeneric(GenericWildCard<T> t){
        System.out.println("first = " + t);
    }

    public static  void showFirstByType(Number t){
        System.out.println("first = " + t);
    }

    public static  void showFirstByGenericType(GenericWildCard<Number> t){
        System.out.println("first = " + t);
    }

    public static void main(String[] args) {
        GenericWildCard<Number> box1 = new GenericWildCard<>();
        box1.setFirst(1000);
        showFirstByGenericType(box1);


        GenericWildCard<Integer> box2 = new GenericWildCard<>();
        Integer integer = new Integer(100);
        box2.setFirst(integer);
        //  GenericWildCard<Number> 不是GenericWildCard<Integer>的父类！！！！！！
        // showFirstByGenericType(box2);


        //父类能出现的地方，子类一般能出现
        Number i =1;
        showFirstByType(i);
        Integer j = 2;
        showFirstByType(j);


        showFirstByGeneric(box1);
        showFirstByGeneric(box2);

        showBoxByWildCard(box1);
        showBoxByWildCard(box2);

    }

}
