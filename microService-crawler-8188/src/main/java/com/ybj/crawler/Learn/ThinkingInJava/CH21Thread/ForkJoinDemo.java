package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Owner yuanbaojian
 * @Team
 */
public class ForkJoinDemo {

    @AllArgsConstructor
    public static  class MyTask extends RecursiveTask<Integer> {

        int[] innerSrc;

        @Override
        protected Integer compute() {
            if(innerSrc.length<2){
                if(innerSrc.length==1){
                    return innerSrc[0];
                } else{
                    return innerSrc[0] > innerSrc[1] ? innerSrc[0] :innerSrc[1];
                }
            } else {
                //进行任务拆分
                int mid = innerSrc.length / 2;
                MyTask leftTask = new MyTask(Arrays.copyOf(innerSrc,mid));
                MyTask rightTask = new MyTask(Arrays.copyOfRange(innerSrc,mid,innerSrc.length));
                invokeAll(leftTask,rightTask);
                return leftTask.join() > rightTask.join() ? leftTask.join() : rightTask.join();
            }
        }
    }

    public static void main(String[] args) {
        int [] src = {1,2,3,4,5,6,77,88,4,2};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask totalTask = new MyTask(src);
        forkJoinPool.invoke(totalTask);
        System.out.println("totalTask.join() = " + totalTask.join());
    }
}
