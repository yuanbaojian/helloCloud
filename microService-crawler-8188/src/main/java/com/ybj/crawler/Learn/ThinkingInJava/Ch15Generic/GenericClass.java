package com.ybj.crawler.Learn.ThinkingInJava.Ch15Generic;

import org.testng.annotations.Test;

/***   范型类， 在类名后加<T> 表示是泛型类
 * @param null
 * @return
 * @author baojian.yuan
 * @date 2019/11/21
 */
public class GenericClass<T> {

    public T getT(){
        return t;
    }


    private T t;

    public <T> void sout(){
        System.out.println("t = " + t);
    }

    @Test
    public void testGenericClass(){
        GenericClass<String> Class1=new GenericClass<String>();
        Class1.t="I am String";
        System.out.println("Class1.t.getClass().getName() = " + Class1.t.getClass().getName());

        GenericClass<Integer> Class2=new GenericClass<Integer>();
        Class2.t=11;
        System.out.println("Class2.t.getClass().getName() = " + Class2.t.getClass().getName());
    }


    class child<T> extends GenericClass{

        @Override
        public Object getT(){
            return  t;
        }


    }

    class StringData extends GenericClass<String>{

    }

}
