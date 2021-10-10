package com.ybj.crawler.Learn.ThinkingInJava.Ch17;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @Author LinklistDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LinklistDemo {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(args);
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add("hello");
        list.add("world");
        list.add(null);
        list.remove(null);
        System.out.println("list = " + list);
        String.join(",",list);
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

    @Test
    public void test2(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        String join = StringUtils.join(list, ",");
        String.join(",", (CharSequence) list);
        System.out.println("list = " + list);
    }


    @Test
    public void test3(){
        List<Integer> integers = Collections.singletonList(null);
        System.out.println("integers = " + integers);
    }

    @Test
    public void test4(){
        Map<String, String> map =new HashMap<>();
        map.put("1","1");
        System.out.println("map = " + map);

    }



}
