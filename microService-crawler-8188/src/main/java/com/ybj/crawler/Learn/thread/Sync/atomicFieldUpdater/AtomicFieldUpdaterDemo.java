package com.ybj.crawler.Learn.thread.Sync.atomicFieldUpdater;

import javafx.scene.control.Alert;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author AtomicFieldUpdaterDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class AtomicFieldUpdaterDemo {
    @Getter
    static class Alex{
        volatile int salary;
    }

    public static void main(String[] args) {
        //1. 通过newUpdater创建 AtomicIntegerFieldUpdater对象
        AtomicIntegerFieldUpdater<Alex> updater = AtomicIntegerFieldUpdater.newUpdater(Alex.class, "salary");

        //2. 实例化Alex
        Alex alex = new Alex();

        int result = updater.addAndGet(alex, 3);
        System.out.println("result = " + result);
    }
}
