package com.ybj.crawler.Learn.ThinkingInJava.CH21Thread;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            futures.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : futures) {
            String s = future.get();
            System.out.println("s = " + s);
        }
    }
}
class TaskWithResult implements Callable{

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public Object call() throws Exception {
        return "the result of TaskWithResult is " + id;
    }
}