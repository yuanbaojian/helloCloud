package com.ybj.crawler.Learn.springBoot.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用于事务测试
 **/
public class TransactionDemo {

    @Transactional(propagation = Propagation.MANDATORY,isolation = Isolation.DEFAULT)
    @Test
    public void test(){

    }
}
