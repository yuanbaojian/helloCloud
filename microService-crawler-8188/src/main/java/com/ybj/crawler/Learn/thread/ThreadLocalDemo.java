package com.ybj.crawler.Learn.thread;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ThreadLocalDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ThreadLocalDemo {

    String localVariable = "hello";

    ThreadLocal<String> tenantId = new ThreadLocal<>();

    public static InheritableThreadLocal<String> inheritableTenantId = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        new TransmittableThreadLocal();
        final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();

        inheritableTenantId.set("t1");
        String s = inheritableTenantId.get();
        inheritableThreadLocal.remove();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(()->{
                String variable = threadLocalDemo.localVariable;
                variable += finalI;
                System.out.println(Thread.currentThread().getName() + "   " + variable);
            });
        }
    }
}
