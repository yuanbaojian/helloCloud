package com.ybj.crawler.Learn.thread.Queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author BlockQueueDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class BlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        try {
            blockingQueue.put(1);
            blockingQueue.put(2);
            //会阻塞
            blockingQueue.put(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("blockingQueue = " + blockingQueue);
    }


    @Test
    public void testOfferByTime(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        try {
            blockingQueue.offer(1,1, TimeUnit.SECONDS);
            boolean offer2 = blockingQueue.offer(2,1, TimeUnit.SECONDS);
            boolean offer3 = blockingQueue.offer(3,4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //会阻塞
        System.out.println("blockingQueue = " + blockingQueue);
    }

    @Test
    public void testOffer(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.offer(1);
        boolean offer2 = blockingQueue.offer(2);
        //会阻塞
        boolean offer3 = blockingQueue.offer(3);
        System.out.println("blockingQueue = " + blockingQueue);
    }
    @Test
    public void testAdd(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        boolean add1 = blockingQueue.add(1);
        boolean add2 = blockingQueue.add(2);
        //会阻塞
        boolean add3 = blockingQueue.add(3);
        System.out.println("blockingQueue = " + blockingQueue);
    }


    @Test
    public void testTake(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        //会阻塞
        try {
            Integer take = blockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("blockingQueue = " + blockingQueue);
    }

    @Test
    public void testPollByTime(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        //会阻塞
        try {
            Integer take = blockingQueue.poll(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("blockingQueue = " + blockingQueue);
    }

    @Test
    public void testPoll(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        //result返回null， 不会阻塞
        Integer result = blockingQueue.poll();
        System.out.println("blockingQueue = " + blockingQueue);
    }

    @Test
    public void testPeek(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        //result返回null， 不会阻塞
        Integer result = blockingQueue.peek();
        System.out.println("blockingQueue = " + blockingQueue);
    }


}
