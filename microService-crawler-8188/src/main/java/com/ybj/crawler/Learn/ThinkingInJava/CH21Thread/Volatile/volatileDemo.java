package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread.Volatile;

import lombok.Data;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author volatileDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class volatileDemo {

    volatile ArrayList list = new ArrayList<String>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        volatileDemo demo = new volatileDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    demo.add(new Object());
                    System.out.println("已添加到 " + i);
                }
                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("开始监测");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(demo.size() == 5){
                        break;
                    }
                }
                System.out.println("t2  数组已满5个成员" );
            }
        },"t2").start();
    }
}
