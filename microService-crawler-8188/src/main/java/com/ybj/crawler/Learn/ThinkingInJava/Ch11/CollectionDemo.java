package com.ybj.crawler.Learn.ThinkingInJava.Ch11;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author CollectionDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CollectionDemo {

    @Test
    public void test(){
        List collection=new ArrayList();
    }

    @Test
    public void queueDemo(){
        Queue queue=new ConcurrentLinkedQueue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        System.out.println("queue = " + queue);
    }
    
    @Test
    public void ListDemo(){
        List list=new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("d");

        List list2=new ArrayList();
        list2.add("a");
        System.out.println("list = " + list);
    }
    
}
