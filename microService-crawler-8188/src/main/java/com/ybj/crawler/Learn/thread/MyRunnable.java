package com.ybj.crawler.Learn.thread;

import lombok.Builder;

/**
 * 继承Runnable ，实现run方法
 * 只能调用run方法
 * @Author MyRunnable
 * @Date $ $
 * @Param $
 * @return $
 **/
@Builder
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("true = " + true);
    }

    public static void main(String[] args) {
        MyRunnable build = MyRunnable.builder().build();
        build.run();
    }
}
