package com.ybj.crawler.Learn.thread.GetResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author getResult
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class getResult {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
      ExecutorService executorService = Executors.newFixedThreadPool(10);
      Runnable task = new Runnable() {
          @Override
          public void run() {
              System.out.println("执行ing");
          }
      };
      for(int i = 0; i < 100; i++) {
          Future future = executorService.submit(task);
          Object o = future.get();
      }
      System.out.println("线程全部结束");
  }

}
