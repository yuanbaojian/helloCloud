package com.ybj.crawler.Learn.ThinkingInJava.Ch17;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author LinklistDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LinklistDemo {


    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add("hello");
        list.add("world");
        list.add(null);
        list.remove(null);
        System.out.println("list = " + list);
    }

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("s");
        list.add("d");
        list.add("f");

        for(int i = 0; i < list.size(); i++) {
            list.remove(i);
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println("next = " + next);
            iterator.remove();
        }
        System.out.println("list.toString() = " + list.toString());
    }
}
