package com.ybj.crawler.Learn.ThinkingInJava.polymorphic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Owner yuanbaojian
 * @Team
 */
@Slf4j
public class PolymorphicDemo {

    @Data
    static class Father{
        String name = "爸爸";
    }

    @Data
    static class Son extends Father{
        String name = "儿子";
        String age = "22";
    }

    @Data
    static class Daughter extends Father{
        String name = "女儿";
    }

    /**
     * 内部类实例化，需要先实例化外部类
     */
    class Mother {

    }

    public static void main(String[] args) {
        /**
         * 向上转型， 子类保留与父类中相同的方法
         */
        Father person1 = new Son();
        log.info("person1 为 {}",person1.toString());
        Father person2 = new Daughter();
        log.info("person2 为 {}",person2.toString());

        // 向下转型
        Son son = (Son) person1;
    }
}
