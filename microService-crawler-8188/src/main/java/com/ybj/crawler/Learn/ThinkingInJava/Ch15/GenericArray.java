package com.ybj.crawler.Learn.ThinkingInJava.Ch15;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GenericArray
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class GenericArray<T> {
    private List<T> array = new ArrayList<>();

    public void add(T item){
        array.add(item);
    }

    public T get(int index){
        return array.get(index);
    }

    public static void main(String[] args) {
        GenericArray<String> genericArray = new GenericArray<>();
        genericArray.add("hello");
        genericArray.add("world");
        String value = genericArray.get(0);
        System.out.println("value = " + value);
    }
}
