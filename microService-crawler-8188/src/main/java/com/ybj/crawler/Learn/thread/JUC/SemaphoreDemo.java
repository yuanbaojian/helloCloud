package com.ybj.crawler.Learn.thread.JUC;

import com.ybj.crawler.utils.videoConverter.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author SemaphoreDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(6);
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
